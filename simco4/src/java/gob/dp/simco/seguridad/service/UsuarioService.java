/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.seguridad.service;

import gob.dp.simco.seguridad.bean.FiltroUsuario;
import gob.dp.simco.seguridad.entity.Rol;
import gob.dp.simco.seguridad.entity.Usuario;

import java.util.List;

/**
 *
 * @author Administrador
 */
public interface UsuarioService {

    public List<Usuario> buscarUsuario(FiltroUsuario filtro);
    
    public List<Usuario> buscarUsuarioTotal();
    
    public String autocompletarUsuario();

    public Usuario consultarUsuario(FiltroUsuario filtro);

    public void insertarUsuario(Usuario usuario, List<Rol> listaRol);

    public void modificarUsuario(Usuario usuario, List<Rol> listaRol);

    public void cambiarClave(Usuario usuario);

    public Integer loginUsuario(Usuario usuario);

    public Integer getTotalBuscarUsuario(FiltroUsuario filtro);
    
    public void modificarUsuarioSimple(Usuario usuario);
    
    public Integer listaUsuarioCount(String codigoUsuario);
}
