/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.ProblemaDetalle;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ProblemaDetalleDAOImpl extends SqlSessionDaoSupport implements ProblemaDetalleDAO{

    @Override
    public void problemaDetalleInsertar(ProblemaDetalle problemaDetalle) {
        getSqlSession().insert("gob.dp.simco.analisis.dao.ProblemaDetalleDAO.problemaDetalleInsertar", problemaDetalle);
    }

    @Override
    public void problemaDetalleUpdate(ProblemaDetalle problemaDetalle) {
        getSqlSession().update("gob.dp.simco.analisis.dao.ProblemaDetalleDAO.problemaDetalleUpdate", problemaDetalle);
    }

    @Override
    public List<ProblemaDetalle> problemaDetalleBuscar(long idProblema) {
        return getSqlSession().selectList("gob.dp.simco.analisis.dao.ProblemaDetalleDAO.problemaDetalleBuscar", idProblema);
    }

    @Override
    public void problemaDetalleUpdateDatos(ProblemaDetalle problemaDetalle) {
        getSqlSession().update("gob.dp.simco.analisis.dao.ProblemaDetalleDAO.problemaDetalleUpdateDatos", problemaDetalle);
    }

   
}
