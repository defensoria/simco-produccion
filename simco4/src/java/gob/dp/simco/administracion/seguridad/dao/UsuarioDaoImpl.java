/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.administracion.seguridad.dao;

import gob.dp.simco.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador
 */
@Repository
public class UsuarioDaoImpl extends SqlSessionDaoSupport implements UsuarioDao {

    @Override
    public void insertarUsuario(Usuario usuario) {
        getSqlSession().insert("usuarioDao.insertarUsuario", usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        getSqlSession().update("usuarioDao.modificarUsuario", usuario);
    }

    @Override
    public void cambiarClaveUsuario(Usuario usuario) {
        getSqlSession().update("usuarioDao.cambiarClaveUsuario", usuario);
    }

    @Override
    public Integer loginUsuario(Usuario usuario) {
        return (Integer) getSqlSession().selectOne("usuarioDao.loginUsuario", usuario);
    }

    @Override
    public Usuario consultarUsuario(FiltroUsuario filtro) {
        return getSqlSession().selectOne("usuarioDao.consultarUsuario", filtro);
    }

    @Override
    public Integer getTotalBuscarUsuario(FiltroUsuario filtro) {
        return getSqlSession().selectOne("usuarioDao.getTotalBuscarUsuario", filtro);
    }

    @Override
    public List<Usuario> buscarUsuario(FiltroUsuario filtro) {
        return getSqlSession().selectList("usuarioDao.buscarUsuario", filtro);
    }

    @Override
    public List<Usuario> buscarUsuarioTotal() {
        return getSqlSession().selectList("usuarioDao.buscarUsuarioTotal");
    }
    
    @Override
    public Integer listaUsuarioCount(String codigoUsuario) {
        return getSqlSession().selectOne("usuarioDao.listaUsuarioCount",codigoUsuario);
    }
}
