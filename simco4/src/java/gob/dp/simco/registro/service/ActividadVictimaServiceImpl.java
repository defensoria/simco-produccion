/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.ActividadVictimaDao;
import gob.dp.simco.registro.entity.ActividadVictima;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActividadVictimaServiceImpl implements ActividadVictimaService{
    
    @Autowired
    private ActividadVictimaDao actividadVictimaDao;

    @Override
    public void actividadVictimaInsertar(ActividadVictima victimas) {
        actividadVictimaDao.actividadVictimaInsertar(victimas);
    }

    @Override
    public void actividadVictimaUpdate(ActividadVictima victimas) {
        actividadVictimaDao.actividadVictimaUpdate(victimas);
    }

    @Override
    public List<ActividadVictima> actividadVictimaBuscar(long idActividad) {
        return actividadVictimaDao.actividadVictimaBuscar(idActividad);
    }

    @Override
    public void actividadVictimaEliminar(long idActividad) {
        actividadVictimaDao.actividadVictimaEliminar(idActividad);
    }
    
}
