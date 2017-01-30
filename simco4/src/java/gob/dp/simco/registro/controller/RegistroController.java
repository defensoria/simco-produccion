/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.controller;

import gob.dp.simco.administracion.seguridad.controller.LoginController;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import gob.dp.simco.comun.entity.Departamento;
import gob.dp.simco.comun.entity.Distrito;
import gob.dp.simco.comun.entity.Provincia;
import gob.dp.simco.comun.service.UbigeoService;
import gob.dp.simco.noticia.entity.Noticia;
import gob.dp.simco.noticia.service.NoticiaService;
import gob.dp.simco.comun.ConstantesUtil;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import gob.dp.simco.registro.entity.ActaAcuerdo;
import gob.dp.simco.registro.entity.Actividad;
import gob.dp.simco.registro.entity.ActividadCaso;
import gob.dp.simco.registro.entity.ActividadHistorial;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.entity.MedioVerificacion;
import gob.dp.simco.registro.entity.NoticiaRegistro;
import gob.dp.simco.registro.service.ActaAcuerdoDetalleService;
import gob.dp.simco.registro.service.ActaAcuerdoService;
import gob.dp.simco.registro.service.ActividadCasoService;
import gob.dp.simco.registro.service.ActividadHistorialService;
import gob.dp.simco.registro.service.ActividadService;
import gob.dp.simco.registro.service.CasoService;
import gob.dp.simco.registro.service.NoticiaRegistroService;
import gob.dp.simco.registro.type.HistorialType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;
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
public class RegistroController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroController.class);

    private Actividad actividad;

    private ActorTemp actorTemp;

    private ActividadBusquedaTemp actividadBusquedaTemp;

    private ActividadActorTemp actividadActorTemp;

    private ActividadCaso actividadCaso;

    private Caso caso;

    private Noticia noticia;

    private List<Actor> listaActoresxActividad;

    private List<Actor> listaActoresxActividadTotal;

    private List<ActaAcuerdo> listaActaAcuerdoxActividad;

    private List<ActaAcuerdo> listaActaAcuerdoxActividadTotal;

    private List<MedioVerificacion> listaMedioVerificacionxActividad;

    private List<MedioVerificacion> listaMedioVerificacionxActividadTotal;

    private List<Caso> listaCasoxActividad;

    private List<Caso> listaCasoxActividadTotal;

    private List<Caso> casos;

    private List<Actividad> listaActividadxActividad;

    private List<Actividad> listaActividadxActividadTotal;

    private List<ActividadHistorial> listaActividadHistorial;

    private List<Noticia> listaNoticias;

    private List<NoticiaRegistro> listaNoticiasRegistros;

    private boolean verVinculosMantenimientoActividad = false;

    private List<SelectItem> listaDepartamento;

    private List<SelectItem> listaProvincia;

    private List<SelectItem> listaDistrito;

    private boolean verBoton = false;

    private boolean verDetalleRegistro = false;

    private Boolean tipoBoton;

    private String nombreCaso = null;

    private String cadenaAutocomplete;

    private int nroActaAcuerdoDetalle = 0;

    private Usuario usuarioSession;

    private ActaAcuerdo actaAcuerdo;

    private Long nroPagina = 1L;

    private String detalleNoticia;

    private boolean verEnlaceNoticia;

    Integer tip = 1;

    private String cordenadax;

    private String cordenaday;

    private Integer zoom;

    private Part file1;

    private List<Actividad> listaActividadesPorCaso;
    
    private List<Actividad> acontecimientoVinculado;
    
    private Boolean esUsuarioRegistro;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private ActaAcuerdoService actaAcuerdoService;

    @Autowired
    private ActividadCasoService actividadCasoService;

    @Autowired
    private CasoService casoService;

    @Autowired
    private ActividadHistorialService actividadHistorialService;

    @Autowired
    private UbigeoService ubigeoService;

    @Autowired
    private ActaAcuerdoDetalleService actaAcuerdoDetalleService;

    @Autowired
    private NoticiaService noticiaService;

    @Autowired
    private NoticiaRegistroService noticiaRegistroService;

    public String cargarPagina() {
        try {
            usuarioSession();
            actividad = new Actividad();
            limpiarListasActores();
            limpiarActaAcuerdo();
            limpiarMedios();
            limpiarVictimas();
            verDetalleRegistro = false;
            actividadCaso = new ActividadCaso();
            nombreCaso = null;
            generarCadenaCasos();
            caso = new Caso();
            listaNoticiasRegistros = new ArrayList<>();
            setearEsUsuarioRegistro();
            return "registroNuevo";
        } catch (Exception e) {
            log.error("ERROR - cargarPagina()" + e);
        }
        return null;
    }

    public String cargarNoticias(int tipo) {
        try {
            setTip(tipo);
            noticia = new Noticia();
            listaNoticias = null;
            if (tipo == 1) {
                listaNoticiasRegistro();
            }
            verEnlaceNoticia = true;
            return "noticiaVinculo";
        } catch (Exception e) {
            log.error("ERROR - cargarNoticias()" + e);
        }
        return null;
    }

    public String cargarNoticiasCaso() {
        try {
            noticia = new Noticia();
            verEnlaceNoticia = false;
            listaNoticias = null;
            return "noticiaVinculo";
        } catch (Exception e) {
            log.error("ERROR - cargarNoticiasCaso()" + e);
        }
        return null;
    }

    private void usuarioSession() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
            usuarioSession = loginController.getUsuarioSesion();
        } catch (Exception e) {
            log.error("ERROR - usuarioSession()" + e);
        }
    }
    
    private void setearEsUsuarioRegistro(){
        System.out.println("usuarioSession.getCodigo()"+usuarioSession.getCodigo());
        System.out.println("actividad.getUsuarioRegistro()"+actividad.getUsuarioRegistro());
        esUsuarioRegistro = StringUtils.equals(usuarioSession.getCodigo(), actividad.getUsuarioRegistro());
    }

    private void limpiarMedios() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            MedioVerificacionController medioVerificacionController = (MedioVerificacionController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "medioVerificacionController");
            medioVerificacionController.limpiarListaMedios();
        } catch (Exception e) {
            log.error("ERROR - limpiarMedios()" + e);
        }
    }

    public void cargarActontecimientoPorCaso() {
        try {
            if (caso.getId() != null) {
                listaActividadesPorCaso = actividadService.actividadBusquedaPorCasoAC(caso.getId());
            }
        } catch (Exception e) {
            log.error("ERROR - cargarActontecimientoPorCaso()" + e);
        }
    }

    public void vincularAcontecimientoActuacion(Actividad acontecimiento) {
        try {
            if(actividad.getIdAcontecimiento() != null){
                removeAcontecimientoVinculado();
            }
            actividad.setIdAcontecimiento(acontecimiento.getId());
            actividadService.actividadUpdateAcontecimiento(actividad);
            actividadService.actividadUpdateVincular(acontecimiento.getId());
            cargarAcontecimientoVinculado();
            msg.messageInfo("Se vinculo el acontecimiento", null);
        } catch (Exception e) {
            log.error("ERROR - vincularAcontecimientoActuacion()" + e);
        }
    }

    public String irModificarRegistro(Actividad acti) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
            MedioVerificacionController medioVerificacionController = (MedioVerificacionController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "medioVerificacionController");
            actorController.initActor();
            medioVerificacionController.listarMedioVerificacion(acti.getId());
            usuarioSession();
            verDetalleRegistro = true;
            actividad = new Actividad();
            actividad = acti;
            cargarAcontecimientoVinculado();
            generarCadenaCasos();
            caso = new Caso();
            caso.setNombre(acti.getNombreCaso());
            caso.setId(acti.getIdCaso());
            if(acti.getIdDepartamento() != null)
                if (!StringUtils.equals(acti.getIdDepartamento(), "0")) {
                    comboProvincia();
                }
            if(acti.getIdProvincia() != null)
                if (!StringUtils.equals(acti.getIdProvincia(), "0")) {
                    comboDistrito();
                }
            actorController.listarActoresXcaso(acti.getId());
            nroActaAcuerdoDetalle = 0;
            nroActaAcuerdoDetalle = actaAcuerdoDetalleService.actaAcuerdoDetalleCount(acti.getId());
            actaAcuerdo = new ActaAcuerdo();
            actaAcuerdo = actaAcuerdoService.actaAcuerdoxActividadBuscarOne(acti.getId());
            listaNoticiasRegistro();
            limpiarActaAcuerdo();
            limpiarVictimas2();
            setearEsUsuarioRegistro();
            return "registroEdit";
        } catch (Exception ex) {
            log.error("ERROR - irModificarRegistro()" + ex);
        }
        return null;
    }

    public String irModificarRegistro2(Actividad acti) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
            MedioVerificacionController medioVerificacionController = (MedioVerificacionController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "medioVerificacionController");
            actorController.initActor();
            medioVerificacionController.listarMedioVerificacion(acti.getId());
            usuarioSession();
            verDetalleRegistro = true;
            actividad = new Actividad();
            actividad = acti;
            cargarAcontecimientoVinculado();
            generarCadenaCasos();
            caso = new Caso();
            caso.setNombre(acti.getNombreCaso());
            caso.setId(acti.getIdCaso());
            if (StringUtils.isNotBlank(acti.getIdDepartamento()) && !StringUtils.equals(acti.getIdDepartamento(),"0")) {
                comboProvincia();
            }
            if (StringUtils.isNotBlank(acti.getIdDepartamento()) && !StringUtils.equals(acti.getIdProvincia(),"0")) {
                comboDistrito();
            }
            actorController.listarActoresXcaso(acti.getId());
            nroActaAcuerdoDetalle = 0;
            nroActaAcuerdoDetalle = actaAcuerdoDetalleService.actaAcuerdoDetalleCount(acti.getId());
            limpiarActaAcuerdo();
            actaAcuerdo = new ActaAcuerdo();
            actaAcuerdo = actaAcuerdoService.actaAcuerdoxActividadBuscarOne(acti.getId());
            listaNoticiasRegistro();
            limpiarVictimas2();
            setearEsUsuarioRegistro();
            return "registroNuevo";
        } catch (Exception ex) {
            log.error("ERROR - irModificarRegistro2()" + ex);
        }
        return null;
    }
    
    private void cargarAcontecimientoVinculado(){
        if(actividad.getIdAcontecimiento() != null){
            try {
                Actividad a = actividadService.actividadBuscarOne(actividad.getIdAcontecimiento());
                if(a != null){
                    acontecimientoVinculado = new ArrayList<>();
                    acontecimientoVinculado.add(a);
                }else{
                    acontecimientoVinculado = null;
                }
            } catch (Exception ex) {
                log.error("ERROR - cargarAcontecimientoVinculado()" + ex);
            }
        }else{
            acontecimientoVinculado = null;
        }
    }
    
    public void removeAcontecimientoVinculado(){
        try {
            acontecimientoVinculado = new ArrayList<>();
        actividadService.actividadUpdateDesVincular(actividad.getIdAcontecimiento());
        actividadService.actividadUpdateAcontecimientoQuitar(actividad.getId());
        msg.messageInfo("Se desvinculo el acontecimiento", null);
        } catch (Exception e) {
            log.error("ERROR - removeAcontecimientoVinculado()" + e);
        }
    }
    
    public void inactivarActividad(){
        actividadService.actividadInactivar(actividad.getId());
        cargarPagina();
        msg.messageInfo("Se ha inactividado el registro", null);
    }
    
    public void removeAcontecimientoVinculadoFichaAD(Actividad activi){
        try {
            actividadService.actividadUpdateDesVincular(activi.getIdAcontecimiento());
            actividadService.actividadUpdateAcontecimientoQuitar(activi.getId());
        } catch (Exception e) {
            log.error("ERROR - removeAcontecimientoVinculadoFicha()" + e);
        }
    }
    
    public void removeAcontecimientoVinculadoFichaAC(Actividad activi){
        try {
            actividadService.actividadUpdateDesVincular(activi.getId());
            Actividad actividadAconte = actividadService.actividadBusquedaPorAcontecimiento(activi.getId());
            actividadService.actividadUpdateAcontecimientoQuitar(actividadAconte.getId());
        } catch (Exception e) {
            log.error("ERROR - removeAcontecimientoVinculadoFicha2()" + e);
        }
    }

    public void verDetalleNoticia(String detalle) {
        detalleNoticia = detalle;
    }

    public Actividad cargarActividadId(long idActividad) {
        try {
            Actividad a = actividadService.actividadxCasoBuscarID(idActividad);
            if (a != null) {
                return a;
            }
        } catch (Exception e) {
            log.error("ERROR - cargarActividadId()" + e);
        }
        return null;
    }

    public void cargarAcontecimientoModal(Caso c, int tipo) {
        try {
            tipoBoton = null;
            setCaso(c);
            FacesContext context = FacesContext.getCurrentInstance();
            ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
            actorController.limpiarListaActorPaginado();
            actorController.cargarRegistroAgregarActor(c);
            usuarioSession();
            actividadCaso = new ActividadCaso();
            actividad = new Actividad();
            tipoBoton = tipo == 1;
            verDetalleRegistro = false;
        } catch (Exception e) {
            log.error("ERROR - cargarAcontecimientoModal()" + e);
        }
    }

    public boolean vinculaNoticia(Noticia noticia) {
        try {
            NoticiaRegistro noticiaRegistro = new NoticiaRegistro();
            noticiaRegistro.setDescripcion(noticia.getDescripcion());
            noticiaRegistro.setEmpresa(noticia.getEmpresa());
            noticiaRegistro.setFechaPublicacion(noticia.getFechaPublica());
            noticiaRegistro.setFechaRegistro(noticia.getFechaRegistro());
            noticiaRegistro.setLink(noticia.getLink());
            noticiaRegistro.setRegion(noticia.getRegion());
            noticiaRegistro.setTitulo(noticia.getTitulo());
            noticiaRegistro.setIdActividad(actividad.getId());
            noticiaRegistro.setEstado("ACT");
            for (NoticiaRegistro noticia1 : listaNoticiasRegistros) {
                if (StringUtils.equals(noticia.getTitulo(), noticia1.getTitulo())) {
                    msg.messageAlert("Esta noticia ya fue vinculada", null);
                    return false;
                }
            }
            listaNoticiasRegistros.add(noticiaRegistro);
            msg.messageInfo("Se vinculó la noticia", null);
            return true;
        } catch (Exception e) {
            log.error("ERROR - vinculaNoticia()" + e);
        }
        return false;
    }
    
    public String retornarActividad(){
        Actividad a = cargarActividadId(actividad.getId());
            if(a.getIdCaso() == null){
                return irModificarRegistro2(a);
            }else{
                return irModificarRegistro(a);
            }
    }

    public void listaNoticiasRegistro() {
        try {
            List<NoticiaRegistro> list = noticiaRegistroService.noticiaRegistroBuscar(actividad.getId());
            listaNoticiasRegistros = new ArrayList<>();
            listaNoticiasRegistros.addAll(list);
        } catch (Exception e) {
            log.error("ERROR - listaNoticiasRegistro()" + e);
        }
    }

    public void eliminarNoticiaRegistro(NoticiaRegistro noticiaRegistro) {
        noticiaRegistro.setEstado("INA");
        msg.messageAlert("Se ha marcado para eliminar esta noticia", null);
    }

    public String guardarVinculos() {
        try {
            if (listaNoticiasRegistros.isEmpty()) {
                msg.messageAlert("Debe de enlazar por lo menos una noticia para poder guardar", null);
                return null;
            }
            for (NoticiaRegistro noticiaRegistro : listaNoticiasRegistros) {
                if (noticiaRegistro.getId() == null) {
                    noticiaRegistroService.noticiaRegistroInsertar(noticiaRegistro);
                } else {
                    noticiaRegistroService.noticiaRegistroUpdate(noticiaRegistro);
                }
            }
            listaNoticiasRegistro();
            msg.messageInfo("Se han registrado los cambios", null);
            if (tip == 1) {
                return "registroNuevo";
            } else {
                return "registroEdit";
            }
        } catch (Exception e) {
            log.error("ERROR - guardarVinculos()" + e);
        }
        return null;
    }

    private void generarCadenaCasos() {
        try {
            Caso cas = new Caso();
            cas.setUsuarioRegistro(usuarioSession.getCodigo());
            cadenaAutocomplete = casoService.casoBuscarAutocomplete(cas);
        } catch (Exception ex) {
            log.error("ERROR - generarCadenaCasos()" + ex);
        }
    }

    private String generarCodigoAD() {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMM");
            String codigo = formato.format(new Date());
            String numero = String.format("%2s", actividadService.actividadADCodigoGenerado().toString()).replace(' ', '0');
            return "AC" + codigo + numero;
        } catch (Exception e) {
            log.error("ERROR - generarCodigoAD()" + e);
        }
        return null;
    }

    public void buscarNoticia(Long pagina) {
        if (pagina > 0) {
            int paginado = ConstantesUtil.PAGINADO_10;
            Long ini = paginado * (pagina - 1) + 1;
            Long fin = paginado * pagina;
            if (pagina == 0) {
                ini = 1L;
                fin = 10L;
            }
            noticia.setIni(ini);
            noticia.setFin(fin);
            try {
                List<Noticia> list = noticiaService.listaNoticias(noticia);
                if (list.size() > 0) {
                    listaNoticias = list;
                    nroPagina = pagina;
                } else {
                    listaNoticias = null;
                    msg.messageAlert("La busqueda no ha obtenido resultados", null);
                }

            } catch (Exception e) {
                log.error("ERROR - buscarNoticia()" + e);
            }
        }
    }

    public void limpiarNoticias() {
        noticia = new Noticia();
        listaNoticias = null;
    }

    public void buscarCaso() {
        try {
            casos = casoService.casoBuscar(caso);
        } catch (Exception ex) {
            log.error("ERROR - buscarCaso()" + ex);
        }
    }

    private void limpiarListasActores() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
            actorController.limpiarListas();
        } catch (Exception e) {
            log.error("ERROR - limpiarListasActores()" + e);
        }
    }

    private void limpiarActaAcuerdo() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ActaAcuerdoController actaAcuerdoController = (ActaAcuerdoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actaAcuerdoController");
            actaAcuerdoController.limpiarActaAcuerdo();
        } catch (Exception e) {
            log.error("ERROR - limpiarActaAcuerdo()" + e);
        }
    }

    private void limpiarVictimas() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            VictimaViolenciaController victimaViolenciaController = (VictimaViolenciaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "victimaViolenciaController");
            victimaViolenciaController.limpiarVictimas();
        } catch (Exception e) {
            log.error("ERROR - limpiarVictimas()" + e);
        }
    }

    private void limpiarVictimas2() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            VictimaViolenciaController victimaViolenciaController = (VictimaViolenciaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "victimaViolenciaController");
            victimaViolenciaController.limpiarVictimasEdit(actividad.getId());
        } catch (Exception e) {
            log.error("ERROR - limpiarVictimas2()" + e);
        }
    }

    public String cargarPaginaBusqueda(int tipo) {
        actividadBusquedaTemp = new ActividadBusquedaTemp();
        return "actividadBusqueda";
    }

    public String regresarDeNoticias() {
        try {
            listaNoticiasRegistro();
            if (tip == 1) {
                return "registroNuevo";
            } else {
                return "registroEdit";
            }
        } catch (Exception e) {
            log.error("ERROR - regresarDeNoticias()" + e);
        }
        return null;
    }

    public String registrarActividad() {
        String ruta = uploadArchiveImage();
        if (StringUtils.isNoneBlank(ruta)) {
            actividad.setRuta(ruta);
        }
        try {
            String accionHistorial;
            if (actividad.getId() != null) {
                actividad.setFechaModificacion(new Date());
                actividad.setUsuarioModificacion(usuarioSession.getCodigo());
                actividadService.actividadModificar(actividad);
                if (caso.getId() != null) {
                    insertaUpdateActividadCaso(setearActividadCaso(actividad, caso));
                }
                accionHistorial = HistorialType.HISTORIAL_ACTUALIZACION.getKey();
            } else {
                actividad.setFechaRegistro(new Date());
                actividad.setUsuarioRegistro(usuarioSession.getCodigo());
                actividad.setCodigoActividad(generarCodigoAD());
                actividadService.actividadNuevo(actividad);
                accionHistorial = HistorialType.HISTORIAL_CREACION.getKey();
                if (caso != null) {
                    if (caso.getId() != null) {
                        insertaUpdateActividadCaso(setearActividadCaso(actividad, caso));
                    }
                }
                verDetalleRegistro = true;
            }
            historialActividad(accionHistorial, actividad.getId());
            msg.messageInfo("Se guardaron los cambios", null);
            setearEsUsuarioRegistro();
            if (caso.getId() != null) {
                return "registroEdit";
            }else{
                return "registroNuevo";
            }
        } catch (Exception e) {
            log.error("ERROR - registrarActividad()" + e);
        }
        return null;
    }

    private void insertaUpdateActividadCaso(ActividadCaso ac) {
        try {
            int count = actividadCasoService.actividadCasoValida(ac.getActividad().getId());
            if (count == 0) {
                actividadCasoService.actividadCasoInsertar(ac);
            } else {
                actividadCasoService.actividadCasoUpdate(ac);
            }
        } catch (Exception e) {
            log.error("ERROR - insertaUpdateActividadCaso()" + e);
        }
    }

    private String uploadArchiveImage() {
        String nameArchive = getFilename(file1);
        String extencion = "";
        if (StringUtils.isNoneBlank(nameArchive)) {
            switch (file1.getContentType()) {
                case "image/png":
                    extencion = ".png";
                    break;
                case "image/jpeg":
                    extencion = ".jpg";
                    break;
                case "image/gif":
                    extencion = ".gif";
                    break;
            }
            DateFormat fechaHora = new SimpleDateFormat("yyyyMMddHHmmss");
            String formato = fechaHora.format(new Date());
            String ruta = formato + extencion;
            File file = new File(ConstantesUtil.FILE_SYSTEM + ruta);
            try (InputStream input = file1.getInputStream()) {
                Files.copy(input, file.toPath());
            } catch (IOException ex) {
                log.error("ERROR - uploadArchiveImage()" + ex);
            }
            return ruta;
        }
        return null;
    }

    private static String getFilename(Part part) {
        if(part != null)
        try {
            for (String cd : part.getHeader("content-disposition").split(";")) {
                if (cd.trim().startsWith("filename")) {
                    String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                    return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
                }
            }
        } catch (Exception e) {
            log.error("ERROR - getFilename()" + e);
        }
        return null;
    }

    public void registrarActividadModal(int tipo) {
        if (tipo == 1) {
            actividad.setTipo("AC");
        }
        if (tipo == 2) {
            actividad.setTipo("AD");
        }
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            String accionHistorial;
            if (actividad.getId() != null) {
                actividad.setFechaModificacion(new Date());
                actividad.setUsuarioModificacion(usuarioSession.getCodigo());
                actividadService.actividadModificar(actividad);
                if (caso.getId() != null) {
                    actividadCasoService.actividadCasoUpdate(setearActividadCaso(actividad, caso));
                }

                ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
                actorController.actividadUnionActorModal(actividad.getId());
                accionHistorial = HistorialType.HISTORIAL_ACTUALIZACION.getKey();
            } else {
                actividad.setFechaRegistro(new Date());
                actividad.setUsuarioRegistro(usuarioSession.getCodigo());
                actividad.setCodigoActividad(generarCodigoAD());
                actividadService.actividadNuevo(actividad);
                accionHistorial = HistorialType.HISTORIAL_CREACION.getKey();
                if (caso != null) {
                    if (caso.getId() != null) {
                        actividadCasoService.actividadCasoInsertar(setearActividadCaso(actividad, caso));
                    }
                }
                verDetalleRegistro = true;
            }
            CasoController casoController = (CasoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "casoController");
            casoController.contarActividades();
            historialActividad(accionHistorial, actividad.getId());
            if (tipo == 1) 
                msg.messageInfo("Se agregó correctamente el acontecimiento", null);
            if (tipo == 2)
                msg.messageInfo("Se agregó correctamente la actuación defensorial", null);
        } catch (Exception e) {
            log.error("ERROR - registrarActividadModal()" + e);
        }
    }

    private ActividadCaso setearActividadCaso(Actividad a, Caso c) {
        try {
            actividadCaso = new ActividadCaso();
            actividadCaso.setCaso(c);
            actividadCaso.setActividad(a);
            actividadCaso.setEstado("ACT");
            return actividadCaso;
        } catch (Exception e) {
            log.error("ERROR - setearActividadCaso()" + e);
        }
        return null;
    }

    public void comboProvincia() {
        try {
            listaProvincia = new ArrayList<>();
            listaDistrito = new ArrayList<>();
            String id = actividad.getIdDepartamento();
            if (StringUtils.equals(id,"0")) {
                listaProvincia.clear();
            } else {
                List<Provincia> list = ubigeoService.provinciaLista(id);
                if (list.size() > 0) {
                    for (Provincia provincia : list) {
                        listaProvincia.add(new SelectItem(provincia.getIdProvincia(), provincia.getDescripcion()));
                    }
                }
                Departamento dep = ubigeoService.departamentoOne(id);
                cordenadax = dep.getCoordenadax();
                cordenaday = dep.getCoordenaday();
                zoom = dep.getZoom();
            }
        } catch (Exception e) {
            log.error("ERROR - comboProvincia()" + e);
        }
    }

    public void comboDistrito() {
        try {
            listaDistrito = new ArrayList<>();
        String idDepartamento = actividad.getIdDepartamento();
        String idProvincia = actividad.getIdProvincia();
        if (StringUtils.equals(idDepartamento, "0") || StringUtils.equals(idProvincia, "0")) {
            listaDistrito.clear();
        } else {
            Distrito d = new Distrito();
            d.setIdDepartamento(idDepartamento);
            d.setIdProvincia(idProvincia);
            List<Distrito> list = ubigeoService.distritoLista(d);
            if (list.size() > 0) {
                for (Distrito distrito : list) {
                    listaDistrito.add(new SelectItem(distrito.getIdDistrito(), distrito.getDescripcion()));
                }
            }
            Provincia p = new Provincia();
            p.setIdDepartamento(idDepartamento);
            p.setIdProvincia(idProvincia);
            Provincia prov = ubigeoService.provinciaOne(p);
            cordenadax = prov.getCoordenadax();
            cordenaday = prov.getCoordenaday();
            zoom = prov.getZoom();
        }
        } catch (Exception e) {
            log.error("ERROR - comboDistrito()" + e);
        }
    }

    public void coordenadasDistrito() {
        try {
            String idDistrito = actividad.getIdDistrito();
            if (idDistrito != null) {
                Distrito d = new Distrito();
                d.setIdDepartamento(actividad.getIdDepartamento());
                d.setIdProvincia(actividad.getIdProvincia());
                d.setIdDistrito(actividad.getIdDistrito());
                Distrito dist = ubigeoService.distritoOne(d);
                cordenadax = dist.getCoordenadax();
                cordenaday = dist.getCoordenaday();
                zoom = dist.getZoom();
            }
        } catch (Exception e) {
            log.error("ERROR - coordenadasDistrito()" + e);
        }
    }

    private void historialActividad(String accion, Long idActividad) {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        ActividadHistorial historial = new ActividadHistorial();
        historial.setAccion(accion);
        historial.setIdActividad(idActividad);
        historial.setFechaRegistro(new Date());
        historial.setUsuarioRegistro(loginController.getUsuarioSesion().getCodigo());
        try {
            actividadHistorialService.actividadHistorialInsertar(historial);
        } catch (Exception e) {
            log.error("ERROR - cargarPagina()" + e);
        }
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public ActorTemp getActorTemp() {
        return actorTemp;
    }

    public void setActorTemp(ActorTemp actorTemp) {
        this.actorTemp = actorTemp;
    }

    public ActividadBusquedaTemp getActividadBusquedaTemp() {
        if (actividadBusquedaTemp == null) {
            actividadBusquedaTemp = new ActividadBusquedaTemp();
        }
        return actividadBusquedaTemp;
    }

    public void setActividadBusquedaTemp(ActividadBusquedaTemp actividadBusquedaTemp) {
        this.actividadBusquedaTemp = actividadBusquedaTemp;
    }

    public ActividadActorTemp getActividadActorTemp() {
        return actividadActorTemp;
    }

    public void setActividadActorTemp(ActividadActorTemp actividadActorTemp) {
        this.actividadActorTemp = actividadActorTemp;
    }

    public List<Actor> getListaActoresxActividad() {
        return listaActoresxActividad;
    }

    public void setListaActoresxActividad(List<Actor> listaActoresxActividad) {
        this.listaActoresxActividad = listaActoresxActividad;
    }

    public List<ActaAcuerdo> getListaActaAcuerdoxActividad() {
        return listaActaAcuerdoxActividad;
    }

    public void setListaActaAcuerdoxActividad(List<ActaAcuerdo> listaActaAcuerdoxActividad) {
        this.listaActaAcuerdoxActividad = listaActaAcuerdoxActividad;
    }

    public List<MedioVerificacion> getListaMedioVerificacionxActividad() {
        return listaMedioVerificacionxActividad;
    }

    public void setListaMedioVerificacionxActividad(List<MedioVerificacion> listaMedioVerificacionxActividad) {
        this.listaMedioVerificacionxActividad = listaMedioVerificacionxActividad;
    }

    public List<Caso> getListaCasoxActividad() {
        return listaCasoxActividad;
    }

    public void setListaCasoxActividad(List<Caso> listaCasoxActividad) {
        this.listaCasoxActividad = listaCasoxActividad;
    }

    public List<ActividadHistorial> getListaActividadHistorial() {
        return listaActividadHistorial;
    }

    public void setListaActividadHistorial(List<ActividadHistorial> listaActividadHistorial) {
        this.listaActividadHistorial = listaActividadHistorial;
    }

    public List<Actor> getListaActoresxActividadTotal() {
        return listaActoresxActividadTotal;
    }

    public void setListaActoresxActividadTotal(List<Actor> listaActoresxActividadTotal) {
        this.listaActoresxActividadTotal = listaActoresxActividadTotal;
    }

    public List<ActaAcuerdo> getListaActaAcuerdoxActividadTotal() {
        return listaActaAcuerdoxActividadTotal;
    }

    public void setListaActaAcuerdoxActividadTotal(List<ActaAcuerdo> listaActaAcuerdoxActividadTotal) {
        this.listaActaAcuerdoxActividadTotal = listaActaAcuerdoxActividadTotal;
    }

    public List<MedioVerificacion> getListaMedioVerificacionxActividadTotal() {
        return listaMedioVerificacionxActividadTotal;
    }

    public void setListaMedioVerificacionxActividadTotal(List<MedioVerificacion> listaMedioVerificacionxActividadTotal) {
        this.listaMedioVerificacionxActividadTotal = listaMedioVerificacionxActividadTotal;
    }

    public List<Caso> getListaCasoxActividadTotal() {
        return listaCasoxActividadTotal;
    }

    public void setListaCasoxActividadTotal(List<Caso> listaCasoxActividadTotal) {
        this.listaCasoxActividadTotal = listaCasoxActividadTotal;
    }

    public List<Actividad> getListaActividadxActividad() {
        return listaActividadxActividad;
    }

    public void setListaActividadxActividad(List<Actividad> listaActividadxActividad) {
        this.listaActividadxActividad = listaActividadxActividad;
    }

    public List<Actividad> getListaActividadxActividadTotal() {
        return listaActividadxActividadTotal;
    }

    public void setListaActividadxActividadTotal(List<Actividad> listaActividadxActividadTotal) {
        this.listaActividadxActividadTotal = listaActividadxActividadTotal;
    }

    public boolean isVerVinculosMantenimientoActividad() {
        return verVinculosMantenimientoActividad;
    }

    public void setVerVinculosMantenimientoActividad(boolean verVinculosMantenimientoActividad) {
        this.verVinculosMantenimientoActividad = verVinculosMantenimientoActividad;
    }

    public boolean isVerBoton() {
        return verBoton;
    }

    public void setVerBoton(boolean verBoton) {
        this.verBoton = verBoton;
    }

    public boolean isVerDetalleRegistro() {
        return verDetalleRegistro;
    }

    public void setVerDetalleRegistro(boolean verDetalleRegistro) {
        this.verDetalleRegistro = verDetalleRegistro;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public String getCadenaAutocomplete() {
        return cadenaAutocomplete;
    }

    public void setCadenaAutocomplete(String cadenaAutocomplete) {
        this.cadenaAutocomplete = cadenaAutocomplete;
    }

    public List<SelectItem> getListaDepartamento() {
        listaDepartamento = new ArrayList<>();
        List<Departamento> list = ubigeoService.departamentoLista();
        if (list.size() > 0) {
            for (Departamento departamento : list) {
                listaDepartamento.add(new SelectItem(departamento.getIdDepartamento(), departamento.getDescripcion()));
            }
        }
        return listaDepartamento;
    }

    public void setListaDepartamento(List<SelectItem> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<SelectItem> getListaProvincia() {
        return listaProvincia;
    }

    public void setListaProvincia(List<SelectItem> listaProvincia) {
        this.listaProvincia = listaProvincia;
    }

    public List<SelectItem> getListaDistrito() {
        return listaDistrito;
    }

    public void setListaDistrito(List<SelectItem> listaDistrito) {
        this.listaDistrito = listaDistrito;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public Boolean getTipoBoton() {
        return tipoBoton;
    }

    public void setTipoBoton(Boolean tipoBoton) {
        this.tipoBoton = tipoBoton;
    }

    public int getNroActaAcuerdoDetalle() {
        return nroActaAcuerdoDetalle;
    }

    public void setNroActaAcuerdoDetalle(int nroActaAcuerdoDetalle) {
        this.nroActaAcuerdoDetalle = nroActaAcuerdoDetalle;
    }

    public ActaAcuerdo getActaAcuerdo() {
        return actaAcuerdo;
    }

    public void setActaAcuerdo(ActaAcuerdo actaAcuerdo) {
        this.actaAcuerdo = actaAcuerdo;
    }

    public List<Noticia> getListaNoticias() {
        return listaNoticias;
    }

    public void setListaNoticias(List<Noticia> listaNoticias) {
        this.listaNoticias = listaNoticias;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public Long getNroPagina() {
        return nroPagina;
    }

    public void setNroPagina(Long nroPagina) {
        this.nroPagina = nroPagina;
    }

    public String getDetalleNoticia() {
        return detalleNoticia;
    }

    public void setDetalleNoticia(String detalleNoticia) {
        this.detalleNoticia = detalleNoticia;
    }

    public List<NoticiaRegistro> getListaNoticiasRegistros() {
        return listaNoticiasRegistros;
    }

    public void setListaNoticiasRegistros(List<NoticiaRegistro> listaNoticiasRegistros) {
        this.listaNoticiasRegistros = listaNoticiasRegistros;
    }

    public boolean isVerEnlaceNoticia() {
        return verEnlaceNoticia;
    }

    public void setVerEnlaceNoticia(boolean verEnlaceNoticia) {
        this.verEnlaceNoticia = verEnlaceNoticia;
    }

    public Integer getTip() {
        return tip;
    }

    public void setTip(Integer tip) {
        this.tip = tip;
    }

    public String getCordenadax() {
        return cordenadax;
    }

    public void setCordenadax(String cordenadax) {
        this.cordenadax = cordenadax;
    }

    public String getCordenaday() {
        return cordenaday;
    }

    public void setCordenaday(String cordenaday) {
        this.cordenaday = cordenaday;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public List<Actividad> getListaActividadesPorCaso() {
        return listaActividadesPorCaso;
    }

    public void setListaActividadesPorCaso(List<Actividad> listaActividadesPorCaso) {
        this.listaActividadesPorCaso = listaActividadesPorCaso;
    }

    public List<Actividad> getAcontecimientoVinculado() {
        return acontecimientoVinculado;
    }

    public void setAcontecimientoVinculado(List<Actividad> acontecimientoVinculado) {
        this.acontecimientoVinculado = acontecimientoVinculado;
    }

    public Boolean getEsUsuarioRegistro() {
        return esUsuarioRegistro;
    }

    public void setEsUsuarioRegistro(Boolean esUsuarioRegistro) {
        this.esUsuarioRegistro = esUsuarioRegistro;
    }

}
