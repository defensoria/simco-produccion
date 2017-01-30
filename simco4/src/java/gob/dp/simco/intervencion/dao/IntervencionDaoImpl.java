/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.dao;

import gob.dp.simco.intervencion.entity.Intervencion;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class IntervencionDaoImpl extends SqlSessionDaoSupport implements IntervencionDao{

    @Override
    public void intervencionInsertar(Intervencion intervencion) {
        getSqlSession().insert("gob.dp.simco.intervencion.dao.IntervencionDao.intervencionInsertar", intervencion);
    }

    @Override
    public List<Intervencion> intervencionBuscar(String codigoUsuario) {
        return getSqlSession().selectList("gob.dp.simco.intervencion.dao.IntervencionDao.intervencionBuscar",codigoUsuario);
    }

    @Override
    public void intervencionUpdate(Intervencion intervencion) {
        getSqlSession().update("gob.dp.simco.intervencion.dao.IntervencionDao.intervencionUpdate", intervencion);
    }

    @Override
    public List<Intervencion> intervencionBuscarPriorizados() {
        return getSqlSession().selectList("gob.dp.simco.intervencion.dao.IntervencionDao.intervencionBuscarPriorizados");
    }

    @Override
    public List<Intervencion> intervencionBuscarArchivados() {
        return getSqlSession().selectList("gob.dp.simco.intervencion.dao.IntervencionDao.intervencionBuscarArchivados");
    }

    @Override
    public List<Intervencion> intervencionBuscarActivas() {
        return getSqlSession().selectList("gob.dp.simco.intervencion.dao.IntervencionDao.intervencionBuscarActivas");
    }

    @Override
    public Intervencion intervencionBuscarCaso(String codigo) {
        return getSqlSession().selectOne("gob.dp.simco.intervencion.dao.IntervencionDao.intervencionBuscarCaso", codigo);
    }

    @Override
    public Integer intervencionBuscarCasoCount(String codigo) {
        return getSqlSession().selectOne("gob.dp.simco.intervencion.dao.IntervencionDao.intervencionBuscarCasoCount", codigo);
    }
    
}
