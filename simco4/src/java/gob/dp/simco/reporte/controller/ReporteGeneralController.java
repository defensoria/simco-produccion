/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.controller;

import gob.dp.simco.parametro.entity.Catalogo;
import gob.dp.simco.parametro.service.CatalogoService;
import gob.dp.simco.comun.util.Filter;
import gob.dp.simco.comun.util.FilterList;
import gob.dp.simco.comun.entity.Departamento;
import gob.dp.simco.comun.entity.RegistroCarga;
import gob.dp.simco.comun.entity.Resumen;
import gob.dp.simco.comun.service.RegistroCargaService;
import gob.dp.simco.comun.service.ReporteService;
import gob.dp.simco.comun.service.UbigeoService;
import gob.dp.simco.comun.type.MesesEnum;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.service.ActividadService;
import gob.dp.simco.registro.service.CasoService;
import gob.dp.simco.registro.type.EstadoCasoType;
import gob.dp.simco.registro.type.MesType;
import gob.dp.simco.reporte.entity.ChartTotal;
import gob.dp.simco.reporte.entity.CuadroGenericoMes;
import gob.dp.simco.reporte.entity.ElementoNombreValor;
import gob.dp.simco.reporte.entity.ElementoReporte;
import gob.dp.simco.reporte.entity.ElementoResumenEjecutivo;
import gob.dp.simco.reporte.entity.EstadoConflicto;
import gob.dp.simco.reporte.entity.FiltroReporte;
import gob.dp.simco.reporte.entity.NuevoCaso;
import gob.dp.simco.reporte.entity.ReporteSimcoCaso;
import gob.dp.simco.reporte.service.ReporteEjecutivoService;
import gob.dp.simco.reporte.service.ReporteGeneralService;
import gob.dp.simco.reporte.service.ReporteSimcoCasoService;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
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
public class ReporteGeneralController extends AbstractManagedBean implements Serializable {

    private FiltroReporte filtroReporte;

    private List<SelectItem> listaDepartamento;

    List<ElementoReporte> elementoReportes;

    private List<Caso> listaCasosReporte;

    private List<Resumen> listaAnhos;

    private Map listasMeses;
    
    JasperPrint jasperPrint;
    
    private List<ElementoResumenEjecutivo> listaResumenEjecutivo;

    @Autowired
    private UbigeoService ubigeoService;

    @Autowired
    private ReporteGeneralService reporteGeneralService;

    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private ReporteService reporteService;
    
    @Autowired
    private RegistroCargaService registroCargaService;
    
    @Autowired
    private ReporteEjecutivoService reporteEjecutivoService;
    
    @Autowired
    private CasoService casoService;
    
    @Autowired
    private ReporteSimcoCasoService reporteSimcoCasoService;
    
    @Autowired
    private ActividadService actividadService;

    public String reporte() {
        filtroReporte = new FiltroReporte();
        listaAnhos = reporteService.listadoAnhoCaso();
        cargarMeses();
        return "reporteGeneral";
    }
    
    private Integer casosDeLatenteAActivoMes(){
        filtroReporte.setTipoEstadoCaso(EstadoCasoType.ACTIVO.getKey());
        List<Caso> lista = casoService.listadoCasosEstadoMes(filtroReporte);
        int i = 0;
        for(Caso c : lista){
            List<Caso> listaInactivos =  casoService.listaCasosAntesDeAprobado(c.getCodigo());
            for(Caso c1 : listaInactivos){
                if(!StringUtils.equals(c1.getTipoEstado(), EstadoCasoType.ACTIVO.getKey())){
                    if(StringUtils.equals(c1.getTipoEstado(),EstadoCasoType.LATENTE.getKey())){
                        i++;
                    }
                    break;
                }
            }
        }
        return i;
    }
    
    private Integer casosDeActivoALatenteMes(){
        filtroReporte.setTipoEstadoCaso(EstadoCasoType.LATENTE.getKey());
        List<Caso> lista = casoService.listadoCasosEstadoMes(filtroReporte);
        int i = 0;
        for(Caso c : lista){
            List<Caso> listaInactivos =  casoService.listaCasosAntesDeAprobado(c.getCodigo());
            for(Caso c1 : listaInactivos){
                if(!StringUtils.equals(c1.getTipoEstado(), EstadoCasoType.LATENTE.getKey())){
                    if(StringUtils.equals(c1.getTipoEstado(),EstadoCasoType.ACTIVO.getKey())){
                        i++;
                    }
                    break;
                }
            }
        }
        return i;
    }
    
    private Integer casosDeLatenteARetirado(){
        filtroReporte.setTipoEstadoCaso(EstadoCasoType.RETIRADO.getKey());
        List<Caso> lista = casoService.listadoCasosEstadoMes(filtroReporte);
        int i = 0;
        for(Caso c : lista){
            List<Caso> listaInactivos =  casoService.listaCasosAntesDeAprobado(c.getCodigo());
            for(Caso c1 : listaInactivos){
                if(!StringUtils.equals(c1.getTipoEstado(), EstadoCasoType.RETIRADO.getKey())){
                    if(StringUtils.equals(c1.getTipoEstado(),EstadoCasoType.LATENTE.getKey())){
                        i++;
                    }
                    break;
                }
            }
        }
        return i;
    }
    
    private Integer casosDeRetiradoAActivoMes(){
        filtroReporte.setTipoEstadoCaso(EstadoCasoType.ACTIVO.getKey());
        List<Caso> lista = casoService.listadoCasosEstadoMes(filtroReporte);
        int i = 0;
        for(Caso c : lista){
            List<Caso> listaInactivos =  casoService.listaCasosAntesDeAprobado(c.getCodigo());
            for(Caso c1 : listaInactivos){
                if(!StringUtils.equals(c1.getTipoEstado(), EstadoCasoType.ACTIVO.getKey())){
                    if(StringUtils.equals(c1.getTipoEstado(),EstadoCasoType.RETIRADO.getKey())){
                        i++;
                    }
                    break;
                }
            }
        }
        return i;
    }
    
    public void resumenEjecutivo() throws JRException, IOException{
        listaResumenEjecutivo = new ArrayList<>();
        Integer totalCasosRegistrados;
        Integer totalCasosActivos;
        Integer totalCasosLatentes;
        
        Integer totalCasosRegistradosMes;
        Integer totalCasosDeLatenteAActivoMes;
        Integer totalCasosDeActivosALatenteMes;
        Integer totalCasosDeRetiradoAActivoMes;
        
        Integer totalCasosResueltoMes;
        Integer totalCasosDeLatenteARetiradoMes;
        Integer totalCasosFaseDialogo;
        Integer totalCasosDialogoNegociacion;
        Integer totalCasosDialogoReuniones;
        Integer totalCasosDialogoEspacioDialogo;
        
        Integer totalCasosACVictimaViolencia;
        Integer totalCasosACVictimaViolenciaMes;
        
        Integer totalCasosACAccionesProtestaMes;
                
        Integer totalCasosAD;
        Integer totalCasosADMes;        
        Integer totalActividadAccionesDefensaLegalMes;        
        Integer totalActividadAccionesHumanitariasMes;        
        Integer totalActividadIntermediacionMes;        
        Integer totalActividadSupervisionPreventivaMes;        
          
        List<ReporteSimcoCaso> listaCasosNuevosPorMes;        
        List<ReporteSimcoCaso> listaCasosResueltosPorMes;
        List<ReporteSimcoCaso> listaCasosActivosTotales;
                
        
        
        
        Integer totalActivosLatentes;
        Integer totalLatentesObservacion;
        Integer totalGeneralDialogo;
        Integer totalGeneralActivo;
        Integer totalGeneralDialogoProceso;
        Integer totalGeneraReunionesPreparatorias;
        Integer totalGeneraParticipacionDefensoria;
        Integer totalGeneraCasosViolencia;
        Integer totalGeneraCasosAccionesProtesta;
        Integer totalGeneralActuacion;
        Integer totalGeneralActuacionSupervisionPreventiva;
        Integer totalGeneralActuacionIntermediaciones;
        Integer totalGeneralActuacionAccionHumanitaria;
        Integer totalGeneralActuacionDefensaLegal;
        
        ElementoResumenEjecutivo ejecutivo = new ElementoResumenEjecutivo();
        
  
        totalCasosRegistrados = reporteEjecutivoService.totalCasosRegistrados();
        totalCasosActivos = reporteEjecutivoService.totalCasosActivos();
        totalCasosLatentes = reporteEjecutivoService.totalCasosLatentes();
        
        totalCasosRegistradosMes = reporteEjecutivoService.totalCasosRegistradosMes(filtroReporte);
        totalCasosDeLatenteAActivoMes = casosDeLatenteAActivoMes();
        totalCasosDeActivosALatenteMes = casosDeActivoALatenteMes();
        totalCasosDeRetiradoAActivoMes = casosDeRetiradoAActivoMes();
        totalCasosResueltoMes = reporteEjecutivoService.totalCasosResueltoMes(filtroReporte);
        totalCasosDeLatenteARetiradoMes = casosDeLatenteARetirado();
        
        totalCasosFaseDialogo = reporteEjecutivoService.totalCasosDialogo();
        totalCasosDialogoNegociacion = reporteEjecutivoService.totalCasosDialogoNegociacion();
        totalCasosDialogoReuniones = reporteEjecutivoService.totalCasosDialogoReuniones();
        totalCasosDialogoEspacioDialogo = reporteEjecutivoService.totalCasosDialogoEspacioDialogo();
        
        totalCasosACVictimaViolencia = reporteEjecutivoService.totalCasosACVictimaViolencia();
        totalCasosACVictimaViolenciaMes = reporteEjecutivoService.totalCasosACVictimaViolenciaMes(filtroReporte);
        
        totalCasosACAccionesProtestaMes = reporteEjecutivoService.totalCasosACAccionesProtestaMes(filtroReporte);
        
        totalCasosAD = reporteEjecutivoService.totalCasosAD();
        totalCasosADMes = reporteEjecutivoService.totalCasosADMes(filtroReporte);
        totalActividadAccionesDefensaLegalMes = reporteEjecutivoService.totalActividadAccionesDefensaLegalMes(filtroReporte);
        totalActividadAccionesHumanitariasMes = reporteEjecutivoService.totalActividadAccionesHumanitariasMes(filtroReporte);
        totalActividadIntermediacionMes = reporteEjecutivoService.totalActividadIntermediacionMes(filtroReporte);
        totalActividadSupervisionPreventivaMes = reporteEjecutivoService.totalActividadSupervisionPreventivaMes(filtroReporte);
        
        listaCasosNuevosPorMes = reporteSimcoCasoService.listaCasosNuevosPorMes(filtroReporte);
        listaCasosResueltosPorMes = reporteSimcoCasoService.listaCasosResueltosPorMes(filtroReporte);
        listaCasosActivosTotales = reporteSimcoCasoService.listaCasosActivosTotales();
        for(ReporteSimcoCaso rsc : listaCasosActivosTotales){
            rsc.setRutaReporte(retornaRutaPath().concat("/jasper/"));
            HashMap<Integer,String> map = reporteEjecutivoService.actoresPorCodigoCasoString(rsc.getCodigoCaso());
            for(Map.Entry m : map.entrySet()){
                if(Integer.parseInt(m.getKey().toString())  == 1)
                    rsc.setActorPrimario(m.getValue().toString());
                if(Integer.parseInt(m.getKey().toString())  == 2)
                    rsc.setActorSecundario(m.getValue().toString());
                if(Integer.parseInt(m.getKey().toString())  == 3)
                    rsc.setActorTerciario(m.getValue().toString());
            }
            rsc.setActividades(actividadService.actividadxCodigoCasoBuscarTotal(rsc.getCodigoCaso()));
        }
        
        totalActivosLatentes = reporteEjecutivoService.totalCasosActivosLatentes(filtroReporte);
        totalLatentesObservacion = reporteEjecutivoService.totalCasosLatentesObservacion(filtroReporte);
        totalGeneralDialogo = reporteEjecutivoService.totalGeneralCasosDialogo(filtroReporte);
        totalGeneralActivo = reporteEjecutivoService.totalGeneralCasosActivo(filtroReporte);
        
        totalGeneraReunionesPreparatorias = reporteEjecutivoService.totalGeneralCasosReunionesPreparatorias(filtroReporte);
        totalGeneraParticipacionDefensoria = reporteEjecutivoService.totalGeneralCasosParticipacionDefensoria(filtroReporte);
        //totalGeneraCasosViolencia = reporteEjecutivoService.totalGeneralCasosEchoViolencia(filtroReporte);
//        totalGeneraCasosAccionesProtesta = reporteEjecutivoService.totalGeneralCasosAccionesProtesta(filtroReporte);
        totalGeneralActuacion = reporteEjecutivoService.totalGeneralCasosActuacionDefensorial(filtroReporte);
        totalGeneralActuacionSupervisionPreventiva = reporteEjecutivoService.totalGeneralCasosActuacionDefensorialSupervisionPreventiva(filtroReporte);
        totalGeneralActuacionIntermediaciones = reporteEjecutivoService.totalGeneralCasosActuacionDefensorialIntermediaciones(filtroReporte);
        totalGeneralActuacionAccionHumanitaria = reporteEjecutivoService.totalGeneralCasosActuacionDefensorialAccionHumanitaria(filtroReporte);
        totalGeneralActuacionDefensaLegal = reporteEjecutivoService.totalGeneralCasosActuacionDefensorialDefensaLegal(filtroReporte);
        
        /**********SET**************/
        ejecutivo.setRutaReporte(retornaRutaPath().concat("/jasper/"));
        ejecutivo.setTitulo1("ESTADO DE LOS CONFLICTOS SOCIALES: "+MesesEnum.get(filtroReporte.getMes()).getValue()+" - 20"+filtroReporte.getAnhos());
        ejecutivo.setTotalCasosRegistrados(totalCasosRegistrados);
        ejecutivo.setTotalCasosActivos(totalCasosActivos);
        ejecutivo.setTotalCasosLatentes(totalCasosLatentes);
        ejecutivo.setTotalCasosActivosLatentes(totalActivosLatentes);
        ejecutivo.setPorcentajeTotalCasosActivos(cambiarPorcentaje(totalCasosRegistrados, totalCasosActivos));
        ejecutivo.setPorcentajeTotalCasosLatentes(cambiarPorcentaje(totalCasosRegistrados, totalCasosLatentes));
        ejecutivo.setTotalCasosRegistradosMes(totalCasosRegistradosMes);
        ejecutivo.setTotalCasosDeLatenteAActivoMes(totalCasosDeLatenteAActivoMes);
        ejecutivo.setTotalCasosDeActivosALatenteMes(totalCasosDeActivosALatenteMes);
        ejecutivo.setTotalCasosDeRetiradoAActivoMes(totalCasosDeRetiradoAActivoMes);
        ejecutivo.setTotalCasosResueltoMes(totalCasosResueltoMes);
        ejecutivo.setTotalCasosDeLatenteARetiradoMes(totalCasosDeLatenteARetiradoMes);
        ejecutivo.setTotalCasosFaseDialogo(totalCasosFaseDialogo);
        ejecutivo.setTotalCasosDialogoNegociacion(totalCasosDialogoNegociacion);
        ejecutivo.setTotalCasosDialogoReuniones(totalCasosDialogoReuniones);
        ejecutivo.setTotalCasosDialogoEspacioDialogo(totalCasosDialogoEspacioDialogo);
        ejecutivo.setPorcentajeCasosFaseDialogo("("+cambiarPorcentaje(totalCasosActivos,totalCasosFaseDialogo)+" de los casos activos)");
        ejecutivo.setPorcentajeCasosFaseDialogoNegociacion("("+cambiarPorcentaje(totalCasosFaseDialogo,totalCasosDialogoNegociacion)+")");
        ejecutivo.setPorcentajeCasosFaseDialogoReuniones("("+cambiarPorcentaje(totalCasosFaseDialogo,totalCasosDialogoReuniones)+")");
        ejecutivo.setPorcentajeCasosFaseDialogoEspacionDialogo("("+cambiarPorcentaje(totalCasosFaseDialogo,totalCasosDialogoEspacioDialogo)+")");
        ejecutivo.setTotalCasosACVictimaViolencia(totalCasosACVictimaViolencia);
        ejecutivo.setTotalCasosACVictimaViolenciaMes(totalCasosACVictimaViolenciaMes);
        ejecutivo.setPorcentajeCasosACVictimaViolencia("("+cambiarPorcentaje(totalCasosActivos,totalCasosACVictimaViolencia)+")");
        ejecutivo.setPorcentajeCasosACVictimaViolenciaMes(" ("+cambiarPorcentaje(totalCasosActivos,totalCasosACVictimaViolenciaMes)+")");
        ejecutivo.setTotalCasosACAccionesProtestaMes(totalCasosACAccionesProtestaMes);
        ejecutivo.setTotalCasosAD(totalCasosAD);
        ejecutivo.setTotalCasosADMes(totalCasosADMes);
        ejecutivo.setTotalActividadAccionesDefensaLegalMes(totalActividadAccionesDefensaLegalMes);
        ejecutivo.setTotalActividadAccionesHumanitariasMes(totalActividadAccionesHumanitariasMes);
        ejecutivo.setTotalActividadIntermediacionMes(totalActividadIntermediacionMes);
        ejecutivo.setTotalActividadSupervisionPreventivaMes(totalActividadSupervisionPreventivaMes);
        
        
        Integer numeroMes = Integer.parseInt(filtroReporte.getMes());
        String anhoe = filtroReporte.getAnhos();
        List<CuadroGenericoMes> listaCasosRegistradosMES = new ArrayList<>();
        List<CuadroGenericoMes> listaCasosResueltosMES = new ArrayList<>();
        List<ChartTotal> registradoTotals;
        CuadroGenericoMes cma = new CuadroGenericoMes();
        CuadroGenericoMes cma1 = new CuadroGenericoMes();
        CuadroGenericoMes cmaCasosRegistradosPorMes = new CuadroGenericoMes();
        CuadroGenericoMes cmaCasosResueltosPorMes = new CuadroGenericoMes();
        /*NUMERO DE CASOS POR MES*/
        registradoTotals = new ArrayList<>();
        for(int i = 0; i< 13; i++){
            ChartTotal ct = new ChartTotal();
            
            if(numeroMes ==  0){
                Integer anho = Integer.parseInt(filtroReporte.getAnhos()) - 1;
                anhoe = anho.toString();
                numeroMes--;
                numeroMes = 12;
            }
            String mes = String.format("%2s",numeroMes).replace(' ', '0');
            FiltroReporte fr = new FiltroReporte();
            fr.setMes(mes);
            fr.setAnhos(anhoe);
            ct.setNombre(MesType.get(mes).getValue()+"-"+anhoe);
            Integer cantid = reporteEjecutivoService.totalCasosRegistradosMes(fr);
            Integer cantid1 = reporteEjecutivoService.totalCasosResueltoMes(fr);
            ct.setValor(cantid);
            registradoTotals.add(ct);
            if(i == 0){
                cma1.setMes0(MesType.get(mes).getValue());
                cma.setMes0("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes0(cantid.toString());
                cmaCasosResueltosPorMes.setMes0(cantid1.toString());
            }
            if(i == 1){
                cma1.setMes1(MesType.get(mes).getValue());
                cma.setMes1("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes1(cantid.toString());
                cmaCasosResueltosPorMes.setMes1(cantid1.toString());
            }
            if(i == 2){
                cma1.setMes2(MesType.get(mes).getValue());
                cma.setMes2("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes2(cantid.toString());
                cmaCasosResueltosPorMes.setMes2(cantid1.toString());
            }
            if(i == 3){
                cma1.setMes3(MesType.get(mes).getValue());
                cma.setMes3("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes3(cantid.toString());
                cmaCasosResueltosPorMes.setMes3(cantid1.toString());
            }
            if(i == 4){
                cma1.setMes4(MesType.get(mes).getValue());
                cma.setMes4("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes4(cantid.toString());
                cmaCasosResueltosPorMes.setMes4(cantid1.toString());
            }
            if(i == 5){
                cma1.setMes5(MesType.get(mes).getValue());
                cma.setMes5("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes5(cantid.toString());
                cmaCasosResueltosPorMes.setMes5(cantid1.toString());
            }
            if(i == 6){
                cma1.setMes6(MesType.get(mes).getValue());
                cma.setMes6("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes6(cantid.toString());
                cmaCasosResueltosPorMes.setMes6(cantid1.toString());
            }
            if(i == 7){
                cma1.setMes7(MesType.get(mes).getValue());
                cma.setMes7("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes7(cantid.toString());
                cmaCasosResueltosPorMes.setMes7(cantid1.toString());
            }
            if(i == 8){
                cma1.setMes8(MesType.get(mes).getValue());
                cma.setMes8("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes8(cantid.toString());
                cmaCasosResueltosPorMes.setMes8(cantid1.toString());
            }
            if(i == 9){
                cma1.setMes9(MesType.get(mes).getValue());
                cma.setMes9("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes9(cantid.toString());
                cmaCasosResueltosPorMes.setMes9(cantid1.toString());
            }
            if(i == 10){
                cma1.setMes10(MesType.get(mes).getValue());
                cma.setMes10("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes10(cantid.toString());
                cmaCasosResueltosPorMes.setMes10(cantid1.toString());
            }
            if(i == 11){
                cma1.setMes11(MesType.get(mes).getValue());
                cma.setMes11("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes11(cantid.toString());
                cmaCasosResueltosPorMes.setMes11(cantid1.toString());
            }
            if(i == 12){
                cma1.setMes12(MesType.get(mes).getValue());
                cma.setMes12("20"+anhoe);
                cmaCasosRegistradosPorMes.setMes12(cantid.toString());
                cmaCasosResueltosPorMes.setMes12(cantid1.toString());
            }
            numeroMes--; 
        }
        listaCasosRegistradosMES.add(cma);
        listaCasosRegistradosMES.add(cma1);
        listaCasosRegistradosMES.add(cmaCasosRegistradosPorMes);
            
        listaCasosResueltosMES.add(cma);
        listaCasosResueltosMES.add(cma1);
        listaCasosResueltosMES.add(cmaCasosResueltosPorMes);
        
        
        List<ChartTotal> registradoTotals1 = new ArrayList<>();
        for(int i = 12; i >= 0; i--){
            registradoTotals1.add(registradoTotals.get(i));
        }
        
        
        /*NUMERO DE CASOS RESUELTOS POR MES*/
        
        
            
        ejecutivo.setRegistradoTotals(registradoTotals1);
        ejecutivo.setListaCasosRegistradosMES(listaCasosRegistradosMES);
        ejecutivo.setListaCasosNuevosPorMes(listaCasosNuevosPorMes);
        ejecutivo.setListaCasosResueltosMES(listaCasosResueltosMES);
        ejecutivo.setListaCasosActivosTotales(listaCasosActivosTotales);
        ejecutivo.setListaCasosResueltosPorMes(listaCasosResueltosPorMes);
        
        
        ejecutivo.setTotalGeneralActuacionDefensorial(totalGeneralActuacion);
        ejecutivo.setPorcentajeActuacionDefensorial(cambiarPorcentaje(totalCasosRegistrados,totalGeneralActuacion, "DEL TOTAL DE CASOS"));
        ejecutivo.setTotalGeneralActuacionDefensorialSupervisionPreventiva(totalGeneralActuacionSupervisionPreventiva);
        ejecutivo.setTotalGeneralActuacionDefensorialIntermediaciones(totalGeneralActuacionIntermediaciones);
        ejecutivo.setTotalGeneralActuacionDefensorialAccionHumanitaria(totalGeneralActuacionAccionHumanitaria);
        ejecutivo.setTotalGeneralActuacionDefensorialDefensaLegal(totalGeneralActuacionDefensaLegal);
//        ejecutivo.setAccionesProtestaTexto1(totalGeneraCasosAccionesProtesta+" ACCIONES DE PROTESTA COLECTIVA DURANTE EL MES "+cambiarPorcentaje(totalCasosMes,totalGeneraCasosAccionesProtesta, "RELACIONADOS CON CONFLICTOS"));
        //ejecutivo.setHechoViolenciaTexto1(totalGeneraCasosViolencia+" CASOS PRESENTARON AL MENOS UN HECHO DE VIOLENCIA DESDE QUE INICIARON "+cambiarPorcentaje(totalCasos,totalGeneraCasosViolencia , "DEL TOTAL DE CASOS"));
        ejecutivo.setProcedoDialogoTexto1(totalGeneraReunionesPreparatorias+" CASOS SE ENCUENTRAN EN REUNIONES PREPARATORIAS PARA EL DIÁLOGO "+cambiarPorcentaje(totalGeneralDialogo,totalGeneraReunionesPreparatorias , "DE LOS CASOS EN DIALOGO")); 
        ejecutivo.setProcedoDialogoTexto2(totalGeneraParticipacionDefensoria+" CASOS TUVIERON PRESENCIA DE LA DEFENSORÍA EN LOS ESPACIOS DE DIÁLOGO  "+cambiarPorcentaje(totalGeneralDialogo,totalGeneraParticipacionDefensoria , "DE LOS CASOS EN DIALOGO")); 
        
        
        ejecutivo.setTotalCasosGeneralDiagolo(totalGeneralDialogo);
        ejecutivo.setPorcentajeGeneralDiagolo(cambiarPorcentaje(totalGeneralActivo,totalGeneralDialogo , "DE LOS CASOS ACTIVOS"));
        ejecutivo.setTotalCasosActivoMes(totalCasosActivos);
        
        ejecutivo.setTotalCasosLatenteMes(totalCasosLatentes);
        ejecutivo.setMesPublicacion(MesesEnum.get(filtroReporte.getMes()).getValue());
        ejecutivo.setAnhoPublicacion("20"+filtroReporte.getAnhos());
        ejecutivo.setTotalCasosActivosLatentes(totalActivosLatentes);
        ejecutivo.setTotalCasosLatentesObservacion(totalLatentesObservacion);
        
        Integer totalCasosGeneralEscalamiento = reporteEjecutivoService.totalGeneralCasosFaceEscalamiento(filtroReporte);
        String porcentajeGeneralEscalamiento = cambiarPorcentaje(totalCasosRegistrados,totalCasosGeneralEscalamiento);
        Integer totalCasosGeneralTemprana  = reporteEjecutivoService.totalGeneralCasosFaceTemprana(filtroReporte);
        String porcentajeGeneralTemprana = cambiarPorcentaje(totalCasosRegistrados,totalCasosGeneralTemprana);
        Integer totalCasosGeneralDesescalamiento  = reporteEjecutivoService.totalGeneralCasosFaceDesescalamiento(filtroReporte);
        String porcentajeGeneralDesescalamiento = cambiarPorcentaje(totalCasosRegistrados,totalCasosGeneralDesescalamiento);
        Integer totalCasosGeneralCrisis = reporteEjecutivoService.totalGeneralCasosFaceCrisis(filtroReporte);
        String porcentajeGeneralCrisis = cambiarPorcentaje(totalCasosRegistrados,totalCasosGeneralCrisis);
        
        StringBuilder sb = new StringBuilder();
        sb.append("En el presente mes se registraron "+totalCasosRegistradosMes+" conflictos: "+ejecutivo.getTotalCasosActivoMes()+" casos activos y "+ejecutivo.getTotalCasosLatenteMes()+" latentes (ver Gráfico N° 1). Del");
        sb.append("total de casos activos, "+ejecutivo.getTotalCasosGeneralDiagolo()+" se encuentran en fase de diálogo lo que representa el "+cambiarPorcentaje(totalCasosRegistrados,ejecutivo.getTotalCasosGeneralDiagolo())+" del total de los ");
        sb.append("casos registrados (ver Gráfico N° 2), "+totalCasosGeneralEscalamiento+" casos están en fase de escalamiento ("+porcentajeGeneralEscalamiento+" de los casos), "+totalCasosGeneralDesescalamiento+"  ");
        sb.append("en fase desescalamiento ("+porcentajeGeneralDesescalamiento+" de los casos) y "+totalCasosGeneralTemprana+" en fase temprana ("+porcentajeGeneralTemprana+" de los casos). Cabe ");
        sb.append("señalar que hay "+totalCasosGeneralCrisis+" casos registrados en fase de crisis ("+porcentajeGeneralCrisis+" de los casos) (ver Anexo III). ");
        ejecutivo.setEstadoConflictoTexto(sb.toString());
        
        List<EstadoConflicto> ecs = new ArrayList<>();
        Integer filtroMes = Integer.parseInt(filtroReporte.getMes()); 
        Integer filtroAnho = Integer.parseInt(filtroReporte.getAnhos()); 
        
        for(int i = 0; i< 13; i++){
            String filtroMesString = String.format("%2s",filtroMes).replace(' ', '0');
            String filtroAnhoString = String.format("%2s",filtroAnho).replace(' ', '0');
            FiltroReporte fr = new FiltroReporte();
            fr.setAnhos(filtroAnhoString);
            fr.setMes(filtroMesString);
            Integer latentes = reporteEjecutivoService.totalCasosLatentes();
            Integer activos = reporteEjecutivoService.totalCasosActivos();
            Integer total = reporteEjecutivoService.totalCasosRegistrados();
            
            filtroMes--;
            if(filtroMes ==  0){
                filtroAnho--;
                filtroMes = 12;
            }
            EstadoConflicto ec = new EstadoConflicto(MesesEnum.get(filtroMesString).getValue(), activos, latentes, total, "20"+filtroAnhoString);
            ecs.add(ec);
        }
        ejecutivo.setEstadoConflictos(ecs);
        
        Integer totalResueltos = reporteEjecutivoService.totalCasosResueltos(filtroReporte);
        ejecutivo.setTotalCasosResueltoMes(totalResueltos);
        Integer totalPropuestos = reporteEjecutivoService.totalCasosPropuestos(filtroReporte);
        String totalResueltosString = "";
        if(totalResueltos > 0){
            totalResueltosString = reporteEjecutivoService.cadenaNombreCasosResueltos(filtroReporte);
        }
        //String totalPropuestosString = reporteEjecutivoService.cadenaNombreCasosResueltos(filtroReporte);
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Durante el mes se resolvieron "+totalResueltos+" casos y se incorporó "+totalPropuestos+" (ver Anexo I y Anexo II). Los casos resueltos ");
        sb1.append("son: "+totalResueltosString);
        //sb1.append("Los casos resueltos son: "+totalPropuestosString+"\n\n");
        
        List<NuevoCaso> ncs = new ArrayList<>();
        Integer filtroMes2 = Integer.parseInt(filtroReporte.getMes()); 
        Integer filtroAnho2 = Integer.parseInt(filtroReporte.getAnhos());
        Integer sumaResuelto = 0;
        Integer sumaNuevo = 0;
        for(int i = 0; i< 13; i++){
            String filtroMesString = String.format("%2s",filtroMes2).replace(' ', '0');
            String filtroAnhoString = String.format("%2s",filtroAnho2).replace(' ', '0');
            FiltroReporte fr = new FiltroReporte();
            fr.setAnhos(filtroAnhoString);
            fr.setMes(filtroMesString);
            Integer resue = reporteEjecutivoService.totalCasosResueltos(fr);
            Integer nuevo = reporteEjecutivoService.totalCasosPropuestos(fr);
            sumaResuelto = sumaResuelto + resue;
            sumaNuevo = sumaNuevo + nuevo;
            filtroMes2--;
            if(filtroMes2 ==  0){
                filtroAnho2--;
                filtroMes2 = 12;
            }
            NuevoCaso nc = new NuevoCaso(MesesEnum.get(filtroMesString).getValue(), resue, nuevo, "20"+filtroAnhoString);
            ncs.add(nc);
        }
        sb1.append("Desde "+MesesEnum.get(filtroReporte.getMes()).getValue()+" del 20"+(Integer.parseInt(filtroReporte.getAnhos())-1)+" hasta "+MesesEnum.get(filtroReporte.getMes()).getValue()+" del 20"+(Integer.parseInt(filtroReporte.getAnhos()))+" se han resuelto "+sumaResuelto+" casos y se han incorporado "+sumaNuevo+" (ver Gráfico N° 3).");
        ejecutivo.setCasosNuevosTexto(sb1.toString());
        
        /*grafic 004 005**/
        List<ElementoResumenEjecutivo> ejecutivos1 = reporteEjecutivoService.totalMensualCasosRegistrados(filtroReporte);
        List<ElementoResumenEjecutivo> ejecutivos2 = reporteEjecutivoService.totalMensualCasosActivos(filtroReporte);
        List<ElementoNombreValor> elementoNombreValors1 = new ArrayList<>();
        List<ElementoNombreValor> elementoNombreValors2 = new ArrayList<>();
        for(ElementoResumenEjecutivo ere1:ejecutivos1){
            ElementoNombreValor env = new ElementoNombreValor(ere1.getNombre(), ere1.getValor());
            elementoNombreValors1.add(env);
        }
        for(ElementoResumenEjecutivo ere1:ejecutivos2){
            ElementoNombreValor env = new ElementoNombreValor(ere1.getNombre(), ere1.getValor());
            elementoNombreValors2.add(env);
        }
        /*grafic 004 005**/
        
        /*grafic 006**/
        filtroReporte.setTipoTipologiaCaso("110");
        filtroReporte.setTipoReporte(1);
        List<ElementoResumenEjecutivo> nivelesParametros = reporteEjecutivoService.totalMensualSegunTipologiaCaso(filtroReporte);
        
        int gobNacionalSum = 0;
        int gobRegionalSum = 0;
        int gobLocalSum = 0;
        int poderLegislativoSum = 0;
        int porderJudicialSum = 0;
        int otrosOrganismosSum = 0;
        int totalGob= 0;
        
        for(ElementoResumenEjecutivo ere : nivelesParametros){
            if(StringUtils.equals(ere.getValorParametro(), "01")){
                gobNacionalSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "02")){
                gobRegionalSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "03")){
                gobLocalSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "04")){
                poderLegislativoSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "05")){
                porderJudicialSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "06")){
                porderJudicialSum++;
            }
        }
        totalGob = gobNacionalSum+gobRegionalSum+gobLocalSum+poderLegislativoSum +porderJudicialSum+otrosOrganismosSum;
        List<ElementoNombreValor> listNivelGob = new ArrayList<>();
        List<ElementoNombreValor> listNivelGobGrafic = new ArrayList<>();
        ElementoNombreValor env1 = new ElementoNombreValor();
        env1.setNombre("Total");
        env1.setValor(totalGob);
        env1.setPorcentaje("100.0%");
        listNivelGob.add(env1);
        
        for(ElementoResumenEjecutivo ere : nivelesParametros){
            ElementoNombreValor env = new ElementoNombreValor();
            env.setNombre(ere.getNombre());
            env.setValor(ere.getValor());
            env.setPorcentaje(cambiarPorcentaje(totalGob, ere.getValor()));
            listNivelGob.add(env);
            listNivelGobGrafic.add(env);
        }
        
        
        ejecutivo.setListaNivelGobierno(listNivelGob);
        ejecutivo.setListaNivelGobiernoGrafico(listNivelGobGrafic);
        /*grafic 006**/
        
        
        /*grafic 007**/
        filtroReporte.setTipoTipologiaCaso("90");
        filtroReporte.setTipoReporte(2);
        List<ElementoResumenEjecutivo> nivelesParametros1 = reporteEjecutivoService.totalMensualSegunTipologiaCaso(filtroReporte);
        
        int asuntoGobiernoLocalSum = 0;
        int asuntoGobiernoNacionalSum = 0;
        int asuntoGobiernoRegionalSum = 0;
        int comunalSum = 0;
        int cultivoIlegalSum = 0;
        int demarcacionTerritorialSum = 0;
        int electoralSum= 0;
        int laboralSum= 0;
        int socioambientalSum= 0;
        int otrosSum= 0;
        int totalTipo= 0;
        
        for(ElementoResumenEjecutivo ere : nivelesParametros1){
            if(StringUtils.equals(ere.getValorParametro(), "01")){
                asuntoGobiernoLocalSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "02")){
                asuntoGobiernoNacionalSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "03")){
                asuntoGobiernoRegionalSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "04")){
                comunalSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "05")){
                cultivoIlegalSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "06")){
                demarcacionTerritorialSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "07")){
                electoralSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "08")){
                laboralSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "09")){
                socioambientalSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "10")){
                otrosSum++;
            }
        }
        totalTipo = asuntoGobiernoLocalSum+asuntoGobiernoNacionalSum+asuntoGobiernoRegionalSum+comunalSum+cultivoIlegalSum+demarcacionTerritorialSum+electoralSum+laboralSum+socioambientalSum+otrosSum;
        List<ElementoNombreValor> listNivelTipo = new ArrayList<>();
        List<ElementoNombreValor> listNivelTipoGrafic = new ArrayList<>();
        ElementoNombreValor env2 = new ElementoNombreValor();
        env2.setNombre("Total");
        env2.setValor(totalTipo);
        env2.setPorcentaje("100.0%");
        listNivelTipo.add(env2);
        
        for(ElementoResumenEjecutivo ere : nivelesParametros1){
            ElementoNombreValor env = new ElementoNombreValor();
            env.setNombre(ere.getNombre());
            env.setValor(ere.getValor());
            env.setPorcentaje(cambiarPorcentaje(totalTipo, ere.getValor()));
            listNivelTipo.add(env);
            listNivelTipoGrafic.add(env);
        }
        
        
        ejecutivo.setListaNivelTipo(listNivelTipo);
        ejecutivo.setListaNivelTipoGrafico(listNivelTipoGrafic);
        /*grafic 007**/
        
        
        
        /*grafic 008**/
        filtroReporte.setTipoTipologiaCaso("130");
        filtroReporte.setTipoReporte(3);
        List<ElementoResumenEjecutivo> nivelesParametros2 = reporteEjecutivoService.totalMensualSegunTipologiaCaso(filtroReporte);

        int mineriaSum = 0;
        int hidrocarburosSum = 0;
        int energiaSum = 0;
        int forestalesSum = 0;
        int residuosSolidosSum = 0;
        int aguasResidualesDomesticasSum = 0;
        int agroindustrialSum = 0;
        int otroSum = 0;
        int totalSubTipo= 0;
        
        for(ElementoResumenEjecutivo ere : nivelesParametros2){
            if(StringUtils.equals(ere.getValorParametro(), "01")){
                mineriaSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "02")){
                hidrocarburosSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "03")){
                energiaSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "04")){
                forestalesSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "05")){
                residuosSolidosSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "06")){
                aguasResidualesDomesticasSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "07")){
                agroindustrialSum++;
            }
            if(StringUtils.equals(ere.getValorParametro(), "08")){
                otroSum++;
            }
        }
        
        totalSubTipo = mineriaSum+hidrocarburosSum+energiaSum+forestalesSum+residuosSolidosSum+aguasResidualesDomesticasSum+agroindustrialSum+otroSum;
        List<ElementoNombreValor> listNivelSubTipo = new ArrayList<>();
        List<ElementoNombreValor> listNivelSubTipoGrafic = new ArrayList<>();
        ElementoNombreValor env3 = new ElementoNombreValor();
        env3.setNombre("Total");
        env3.setValor(totalTipo);
        env3.setPorcentaje("100.0%");
        listNivelSubTipo.add(env3);
        
        for(ElementoResumenEjecutivo ere : nivelesParametros1){
            ElementoNombreValor env = new ElementoNombreValor();
            env.setNombre(ere.getNombre());
            env.setValor(ere.getValor());
            env.setPorcentaje(cambiarPorcentaje(totalSubTipo, ere.getValor()));
            listNivelSubTipo.add(env);
            listNivelSubTipoGrafic.add(env);
        }
        
        ejecutivo.setListaNivelSubTipo(listNivelSubTipo);
        ejecutivo.setListaNivelSubTipoGrafico(listNivelSubTipoGrafic);
        /*grafic 008**/
        
        
        
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Así, en el mes de "+MesesEnum.get(filtroReporte.getMes()).getValue()+" de 20"+filtroReporte.getAnhos()+", la principal competencia ");
        sb2.append("(entre varias que coexisten) en la atención de los conflictos sociales registrados recae en el Gobierno Nacional, con "+gobNacionalSum+" casos ("+cambiarPorcentaje(totalGob, gobNacionalSum)+"), ");
        sb2.append("los Gobiernos Regionales con "+gobRegionalSum+" casos ("+cambiarPorcentaje(totalGob, gobRegionalSum)+"); los Gobiernos Locales con "+gobLocalSum+" casos ("+cambiarPorcentaje(totalGob, gobLocalSum)+"), a continuación el cuadro por competencias:");
        ejecutivo.setNivelGobiernoTexto(sb2.toString());
        /*grafic 006**/
        ejecutivo.setListaMensualCasosTotales(elementoNombreValors1);
        ejecutivo.setListaMensualCasosActivos(elementoNombreValors2);
        
        
        /**grafic 007*/
        ejecutivo.setElementoReportesAutoridad(generarReporteAutoridadReport());
        /**grafic 007*/
        /**grafic 008*/
        ejecutivo.setElementoReportesDepartamentoEstado(generarReporteEstadoRegion());
        
        
            
            
        for(ElementoReporte er : ejecutivo.getElementoReportesDepartamentoEstado()){
            if(StringUtils.equals("AMAZONAS", er.getTipoConflicto())){
                ejecutivo.setAmazonasAct(er.getActivo());
                ejecutivo.setAmazonasLat(er.getLatente()) ;
                ejecutivo.setAmazonasNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("ANCASH", er.getTipoConflicto())){
                ejecutivo.setAncashAct(er.getActivo());
                ejecutivo.setAncashLat(er.getLatente());
                ejecutivo.setAncashNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("APURIMAC", er.getTipoConflicto())){
                ejecutivo.setApurimacAct(er.getActivo());
                ejecutivo.setApurimacLat(er.getLatente());
                ejecutivo.setApurimacNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("AREQUIPA", er.getTipoConflicto())){
                ejecutivo.setArequipaAct(er.getActivo());
                ejecutivo.setArequipaLat(er.getLatente());
                ejecutivo.setArequipaNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("AYACUCHO", er.getTipoConflicto())){
                ejecutivo.setAyacuchoAct(er.getActivo());
                ejecutivo.setAyacuchoLat(er.getLatente());
                ejecutivo.setAyacuchoNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("CAJAMARCA", er.getTipoConflicto())){
                ejecutivo.setCajamarcaAct(er.getActivo());
                ejecutivo.setCajamarcaLat(er.getLatente());
                ejecutivo.setCajamarcaNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("CUSCO", er.getTipoConflicto())){
                ejecutivo.setCuscoAct(er.getActivo());
                ejecutivo.setCuscoLat(er.getLatente());
                ejecutivo.setCuscoNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("HUANCAVELICA", er.getTipoConflicto())){
                ejecutivo.setHuancavelicaAct(er.getActivo());
                ejecutivo.setHuancavelicaLat(er.getLatente());
                ejecutivo.setHuancavelicaNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("HUANUCO", er.getTipoConflicto())){
                ejecutivo.setHuanucoAct(er.getActivo());
                ejecutivo.setHuanucoLat(er.getLatente());
                ejecutivo.setHuanucoNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("ICA", er.getTipoConflicto())){
                ejecutivo.setIcaAct(er.getActivo());
                ejecutivo.setIcaLat(er.getLatente());
                ejecutivo.setIcaNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("JUNIN", er.getTipoConflicto())){
                ejecutivo.setJuninAct(er.getActivo());
                ejecutivo.setJuninLat(er.getLatente());
                ejecutivo.setJuninNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("LA LIBERTAD", er.getTipoConflicto())){
                ejecutivo.setLaLibertadAct(er.getActivo());
                ejecutivo.setLaLibertadLat(er.getLatente());
                ejecutivo.setLaLibertadNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("LAMBAYEQUE", er.getTipoConflicto())){
                ejecutivo.setLambayequeAct(er.getActivo());
                ejecutivo.setLambayequeLat(er.getLatente());
                ejecutivo.setLambayequeNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("LIMA", er.getTipoConflicto())){
                ejecutivo.setLimaAct(er.getActivo());
                ejecutivo.setLimaLat(er.getLatente());
                ejecutivo.setLimaNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("LORETO", er.getTipoConflicto())){
                ejecutivo.setLoretoAct(er.getActivo());
                ejecutivo.setLoretoLat(er.getLatente());
                ejecutivo.setLoretoNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("MADRE DE DIOS", er.getTipoConflicto())){
                ejecutivo.setMadreDeDiosAct(er.getActivo());
                ejecutivo.setMadreDeDiosLat(er.getLatente());
                ejecutivo.setMadreDeDiosNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("MOQUEGUA", er.getTipoConflicto())){
                ejecutivo.setMoqueguaAct(er.getActivo());
                ejecutivo.setMoqueguaLat(er.getLatente());
                ejecutivo.setMoqueguaNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("PASCO", er.getTipoConflicto())){
                ejecutivo.setPascoAct(er.getActivo());
                ejecutivo.setPascoLat(er.getLatente());
                ejecutivo.setPascoNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("PIURA", er.getTipoConflicto())){
                ejecutivo.setPiuraAct(er.getActivo());
                ejecutivo.setPiuraLat(er.getLatente());
                ejecutivo.setPiuraNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("PUNO", er.getTipoConflicto())){
                ejecutivo.setPunoAct(er.getActivo());
                ejecutivo.setPunoLat(er.getLatente());
                ejecutivo.setPunoNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("SAN MARTIN", er.getTipoConflicto())){
                ejecutivo.setSanMartinAct(er.getActivo());
                ejecutivo.setSanMartinLat(er.getLatente());
                ejecutivo.setSanMartinNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("TACNA", er.getTipoConflicto())){
                ejecutivo.setTacnaAct(er.getActivo());
                ejecutivo.setTacnaLat(er.getLatente());
                ejecutivo.setTacnaNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("TUMBES", er.getTipoConflicto())){
                ejecutivo.setTumbesAct(er.getActivo());
                ejecutivo.setTumbesLat(er.getLatente());
                ejecutivo.setTumbesNom(er.getTipoConflicto());
            }
            if(StringUtils.equals("CALLAO", er.getTipoConflicto())){
                ejecutivo.setCallaoAct(er.getActivo());
                ejecutivo.setCallaoLat(er.getLatente());
                ejecutivo.setCallaoNom(er.getTipoConflicto());
            }
            
            if(StringUtils.equals("UCAYALI", er.getTipoConflicto())){
                ejecutivo.setUcayaliAct(er.getActivo());
                ejecutivo.setUcayaliLat(er.getLatente());
                ejecutivo.setUcayaliNom(er.getTipoConflicto());
            }
        }
        
        /**grafic 008*/
        ejecutivo.setNuevoCasos(ncs);
        
        
        
        List<ChartTotal> fts = new ArrayList<>();
        
        fts.add(new ChartTotal("Temprana", totalCasosGeneralTemprana));
        fts.add(new ChartTotal("Escalamiento", totalCasosGeneralEscalamiento));
        fts.add(new ChartTotal("Crisis", totalCasosGeneralCrisis));
        fts.add(new ChartTotal("Desescalamiento", totalCasosGeneralDesescalamiento));
        fts.add(new ChartTotal("Dialogo", totalGeneralDialogo));
        ejecutivo.setFaceTotals(fts);
        ejecutivo.setImagePath001(retornaRutaPath().concat("/images/"));
        listaResumenEjecutivo.add(ejecutivo);

        
        generarReportePublicoMensual();
    }
    
    public List<ElementoReporte> generarReporteEstadoRegion() {
        FiltroReporte fr = new FiltroReporte();
        fr.setClasificacion("0");
        List<Caso> listaCasosReporte1 = reporteGeneralService.reporteCaso(fr);
        List<Catalogo> catalogos = catalogoService.parametroPorPadre(290);
        elementoReportes = new ArrayList<>();
        ElementoReporte er1 = new ElementoReporte();
        er1.setTipoConflicto("Total");
        int borradorSum = 0;
        int propuestaSum = 0;
        int observacionSum = 0;
        int activoSum = 0;
        int latenteSum = 0;
        int resueltoSum = 0;
        for (Catalogo cata : catalogos) {
            List<Caso> listaCasosFl = null;
            
                listaCasosFl = filtrarCasosPorRegion(listaCasosReporte1, cata.getValorParametro());
            
            int borrador = 0;
            int propuesta = 0;
            int observacion = 0;
            int activo = 0;
            int latente = 0;
            int resuelto = 0;
            for (Caso cs : listaCasosFl) {
                if (StringUtils.equals(cs.getTipoEstado(), "01")) {
                    borrador++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "02")) {
                    propuesta++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "03")) {
                    observacion++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "04")) {
                    activo++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "05")) {
                    latente++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "06")) {
                    resuelto++;
                }
            }
            borradorSum = borradorSum + borrador;
            propuestaSum = propuestaSum + propuesta;
            observacionSum = observacionSum + observacion;
            activoSum = activoSum + activo;
            latenteSum = latenteSum + latente;
            resueltoSum = resueltoSum + resuelto;
            ElementoReporte er = new ElementoReporte(cata.getNombreParametro(), null, null, borrador, propuesta, observacion, activo, latente, resuelto, null);

            elementoReportes.add(er);
        }
        er1.setBorrador(borradorSum);
        er1.setPropuesta(propuestaSum);
        er1.setObservacion(observacionSum);
        er1.setActivo(activoSum);
        er1.setLatente(latenteSum);
        er1.setResuelto(resueltoSum);
        er1.setTotal(borradorSum + propuestaSum + observacionSum + activoSum + latenteSum + latenteSum);
        elementoReportes.add(0, er1);

        for (ElementoReporte ers : elementoReportes) {
            Integer tot = ers.getBorrador() + ers.getPropuesta() + ers.getObservacion() + ers.getActivo() + ers.getLatente() + ers.getResuelto();
            Double tota2 = tot * 1.0;
            double porcent = tota2 * 100 / er1.getTotal();
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = " - ";
            } else {
                porcentaj = Double.toString(porcent) + "%";
            }
            ers.setTotal(tot);
            ers.setPorcentaje(porcentaj);
        }
        return elementoReportes;
    }
    
    private List<ElementoReporte> generarReporteAutoridadReport() {
        List<Caso> listaCasosReporte1 = null;
        FiltroReporte fr = new FiltroReporte();
        fr.setClasificacion("0");
        listaCasosReporte1 = reporteGeneralService.reporteCaso(fr);
        List<Catalogo> catalogos = null;
        
            catalogos = catalogoService.parametroPorPadre(90);
        
        elementoReportes = new ArrayList<>();
        ElementoReporte er1 = new ElementoReporte();
        er1.setTipoConflicto("Total");
        int gobNacionalSum = 0;
        int gobRegionalSum = 0;
        int gobLocalSum = 0;
        int poderLegislativoSum = 0;
        int porderJudicialSum = 0;
        int otrosOrganismosSum = 0;
        for (Catalogo cata : catalogos) {
            List<Caso> listaCasosFl = null;
            
                listaCasosFl = filtrarCasosPorTipo(listaCasosReporte1, cata.getValorParametro());
            
            int gobNacional = 0;
            int gobRegional = 0;
            int gobLocal = 0;
            int poderLegislativo = 0;
            int porderJudicial = 0;
            int otrosOrganismos = 0;
            for (Caso cs : listaCasosFl) {
                if (StringUtils.equals(cs.getTipoAsunto(), "01")) {
                    gobNacional++;
                }
                if (StringUtils.equals(cs.getTipoAsunto(), "02")) {
                    gobRegional++;
                }
                if (StringUtils.equals(cs.getTipoAsunto(), "03")) {
                    gobLocal++;
                }
                if (StringUtils.equals(cs.getTipoAsunto(), "04")) {
                    poderLegislativo++;
                }
                if (StringUtils.equals(cs.getTipoAsunto(), "05")) {
                    porderJudicial++;
                }
                if (StringUtils.equals(cs.getTipoAsunto(), "06")) {
                    otrosOrganismos++;
                }
            }
            gobNacionalSum = gobNacionalSum + gobNacional;
            gobRegionalSum = gobRegionalSum + gobRegional;
            gobLocalSum = gobLocalSum + gobLocal;
            poderLegislativoSum = poderLegislativoSum + poderLegislativo;
            porderJudicialSum = porderJudicialSum + porderJudicial;
            otrosOrganismosSum = otrosOrganismosSum + otrosOrganismos;
            ElementoReporte er = new ElementoReporte(cata.getNombreParametro(), null, null, gobNacional, gobRegional, gobLocal, poderLegislativo, porderJudicial, otrosOrganismos);
            elementoReportes.add(er);
        }
        er1.setGobNacional(gobNacionalSum);
        er1.setGobRegional(gobRegionalSum);
        er1.setGobLocal(gobLocalSum);
        er1.setPoderLegislativo(poderLegislativoSum);
        er1.setPoderJudicial(porderJudicialSum);
        er1.setOrganismoAutonomos(otrosOrganismosSum);
        er1.setTotal(gobNacionalSum + gobRegionalSum + gobLocalSum + poderLegislativoSum + porderJudicialSum + otrosOrganismosSum);
        elementoReportes.add(0, er1);
        for (ElementoReporte ers : elementoReportes) {
            Integer tot = ers.getGobLocal() + ers.getGobRegional() + ers.getGobNacional() + ers.getPoderJudicial() + ers.getPoderLegislativo() + ers.getOrganismoAutonomos();
            Double tota2 = tot * 1.0;
            double porcent = tota2 * 100 / er1.getTotal();
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = " - ";
            } else {
                porcentaj = Double.toString(porcent) + "%";
            }
            ers.setTotal(tot);
            ers.setPorcentaje(porcentaj);
        }
        return elementoReportes;
    }
    
    private String cambiarPorcentaje(int total, int valor, String texto){
        Double tota2 = valor * 1.0;
            double porcent = tota2 * 100 / total;
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = "( 0% "+texto+")";
            } else {
                porcentaj = "( "+Double.toString(porcent) + "% "+texto+")";
            }
            return porcentaj;
    }
    
    private String cambiarPorcentaje(int total, int valor){
        Double tota2 = valor * 1.0;
            double porcent = tota2 * 100 / total;
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = "0%";
            } else {
                porcentaj = Double.toString(porcent) + "%";
            }
            return porcentaj;
    }
    
    public String reportePublic() {
        filtroReporte = new FiltroReporte();
        listaAnhos = reporteService.listadoAnhoCaso();
        cargarMeses();
        return "reportePublic";
    }
    
    public void cargarReporteMensual() {
        RegistroCarga registroCarga = new RegistroCarga();
        registroCarga.setFecha(new Date());
        registroCarga.setUltimoMes("SI");
        registroCarga.setUltimoAnho("SI");
        //compararUltimoMes();
        cargarCodigoCarga(registroCarga);
        reporteService.cargaCasoMes(registroCarga.getId());
    }
    
    private void cargarCodigoCarga(RegistroCarga registrocar) {
        registroCargaService.registroCargaInsert(registrocar);
    }
    
    public void initJasperReporteEjecutivo() throws JRException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
		ec.responseReset();
		HttpServletRequest  request = (HttpServletRequest)ec.getRequest();
		String path = request.getPathTranslated();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaResumenEjecutivo);
        jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/reportePublicResumenEjecutivo.jasper"),new HashMap(), beanCollectionDataSource);
     }
    
    public void generarReportePublicoMensual() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperReporteEjecutivo();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporte.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    private void cargarMeses() {
        listasMeses = new HashMap();
        listasMeses.put("Enero", "01");
        listasMeses.put("Febrero", "02");
        listasMeses.put("Marzo", "03");
        listasMeses.put("Abril", "04");
        listasMeses.put("Mayo", "05");
        listasMeses.put("Junio", "06");
        listasMeses.put("Julio", "07");
        listasMeses.put("Agosto", "08");
        listasMeses.put("Septiembre", "09");
        listasMeses.put("Octubre", "10");
        listasMeses.put("Noviembre", "11");
        listasMeses.put("Diciembre", "12");
        listasMeses = sortByValues((HashMap) listasMeses);
    }

    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public void generaReporte() {
        listaCasosReporte = null;
        listaCasosReporte = reporteGeneralService.reporteCaso(filtroReporte);
        if (filtroReporte.getColumna().equals("01") && filtroReporte.getFila().equals("06")) {
            generarReporteFase(6);
        }
        if (filtroReporte.getColumna().equals("02") && filtroReporte.getFila().equals("06")) {
            generarReporteFase(7);
        }
        if (filtroReporte.getColumna().equals("03") && filtroReporte.getFila().equals("06")) {
            generarReporteFase(1);
        }
        if (filtroReporte.getColumna().equals("04") && filtroReporte.getFila().equals("06")) {
            generarReporteFase(2);
        }
        if (filtroReporte.getColumna().equals("05") && filtroReporte.getFila().equals("06")) {
            generarReporteFase(3);
        }
        if (filtroReporte.getColumna().equals("07") && filtroReporte.getFila().equals("06")) {
            generarReporteFase(4);
        }
        if (filtroReporte.getColumna().equals("08") && filtroReporte.getFila().equals("06")) {
            generarReporteFase(5);
        }
        if (filtroReporte.getColumna().equals("01") && filtroReporte.getFila().equals("03")) {
            generarReporteTipo(6);
        }
        if (filtroReporte.getColumna().equals("02") && filtroReporte.getFila().equals("03")) {
            generarReporteTipo(7);
        }
        if (filtroReporte.getColumna().equals("04") && filtroReporte.getFila().equals("03")) {
            generarReporteTipo(1);
        }
        if (filtroReporte.getColumna().equals("05") && filtroReporte.getFila().equals("03")) {
            generarReporteTipo(2);
        }
        if (filtroReporte.getColumna().equals("06") && filtroReporte.getFila().equals("03")) {
            generarReporteTipo(3);
        }
        if (filtroReporte.getColumna().equals("07") && filtroReporte.getFila().equals("03")) {
            generarReporteTipo(4);
        }
        if (filtroReporte.getColumna().equals("08") && filtroReporte.getFila().equals("03")) {
            generarReporteTipo(5);
        }
        if (filtroReporte.getColumna().equals("01") && filtroReporte.getFila().equals("04")) {
            generarReporteAutoridad(6);
        }
        if (filtroReporte.getColumna().equals("02") && filtroReporte.getFila().equals("04")) {
            generarReporteAutoridad(7);
        }
        if (filtroReporte.getColumna().equals("03") && filtroReporte.getFila().equals("04")) {
            generarReporteAutoridad(1);
        }
        if (filtroReporte.getColumna().equals("05") && filtroReporte.getFila().equals("04")) {
            generarReporteAutoridad(2);
        }
        if (filtroReporte.getColumna().equals("06") && filtroReporte.getFila().equals("04")) {
            generarReporteAutoridad(3);
        }
        if (filtroReporte.getColumna().equals("07") && filtroReporte.getFila().equals("04")) {
            generarReporteAutoridad(4);
        }
        if (filtroReporte.getColumna().equals("08") && filtroReporte.getFila().equals("04")) {
            generarReporteAutoridad(5);
        }
        if (filtroReporte.getColumna().equals("01") && filtroReporte.getFila().equals("07")) {
            generarReporteMecanismo(6);
        }
        if (filtroReporte.getColumna().equals("02") && filtroReporte.getFila().equals("07")) {
            generarReporteMecanismo(7);
        }
        if (filtroReporte.getColumna().equals("03") && filtroReporte.getFila().equals("07")) {
            generarReporteMecanismo(1);
        }
        if (filtroReporte.getColumna().equals("04") && filtroReporte.getFila().equals("07")) {
            generarReporteMecanismo(2);
        }
        if (filtroReporte.getColumna().equals("05") && filtroReporte.getFila().equals("07")) {
            generarReporteMecanismo(3);
        }
        if (filtroReporte.getColumna().equals("06") && filtroReporte.getFila().equals("07")) {
            generarReporteMecanismo(4);
        }
        if (filtroReporte.getColumna().equals("08") && filtroReporte.getFila().equals("07")) {
            generarReporteMecanismo(5);
        }
        if (filtroReporte.getColumna().equals("01") && filtroReporte.getFila().equals("05")) {
            generarReporteEstado(6);
        }
        if (filtroReporte.getColumna().equals("02") && filtroReporte.getFila().equals("05")) {
            generarReporteEstado(7);
        }
        if (filtroReporte.getColumna().equals("03") && filtroReporte.getFila().equals("05")) {
            generarReporteEstado(1);
        }
        if (filtroReporte.getColumna().equals("04") && filtroReporte.getFila().equals("05")) {
            generarReporteEstado(2);
        }
        if (filtroReporte.getColumna().equals("06") && filtroReporte.getFila().equals("05")) {
            generarReporteEstado(3);
        }
        if (filtroReporte.getColumna().equals("07") && filtroReporte.getFila().equals("05")) {
            generarReporteEstado(4);
        }
        if (filtroReporte.getColumna().equals("08") && filtroReporte.getFila().equals("05")) {
            generarReporteEstado(5);
        }
        if (filtroReporte.getColumna().equals("01") && filtroReporte.getFila().equals("08")) {
            generarReporteParticipacion(6);
        }
        if (filtroReporte.getColumna().equals("02") && filtroReporte.getFila().equals("08")) {
            generarReporteParticipacion(7);
        }
        if (filtroReporte.getColumna().equals("03") && filtroReporte.getFila().equals("08")) {
            generarReporteParticipacion(1);
        }
        if (filtroReporte.getColumna().equals("04") && filtroReporte.getFila().equals("08")) {
            generarReporteParticipacion(2);
        }
        if (filtroReporte.getColumna().equals("05") && filtroReporte.getFila().equals("08")) {
            generarReporteParticipacion(3);
        }
        if (filtroReporte.getColumna().equals("06") && filtroReporte.getFila().equals("08")) {
            generarReporteParticipacion(4);
        }
        if (filtroReporte.getColumna().equals("07") && filtroReporte.getFila().equals("08")) {
            generarReporteParticipacion(5);
        }
        if (filtroReporte.getColumna().equals("01") && filtroReporte.getFila().equals("02")) {
            generarReporteDepartamento(7);
        }
        if (filtroReporte.getColumna().equals("03") && filtroReporte.getFila().equals("02")) {
            generarReporteDepartamento(1);
        }
        if (filtroReporte.getColumna().equals("04") && filtroReporte.getFila().equals("02")) {
            generarReporteDepartamento(2);
        }
        if (filtroReporte.getColumna().equals("05") && filtroReporte.getFila().equals("02")) {
            generarReporteDepartamento(3);
        }
        if (filtroReporte.getColumna().equals("06") && filtroReporte.getFila().equals("02")) {
            generarReporteDepartamento(4);
        }
        if (filtroReporte.getColumna().equals("07") && filtroReporte.getFila().equals("02")) {
            generarReporteDepartamento(5);
        }
        if (filtroReporte.getColumna().equals("08") && filtroReporte.getFila().equals("02")) {
            generarReporteDepartamento(6);
        }
    }

    public void generarReporteDepartamento(int ind) {
        List<Catalogo> catalogos = null;
        if (ind == 1) {
            catalogos = catalogoService.parametroPorPadre(90);
        }
        if (ind == 2) {
            catalogos = catalogoService.parametroPorPadre(110);
        }
        if (ind == 3) {
            catalogos = catalogoService.parametroPorPadre(120);
        }
        if (ind == 4) {
            catalogos = catalogoService.parametroPorPadre(210);
        }
        if (ind == 5) {
            catalogos = catalogoService.parametroPorPadre(220);
        }
        if (ind == 6) {
            catalogos = catalogoService.parametroPorPadre(230);
        }
        if (ind == 7) {
            catalogos = catalogoService.parametroPorPadre(280);
        }
        elementoReportes = new ArrayList<>();
        ElementoReporte er1 = new ElementoReporte();
        er1.setTipoConflicto("Total");
        int amazonasSum = 0;
        int ancashSum = 0;
        int apurimacSum = 0;
        int arequipaSum = 0;
        int ayacuchoSum = 0;
        int cajamarcaSum = 0;
        int cuscoSum = 0;
        int huancavelicaSum = 0;
        int huanucoSum = 0;
        int icaSum = 0;
        int juninSum = 0;
        int laLibertadSum = 0;
        int lambayequeSum = 0;
        int limaSum = 0;
        int loretoSum = 0;
        int madreDeDiosSum = 0;
        int moqueguaSum = 0;
        int pascoSum = 0;
        int piuraSum = 0;
        int punoSum = 0;
        int sanMartinSum = 0;
        int tacnaSum = 0;
        int tumbesSum = 0;
        int callaoSum = 0;
        int ucayaliSum = 0;

        for (Catalogo cata : catalogos) {
            List<Caso> listaCasosFl = null;
            if (ind == 1) {
                listaCasosFl = filtrarCasosPorTipo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 2) {
                listaCasosFl = filtrarCasosPorAutoridadCompetente(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 3) {
                listaCasosFl = filtrarCasosPorEstado(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 4) {
                listaCasosFl = filtrarCasosPorFace(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 5) {
                listaCasosFl = filtrarCasosPorMecanismoDialogo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 6) {
                listaCasosFl = filtrarCasosPorParticipacionDefensoria(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 7) {
                listaCasosFl = filtrarCasosPorAnhos(listaCasosReporte, cata.getValorParametro());
            }

            int amazonas = 0;
            int ancash = 0;
            int apurimac = 0;
            int arequipa = 0;
            int ayacucho = 0;
            int cajamarca = 0;
            int cusco = 0;
            int huancavelica = 0;
            int huanuco = 0;
            int ica = 0;
            int junin = 0;
            int laLibertad = 0;
            int lambayeque = 0;
            int lima = 0;
            int loreto = 0;
            int madreDeDios = 0;
            int moquegua = 0;
            int pasco = 0;
            int piura = 0;
            int puno = 0;
            int sanMartin = 0;
            int tacna = 0;
            int tumbes = 0;
            int callao = 0;
            int ucayali = 0;

            for (Caso cs : listaCasosFl) {
                if(cs.getIdDepartamento() != null){
                if (StringUtils.equals(cs.getIdDepartamento(), "1")) {
                    amazonas++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "2")) {
                    ancash++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "3")) {
                    apurimac++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "4")) {
                    arequipa++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "5")) {
                    ayacucho++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "6")) {
                    cajamarca++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "7")) {
                    cusco++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "8")) {
                    huancavelica++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "9")) {
                    huanuco++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "10")) {
                    ica++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "11")) {
                    junin++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "12")) {
                    laLibertad++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "13")) {
                    lambayeque++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "14")) {
                    lima++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "15")) {
                    loreto++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "16")) {
                    madreDeDios++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "17")) {
                    moquegua++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "18")) {
                    pasco++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "19")) {
                    piura++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "20")) {
                    puno++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "21")) {
                    sanMartin++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "22")) {
                    tacna++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "23")) {
                    tumbes++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "24")) {
                    callao++;
                }
                if (StringUtils.equals(cs.getIdDepartamento(), "25")) {
                    ucayali++;
                }
                }
            }
            amazonasSum = amazonasSum + amazonas;
            ancashSum = ancashSum + ancash;
            apurimacSum = apurimacSum + apurimac;
            arequipaSum = arequipaSum + arequipa;
            ayacuchoSum = ayacuchoSum + ayacucho;
            cajamarcaSum = cajamarcaSum + cajamarca;
            cuscoSum = cuscoSum + cusco;
            huancavelicaSum = huancavelicaSum + huancavelica;
            huanucoSum = huanucoSum + huanuco;
            icaSum = icaSum + ica;
            juninSum = juninSum + junin;
            laLibertadSum = laLibertadSum + laLibertad;
            lambayequeSum = lambayequeSum + lambayeque;
            limaSum = limaSum + lima;
            loretoSum = loretoSum + loreto;
            madreDeDiosSum = madreDeDiosSum + madreDeDios;
            moqueguaSum = moqueguaSum + moquegua;
            pascoSum = pascoSum + pasco;
            piuraSum = piuraSum + piura;
            punoSum = punoSum + puno;
            sanMartinSum = sanMartinSum + sanMartin;
            tacnaSum = tacnaSum + tacna;
            tumbesSum = tumbesSum + tumbes;
            callaoSum = callaoSum + callao;
            ucayaliSum = ucayaliSum + ucayali;

            ElementoReporte er = new ElementoReporte(cata.getNombreParametro(), null, null, amazonas, ancash, apurimac, arequipa, ayacucho, cajamarca, cusco, huancavelica, huanuco, ica, junin, laLibertad, lambayeque, lima, loreto, madreDeDios, moquegua, pasco, piura, puno, sanMartin, tacna, tumbes, callao, ucayali);

            elementoReportes.add(er);
        }
        er1.setAmazonas(amazonasSum);
        er1.setAncash(ancashSum);
        er1.setArequipa(arequipaSum);
        er1.setApurimac(apurimacSum);
        er1.setAyacucho(ayacuchoSum);
        er1.setCajamarca(cajamarcaSum);
        er1.setCusco(cuscoSum);
        er1.setHuancavelica(huancavelicaSum);
        er1.setHuanuco(huanucoSum);
        er1.setIca(icaSum);
        er1.setJunin(juninSum);
        er1.setLaLibertad(laLibertadSum);
        er1.setLambayeque(lambayequeSum);
        er1.setLima(limaSum);
        er1.setLoreto(loretoSum);
        er1.setMadreDeDios(madreDeDiosSum);
        er1.setMoquegua(moqueguaSum);
        er1.setPasco(pascoSum);
        er1.setPiura(piuraSum);
        er1.setPuno(punoSum);
        er1.setSanMartin(sanMartinSum);
        er1.setTacna(tacnaSum);
        er1.setTumbes(tumbesSum);
        er1.setCallao(callaoSum);
        er1.setUcayali(ucayaliSum);

        er1.setTotal(amazonasSum + ancashSum + apurimacSum + arequipaSum + ayacuchoSum + cajamarcaSum + cuscoSum + huancavelicaSum + huanucoSum + icaSum + juninSum + laLibertadSum + lambayequeSum + limaSum + loretoSum + madreDeDiosSum + moqueguaSum + pascoSum + piuraSum + punoSum + sanMartinSum + tacnaSum + tumbesSum + callaoSum + ucayaliSum);
        elementoReportes.add(0, er1);

        for (ElementoReporte ers : elementoReportes) {
            Integer tot = ers.getAmazonas() + ers.getAncash() + ers.getApurimac() + ers.getAyacucho() + ers.getCajamarca() + ers.getCusco() + ers.getHuancavelica() + ers.getHuanuco() + ers.getHuancavelica() + ers.getIca() + ers.getJunin() + ers.getLaLibertad() + ers.getLambayeque() + ers.getLima() + ers.getLoreto() + ers.getMadreDeDios() + ers.getMoquegua() + ers.getPasco() + ers.getPiura() + ers.getPuno() + ers.getSanMartin() + ers.getTacna() + ers.getTumbes() + ers.getCallao() + ers.getUcayali();
            Double tota2 = tot * 1.0;
            double porcent = tota2 * 100 / er1.getTotal();
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = " - ";
            } else {
                porcentaj = Double.toString(porcent) + "%";
            }
            ers.setTotal(tot);
            ers.setPorcentaje(porcentaj);
        }
    }

    public void generarReporteParticipacion(int ind) {
        List<Catalogo> catalogos = null;
        if (ind == 1) {
            catalogos = catalogoService.parametroPorPadre(90);
        }
        if (ind == 2) {
            catalogos = catalogoService.parametroPorPadre(110);
        }
        if (ind == 3) {
            catalogos = catalogoService.parametroPorPadre(120);
        }
        if (ind == 4) {
            catalogos = catalogoService.parametroPorPadre(210);
        }
        if (ind == 5) {
            catalogos = catalogoService.parametroPorPadre(220);
        }
        if (ind == 6) {
            catalogos = catalogoService.parametroPorPadre(280);
        }
        if (ind == 7) {
            catalogos = catalogoService.parametroPorPadre(290);
        }
        elementoReportes = new ArrayList<>();
        ElementoReporte er1 = new ElementoReporte();
        er1.setTipoConflicto("Total");
        int intermediadorSum = 0;
        int promotorSum = 0;
        int observadorSum = 0;
        int mediadorSum = 0;
        int participacionSum = 0;
        for (Catalogo cata : catalogos) {
            List<Caso> listaCasosFl = null;
            if (ind == 1) {
                listaCasosFl = filtrarCasosPorTipo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 2) {
                listaCasosFl = filtrarCasosPorAutoridadCompetente(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 3) {
                listaCasosFl = filtrarCasosPorEstado(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 4) {
                listaCasosFl = filtrarCasosPorFace(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 5) {
                listaCasosFl = filtrarCasosPorMecanismoDialogo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 6) {
                listaCasosFl = filtrarCasosPorParticipacionDefensoria(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 7) {
                listaCasosFl = filtrarCasosPorAnhos(listaCasosReporte, cata.getValorParametro());
            }
            int intermediador = 0;
            int promotor = 0;
            int observador = 0;
            int mediador = 0;
            int participacion = 0;
            for (Caso cs : listaCasosFl) {
                if (StringUtils.equals(cs.getTipoParticipacionCaso(), "01")) {
                    intermediador++;
                }
                if (StringUtils.equals(cs.getTipoParticipacionCaso(), "02")) {
                    promotor++;
                }
                if (StringUtils.equals(cs.getTipoParticipacionCaso(), "03")) {
                    observador++;
                }
                if (StringUtils.equals(cs.getTipoParticipacionCaso(), "04")) {
                    mediador++;
                }
                if (StringUtils.equals(cs.getTipoParticipacionCaso(), "05")) {
                    participacion++;
                }
            }
            intermediadorSum = intermediadorSum + intermediador;
            promotorSum = promotorSum + promotor;
            observadorSum = observadorSum + observador;
            mediadorSum = mediadorSum + mediador;
            participacionSum = participacionSum + participacion;
            ElementoReporte er = new ElementoReporte(cata.getNombreParametro(), null, null, intermediador, promotor, observador, mediador, participacion, null, null, null);
            elementoReportes.add(er);
        }
        er1.setIntermediador(intermediadorSum);
        er1.setPromotor(promotorSum);
        er1.setObservador(observadorSum);
        er1.setMediador(mediadorSum);
        er1.setParticipacion(participacionSum);
        er1.setTotal(intermediadorSum + promotorSum + observadorSum + mediadorSum + participacionSum);
        elementoReportes.add(0, er1);

        for (ElementoReporte ers : elementoReportes) {
            Integer tot = ers.getIntermediador() + ers.getPromotor() + ers.getObservador() + ers.getMediador() + ers.getParticipacion();
            Double tota2 = tot * 1.0;
            double porcent = tota2 * 100 / er1.getTotal();
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = " - ";
            } else {
                porcentaj = Double.toString(porcent) + "%";
            }
            ers.setTotal(tot);
            ers.setPorcentaje(porcentaj);
        }
    }

    public void generarReporteEstado(int ind) {
        List<Catalogo> catalogos = null;
        if (ind == 1) {
            catalogos = catalogoService.parametroPorPadre(90);
        }
        if (ind == 2) {
            catalogos = catalogoService.parametroPorPadre(110);
        }
        if (ind == 3) {
            catalogos = catalogoService.parametroPorPadre(210);
        }
        if (ind == 4) {
            catalogos = catalogoService.parametroPorPadre(220);
        }
        if (ind == 5) {
            catalogos = catalogoService.parametroPorPadre(230);
        }
        if (ind == 6) {
            catalogos = catalogoService.parametroPorPadre(280);
        }
        if (ind == 7) {
            catalogos = catalogoService.parametroPorPadre(290);
        }
        elementoReportes = new ArrayList<>();
        ElementoReporte er1 = new ElementoReporte();
        er1.setTipoConflicto("Total");
        int borradorSum = 0;
        int propuestaSum = 0;
        int observacionSum = 0;
        int activoSum = 0;
        int latenteSum = 0;
        int resueltoSum = 0;
        for (Catalogo cata : catalogos) {
            List<Caso> listaCasosFl = null;
            if (ind == 1) {
                listaCasosFl = filtrarCasosPorTipo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 2) {
                listaCasosFl = filtrarCasosPorAutoridadCompetente(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 3) {
                listaCasosFl = filtrarCasosPorFace(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 4) {
                listaCasosFl = filtrarCasosPorMecanismoDialogo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 5) {
                listaCasosFl = filtrarCasosPorParticipacionDefensoria(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 6) {
                listaCasosFl = filtrarCasosPorAnhos(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 7) {
                listaCasosFl = filtrarCasosPorRegion(listaCasosReporte, cata.getValorParametro());
            }
            int borrador = 0;
            int propuesta = 0;
            int observacion = 0;
            int activo = 0;
            int latente = 0;
            int resuelto = 0;
            for (Caso cs : listaCasosFl) {
                if (StringUtils.equals(cs.getTipoEstado(), "01")) {
                    borrador++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "02")) {
                    propuesta++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "03")) {
                    observacion++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "04")) {
                    activo++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "05")) {
                    latente++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "06")) {
                    resuelto++;
                }
            }
            borradorSum = borradorSum + borrador;
            propuestaSum = propuestaSum + propuesta;
            observacionSum = observacionSum + observacion;
            activoSum = activoSum + activo;
            latenteSum = latenteSum + latente;
            resueltoSum = resueltoSum + resuelto;
            ElementoReporte er = new ElementoReporte(cata.getNombreParametro(), null, null, borrador, propuesta, observacion, activo, latente, resuelto, null);

            elementoReportes.add(er);
        }
        er1.setBorrador(borradorSum);
        er1.setPropuesta(propuestaSum);
        er1.setObservacion(observacionSum);
        er1.setActivo(activoSum);
        er1.setLatente(latenteSum);
        er1.setResuelto(resueltoSum);
        er1.setTotal(borradorSum + propuestaSum + observacionSum + activoSum + latenteSum + latenteSum);
        elementoReportes.add(0, er1);

        for (ElementoReporte ers : elementoReportes) {
            Integer tot = ers.getBorrador() + ers.getPropuesta() + ers.getObservacion() + ers.getActivo() + ers.getLatente() + ers.getResuelto();
            Double tota2 = tot * 1.0;
            double porcent = tota2 * 100 / er1.getTotal();
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = " - ";
            } else {
                porcentaj = Double.toString(porcent) + "%";
            }
            ers.setTotal(tot);
            ers.setPorcentaje(porcentaj);
        }
    }

    public void generarReporteMecanismo(int ind) {
        List<Catalogo> catalogos = null;
        if (ind == 1) {
            catalogos = catalogoService.parametroPorPadre(90);
        }
        if (ind == 2) {
            catalogos = catalogoService.parametroPorPadre(110);
        }
        if (ind == 3) {
            catalogos = catalogoService.parametroPorPadre(120);
        }
        if (ind == 4) {
            catalogos = catalogoService.parametroPorPadre(210);
        }
        if (ind == 5) {
            catalogos = catalogoService.parametroPorPadre(230);
        }
        if (ind == 6) {
            catalogos = catalogoService.parametroPorPadre(280);
        }
        if (ind == 7) {
            catalogos = catalogoService.parametroPorPadre(290);
        }
        elementoReportes = new ArrayList<>();
        ElementoReporte er1 = new ElementoReporte();
        er1.setTipoConflicto("Total");
        int negociacionDirectaSum = 0;
        int negociacionMediadaSum = 0;
        int comisionAltoSum = 0;
        int reunionesPreparatoriasSum = 0;
        for (Catalogo cata : catalogos) {
            List<Caso> listaCasosFl = null;
            if (ind == 1) {
                listaCasosFl = filtrarCasosPorTipo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 2) {
                listaCasosFl = filtrarCasosPorAutoridadCompetente(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 3) {
                listaCasosFl = filtrarCasosPorEstado(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 4) {
                listaCasosFl = filtrarCasosPorFace(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 5) {
                listaCasosFl = filtrarCasosPorParticipacionDefensoria(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 6) {
                listaCasosFl = filtrarCasosPorAnhos(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 7) {
                listaCasosFl = filtrarCasosPorRegion(listaCasosReporte, cata.getValorParametro());
            }
            int negociacionDirecta = 0;
            int negociacionMediada = 0;
            int comisionAlto = 0;
            int reunionesPreparatorias = 0;
            for (Caso cs : listaCasosFl) {
                if (StringUtils.equals(cs.getTipoMecanismo(), "01")) {
                    negociacionDirecta++;
                }
                if (StringUtils.equals(cs.getTipoMecanismo(), "02")) {
                    negociacionMediada++;
                }
                if (StringUtils.equals(cs.getTipoMecanismo(), "03")) {
                    comisionAlto++;
                }
                if (StringUtils.equals(cs.getTipoMecanismo(), "04")) {
                    reunionesPreparatorias++;
                }
            }
            negociacionDirectaSum = negociacionDirectaSum + negociacionDirecta;
            negociacionMediadaSum = negociacionMediadaSum + negociacionMediada;
            comisionAltoSum = comisionAltoSum + comisionAlto;
            reunionesPreparatoriasSum = reunionesPreparatoriasSum + reunionesPreparatorias;
            ElementoReporte er = new ElementoReporte(cata.getNombreParametro(), null, null, negociacionDirecta, negociacionMediada, comisionAlto, reunionesPreparatorias);

            elementoReportes.add(er);
        }
        er1.setNegociacionDirecta(negociacionDirectaSum);
        er1.setNegociacionMediada(negociacionMediadaSum);
        er1.setComisionAlto(comisionAltoSum);
        er1.setReunionesPreparatorias(reunionesPreparatoriasSum);
        er1.setTotal(negociacionDirectaSum + negociacionMediadaSum + comisionAltoSum + reunionesPreparatoriasSum);
        elementoReportes.add(0, er1);
        for (ElementoReporte ers : elementoReportes) {
            Integer tot = ers.getNegociacionDirecta() + ers.getNegociacionMediada() + ers.getComisionAlto() + ers.getReunionesPreparatorias();
            Double tota2 = tot * 1.0;
            double porcent = tota2 * 100 / er1.getTotal();
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = " - ";
            } else {
                porcentaj = Double.toString(porcent) + "%";
            }
            ers.setTotal(tot);
            ers.setPorcentaje(porcentaj);
        }
    }

    public void generarReporteAutoridad(int ind) {
        List<Catalogo> catalogos = null;
        if (ind == 1) {
            catalogos = catalogoService.parametroPorPadre(90);
        }
        if (ind == 2) {
            catalogos = catalogoService.parametroPorPadre(120);
        }
        if (ind == 3) {
            catalogos = catalogoService.parametroPorPadre(210);
        }
        if (ind == 4) {
            catalogos = catalogoService.parametroPorPadre(220);
        }
        if (ind == 5) {
            catalogos = catalogoService.parametroPorPadre(230);
        }
        if (ind == 6) {
            catalogos = catalogoService.parametroPorPadre(280);
        }
        if (ind == 7) {
            catalogos = catalogoService.parametroPorPadre(290);
        }
        elementoReportes = new ArrayList<>();
        ElementoReporte er1 = new ElementoReporte();
        er1.setTipoConflicto("Total");
        int gobNacionalSum = 0;
        int gobRegionalSum = 0;
        int gobLocalSum = 0;
        int poderLegislativoSum = 0;
        int porderJudicialSum = 0;
        int otrosOrganismosSum = 0;
        for (Catalogo cata : catalogos) {
            List<Caso> listaCasosFl = null;
            if (ind == 1) {
                listaCasosFl = filtrarCasosPorTipo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 2) {
                listaCasosFl = filtrarCasosPorEstado(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 3) {
                listaCasosFl = filtrarCasosPorFace(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 4) {
                listaCasosFl = filtrarCasosPorMecanismoDialogo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 5) {
                listaCasosFl = filtrarCasosPorParticipacionDefensoria(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 6) {
                listaCasosFl = filtrarCasosPorAnhos(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 7) {
                listaCasosFl = filtrarCasosPorRegion(listaCasosReporte, cata.getValorParametro());
            }
            int gobNacional = 0;
            int gobRegional = 0;
            int gobLocal = 0;
            int poderLegislativo = 0;
            int porderJudicial = 0;
            int otrosOrganismos = 0;
            for (Caso cs : listaCasosFl) {
                if (StringUtils.equals(cs.getTipoAsunto(), "01")) {
                    gobNacional++;
                }
                if (StringUtils.equals(cs.getTipoAsunto(), "02")) {
                    gobRegional++;
                }
                if (StringUtils.equals(cs.getTipoAsunto(), "03")) {
                    gobLocal++;
                }
                if (StringUtils.equals(cs.getTipoAsunto(), "04")) {
                    poderLegislativo++;
                }
                if (StringUtils.equals(cs.getTipoAsunto(), "05")) {
                    porderJudicial++;
                }
                if (StringUtils.equals(cs.getTipoAsunto(), "06")) {
                    otrosOrganismos++;
                }
            }
            gobNacionalSum = gobNacionalSum + gobNacional;
            gobRegionalSum = gobRegionalSum + gobRegional;
            gobLocalSum = gobLocalSum + gobLocal;
            poderLegislativoSum = poderLegislativoSum + poderLegislativo;
            porderJudicialSum = porderJudicialSum + porderJudicial;
            otrosOrganismosSum = otrosOrganismosSum + otrosOrganismos;
            ElementoReporte er = new ElementoReporte(cata.getNombreParametro(), null, null, gobNacional, gobRegional, gobLocal, poderLegislativo, porderJudicial, otrosOrganismos);
            elementoReportes.add(er);
        }
        er1.setGobNacional(gobNacionalSum);
        er1.setGobRegional(gobRegionalSum);
        er1.setGobLocal(gobLocalSum);
        er1.setPoderLegislativo(poderLegislativoSum);
        er1.setPoderJudicial(porderJudicialSum);
        er1.setOrganismoAutonomos(otrosOrganismosSum);
        er1.setTotal(gobNacionalSum + gobRegionalSum + gobLocalSum + poderLegislativoSum + porderJudicialSum + otrosOrganismosSum);
        elementoReportes.add(0, er1);
        for (ElementoReporte ers : elementoReportes) {
            Integer tot = ers.getGobLocal() + ers.getGobRegional() + ers.getGobNacional() + ers.getPoderJudicial() + ers.getPoderLegislativo() + ers.getOrganismoAutonomos();
            Double tota2 = tot * 1.0;
            double porcent = tota2 * 100 / er1.getTotal();
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = " - ";
            } else {
                porcentaj = Double.toString(porcent) + "%";
            }
            ers.setTotal(tot);
            ers.setPorcentaje(porcentaj);
        }
    }

    public void generarReporteTipo(int ind) {
        List<Catalogo> catalogos = null;
        if (ind == 1) {
            catalogos = catalogoService.parametroPorPadre(110);
        }
        if (ind == 2) {
            catalogos = catalogoService.parametroPorPadre(120);
        }
        if (ind == 3) {
            catalogos = catalogoService.parametroPorPadre(210);
        }
        if (ind == 4) {
            catalogos = catalogoService.parametroPorPadre(220);
        }
        if (ind == 5) {
            catalogos = catalogoService.parametroPorPadre(230);
        }
        if (ind == 6) {
            catalogos = catalogoService.parametroPorPadre(280);
        }
        if (ind == 7) {
            catalogos = catalogoService.parametroPorPadre(290);
        }
        elementoReportes = new ArrayList<>();
        ElementoReporte er1 = new ElementoReporte();
        er1.setTipoConflicto("Total");
        int gobiernoLocalSum = 0;
        int gobiernoRegionalSum = 0;
        int gobiernoNacionalSum = 0;
        int comunalSum = 0;
        int cultivoCocaSum = 0;
        int demarcacionTerritorialSum = 0;
        int electoralSum = 0;
        int laboralSum = 0;
        int socioambientaSum = 0;
        int otrosSum = 0;
        for (Catalogo cata : catalogos) {
            List<Caso> listaCasosFl = null;
            if (ind == 1) {
                listaCasosFl = filtrarCasosPorAutoridadCompetente(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 2) {
                listaCasosFl = filtrarCasosPorEstado(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 3) {
                listaCasosFl = filtrarCasosPorFace(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 4) {
                listaCasosFl = filtrarCasosPorMecanismoDialogo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 5) {
                listaCasosFl = filtrarCasosPorParticipacionDefensoria(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 6) {
                listaCasosFl = filtrarCasosPorAnhos(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 7) {
                listaCasosFl = filtrarCasosPorRegion(listaCasosReporte, cata.getValorParametro());
            }
            int gobiernoLocal = 0;
            int gobiernoRegional = 0;
            int gobiernoNacional = 0;
            int comunal = 0;
            int cultivoCoca = 0;
            int demarcacionTerritorial = 0;
            int electoral = 0;
            int laboral = 0;
            int socioambienta = 0;
            int otros = 0;
            for (Caso cs : listaCasosFl) {
                if (StringUtils.equals(cs.getTipo(), "01")) {
                    gobiernoLocal++;
                }
                if (StringUtils.equals(cs.getTipo(), "02")) {
                    gobiernoNacional++;
                }
                if (StringUtils.equals(cs.getTipo(), "03")) {
                    gobiernoRegional++;
                }
                if (StringUtils.equals(cs.getTipo(), "04")) {
                    comunal++;
                }
                if (StringUtils.equals(cs.getTipo(), "05")) {
                    cultivoCoca++;
                }
                if (StringUtils.equals(cs.getTipo(), "06")) {
                    demarcacionTerritorial++;
                }
                if (StringUtils.equals(cs.getTipo(), "07")) {
                    electoral++;
                }
                if (StringUtils.equals(cs.getTipo(), "08")) {
                    laboral++;
                }
                if (StringUtils.equals(cs.getTipo(), "09")) {
                    socioambienta++;
                }
                if (StringUtils.equals(cs.getTipo(), "10")) {
                    otros++;
                }
            }
            gobiernoLocalSum = gobiernoLocalSum + gobiernoLocal;
            gobiernoRegionalSum = gobiernoRegionalSum + gobiernoRegional;
            gobiernoNacionalSum = gobiernoNacionalSum + gobiernoNacional;
            comunalSum = comunalSum + comunal;
            cultivoCocaSum = cultivoCocaSum + cultivoCoca;
            demarcacionTerritorialSum = demarcacionTerritorialSum + demarcacionTerritorial;
            electoralSum = electoralSum + electoral;
            laboralSum = laboralSum + laboral;
            socioambientaSum = socioambientaSum + socioambienta;
            otrosSum = otrosSum + otros;
            ElementoReporte er = new ElementoReporte(cata.getNombreParametro(), null, null, gobiernoLocal, gobiernoRegional, gobiernoNacional, comunal, cultivoCoca, demarcacionTerritorial, electoral, laboral, socioambienta, otros);
            elementoReportes.add(er);
        }
        er1.setGobiernoLocal(gobiernoLocalSum);
        er1.setGobiernoRegional(gobiernoRegionalSum);
        er1.setGobiernoNacional(gobiernoNacionalSum);
        er1.setComunal(comunalSum);
        er1.setCultivoCoca(cultivoCocaSum);
        er1.setDemarcacionTerritorial(demarcacionTerritorialSum);
        er1.setElectoral(electoralSum);
        er1.setLaboral(laboralSum);
        er1.setSocioambienta(socioambientaSum);
        er1.setOtros(otrosSum);
        er1.setTotal(gobiernoLocalSum + gobiernoRegionalSum + gobiernoNacionalSum + comunalSum + cultivoCocaSum + demarcacionTerritorialSum + electoralSum + laboralSum + socioambientaSum + otrosSum);
        elementoReportes.add(0, er1);
        for (ElementoReporte ers : elementoReportes) {
            Integer tot = ers.getGobiernoLocal() + ers.getGobiernoRegional() + ers.getGobiernoNacional() + ers.getComunal() + ers.getCultivoCoca() + ers.getDemarcacionTerritorial() + ers.getElectoral() + ers.getLaboral() + ers.getSocioambienta() + ers.getOtros();
            Double tota2 = tot * 1.0;
            double porcent = tota2 * 100 / er1.getTotal();
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = " - ";
            } else {
                porcentaj = Double.toString(porcent) + "%";
            }
            ers.setTotal(tot);
            ers.setPorcentaje(porcentaj);
        }
    }

    public void generarReporteFase(int ind) {
        List<Catalogo> catalogos = null;
        if (ind == 1) {
            catalogos = catalogoService.parametroPorPadre(90);
        }
        if (ind == 2) {
            catalogos = catalogoService.parametroPorPadre(110);
        }
        if (ind == 3) {
            catalogos = catalogoService.parametroPorPadre(210);
        }
        if (ind == 4) {
            catalogos = catalogoService.parametroPorPadre(220);
        }
        if (ind == 5) {
            catalogos = catalogoService.parametroPorPadre(230);
        }
        if (ind == 6) {
            catalogos = catalogoService.parametroPorPadre(280);
        }
        if (ind == 7) {
            catalogos = catalogoService.parametroPorPadre(290);
        }
        elementoReportes = new ArrayList<>();
        ElementoReporte er1 = new ElementoReporte();
        er1.setTipoConflicto("Total");
        int tempranaSum = 0;
        int escalamientoSum = 0;
        int crisisSum = 0;
        int dialogoSum = 0;
        int desescalamientoSum = 0;
        for (Catalogo cata : catalogos) {
            List<Caso> listaCasosFl = null;
            if (ind == 1) {
                listaCasosFl = filtrarCasosPorTipo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 2) {
                listaCasosFl = filtrarCasosPorAutoridadCompetente(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 3) {
                listaCasosFl = filtrarCasosPorEstado(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 4) {
                listaCasosFl = filtrarCasosPorMecanismoDialogo(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 5) {
                listaCasosFl = filtrarCasosPorParticipacionDefensoria(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 6) {
                listaCasosFl = filtrarCasosPorAnhos(listaCasosReporte, cata.getValorParametro());
            }
            if (ind == 7) {
                listaCasosFl = filtrarCasosPorRegion(listaCasosReporte, cata.getValorParametro());
            }
            int temprana = 0;
            int escalamiento = 0;
            int crisis = 0;
            int dialogo = 0;
            int desescalamiento = 0;
            for (Caso cs : listaCasosFl) {
                if (StringUtils.equals(cs.getTipoDialogo(), "01")) {
                    temprana++;
                }
                if (StringUtils.equals(cs.getTipoDialogo(), "02")) {
                    escalamiento++;
                }
                if (StringUtils.equals(cs.getTipoDialogo(), "03")) {
                    crisis++;
                }
                if (StringUtils.equals(cs.getTipoDialogo(), "04")) {
                    desescalamiento++;
                }
                if (StringUtils.equals(cs.getTipoDialogo(), "05")) {
                    dialogo++;
                }
            }
            dialogoSum = dialogoSum + dialogo;
            desescalamientoSum = desescalamientoSum + desescalamiento;
            crisisSum = crisisSum + crisis;
            escalamientoSum = escalamientoSum + escalamiento;
            tempranaSum = tempranaSum + temprana;

            ElementoReporte er = new ElementoReporte(cata.getNombreParametro(), null, null, temprana, escalamiento, crisis, desescalamiento, dialogo);
            elementoReportes.add(er);
        }
        er1.setTemprana(tempranaSum);
        er1.setEscalamiento(escalamientoSum);
        er1.setCrisis(crisisSum);
        er1.setDesescalamiento(desescalamientoSum);
        er1.setDialogo(dialogoSum);
        er1.setTotal(tempranaSum + escalamientoSum + desescalamientoSum + dialogoSum + crisisSum);
        elementoReportes.add(0, er1);
        for (ElementoReporte ers : elementoReportes) {
            Integer tot = ers.getTemprana() + ers.getEscalamiento() + ers.getCrisis() + ers.getDesescalamiento() + ers.getDialogo();
            Double tota2 = tot * 1.0;
            double porcent = tota2 * 100 / er1.getTotal();
            porcent = (double) ((int) (porcent * 100) / 100.0);
            String porcentaj = "";
            if (porcent == 0) {
                porcentaj = " - ";
            } else {
                porcentaj = Double.toString(porcent) + "%";
            }
            ers.setTotal(tot);
            ers.setPorcentaje(porcentaj);
        }
    }

    private List<Caso> filtrarCasosPorTipo(List<Caso> casos, String tipo) {
        Filter<Caso, String> filter = new Filter<Caso, String>() {
            public boolean isMatched(Caso object, String text) {
                return object.getTipo().startsWith(String.valueOf(text));
            }
        };
        List<Caso> sortCasos = new FilterList().filterList(casos, filter, tipo);
        return sortCasos;
    }

    private List<Caso> filtrarCasosPorFace(List<Caso> casos, String tipo) {
        Filter<Caso, String> filter = new Filter<Caso, String>() {
            public boolean isMatched(Caso object, String text) {
                return object.getTipoDialogo().startsWith(String.valueOf(text));
            }
        };
        List<Caso> sortCasos = new FilterList().filterList(casos, filter, tipo);
        return sortCasos;
    }

    private List<Caso> filtrarCasosPorAutoridadCompetente(List<Caso> casos, String tipo) {
        Filter<Caso, String> filter = new Filter<Caso, String>() {
            public boolean isMatched(Caso object, String text) {
                return object.getTipoAsunto().startsWith(String.valueOf(text));
            }
        };
        List<Caso> sortCasos = new FilterList().filterList(casos, filter, tipo);
        return sortCasos;
    }

    private List<Caso> filtrarCasosPorEstado(List<Caso> casos, String tipo) {
        Filter<Caso, String> filter = new Filter<Caso, String>() {
            public boolean isMatched(Caso object, String text) {
                return object.getTipoEstado().startsWith(String.valueOf(text));
            }
        };
        List<Caso> sortCasos = new FilterList().filterList(casos, filter, tipo);
        return sortCasos;
    }

    private List<Caso> filtrarCasosPorMecanismoDialogo(List<Caso> casos, String tipo) {
        Filter<Caso, String> filter = new Filter<Caso, String>() {
            public boolean isMatched(Caso object, String text) {
                return object.getTipoMecanismo().startsWith(String.valueOf(text));
            }
        };
        List<Caso> sortCasos = new FilterList().filterList(casos, filter, tipo);
        return sortCasos;
    }

    private List<Caso> filtrarCasosPorParticipacionDefensoria(List<Caso> casos, String tipo) {
        Filter<Caso, String> filter = new Filter<Caso, String>() {
            public boolean isMatched(Caso object, String text) {
                return object.getTipoParticipacionCaso().startsWith(String.valueOf(text));
            }
        };
        List<Caso> sortCasos = new FilterList().filterList(casos, filter, tipo);
        return sortCasos;
    }
    
    private List<Caso> filtrarCasosPorRegion(List<Caso> casos, String tipo) {
        Filter<Caso, String> filter = new Filter<Caso, String>() {
            public boolean isMatched(Caso object, String text) {
                if(object.getIdDepartamento() != null){
                    String entero = object.getIdDepartamento();
                    return entero.startsWith(String.valueOf(text));
                }
                return false;
            }
        };
        List<Caso> sortCasos = new FilterList().filterList(casos, filter, tipo);
        return sortCasos;
    }

    private List<Caso> filtrarCasosPorAnhos(List<Caso> casos, String tipo) {
        Filter<Caso, String> filter = new Filter<Caso, String>() {
            public boolean isMatched(Caso object, String text) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy");
                String anho = simpleDateFormat.format(object.getFechaPublicacion());
                return anho.startsWith(String.valueOf(text));
            }
        };
        List<Caso> sortCasos = new FilterList().filterList(casos, filter, tipo);
        return sortCasos;
    }
    
    public void mostrarReporte() {
        if (filtroReporte.getTipoReporte() == 1) {
            filtroReporte.setTitulo("- información de Casos");
        }
        elementoReportes = null;
    }
    
    public void initJasper(int ind) throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                elementoReportes);
        if(ind == 1)
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/").concat("reporteFiltroCasoTipo.jasper"),new HashMap(), beanCollectionDataSource);
        if(ind == 2)
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/").concat("reporteFiltroCasoAutoridadCompetente.jasper"),new HashMap(), beanCollectionDataSource);
        if(ind == 3)
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/").concat("reporteFiltroCasoEstado.jasper"),new HashMap(), beanCollectionDataSource);
        if(ind == 4)
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/").concat("reporteFiltroCasoFace.jasper"),new HashMap(), beanCollectionDataSource);
        if(ind == 5)
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/").concat("reporteFiltroCasoMecanismoDialogo.jasper"),new HashMap(), beanCollectionDataSource);
        if(ind == 6)
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/").concat("reporteFiltroCasoFaceParticipacion.jasper"),new HashMap(), beanCollectionDataSource);
        if(ind == 7)
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/").concat("reporteFiltroCasoRegion.jasper"),new HashMap(), beanCollectionDataSource);
        
        
    }

    public void pdf(int ind) throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasper(ind);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporte.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    public void xls(int ind) throws JRException, IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(new Date());
        initJasper(ind);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporte.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    public FiltroReporte getFiltroReporte() {
        return filtroReporte;
    }

    public void setFiltroReporte(FiltroReporte filtroReporte) {
        this.filtroReporte = filtroReporte;
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

    public List<ElementoReporte> getElementoReportes() {
        return elementoReportes;
    }

    public void setElementoReportes(List<ElementoReporte> elementoReportes) {
        this.elementoReportes = elementoReportes;
    }

    public List<Caso> getListaCasosReporte() {
        return listaCasosReporte;
    }

    public void setListaCasosReporte(List<Caso> listaCasosReporte) {
        this.listaCasosReporte = listaCasosReporte;
    }

    public List<Resumen> getListaAnhos() {
        return listaAnhos;
    }

    public void setListaAnhos(List<Resumen> listaAnhos) {
        this.listaAnhos = listaAnhos;
    }

    public Map getListasMeses() {
        return listasMeses;
    }

    public void setListasMeses(Map listasMeses) {
        this.listasMeses = listasMeses;
    }

    public List<ElementoResumenEjecutivo> getListaResumenEjecutivo() {
        return listaResumenEjecutivo;
    }

    public void setListaResumenEjecutivo(List<ElementoResumenEjecutivo> listaResumenEjecutivo) {
        this.listaResumenEjecutivo = listaResumenEjecutivo;
    }

}
