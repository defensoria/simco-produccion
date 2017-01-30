/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.controller;

import gob.dp.simco.administracion.seguridad.controller.BusquedaUsuarioController;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import gob.dp.simco.comun.ConstantesUtil;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import gob.dp.simco.registro.entity.ActaAcuerdo;
import gob.dp.simco.registro.entity.ActaAcuerdoDetalle;
import gob.dp.simco.registro.entity.ActaAcuerdoDetalleMiembro;
import gob.dp.simco.registro.entity.Actividad;
import gob.dp.simco.registro.entity.ActividadActaAcuerdo;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.ActorAcuerdo;
import gob.dp.simco.registro.service.ActaAcuerdoDetalleMiembroService;
import gob.dp.simco.registro.service.ActaAcuerdoDetalleService;
import gob.dp.simco.registro.service.ActaAcuerdoService;
import gob.dp.simco.registro.service.ActividadActaAcuerdoService;
import gob.dp.simco.registro.service.ActividadService;
import gob.dp.simco.registro.service.ActorAcuerdoService;
import gob.dp.simco.registro.service.ActorService;
import gob.dp.simco.seguimiento.entity.SeguimientoAcuerdo;
import gob.dp.simco.seguimiento.service.SeguimientoAcuerdoService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class ActaAcuerdoController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(ActaAcuerdoController.class);

    private ActaAcuerdo actaAcuerdo;

    private ActaAcuerdoDetalle actaAcuerdoDetalle;

    private SeguimientoAcuerdo seguimientoAcuerdo;

    private Usuario usuarioSession;

    private Actor actor;

    private List<Actor> listaActoresActividad;

    private List<ActaAcuerdoDetalleMiembro> listaActaAcuerdoDetalleMiembro;

    private List<Actividad> listaActividadxActaAcuerdo;

    private List<Actividad> listaActividadxActaAcuerdoTotal;

    private List<ActaAcuerdoDetalle> listaActaAcuerdoDetalleXActa;

    private List<Actor> listaActorAcuerdoDetalle;

    private boolean verVinculosMantenimientoActaAcuerdo = false;

    private boolean verVinculoActividad = false;

    private boolean verBoton = false;

    private boolean verBotonActividad = false;

    private boolean verBotonEditar = false;

    private int indice = 0;

    String cadenaAutocomplete = "";

    int tip = 1;

    private Part file1;

    @Autowired
    private ActaAcuerdoService actaAcuerdoService;

    @Autowired
    private ActividadActaAcuerdoService actividadActaAcuerdoService;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private ActaAcuerdoDetalleService actaAcuerdoDetalleService;

    @Autowired
    private ActorAcuerdoService actorAcuerdoService;

    @Autowired
    private SeguimientoAcuerdoService seguimientoAcuerdoService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActaAcuerdoDetalleMiembroService actaAcuerdoDetalleMiembroService;

    public ActaAcuerdoController() {
        seguimientoAcuerdo = new SeguimientoAcuerdo();
        actaAcuerdoDetalle = new ActaAcuerdoDetalle();
        actaAcuerdoDetalle.setSeguimientoAcuerdo(seguimientoAcuerdo);
        actaAcuerdoDetalle.setIndCumplimiento(false);
        actaAcuerdoDetalle.setIndAlertar(false);
        listaActaAcuerdoDetalleXActa = new ArrayList<>();
    }

    public String cargarPagina(int tipo) {
        actaAcuerdo = new ActaAcuerdo();
        verVinculosMantenimientoActaAcuerdo = false;
        limpiarListas();
        return "actaAcuerdoNuevo";
    }

    public String cargarRegistroAgregarAcuerdo(Long idActividad, int tip) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
            busquedaUsuarioController.listarPaginado(1L);
            setTip(tip);
            actor = new Actor();
            listaActoresActividad = new ArrayList<>();
            cargarListaMiembroUsuarioSession();
            actaAcuerdo = actaAcuerdoService.actaAcuerdoxActividadBuscarOne(idActividad);
            if (actaAcuerdo == null) {
                actaAcuerdo = new ActaAcuerdo();
            } else {
                cargarListaAcuerdoDetalle();
            }
            verBotonEditar = false;
            cadenaAutocomplete = actorService.actorXactividadSimpleBuscar(idActividad);
            cargarUsuariosSistema();
            return "registroAgregarAcuerdo";
        } catch (Exception ex) {
            log.error(ex);
        }
        return null;
    }

    public String cargarRegistroAgregarAcuerdo2(Long idActividad, int tip) {
        try {
            setTip(tip);
            actaAcuerdo = actaAcuerdoService.actaAcuerdoxActividadBuscarOne(idActividad);
            cadenaAutocomplete = actorService.actorXactividadSimpleBuscar(idActividad);
            if (actaAcuerdo == null) {
                actaAcuerdo = new ActaAcuerdo();
            } else {
                cargarListaAcuerdoDetalle();
            }
            cargarListaMiembroUsuarioSession();
            cargarUsuariosSistema();
            return "registroAgregarAcuerdo";
        } catch (Exception ex) {
            log.error(ex.getCause());
        }
        return null;
    }

    public String cargarRegistroAgregarAcuerdoDetalle(Long idActividad, ActaAcuerdoDetalle aad) {
        try {
            actaAcuerdo = actaAcuerdoService.actaAcuerdoxActividadBuscarOne(idActividad);
            cadenaAutocomplete = actorService.actorXactividadSimpleBuscar(idActividad);
            if (actaAcuerdo == null) {
                actaAcuerdo = new ActaAcuerdo();
            } else {
                cargarListaAcuerdoDetalle();
            }
            cargarUsuariosSistema();
            editActaAcuerdoxActividad(aad);
            return "seguimientoAcuerdoDetalle";
        } catch (Exception ex) {
            log.error(ex);
        }
        return null;
    }

    private void cargarListaAcuerdoDetalle() {
        listaActaAcuerdoDetalleXActa = actaAcuerdoDetalleService.actaAcuerdoDetalleBuscarxActa(actaAcuerdo.getId());
        for (ActaAcuerdoDetalle aad : listaActaAcuerdoDetalleXActa) {
            aad.setListaActor(actorService.actorxAcuerdoDetalleBusqueda(aad.getId(), 1));
            aad.setListaActorFin(actorService.actorxAcuerdoDetalleBusqueda(aad.getId(), 2));
            if (!aad.isIndAlertar()) {
                aad.setSeguimientoAcuerdo(seguimientoAcuerdoService.seguimientoAcuerdoBuscar(aad.getId()));
            }
        }
    }

    private void cargarUsuariosSistema() {
        FacesContext context = FacesContext.getCurrentInstance();
        BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
        busquedaUsuarioController.listarPaginado(1L);
    }

    private void cargarListaMiembroUsuarioSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        usuarioSession = registroController.getUsuarioSession();
        listaActaAcuerdoDetalleMiembro = new ArrayList<>();
        ActaAcuerdoDetalleMiembro miembro = new ActaAcuerdoDetalleMiembro();
        miembro.setCodigo(usuarioSession.getCodigo());
        miembro.setNombre(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
        miembro.setEstado("ACT");
        listaActaAcuerdoDetalleMiembro.add(miembro);
    }

    public boolean addMiembroSeguimiento(Usuario usuario) {
        for (ActaAcuerdoDetalleMiembro aadm : listaActaAcuerdoDetalleMiembro) {
            if (StringUtils.equals(usuario.getCodigo(), aadm.getCodigo())) {
                return false;
            }
        }
        ActaAcuerdoDetalleMiembro miembro = new ActaAcuerdoDetalleMiembro();
        miembro.setCodigo(usuario.getCodigo());
        miembro.setEstado("ACT");
        miembro.setNombre(usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno());
        listaActaAcuerdoDetalleMiembro.add(miembro);
        return true;
    }

    public void removeMiembroSeguimiento(ActaAcuerdoDetalleMiembro miembro) {
        if (!StringUtils.equals(miembro.getCodigo(), usuarioSession.getCodigo())) {
            listaActaAcuerdoDetalleMiembro.remove(miembro);
            if (miembro.getId() != null) {
                miembro.setEstado("INA");
                actaAcuerdoDetalleMiembroService.actaAcuerdoDetalleMiembroUpdate(miembro);
            }
        }
    }

    public void limpiarActaAcuerdo() {
        actaAcuerdo = new ActaAcuerdo();
        actaAcuerdoDetalle = new ActaAcuerdoDetalle();
        seguimientoAcuerdo = new SeguimientoAcuerdo();
        verBotonEditar = false;
        listaActaAcuerdoDetalleXActa.clear();
        indice = 0;
    }

    private void limpiarListas() {
        listaActividadxActaAcuerdo = null;
        listaActividadxActaAcuerdoTotal = null;
    }

    public void registrarActaAcuerdo(long idActividad) {
        try {
            ActaAcuerdo aa = actaAcuerdoService.actaAcuerdoxActividadBuscarOne(idActividad);
            String ruta = uploadArchive(file1);
            if(ruta != null)
                actaAcuerdo.setRuta(ruta);
            if (aa != null) {
                actaAcuerdo.setId(aa.getId());
                actaAcuerdoService.actaAcuerdoModificar(actaAcuerdo);
            } else {
                actaAcuerdo.setCodigo(generarCodigoActaAcuerdo());
                actaAcuerdoService.actaAcuerdoNuevo(actaAcuerdo);
            }
        } catch (Exception e) {
            log.error("METODO : ActaAcuerdoController.registrarActaAcuerdo" + e.getMessage());
        }
    }

    public void agregarActaAcuerdoxActividad(Long idActividad) {
        actaAcuerdoDetalle.setActaAcuerdo(actaAcuerdo);
        if (actaAcuerdoDetalle.getFechaRegistro() == null) {
            actaAcuerdoDetalle.setFechaRegistro(new Date());
        }
        actaAcuerdoDetalle.setListaActor(vincularActaAcuerdoListaActoresView());
        actaAcuerdoDetalle.setListaActorFin(vincularActaAcuerdoListaActoresViewFin());
        actaAcuerdoDetalle.setCodigo(generarCodigoActaAcuerdoDetalle());
        actaAcuerdoDetalleService.actaAcuerdoDetalleInsertar(actaAcuerdoDetalle);
        registrarListaActores();
        registrarListaMiembros();
        listaActaAcuerdoDetalleXActa.add(actaAcuerdoDetalle);
        if (!actaAcuerdoDetalle.isIndAlertar()) {
            actaAcuerdoDetalle.setSeguimientoAcuerdo(seguimientoAcuerdo);
            seguimientoAcuerdo.setActaAcuerdoDetalle(actaAcuerdoDetalle);
            seguimientoAcuerdo.setEstado("ACT");
            registrarSeguimiento(seguimientoAcuerdo);
        }
        actaAcuerdoDetalle = null;
        actaAcuerdoDetalle = new ActaAcuerdoDetalle();
        seguimientoAcuerdo = new SeguimientoAcuerdo();
        limpiarListasActores();
        limpiarMiembros();
        msg.messageInfo("Se ha agregado el detalle", null);
    }

    private void registrarListaMiembros() {
        if (actaAcuerdoDetalle.getId() != null) {
            for (ActaAcuerdoDetalleMiembro detalleMiembro : listaActaAcuerdoDetalleMiembro) {
                if (detalleMiembro.getId() == null) {
                    detalleMiembro.setIdActaAcuerdoDetalle(actaAcuerdoDetalle.getId());
                    actaAcuerdoDetalleMiembroService.actaAcuerdoDetalleMiembroInsertar(detalleMiembro);
                } else {
                    actaAcuerdoDetalleMiembroService.actaAcuerdoDetalleMiembroUpdate(detalleMiembro);
                }
            }
        }
    }

    private String generarCodigoActaAcuerdoDetalle() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMM");
        String codigo = formato.format(new Date());
        String numero = String.format("%2s", actaAcuerdoDetalleService.actaAcuerdoDetalleCodigoGenerado().toString()).replace(' ', '0');
        return "AUD" + codigo + numero;
    }

    private void registrarListaActores() {
        for (Actor aa : actaAcuerdoDetalle.getListaActor()) {
            try {
                ActorAcuerdo actorAcuerdo = new ActorAcuerdo();
                actorAcuerdo.setActor(aa);
                actorAcuerdo.setActaAcuerdoDetalle(actaAcuerdoDetalle);
                actorAcuerdo.setTipo("INI");
                actorAcuerdo.setEstado("ACT");
                actorAcuerdoService.actorAcuerdoInsertar(actorAcuerdo);
            } catch (Exception ex) {
                log.error(ex.getCause());
            }
        }
        for (Actor aa : actaAcuerdoDetalle.getListaActorFin()) {
            try {
                ActorAcuerdo actorAcuerdo = new ActorAcuerdo();
                actorAcuerdo.setActor(aa);
                actorAcuerdo.setActaAcuerdoDetalle(actaAcuerdoDetalle);
                actorAcuerdo.setTipo("FIN");
                actorAcuerdo.setEstado("ACT");
                actorAcuerdoService.actorAcuerdoInsertar(actorAcuerdo);
            } catch (Exception ex) {
                log.error(ex.getCause());
            }
        }
    }

    private String generarCodigoActaAcuerdo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMM");
        String codigo = formato.format(new Date());
        String numero = String.format("%2s", actaAcuerdoService.actaAcuerdoCodigoGenerado().toString()).replace(' ', '0');
        return "AU" + codigo + numero;
    }

    public void limpiarListasActores() {
        FacesContext context = FacesContext.getCurrentInstance();
        ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
        actorController.limpiarListasActoresAcuerdos();
    }

    public void limpiarMiembros() {
        listaActaAcuerdoDetalleMiembro = new ArrayList<>();
        cargarListaMiembroUsuarioSession();
    }

    public void removeActaAcuerdoxActividad(ActaAcuerdoDetalle actaAcuerdoDetalle) {
        listaActaAcuerdoDetalleXActa.remove(actaAcuerdoDetalle);
        List<ActaAcuerdoDetalle> lista = new ArrayList<>();
        int i = 0;
        for (ActaAcuerdoDetalle detalle : listaActaAcuerdoDetalleXActa) {
            detalle.setIndiceModificacion(i);
            i++;
            lista.add(detalle);
        }
        setListaActaAcuerdoDetalleXActa(lista);
        indice--;
        if (actaAcuerdoDetalle.getId() != null) {
            if (actaAcuerdoDetalle.getSeguimientoAcuerdo() != null) {
                if (actaAcuerdoDetalle.getSeguimientoAcuerdo().getId() != null) {
                    seguimientoAcuerdoService.seguimientoAcuerdoDelete(actaAcuerdoDetalle.getSeguimientoAcuerdo().getId());
                }
            }
            try {
                actorAcuerdoService.actorAcuerdoDelete(actaAcuerdoDetalle.getId());
                actaAcuerdoDetalleService.actaAcuerdoDetalleDelete(actaAcuerdoDetalle.getId());
                msg.messageInfo("Se ha eliminado el detalle", null);
            } catch (Exception ex) {
                log.error("METODO : ActaAcuerdoController.removeActaAcuerdoxActividad" + ex.getMessage());
            }
        }
    }

    public void editActaAcuerdoxActividad(ActaAcuerdoDetalle aad) {
        FacesContext context = FacesContext.getCurrentInstance();
        ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
        setActaAcuerdoDetalle(aad);
        actorController.seteaListaActorSeleccionadoAcuerdoIni(aad.getListaActor());
        actorController.seteaListaActorSeleccionadoAcuerdoFin(aad.getListaActorFin());
        if (aad.getSeguimientoAcuerdo() != null) {
            seguimientoAcuerdo = aad.getSeguimientoAcuerdo();
        } else {
            seguimientoAcuerdo = new SeguimientoAcuerdo();
        }
        cargarMiembros();
        verBotonEditar = true;
    }

    public void modifyActaAcuerdoxActividad() {
        try {
            actaAcuerdoDetalle.setListaActor(vincularActaAcuerdoListaActoresView());
            actaAcuerdoDetalle.setListaActorFin(vincularActaAcuerdoListaActoresViewFin());
            actaAcuerdoDetalle.setSeguimientoAcuerdo(seguimientoAcuerdo);
            actaAcuerdoDetalleService.actaAcuerdoDetalleUpdate(actaAcuerdoDetalle);
            actorAcuerdoService.actorAcuerdoDelete(actaAcuerdoDetalle.getId());
            registrarListaActores();
            registrarListaMiembros();
        } catch (Exception ex) {
            log.error(ex.getCause());
        }
        if (!actaAcuerdoDetalle.isIndAlertar()) {
            actaAcuerdoDetalle.setSeguimientoAcuerdo(seguimientoAcuerdo);
            seguimientoAcuerdo.setActaAcuerdoDetalle(actaAcuerdoDetalle);
            seguimientoAcuerdo.setEstado("ACT");
            registrarSeguimiento(seguimientoAcuerdo);
        } else {
            if (seguimientoAcuerdo.getId() != null) {
                seguimientoAcuerdo.setEstado("ACT");
                registrarSeguimiento(seguimientoAcuerdo);
            }
        }
        actaAcuerdoDetalle = new ActaAcuerdoDetalle();
        seguimientoAcuerdo = new SeguimientoAcuerdo();
        limpiarListasActores();
        limpiarMiembros();
        verBotonEditar = false;
        msg.messageInfo("Se han registrado los cambios", null);
    }

    private void cargarMiembros() {
        listaActaAcuerdoDetalleMiembro = actaAcuerdoDetalleMiembroService.actaAcuerdoDetalleMiembroBuscar(actaAcuerdoDetalle.getId());
    }

    public void cancelarActaAcuerdoxActividad() {
        actaAcuerdoDetalle = new ActaAcuerdoDetalle();
        seguimientoAcuerdo = new SeguimientoAcuerdo();
        limpiarListasActores();
        limpiarMiembros();
        verBotonEditar = false;
    }

    public String registrarActaAcuerdoxActividad(Long idActividad) {
        registrarActaAcuerdo(idActividad);
        vincularActaAcuerdoxActividad(idActividad);
        registrarActaAcuerdoDetalle();
        return "registroNuevo";
    }

    public void registrarActaAcuerdoxActividad2(Long idActividad) {
        try {
            registrarActaAcuerdo(idActividad);
            vincularActaAcuerdoxActividad(idActividad);
            msg.messageInfo("Se han registrado los cambios", null);
        } catch (Exception e) {
            log.error(e.getCause());
        }
    }

    private String uploadArchive(Part fil) {
        String nameArchive = getFilename(fil);
        String extencion = getFileExtension(getFilename(fil));
        if (StringUtils.isNoneBlank(nameArchive)) {
            String formato = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
            String ruta = formato + extencion;
            File file = new File(ConstantesUtil.FILE_SYSTEM + ruta);
            try (InputStream input = fil.getInputStream()) {
                Files.copy(input, file.toPath());
            } catch (IOException ex) {
                log.error(ex);
            }
            return ruta;
        }
        return null;
    }

    private String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    private void registrarActaAcuerdoDetalle() {
        for (ActaAcuerdoDetalle aad : listaActaAcuerdoDetalleXActa) {
            aad.setActaAcuerdo(actaAcuerdo);
            registrarUpdateActaAcuerdoDetalle(aad);
            ActaAcuerdoDetalle a = new ActaAcuerdoDetalle();
            a.setId(aad.getId());
            aad.getSeguimientoAcuerdo().setActaAcuerdoDetalle(aad);
            aad.getSeguimientoAcuerdo().setEstado("PEN");
            if (!aad.isIndAlertar()) {
                registrarSeguimiento(aad.getSeguimientoAcuerdo());
            }
            for (ActaAcuerdoDetalle aad1 : listaActaAcuerdoDetalleXActa) {
                actaAcuerdoDetalle.setId(aad1.getId());
                for (Actor aa : aad1.getListaActor()) {
                    try {
                        ActorAcuerdo actorAcuerdo = new ActorAcuerdo();
                        actorAcuerdo.setActor(aa);
                        actorAcuerdo.setActaAcuerdoDetalle(actaAcuerdoDetalle);
                        actorAcuerdo.setTipo("INI");
                        actorAcuerdo.setEstado("ACT");
                        actorAcuerdoService.actorAcuerdoInsertar(actorAcuerdo);
                    } catch (Exception ex) {
                        log.error(ex.getCause());
                    }
                }
                for (Actor aa : aad1.getListaActorFin()) {
                    try {
                        ActorAcuerdo actorAcuerdo = new ActorAcuerdo();
                        actorAcuerdo.setActor(aa);
                        actorAcuerdo.setActaAcuerdoDetalle(actaAcuerdoDetalle);
                        actorAcuerdo.setTipo("FIN");
                        actorAcuerdo.setEstado("ACT");
                        actorAcuerdoService.actorAcuerdoInsertar(actorAcuerdo);
                    } catch (Exception ex) {
                        log.error(ex.getCause());
                    }
                }
            }
        }
    }

    private void registrarUpdateActaAcuerdoDetalle(ActaAcuerdoDetalle aad) {
        if (aad.getId() == null) {
            actaAcuerdoDetalleService.actaAcuerdoDetalleInsertar(aad);
            String codigo = String.format("%8s", aad.getId().toString()).replace(' ', '0');
            aad.setCodigo(codigo);
            actaAcuerdoDetalleService.actaAcuerdoDetalleUpdate(aad);
        } else {
            actaAcuerdoDetalleService.actaAcuerdoDetalleUpdate(aad);
        }
    }

    private void registrarSeguimiento(SeguimientoAcuerdo sa) {
        if (sa.getId() == null) {
            seguimientoAcuerdoService.seguimientoAcuerdoInsertar(sa);
        } else {
            seguimientoAcuerdoService.seguimientoAcuerdoUpdate(sa);
        }
    }

    private List<Actor> vincularActaAcuerdoListaActoresView() {
        FacesContext context = FacesContext.getCurrentInstance();
        ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
        List<Actor> listaAct = actorController.vincularListaActoresInicio();
        return listaAct;
    }

    private List<Actor> vincularActaAcuerdoListaActoresViewFin() {
        FacesContext context = FacesContext.getCurrentInstance();
        ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
        List<Actor> listaAct = actorController.vincularListaActoresFin();
        return listaAct;
    }

    private void vincularActaAcuerdoxActividad(Long idActividad) {
        ActividadActaAcuerdo actividadActaAcuerdo = new ActividadActaAcuerdo();
        Actividad actividad = new Actividad();
        actividad.setId(idActividad);
        actividadActaAcuerdo.setActividad(actividad);
        actividadActaAcuerdo.setActaAcuerdo(actaAcuerdo);
        actividadActaAcuerdo.setEstado("ACT");
        try {
            actividadActaAcuerdoService.actividadActaAcuerdoInsertarUpdate(actividadActaAcuerdo);
        } catch (Exception e) {
            log.error("ERROR : ActividadController.vincularActaAcuerdoxActividad: " + e.getMessage());
        }
    }

    private List<Actividad> listarActividadActaAcuerdoTotal() {
        List<Actividad> list = null;
        try {
            list = actividadService.actividadxActaAcuerdoBuscarTotal(actaAcuerdo.getId());
        } catch (Exception e) {
            log.error("ERROR : ActaAcuerdoController.listarActividadActaAcuerdoTotal: " + e.getMessage());
        }
        return list;
    }

    public void eliminarActividadActaAcuerdo(Long idActividad) {
        Actividad actividad = new Actividad();
        ActaAcuerdo aa = new ActaAcuerdo();
        aa.setId(actaAcuerdo.getId());
        actividad.setId(idActividad);
        ActividadActaAcuerdo actividadActaAcuerdo = new ActividadActaAcuerdo(actividad, aa, null);
        try {
            actividadActaAcuerdoService.actividadActaAcuerdoDelete(actividadActaAcuerdo);
            listaActividadxActaAcuerdoTotal = listarActividadActaAcuerdoTotal();
        } catch (Exception e) {
            log.error("METODO : ActaAcuerdoController.eliminarActividadActaAcuerdo" + e.getMessage());
        }
    }

    public ActaAcuerdoService getActaAcuerdoService() {
        return actaAcuerdoService;
    }

    public void setActaAcuerdoService(ActaAcuerdoService actaAcuerdoService) {
        this.actaAcuerdoService = actaAcuerdoService;
    }

    public List<Actividad> getListaActividadxActaAcuerdo() {
        return listaActividadxActaAcuerdo;
    }

    public void setListaActividadxActaAcuerdo(List<Actividad> listaActividadxActaAcuerdo) {
        this.listaActividadxActaAcuerdo = listaActividadxActaAcuerdo;
    }

    public List<Actividad> getListaActividadxActaAcuerdoTotal() {
        return listaActividadxActaAcuerdoTotal;
    }

    public void setListaActividadxActaAcuerdoTotal(List<Actividad> listaActividadxActaAcuerdoTotal) {
        this.listaActividadxActaAcuerdoTotal = listaActividadxActaAcuerdoTotal;
    }

    public boolean isVerVinculoActividad() {
        return verVinculoActividad;
    }

    public void setVerVinculoActividad(boolean verVinculoActividad) {
        this.verVinculoActividad = verVinculoActividad;
    }

    public boolean isVerBoton() {
        return verBoton;
    }

    public void setVerBoton(boolean verBoton) {
        this.verBoton = verBoton;
    }

    public boolean isVerBotonActividad() {
        return verBotonActividad;
    }

    public void setVerBotonActividad(boolean verBotonActividad) {
        this.verBotonActividad = verBotonActividad;
    }

    public boolean isVerVinculosMantenimientoActaAcuerdo() {
        return verVinculosMantenimientoActaAcuerdo;
    }

    public void setVerVinculosMantenimientoActaAcuerdo(boolean verVinculosMantenimientoActaAcuerdo) {
        this.verVinculosMantenimientoActaAcuerdo = verVinculosMantenimientoActaAcuerdo;
    }

    public ActaAcuerdoDetalle getActaAcuerdoDetalle() {
        return actaAcuerdoDetalle;
    }

    public void setActaAcuerdoDetalle(ActaAcuerdoDetalle actaAcuerdoDetalle) {
        this.actaAcuerdoDetalle = actaAcuerdoDetalle;
    }

    public List<ActaAcuerdoDetalle> getListaActaAcuerdoDetalleXActa() {
        return listaActaAcuerdoDetalleXActa;
    }

    public void setListaActaAcuerdoDetalleXActa(List<ActaAcuerdoDetalle> listaActaAcuerdoDetalleXActa) {
        this.listaActaAcuerdoDetalleXActa = listaActaAcuerdoDetalleXActa;
    }

    public List<Actor> getListaActorAcuerdoDetalle() {
        return listaActorAcuerdoDetalle;
    }

    public void setListaActorAcuerdoDetalle(List<Actor> listaActorAcuerdoDetalle) {
        this.listaActorAcuerdoDetalle = listaActorAcuerdoDetalle;
    }

    public boolean isVerBotonEditar() {
        return verBotonEditar;
    }

    public void setVerBotonEditar(boolean verBotonEditar) {
        this.verBotonEditar = verBotonEditar;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public SeguimientoAcuerdo getSeguimientoAcuerdo() {
        return seguimientoAcuerdo;
    }

    public void setSeguimientoAcuerdo(SeguimientoAcuerdo seguimientoAcuerdo) {
        this.seguimientoAcuerdo = seguimientoAcuerdo;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public List<Actor> getListaActoresActividad() {
        return listaActoresActividad;
    }

    public void setListaActoresActividad(List<Actor> listaActoresActividad) {
        this.listaActoresActividad = listaActoresActividad;
    }

    public String getCadenaAutocomplete() {
        return cadenaAutocomplete;
    }

    public void setCadenaAutocomplete(String cadenaAutocomplete) {
        this.cadenaAutocomplete = cadenaAutocomplete;
    }

    public ActaAcuerdo getActaAcuerdo() {
        return actaAcuerdo;
    }

    public void setActaAcuerdo(ActaAcuerdo actaAcuerdo) {
        this.actaAcuerdo = actaAcuerdo;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public List<ActaAcuerdoDetalleMiembro> getListaActaAcuerdoDetalleMiembro() {
        return listaActaAcuerdoDetalleMiembro;
    }

    public void setListaActaAcuerdoDetalleMiembro(List<ActaAcuerdoDetalleMiembro> listaActaAcuerdoDetalleMiembro) {
        this.listaActaAcuerdoDetalleMiembro = listaActaAcuerdoDetalleMiembro;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }
}
