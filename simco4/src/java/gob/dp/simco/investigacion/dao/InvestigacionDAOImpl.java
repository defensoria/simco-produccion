/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.dao;

import gob.dp.simco.investigacion.entity.Investigacion;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class InvestigacionDAOImpl extends SqlSessionDaoSupport implements InvestigacionDAO{

    @Override
    public void investigacionInsertar(Investigacion investigacion) {
        getSqlSession().insert("gob.dp.simco.investigacion.dao.InvestigacionDAO.investigacionInsertar", investigacion);
    }

    @Override
    public void investigacionUpdate(Investigacion investigacion) {
        getSqlSession().update("gob.dp.simco.investigacion.dao.InvestigacionDAO.investigacionUpdate", investigacion);
    }

    @Override
    public List<Investigacion> investigacionBuscar(Investigacion investigacion) {
        return getSqlSession().selectList("gob.dp.simco.investigacion.dao.InvestigacionDAO.investigacionBuscar", investigacion);
    }
    
}
