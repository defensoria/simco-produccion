/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.dao;

import gob.dp.simco.intervencion.entity.IntervencionHistorialAct;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface IntervencionHistorialActDao {
    
    public void intervencionHistorialActInsertar(IntervencionHistorialAct intervencionHistorialAct);
            
    public List<IntervencionHistorialAct> intervencionHistorialActBuscar(long idEtapa);
    
}
