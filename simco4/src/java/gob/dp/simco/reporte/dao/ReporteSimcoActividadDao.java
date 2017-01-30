/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.dao;

import gob.dp.simco.reporte.entity.ReporteSimcoActividad;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ReporteSimcoActividadDao {
    
    public List<ReporteSimcoActividad> reporteActividad(ReporteSimcoActividad reporteSimcoActividad);
    
}
