/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.dao;

import gob.dp.simco.comun.entity.Resumen;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ReporteDaoImpl extends SqlSessionDaoSupport implements ReporteDao{

    @Override
    public List<Resumen> graficoEstadoAnho(List<Integer> anhos) {
        return getSqlSession().selectList("gob.dp.simco.comun.dao.ReporteDao.graficoEstadoAnho", anhos);
    }

    @Override
    public int grafico001Activo(Resumen resumen) {
        return getSqlSession().selectOne("gob.dp.simco.comun.dao.ReporteDao.grafico001Activo", resumen);
    }

    @Override
    public List<Resumen> grafico001Meses(String anho) {
        return getSqlSession().selectList("gob.dp.simco.comun.dao.ReporteDao.grafico001Meses",anho);
    }

    @Override
    public int grafico001MesesCount(Resumen resumen) {
        return getSqlSession().selectOne("gob.dp.simco.comun.dao.ReporteDao.grafico001MesesCount", resumen);
    }

    @Override
    public List<Resumen> grafico001Departamento(String anho) {
        return getSqlSession().selectList("gob.dp.simco.comun.dao.ReporteDao.grafico001Departamento",anho);
    }

    @Override
    public int grafico001DepartamentoCount(Resumen resumen) {
        return getSqlSession().selectOne("gob.dp.simco.comun.dao.ReporteDao.grafico001DepartamentoCount", resumen);
    }

    @Override
    public int grafico004TipoCaso(Resumen resumen) {
        return getSqlSession().selectOne("gob.dp.simco.comun.dao.ReporteDao.grafico004TipoCaso", resumen);
    }

    @Override
    public int grafico005DepartamentoCount(Resumen resumen) {
        return getSqlSession().selectOne("gob.dp.simco.comun.dao.ReporteDao.grafico005DepartamentoCount", resumen);
    }

    @Override
    public int grafico006MesesCount(Resumen resumen) {
        return getSqlSession().selectOne("gob.dp.simco.comun.dao.ReporteDao.grafico006MesesCount", resumen);
    }

    @Override
    public List<Resumen> listadoAnhoCaso() {
        return getSqlSession().selectList("gob.dp.simco.comun.dao.ReporteDao.listadoAnhoCaso");
    }

    @Override
    public void cargaCasoMes(long idCodigoCarga) {
        getSqlSession().selectOne("gob.dp.simco.comun.dao.ReporteDao.cargaCasoMes",idCodigoCarga);
    }
    
}
