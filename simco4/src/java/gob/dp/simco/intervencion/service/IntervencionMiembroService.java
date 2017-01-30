/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.service;

import gob.dp.simco.intervencion.entity.IntervencionMiembro;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface IntervencionMiembroService {
    
    public void intervencionMiembroInsertar(IntervencionMiembro intervencionMiembro);
            
    public List<IntervencionMiembro> intervencionMiembroBuscar(Long idEtapa);
    
    public Integer intervencionMiembroCountEtapa(Long idEtapa);
    
    public void intervencionMiembroUpdate(IntervencionMiembro intervencionMiembro);
    
}
