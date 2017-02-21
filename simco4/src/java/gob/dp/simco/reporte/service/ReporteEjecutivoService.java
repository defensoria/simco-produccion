/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.service;

import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.reporte.entity.ElementoResumenEjecutivo;
import gob.dp.simco.reporte.entity.FiltroReporte;
import gob.dp.simco.reporte.entity.ReporteSimcoCaso;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ReporteEjecutivoService {
    
    public void cargaCasoMes(FiltroReporte filtro);
    
    public boolean existeReporteMes(FiltroReporte filtro);
    
    public Integer codigoReporteMes(FiltroReporte filtro);
    
    public Integer totalCasosRegistrados(FiltroReporte filtro); 
    
    public Integer totalCasosActivos(FiltroReporte filtro);
    
    public Integer totalCasosLatentes(FiltroReporte filtro);
    
    public Integer totalCasosActivosLatentes(FiltroReporte filtroReporte);
            
    public Integer totalCasosLatentesObservacion(FiltroReporte filtroReporte);    
    
    public Integer totalGeneralCasosDialogo(FiltroReporte filtroReporte);
    
    public Integer totalGeneralCasosActivo(FiltroReporte filtroReporte);
    
    public Integer totalCasosDialogo(FiltroReporte filtroReporte);
    
    public Integer totalCasosDialogoNegociacion(FiltroReporte filtroReporte);  
    
    public Integer totalCasosDialogoReuniones(FiltroReporte filtroReporte);  
    
    public Integer totalCasosDialogoEspacioDialogo(FiltroReporte filtroReporte);  
    
    public Integer totalGeneralCasosReunionesPreparatorias(FiltroReporte filtroReporte);
    
    public Integer totalGeneralCasosParticipacionDefensoria(FiltroReporte filtroReporte);
    
    public Integer totalGeneralCasosEchoViolencia(FiltroReporte filtroReporte);  
    
    public Integer totalCasosRegistradosMes(FiltroReporte filtroReporte);
    
    public Integer totalGeneralCasosAccionesProtesta(FiltroReporte filtroReporte);
    
    public Integer totalGeneralCasosActuacionDefensorial(FiltroReporte filtroReporte); 
    
    public Integer totalGeneralCasosActuacionDefensorialSupervisionPreventiva(FiltroReporte filtroReporte); 
    
    public Integer totalGeneralCasosActuacionDefensorialIntermediaciones(FiltroReporte filtroReporte); 
    
    public Integer totalGeneralCasosActuacionDefensorialAccionHumanitaria(FiltroReporte filtroReporte); 
    
    public Integer totalGeneralCasosActuacionDefensorialDefensaLegal(FiltroReporte filtroReporte); 
    
    public Integer totalGeneralCasosFaceEscalamiento(FiltroReporte filtroReporte);
    
    public Integer totalGeneralCasosFaceTemprana(FiltroReporte filtroReporte);
    
    public Integer totalGeneralCasosFaceDesescalamiento(FiltroReporte filtroReporte);
    
    public Integer totalGeneralCasosFaceCrisis(FiltroReporte filtroReporte);
    
    public Integer totalCasosResueltos(FiltroReporte filtroReporte);
            
    public Integer totalCasosPropuestos(FiltroReporte filtroReporte);
    
    public String cadenaNombreCasosResueltos(FiltroReporte filtroReporte);
            
    public String cadenaNombreCasosPropuestos(FiltroReporte filtroReporte);
    
    public List<ElementoResumenEjecutivo> totalMensualCasosRegistrados(FiltroReporte filtroReporte);
    
    public List<ElementoResumenEjecutivo> totalMensualCasosActivos(FiltroReporte filtroReporte);
    
    public List<ElementoResumenEjecutivo> totalMensualSegunTipologiaCaso(FiltroReporte filtroReporte);
    
    public Integer totalCasosResueltoMes(FiltroReporte filtroReporte);
    
    public Integer totalCasosACVictimaViolencia(FiltroReporte filtroReporte);
    
    public Integer totalCasosACVictimaViolenciaMes(FiltroReporte filtroReporte);
    
    public Integer totalCasosACAccionesProtestaMes(FiltroReporte filtroReporte);
    
    public Integer totalCasosAD(FiltroReporte filtroReporte);
    
    public Integer totalCasosADMes(FiltroReporte filtroReporte);
    
    public Integer totalActividadAccionesHumanitariasMes(FiltroReporte filtroReporte);
            
    public Integer totalActividadIntermediacionMes(FiltroReporte filtroReporte);
    
    public Integer totalActividadSupervisionPreventivaMes(FiltroReporte filtroReporte);
    
    public Integer totalActividadAccionesDefensaLegalMes(FiltroReporte filtroReporte);
    
    public HashMap<Integer,String> actoresPorCodigoCasoString(FiltroReporte filtroReporte);
    
    public List<ElementoResumenEjecutivo> totalMensualSegunTipologiaCasoDialogo(FiltroReporte filtroReporte);
    
    public Integer totalCasosSegunTipologiaCasoDialogo(FiltroReporte filtroReporte);
    
    public List<Caso> listadoCasosEstadoMes(FiltroReporte filtroReporte);
    
    public List<Caso> listaCasosAntesDeAprobado(FiltroReporte filtroReporte);
    
    public List<Caso> reporteCaso(FiltroReporte filtroReporte);
    
    public ReporteSimcoCaso casoActivoTotal(FiltroReporte filtroReporte);
    
    public List<ReporteSimcoCaso> casoaLatentesLista(FiltroReporte filtroReporte);
    
}
