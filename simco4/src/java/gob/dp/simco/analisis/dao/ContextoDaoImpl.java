/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.Contexto;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ContextoDaoImpl extends SqlSessionDaoSupport implements ContextoDao{

    @Override
    public void contextoInsertar(Contexto contexto) {
        getSqlSession().insert("gob.dp.simco.analisis.dao.ContextoDao.contextoInsertar", contexto);
    }

    @Override
    public void contextoUpdate(Contexto contexto) {
        getSqlSession().update("gob.dp.simco.analisis.dao.ContextoDao.contextoUpdate", contexto);
    }

    @Override
    public Contexto contextoBuscar(Contexto contexto) {
        return getSqlSession().selectOne("gob.dp.simco.analisis.dao.ContextoDao.contextoBuscar", contexto);
    }
    
}
