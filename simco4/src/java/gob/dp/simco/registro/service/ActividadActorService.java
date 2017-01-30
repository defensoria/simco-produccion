/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.bean.FiltroCasoActor;
import gob.dp.simco.registro.entity.ActividadActor;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ActividadActorService {
    
   public void actividadActorInsertar(ActividadActor actividadActor);
   
   public void actividadActorUpdate(ActividadActor actividadActor);
   
   public void actividadActorDelete(ActividadActor actividadActor);
   
   public void saveOrUpdate(ActividadActor actividadActor);
   
   public List<ActividadActor> actividadActorXcaso(FiltroCasoActor filtroCasoActor);
   
   public List<ActividadActor> actividadActorNivelAD(ActividadActor actividadActor); 
   
   public Double ponderadoAD(long idActor);
    
}
