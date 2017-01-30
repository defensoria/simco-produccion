/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActorAcuerdo;

/**
 *
 * @author carlos
 */
public interface ActorAcuerdoDao {
    
   public void actorAcuerdoInsertar(ActorAcuerdo actorAcuerdo);
   
   public void actorAcuerdoUpdate(ActorAcuerdo actorAcuerdo);
   
   public Integer actorAcuerdoValida(ActorAcuerdo actorAcuerdo);
   
   public void actorAcuerdoDelete(Long idActaAcuerdoDetalle);
   
   public void actorAcuerdoAnular(Long idActaAcuerdoDetalle);
    
}
