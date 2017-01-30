/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActividadActividad;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActividadActividadDaoImpl extends SqlSessionDaoSupport implements ActividadActividadDao{
    
    @Override
    public void actividadActividadInsertar(ActividadActividad actividadActividad) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActividadActividadDao.actividadActividadInsertar", actividadActividad);
    }

    @Override
    public void actividadActividadUpdate(ActividadActividad actividadActividad) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadActividadDao.actividadActividadUpdate", actividadActividad);
    }

    @Override
    public void actividadActividadDelete(ActividadActividad actividadActividad) {
        getSqlSession().delete("gob.dp.simco.registro.dao.ActividadActividadDao.actividadActividadDelete", actividadActividad);
    }
    
}