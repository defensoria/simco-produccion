/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActividadVictima;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActividadVictimaDaoImpl extends SqlSessionDaoSupport implements ActividadVictimaDao{

    @Override
    public void actividadVictimaInsertar(ActividadVictima victimas) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActividadVictimaDao.actividadVictimaInsertar", victimas);
    }

    @Override
    public void actividadVictimaUpdate(ActividadVictima victimas) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadVictimaDao.actividadVictimaUpdate", victimas);
    }

    @Override
    public List<ActividadVictima> actividadVictimaBuscar(long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadVictimaDao.actividadVictimaBuscar", idActividad);
    }

    @Override
    public void actividadVictimaEliminar(long idActividad) {
        getSqlSession().delete("gob.dp.simco.registro.dao.ActividadVictimaDao.actividadVictimaEliminar", idActividad);
    }
    
}
