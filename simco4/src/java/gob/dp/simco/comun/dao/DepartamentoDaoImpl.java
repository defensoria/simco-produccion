/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.dao;

import gob.dp.simco.comun.entity.Departamento;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class DepartamentoDaoImpl extends SqlSessionDaoSupport implements DepartamentoDao{
    
    @Override
    public List<Departamento> departamentoLista() {
        return getSqlSession().selectList("gob.dp.simco.comun.dao.DepartamentoDao.departamentoLista");
    }

    @Override
    public Departamento departamentoOne(String idDepartamento) {
        return getSqlSession().selectOne("gob.dp.simco.comun.dao.DepartamentoDao.departamentoOne",idDepartamento);
    }
    
}
