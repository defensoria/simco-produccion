/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.TercerNivel;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class TercerNivelDaoImpl extends SqlSessionDaoSupport implements TercerNivelDao {

    @Override
    public List<TercerNivel> tercerNivelBuscar(String tipo) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.TercerNivelDao.tercerNivelBuscar", tipo);
    }

    @Override
    public TercerNivel tercerNivelOne(String tipo) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.TercerNivelDao.tercerNivelOne", tipo);
    }
    
}
