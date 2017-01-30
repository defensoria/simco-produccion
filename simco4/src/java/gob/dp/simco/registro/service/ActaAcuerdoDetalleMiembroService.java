/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.entity.ActaAcuerdoDetalleMiembro;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ActaAcuerdoDetalleMiembroService {
    
    public void actaAcuerdoDetalleMiembroInsertar(ActaAcuerdoDetalleMiembro actaAcuerdoDetalleMiembro);
            
    public void actaAcuerdoDetalleMiembroUpdate(ActaAcuerdoDetalleMiembro actaAcuerdoDetalleMiembro);
            
    public List<ActaAcuerdoDetalleMiembro> actaAcuerdoDetalleMiembroBuscar(long idActaAcuerdoDetalle);
    
}
