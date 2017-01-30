package gob.dp.simco.administracion.seguridad.dao;
import gob.dp.simco.administracion.seguridad.entity.Rol;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import gob.dp.simco.administracion.seguridad.entity.UsuarioRol;
import java.util.List;
import java.util.Map;

/**
 * Interface RolDao que realiza la relación entre el service y el sqlMap
 *
 * @author  Dante Antiporta
 * @version 1.0 - 10/12/2011
 * @since   1.0
 */
public interface RolDao {

   public List<Rol> buscarRol(Rol filtro);
   
   public List<Rol> buscarRolSegunUsuario(Usuario filtro);
   
   public void insertarUsuarioRol(UsuarioRol usuarioRol);
   
   public void deleteUsuarioRol(UsuarioRol usuarioRol);
   
   public Map<String,Rol> buscarMapRolSegunUsuario(Usuario filtro);

}