/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActividadMedioVerificacion;

/**
 *
 * @author carlos
 */
public interface ActividadMedioVerificacionDao {
    
   public void actividadMedioVerificacionInsertar(ActividadMedioVerificacion actividadMedioVerificacion);
   
   public void actividadMedioVerificacionUpdate(ActividadMedioVerificacion actividadMedioVerificacion);
   
   public void actividadMedioVerificacionDelete(ActividadMedioVerificacion actividadMedioVerificacion);
   
}
