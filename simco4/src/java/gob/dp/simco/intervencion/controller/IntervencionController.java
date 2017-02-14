/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.controller;

import gob.dp.simco.seguridad.controller.BusquedaUsuarioController;
import gob.dp.simco.seguridad.controller.LoginController;
import gob.dp.simco.seguridad.entity.Usuario;
import gob.dp.simco.intervencion.entity.Intervencion;
import gob.dp.simco.intervencion.entity.IntervencionAccion;
import gob.dp.simco.intervencion.entity.IntervencionEtapa;
import gob.dp.simco.intervencion.entity.IntervencionEtapaActuacion;
import gob.dp.simco.intervencion.entity.IntervencionHistorialAct;
import gob.dp.simco.intervencion.entity.IntervencionMiembro;
import gob.dp.simco.intervencion.service.IntervencionAccionService;
import gob.dp.simco.intervencion.service.IntervencionEtapaActuacionService;
import gob.dp.simco.intervencion.service.IntervencionEtapaService;
import gob.dp.simco.intervencion.service.IntervencionHistorialActService;
import gob.dp.simco.intervencion.service.IntervencionMiembroService;
import gob.dp.simco.intervencion.service.IntervencionService;
import gob.dp.simco.intervencion.vo.ReportPlanIntervencionVO;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import gob.dp.simco.registro.entity.Actividad;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.service.ActividadService;
import gob.dp.simco.registro.service.CasoService;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
public class IntervencionController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(IntervencionController.class);

    private Intervencion intervencion;

    private IntervencionEtapa intervencionEtapa;

    private IntervencionAccion intervencionAccion;

    private IntervencionAccion intervencionAccionSelect;

    private List<Intervencion> intervencions;

    private List<Intervencion> intervencionActivas;

    private List<Intervencion> intervencionPriorizadas;

    private List<Intervencion> intervencionArchivadas;

    private List<IntervencionAccion> accions;

    private List<IntervencionEtapa> listPlanificado;

    private List<IntervencionEtapa> listEnEjecucion;

    private List<IntervencionEtapa> listEjecutado;

    private List<IntervencionEtapaActuacion> intervencionEtapaActuacions;

    private List<IntervencionHistorialAct> historial;

    private List<Actividad> actividads;

    private List<IntervencionMiembro> intervencionMiembros;

    private String cadenaAutocomplete;

    private Caso caso;

    private Usuario usuarioSession;

    private String descripcionActuacion;

    private Integer ejecutados;

    JasperPrint jasperPrint;

    private Integer actividadesTotales;

    private Integer actividadesTotalesEjecutadas;

    private Integer actividadesPorcentaje;

    @Autowired
    private IntervencionService intervencionService;

    @Autowired
    private IntervencionAccionService intervencionAccionService;

    @Autowired
    private IntervencionEtapaService intervencionEtapaService;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private IntervencionEtapaActuacionService intervencionEtapaActuacionService;

    @Autowired
    private IntervencionMiembroService intervencionMiembroService;

    @Autowired
    private IntervencionHistorialActService intervencionHistorialActService;

    @Autowired
    private CasoService casoService;

    public String cargarPaginaIntervencion() {
        usuarioSession();
        intervencion = new Intervencion();
        intervencions = intervencionService.intervencionBuscar(usuarioSession.getCodigo());
        listarIntervenciones();
        caso = new Caso();
        generarCadenaCasos();
        return "intervencion";
    }

    private void usuarioSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }

    public String cargarPaginaIntervencionDetalle(Intervencion intervencion) {
        FacesContext context = FacesContext.getCurrentInstance();
        BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
        busquedaUsuarioController.listarPaginado(1L);
        intervencionAccion = new IntervencionAccion();
        intervencionAccionSelect = new IntervencionAccion();
        intervencionEtapa = new IntervencionEtapa();
        intervencionMiembros = new ArrayList<>();
        setIntervencion(intervencion);
        listarAcciones(intervencion.getId());
        cargarListasTipoTotal();
        return "intervencionDetalle";
    }

    public String cargarPaginaIntervencionDetalleCaso(long idCaso) {
        usuarioSession();
        caso = new Caso();
        try {
            caso = casoService.casoBuscarOne(idCaso);
            intervencionEtapa = new IntervencionEtapa();
            Intervencion i = intervencionService.intervencionBuscarCaso(caso.getCodigo());
            if (i == null) {
                intervencion = new Intervencion();
                saveIntervencion();
                cargarListas2();
                accions = null;
            } else {
                setIntervencion(i);
                listarAcciones(i.getId());
                cargarListasTipoTotal();
            }
            FacesContext context = FacesContext.getCurrentInstance();
            BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
            busquedaUsuarioController.listarPaginado(1L);
            intervencionAccion = new IntervencionAccion();
            intervencionAccionSelect = new IntervencionAccion();
            intervencionMiembros = new ArrayList<>();
        } catch (Exception ex) {
            log.error(ex);
        }
        return "intervencionDetalle";
    }

    public boolean initJasper() throws JRException {
        List<ReportPlanIntervencionVO> lista = new ArrayList<>();
        ReportPlanIntervencionVO vo = new ReportPlanIntervencionVO();
        vo.setDescripcion(intervencion.getDescripcion());
        vo.setNombre(intervencion.getNombre());
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.MEDIUM);
        DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
        if (intervencion.getId() != null) {
            List<IntervencionEtapa> etapasTotales = intervencionEtapaService.intervencionEtapaxIntervencion(intervencion.getId());
            for(IntervencionEtapa ei : etapasTotales){
                List<IntervencionEtapaActuacion> listiea = intervencionEtapaActuacionService.intervencionEtapaActuacionBuscarActividadGSA(ei.getId());
                ei.setRutaReporte1(retornaRutaPath().concat("/jasper/"));
                ei.setIeas(listiea);
            }
            vo.setEtapasTotales(etapasTotales);
            
            List<IntervencionAccion> accionesSeleccionadas = intervencionAccionService.intervencionAccionBuscarxIntervencion(intervencion.getId());
            int j = 0;
            for (IntervencionAccion ia : accionesSeleccionadas) {
                j = ++j;
                ia.setNumero("3." + j + " Campo de accion " + j + ":");
                ia.setEtapas(intervencionEtapaService.intervencionEtapaxAccion(ia.getId()));
                ia.setRutaReporte1(retornaRutaPath().concat("/jasper/"));
                int k = 0;
                for (IntervencionEtapa ie : ia.getEtapas()) {
                    k = ++k;
                    ie.setNumero1("3." + j + "." + k);
                    String s4 = "";
                    if (ie.getFechaLimite() != null) {
                        s4 = df4.format(ie.getFechaLimite());
                    }
                    if (ie.getDescripcion() == null) {
                        ie.setDescripcion("");
                    }
                    ie.setFechaLimiteString(s4);
                    ie.setNumero2(ie.getNumero1() + ".1");
                    ie.setNumero3(ie.getNumero1() + ".2");
                    ie.setRutaReporte1(retornaRutaPath().concat("/jasper/"));
                    ie.setRutaReporte1(retornaRutaPath().concat("/jasper/"));
                    ie.setIeas(intervencionEtapaActuacionService.intervencionEtapaActuacionBuscarActividad(ie.getId()));
                    for (IntervencionEtapaActuacion etapaActuacion : ie.getIeas()) {
                        if (etapaActuacion.getActividadId() != null) {
                            etapaActuacion.setDetalleReporte(etapaActuacion.getDescripcion() + " (realizada el " + df2.format(etapaActuacion.getFechaCulminacion()) + " - " + etapaActuacion.getCodigoActividad() + " \" " + etapaActuacion.getNombreActividad() + " \")");
                        } else {
                            etapaActuacion.setDetalleReporte(etapaActuacion.getDescripcion());
                        }
                    }
                    Integer porcentaje = defineAvanceReport(ie.getIeas());
                    ie.setAvanceString("Actuaciones defensoriales planificadas(" + porcentaje + "% de avance)");
                    String estadoReporte = "";
                    if (porcentaje == 0) {
                        estadoReporte = "Planificado";
                    }
                    if (porcentaje < 100 && porcentaje > 0) {
                        estadoReporte = "En Ejecucion";
                    }
                    if (porcentaje == 100) {
                        estadoReporte = "Ejecutado";
                    }
                    ie.setDetalle(ie.getDetalle() + "(" + estadoReporte + ")");

                    List<IntervencionMiembro> miembros = intervencionMiembroService.intervencionMiembroBuscar(ie.getId());
                    ie.setIms(miembros);
                }
            }

            vo.setAccionesSeleccionadas(accionesSeleccionadas);
            vo.setEtapas(listPlanificado);
            
            List<IntervencionAccion> ias = new ArrayList<>();
            int i = 0;
            for (IntervencionAccion ia : accions) {
                i++;
                ia.setNumero("2." + i);
                ias.add(ia);
            }
            vo.setAcciones(ias);
            vo.setImagePath(retornaRutaPath().concat("/images/"));
            vo.setRutaReporte1(retornaRutaPath().concat("/jasper/"));
            lista.add(vo);
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/planIntervencion.jasper"),new HashMap(), beanCollectionDataSource);
            return true;
        } else {
            msg.messageAlert("No existe un plan de intervención para este caso", null);
            return false;
        }
    }

    public void pdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        if (initJasper()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_planIntervencion.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            facesContext.responseComplete();
            facesContext.renderResponse();
        }
    }

    private void registrarListasTipo() {
        for (IntervencionEtapaActuacion iea : intervencionEtapaActuacions) {
            addActuacionActividad(iea);
        }
        cargarListasTipoTotal();
    }

    private void cargarListasTipoTotal() {
        listPlanificado = new ArrayList<>();
        listEnEjecucion = new ArrayList<>();
        listEjecutado = new ArrayList<>();
        IntervencionEtapa ie = new IntervencionEtapa();
        ie.setIdIntervencion(intervencion.getId());
        List<IntervencionEtapa> list1 = intervencionEtapaService.intervencionEtapaBuscarTipo(ie);
        List<IntervencionEtapa> list2 = new ArrayList<>();
        Integer total;

        for (IntervencionEtapa ie1 : list1) {
            buscarActuacionesxEtapa(ie1.getId());
            total = intervencionEtapaActuacions.size();
            ejecutados = 0;
            for (IntervencionEtapaActuacion iea : intervencionEtapaActuacions) {
                if (iea.getActividadId() != null) {
                    if (iea.getActividadId() != 0) {
                        ejecutados++;
                    }
                }
            }
            ie1.setListaActuacionesTotal(total);
            ie1.setListaActuacionesEjecutadas(ejecutados);
            list2.add(ie1);
        }
        list1.clear();
        list1.addAll(list2);

        if (list1.size() > 0) {
            listPlanificado.addAll(list1);
            listEnEjecucion.addAll(list1);
            listEjecutado.addAll(list1);
            initContadorActividades(list1);
        }else{
            actividadesTotales = 0;
            actividadesTotalesEjecutadas = 0;
            actividadesPorcentaje = 0;
        }
    }

    private void initContadorActividades(List<IntervencionEtapa> lis) {
        actividadesTotales = 0;
        actividadesTotalesEjecutadas = 0;
        actividadesPorcentaje = 0;
        if (lis.size() > 0) {
            for (IntervencionEtapa ie : lis) {
                actividadesTotales += ie.getListaActuacionesTotal();
                actividadesTotalesEjecutadas += ie.getListaActuacionesEjecutadas();
                if (actividadesTotales != 0) {
                    actividadesPorcentaje = actividadesTotalesEjecutadas * 100 / actividadesTotales;
                } else {
                    actividadesPorcentaje = 0;
                }
            }
        }
    }

    public void cargarListas2() {
        listPlanificado = new ArrayList<>();
        listEnEjecucion = new ArrayList<>();
        listEjecutado = new ArrayList<>();
    }

    public void limpiarNombre() {
        intervencion = new Intervencion();
    }

    private void generarCadenaCasos() {
        try {
            usuarioSession();
            Caso cas = new Caso();
            cas.setUsuarioRegistro(usuarioSession.getCodigo());
            cadenaAutocomplete = casoService.casoBuscarAutocomplete(cas);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void setearIdSelectIntervencionAccion(IntervencionAccion ia) {
        setIntervencionAccionSelect(ia);
        intervencionEtapa.setIntervencionAccionId(ia.getId());
        intervencionEtapa.setColor(ia.getColor());
    }

    private void saveHistorial(String mensaje, String usuario, long idEtapa) {
        IntervencionHistorialAct historialAct = new IntervencionHistorialAct();

        historialAct.setDescripcion(mensaje);
        historialAct.setUsuario(usuario);
        historialAct.setFecha(new Date());
        historialAct.setIdEtapa(idEtapa);

        intervencionHistorialActService.intervencionHistorialActInsertar(historialAct);
    }

    public void addListaIntervencionEtapa(int tipo) {
        IntervencionEtapa etapa = new IntervencionEtapa();

        if (tipo == 1) {
            etapa.setTipo("PLA");
            etapa.setEstado("TEMP");
            etapa.setListaActuacionesEjecutadas(0);
            etapa.setListaActuacionesTotal(0);
            listPlanificado.add(etapa);
        }
    }

    public void removeListaIntervencionEtapa(IntervencionEtapa etapa, int tipo) {
        if (tipo == 0) {
            listPlanificado.remove(etapa);
        }

        if (tipo > 0 && tipo < 100) {
            listEnEjecucion.remove(etapa);
        }

        if (tipo == 100) {
            listEjecutado.remove(etapa);
        }

        if (etapa.getId() != null) {
            intervencionEtapaService.intervencionEtapaDetalleDelete(etapa.getId());
        }
        cargarListasTipoTotal();
    }

    private void buscarActuacionesxEtapa(long idEtapa) {
        DateFormat df = DateFormat.getDateInstance();
        try {
            intervencionEtapaActuacions = intervencionEtapaActuacionService.intervencionEtapaActuacionBuscar(idEtapa);
            List<IntervencionEtapaActuacion> lst = new ArrayList<>();
            for (IntervencionEtapaActuacion iea : intervencionEtapaActuacions) {
                if (iea.getActividadId() != null) {
                    Actividad a = actividadService.actividadBuscarOne(iea.getActividadId());
                    iea.setNombreActividad(a.getNombre());
                    iea.setCodigoActividad(a.getCodigoActividad());
                }

                if (iea.getFechaCulminacion() != null) {
                    iea.setFechaStringCulminacion(df.format(iea.getFechaCulminacion()));
                }
            }
            intervencionEtapa.setAvance(defineAvance());
        } catch (Exception e) {
            log.error(e.getCause());
        }
    }

    public void addActuacionActividad(IntervencionEtapaActuacion actuacion) {
        actuacion.setIntervencionEtapa(intervencionEtapa);
        if (actuacion.getId() == null) {
            if (actuacion.getActividadId() != null) {
                if (actuacion.getActividadId() != 0) {
                    Actividad actividad = new Actividad();
                    actividad.setId(actuacion.getActividadId());
                    actuacion.setActividad(actividad);
                }
                if (StringUtils.isNotBlank(actuacion.getDescripcion()) && actuacion.getActividadId() != 0) {

                    actuacion.setEstado("ACT");
                } else {
                    actuacion.setEstado("INA");
                }
            } else {
                actuacion.setEstado("INA");
            }
            intervencionEtapaActuacionService.intervencionEtapaActuacionInsertar(actuacion);
            saveHistorial("agrego la actuacion:" + actuacion.getDescripcion(), usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno(), actuacion.getIntervencionEtapa().getId());
            verHistorial(actuacion.getIntervencionEtapa().getId());
        } else {
            if (!actuacion.getIndCheck()) {
                actuacion.setActividadId(null);
                actuacion.setEstado("INA");
            }
            if (actuacion.getActividadId() != null) {
                if (actuacion.getActividadId() != 0) {
                    Actividad actividad = new Actividad();
                    actividad.setId(actuacion.getActividadId());
                    actuacion.setActividad(actividad);

                    if (StringUtils.isNotBlank(actuacion.getDescripcion()) && actuacion.getActividadId() != 0) {
                        actuacion.setEstado("ACT");
                    } else {
                        actuacion.setEstado("INA");
                    }
                }
            }
            if (actuacion.getIndCheck()) {
                if (actuacion.getActividadId() != 0) {
                    intervencionEtapaActuacionService.intervencionEtapaActuacionUpdate(actuacion);
                }
            } else {
                intervencionEtapaActuacionService.intervencionEtapaActuacionUpdate(actuacion);
            }

        }

        intervencionEtapa.setAvance(defineAvance());
    }

    public void removeActuacionActividad(IntervencionEtapaActuacion iea) {
        if (iea.getId() != null) {
            intervencionEtapaActuacionService.intervencionEtapaActuacionEliminar(iea.getId());
            intervencionEtapa.setAvance(defineAvance());
            saveHistorial("Elimino la actuacion:" + iea.getDescripcion(), usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno(), iea.getIntervencionEtapa().getId());
            verHistorial(iea.getIntervencionEtapa().getId());
        }
        intervencionEtapaActuacions.remove(iea);
        intervencionEtapa.setAvance(defineAvance());
    }

    private Integer defineAvance() {
        double total = intervencionEtapaActuacions.size();
        double activos = 0;

        for (IntervencionEtapaActuacion iea : intervencionEtapaActuacions) {
            if (StringUtils.equals(iea.getEstado(), "ACT")) {
                activos++;
            }
        }
        Double porcentajeD = (activos / total) * 100;
        Integer porcentaje = porcentajeD.intValue();
        return porcentaje;
    }

    private Integer defineAvanceReport(List<IntervencionEtapaActuacion> lista) {
        double total = lista.size();
        double activos = 0;

        for (IntervencionEtapaActuacion iea : lista) {
            if (StringUtils.equals(iea.getEstado(), "ACT")) {
                activos++;
            }
        }
        Double porcentajeD = (activos / total) * 100;
        Integer porcentaje = porcentajeD.intValue();
        return porcentaje;
    }

    public void addListaIntervencionEtapaActuacion() {
        if (StringUtils.isNotBlank(descripcionActuacion)) {
            IntervencionEtapaActuacion iea = new IntervencionEtapaActuacion();
            iea.setDescripcion(descripcionActuacion);
            descripcionActuacion = "";
            intervencionEtapaActuacions.add(iea);
            addActuacionActividad(iea);
        }

    }

    public void saveIntervencionEtapa() {
        for (IntervencionEtapa etapa1 : listPlanificado) {
            if (StringUtils.equals(etapa1.getEstado(), "ACT")) {
                intervencionEtapaService.intervencionEtapaUpdateDetalle(etapa1);
            } else {
                etapa1.setIdIntervencion(intervencion.getId());
                etapa1.setTipo("PLA");
                etapa1.setEstado("ACT");
                intervencionEtapaService.intervencionEtapaInsertar(etapa1);
            }
        }
        cargarListasTipoTotal();
        msg.messageInfo("Se registraron todos los cambios realizados", null);
    }

    public void intervencionEtapaUpdate() {

        IntervencionAccion intervencionAc = new IntervencionAccion();
        intervencionAc.setId(intervencionEtapa.getIntervencionAccionId());
        intervencionEtapa.setIntervencionAccion(intervencionAc);
        intervencionEtapaService.intervencionEtapaUpdate(intervencionEtapa);
        registrarListasTipo();
        saveHistorial("actualizo la intervencion:" + intervencionEtapa.getDescripcion(), usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno(), intervencionEtapa.getId());
        verHistorial(intervencionEtapa.getId());
    }

    public void updateIntervencionEtapaEnEjecucion() {
        List<IntervencionMiembro> ims = intervencionMiembroService.intervencionMiembroBuscar(intervencionEtapa.getId());
        for (IntervencionMiembro im : ims) {
            im.setEstado("TEM");
            intervencionMiembroService.intervencionMiembroUpdate(im);
        }
        if (intervencionEtapa.getListaActuacionesEjecutadas() > 0) {
            intervencionEtapa.setTipo("ENE");
        } else {
            intervencionEtapa.setTipo("PLA");
        }

        for (IntervencionMiembro miembro : intervencionMiembros) {
            miembro.setIntervencionEtapa(intervencionEtapa);
            intervencionMiembroService.intervencionMiembroInsertar(miembro);
        }

        intervencionEtapaUpdate();

        if (ejecutados > 0) {
            intervencionEtapa.setTipo("ENE");
        } else {
            intervencionEtapa.setTipo("PLA");
        }
        msg.messageInfo("Se actualizo el Plan de Intervencion", null);
    }

    public boolean addMiembro(Usuario usuario) {
        for (IntervencionMiembro im : intervencionMiembros) {
            if (StringUtils.equals(usuario.getCodigo(), im.getCodigoUsuario())) {
                return false;
            }
        }
        IntervencionMiembro miembro = new IntervencionMiembro();
        miembro.setCodigoUsuario(usuario.getCodigo());
        miembro.setNombre(usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno());
        miembro.setEstado("TMP");
        miembro.setIntervencionEtapa(intervencionEtapa);
        intervencionMiembros.add(miembro);
        return true;
    }

    public boolean removeMiembro(IntervencionMiembro miembro) {
        if (StringUtils.equals(usuarioSession.getCodigo(), miembro.getCodigoUsuario())) {
            return false;
        } else {
            intervencionMiembros.remove(miembro);
        }
        return true;
    }

    public void priorizar(Intervencion inter) {
        inter.setEstado("PRI");
        setIntervencion(inter);
        updateIntervencion();
        listarIntervenciones();
    }

    public void activar(Intervencion inter) {
        inter.setEstado("ACT");
        setIntervencion(inter);
        updateIntervencion();
        listarIntervenciones();
    }

    public String archivar() {
        intervencion.setEstado("ARC");
        updateIntervencion();
        listarIntervenciones();
        msg.messageAlert("Se archivo el plan de intervención", null);
        return "intervencion";
    }

    public void updateDetalle() {
        updateIntervencion();
        msg.messageInfo("Se registro la descripción correctamente", null);
    }

    public void updateIntervencion() {
        intervencionService.intervencionUpdate(intervencion);
        msg.messageInfo("Se actualizó el detalle de la intervención", null);
    }

    public void openModalRegistroDescripcion(IntervencionEtapa etapa) {
        try {
            actividads = actividadService.actividadxCodigoCasoBuscarTotalAD(intervencion.getCodigoCaso());
            intervencionAccionSelect = new IntervencionAccion();
            descripcionActuacion = "";
            usuarioSession();
            IntervencionEtapa etapa1 = intervencionEtapaService.intervencionEtapaBuscar(etapa.getId());
            if (etapa1.getIntervencionAccionId() != null) {
                intervencionAccionSelect = intervencionAccionService.intervencionAccionBuscar(etapa.getIntervencionAccionId());
            } else {
                etapa.setIntervencionAccionId(null);
            }
            if (StringUtils.equals(etapa.getTipo(), "PLA")) {
                IntervencionMiembro im = new IntervencionMiembro();
                im.setCodigoUsuario(usuarioSession.getCodigo());
                im.setNombre(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
                im.setEstado("ACT");
                intervencionMiembros = new ArrayList<>();
                intervencionMiembros = intervencionMiembroService.intervencionMiembroBuscar(etapa.getId());
                if (intervencionMiembros.isEmpty()) {
                    intervencionMiembros.add(im);
                }
                //intervencionMiembroService.intervencionMiembroInsertar(im);
            }
            if (StringUtils.equals(etapa.getTipo(), "ENE")) {
                intervencionMiembros = new ArrayList<>();
                intervencionMiembros = intervencionMiembroService.intervencionMiembroBuscar(etapa.getId());
            }
            setIntervencionEtapa(etapa);
            buscarActuacionesxEtapa(etapa.getId());
            verHistorial(etapa.getId());
            //intervencionMiembros = intervencionMiembroService.intervencionMiembroBuscar(etapa.getId());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    private void verHistorial(long id) {
        historial = intervencionHistorialActService.intervencionHistorialActBuscar(id);
    }

    public void openModalCampoAccion() {
        intervencionAccion = new IntervencionAccion();
    }

    public boolean saveIntervencion() {
        try {
            intervencions = intervencionService.intervencionBuscar(usuarioSession.getCodigo());

            if (caso.getId() == null) {
                msg.messageAlert("Debe de seleccionar un caso", null);
                return false;
            } else {
                caso = casoService.casoBuscarOne(caso.getId());
            }

            for (Intervencion i : intervencions) {
                if (i.getCodigoCaso().equals(caso.getCodigo())) {
                    msg.messageAlert("Ya ha sido seleccionado el caso", null);
                    return false;
                }
            }
            intervencion.setEstado("ACT");
            intervencion.setNombre(caso.getNombre());
            intervencion.setCodigoCaso(caso.getCodigo());
            intervencionService.intervencionInsertar(intervencion);
            intervencions = intervencionService.intervencionBuscar(usuarioSession.getCodigo());
            listarIntervenciones();
        } catch (Exception ex) {
            log.error(ex);
        }
        return true;
    }

    public void listarIntervenciones() {
        intervencionPriorizadas = intervencionService.intervencionBuscarPriorizados();
        intervencionActivas = intervencionService.intervencionBuscarActivas();
        intervencionArchivadas = intervencionService.intervencionBuscarArchivados();
    }

    public void saveIntervencionAccion() {
        Intervencion inte = new Intervencion();
        inte.setId(intervencion.getId());
        intervencionAccion.setIntervencion(inte);
        intervencionAccionService.intervencionAccionInsertar(intervencionAccion);
        listarAcciones(intervencion.getId());
        msg.messageInfo("Se ha agregado un Campo de Accion", null);
    }

    private void listarAcciones(Long idIntervencion) {
        if (idIntervencion != null) {
            accions = intervencionAccionService.intervencionAccionBuscarxCaso(idIntervencion);
        } else {
            accions = null;
        }

    }

    public Intervencion getIntervencion() {
        return intervencion;
    }

    public void setIntervencion(Intervencion intervencion) {
        this.intervencion = intervencion;
    }

    public List<Intervencion> getIntervencions() {
        return intervencions;
    }

    public void setIntervencions(List<Intervencion> intervencions) {
        this.intervencions = intervencions;
    }

    public List<Intervencion> getIntervencionPriorizadas() {
        return intervencionPriorizadas;
    }

    public void setIntervencionPriorizadas(List<Intervencion> intervencionPriorizadas) {
        this.intervencionPriorizadas = intervencionPriorizadas;
    }

    public IntervencionAccion getIntervencionAccion() {
        return intervencionAccion;
    }

    public void setIntervencionAccion(IntervencionAccion intervencionAccion) {
        this.intervencionAccion = intervencionAccion;
    }

    public List<IntervencionAccion> getAccions() {
        return accions;
    }

    public void setAccions(List<IntervencionAccion> accions) {
        this.accions = accions;
    }

    public List<IntervencionEtapa> getListEnEjecucion() {
        return listEnEjecucion;
    }

    public void setListEnEjecucion(List<IntervencionEtapa> listEnEjecucion) {
        this.listEnEjecucion = listEnEjecucion;
    }

    public List<IntervencionEtapa> getListEjecutado() {
        return listEjecutado;
    }

    public void setListEjecutado(List<IntervencionEtapa> listEjecutado) {
        this.listEjecutado = listEjecutado;
    }

    public List<IntervencionEtapa> getListPlanificado() {
        return listPlanificado;
    }

    public void setListPlanificado(List<IntervencionEtapa> listPlanificado) {
        this.listPlanificado = listPlanificado;
    }

    public IntervencionEtapa getIntervencionEtapa() {
        return intervencionEtapa;
    }

    public void setIntervencionEtapa(IntervencionEtapa intervencionEtapa) {
        this.intervencionEtapa = intervencionEtapa;
    }

    public List<IntervencionEtapaActuacion> getIntervencionEtapaActuacions() {
        return intervencionEtapaActuacions;
    }

    public void setIntervencionEtapaActuacions(List<IntervencionEtapaActuacion> intervencionEtapaActuacions) {
        this.intervencionEtapaActuacions = intervencionEtapaActuacions;
    }

    public List<Actividad> getActividads() {
        return actividads;
    }

    public void setActividads(List<Actividad> actividads) {
        this.actividads = actividads;
    }

    public List<IntervencionMiembro> getIntervencionMiembros() {
        return intervencionMiembros;
    }

    public void setIntervencionMiembros(List<IntervencionMiembro> intervencionMiembros) {
        this.intervencionMiembros = intervencionMiembros;
    }

    public List<IntervencionHistorialAct> getHistorial() {
        return historial;
    }

    public void setHistorial(List<IntervencionHistorialAct> historial) {
        this.historial = historial;
    }

    public String getCadenaAutocomplete() {
        return cadenaAutocomplete;
    }

    public void setCadenaAutocomplete(String cadenaAutocomplete) {
        this.cadenaAutocomplete = cadenaAutocomplete;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public List<Intervencion> getIntervencionArchivadas() {
        return intervencionArchivadas;
    }

    public void setIntervencionArchivadas(List<Intervencion> intervencionArchivadas) {
        this.intervencionArchivadas = intervencionArchivadas;
    }

    public List<Intervencion> getIntervencionActivas() {
        return intervencionActivas;
    }

    public void setIntervencionActivas(List<Intervencion> intervencionActivas) {
        this.intervencionActivas = intervencionActivas;
    }

    public IntervencionAccion getIntervencionAccionSelect() {
        return intervencionAccionSelect;
    }

    public void setIntervencionAccionSelect(IntervencionAccion intervencionAccionSelect) {
        this.intervencionAccionSelect = intervencionAccionSelect;
    }

    public String getDescripcionActuacion() {
        return descripcionActuacion;
    }

    public void setDescripcionActuacion(String descripcionActuacion) {
        this.descripcionActuacion = descripcionActuacion;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public Integer getEjecutados() {
        return ejecutados;
    }

    public void setEjecutados(Integer ejecutados) {
        this.ejecutados = ejecutados;
    }

    public Integer getActividadesTotales() {
        return actividadesTotales;
    }

    public void setActividadesTotales(Integer actividadesTotales) {
        this.actividadesTotales = actividadesTotales;
    }

    public Integer getActividadesTotalesEjecutadas() {
        return actividadesTotalesEjecutadas;
    }

    public void setActividadesTotalesEjecutadas(Integer actividadesTotalesEjecutadas) {
        this.actividadesTotalesEjecutadas = actividadesTotalesEjecutadas;
    }

    public Integer getActividadesPorcentaje() {
        return actividadesPorcentaje;
    }

    public void setActividadesPorcentaje(Integer actividadesPorcentaje) {
        this.actividadesPorcentaje = actividadesPorcentaje;
    }

}