/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.Actividad;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActividadDaoImpl extends SqlSessionDaoSupport implements ActividadDao{
   
    @Override
    public void actividadInsertar(Actividad actividad) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActividadDao.actividadInsertar", actividad);
    }

    @Override
    public void actividadUpdate(Actividad actividad) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadDao.actividadUpdate", actividad);
    }

    @Override
    public Actividad actividadBuscarOne(Actividad actividad) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActividadDao.actividadBuscarOne",actividad);
    }

    @Override
    public List<Actividad> actividadxActividadBuscar(Long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxActividadBuscar",idActividad);
    }

    @Override
    public List<Actividad> actividadxActividadBuscarTotal(Long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxActividadBuscarTotal",idActividad);
    }
    
    @Override
    public List<Actividad> actividadxActorBuscar(Long idActor) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxActorBuscar",idActor);
    }

    @Override
    public List<Actividad> actividadxActorBuscarTotal(Long idActor) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxActorBuscarTotal",idActor);
    }

    @Override
    public List<Actividad> actividadxCasoBuscar(Long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxCasoBuscar",idCaso);
    }

    @Override
    public List<Actividad> actividadxCasoBuscarTotal(Long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxCasoBuscarTotal",idCaso);
    }

    @Override
    public List<Actividad> actividadxActaAcuerdoBuscar(Long idActaAcuerdo) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxActaAcuerdoBuscar",idActaAcuerdo);
    }

    @Override
    public List<Actividad> actividadxActaAcuerdoBuscarTotal(Long idActaAcuerdo) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxActaAcuerdoBuscarTotal",idActaAcuerdo);
    }

    @Override
    public List<Actividad> actividadxMedioVerificacionBuscar(Long idMedioVerificacion) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxMedioVerificacionBuscar",idMedioVerificacion);
    }

    @Override
    public List<Actividad> actividadxMedioVerificacionBuscarTotal(Long idMedioVerificacion) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxMedioVerificacionBuscarTotal",idMedioVerificacion);
    }

    @Override
    public List<Actividad> actividadBusquedaPaginado() {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadBusquedaPaginado");
    }

    @Override
    public List<Actividad> actividadBusquedaSinCasoAC() {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadBusquedaSinCasoAC");
    }

    @Override
    public List<Actividad> actividadBusquedaSinCasoAD() {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadBusquedaSinCasoAD");
    }

    @Override
    public List<Actividad> actividadxCasoBuscarTotalAC(Long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxCasoBuscarTotalAC",idCaso);
    }

    @Override
    public List<Actividad> actividadxCasoBuscarTotalAD(Long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxCasoBuscarTotalAD",idCaso);
    }

    @Override
    public int actividadADCodigoGenerado() {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActividadDao.actividadADCodigoGenerado");
    }

    @Override
    public Actividad actividadxCasoBuscarID(long idActividad) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActividadDao.actividadxCasoBuscarID",idActividad);
    }

    @Override
    public List<Actividad> actividadxCodigoCasoBuscarTotal(String codigo) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxCodigoCasoBuscarTotal",codigo);
    }

    @Override
    public List<Actividad> actividadBusquedaPorCasoAC(long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadBusquedaPorCasoAC",idCaso);
    }

    @Override
    public void actividadUpdateAcontecimiento(Actividad actividad) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadDao.actividadUpdateAcontecimiento", actividad);
    }

    @Override
    public void actividadUpdateVincular(long idActividad) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadDao.actividadUpdateVincular", idActividad);
    }

    @Override
    public void actividadUpdateDesVincular(long idActividad) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadDao.actividadUpdateDesVincular", idActividad);
    }

    @Override
    public void actividadUpdateAcontecimientoQuitar(long idActividad) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadDao.actividadUpdateAcontecimientoQuitar", idActividad);
    }

    @Override
    public Actividad actividadBusquedaPorAcontecimiento(long idAcontecimiento) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActividadDao.actividadBusquedaPorAcontecimiento",idAcontecimiento);
    }

    @Override
    public Integer contadorAcontecimiento(long idCaso) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActividadDao.contadorAcontecimiento",idCaso);
    }

    @Override
    public Integer contadorActuacion(long idCaso) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.ActividadDao.contadorActuacion",idCaso);
    }

    @Override
    public List<Actividad> actividadxCodigoCasoBuscarTotalAD(String codigo) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActividadDao.actividadxCodigoCasoBuscarTotalAD",codigo);
    }

    @Override
    public void actividadInactivar(long idActividad) {
        getSqlSession().update("gob.dp.simco.registro.dao.ActividadDao.actividadInactivar", idActividad);
    }
    
}
