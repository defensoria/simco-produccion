/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.dao;

import gob.dp.simco.reporte.entity.ElementoResumenEjecutivo;
import gob.dp.simco.reporte.entity.FiltroReporte;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ReporteEjecutivoDao {
    
    public Integer totalCasosRegistrados(); 
    
    public Integer totalCasosActivos();
    
    public Integer totalCasosLatentes();
    
    public Integer totalCasosActivosLatentes(FiltroReporte filtroReporte);
            
    public Integer totalCasosLatentesObservacion(FiltroReporte filtroReporte);  
    
    public Integer totalGeneralCasosDialogo(FiltroReporte filtroReporte);  
    
    public Integer totalGeneralCasosActivo(FiltroReporte filtroReporte);  
    
    public Integer totalCasosDialogo();  
    
    public Integer totalCasosDialogoNegociacion();  
    
    public Integer totalCasosDialogoReuniones();  
    
    public Integer totalCasosDialogoEspacioDialogo();  
    
    public Integer totalCasosRegistradosMes(FiltroReporte filtroReporte);        
            
    public Integer totalGeneralCasosReunionesPreparatorias(FiltroReporte filtroReporte);  
    
    public Integer totalGeneralCasosParticipacionDefensoria(FiltroReporte filtroReporte);  
    
    public Integer totalGeneralCasosEchoViolencia(FiltroReporte filtroReporte); 
    
    
    
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
    
    public List<ElementoResumenEjecutivo> cadenaNombreCasosResueltos(FiltroReporte filtroReporte);
            
    public List<ElementoResumenEjecutivo> cadenaNombreCasosPropuestos(FiltroReporte filtroReporte);
    
    public List<ElementoResumenEjecutivo> totalMensualCasosRegistrados(FiltroReporte filtroReporte);
    
    public List<ElementoResumenEjecutivo> totalMensualCasosActivos(FiltroReporte filtroReporte);
    
    public List<ElementoResumenEjecutivo> totalMensualSegunTipologiaCaso(FiltroReporte filtroReporte);
    
    public Integer totalCasosResueltoMes(FiltroReporte filtroReporte);
    
    public Integer totalCasosACVictimaViolencia();
    
    public Integer totalCasosACVictimaViolenciaMes(FiltroReporte filtroReporte);
    
    public Integer totalCasosACAccionesProtestaMes(FiltroReporte filtroReporte);
    
    public Integer totalCasosAD();
    
    public Integer totalCasosADMes(FiltroReporte filtroReporte);
    
    public Integer totalActividadAccionesDefensaLegalMes(FiltroReporte filtroReporte);
    
    public Integer totalActividadAccionesHumanitariasMes(FiltroReporte filtroReporte);
            
    public Integer totalActividadIntermediacionMes(FiltroReporte filtroReporte);
    
    public Integer totalActividadSupervisionPreventivaMes(FiltroReporte filtroReporte);
    
}
