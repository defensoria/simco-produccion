/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActividadCaso;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActividadCasoDaoImpl extends SqlSessionDaoSupport implements ActividadCasoDao{
    
    @Override
    public void actividadCasoInsertar(ActividadCaso actividadCaso){
        getSqlSession().insert("gob.dp.simco.registro.dao.ActividadCasoDao.actividadCasoInsertar", actividadCaso);
    }

    @Override
    public void actividadCasoUpdate(ActividadCaso actividadCaso) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadCasoDao.actividadCasoUpdate", actividadCaso);
    }

    @Override
    public void actividadCasoDelete(ActividadCaso actividadCaso){
        getSqlSession().delete("gob.dp.simco.registro.dao.ActividadCasoDao.actividadCasoDelete", actividadCaso);
    }

    @Override
    public int actividadCasoValida(long idActividad) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActividadCasoDao.actividadCasoValida", idActividad);
    }

    @Override
    public void actividadesCasoUpdate(ActividadCaso actividadCaso) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadCasoDao.actividadesCasoUpdate", actividadCaso);
    }
    
}
