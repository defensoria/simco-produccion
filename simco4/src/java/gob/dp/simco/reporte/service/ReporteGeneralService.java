/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.service;

import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.reporte.entity.FiltroReporte;
import java.util.List;

/**
 *
 * @author carlos
 */

public interface ReporteGeneralService {
    
    public List<Caso> reporteCaso(FiltroReporte filtroReporte);
    
}
