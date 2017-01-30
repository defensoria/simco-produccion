/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActaAcuerdoDetalle;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ActaAcuerdoDetalleDao {
    
   public void actaAcuerdoDetalleInsertar(ActaAcuerdoDetalle actaAcuerdoDetalle);
   
   public void actaAcuerdoDetalleUpdate(ActaAcuerdoDetalle actaAcuerdoDetalle);
   
   public List<ActaAcuerdoDetalle> actaAcuerdoDetalleBuscarxActa(Long idActaAcuerdoDetalle);
   
   public List<ActaAcuerdoDetalle> actaAcuerdoDetalleSeguimiento(ActaAcuerdoDetalle actaAcuerdoDetalle);
   
   public List<ActaAcuerdoDetalle> actaAcuerdoDetalleSeguimientoCaso(long idCaso);
   
   public void actaAcuerdoDetalleDelete(long id);
   
   public void actaAcuerdoDetalleUpdateColor(ActaAcuerdoDetalle aad);
   
   public List<ActaAcuerdoDetalle> actaAcuerdoDetalleListaxActa(Long idActaAcuerdo);   
   
   public Integer actaAcuerdoDetalleCodigoGenerado();
   
   public Integer actaAcuerdoDetalleCount(Long idActaAcuerdo);
   
   
    
}
