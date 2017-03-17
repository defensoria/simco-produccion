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
import gob.dp.simco.comun.entity.Resumen;
import gob.dp.simco.comun.service.ReporteService;
import gob.dp.simco.comun.service.UbigeoService;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import gob.dp.simco.comun.type.MesesEnum;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.service.ActividadService;
import gob.dp.simco.registro.type.EstadoCasoType;
import gob.dp.simco.registro.type.MesType;
import gob.dp.simco.reporte.entity.CuadroGenericoMes;
import gob.dp.simco.reporte.entity.ElementoNombreValor;
import gob.dp.simco.reporte.entity.ElementoReporte;
import gob.dp.simco.reporte.entity.ElementoResumenEjecutivo;
import gob.dp.simco.reporte.entity.FiltroReporte;
import gob.dp.simco.reporte.entity.ReporteSimcoCaso;
import gob.dp.simco.reporte.service.ReporteEjecutivoService;
import gob.dp.simco.reporte.service.ReporteSimcoCasoService;
import gob.dp.simco.seguridad.controller.LoginController;
import gob.dp.simco.seguridad.entity.Usuario;
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
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
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
    
    private FiltroReporte filtroReporteCarga;

    private List<SelectItem> listaDepartamento;

    List<ElementoReporte> elementoReportes;

    private List<Caso> listaCasosReporte;

    private List<Resumen> listaAnhos;

    private Map listasMeses;
    
    JasperPrint jasperPrint;
    
    private List<ElementoResumenEjecutivo> listaResumenEjecutivo;
    
    private Usuario usuarioSession;

    @Autowired
    private UbigeoService ubigeoService;

    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private ReporteService reporteService;
    
    @Autowired
    private ReporteEjecutivoService reporteEjecutivoService;
    
    @Autowired
    private ReporteSimcoCasoService reporteSimcoCasoService;
    
    @Autowired
    private ActividadService actividadService;

    public String reporte() {
        filtroReporte = new FiltroReporte();
        filtroReporteCarga = new FiltroReporte();
        listaAnhos = reporteService.listadoAnhoCaso();
        cargarMeses();
        return "reporteGeneral";
    }
    
    private Integer casosDeLatenteAActivoMes(){
        filtroReporte.setTipoEstadoCaso(EstadoCasoType.ACTIVO.getKey());
        List<Caso> lista = reporteEjecutivoService.listadoCasosEstadoMes(filtroReporte);
        int i = 0;
        for(Caso c : lista){
            filtroReporte.setCodigoCaso(c.getCodigo());
            List<Caso> listaInactivos =  reporteEjecutivoService.listaCasosAntesDeAprobado(filtroReporte);
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
        List<Caso> lista = reporteEjecutivoService.listadoCasosEstadoMes(filtroReporte);
        int i = 0;
        for(Caso c : lista){
            filtroReporte.setCodigoCaso(c.getCodigo());
            List<Caso> listaInactivos =  reporteEjecutivoService.listaCasosAntesDeAprobado(filtroReporte);
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
        List<Caso> lista = reporteEjecutivoService.listadoCasosEstadoMes(filtroReporte);
        int i = 0;
        for(Caso c : lista){
            filtroReporte.setCodigoCaso(c.getCodigo());
            List<Caso> listaInactivos =  reporteEjecutivoService.listaCasosAntesDeAprobado(filtroReporte);
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
        List<Caso> lista = reporteEjecutivoService.listadoCasosEstadoMes(filtroReporte);
        int i = 0;
        for(Caso c : lista){
            filtroReporte.setCodigoCaso(c.getCodigo());
            List<Caso> listaInactivos =  reporteEjecutivoService.listaCasosAntesDeAprobado(filtroReporte);
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
    
    public boolean resumenEjecutivo(int tipo) throws JRException, IOException{
        listaResumenEjecutivo = new ArrayList<>();
        if(StringUtils.equals(filtroReporte.getAnhos(), "0") || StringUtils.equals(filtroReporte.getMes(), "0")){
            msg.messageAlert("Debe ingresar el año y mes del reporte", null);
            return false;
        }
        filtroReporte.setAnhoMesString(filtroReporte.getAnhos()+filtroReporte.getMes());
        if(!reporteEjecutivoService.existeReporteMes(filtroReporte)){
            msg.messageAlert("No existe información para el reporte con el año y mes ingresados", null);
            return false;
        }
        
        Integer codigoReporte = reporteEjecutivoService.codigoReporteMes(filtroReporte);
        if(codigoReporte == null){
            msg.messageAlert("No se ha podido generar el reporte", null);
            return false;
        }
        filtroReporte.setCodigoReporte(codigoReporte);
        ElementoResumenEjecutivo ejecutivo = new ElementoResumenEjecutivo();
        /****MES PUBLICACION*****/
        String mesPublicacion;
        mesPublicacion = MesesEnum.get(filtroReporte.getMes()).getValue();
        ejecutivo.setMesPublicacion(mesPublicacion);
        /****MES PUBLICACION*****/
        
        /****ANHO PUBLICACION*****/
        String anhoPublicacion;
        anhoPublicacion = "20"+filtroReporte.getAnhos();
        ejecutivo.setAnhoPublicacion(anhoPublicacion); 
        /****ANHO PUBLICACION*****/
        
        /****TOTAL REGISTRADOS*****/
        Integer totalCasosRegistrados;
        totalCasosRegistrados = reporteEjecutivoService.totalCasosRegistrados(filtroReporte);
        ejecutivo.setTotalCasosRegistrados(totalCasosRegistrados); 
        /****TOTAL REGISTRADOS*****/
        
        /****TOTAL ACTIVOS*****/
        Integer totalCasosActivos;
        totalCasosActivos = reporteEjecutivoService.totalCasosActivos(filtroReporte);
        ejecutivo.setTotalCasosActivos(totalCasosActivos);
        ejecutivo.setPorcentajeTotalCasosActivos(cambiarPorcentaje(totalCasosRegistrados, totalCasosActivos));
        /****TOTAL ACTIVOS*****/
        
        /****TOTAL LATENTES*****/
        Integer totalCasosLatentes;
        totalCasosLatentes = reporteEjecutivoService.totalCasosLatentes(filtroReporte);
        ejecutivo.setTotalCasosLatentes(totalCasosLatentes); 
        ejecutivo.setPorcentajeTotalCasosLatentes(cambiarPorcentaje(totalCasosRegistrados, totalCasosLatentes));
        /****TOTAL LATENTES*****/
       
        /****TOTAL CASOS REGISTRADOS EN EL MES*****/
        Integer totalCasosRegistradosMes;
        totalCasosRegistradosMes = reporteEjecutivoService.totalCasosRegistradosMes(filtroReporte);
        ejecutivo.setTotalCasosRegistradosMes(totalCasosRegistradosMes); 
        /****TOTAL CASOS REGISTRADOS EN EL MES*****/
        
        /****TOTAL CASOS REACTIVADOS EN EL MES*****/
        Integer totalCasosDeLatenteAActivoMes;
        totalCasosDeLatenteAActivoMes = casosDeLatenteAActivoMes();
        ejecutivo.setTotalCasosDeLatenteAActivoMes(totalCasosDeLatenteAActivoMes);
        /****TOTAL CASOS REACTIVADOS EN EL MES*****/
        
        /****TOTAL CASOS INGRESARON A LATENTE EN EL MES*****/
        Integer totalCasosDeActivosALatenteMes;
        totalCasosDeActivosALatenteMes = casosDeActivoALatenteMes();
        ejecutivo.setTotalCasosDeActivosALatenteMes(totalCasosDeActivosALatenteMes);
        /****TOTAL CASOS INGRESARON A LATENTE EN EL MES*****/
        
        /****TOTAL CASOS REINCORPORADOS EN EL MES*****/
        Integer totalCasosDeRetiradoAActivoMes;
        totalCasosDeRetiradoAActivoMes = casosDeRetiradoAActivoMes();
        ejecutivo.setTotalCasosDeRetiradoAActivoMes(totalCasosDeRetiradoAActivoMes);
        /****TOTAL CASOS REINCORPORADOS EN EL MES*****/
        
        /****TOTAL CASOS RESUELTOS EN EL MES*****/
        Integer totalCasosResueltoMes;
        totalCasosResueltoMes = reporteEjecutivoService.totalCasosResueltoMes(filtroReporte);
        ejecutivo.setTotalCasosResueltoMes(totalCasosResueltoMes);
        /****TOTAL CASOS RESUELTOS EN EL MES*****/
        
        /****TOTAL CASOS RETIRADOS EN EL MES*****/
        Integer totalCasosLatentesObservacion;
        totalCasosLatentesObservacion = casosDeLatenteARetirado();
        ejecutivo.setTotalCasosLatentesObservacion(totalCasosLatentesObservacion);
        /****TOTAL CASOS RETIRADOS EN EL MES*****/
        
        /****TOTAL CASOS EN PROCESO DE DIALOGO*****/
        Integer totalCasosFaseDialogo;
        totalCasosFaseDialogo = reporteEjecutivoService.totalCasosDialogo(filtroReporte);
        ejecutivo.setTotalCasosFaseDialogo(totalCasosFaseDialogo);
        ejecutivo.setPorcentajeCasosFaseDialogo("("+cambiarPorcentaje(totalCasosActivos,totalCasosFaseDialogo)+" de los casos activos)");        
        /****TOTAL CASOS EN PROCESO DE DIALOGO*****/
        
        /****TOTAL CASOS EN PROCESO DE DIALOGO EN MESA DE NEGOCIACION****/
        Integer totalCasosDialogoNegociacion;
        totalCasosDialogoNegociacion = reporteEjecutivoService.totalCasosDialogoNegociacion(filtroReporte);
        ejecutivo.setTotalCasosDialogoNegociacion(totalCasosDialogoNegociacion);
        ejecutivo.setPorcentajeCasosFaseDialogoNegociacion("("+cambiarPorcentaje(totalCasosFaseDialogo,totalCasosDialogoNegociacion)+")");
        /****TOTAL CASOS EN PROCESO DE DIALOGO EN MESA DE NEGOCIACION****/
        
        /****TOTAL CASOS EN PROCESO DE DIALOGO REUNIONES PREPARATORIAS****/
        Integer totalCasosDialogoReuniones;
        totalCasosDialogoReuniones = reporteEjecutivoService.totalCasosDialogoReuniones(filtroReporte);
        ejecutivo.setTotalCasosDialogoReuniones(totalCasosDialogoReuniones);
        ejecutivo.setPorcentajeCasosFaseDialogoReuniones("("+cambiarPorcentaje(totalCasosFaseDialogo,totalCasosDialogoReuniones)+")");
        /****TOTAL CASOS EN PROCESO DE DIALOGO REUNIONES PREPARATORIAS****/
        
        /****TOTAL CASOS EN PROCESO DE DIALOGO CON PRESENCIA DE LA DEFENSORIA****/
        Integer totalCasosDialogoEspacioDialogo;
        totalCasosDialogoEspacioDialogo = reporteEjecutivoService.totalCasosDialogoEspacioDialogo(filtroReporte);
        ejecutivo.setTotalCasosDialogoEspacioDialogo(totalCasosDialogoEspacioDialogo);
        ejecutivo.setPorcentajeCasosFaseDialogoEspacionDialogo("("+cambiarPorcentaje(totalCasosFaseDialogo,totalCasosDialogoEspacioDialogo)+")");
        /****TOTAL CASOS EN PROCESO DE DIALOGO CON PRESENCIA DE LA DEFENSORIA****/
        
        /****TOTAL CASOS CON HECHOS DE VIOLENCIA****/
        Integer totalCasosACVictimaViolencia;
        totalCasosACVictimaViolencia = reporteEjecutivoService.totalCasosACVictimaViolencia(filtroReporte);
        ejecutivo.setTotalCasosACVictimaViolencia(totalCasosACVictimaViolencia);
        ejecutivo.setPorcentajeCasosACVictimaViolencia("("+cambiarPorcentaje(totalCasosActivos,totalCasosACVictimaViolencia)+")");
        /****TOTAL CASOS CON HECHOS DE VIOLENCIA****/
        
        /****TOTAL CASOS CON HECHOS DE VIOLENCIA MES****/
        Integer totalCasosACVictimaViolenciaMes;
        totalCasosACVictimaViolenciaMes = reporteEjecutivoService.totalCasosACVictimaViolenciaMes(filtroReporte);        
        ejecutivo.setTotalCasosACVictimaViolenciaMes(totalCasosACVictimaViolenciaMes);
        ejecutivo.setPorcentajeCasosACVictimaViolenciaMes(" ("+cambiarPorcentaje(totalCasosActivos,totalCasosACVictimaViolenciaMes)+")");
        /****TOTAL CASOS CON HECHOS DE VIOLENCIA MES****/
                
        /****ACCIONES COLECTIVAS DE PROTESTA****/
        Integer totalCasosACAccionesProtestaMes;
        totalCasosACAccionesProtestaMes = reporteEjecutivoService.totalCasosACAccionesProtestaMes(filtroReporte);
        ejecutivo.setTotalCasosACAccionesProtestaMes(totalCasosACAccionesProtestaMes);
        /****ACCIONES COLECTIVAS DE PROTESTA****/
        
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA****/
        Integer totalCasosAD;
        totalCasosAD = reporteEjecutivoService.totalCasosAD(filtroReporte);
        ejecutivo.setTotalCasosAD(totalCasosAD);
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA****/
        
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA MES****/
        Integer totalCasosADMes;
        totalCasosADMes = reporteEjecutivoService.totalCasosADMes(filtroReporte);
        ejecutivo.setTotalCasosADMes(totalCasosADMes);
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA MES****/
                                
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA MES CON SUPERVISION PREVENTIVA****/
        Integer totalActividadSupervisionPreventivaMes;
        totalActividadSupervisionPreventivaMes = reporteEjecutivoService.totalActividadSupervisionPreventivaMes(filtroReporte);
        ejecutivo.setTotalActividadSupervisionPreventivaMes(totalActividadSupervisionPreventivaMes);
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA MES CON SUPERVISION PREVENTIVA****/
                                
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA MES CON INTERMEDIACIONES****/                       
        Integer totalActividadIntermediacionMes;
        totalActividadIntermediacionMes = reporteEjecutivoService.totalActividadIntermediacionMes(filtroReporte);
        ejecutivo.setTotalActividadIntermediacionMes(totalActividadIntermediacionMes);
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA MES CON INTERMEDIACIONES****/                       
                                
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA MES CON ACCIONES HUMANITARIAS****/    
        Integer totalActividadAccionesHumanitariasMes;
        totalActividadAccionesHumanitariasMes = reporteEjecutivoService.totalActividadAccionesHumanitariasMes(filtroReporte);
        ejecutivo.setTotalActividadAccionesHumanitariasMes(totalActividadAccionesHumanitariasMes);
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA MES CON ACCIONES HUMANITARIAS****/                       
                                        
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA MES CON DEFENSA LEGAL****/ 
        Integer totalActividadAccionesDefensaLegalMes;
        totalActividadAccionesDefensaLegalMes = reporteEjecutivoService.totalActividadAccionesDefensaLegalMes(filtroReporte);
        ejecutivo.setTotalActividadAccionesDefensaLegalMes(totalActividadAccionesDefensaLegalMes);
        /****CASOS CON PARTICIPACION DE LA DEFENSORIA MES CON DEFENSA LEGAL****/
        
        /****Cuadro N.° 1:****/ 
        Integer numeroMes = Integer.parseInt(filtroReporte.getMes());
        String anhoe = filtroReporte.getAnhos();
        List<CuadroGenericoMes> listaCasosRegistradosMES = new ArrayList<>();
        List<CuadroGenericoMes> listaCasosResueltosMES = new ArrayList<>();
        List<ElementoNombreValor> registradoTotals = new ArrayList<>();
        CuadroGenericoMes cma = new CuadroGenericoMes();
        CuadroGenericoMes cma1 = new CuadroGenericoMes();
        CuadroGenericoMes cmaCasosRegistradosPorMes = new CuadroGenericoMes();
        CuadroGenericoMes cmaCasosResueltosPorMes = new CuadroGenericoMes();
        
        /*Cuadro N.° 5:*/
        /*NUMERO DE CASOS POR MES*/
        for(int i = 0; i< 13; i++){
            ElementoNombreValor ct = new ElementoNombreValor();
            
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
            fr.setCodigoReporte(codigoReporte);
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
        
        ejecutivo.setListaCasosRegistradosMES(listaCasosRegistradosMES);
        ejecutivo.setListaCasosResueltosMES(listaCasosResueltosMES);
        /****Cuadro N.° 1:****/ 
        /*Cuadro N.° 5:*/
        
        /****Gráfico N° 1****/
        List<ElementoNombreValor> registradoTotals1 = new ArrayList<>();
        for(int i = 12; i >= 0; i--){
            registradoTotals1.add(registradoTotals.get(i));
        }
        ejecutivo.setRegistradoTotals(registradoTotals1);
        /****Gráfico N° 1****/
        
        /****Cuadro N.° 2****/
        /****Gráfico N° 2****/
        filtroReporte.setTipoTipologiaCaso("110");
        filtroReporte.setTipoReporte(1);
        List<ElementoResumenEjecutivo> nivelesParametros = reporteEjecutivoService.totalMensualSegunTipologiaCaso(filtroReporte);
        
        int totalGob= 0;
        
        for(ElementoResumenEjecutivo ere : nivelesParametros){
            totalGob += ere.getValor();
        }
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
        /****Gráfico N° 2****/
        /****Cuadro N.° 2****/
        
        /****Cuadro N.° 3:****/
        ejecutivo.setElementoReportesAutoridad(generarReporteAutoridadReport());
        /****Cuadro N.° 3:****/
        
        /****Cuadro N.° 4:****/
        List<ReporteSimcoCaso> listaCasosNuevosPorMes;
        listaCasosNuevosPorMes = reporteSimcoCasoService.listaCasosNuevosPorMes(filtroReporte);
        int contad = 0;
        for(ReporteSimcoCaso rsc : listaCasosNuevosPorMes){
            contad++;
            rsc.setContador(contad);
        }
        ejecutivo.setListaCasosNuevosPorMes(listaCasosNuevosPorMes);
        /****Cuadro N.° 4:****/
        
        /****Cuadro N.° 5:****/
        List<ReporteSimcoCaso> listaCasosResueltosPorMes;
        listaCasosResueltosPorMes = reporteSimcoCasoService.listaCasosResueltosPorMes(filtroReporte);
        contad = 0;
        for(ReporteSimcoCaso rsc : listaCasosResueltosPorMes){
            contad++;
            rsc.setContador(contad);
        }
        ejecutivo.setListaCasosResueltosPorMes(listaCasosResueltosPorMes);
        /****Cuadro N.° 5:****/
        
        
        
        /****Cuadro Cuadro N.° 9:****/
        /****Gráfico N° 6:****/
        int totalTipo2= 0;
        filtroReporte.setTipoTipologiaCaso("90");
        filtroReporte.setTipoReporte(2);
        List<ElementoResumenEjecutivo> nivelesParametros3 = reporteEjecutivoService.totalMensualSegunTipologiaCasoDialogo(filtroReporte);
        
        for(ElementoResumenEjecutivo ere : nivelesParametros3){
            totalTipo2 += ere.getValor();
        }
        List<ElementoNombreValor> listNivelTipoDialogo = new ArrayList<>();
        List<ElementoNombreValor> listNivelTipoDialogoGrafic = new ArrayList<>();
        ElementoNombreValor env4 = new ElementoNombreValor();
        env4.setNombre("Total");
        env4.setValor(totalTipo2);
        env4.setPorcentaje("100.0%");
        listNivelTipoDialogo.add(env4);
        
        for(ElementoResumenEjecutivo ere : nivelesParametros3){
            ElementoNombreValor env = new ElementoNombreValor();
            env.setNombre(ere.getNombre());
            env.setValor(ere.getValor());
            env.setPorcentaje(cambiarPorcentaje(totalTipo2, ere.getValor()));
            listNivelTipoDialogo.add(env);
            listNivelTipoDialogoGrafic.add(env);
        }
        ejecutivo.setListaNivelTipoDialogo(listNivelTipoDialogo);
        ejecutivo.setListaNivelTipoDialogoGrafico(listNivelTipoDialogoGrafic);
        /****Cuadro Cuadro N.° 9:****/
        /****Gráfico N° 6:****/
        ejecutivo.setElementoReportesDepartamentoEstado(generarReporteEstadoRegion());
        
        for(ElementoReporte er : ejecutivo.getElementoReportesDepartamentoEstado()){
            if(StringUtils.equals("AMAZONAS", er.getTipoConflicto())){
                ejecutivo.setAmazonasAct(er.getActivo());
                ejecutivo.setAmazonasLat(er.getLatente()) ;
            }
            if(StringUtils.equals("ANCASH", er.getTipoConflicto())){
                ejecutivo.setAncashAct(er.getActivo());
                ejecutivo.setAncashLat(er.getLatente());
            }
            if(StringUtils.equals("APURIMAC", er.getTipoConflicto())){
                ejecutivo.setApurimacAct(er.getActivo());
                ejecutivo.setApurimacLat(er.getLatente());
            }
            if(StringUtils.equals("AREQUIPA", er.getTipoConflicto())){
                ejecutivo.setArequipaAct(er.getActivo());
                ejecutivo.setArequipaLat(er.getLatente());
            }
            if(StringUtils.equals("AYACUCHO", er.getTipoConflicto())){
                ejecutivo.setAyacuchoAct(er.getActivo());
                ejecutivo.setAyacuchoLat(er.getLatente());
            }
            if(StringUtils.equals("CAJAMARCA", er.getTipoConflicto())){
                ejecutivo.setCajamarcaAct(er.getActivo());
                ejecutivo.setCajamarcaLat(er.getLatente());
            }
            if(StringUtils.equals("CUSCO", er.getTipoConflicto())){
                ejecutivo.setCuscoAct(er.getActivo());
                ejecutivo.setCuscoLat(er.getLatente());
            }
            if(StringUtils.equals("HUANCAVELICA", er.getTipoConflicto())){
                ejecutivo.setHuancavelicaAct(er.getActivo());
                ejecutivo.setHuancavelicaLat(er.getLatente());
            }
            if(StringUtils.equals("HUANUCO", er.getTipoConflicto())){
                ejecutivo.setHuanucoAct(er.getActivo());
                ejecutivo.setHuanucoLat(er.getLatente());
            }
            if(StringUtils.equals("ICA", er.getTipoConflicto())){
                ejecutivo.setIcaAct(er.getActivo());
                ejecutivo.setIcaLat(er.getLatente());
            }
            if(StringUtils.equals("JUNIN", er.getTipoConflicto())){
                ejecutivo.setJuninAct(er.getActivo());
                ejecutivo.setJuninLat(er.getLatente());
            }
            if(StringUtils.equals("LA LIBERTAD", er.getTipoConflicto())){
                ejecutivo.setLaLibertadAct(er.getActivo());
                ejecutivo.setLaLibertadLat(er.getLatente());
            }
            if(StringUtils.equals("LAMBAYEQUE", er.getTipoConflicto())){
                ejecutivo.setLambayequeAct(er.getActivo());
                ejecutivo.setLambayequeLat(er.getLatente());
            }
            if(StringUtils.equals("LIMA", er.getTipoConflicto())){
                ejecutivo.setLimaAct(er.getActivo());
                ejecutivo.setLimaLat(er.getLatente());
            }
            if(StringUtils.equals("LORETO", er.getTipoConflicto())){
                ejecutivo.setLoretoAct(er.getActivo());
                ejecutivo.setLoretoLat(er.getLatente());
            }
            if(StringUtils.equals("MADRE DE DIOS", er.getTipoConflicto())){
                ejecutivo.setMadreDeDiosAct(er.getActivo());
                ejecutivo.setMadreDeDiosLat(er.getLatente());
            }
            if(StringUtils.equals("MOQUEGUA", er.getTipoConflicto())){
                ejecutivo.setMoqueguaAct(er.getActivo());
                ejecutivo.setMoqueguaLat(er.getLatente());
            }
            if(StringUtils.equals("PASCO", er.getTipoConflicto())){
                ejecutivo.setPascoAct(er.getActivo());
                ejecutivo.setPascoLat(er.getLatente());
            }
            if(StringUtils.equals("PIURA", er.getTipoConflicto())){
                ejecutivo.setPiuraAct(er.getActivo());
                ejecutivo.setPiuraLat(er.getLatente());
            }
            if(StringUtils.equals("PUNO", er.getTipoConflicto())){
                ejecutivo.setPunoAct(er.getActivo());
                ejecutivo.setPunoLat(er.getLatente());
            }
            if(StringUtils.equals("SAN MARTIN", er.getTipoConflicto())){
                ejecutivo.setSanMartinAct(er.getActivo());
                ejecutivo.setSanMartinLat(er.getLatente());
            }
            if(StringUtils.equals("TACNA", er.getTipoConflicto())){
                ejecutivo.setTacnaAct(er.getActivo());
                ejecutivo.setTacnaLat(er.getLatente());
            }
            if(StringUtils.equals("TUMBES", er.getTipoConflicto())){
                ejecutivo.setTumbesAct(er.getActivo());
                ejecutivo.setTumbesLat(er.getLatente());
            }
            if(StringUtils.equals("CALLAO", er.getTipoConflicto())){
                ejecutivo.setCallaoAct(er.getActivo());
                ejecutivo.setCallaoLat(er.getLatente());
            }
            
            if(StringUtils.equals("UCAYALI", er.getTipoConflicto())){
                ejecutivo.setUcayaliAct(er.getActivo());
                ejecutivo.setUcayaliLat(er.getLatente());
            }
        }
        /****Gráfico N° 6:****/
        
        /****Cuadro N.° 10:****/
        /****Gráfico N° 8****/
        filtroReporte.setTipoTipologiaCaso("90");
        filtroReporte.setTipoReporte(2);
        List<ElementoResumenEjecutivo> nivelesParametros1 = reporteEjecutivoService.totalMensualSegunTipologiaCaso(filtroReporte);
        
        int totalTipo= 0;
        
        for(ElementoResumenEjecutivo ere : nivelesParametros1){
            totalTipo += ere.getValor();
        }
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
        /****Gráfico N° 8****/
        /****Cuadro N.° 10:****/
        
        /****SOCIOAMBIENTALES****/
        filtroReporte.setTipoTipologiaCaso("130");
        filtroReporte.setTipoReporte(3);
        List<ElementoResumenEjecutivo> nivelesParametros2 = reporteEjecutivoService.totalMensualSegunTipologiaCaso(filtroReporte);
        int totalSubTipo= 0;
        for(ElementoResumenEjecutivo ere : nivelesParametros2){
            totalSubTipo += ere.getValor();
        }
        List<ElementoNombreValor> listNivelSubTipo = new ArrayList<>();
        List<ElementoNombreValor> listNivelSubTipoGrafic = new ArrayList<>();
        ElementoNombreValor env3 = new ElementoNombreValor();
        env3.setNombre("Total");
        env3.setValor(totalSubTipo);
        env3.setPorcentaje("100.0%");
        listNivelSubTipo.add(env3);
        
        for(ElementoResumenEjecutivo ere : nivelesParametros2){
            ElementoNombreValor env = new ElementoNombreValor();
            env.setNombre(ere.getNombre());
            env.setValor(ere.getValor());
            env.setPorcentaje(cambiarPorcentaje(totalSubTipo, ere.getValor()));
            listNivelSubTipo.add(env);
            listNivelSubTipoGrafic.add(env);
        }
        ejecutivo.setListaNivelSubTipo(listNivelSubTipo);
        ejecutivo.setListaNivelSubTipoGrafico(listNivelSubTipoGrafic);
        /****SOCIOAMBIENTALES****/
        
        /****Casos activos detalle****/
        List<ReporteSimcoCaso> listaCasosActivosTotales;
        listaCasosActivosTotales = reporteSimcoCasoService.listaCasosActivosTotales(filtroReporte);
        for(ReporteSimcoCaso rsc : listaCasosActivosTotales){
            filtroReporte.setCodigoCaso(rsc.getCodigoCaso());
            HashMap<Integer,String> map = reporteEjecutivoService.actoresPorCodigoCasoString(filtroReporte);
            for(Map.Entry m : map.entrySet()){
                if(Integer.parseInt(m.getKey().toString())  == 1)
                    rsc.setActorPrimario(m.getValue().toString());
                if(Integer.parseInt(m.getKey().toString())  == 2)
                    rsc.setActorSecundario(m.getValue().toString());
                if(Integer.parseInt(m.getKey().toString())  == 3)
                    rsc.setActorTerciario(m.getValue().toString());
            }
            List<ElementoNombreValor> envs = new ArrayList<>();
            ElementoNombreValor env;
            env = new ElementoNombreValor();
            env.setNombre(rsc.getNombreCaso() == null? "Nombre:" : "Nombre: "+rsc.getNombreCaso());
            envs.add(env);
            env = new ElementoNombreValor();
            env.setNombre(rsc.getTipologia() == null? "Tipo:" : "Tipo: "+rsc.getTipologia());
            envs.add(env);
            env = new ElementoNombreValor();
            env.setNombre(rsc.getFase() == null? "Fase:" : "Fase: "+rsc.getFase());
            envs.add(env);
            env = new ElementoNombreValor();
            env.setNombre(rsc.getDescripcionCaso() == null? "Descripción:" : "Descripción: "+rsc.getDescripcionCaso());
            envs.add(env);
            env = new ElementoNombreValor();
            env.setNombre(rsc.getRegionPrincial() == null? "Región:" : "Región: "+rsc.getRegionPrincial());
            envs.add(env);
            env = new ElementoNombreValor();
            env.setNombre(rsc.getProvinciaPrincial() == null? "Provincia:" : "Provincia: "+rsc.getProvinciaPrincial());
            envs.add(env);
            env = new ElementoNombreValor();
            env.setNombre(rsc.getDistritoPrincial() == null? "Distrito:" : "Distrito: "+rsc.getDistritoPrincial());
            envs.add(env);
            env = new ElementoNombreValor();
            env.setNombre(rsc.getActorPrimario() == null? "Actores primarios:" : "Actores primarios: "+rsc.getActorPrimario());
            envs.add(env);
            env = new ElementoNombreValor();
            env.setNombre(rsc.getActorSecundario() == null? "Actores secundarios:" : "Actores secundarios: "+rsc.getActorSecundario());
            envs.add(env);
            env = new ElementoNombreValor();
            env.setNombre(rsc.getActorTerciario() == null? "Actores terciarios:" : "Actores terciarios: "+rsc.getActorTerciario());
            envs.add(env);
            rsc.setDatosCasos(envs);
            rsc.setRutaReporte(retornaRutaPath().concat("/jasper/"));
            
            rsc.setActividades(actividadService.actividadxCodigoCasoBuscarTotal(rsc.getCodigoCaso()));
        }
        ejecutivo.setListaCasosActivosTotales(listaCasosActivosTotales);
        /****Casos activos detalle****/
         
        /****lista casos reactivados****/
        List<ReporteSimcoCaso> listaCasosReactivadosPorMes = new ArrayList<>();
        filtroReporte.setTipoEstadoCaso(EstadoCasoType.ACTIVO.getKey());
        List<Caso> lista = reporteEjecutivoService.listadoCasosEstadoMes(filtroReporte);
        for(Caso c : lista){
            filtroReporte.setCodigoCaso(c.getCodigo());
            List<Caso> listaInactivos =  reporteEjecutivoService.listaCasosAntesDeAprobado(filtroReporte);
            for(Caso c1 : listaInactivos){
                if(!StringUtils.equals(c1.getTipoEstado(), EstadoCasoType.ACTIVO.getKey())){
                    if(StringUtils.equals(c1.getTipoEstado(),EstadoCasoType.LATENTE.getKey())){
                        filtroReporte.setCodigoCaso(c1.getCodigo());
                        listaCasosReactivadosPorMes.add(reporteEjecutivoService.casoActivoTotal(filtroReporte));
                        filtroReporte.setCodigoCaso(null);
                    }
                    break;
                }
            }
        }
        
        int contad1 = 0;
        for(ReporteSimcoCaso rsc : listaCasosReactivadosPorMes){
            contad1++;
            rsc.setContador(contad1);
        }
        ejecutivo.setListaCasosReactivadosPorMes(listaCasosReactivadosPorMes);
        /****lista casos reactivados****/
        
        /****lista casos latentes****/
        List<ReporteSimcoCaso> listaCasosLatentesPorMes;
        listaCasosLatentesPorMes = reporteEjecutivoService.casoaLatentesLista(filtroReporte);
        int contad2 = 0;
        for(ReporteSimcoCaso rsc : listaCasosLatentesPorMes){
            contad2++;
            rsc.setContador(contad2);
        }
        ejecutivo.setListaCasosLatentesPorMes(listaCasosLatentesPorMes);
        /****lista casos latentes****/
        
        
        
        
        /****lista casos de activo a latente****/
        List<ReporteSimcoCaso> listaCasosActivoALatentes = new ArrayList<>();
        filtroReporte.setTipoEstadoCaso(EstadoCasoType.LATENTE.getKey());
        List<Caso> listaActivids = reporteEjecutivoService.listadoCasosEstadoMes(filtroReporte);
        for(Caso c : listaActivids){
            filtroReporte.setCodigoCaso(c.getCodigo());
            List<Caso> listaInactivos =  reporteEjecutivoService.listaCasosAntesDeAprobado(filtroReporte);
            for(Caso c1 : listaInactivos){
                if(!StringUtils.equals(c1.getTipoEstado(), EstadoCasoType.ACTIVO.getKey())){
                    if(StringUtils.equals(c1.getTipoEstado(),EstadoCasoType.LATENTE.getKey())){
                        filtroReporte.setCodigoCaso(c1.getCodigo());
                        listaCasosActivoALatentes.add(reporteEjecutivoService.casoActivoTotal(filtroReporte));
                        filtroReporte.setCodigoCaso(null);
                    }
                    break;
                }
            }
        }
        
        int contad3 = 0;
        for(ReporteSimcoCaso rsc : listaCasosActivoALatentes){
            contad3++;
            rsc.setContador(contad3);
        }
        ejecutivo.setListaCasosActivoALatentes(listaCasosActivoALatentes);
        /****lista casos de activo a latente****/
        ejecutivo.setImagePath001(retornaRutaPath().concat("/images/"));
        listaResumenEjecutivo.add(ejecutivo);
        if(tipo == 1)
            generarReportePublicoMensual();
        if(tipo == 2)
            generarReportePublicoMensualWord();
        return true;
    }
    
    public List<ElementoReporte> generarReporteEstadoRegion() {
        FiltroReporte fr = new FiltroReporte();
        fr.setCodigoReporte(filtroReporte.getCodigoReporte());
        fr.setAnhoMesString(filtroReporte.getAnhoMesString());
        fr.setClasificacion("0");
        List<Caso> listaCasosReporte1 = reporteEjecutivoService.reporteCaso(fr);
        List<Catalogo> catalogos = catalogoService.parametroPorPadre(290);
        elementoReportes = new ArrayList<>();
        ElementoReporte er1 = new ElementoReporte();
        er1.setTipoConflicto("Total");
        int activoSum = 0;
        int latenteSum = 0;
        for (Catalogo cata : catalogos) {
            List<Caso> listaCasosFl = null;
            listaCasosFl = filtrarCasosPorRegion    (listaCasosReporte1, cata.getValorParametro());    
            int activo = 0;
            int latente = 0;
            for (Caso cs : listaCasosFl) {
                if (StringUtils.equals(cs.getTipoEstado(), "04")) {
                    activo++;
                }
                if (StringUtils.equals(cs.getTipoEstado(), "05")) {
                    latente++;
                }
            }
            activoSum = activoSum + activo;
            latenteSum = latenteSum + latente;
            ElementoReporte er = new ElementoReporte(cata.getNombreParametro(), null, null, null, null, null, activo, latente, null, null);

            elementoReportes.add(er);
        }
        er1.setActivo(activoSum);
        er1.setLatente(latenteSum);
        er1.setTotal(activoSum + latenteSum);
        elementoReportes.add(0, er1);

        for (ElementoReporte ers : elementoReportes) {
            Integer tot =  ers.getActivo() + ers.getLatente();
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
        fr.setCodigoReporte(filtroReporte.getCodigoReporte());
        fr.setAnhoMesString(filtroReporte.getAnhoMesString());
        fr.setClasificacion("0");
        listaCasosReporte1 = reporteEjecutivoService.reporteCaso(fr);
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
        filtroReporteCarga = new FiltroReporte();
        listaAnhos = reporteService.listadoAnhoCaso();
        cargarMeses();
        return "reportePublic";
    }
    
    private void usuarioSession() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
            usuarioSession = loginController.getUsuarioSesion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean cargarReporteMensual() {
        if(StringUtils.equals(filtroReporteCarga.getAnhos(), "0") || StringUtils.equals(filtroReporteCarga.getMes(), "0")){
            msg.messageAlert("Debe ingresar el año y el mes de la carga", null);
            return false;
        }
        /*RegistroCarga registroCarga = new RegistroCarga();
        registroCarga.setFecha(new Date());
        registroCarga.setUltimoMes("SI");
        registroCarga.setUltimoAnho("SI");
        //compararUltimoMes();
        cargarCodigoCarga(registroCarga);*/
        usuarioSession();
        filtroReporteCarga.setUsuarioRegistro(usuarioSession.getCodigo());
        reporteEjecutivoService.cargaCasoMes(filtroReporteCarga);
        msg.messageInfo("Se ejecutó la carga de datos", null);
        return true;
        //reporteService.cargaCasoMes(registroCarga.getId());
    }
    
    public void initJasperReporteEjecutivo(int tipo) throws JRException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaResumenEjecutivo);
        if(tipo == 1)
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/reportePublicResumenEjecutivo.jasper"),new HashMap(), beanCollectionDataSource);
        if(tipo == 2)
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/reportePublicResumenEjecutivoWord.jasper"),new HashMap(), beanCollectionDataSource);
     }
    
    public void generarReportePublicoMensual() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperReporteEjecutivo(1);
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
    
    @SuppressWarnings("static-access")
    public void generarReportePublicoMensualWord() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperReporteEjecutivo(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_reporte.doc");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
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
        listaCasosReporte = reporteEjecutivoService.reporteCaso(filtroReporte);
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
        List<Caso> list = new ArrayList<>();
        String codigoDepartamento = String.format("%2s", tipo).replace(' ', '0');
        for(Caso c : casos){
            if(c.getIdDepartamento() != null){
                if(StringUtils.equals(c.getIdDepartamento(), codigoDepartamento)){
                    list.add(c);
                }
            }      
        }
        /*Filter<Caso, String> filter = new Filter<Caso, String>() {
            public boolean isMatched(Caso object, String text) {
                if(object.getIdDepartamento() != null){
                    String entero = object.getIdDepartamento();
                    return entero.startsWith(String.valueOf(text));
                }
                return false;
            }
        };
        List<Caso> sortCasos = new FilterList().filterList(casos, filter, tipo);*/
        return list;
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

    public FiltroReporte getFiltroReporteCarga() {
        return filtroReporteCarga;
    }

    public void setFiltroReporteCarga(FiltroReporte filtroReporteCarga) {
        this.filtroReporteCarga = filtroReporteCarga;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

}