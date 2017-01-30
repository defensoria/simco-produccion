/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.SegundoNivel;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class SegundoNivelDaoImpl extends SqlSessionDaoSupport implements SegundoNivelDao {

    @Override
    public List<SegundoNivel> segundoNivelBuscar(String tipo) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.SegundoNivelDao.segundoNivelBuscar", tipo);
    }

    @Override
    public SegundoNivel segundoNivelOne(String tipo) {
        return getSqlSession().selectOne("gob.dp.simco.registro.dao.SegundoNivelDao.segundoNivelOne", tipo);
    }
    
}
