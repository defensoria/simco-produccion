/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.service;

import gob.dp.simco.analisis.entity.AnalisisRelacion;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface AnalisisRelacionService {
    
    public void analisisRelacionInsertar(AnalisisRelacion analisisRelacion);
            
    public List<AnalisisRelacion> analisisRelacionBuscar(Long idCaso);
    
    public void analisisRelacioUpdate(AnalisisRelacion analisisRelacion);
    
    public AnalisisRelacion analisisRelacionBuscarOne(AnalisisRelacion analisisRelacion);
    
    public List<AnalisisRelacion> analisisRelacionBuscarTodos(Long idCaso);
    
    public void analisisRelacioEliminar(AnalisisRelacion analisisRelacion);
}
