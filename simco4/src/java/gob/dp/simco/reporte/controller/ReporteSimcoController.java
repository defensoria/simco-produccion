/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.controller;

import gob.dp.simco.analisis.entity.AnalisisActorIntensidad;
import gob.dp.simco.analisis.service.AnalisisActorIntensidadService;
import gob.dp.simco.comun.ConstantesUtil;
import gob.dp.simco.comun.FunctionUtil;
import gob.dp.simco.comun.type.AnhosEnum;
import gob.dp.simco.intervencion.entity.Intervencion;
import gob.dp.simco.intervencion.service.IntervencionService;
import gob.dp.simco.registro.bean.FiltroCaso;
import gob.dp.simco.registro.bean.FiltroParametro;
import gob.dp.simco.registro.controller.VictimaViolenciaController;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.entity.CasoActor;
import gob.dp.simco.registro.entity.Parametro;
import gob.dp.simco.registro.service.ActaAcuerdoDetalleService;
import gob.dp.simco.registro.service.ActividadActorService;
import gob.dp.simco.registro.service.ActividadService;
import gob.dp.simco.registro.service.ActorService;
import gob.dp.simco.registro.service.CasoService;
import gob.dp.simco.registro.service.ParametroService;
import gob.dp.simco.reporte.entity.ReporteSimcoActividad;
import gob.dp.simco.reporte.entity.ReporteSimcoCaso;
import gob.dp.simco.reporte.entity.ReporteSimcoActor;
import gob.dp.simco.reporte.entity.ReporteSimcoVictima;
import gob.dp.simco.reporte.service.ReporteSimcoActividadService;
import gob.dp.simco.reporte.service.ReporteSimcoActorService;
import gob.dp.simco.reporte.service.ReporteSimcoCasoService;
import gob.dp.simco.reporte.service.ReporteSimcoVictimaService;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class ReporteSimcoController implements Serializable {

    @Autowired
    private ReporteSimcoCasoService reporteSimcoCasoService;

    @Autowired
    private ReporteSimcoActorService reporteSimcoActorService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private AnalisisActorIntensidadService analisisActorIntensidadService;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private IntervencionService intervencionService;

    @Autowired
    private CasoService casoService;

    @Autowired
    private ActividadActorService actividadActorService;

    @Autowired
    private ReporteSimcoActividadService reporteSimcoActividadService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private ActaAcuerdoDetalleService actaAcuerdoDetalleService;
    
    @Autowired
    private ReporteSimcoVictimaService reporteSimcoVictimaService;

    private List<SelectItem> listaAnhos;

    private List<ReporteSimcoCaso> listReporteCasos;

    private List<ReporteSimcoActor> listReporteActor;

    private List<ReporteSimcoActividad> listReporteActividad;
    
    private List<ReporteSimcoVictima> listReporteVictima;

    private List<Caso> listadoCasos;

    private ReporteSimcoCaso reporteSimcoCaso;

    private ReporteSimcoActor reporteSimcoActor;

    private ReporteSimcoActividad reporteSimcoActividad;
    
    private ReporteSimcoVictima reporteSimcoVictima;

    JasperPrint jasperPrint;

    private Long nroPagina = 1L;
    

    public String cargarReporteCaso() {
        listaAnhos = AnhosEnum.getList();
        reporteSimcoCaso = new ReporteSimcoCaso();
        listReporteCasos = null;
        limpiarReporteCaso();
        return "reporteSimcoCaso";
    }

    public String cargarReporteActor() {
        listaAnhos = AnhosEnum.getList();
        reporteSimcoActor = new ReporteSimcoActor();
        limpiarReporteActor();
        return "reporteSimcoActor";
    }

    public String cargarReporteActividad() {
        listaAnhos = AnhosEnum.getList();
        reporteSimcoActividad = new ReporteSimcoActividad();
        limpiarReporteActividad();
        return "reporteSimcoActividad";
    }
    
    public String cargarReporteVictima(){
        listaAnhos = AnhosEnum.getList();
        reporteSimcoVictima = new ReporteSimcoVictima();
        limpiarReporteVictima();
        return "reporteSimcoVictima";
    }

    public void reporteCasos() {
        listReporteCasos = reporteSimcoCasoService.reporteCasos(reporteSimcoCaso);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (ReporteSimcoCaso rs : listReporteCasos) {
            String esEmpresaMinera = "No";
            String esComunidadNativa = "No";
            List<Actor> listaActor = listaActoresCaso(rs.getIdCaso());
            int minera = 0;
            int nativa = 0;
            for (Actor a : listaActor) {
                if (StringUtils.equals(a.getSubTipo2Empresa(), "01")) {
                    minera++;
                }
                if (StringUtils.equals(a.getTipoOrganizacion(), "10")) {
                    nativa++;
                }
            }
            if (minera > 0) {
                esEmpresaMinera = "Si";
            }
            if (nativa > 0) {
                esComunidadNativa = "Si";
            }
            rs.setEsEmpresaMinera(esEmpresaMinera);
            rs.setEsComunidadNativa(esComunidadNativa);
            if (StringUtils.equals(rs.getEstado(), "Resuelto")) {//resuelto
                rs.setFechaResolucion(rs.getFechaFinCaso());
                try {
                    if (rs.getFechaInicioCaso() != null && rs.getFechaFinCaso() != null) {
                        rs.setMesesResolucion(FunctionUtil.calcularMesesAFecha(simpleDateFormat.parse(rs.getFechaInicioCaso()), simpleDateFormat.parse(rs.getFechaFinCaso())));
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ReporteSimcoController.class.getName()).log(Level.SEVERE, null, ex);
                    rs.setMesesResolucion(null);
                }
            }
            rs.setCantidadAcontecimientos(actividadService.contadorActuacionesAcontecimientos(rs.getIdCaso(), 1));
            rs.setCantidadActuaciones(actividadService.contadorActuacionesAcontecimientos(rs.getIdCaso(), 2));

            List<AnalisisActorIntensidad> listaAnalisis = analisisActorIntensidadService.analisisActorIntensidadBuscar(rs.getIdCaso());
            if (listaAnalisis.size() > 0) {
                rs.setCantidadAnalisis(1);
            } else {
                rs.setCantidadAnalisis(0);
            }

            Intervencion interv = intervencionService.intervencionBuscarCaso(rs.getCodigoCaso());
            if (interv != null) {
                rs.setCantidadIntervencion(1);
            } else {
                rs.setCantidadIntervencion(0);
            }

            rs.setCantidadAcuerdos(reporteSimcoCasoService.cantidadAcuerdosCaso(rs.getIdCaso()));

            rs.setCantidadMuertoCiviles(reporteSimcoCasoService.cantidadMuertosHeridos(rs.getCodigoCaso(), "01", "01"));
            rs.setCantidadMuertoPNP(reporteSimcoCasoService.cantidadMuertosHeridos(rs.getCodigoCaso(), "01", "02"));
            rs.setCantidadMuertoFFAA(reporteSimcoCasoService.cantidadMuertosHeridos(rs.getCodigoCaso(), "01", "03"));
            rs.setCantidadHeridoCiviles(reporteSimcoCasoService.cantidadMuertosHeridos(rs.getCodigoCaso(), "02", "01"));
            rs.setCantidadHeridoPNP(reporteSimcoCasoService.cantidadMuertosHeridos(rs.getCodigoCaso(), "02", "02"));
            rs.setCantidadHeridoFFAA(reporteSimcoCasoService.cantidadMuertosHeridos(rs.getCodigoCaso(), "02", "03"));
        }
    }

    public void reporteActores() {
        listReporteActor = reporteSimcoActorService.reporteActor(reporteSimcoActor);
        for (ReporteSimcoActor a : listReporteActor) {
            if (StringUtils.equals(a.getTipoActor(), "PE")) {
                a.setDescripcionTipoActor("Población");
            }
            if (StringUtils.equals(a.getTipoActor(), "EN")) {
                a.setDescripcionTipoActor("Entidad estatal");
            }
            if (StringUtils.equals(a.getTipoActor(), "EM") && StringUtils.equals(a.getClasificacion(), "OR")) {
                a.setDescripcionTipoActor("Organización");
            }
            if (StringUtils.equals(a.getTipoActor(), "EM") && StringUtils.equals(a.getClasificacion(), "EM")) {
                a.setDescripcionTipoActor("Empresa");
            }

            if (StringUtils.equals(a.getSexo(), "M")) {
                a.setSexo("Masculino");
            }
            if (StringUtils.equals(a.getSexo(), "F")) {
                a.setSexo("Femenino");
            }

            if (StringUtils.equals(a.getTipoActor(), "PE")) {
                a.setSubtipo(a.getTipoPoblacion());
                a.setSubtipo1(a.getSubTipo1Poblacion());
                a.setSubtipo2(a.getSubTipo2Poblacion());
            }
            if (StringUtils.equals(a.getTipoActor(), "EN")) {
                a.setSubtipo(a.getTipoEntidad());
                a.setSubtipo1(a.getSubTipo1Entidad());
                a.setSubtipo2(a.getSubTipo2Entidad());
            }
            if (StringUtils.equals(a.getTipoActor(), "EM") && StringUtils.equals(a.getClasificacion(), "OR")) {
                a.setSubtipo(a.getTipoOrganizacion());
                a.setSubtipo1(a.getSubTipo1Organizacion());
                a.setSubtipo2(a.getSubTipo2Organizacion());
            }
            if (StringUtils.equals(a.getTipoActor(), "EM") && StringUtils.equals(a.getClasificacion(), "EM")) {
                a.setSubtipo(a.getTipoEmpresa());
                a.setSubtipo1(a.getSubTipo1Empresa());
                a.setSubtipo2(a.getSubTipo2Empresa());
                a.setSubtipo3(a.getSubTipo3Empresa());
            }

            a.setContadorActorAcontecimiento(reporteSimcoActorService.contarActorAcontecimiento(a.getIdActor()));
            a.setContadorActorAcontecimientoProtesta(reporteSimcoActorService.contarActorAcontecimientoProtesta(a.getIdActor()));
            a.setContadorActorAcontecimientoProtestaViolencia(reporteSimcoActorService.contarActorAcontecimientoProtestaViolencia(a.getIdActor()));
            a.setContadorActorAcuerdoComprometido(reporteSimcoActorService.contarActorAcuerdoComprometido(a.getIdActor()));
            a.setContadorActorAcuerdoBeneficiario(reporteSimcoActorService.contarActorAcuerdoBeneficiario(a.getIdActor()));
            a.setPonderado(actividadActorService.ponderadoAD(a.getIdActor()));
            a.setContadorActorCaso(reporteSimcoActorService.contarCasosPorActor(a.getIdActor()));
            a.setContadorCasosPorActorPrimario(reporteSimcoActorService.contarCasosPorActorParticipacion(new CasoActor(a.getIdActor(), "01")));
            a.setContadorCasosPorActorSecundario(reporteSimcoActorService.contarCasosPorActorParticipacion(new CasoActor(a.getIdActor(), "02")));
            a.setContadorCasosPorActorTerciario(reporteSimcoActorService.contarCasosPorActorParticipacion(new CasoActor(a.getIdActor(), "03")));
        }
    }

    public void reporteActividades() {
        listReporteActividad = reporteSimcoActividadService.reporteActividad(reporteSimcoActividad);
        for (ReporteSimcoActividad ac : listReporteActividad) {
            ac.setCantidadActores(actorService.actorXactividadSimpleBuscarCount(ac.getIdActividad()));
            if (StringUtils.equals(ac.getClaseActividad(), "AD")) {
                ac.setClaseActividad("Actuacion defensorial");
                ac.setTipoActividadDetalle(ac.getTipoActuacionDefensorialDetalle());
                if(StringUtils.isNotBlank(ac.getTipoActuacionDefensorial()))
                    ac.setGrupoActividadDetalle(obtenerGrupoActuacionDefensorial(ac.getTipoActuacionDefensorial()));
            }
            if (StringUtils.equals(ac.getClaseActividad(), "AC")) {
                ac.setClaseActividad("Acontecimiento");
                ac.setTipoViolenciaItem1("NO");
                        ac.setTipoViolenciaItem2("NO");
                        ac.setTipoViolenciaItem3("NO");
                        ac.setTipoViolenciaItem4("NO");
                        ac.setTipoViolenciaItem5("NO");
                        ac.setTipoViolenciaItem6("NO");
                        ac.setTipoViolenciaItem7("NO");
                if (StringUtils.isNotBlank(ac.getTipoViolencia())) {
                    List<String> itemsTipoViolencia = Arrays.asList(ac.getTipoViolencia().split("\\s*,\\s*"));
                    List<String> ses = new ArrayList<>();
                    for (String s : itemsTipoViolencia) {
                        
                        if(StringUtils.equals(s, "01")){
                            ac.setTipoViolenciaItem1("SI");
                        }
                        if(StringUtils.equals(s, "02")){
                            ac.setTipoViolenciaItem2("SI");
                        }
                        if(StringUtils.equals(s, "03")){
                            ac.setTipoViolenciaItem3("SI");
                        }
                        if(StringUtils.equals(s, "04")){
                            ac.setTipoViolenciaItem4("SI");
                        }
                        if(StringUtils.equals(s, "05")){
                            ac.setTipoViolenciaItem5("SI");
                        }
                        if(StringUtils.equals(s, "06")){
                            ac.setTipoViolenciaItem6("SI");
                        }
                        if(StringUtils.equals(s, "07")){
                            ac.setTipoViolenciaItem7("SI");
                        }
                        Parametro p = parametroService.consultarParametroValor(new FiltroParametro(250, s));
                        ses.add(p.getNombreParametro());
                    }
                    ac.setListaTipoViolencia(ses);
                }
                ac.setCantidadAcuerdos(actaAcuerdoDetalleService.actaAcuerdoDetalleCount(ac.getIdActividad()));
                
                if(StringUtils.isNotBlank(ac.getTipoAcontecimiento())){
                    ac.setSubTipoAcontecimientoDetalle(obtenerSubTipoAcontecimiento(ac.getTipoAcontecimiento()));
                    ac.setTipoAcontecimientoDetalle(obtenerTipoAcontecimiento(ac.getTipoAcontecimiento()));
                    ac.setGrupoAcontecimientoDetalle(obtenerGrupoAcontecimiento(ac.getTipoAcontecimiento()));
                    ac.setTipoActividadDetalle(ac.getTipoAcontecimientoDetalle());
                    ac.setGrupoActividadDetalle(ac.getGrupoAcontecimientoDetalle());
                }
                FacesContext context = FacesContext.getCurrentInstance();
                VictimaViolenciaController victimaViolenciaController = (VictimaViolenciaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "victimaViolenciaController");
                Map<String, String> victimas = victimaViolenciaController.listaVictimas(ac.getIdActividad());
                List<String> itemsResultadoViolencia = new ArrayList<>();
                ac.setResultadoViolenciaItem1(0);
                ac.setResultadoViolenciaItem2(0);
                ac.setResultadoViolenciaItem3(0);
                ac.setResultadoViolenciaItem4(0);
                ac.setResultadoViolenciaItem5(0);
                ac.setResultadoViolenciaItem6(0);
                ac.setResultadoViolenciaItem7(0);
                for (Map.Entry entry : victimas.entrySet()){
                    String item = entry.getKey()+": "+entry.getValue();
                    itemsResultadoViolencia.add(item);
                    if(StringUtils.equals(entry.getKey().toString(), "Muertos"))
                        ac.setResultadoViolenciaItem1(Integer.parseInt(entry.getValue().toString()));
                    if(StringUtils.equals(entry.getKey().toString(), "Heridos"))
                        ac.setResultadoViolenciaItem2(Integer.parseInt(entry.getValue().toString()));
                    if(StringUtils.equals(entry.getKey().toString(), "Secuestrados"))
                        ac.setResultadoViolenciaItem3(Integer.parseInt(entry.getValue().toString()));
                    if(StringUtils.equals(entry.getKey().toString(), "Detenidos"))
                        ac.setResultadoViolenciaItem4(Integer.parseInt(entry.getValue().toString()));
                    if(StringUtils.equals(entry.getKey().toString(), "Desaparecidos"))
                        ac.setResultadoViolenciaItem5(Integer.parseInt(entry.getValue().toString()));
                    if(StringUtils.equals(entry.getKey().toString(), "Otros"))
                        ac.setResultadoViolenciaItem6(Integer.parseInt(entry.getValue().toString()));
                    if(StringUtils.equals(entry.getKey().toString(), "NN"))
                        ac.setResultadoViolenciaItem7(Integer.parseInt(entry.getValue().toString()));
                }
                ac.setListaResultadoViolencia(itemsResultadoViolencia);
            }
        }
    }
    
    public void reporteVictimas(){
        listReporteVictima  = reporteSimcoVictimaService.reporteVictima(reporteSimcoVictima);
        for(ReporteSimcoVictima rsv : listReporteVictima){
            if(StringUtils.isNotBlank(rsv.getTipoAcontecimiento())){
                rsv.setSubTipoAcontecimientoDetalle(obtenerSubTipoAcontecimiento(rsv.getTipoAcontecimiento()));
                rsv.setTipoAcontecimientoDetalle(obtenerTipoAcontecimiento(rsv.getTipoAcontecimiento()));
                rsv.setGrupoAcontecimientoDetalle(obtenerGrupoAcontecimiento(rsv.getTipoAcontecimiento()));
                if(rsv.getIndicadorNN() != null && rsv.getIndicadorNN() == 1)
                    rsv.setTipoVictima("NN");    
            }
            rsv.setCantidadActores(actorService.actorXactividadSimpleBuscarCount(rsv.getIdActividad()));
        }
    }

    public void reporteSimcoCasoExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperSimcoCaso(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporteCasos.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteSimcoCasoPdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperSimcoCaso(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporteSimcoCaso.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    public void reporteSimcoActorExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperSimcoActor(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporteActores.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteSimcoActorPdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperSimcoActor(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporteSimcoActor.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    public void reporteSimcoActividadExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperSimcoActividad(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporteActividad.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteSimcoActividadPdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperSimcoActividad(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporteSimcoActividad.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteSimcoVictimaExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperSimcoVictima(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporteVictima.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteSimcoVictimaPdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperSimcoVictima(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporteSimcoVictima.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    public void initJasperSimcoCaso(int tipo) throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listReporteCasos);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(ConstantesUtil.BASE_URL_REPORT + "reporteSimcoCasoExcel.jasper", new HashMap(), beanCollectionDataSource);
        } else {
            jasperPrint = JasperFillManager.fillReport(ConstantesUtil.BASE_URL_REPORT + "reporteSimcoCasoPdf.jasper", new HashMap(), beanCollectionDataSource);
        }
    }

    public void initJasperSimcoActor(int tipo) throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listReporteActor);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(ConstantesUtil.BASE_URL_REPORT + "reporteSimcoActorExcel.jasper", new HashMap(), beanCollectionDataSource);
        } else {
            jasperPrint = JasperFillManager.fillReport(ConstantesUtil.BASE_URL_REPORT + "reporteSimcoActorPdf.jasper", new HashMap(), beanCollectionDataSource);
        }
    }

    public void initJasperSimcoActividad(int tipo) throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listReporteActividad);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(ConstantesUtil.BASE_URL_REPORT + "reporteSimcoActividadExcel.jasper", new HashMap(), beanCollectionDataSource);
        } else {
            jasperPrint = JasperFillManager.fillReport(ConstantesUtil.BASE_URL_REPORT + "reporteSimcoActividadPdf.jasper", new HashMap(), beanCollectionDataSource);
        }
    }
    
    public void initJasperSimcoVictima(int tipo) throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listReporteVictima);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(ConstantesUtil.BASE_URL_REPORT + "reporteSimcoVictimaExcel.jasper", new HashMap(), beanCollectionDataSource);
        } else {
            jasperPrint = JasperFillManager.fillReport(ConstantesUtil.BASE_URL_REPORT + "reporteSimcoVictimaPdf.jasper", new HashMap(), beanCollectionDataSource);
        }
    }

    private List<Actor> listaActoresCaso(Long idCaso) {
        List<Actor> actors = actorService.actorxCasoBuscar(idCaso);
        return actors;
    }

    public void listaCasos(Long pagina) {
        int paginado = ConstantesUtil.PAGINADO_10;
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nombreCaso");
        Long ini = paginado * (pagina - 1) + 1;
        Long fin = paginado * pagina;
        if (pagina == 0) {
            ini = 1L;
            fin = 10L;
            pagina = 1L;
        }
        FiltroCaso filtroCaso = new FiltroCaso();
        filtroCaso.setIni(ini);
        filtroCaso.setFin(fin);
        filtroCaso.setNombre(value);
        try {
            List<Caso> lista = casoService.buscarCasoXnombreCodigo(filtroCaso);
            if (lista.size() > 0) {
                listadoCasos = lista;
                nroPagina = pagina;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setearCasoActor(Caso caso) {
        reporteSimcoActor.setCodigoCaso(caso.getCodigo());
        reporteSimcoActor.setNombreCaso(caso.getCodigo() + " " + caso.getNombre());
    }

    public void setearCasoActividad(Caso caso) {
        reporteSimcoActividad.setCodigoCaso(caso.getCodigo());
        reporteSimcoActividad.setNombreCaso(caso.getCodigo() + " " + caso.getNombre());
    }
    
    public void setearCasoVictima(Caso caso) {
        reporteSimcoVictima.setCodigoCaso(caso.getCodigo());
        reporteSimcoVictima.setNombreCaso(caso.getCodigo() + " " + caso.getNombre());
    }

    public void setearActorActor(Actor actor) {
        reporteSimcoActor.setIdActor(actor.getId());
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(actor.getNombre())) {
            sb.append(actor.getNombre());
        }
        if (StringUtils.isNotBlank(actor.getApellidoPat())) {
            sb.append(" ").append(actor.getApellidoPat());
        }
        if (StringUtils.isNotBlank(actor.getApellidoMat())) {
            sb.append(" ").append(actor.getApellidoMat());
        }
        reporteSimcoActor.setNombreActor(sb.toString());
    }

    public void setearActorActividad(Actor actor) {
        reporteSimcoActividad.setIdActor(actor.getId());
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(actor.getNombre())) {
            sb.append(actor.getNombre());
        }
        if (StringUtils.isNotBlank(actor.getApellidoPat())) {
            sb.append(" ").append(actor.getApellidoPat());
        }
        if (StringUtils.isNotBlank(actor.getApellidoMat())) {
            sb.append(" ").append(actor.getApellidoMat());
        }
        reporteSimcoActividad.setNombreActor(sb.toString());
    }
    
    public void setearActorVictima(Actor actor) {
        reporteSimcoVictima.setIdActor(actor.getId());
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(actor.getNombre())) {
            sb.append(actor.getNombre());
        }
        if (StringUtils.isNotBlank(actor.getApellidoPat())) {
            sb.append(" ").append(actor.getApellidoPat());
        }
        if (StringUtils.isNotBlank(actor.getApellidoMat())) {
            sb.append(" ").append(actor.getApellidoMat());
        }
        reporteSimcoVictima.setNombreActor(sb.toString());
    }

    public void limpiarReporteCaso() {
        reporteSimcoCaso = new ReporteSimcoCaso();
        listReporteCasos = null;
    }

    public void limpiarReporteActor() {
        reporteSimcoActor = new ReporteSimcoActor();
        listReporteActor = null;
    }

    public void limpiarReporteActividad() {
        reporteSimcoActividad = new ReporteSimcoActividad();
        listReporteActividad = null;
    }
    
    public void limpiarReporteVictima() {
        reporteSimcoVictima = new ReporteSimcoVictima();
        listReporteVictima = null;
    }

    public void setearActor(Actor actor) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(actor.getNombre())) {
            sb.append(actor.getNombre());
        }
        if (StringUtils.isNotBlank(actor.getApellidoPat())) {
            sb.append(" ").append(actor.getApellidoPat());
        }
        if (StringUtils.isNotBlank(actor.getApellidoMat())) {
            sb.append(" ").append(actor.getApellidoMat());
        }
        reporteSimcoCaso.setNombreActor(sb.toString());
    }

    private String obtenerSubTipoAcontecimiento(String subTipo) {
        if (StringUtils.equals(subTipo, "01")) {
            return "Movilización";
        }
        if (StringUtils.equals(subTipo, "02")) {
            return "Bloqueo de vías (carreteras o vías de acceso)";
        }
        if (StringUtils.equals(subTipo, "03")) {
            return "Paros (24 horas, 48 horas, 72 horas, indefinido)";
        }
        if (StringUtils.equals(subTipo, "04")) {
            return "Plantones (concentraciones o mítines)";
        }
        if (StringUtils.equals(subTipo, "05")) {
            return "Huelgas (huelga indefinida)";
        }
        if (StringUtils.equals(subTipo, "06")) {
            return "Toma de entidades, locales";
        }
        if (StringUtils.equals(subTipo, "07")) {
            return "Marcha";
        }
        if (StringUtils.equals(subTipo, "08")) {
            return "Otros (Huelgas de hambre, marchas de sacrificio, vigilias, encadenamientos, desangramientos, crucifixiones, etc.)";
        }
        if (StringUtils.equals(subTipo, "09")) {
            return "Pedido de información";
        }
        if (StringUtils.equals(subTipo, "10")) {
            return "Envío de información";
        }
        if (StringUtils.equals(subTipo, "11")) {
            return "Llamado a la acción/intervención";
        }
        if (StringUtils.equals(subTipo, "22")) {
            return "Decreto supremo";
        }
        if (StringUtils.equals(subTipo, "23")) {
            return "Resolución suprema";
        }
        if (StringUtils.equals(subTipo, "24")) {
            return "Resolución directoral";
        }
        if (StringUtils.equals(subTipo, "25")) {
            return "Resolución jefatural";
        }
        if (StringUtils.equals(subTipo, "26")) {
            return "otras resoluciones";
        }
        if (StringUtils.equals(subTipo, "27")) {
            return "Sentencia";
        }
        if (StringUtils.equals(subTipo, "28")) {
            return "Medida cautelar";
        }
        return null;
    }

    private String obtenerTipoAcontecimiento(String subTipo) {
        if (StringUtils.equals(subTipo, "01") || StringUtils.equals(subTipo, "02") || StringUtils.equals(subTipo, "03") || StringUtils.equals(subTipo, "04")
                || StringUtils.equals(subTipo, "05") || StringUtils.equals(subTipo, "06") || StringUtils.equals(subTipo, "07") || StringUtils.equals(subTipo, "08")) {
            return "Acciones colectivas de protesta";
        }
        if (StringUtils.equals(subTipo, "09") || StringUtils.equals(subTipo, "10") || StringUtils.equals(subTipo, "11")) {
            return "Envío de oficios";
        }
        if (StringUtils.equals(subTipo, "12")) 
            return "Detención";
        if (StringUtils.equals(subTipo, "13")) 
            return "Intervenciones estatales";
        if (StringUtils.equals(subTipo, "14"))
            return "Intervenciones/acciones privadas";
        if (StringUtils.equals(subTipo, "15"))
            return "Resoluciones del TC";
        if (StringUtils.equals(subTipo, "16")) 
            return "Actos Legislativos";
        if (StringUtils.equals(subTipo, "17"))
            return "Resolución fiscal";
        if (StringUtils.equals(subTipo, "18"))
            return "Pronunciamientos";
        if (StringUtils.equals(subTipo, "19"))
            return "Opiniones";
        if (StringUtils.equals(subTipo, "20"))
            return "Reuniones";
        
        if (StringUtils.equals(subTipo, "22") || StringUtils.equals(subTipo, "23") || StringUtils.equals(subTipo, "24")
                || StringUtils.equals(subTipo, "25") || StringUtils.equals(subTipo, "26")) {
            return "Actos Administrativos";
        }
        if (StringUtils.equals(subTipo, "27") || StringUtils.equals(subTipo, "28")) {
            return "Resolución judicial";
        }

        return null;
    }

    private String obtenerGrupoAcontecimiento(String subTipo) {
        if (StringUtils.equals(subTipo, "01") || StringUtils.equals(subTipo, "02") || StringUtils.equals(subTipo, "03") || StringUtils.equals(subTipo, "04")
                || StringUtils.equals(subTipo, "05") || StringUtils.equals(subTipo, "06") || StringUtils.equals(subTipo, "07") || StringUtils.equals(subTipo, "08")) {
            return "Acciones en general";
        }
        if (StringUtils.equals(subTipo, "09") || StringUtils.equals(subTipo, "10") || StringUtils.equals(subTipo, "11")) {
            return "Acciones en general";
        }
        if (StringUtils.equals(subTipo, "12") || StringUtils.equals(subTipo, "13") || StringUtils.equals(subTipo, "14")) {
            return "Acciones en general";
        }
        if (StringUtils.equals(subTipo, "15") || StringUtils.equals(subTipo, "16") || StringUtils.equals(subTipo, "17")) {
            return "Emisión de normas y/o resoluciones";
        }
        if (StringUtils.equals(subTipo, "18") || StringUtils.equals(subTipo, "19")) {
            return "Declaraciones públicas";
        }
        if (StringUtils.equals(subTipo, "20")) {
            return "Acciones en general";
        }
        if (StringUtils.equals(subTipo, "21")) {
            return subTipo;
        }
        if (StringUtils.equals(subTipo, "22") || StringUtils.equals(subTipo, "23") || StringUtils.equals(subTipo, "24")
                || StringUtils.equals(subTipo, "25") || StringUtils.equals(subTipo, "26")) {
            return "Emisión de normas y/o resoluciones";
        }
        if (StringUtils.equals(subTipo, "27") || StringUtils.equals(subTipo, "28")) {
            return "Emisión de normas y/o resoluciones";
        }
        return null;
    }
    
    private String obtenerGrupoActuacionDefensorial(String tipo) {
        if (StringUtils.equals(tipo, "01") || StringUtils.equals(tipo, "02")) {
            return "Acciones de defensa legal";
        }
        if (StringUtils.equals(tipo, "03") || StringUtils.equals(tipo, "04") || StringUtils.equals(tipo, "05")){
            return "Acciones humanitarias";
        }
        if (StringUtils.equals(tipo, "06") || StringUtils.equals(tipo, "07")) {
            return "Intermediación";
        }
        if (StringUtils.equals(tipo, "08") || StringUtils.equals(tipo, "09") || StringUtils.equals(tipo, "10") || StringUtils.equals(tipo, "11")
                || StringUtils.equals(tipo, "12") || StringUtils.equals(tipo, "13") || StringUtils.equals(tipo, "14")){
            return "Supervisión preventiva";
        }
        return null;
    }

    public List<SelectItem> getListaAnhos() {
        return listaAnhos;
    }

    public void setListaAnhos(List<SelectItem> listaAnhos) {
        this.listaAnhos = listaAnhos;
    }

    public List<ReporteSimcoCaso> getListReporteCasos() {
        return listReporteCasos;
    }

    public void setListReporteCasos(List<ReporteSimcoCaso> listReporteCasos) {
        this.listReporteCasos = listReporteCasos;
    }

    public List<ReporteSimcoActor> getListReporteActor() {
        return listReporteActor;
    }

    public void setListReporteActor(List<ReporteSimcoActor> listReporteActor) {
        this.listReporteActor = listReporteActor;
    }

    public ReporteSimcoActor getReporteSimcoActor() {
        return reporteSimcoActor;
    }

    public void setReporteSimcoActor(ReporteSimcoActor reporteSimcoActor) {
        this.reporteSimcoActor = reporteSimcoActor;
    }

    public ReporteSimcoCaso getReporteSimcoCaso() {
        return reporteSimcoCaso;
    }

    public void setReporteSimcoCaso(ReporteSimcoCaso reporteSimcoCaso) {
        this.reporteSimcoCaso = reporteSimcoCaso;
    }

    public List<Caso> getListadoCasos() {
        return listadoCasos;
    }

    public void setListadoCasos(List<Caso> listadoCasos) {
        this.listadoCasos = listadoCasos;
    }

    public Long getNroPagina() {
        return nroPagina;
    }

    public void setNroPagina(Long nroPagina) {
        this.nroPagina = nroPagina;
    }

    public ReporteSimcoActividad getReporteSimcoActividad() {
        return reporteSimcoActividad;
    }

    public void setReporteSimcoActividad(ReporteSimcoActividad reporteSimcoActividad) {
        this.reporteSimcoActividad = reporteSimcoActividad;
    }

    public List<ReporteSimcoActividad> getListReporteActividad() {
        return listReporteActividad;
    }

    public void setListReporteActividad(List<ReporteSimcoActividad> listReporteActividad) {
        this.listReporteActividad = listReporteActividad;
    }

    public ReporteSimcoVictima getReporteSimcoVictima() {
        return reporteSimcoVictima;
    }

    public void setReporteSimcoVictima(ReporteSimcoVictima reporteSimcoVictima) {
        this.reporteSimcoVictima = reporteSimcoVictima;
    }

    public List<ReporteSimcoVictima> getListReporteVictima() {
        return listReporteVictima;
    }

    public void setListReporteVictima(List<ReporteSimcoVictima> listReporteVictima) {
        this.listReporteVictima = listReporteVictima;
    }

}
