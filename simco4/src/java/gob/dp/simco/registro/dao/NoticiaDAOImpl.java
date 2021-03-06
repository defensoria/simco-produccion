/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.Noticia;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class NoticiaDAOImpl extends SqlSessionDaoSupport implements NoticiaDAO{

    @Override
    public List<Noticia> listaNoticias(Noticia noticia) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.NoticiaDao.noticiaBuscar",noticia);
    }
    
}
