/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.dao;

import gob.dp.simco.investigacion.entity.HistorialActividad;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class HistorialActividadDAOImpl extends SqlSessionDaoSupport implements HistorialActividadDAO{

    @Override
    public void historialActividadInsert(HistorialActividad historialActividad) {
        try {
            getSqlSession().insert("gob.dp.simco.investigacion.dao.HistorialActividadDAO.historialActividadInsert", historialActividad);
        } catch (Exception e) {
        }
        
    }

    @Override
    public List<HistorialActividad> historialActividadBuscar(Long idInvestigacion) {
        return getSqlSession().selectList("gob.dp.simco.investigacion.dao.HistorialActividadDAO.historialActividadBuscar", idInvestigacion);
    }
    
}
