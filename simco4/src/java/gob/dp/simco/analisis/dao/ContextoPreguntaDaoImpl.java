/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.ContextoPregunta;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ContextoPreguntaDaoImpl extends SqlSessionDaoSupport implements ContextoPreguntaDao {

    @Override
    public List<ContextoPregunta> contextoPreguntaBuscar(Long idTipo) {
        return getSqlSession().selectList("gob.dp.simco.analisis.dao.ContextoPreguntaDao.contextoPreguntaBuscar", idTipo);
    }
    
}
