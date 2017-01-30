/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.service;

import gob.dp.simco.reporte.dao.ReporteSimcoActividadDao;
import gob.dp.simco.reporte.entity.ReporteSimcoActividad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ReporteSimcoActividadServiceImpl implements ReporteSimcoActividadService{
    
    @Autowired
    private ReporteSimcoActividadDao reporteSimcoActividadDao;

    @Override
    public List<ReporteSimcoActividad> reporteActividad(ReporteSimcoActividad reporteSimcoActividad) {
        return reporteSimcoActividadDao.reporteActividad(reporteSimcoActividad);
    }
    
}
