/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.dao;

import gob.dp.simco.comun.entity.Distrito;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class DistritoDaoImpl extends SqlSessionDaoSupport implements DistritoDao{
    
    @Override
    public List<Distrito> distritoLista(Distrito distrito) {
        return getSqlSession().selectList("gob.dp.simco.comun.dao.DistritoDao.distritoLista",distrito);
    }

    @Override
    public Distrito distritoOne(Distrito distrito) {
        return getSqlSession().selectOne("gob.dp.simco.comun.dao.DistritoDao.distritoOne",distrito);
    }
    
}
