package gob.dp.simco.seguridad.dao;
import gob.dp.simco.seguridad.entity.Recurso;
import gob.dp.simco.seguridad.entity.Rol;
import gob.dp.simco.seguridad.entity.RolRecurso;
import gob.dp.simco.seguridad.entity.Usuario;
import java.util.List;
import java.util.Map;


/**
 * Interface RecursoDao que realiza la relación entre el service y el sqlMap
 * @author Dante
 */

public interface RecursoDao {

    public List<Recurso> buscarRecurso(Recurso filtro);
    
    public List<Recurso> buscarRecursoSegunUsuario(Usuario filtro);
    
    public Map<String, Recurso> buscarMapRecursoSegunUsuario(Usuario filtro);
    
    public List<Recurso> buscarRecursoSegunRol(Rol filtro);
    
    public void insertarRolRecurso(RolRecurso rolRecurso);
    
    public void deleteRolRecurso(RolRecurso rolRecurso);
 
}