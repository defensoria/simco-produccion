/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActividadActaAcuerdo;

/**
 *
 * @author carlos
 */
public interface ActividadActaAcuerdoDao {
    
   public void actividadActaAcuerdoInsertar(ActividadActaAcuerdo actividadActaAcuerdo);
   
   public void actividadActaAcuerdoUpdate(ActividadActaAcuerdo actividadActaAcuerdo);
   
   public void actividadActaAcuerdoDelete(ActividadActaAcuerdo actividadActaAcuerdo);

   public int actividadActaAcuerdoValida(ActividadActaAcuerdo actividadActaAcuerdo);
   
}
