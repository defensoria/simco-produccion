/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.entity.CasoActor;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface CasoActorService {
    
    public List<CasoActor> casoActorBuscar(long idCaso);
    
    public Integer casoActorBuscarCount(CasoActor casoActor);
    
    public void casoActorInsertar(CasoActor casoActor);
    
    public void casoUpdate(CasoActor casoActor);
    
    public List<CasoActor> casoActorBuscarComplete(long idCaso);
}
