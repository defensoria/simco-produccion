/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.PrimerNivel;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class PrimerNivelDaoImpl extends SqlSessionDaoSupport implements PrimerNivelDao {

    @Override
    public List<PrimerNivel> listarPrimerNivel(String tipo) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.PrimerNivelDao.primerNivelTodos", tipo);
    }

    @Override
    public PrimerNivel primerNivelOne(String tipo) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.PrimerNivelDao.primerNivelOne", tipo);
    }
    
}
