/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.dao;

import gob.dp.simco.investigacion.entity.Campo;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class CampoDAOImpl extends SqlSessionDaoSupport implements CampoDAO {

    @Override
    public void campoInsertar(Campo campo) {
        getSqlSession().insert("gob.dp.simco.investigacion.dao.CampoDAO.campoInsertar", campo);
    }

    @Override
    public void campoUpdate(Campo campo) {
        getSqlSession().update("gob.dp.simco.investigacion.dao.CampoDAO.campoUpdate", campo);
    }

    @Override
    public List<Campo> campoxInvestigacionBuscar(Long idInvestigacion) {
        return getSqlSession().selectList("gob.dp.simco.investigacion.dao.CampoDAO.campoxInvestigacionBuscar", idInvestigacion);
    }
    
}
