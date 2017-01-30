/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActorAcuerdo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActorAcuerdoDaoImpl extends SqlSessionDaoSupport implements ActorAcuerdoDao{
    
    @Override
    public void actorAcuerdoInsertar(ActorAcuerdo actorAcuerdo) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActorAcuerdoDao.actorAcuerdoInsertar", actorAcuerdo);
    }

    @Override
    public void actorAcuerdoUpdate(ActorAcuerdo actorAcuerdo) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActorAcuerdoDao.actorAcuerdoUpdate", actorAcuerdo);
    }

    @Override
    public Integer actorAcuerdoValida(ActorAcuerdo actorAcuerdo) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActorAcuerdoDao.actorAcuerdoValida", actorAcuerdo);
    }

    @Override
    public void actorAcuerdoDelete(Long idActaAcuerdoDetalle) {
        getSqlSession().delete("gob.dp.simco.registro.dao.ActorAcuerdoDao.actorAcuerdoDelete", idActaAcuerdoDetalle);
    }

    @Override
    public void actorAcuerdoAnular(Long idActaAcuerdoDetalle) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActorAcuerdoDao.actorAcuerdoAnular", idActaAcuerdoDetalle);
    }
}
