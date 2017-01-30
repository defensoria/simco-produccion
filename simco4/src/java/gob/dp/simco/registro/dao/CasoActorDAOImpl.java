/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.CasoActor;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class CasoActorDAOImpl extends SqlSessionDaoSupport implements CasoActorDAO{

    @Override
    public List<CasoActor> casoActorBuscar(long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.CasoActorDAO.casoActorBuscar", idCaso);
    }

    @Override
    public void casoActorInsertar(CasoActor casoActor) {
        getSqlSession().insert("gob.dp.simco.registro.dao.CasoActorDAO.casoActorInsertar", casoActor);
    }

    @Override
    public void casoUpdate(CasoActor casoActor) {
        getSqlSession().update("gob.dp.simco.registro.dao.CasoActorDAO.casoUpdate", casoActor);
    }

    @Override
    public Integer casoActorBuscarCount(CasoActor casoActor) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.CasoActorDAO.casoActorBuscarCount", casoActor);
    }

    @Override
    public List<CasoActor> casoActorBuscarComplete(long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.CasoActorDAO.casoActorBuscarComplete", idCaso);
    }
    
}
