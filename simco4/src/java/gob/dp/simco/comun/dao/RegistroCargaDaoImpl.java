/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.dao;

import gob.dp.simco.comun.entity.RegistroCarga;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class RegistroCargaDaoImpl extends SqlSessionDaoSupport implements RegistroCargaDao {

    @Override
    public void registroCargaInsert(RegistroCarga registroCarga) {
        getSqlSession().insert("gob.dp.simco.comun.dao.RegistroCargaDao.registroCargaInsert",registroCarga);
    }

    @Override
    public RegistroCarga registroCargaBuscarUltimo() {
       return getSqlSession().selectOne("gob.dp.simco.comun.dao.RegistroCargaDao.registroCargaBuscarUltimo");
    }

    @Override
    public void registroCargaBuscarUpdate(RegistroCarga registroCarga) {
        getSqlSession().update("gob.dp.simco.comun.dao.RegistroCargaDao.registroCargaBuscarUpdate",registroCarga);
    }
    
}
