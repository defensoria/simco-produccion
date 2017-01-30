/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActaAcuerdoDetalleMiembro;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ActaAcuerdoDetalleMiembroDAOImpl extends SqlSessionDaoSupport implements ActaAcuerdoDetalleMiembroDAO{

    @Override
    public void actaAcuerdoDetalleMiembroInsertar(ActaAcuerdoDetalleMiembro actaAcuerdoDetalleMiembro) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActaAcuerdoDetalleMiembroDAO.actaAcuerdoDetalleMiembroInsertar", actaAcuerdoDetalleMiembro);
    }

    @Override
    public void actaAcuerdoDetalleMiembroUpdate(ActaAcuerdoDetalleMiembro actaAcuerdoDetalleMiembro) {
        getSqlSession().insert("gob.dp.simco.registro.dao.ActaAcuerdoDetalleMiembroDAO.actaAcuerdoDetalleMiembroUpdate", actaAcuerdoDetalleMiembro);
    }

    @Override
    public List<ActaAcuerdoDetalleMiembro> actaAcuerdoDetalleMiembroBuscar(long idActaAcuerdoDetalle) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.ActaAcuerdoDetalleMiembroDAO.actaAcuerdoDetalleMiembroBuscar", idActaAcuerdoDetalle);
    }
    
}
