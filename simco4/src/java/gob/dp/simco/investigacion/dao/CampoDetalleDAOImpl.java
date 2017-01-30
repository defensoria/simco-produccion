/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.dao;

import gob.dp.simco.investigacion.entity.CampoDetalle;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class CampoDetalleDAOImpl extends SqlSessionDaoSupport implements CampoDetalleDAO{

    @Override
    public void campoDetalleInsertar(CampoDetalle campoDetalle) {
        getSqlSession().insert("gob.dp.simco.investigacion.dao.CampoDetalleDAO.campoDetalleInsertar", campoDetalle);
    }

    @Override
    public List<CampoDetalle> campoDetalleBuscar(Long idCampo) {
        return getSqlSession().selectList("gob.dp.simco.investigacion.dao.CampoDetalleDAO.campoDetalleBuscar", idCampo);
    }

    @Override
    public void campoDetalleUpdate(CampoDetalle campoDetalle) {
        getSqlSession().update("gob.dp.simco.investigacion.dao.CampoDetalleDAO.campoDetalleUpdate", campoDetalle);
    }
    
}
