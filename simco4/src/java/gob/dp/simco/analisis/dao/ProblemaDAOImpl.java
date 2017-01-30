/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.Problema;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ProblemaDAOImpl extends SqlSessionDaoSupport implements ProblemaDAO {

    @Override
    public void problemaInsertar(Problema problema) {
        getSqlSession().insert("gob.dp.simco.analisis.dao.ProblemaDAO.problemaInsertar", problema);
    }

    @Override
    public void problemaUpdate(Problema problema) {
        getSqlSession().update("gob.dp.simco.analisis.dao.ProblemaDAO.problemaUpdate", problema);
    }

    @Override
    public Problema problemaBuscar(long id) {
        return getSqlSession().selectOne("gob.dp.simco.analisis.dao.ProblemaDAO.problemaBuscar", id);
    }
    
}
