/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.bean.FiltroMedioVerificacion;
import gob.dp.simco.registro.entity.MedioVerificacion;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class MedioVerificacionDaoImpl extends SqlSessionDaoSupport implements MedioVerificacionDao {

    @Override
    public void medioVerificacionInsertar(MedioVerificacion medioVerificacion){
        getSqlSession().insert("gob.dp.simco.registro.dao.MedioVerificacionDao.medioVerificacionInsertar", medioVerificacion);
    }

    @Override
    public void medioVerificacionUpdate(MedioVerificacion medioVerificacion) {
        getSqlSession().update("gob.dp.simco.registro.dao.MedioVerificacionDao.medioVerificacionUpdate", medioVerificacion);
    }

    @Override
    public Integer medioVerificacionTotalBuscar(FiltroMedioVerificacion filtroMedioVerificacion){
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.MedioVerificacionDao.medioVerificacionTotalBuscar",filtroMedioVerificacion);
    }

    @Override
    public List<MedioVerificacion> medioVerificacionxActividadBuscar(long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.MedioVerificacionDao.medioVerificacionxActividadBuscar",idActividad);
    }

    @Override
    public List<MedioVerificacion> medioVerificacionxActividadBuscarTotal(long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.MedioVerificacionDao.medioVerificacionxActividadBuscarTotal",idActividad);
    }

    @Override
    public MedioVerificacion medioVerificacionBuscarOne(MedioVerificacion medioVerificacion){
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.MedioVerificacionDao.medioVerificacionBuscarOne",medioVerificacion);
    }

    @Override
    public int medioVerificacionCodigoGenerado() {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.MedioVerificacionDao.medioVerificacionCodigoGenerado");
    }
    
}
