/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.Tema;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class TemaDaoImpl extends SqlSessionDaoSupport implements TemaDao{
    
    @Override
    public void temaInsertar(Tema tema) {
        getSqlSession().insert("gob.dp.simco.analisis.dao.TemaDao.temaInsertar", tema);
    }

    @Override
    public List<Tema> temaBuscar(Long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.analisis.dao.TemaDao.temaBuscar", idCaso);
    }

    @Override
    public Tema temaxidBuscar(Long idTema) {
        return getSqlSession().selectOne("gob.dp.simco.analisis.dao.TemaDao.temaxidBuscar", idTema);
    }

    @Override
    public List<Tema> temaxcasoxactorBuscar(Tema tema) {
        return getSqlSession().selectList("gob.dp.simco.analisis.dao.TemaDao.temaxcasoxactorBuscar", tema);
    }
    
}
