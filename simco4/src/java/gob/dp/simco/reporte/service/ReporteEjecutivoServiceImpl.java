/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.service;

import gob.dp.simco.registro.dao.ActorDao;
import gob.dp.simco.registro.dao.CasoDao;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.reporte.dao.ReporteEjecutivoDao;
import gob.dp.simco.reporte.dao.ReporteSimcoCasoDao;
import gob.dp.simco.reporte.entity.ElementoResumenEjecutivo;
import gob.dp.simco.reporte.entity.FiltroReporte;
import gob.dp.simco.reporte.entity.ReporteSimcoCaso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
    
    @Autowired
    private CasoDao casoDao;
    
    @Autowired
    private ReporteSimcoCasoDao reporteSimcoCasoDao;
    
    @Override
    public void cargaCasoMes(FiltroReporte filtro) {
        reporteEjecutivoDao.cargaCasoMes(filtro);
    }
    
    @Override
    public boolean existeReporteMes(FiltroReporte filtro) {
        return reporteEjecutivoDao.existeReporteMes(filtro) > 0;
    }
    
    @Override
    public Integer codigoReporteMes(FiltroReporte filtro) {
        return reporteEjecutivoDao.codigoReporteMes(filtro);
    }

    @Override
    public Integer totalCasosRegistrados(FiltroReporte filtro) {
        return reporteEjecutivoDao.totalCasosRegistrados(filtro);
    }

    @Override
    public Integer totalCasosActivos(FiltroReporte filtro) {
        return reporteEjecutivoDao.totalCasosActivos(filtro);
    }

    @Override
    public Integer totalCasosLatentes(FiltroReporte filtro) {
        return reporteEjecutivoDao.totalCasosLatentes(filtro);
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
    public Integer totalCasosDialogo(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosDialogo(filtroReporte);
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
    public Integer totalCasosDialogoNegociacion(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosDialogoNegociacion(filtroReporte);
    }

    @Override
    public Integer totalCasosDialogoReuniones(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosDialogoReuniones(filtroReporte);
    }

    @Override
    public Integer totalCasosResueltoMes(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosResueltoMes(filtroReporte);
    }

    @Override
    public Integer totalCasosDialogoEspacioDialogo(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosDialogoEspacioDialogo(filtroReporte);
    }

    @Override
    public Integer totalCasosACVictimaViolencia(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosACVictimaViolencia(filtroReporte);
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
    public Integer totalCasosAD(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosAD(filtroReporte);
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
    public HashMap<Integer,String> actoresPorCodigoCasoString(FiltroReporte filtroReporte) {
        List<Actor> list = actorDao.actoresPorCodigoCaso(filtroReporte);
        StringBuilder primario = new StringBuilder();
        StringBuilder secundario = new StringBuilder();
        StringBuilder terciario = new StringBuilder();
        primario.append(" ");
        secundario.append(" ");
        terciario.append(" ");
        for(Actor a : list){
            if(StringUtils.equals(a.getTipoActoCaso(), "01")){
                if(StringUtils.equals(a.getTipoGeneral(), "PE")){
                    if(StringUtils.isNotBlank(a.getNombre()))
                        primario.append(" "+a.getNombre());
                    if(StringUtils.isNotBlank(a.getApellidoPat()))
                        primario.append(" "+a.getApellidoPat());
                    if(StringUtils.isNotBlank(a.getApellidoMat()))
                        primario.append(" "+a.getApellidoMat());
                    primario.append(", ");
                }else{
                    primario.append(" "+a.getNombre()+",");
                }
            }
            if(StringUtils.equals(a.getTipoActoCaso(), "02")){
                if(StringUtils.equals(a.getTipoGeneral(), "PE")){
                    if(StringUtils.isNotBlank(a.getNombre()))
                        primario.append(" "+a.getNombre());
                    if(StringUtils.isNotBlank(a.getApellidoPat()))
                        primario.append(" "+a.getApellidoPat());
                    if(StringUtils.isNotBlank(a.getApellidoMat()))
                        primario.append(" "+a.getApellidoMat());
                    primario.append(", ");
                }else{
                    secundario.append(" "+a.getNombre()+",");
                }
            }
            if(StringUtils.equals(a.getTipoActoCaso(), "03")){
                if(StringUtils.equals(a.getTipoGeneral(), "PE")){
                    if(StringUtils.isNotBlank(a.getNombre()))
                        primario.append(" "+a.getNombre());
                    if(StringUtils.isNotBlank(a.getApellidoPat()))
                        primario.append(" "+a.getApellidoPat());
                    if(StringUtils.isNotBlank(a.getApellidoMat()))
                        primario.append(" "+a.getApellidoMat());
                    primario.append(", ");
                }else{
                    terciario.append(" "+a.getNombre()+",");
                }
            }
        }
        HashMap<Integer,String> mapActor=new HashMap<>();  
        mapActor.put(1, primario.toString().substring(0, primario.toString().length()-1));
        mapActor.put(2, secundario.toString().substring(0, secundario.toString().length()-1));
        mapActor.put(3, terciario.toString().substring(0, terciario.toString().length()-1));
        return mapActor;
    }

    @Override
    public List<ElementoResumenEjecutivo> totalMensualSegunTipologiaCasoDialogo(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalMensualSegunTipologiaCasoDialogo(filtroReporte);
    }

    @Override
    public Integer totalCasosSegunTipologiaCasoDialogo(FiltroReporte filtroReporte) {
        return reporteEjecutivoDao.totalCasosSegunTipologiaCasoDialogo(filtroReporte);
    }
    
    @Override
    public List<Caso> listadoCasosEstadoMes(FiltroReporte filtroReporte) {
        return casoDao.listadoCasosEstadoMes(filtroReporte);
    }
    
    @Override
    public List<Caso> listaCasosAntesDeAprobado(FiltroReporte filtroReporte) {
        return casoDao.listaCasosAntesDeAprobado(filtroReporte);
    }
    
    @Override
    public List<Caso> reporteCaso(FiltroReporte filtroReporte) {
        List<Integer> listDeparamentos = new ArrayList<>();
        if(StringUtils.isNotBlank(filtroReporte.getDepartamento())){
            String[] adArray = filtroReporte.getDepartamento().split(",");
            for (String adArray1 : adArray) {
                listDeparamentos.add(Integer.parseInt(adArray1));
            }
            filtroReporte.setListaDepartamentos(listDeparamentos);
            filtroReporte.setListaDepartamentosSize(listDeparamentos.size());
        }else{
            filtroReporte.setListaDepartamentos(listDeparamentos);
            filtroReporte.setListaDepartamentosSize(null);
        }
        
        List<String> listAnhos = new ArrayList<>();
        if(StringUtils.isNotBlank(filtroReporte.getAnhos())){
            String[] adArray = filtroReporte.getAnhos().split(",");
            for (String adArray1 : adArray) {
                listAnhos.add(adArray1);
            }
            filtroReporte.setListaAnhos(listAnhos);
            filtroReporte.setListaAnhosSize(listAnhos.size());
        }else{
            filtroReporte.setListaAnhos(listAnhos);
            filtroReporte.setListaAnhosSize(null);
        }
        
        List<String> listMes = new ArrayList<>();
        if(StringUtils.isNotBlank(filtroReporte.getMes())){
            String[] adArray = filtroReporte.getMes().split(",");
            for (String adArray1 : adArray) {
                listMes.add(adArray1);
            }
            filtroReporte.setListaMeses(listMes);
            filtroReporte.setListaMesesSize(listMes.size());
        }else{
            filtroReporte.setListaMeses(listMes);
            filtroReporte.setListaMesesSize(null);
        }
        
        return casoDao.reporteCaso(filtroReporte);
    }

    @Override
    public ReporteSimcoCaso casoActivoTotal(FiltroReporte filtroReporte) {
        return reporteSimcoCasoDao.casoActivoTotal(filtroReporte);
    }

    @Override
    public List<ReporteSimcoCaso> casoaLatentesLista(FiltroReporte filtroReporte) {
        return reporteSimcoCasoDao.casoaLatentesLista(filtroReporte);
    }

}
