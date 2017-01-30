/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActaAcuerdo;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ActaAcuerdoDao {
    
   public void actaAcuerdoInsertar(ActaAcuerdo actaAcuerdo);
   
   public void actaAcuerdoUpdate(ActaAcuerdo actaAcuerdo);
   
   public List<ActaAcuerdo> actaAcuerdoxActividadBuscar(long idActividad);
   
   public List<ActaAcuerdo> actaAcuerdoxActividadBuscarTotal(long idActividad);
   
   public ActaAcuerdo actaAcuerdoBuscarOne(ActaAcuerdo actaAcuerdo);
   
   public ActaAcuerdo actaAcuerdoxActividadBuscarOne(long idActividad);
   
   public Integer actaAcuerdoCodigoGenerado();

}
