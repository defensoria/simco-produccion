/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.bean.FiltroCaso;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.reporte.entity.FiltroReporte;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class CasoDaoImpl extends SqlSessionDaoSupport implements CasoDao{
    
    @Override
    public void casoInsertar(Caso caso){
        getSqlSession().insert("gob.dp.simco.registro.dao.CasoDao.casoInsertar", caso);
    }

    @Override
    public void casoUpdate(Caso caso) {
        getSqlSession().update("gob.dp.simco.registro.dao.CasoDao.casoUpdate", caso);
    }

    @Override
    public List<Caso> casoBuscar(Caso caso){
        return getSqlSession().selectList("gob.dp.simco.registro.dao.CasoDao.casoBuscar",caso);
    }

    @Override
    public Integer casoTotalBuscar(FiltroCaso filtroCaso){
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.CasoDao.casoTotalBuscar",filtroCaso);
    }

    @Override
    public List<Caso> casoxActividadBuscar(long idActividad){
        return getSqlSession().selectList("gob.dp.simco.registro.dao.CasoDao.casoxActividadBuscar",idActividad);
    }

    @Override
    public List<Caso> casoxActividadBuscarTotal(long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.CasoDao.casoxActividadBuscarTotal",idActividad);
    }

    @Override
    public Caso casoBuscarOne(long idCaso) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.CasoDao.casoBuscarOne",idCaso);
    }

    @Override
    public Integer casoCodigoGenerado() {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.CasoDao.casoCodigoGenerado");
    }

    @Override
    public Caso casoxActaAcuerdoDetalle(long idActaAcuerdoDetalle) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.CasoDao.casoxActaAcuerdoDetalle",idActaAcuerdoDetalle);
    }

    @Override
    public void casoUpdateIndicador(Caso caso) {
        getSqlSession().update("gob.dp.simco.registro.dao.CasoDao.casoUpdateIndicador", caso);
    }

    @Override
    public List<Caso> reporteCaso(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.CasoDao.reporteCaso",filtroReporte);
    }

    @Override
    public void casoUpdateSistesis(Caso caso) {
        getSqlSession().update("gob.dp.simco.registro.dao.CasoDao.casoUpdateSistesis", caso);
    }

    @Override
    public List<Caso> buscarCasoXnombreCodigo(FiltroCaso filtroCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.CasoDao.buscarCasoXnombreCodigo",filtroCaso);
    }

    @Override
    public void casoUpdateAprobar(Caso caso) {
        getSqlSession().update("gob.dp.simco.registro.dao.CasoDao.casoUpdateAprobar", caso);
    }

    @Override
    public List<Caso> listadoCasosEstadoMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.CasoDao.listadoCasosEstadoMes",filtroReporte);
    }

    @Override
    public List<Caso> listaCasosAntesDeAprobado(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.CasoDao.listaCasosAntesDeAprobado",filtroReporte);
    }

}
