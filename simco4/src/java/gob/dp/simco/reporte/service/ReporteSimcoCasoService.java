/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.service;

import gob.dp.simco.reporte.entity.ReporteSimcoCaso;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ReporteSimcoCasoService {
    
    public List<ReporteSimcoCaso> reporteCasos(ReporteSimcoCaso reporteSimco);
    
    public Integer cantidadAcuerdosCaso(long idCaso);
    
    public Integer cantidadEmpresaMineraCaso(String codigoCaso);
    
    public Integer cantidadMuertosHeridos(String codigoCaso, String estado, String estadoTipo);
    
}
