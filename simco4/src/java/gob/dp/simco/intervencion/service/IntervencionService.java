/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.service;

import gob.dp.simco.intervencion.entity.Intervencion;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface IntervencionService {
    
    public void intervencionInsertar(Intervencion intervencion);
    
    public List<Intervencion> intervencionBuscar(String codigoUsuario);
    
    public void intervencionUpdate(Intervencion intervencion);
    
    public List<Intervencion> intervencionBuscarPriorizados();
    
    public List<Intervencion> intervencionBuscarArchivados();
    
    public List<Intervencion> intervencionBuscarActivas();
    
    public Intervencion intervencionBuscarCaso(String codigo);
    
    public Integer intervencionBuscarCasoCount(String codigo);
}
