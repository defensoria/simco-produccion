/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.bean.FiltroActor;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.reporte.entity.FiltroReporte;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActorDaoImpl extends SqlSessionDaoSupport implements ActorDao {
    
    @Override
    public void actorInsertar(Actor actor) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActorDao.actorInsertar", actor);
    }

    @Override
    public void actorUpdate(Actor actor) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActorDao.actorUpdate", actor);
    }

    @Override
    public List<Actor> actorBuscar(FiltroActor filtroActor) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorBuscar",filtroActor);
    }

    @Override
    public Integer actorTotalBuscar(FiltroActor filtroActor) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActorDao.actorTotalBuscar",filtroActor);
    }

    @Override
    public List<Actor> actorxActividadBuscar(Long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorxActividadBuscar",idActividad);
    }

    @Override
    public List<Actor> actorxActividadBuscarTotal(Long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorxActividadBuscarTotal",idActividad);
    }

    @Override
    public Actor actorBuscarOne(Actor actor) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActorDao.actorBuscarOne",actor);
    }

    @Override
    public List<Actor> actorBuscarPaginado(FiltroActor filtroActor) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorBuscarPaginado",filtroActor);
    }

    @Override
    public List<Actor> actorxAcuerdoDetalleBusqueda(Long idAcuerdoDetalle) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorxAcuerdoDetalleBusqueda",idAcuerdoDetalle);
    }

    @Override
    public List<Actor> actorxCasoBuscar(Long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorxCasoBuscar",idCaso);
    }

    @Override
    public List<Actor> actorxCasoIntensidadBuscar(Long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorxCasoIntensidadBuscar",idCaso);
    }

    @Override
    public List<Actor> actorTodosBuscar() {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorTodosBuscar");
    }
    
    @Override
    public List<Actor> actorBuscarEmpresaEntidad() {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorBuscarEmpresaEntidad");
    }

    @Override
    public List<Actor> actorXactividadSimpleBuscar(Long id) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorXactividadSimpleBuscar",id);
    }

    @Override
    public List<Actor> actorBuscarSimple(Actor actor) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorBuscarSimple",actor);
    }

    @Override
    public Actor actorBuscarTotalSimple(Actor actor) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActorDao.actorBuscarTotalSimple",actor);
    }

    @Override
    public Integer actorBuscarTotalSimpleCount(Actor actor) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActorDao.actorBuscarTotalSimpleCount",actor);
    }
    
    @Override
    public Integer actorBuscarTotalSimpleCountRUC(Actor actor) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActorDao.actorBuscarTotalSimpleCountRUC",actor);
    }

    @Override
    public List<Actor> actorxActaAcuerdoBuscar(long idAcuerdoDetalle) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorxActaAcuerdoBuscar",idAcuerdoDetalle);
    }

    @Override
    public List<Actor> actorxAcuerdoDetalleBusquedaFin(Long idAcuerdoDetalle) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorxAcuerdoDetalleBusquedaFin",idAcuerdoDetalle);
    }

    @Override
    public int actorValidadDNI(Actor actor) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActorDao.actorValidadDNI",actor);
    }

    @Override
    public int actorValidadRUC(Actor actor) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActorDao.actorValidadRUC",actor);
    }

    @Override
    public List<Actor> actoresSigues(String codigoUsuario) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actoresSigues",codigoUsuario);
    }

    @Override
    public List<Actor> actorBuscarNombrePaginado(FiltroActor filtroActor) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actorBuscarNombrePaginado",filtroActor);
    }

    @Override
    public Integer actorXactividadSimpleBuscarCount(Long idActividad) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActorDao.actorXactividadSimpleBuscarCount",idActividad);
    }

    @Override
    public List<Actor> actoresPorCodigoCaso(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActorDao.actoresPorCodigoCaso",filtroReporte);
    }
    
}
