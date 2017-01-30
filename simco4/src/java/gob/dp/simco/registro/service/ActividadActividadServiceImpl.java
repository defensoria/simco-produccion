/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.ActividadActividadDao;
import gob.dp.simco.registro.entity.ActividadActividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActividadActividadServiceImpl implements ActividadActividadService{
    
    @Autowired
    private ActividadActividadDao actividadActividadDao;

    @Override
    public void actividadActividadInsertar(ActividadActividad actividadActividad) {
        actividadActividadDao.actividadActividadInsertar(actividadActividad);
    }

    @Override
    public void actividadActividadUpdate(ActividadActividad actividadActividad) {
        actividadActividadDao.actividadActividadUpdate(actividadActividad);
    }

    @Override
    public void actividadActividadDelete(ActividadActividad actividadActividad) {
        actividadActividadDao.actividadActividadDelete(actividadActividad);
    }
    
}
