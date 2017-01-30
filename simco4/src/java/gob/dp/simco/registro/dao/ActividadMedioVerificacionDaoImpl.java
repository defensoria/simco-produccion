/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActividadMedioVerificacion;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActividadMedioVerificacionDaoImpl extends SqlSessionDaoSupport implements ActividadMedioVerificacionDao{
    
    @Override
    public void actividadMedioVerificacionInsertar(ActividadMedioVerificacion actividadMedioVerificacion) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActividadMedioVerificacionDao.actividadMedioVerificacionInsertar", actividadMedioVerificacion);
    }

    @Override
    public void actividadMedioVerificacionUpdate(ActividadMedioVerificacion actividadMedioVerificacion) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadMedioVerificacionDao.actividadMedioVerificacionUpdate", actividadMedioVerificacion);
    }

    @Override
    public void actividadMedioVerificacionDelete(ActividadMedioVerificacion actividadMedioVerificacion) {
        getSqlSession().delete("gob.dp.simco.registro.dao.ActividadMedioVerificacionDao.actividadMedioVerificacionDelete", actividadMedioVerificacion);
    }
    
}
