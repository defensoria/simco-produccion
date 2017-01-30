/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActaAcuerdo;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActaAcuerdoDaoImpl extends SqlSessionDaoSupport implements ActaAcuerdoDao {
    
    @Override
    public void actaAcuerdoInsertar(ActaAcuerdo actaAcuerdo) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActaAcuerdoDao.actaAcuerdoInsertar", actaAcuerdo);
    }

    @Override
    public void actaAcuerdoUpdate(ActaAcuerdo actaAcuerdo) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActaAcuerdoDao.actaAcuerdoUpdate", actaAcuerdo);
    }
    
    @Override
    public List<ActaAcuerdo> actaAcuerdoxActividadBuscar(long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActaAcuerdoDao.actaAcuerdoxActividadBuscar",idActividad);
    }

    @Override
    public List<ActaAcuerdo> actaAcuerdoxActividadBuscarTotal(long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActaAcuerdoDao.actaAcuerdoxActividadBuscarTotal",idActividad);
    }

    @Override
    public ActaAcuerdo actaAcuerdoBuscarOne(ActaAcuerdo actaAcuerdo) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActaAcuerdoDao.actaAcuerdoBuscarOne",actaAcuerdo);
    }

    @Override
    public ActaAcuerdo actaAcuerdoxActividadBuscarOne(long idActividad) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActaAcuerdoDao.actaAcuerdoxActividadBuscarOne",idActividad);
    }

    @Override
    public Integer actaAcuerdoCodigoGenerado() {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActaAcuerdoDao.actaAcuerdoCodigoGenerado");
    }
    
}
