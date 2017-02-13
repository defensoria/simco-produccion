/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.dao;

import gob.dp.simco.reporte.entity.FiltroReporte;
import gob.dp.simco.reporte.entity.ReporteSimcoCaso;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ReporteSimcoCasoDao {
    
    public List<ReporteSimcoCaso> reporteCasos(ReporteSimcoCaso reporteSimco);
    
    public Integer cantidadAcuerdosCaso(long idCaso);
    
    public Integer cantidadEmpresaMineraCaso(String codigoCaso);
    
    public Integer cantidadMuertosPNP(String codigoCaso);
    
    public Integer cantidadMuertosCiviles(String codigoCaso);
    
    public Integer cantidadMuertosFFAA(String codigoCaso);
    
    public Integer cantidadHeridosPNP(String codigoCaso);
    
    public Integer cantidadHeridosCiviles(String codigoCaso);
    
    public Integer cantidadHeridosFFAA(String codigoCaso);
    
    
    /*REPORTE GENRAL*/
    public List<ReporteSimcoCaso> listaCasosNuevosPorMes(FiltroReporte filtroReporte);
    
    public List<ReporteSimcoCaso> listaCasosResueltosPorMes(FiltroReporte filtroReporte);
    
    public List<ReporteSimcoCaso> listaCasosActivosTotales();
    
    
}
