/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActividadHistorial;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActividadHistorialDaoImpl extends SqlSessionDaoSupport implements ActividadHistorialDao{
    
    @Override
    public void actividadHistorialInsertar(ActividadHistorial historial) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActividadHistorialDao.actividadHistorialInsertar", historial);
    }   
}