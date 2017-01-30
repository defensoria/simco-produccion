/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.dao;

import gob.dp.simco.intervencion.entity.IntervencionEtapa;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface IntervencionEtapaDao {
    
    public void intervencionEtapaInsertar(IntervencionEtapa intervencionEtapa);
            
    public IntervencionEtapa intervencionEtapaBuscar(Long id);
    
    public List<IntervencionEtapa> intervencionEtapaBuscarTipo(IntervencionEtapa intervencionEtapa);
    
    public void intervencionEtapaUpdate(IntervencionEtapa intervencionEtapa);
    
    public void intervencionEtapaDetalleDelete(long idIntervencionEtapa);
    
    public void intervencionEtapaUpdateDetalle(IntervencionEtapa intervencionEtapa);
    
    public List<IntervencionEtapa> intervencionEtapaxAccion(long idEtapaAccion);
    
    public List<IntervencionEtapa> intervencionEtapaxIntervencion(long idIntervencion);
    
}
