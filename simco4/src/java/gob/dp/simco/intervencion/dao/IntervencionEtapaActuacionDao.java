/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.dao;

import gob.dp.simco.intervencion.entity.IntervencionEtapaActuacion;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface IntervencionEtapaActuacionDao {
    
    public void intervencionEtapaActuacionInsertar(IntervencionEtapaActuacion intervencionEtapaActuacion);
    
    public List<IntervencionEtapaActuacion> intervencionEtapaActuacionBuscar(Long idEtapa);
    
    public List<IntervencionEtapaActuacion> intervencionEtapaActuacionBuscarActividad(Long idEtapa);
    
    public List<IntervencionEtapaActuacion> intervencionEtapaActuacionBuscarActividadGSA(Long idEtapa);
    
    public void intervencionEtapaActuacionUpdate(IntervencionEtapaActuacion intervencionEtapaActuacion);    
    
    public void intervencionEtapaActuacionEliminar(long id);    
    
}
