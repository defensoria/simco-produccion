/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.dao;

import gob.dp.simco.intervencion.entity.IntervencionEtapa;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class IntervencionEtapaDaoImpl  extends SqlSessionDaoSupport implements IntervencionEtapaDao {
    
    @Override
    public void intervencionEtapaInsertar(IntervencionEtapa intervencionEtapa) {
        getSqlSession().insert("gob.dp.simco.intervencion.dao.IntervencionEtapaDao.intervencionEtapaInsertar", intervencionEtapa);
    }

    @Override
    public IntervencionEtapa intervencionEtapaBuscar(Long id) {
        return getSqlSession().selectOne("gob.dp.simco.intervencion.dao.IntervencionEtapaDao.intervencionEtapaBuscar",id); 
    }

    @Override
    public List<IntervencionEtapa> intervencionEtapaBuscarTipo(IntervencionEtapa intervencionEtapa) {
        return getSqlSession().selectList("gob.dp.simco.intervencion.dao.IntervencionEtapaDao.intervencionEtapaBuscarTipo",intervencionEtapa); 
    }

    @Override
    public void intervencionEtapaUpdate(IntervencionEtapa intervencionEtapa) {
        getSqlSession().update("gob.dp.simco.intervencion.dao.IntervencionEtapaDao.intervencionEtapaUpdate", intervencionEtapa);
    }

    @Override
    public void intervencionEtapaUpdateDetalle(IntervencionEtapa intervencionEtapa) {
        getSqlSession().update("gob.dp.simco.intervencion.dao.IntervencionEtapaDao.intervencionEtapaUpdateDetalle", intervencionEtapa);
    }

    @Override
    public void intervencionEtapaDetalleDelete(long idIntervencionEtapa) {
        getSqlSession().update("gob.dp.simco.intervencion.dao.IntervencionEtapaDao.intervencionEtapaDetalleDelete", idIntervencionEtapa);
    }

    @Override
    public List<IntervencionEtapa> intervencionEtapaxAccion(long idEtapaAccion) {
        return getSqlSession().selectList("gob.dp.simco.intervencion.dao.IntervencionEtapaDao.intervencionEtapaxAccion",idEtapaAccion); 
    }

    @Override
    public List<IntervencionEtapa> intervencionEtapaxIntervencion(long idIntervencion) {
        return getSqlSession().selectList("gob.dp.simco.intervencion.dao.IntervencionEtapaDao.intervencionEtapaxIntervencion",idIntervencion); 
    }
}
