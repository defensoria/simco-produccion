/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.AnalisisActorTema;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface AnalisisActorTemaDao {
    
    public void analisisActorTemaInsertar(AnalisisActorTema analisisActorTema);
    
    public List<AnalisisActorTema> analisisActorTemaXactorBuscar(Long idTema);
    
    public void analisisActorTemaUpdate(AnalisisActorTema analisisActorTema);
    
    public Integer analisisActorTemaXactorValida(AnalisisActorTema analisisActorTema);
    
    public Integer analisisActorCasoTemaValida(AnalisisActorTema analisisActorTema);
    
    public void analisisActorTemaDelete(AnalisisActorTema analisisActorTema);
    
    public void analisisActorTemaDeletexActor(AnalisisActorTema analisisActorTema);
    
}
