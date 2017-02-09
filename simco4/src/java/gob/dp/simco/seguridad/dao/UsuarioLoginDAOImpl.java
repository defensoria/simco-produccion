package gob.dp.simco.seguridad.dao;

import gob.dp.simco.seguridad.entity.UsuarioLogin;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class UsuarioLoginDAOImpl extends SqlSessionDaoSupport implements UsuarioLoginDAO {

    @Override
    public Integer loginUsuario(UsuarioLogin login) {
        return getSqlSession().selectOne("gob.dp.simco.seguridad.dao.UsuarioLoginDAO.loginUsuario", login);
    }

    @Override
    public List<UsuarioLogin> buscarUsuarios(UsuarioLogin login) {
        return getSqlSession().selectList("gob.dp.simco.seguridad.dao.UsuarioLoginDAO.buscarUsuarios", login);
    }
    
}
