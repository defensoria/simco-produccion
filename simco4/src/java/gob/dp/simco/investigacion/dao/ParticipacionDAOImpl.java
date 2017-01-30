/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.dao;

import gob.dp.simco.investigacion.entity.Participacion;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ParticipacionDAOImpl extends SqlSessionDaoSupport implements ParticipacionDAO {

    @Override
    public void participacionInsertar(Participacion participacion) {
        getSqlSession().insert("gob.dp.simco.investigacion.dao.ParticipacionDAO.participacionInsertar", participacion);
    }

    @Override
    public void participacionUpdate(Participacion participacion) {
        getSqlSession().update("gob.dp.simco.investigacion.dao.ParticipacionDAO.participacionUpdate", participacion);
    }

    @Override
    public List<Participacion> participacionBuscar(Long idInvestigacion) {
        return getSqlSession().selectList("gob.dp.simco.investigacion.dao.ParticipacionDAO.participacionBuscar", idInvestigacion);
    }
    
}
