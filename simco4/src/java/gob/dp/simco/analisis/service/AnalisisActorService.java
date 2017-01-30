/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.service;

import gob.dp.simco.analisis.entity.AnalisisActor;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface AnalisisActorService {
    
    public void analisisActorInsertar(AnalisisActor analisisActor);
    
    public List<AnalisisActor> analisisActorxcasoBuscar(long idCaso);
    
    public List<AnalisisActor> analisisActorxcasoBuscarxActor(AnalisisActor analisisActor);
    
    public AnalisisActor analisisActorxcasoBuscarOne(AnalisisActor analisisActor);
    
    public void analisisActorxcasoUpdate(AnalisisActor analisisActor);
    
    public void analisisActorDelete(AnalisisActor analisisActor);
    
    public void analisisActorArchivar(AnalisisActor analisisActor);
    
}
