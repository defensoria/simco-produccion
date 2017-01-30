/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.dao;

import gob.dp.simco.intervencion.entity.IntervencionMiembro;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class IntervencionMiembroDaoImpl extends SqlSessionDaoSupport implements IntervencionMiembroDao{
    
    @Override
    public void intervencionMiembroInsertar(IntervencionMiembro intervencionMiembro) {
        getSqlSession().insert("gob.dp.simco.intervencion.dao.IntervencionMiembroDao.intervencionMiembroInsertar", intervencionMiembro);
    }

    @Override
    public List<IntervencionMiembro> intervencionMiembroBuscar(Long idEtapa) {
        return getSqlSession().selectList("gob.dp.simco.intervencion.dao.IntervencionMiembroDao.intervencionMiembroBuscar",idEtapa); 
    }

    @Override
    public Integer intervencionMiembroCountEtapa(Long idEtapa) {
        return getSqlSession().selectOne("gob.dp.simco.intervencion.dao.IntervencionMiembroDao.intervencionMiembroCountEtapa",idEtapa); 
    }

    @Override
    public Integer intervencionMiembroValidaInsert(IntervencionMiembro intervencionMiembro) {
        return getSqlSession().selectOne("gob.dp.simco.intervencion.dao.IntervencionMiembroDao.intervencionMiembroValidaInsert",intervencionMiembro); 
    }

    @Override
    public void intervencionMiembroUpdate(IntervencionMiembro intervencionMiembro) {
        getSqlSession().update("gob.dp.simco.intervencion.dao.IntervencionMiembroDao.intervencionMiembroUpdate", intervencionMiembro);
    }

    @Override
    public Integer intervencionMiembroCodigoMiembro(IntervencionMiembro intervencionMiembro) {
        return getSqlSession().selectOne("gob.dp.simco.intervencion.dao.IntervencionMiembroDao.intervencionMiembroCodigoMiembro",intervencionMiembro); 
    }
    
}
