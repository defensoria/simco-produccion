/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.dao;

import gob.dp.simco.reporte.entity.FiltroReporte;
import gob.dp.simco.reporte.entity.ReporteSimcoCaso;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ReporteSimcoCasoDaoImpl extends SqlSessionDaoSupport implements ReporteSimcoCasoDao {

    @Override
    public List<ReporteSimcoCaso> reporteCasos(ReporteSimcoCaso reporteSimco) {
        return getSqlSession().selectList("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.reporteCasos",reporteSimco);
    }

    @Override
    public Integer cantidadAcuerdosCaso(long idCaso) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.cantidadAcuerdosCaso",idCaso);
    }

    @Override
    public Integer cantidadEmpresaMineraCaso(String codigoCaso) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.cantidadEmpresaMineraCaso",codigoCaso);
    }

    @Override
    public Integer cantidadMuertosPNP(String codigoCaso) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.cantidadMuertosPNP",codigoCaso);
    }

    @Override
    public Integer cantidadMuertosCiviles(String codigoCaso) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.cantidadMuertosCiviles",codigoCaso);
    }

    @Override
    public Integer cantidadMuertosFFAA(String codigoCaso) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.cantidadMuertosFFAA",codigoCaso);
    }

    @Override
    public Integer cantidadHeridosPNP(String codigoCaso) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.cantidadHeridosPNP",codigoCaso);
    }

    @Override
    public Integer cantidadHeridosCiviles(String codigoCaso) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.cantidadHeridosCiviles",codigoCaso);
    }

    @Override
    public Integer cantidadHeridosFFAA(String codigoCaso) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.cantidadMuertosPNP",codigoCaso);
    }
    
    @Override
    public List<ReporteSimcoCaso> listaCasosNuevosPorMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.listaCasosNuevosPorMes",filtroReporte);
    }

    @Override
    public List<ReporteSimcoCaso> listaCasosResueltosPorMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.reporte.dao.ReporteSimcoCasoDao.listaCasosResueltosPorMes",filtroReporte);
    }
    
    
}
