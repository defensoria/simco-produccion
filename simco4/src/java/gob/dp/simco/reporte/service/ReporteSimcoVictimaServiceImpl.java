/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.service;

import gob.dp.simco.reporte.dao.ReporteSimcoVictimaDao;
import gob.dp.simco.reporte.entity.ReporteSimcoVictima;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ReporteSimcoVictimaServiceImpl implements ReporteSimcoVictimaService{
    
    @Autowired
    private ReporteSimcoVictimaDao reporteSimcoVictimaDao;

    @Override
    public List<ReporteSimcoVictima> reporteVictima(ReporteSimcoVictima reporteSimcoVictima) {
        return reporteSimcoVictimaDao.reporteVictima(reporteSimcoVictima);
    }
    
}
