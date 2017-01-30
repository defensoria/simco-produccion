/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.AnalisisActor;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class AnalisisActorDaoImpl extends SqlSessionDaoSupport implements AnalisisActorDao{
    
    @Override
    public void analisisActorInsertar(AnalisisActor analisisActor) {
        getSqlSession().insert("gob.dp.simco.analisis.dao.AnalisisActorDao.analisisActorInsertar", analisisActor);
    }

    @Override
    public List<AnalisisActor> analisisActorxcasoBuscar(long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.analisis.dao.AnalisisActorDao.analisisActorxcasoBuscar", idCaso);
    }

    @Override
    public AnalisisActor analisisActorxcasoBuscarOne(AnalisisActor analisisActor) {
        return getSqlSession().selectOne("gob.dp.simco.analisis.dao.AnalisisActorDao.analisisActorxcasoBuscarOne", analisisActor);
    }
    
     @Override
    public List<AnalisisActor> analisisActorxcasoBuscarxActor(AnalisisActor analisisActor) {
        return getSqlSession().selectList("gob.dp.simco.analisis.dao.AnalisisActorDao.analisisActorxcasoBuscarxActor", analisisActor);
    }
    
    @Override
    public void analisisActorxcasoUpdate(AnalisisActor analisisActor) {
        getSqlSession().update("gob.dp.simco.analisis.dao.AnalisisActorDao.analisisActorxcasoUpdate", analisisActor);
    }

    @Override
    public void analisisActorDelete(AnalisisActor analisisActor) {
        getSqlSession().delete("gob.dp.simco.analisis.dao.AnalisisActorDao.analisisActorDelete", analisisActor);
    }

    @Override
    public void analisisActorArchivar(AnalisisActor analisisActor) {
        getSqlSession().update("gob.dp.simco.analisis.dao.AnalisisActorDao.analisisActorArchivar", analisisActor);
    }

   
}
