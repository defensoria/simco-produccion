/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.service;

import gob.dp.simco.registro.dao.ActorDao;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.reporte.dao.ReporteEjecutivoDao;
import gob.dp.simco.reporte.entity.ElementoResumenEjecutivo;
import gob.dp.simco.reporte.entity.FiltroReporte;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ReporteEjecutivoServiceImpl implements ReporteEjecutivoService{
    
    @Autowired
    private ReporteEjecutivoDao reporteEjecutivoDao;
    
    @Autowired
    private ActorDao actorDao;

    @Override
    public Integer totalCasosRegistrados() {
        return reporteEjecutivoDao.totalCasosRegistrados();
    }

    @Override
    public Integer totalCasosActivos() {
        return reporteEjecutivoDao.totalCasosActivos();
    }

    @Override
    public Integer totalCasosLatentes() {
        return reporteEjecutivoDao.totalCasosLatentes();
    }

    @Override
    public Integer totalCasosActivosLatentes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosActivosLatentes(filtroReporte);
    }

    @Override
    public Integer totalCasosLatentesObservacion(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosLatentesObservacion(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosDialogo(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosDialogo(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActivo(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosActivo(filtroReporte);
    }

    @Override
    public Integer totalCasosDialogo() {
        return reporteEjecutivoDao.totalCasosDialogo();
    }

    @Override
    public Integer totalGeneralCasosReunionesPreparatorias(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosReunionesPreparatorias(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosParticipacionDefensoria(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosParticipacionDefensoria(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosEchoViolencia(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosEchoViolencia(filtroReporte);
    }

    @Override
    public Integer totalCasosRegistradosMes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosRegistradosMes(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosAccionesProtesta(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosAccionesProtesta(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActuacionDefensorial(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosActuacionDefensorial(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActuacionDefensorialSupervisionPreventiva(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosActuacionDefensorialSupervisionPreventiva(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActuacionDefensorialIntermediaciones(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosActuacionDefensorialIntermediaciones(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActuacionDefensorialAccionHumanitaria(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosActuacionDefensorialAccionHumanitaria(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActuacionDefensorialDefensaLegal(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosActuacionDefensorialDefensaLegal(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosFaceEscalamiento(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosFaceEscalamiento(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosFaceTemprana(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosFaceTemprana(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosFaceDesescalamiento(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosFaceDesescalamiento(filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosFaceCrisis(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalGeneralCasosFaceCrisis(filtroReporte);
    }

    @Override
    public Integer totalCasosResueltos(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosResueltos(filtroReporte);
    }

    @Override
    public Integer totalCasosPropuestos(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosPropuestos(filtroReporte);
    }

    @Override
    public String cadenaNombreCasosResueltos(FiltroReporte filtroReporte) {
        List<ElementoResumenEjecutivo> list = reporteEjecutivoDao.cadenaNombreCasosResueltos(filtroReporte);
        StringBuilder cadenaNombre = new StringBuilder();
        for(ElementoResumenEjecutivo ere : list){
            cadenaNombre.append(ere.getCadenaNombreCaso()+", ");
        }
        String cadenaNueva = cadenaNombre.substring(0, cadenaNombre.length()-2); 
        return cadenaNueva.toString();
    }

    @Override
    public String cadenaNombreCasosPropuestos(FiltroReporte filtroReporte) {
        List<ElementoResumenEjecutivo> list =  reporteEjecutivoDao.cadenaNombreCasosPropuestos(filtroReporte);
        StringBuilder cadenaNombre = new StringBuilder();
        for(ElementoResumenEjecutivo ere : list){
            cadenaNombre.append(ere.getCadenaNombreCaso()+", ");
        }
        String cadenaNueva = cadenaNombre.substring(0, cadenaNombre.length()-2); 
        return cadenaNueva.toString();
    }

    @Override
    public List<ElementoResumenEjecutivo> totalMensualCasosRegistrados(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalMensualCasosRegistrados(filtroReporte);
    }

    @Override
    public List<ElementoResumenEjecutivo> totalMensualCasosActivos(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalMensualCasosActivos(filtroReporte);
    }

    @Override
    public List<ElementoResumenEjecutivo> totalMensualSegunTipologiaCaso(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalMensualSegunTipologiaCaso(filtroReporte);
    }

    

    @Override
    public Integer totalCasosDialogoNegociacion() {
        return reporteEjecutivoDao.totalCasosDialogoNegociacion();
    }

    @Override
    public Integer totalCasosDialogoReuniones() {
        return reporteEjecutivoDao.totalCasosDialogoReuniones();
    }

    @Override
    public Integer totalCasosResueltoMes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosResueltoMes(filtroReporte);
    }

    @Override
    public Integer totalCasosDialogoEspacioDialogo() {
        return reporteEjecutivoDao.totalCasosDialogoEspacioDialogo();
    }

    @Override
    public Integer totalCasosACVictimaViolencia() {
        return reporteEjecutivoDao.totalCasosACVictimaViolencia();
    }

    @Override
    public Integer totalCasosACVictimaViolenciaMes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosACVictimaViolenciaMes(filtroReporte);
    }

    @Override
    public Integer totalCasosACAccionesProtestaMes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosACAccionesProtestaMes(filtroReporte);
    }

    @Override
    public Integer totalCasosAD() {
        return reporteEjecutivoDao.totalCasosAD();
    }

    @Override
    public Integer totalCasosADMes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosADMes(filtroReporte);
    }

    @Override
    public Integer totalActividadAccionesHumanitariasMes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalActividadAccionesHumanitariasMes(filtroReporte);
    }

    @Override
    public Integer totalActividadIntermediacionMes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalActividadIntermediacionMes(filtroReporte);
    }

    @Override
    public Integer totalActividadSupervisionPreventivaMes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalActividadSupervisionPreventivaMes(filtroReporte);
    }

    @Override
    public Integer totalActividadAccionesDefensaLegalMes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalActividadAccionesDefensaLegalMes(filtroReporte);
    }

    @Override
    public HashMap<Integer,String> actoresPorCodigoCasoString(String codigoCaso) {
        List<Actor> list = actorDao.actoresPorCodigoCaso(codigoCaso);
        StringBuilder primario = new StringBuilder();
        StringBuilder secundario = new StringBuilder();
        StringBuilder terciario = new StringBuilder();
        int count = 0;
        for(Actor a : list){
            if(StringUtils.equals(a.getTipoActoCaso(), "01")){
                if(count > 0) primario.append(", ");
                if(StringUtils.equals(a.getTipoGeneral(), "PE")){
                    primario.append(a.getNombre()+" "+a.getApellidoPat()+" "+a.getApellidoMat());
                }else{
                    primario.append(a.getNombre());
                }
            }
            if(StringUtils.equals(a.getTipoActoCaso(), "02")){
                if(count > 0) secundario.append(", ");
                if(StringUtils.equals(a.getTipoGeneral(), "PE")){
                    secundario.append(a.getNombre()+" "+a.getApellidoPat()+" "+a.getApellidoMat());
                }else{
                    secundario.append(a.getNombre());
                }
            }
            if(StringUtils.equals(a.getTipoActoCaso(), "03")){
                if(count > 0) terciario.append(", ");
                if(StringUtils.equals(a.getTipoGeneral(), "PE")){
                    terciario.append(a.getNombre()+" "+a.getApellidoPat()+" "+a.getApellidoMat());
                }else{
                    terciario.append(a.getNombre());
                }
            }
        }
        count++;
        HashMap<Integer,String> mapActor=new HashMap<>();  
        mapActor.put(1, primario.toString());
        mapActor.put(2, secundario.toString());
        mapActor.put(3, terciario.toString());
        return mapActor;
    }

    
}
