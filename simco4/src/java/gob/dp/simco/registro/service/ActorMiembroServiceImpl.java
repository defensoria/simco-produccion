/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.ActorMiembroDao;
import gob.dp.simco.registro.entity.ActorMiembro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActorMiembroServiceImpl implements ActorMiembroService{
    
    @Autowired
    private ActorMiembroDao actorMiembroDao;

    @Override
    public void actorMiembroInsertar(ActorMiembro actorMiembro) {
        actorMiembroDao.actorMiembroInsertar(actorMiembro);
    }

    @Override
    public void actorMiembroUpdate(ActorMiembro actorMiembro) {
        actorMiembroDao.actorMiembroUpdate(actorMiembro);
    }

    @Override
    public void actorMiembroDelete(ActorMiembro actorMiembro) {
        actorMiembroDao.actorMiembroDelete(actorMiembro);
    }

    @Override
    public List<ActorMiembro> actorMiembroBuscarxActor(long idActor) {
        return actorMiembroDao.actorMiembroBuscarxActor(idActor);
    }
    
    @Override
    public List<ActorMiembro> actorMiembroBuscarxMiembro(long idActor) {
        return actorMiembroDao.actorMiembroBuscarxMiembro(idActor);
    }

    @Override
    public List<ActorMiembro> actorMiembroBuscarTodosxActor(long idActor) {
        return actorMiembroDao.actorMiembroBuscarTodosxActor(idActor);
    }
    
}
