/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.bean.FiltroCasoActor;
import gob.dp.simco.registro.entity.ActividadActor;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ActividadActorDao {
    
   public void actividadActorInsertar(ActividadActor actividadActor);
   
   public void actividadActorUpdate(ActividadActor actividadActor);
   
   public void actividadActorDelete(ActividadActor actividadActor);
   
   public Integer actividadActorBuscarOne(ActividadActor actividadActor);
   
   public List<ActividadActor> actividadActorXcaso(FiltroCasoActor filtroCasoActor);
   
   public List<ActividadActor> actividadActorNivelAD(ActividadActor actividadActor);
   
   public List<ActividadActor> listaActorAD(long idActor);

}
