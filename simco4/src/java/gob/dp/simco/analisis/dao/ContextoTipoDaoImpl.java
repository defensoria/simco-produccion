/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.ContextoTipo;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ContextoTipoDaoImpl extends SqlSessionDaoSupport implements ContextoTipoDao {

    @Override
    public List<ContextoTipo> contextoTipoBuscar() {
        return getSqlSession().selectList("gob.dp.simco.analisis.dao.ContextoTipoDao.contextoTipoBuscar");
    }
    
}
