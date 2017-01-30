/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.ActividadMedioVerificacionDao;
import gob.dp.simco.registro.entity.ActividadMedioVerificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActividadMedioVerificacionServiceImpl implements ActividadMedioVerificacionService{
    
    @Autowired
    private ActividadMedioVerificacionDao actividadMedioVerificacionDao;

    @Override
    public void actividadMedioVerificacionInsertar(ActividadMedioVerificacion actividadMedioVerificacion) {
        actividadMedioVerificacionDao.actividadMedioVerificacionInsertar(actividadMedioVerificacion);
    }

    @Override
    public void actividadMedioVerificacionUpdate(ActividadMedioVerificacion actividadMedioVerificacion) {
        actividadMedioVerificacionDao.actividadMedioVerificacionUpdate(actividadMedioVerificacion);
    }

    @Override
    public void actividadMedioVerificacionDelete(ActividadMedioVerificacion actividadMedioVerificacion) {
        actividadMedioVerificacionDao.actividadMedioVerificacionDelete(actividadMedioVerificacion);
    }
    
}
