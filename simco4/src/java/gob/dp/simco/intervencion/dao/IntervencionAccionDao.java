/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.dao;

import gob.dp.simco.intervencion.entity.IntervencionAccion;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface IntervencionAccionDao {
    
    public void intervencionAccionInsertar(IntervencionAccion intervencionAccion);
    
    public IntervencionAccion intervencionAccionBuscar(Long idIntervencion);
    
    public List<IntervencionAccion> intervencionAccionBuscarxCaso(Long idIntervencion);
    
    public List<IntervencionAccion> intervencionAccionBuscarxIntervencion(Long idIntervencion);

}
