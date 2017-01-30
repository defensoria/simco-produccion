/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.service;

import gob.dp.simco.analisis.entity.ProblemaDetalle;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ProblemaDetalleService {
    
    public void problemaDetalleInsertar(ProblemaDetalle problemaDetalle);
          
    public void problemaDetalleUpdate(ProblemaDetalle problemaDetalle);
            
    public List<ProblemaDetalle> problemaDetalleBuscar(long idProblema);
    
    public void problemaDetalleUpdateDatos(ProblemaDetalle problemaDetalle);
    
}
