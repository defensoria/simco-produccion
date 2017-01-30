/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.ActividadCasoDao;
import gob.dp.simco.registro.entity.ActividadCaso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActividadCasoServiceImpl implements ActividadCasoService{
    
    @Autowired
    private ActividadCasoDao actividadCasoDao;

    @Override
    public void actividadCasoInsertar(ActividadCaso actividadCaso) {
        actividadCasoDao.actividadCasoInsertar(actividadCaso);
    }

    @Override
    public void actividadCasoUpdate(ActividadCaso actividadCaso){
        actividadCasoDao.actividadCasoUpdate(actividadCaso);
    }

    @Override
    public void actividadCasoDelete(ActividadCaso actividadCaso){
        actividadCasoDao.actividadCasoDelete(actividadCaso);
    }

    @Override
    public int actividadCasoValida(long idActividad) {
        return actividadCasoDao.actividadCasoValida(idActividad);
    }

    @Override
    public void actividadesCasoUpdate(ActividadCaso actividadCaso) {
        actividadCasoDao.actividadesCasoUpdate(actividadCaso);
    }
    
}
