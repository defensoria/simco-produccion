/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.entity.ActividadCaso;

/**
 *
 * @author carlos
 */
public interface ActividadCasoService {
    
   public void actividadCasoInsertar(ActividadCaso actividadActor);
   
   public void actividadCasoUpdate(ActividadCaso actividadActor);
   
   public void actividadesCasoUpdate(ActividadCaso actividadCaso);
   
   public void actividadCasoDelete(ActividadCaso actividadActor);
   
   public int actividadCasoValida(long idActividad);
    
}
