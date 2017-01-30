/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.service;

import gob.dp.simco.intervencion.dao.IntervencionHistorialActDao;
import gob.dp.simco.intervencion.entity.IntervencionHistorialAct;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class IntervencionHistorialActServiceImpl implements IntervencionHistorialActService{
    
    @Autowired
    private IntervencionHistorialActDao intervencionHistorialActDao;

    @Override
    public void intervencionHistorialActInsertar(IntervencionHistorialAct intervencionHistorialAct) {
        intervencionHistorialActDao.intervencionHistorialActInsertar(intervencionHistorialAct);
    }

    @Override
    public List<IntervencionHistorialAct> intervencionHistorialActBuscar(long idEtapa) {
        return intervencionHistorialActDao.intervencionHistorialActBuscar(idEtapa);
    }
    
}
