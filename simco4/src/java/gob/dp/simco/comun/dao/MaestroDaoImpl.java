/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.dao;

import gob.dp.simco.comun.entity.Maestro;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class MaestroDaoImpl extends SqlSessionDaoSupport implements MaestroDao{

    @Override
    public List<Maestro> listaSimple(Maestro maestro) {
        return getSqlSession().selectList("gob.dp.simco.comun.dao.MaestroDao.listaSimple",maestro);
    }

    @Override
    public List<Maestro> listaCompuesta(Maestro maestro) {
        return getSqlSession().selectList("gob.dp.simco.comun.dao.MaestroDao.listaCompuesta",maestro);
    }

    @Override
    public Integer padreParametro(Maestro maestro) {
        return getSqlSession().selectOne("gob.dp.simco.comun.dao.MaestroDao.padreParametro",maestro);
    }
    
}
