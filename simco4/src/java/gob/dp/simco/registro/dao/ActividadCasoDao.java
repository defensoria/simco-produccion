/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActividadCaso;

/**
 *
 * @author carlos
 */
public interface ActividadCasoDao {
    
   public void actividadCasoInsertar(ActividadCaso actividadCaso);
   
   public void actividadCasoUpdate(ActividadCaso actividadCaso);
   
   public void actividadesCasoUpdate(ActividadCaso actividadCaso);
   
   public void actividadCasoDelete(ActividadCaso actividadCaso);
   
   public int actividadCasoValida(long idActividad);
   
}
