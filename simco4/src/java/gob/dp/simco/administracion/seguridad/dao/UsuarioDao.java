package gob.dp.simco.administracion.seguridad.dao;

import gob.dp.simco.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import java.util.List;

public interface UsuarioDao {

    public List<Usuario> buscarUsuario(FiltroUsuario filtro);
    
    public List<Usuario> buscarUsuarioTotal();
    
    public Usuario consultarUsuario(FiltroUsuario filtro);

    public void insertarUsuario(Usuario usuario);

    public void modificarUsuario(Usuario usuario);

    public void cambiarClaveUsuario(Usuario usuario);

    public Integer loginUsuario(Usuario usuario);

    public Integer getTotalBuscarUsuario(FiltroUsuario filtro);

    public Integer listaUsuarioCount(String codigoUsuario);
}
