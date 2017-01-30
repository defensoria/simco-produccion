/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActividadActaAcuerdo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActividadActaAcuerdoDaoIml extends SqlSessionDaoSupport implements ActividadActaAcuerdoDao{
    
    @Override
    public void actividadActaAcuerdoInsertar(ActividadActaAcuerdo actividadActaAcuerdo) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActividadActaAcuerdoDao.actividadActaAcuerdoInsertar", actividadActaAcuerdo);
    }

    @Override
    public void actividadActaAcuerdoUpdate(ActividadActaAcuerdo actividadActaAcuerdo) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadActaAcuerdoDao.actividadActaAcuerdoUpdate", actividadActaAcuerdo);
    }

    @Override
    public void actividadActaAcuerdoDelete(ActividadActaAcuerdo actividadActaAcuerdo) {
        getSqlSession().delete("gob.dp.simco.registro.dao.ActividadActaAcuerdoDao.actividadActaAcuerdoDelete", actividadActaAcuerdo);
    }

    @Override
    public int actividadActaAcuerdoValida(ActividadActaAcuerdo actividadActaAcuerdo) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActividadActaAcuerdoDao.actividadActaAcuerdoValida",actividadActaAcuerdo);
    }
    
}