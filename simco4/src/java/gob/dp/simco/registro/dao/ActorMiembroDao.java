/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActorMiembro;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ActorMiembroDao {
    
    public void actorMiembroInsertar(ActorMiembro actorMiembro);
            
    public void actorMiembroUpdate(ActorMiembro actorMiembro);
            
    public void actorMiembroDelete(ActorMiembro actorMiembro);
                    
    public List<ActorMiembro> actorMiembroBuscarxActor(long idActor);
    
    public List<ActorMiembro> actorMiembroBuscarxMiembro(long idActor);
    
    public List<ActorMiembro> actorMiembroBuscarTodosxActor(long idActor);
    
}
