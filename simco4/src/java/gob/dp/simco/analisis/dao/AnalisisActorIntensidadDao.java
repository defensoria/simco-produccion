/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.AnalisisActorIntensidad;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface AnalisisActorIntensidadDao {
    
    public void analisisActorIntensidadInsertar(AnalisisActorIntensidad analisisActorIntensidad);
    
    public List<AnalisisActorIntensidad> analisisActorIntensidadBuscar(Long idCaso);  
    
    public void analisisActorIntensidadUpdate(AnalisisActorIntensidad analisisActorIntensidad);
    
    public Integer analisisActorIntensidadBuscarUno(AnalisisActorIntensidad analisisActorIntensidad);
    
    
}
