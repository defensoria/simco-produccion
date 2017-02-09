/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.seguridad.dao;
import gob.dp.simco.comun.util.Auditoria;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador
 */
@Repository
public class AuditoriaDaoImpl extends SqlSessionDaoSupport implements AuditoriaDao
{
    @Override
    public void insertarAuditoria(Auditoria filter){
        getSqlSession().insert("auditoriaDao.insertarAuditoria", filter);
    }

}
