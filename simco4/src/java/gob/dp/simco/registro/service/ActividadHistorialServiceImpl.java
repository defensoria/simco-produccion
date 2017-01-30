/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.ActividadHistorialDao;
import gob.dp.simco.registro.entity.ActividadHistorial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActividadHistorialServiceImpl implements ActividadHistorialService{
    
    @Autowired
    private ActividadHistorialDao actividadHistorialDao;

    @Override
    public void actividadHistorialInsertar(ActividadHistorial historial) {
        actividadHistorialDao.actividadHistorialInsertar(historial);
    }
    
}
