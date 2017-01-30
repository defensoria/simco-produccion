/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.seguimiento.dao;

import gob.dp.simco.seguimiento.entity.SeguimientoAcuerdo;

/**
 *
 * @author carlos
 */
public interface SeguimientoAcuerdoDao {
    
    public void seguimientoAcuerdoInsertar(SeguimientoAcuerdo seguimientoAcuerdo);
    
    public SeguimientoAcuerdo seguimientoAcuerdoBuscar(Long idActaAcuerdoDetalle);
    
    public void seguimientoAcuerdoUpdate(SeguimientoAcuerdo seguimientoAcuerdo);
    
    public void seguimientoAcuerdoDelete(Long id);
    
    public SeguimientoAcuerdo seguimientoAcuerdoBuscarAcuerdo(Long idAcuerdo);
    
}
