/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActorMiembro;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActorMiembroDaoImpl extends SqlSessionDaoSupport implements ActorMiembroDao {

    @Override
    public void actorMiembroInsertar(ActorMiembro actorMiembro) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActorMiembroDao.actorMiembroInsertar", actorMiembro);
    }

    @Override
    public void actorMiembroUpdate(ActorMiembro actorMiembro) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActorMiembroDao.actorMiembroUpdate", actorMiembro);
    }

    @Override
    public void actorMiembroDelete(ActorMiembro actorMiembro) {
        getSqlSession().delete("gob.dp.simco.registro.dao.ActorMiembroDao.actorMiembroDelete", actorMiembro);
    }

    @Override
    public List<ActorMiembro> actorMiembroBuscarxActor(long idActor) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorMiembroDao.actorMiembroBuscarxActor", idActor);
    }
    
    @Override
    public List<ActorMiembro> actorMiembroBuscarxMiembro(long idActor) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorMiembroDao.actorMiembroBuscarxMiembro", idActor);
    }

    @Override
    public List<ActorMiembro> actorMiembroBuscarTodosxActor(long idActor) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorMiembroDao.actorMiembroBuscarTodosxActor", idActor);
    }
    
}
