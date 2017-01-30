/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.dao;

import gob.dp.simco.intervencion.entity.IntervencionAccion;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class IntervencionAccionDaoImpl extends SqlSessionDaoSupport implements IntervencionAccionDao{
    
    @Override
    public void intervencionAccionInsertar(IntervencionAccion intervencionAccion) {
        getSqlSession().insert("gob.dp.simco.intervencion.dao.IntervencionAccionDao.intervencionAccionInsertar", intervencionAccion);
    }

    @Override
    public IntervencionAccion intervencionAccionBuscar(Long idIntervencionAccion) {
        return getSqlSession().selectOne("gob.dp.simco.intervencion.dao.IntervencionAccionDao.intervencionAccionBuscar",idIntervencionAccion);
    }

    @Override
    public List<IntervencionAccion> intervencionAccionBuscarxCaso(Long idIntervencion) {
        return getSqlSession().selectList("gob.dp.simco.intervencion.dao.IntervencionAccionDao.intervencionAccionBuscarxCaso",idIntervencion);
    }

    @Override
    public List<IntervencionAccion> intervencionAccionBuscarxIntervencion(Long idIntervencion) {
        return getSqlSession().selectList("gob.dp.simco.intervencion.dao.IntervencionAccionDao.intervencionAccionBuscarxIntervencion",idIntervencion);
    }
    
}
