/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.seguimiento.dao;

import gob.dp.simco.seguimiento.entity.Alerta;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class AlertaDaoImpl extends SqlSessionDaoSupport implements AlertaDao{
    
    @Override
    public void alertaInsertar(Alerta alerta) {
        getSqlSession().insert("gob.dp.simco.seguimiento.dao.AlertaDao.alertaInsertar", alerta);
    }

    @Override
    public List<Alerta> alertaBuscar(Long idSeguimientoAcuerdo) {
        return getSqlSession().selectList("gob.dp.simco.seguimiento.dao.AlertaDao.alertaBuscar",idSeguimientoAcuerdo);
    }

    @Override
    public Alerta alertaBuscarDetalle(Long id) {
        return getSqlSession().selectOne("gob.dp.simco.seguimiento.dao.AlertaDao.alertaBuscarDetalle",id);
    }

    @Override
    public void alertaUpdate(long id) {
        getSqlSession().update("gob.dp.simco.seguimiento.dao.AlertaDao.alertaUpdate", id);
    }

    @Override
    public List<Alerta> alertaBuscarUsuario(Long idAcuerdo) {
        return getSqlSession().selectList("gob.dp.simco.seguimiento.dao.AlertaDao.alertaBuscarUsuario",idAcuerdo);
    }
    
}
