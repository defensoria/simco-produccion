/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.dao;

import gob.dp.simco.intervencion.entity.IntervencionMiembro;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface IntervencionMiembroDao {
    
    public void intervencionMiembroInsertar(IntervencionMiembro intervencionMiembro);
    
    public void intervencionMiembroUpdate(IntervencionMiembro intervencionMiembro);
            
    public List<IntervencionMiembro> intervencionMiembroBuscar(Long idEtapa);
    
    public Integer intervencionMiembroCountEtapa(Long idEtapa);
    
    public Integer intervencionMiembroValidaInsert(IntervencionMiembro intervencionMiembro);
    
    public Integer intervencionMiembroCodigoMiembro(IntervencionMiembro intervencionMiembro);
    
}
