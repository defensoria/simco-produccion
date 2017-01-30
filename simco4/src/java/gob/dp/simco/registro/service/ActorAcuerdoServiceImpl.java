/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.ActorAcuerdoDao;
import gob.dp.simco.registro.entity.ActorAcuerdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActorAcuerdoServiceImpl implements ActorAcuerdoService{
    
    @Autowired
    private ActorAcuerdoDao actorAcuerdoDao;

    @Override
    public void actorAcuerdoInsertar(ActorAcuerdo actorAcuerdo) {
        if(actorAcuerdoValida(actorAcuerdo) == 0)
            actorAcuerdoDao.actorAcuerdoInsertar(actorAcuerdo);
    }

    @Override
    public void actorAcuerdoUpdate(ActorAcuerdo actorAcuerdo) {
        actorAcuerdoDao.actorAcuerdoUpdate(actorAcuerdo);
    }

    @Override
    public Integer actorAcuerdoValida(ActorAcuerdo actorAcuerdo) {
        return actorAcuerdoDao.actorAcuerdoValida(actorAcuerdo);
    }

    @Override
    public void actorAcuerdoDelete(Long idActaAcuerdoDetalle) {
        actorAcuerdoDao.actorAcuerdoDelete(idActaAcuerdoDetalle);
    }

    @Override
    public void actorAcuerdoAnular(Long idActaAcuerdoDetalle) {
        actorAcuerdoDao.actorAcuerdoAnular(idActaAcuerdoDetalle);
    }
    
}
