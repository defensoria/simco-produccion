/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.ActaAcuerdoDetalleMiembroDAO;
import gob.dp.simco.registro.entity.ActaAcuerdoDetalleMiembro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActaAcuerdoDetalleMiembroServiceImpl implements  ActaAcuerdoDetalleMiembroService{
    
    @Autowired
    private ActaAcuerdoDetalleMiembroDAO actaAcuerdoDetalleMiembroDAO;

    @Override
    public void actaAcuerdoDetalleMiembroInsertar(ActaAcuerdoDetalleMiembro actaAcuerdoDetalleMiembro) {
        actaAcuerdoDetalleMiembroDAO.actaAcuerdoDetalleMiembroInsertar(actaAcuerdoDetalleMiembro);
    }

    @Override
    public void actaAcuerdoDetalleMiembroUpdate(ActaAcuerdoDetalleMiembro actaAcuerdoDetalleMiembro) {
        actaAcuerdoDetalleMiembroDAO.actaAcuerdoDetalleMiembroUpdate(actaAcuerdoDetalleMiembro);
    }

    @Override
    public List<ActaAcuerdoDetalleMiembro> actaAcuerdoDetalleMiembroBuscar(long idActaAcuerdoDetalle) {
        return actaAcuerdoDetalleMiembroDAO.actaAcuerdoDetalleMiembroBuscar(idActaAcuerdoDetalle);
    }
    
}
