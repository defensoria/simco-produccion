/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.NoticiaRegistro;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class NoticiaRegistroDAOImpl extends SqlSessionDaoSupport implements NoticiaRegistroDAO {

    @Override
    public List<NoticiaRegistro> noticiaRegistroBuscar(long idActividad) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.NoticiaRegistroDAO.noticiaRegistroBuscar",idActividad);
    }

    @Override
    public void noticiaRegistroInsertar(NoticiaRegistro noticiaRegistro) {
        getSqlSession().insert("gob.dp.simco.registro.dao.NoticiaRegistroDAO.noticiaRegistroInsertar", noticiaRegistro);
    }

    @Override
    public void noticiaRegistroUpdate(NoticiaRegistro noticiaRegistro) {
        getSqlSession().update("gob.dp.simco.registro.dao.NoticiaRegistroDAO.noticiaRegistroUpdate", noticiaRegistro);
    }
    
}
