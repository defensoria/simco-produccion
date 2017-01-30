package gob.dp.simco.administracion.seguridad.dao;

import gob.dp.simco.administracion.seguridad.entity.RolRecurso;
import java.util.List;
import org.springframework.transaction.TransactionException;

public interface RolRecursoDao {
    
    public List buscarRolRecursoPorSistema(String codigoSistema,String codigoRol) throws TransactionException;
    
    public void asignarRolRecurso() throws TransactionException;
    
    public void aprobarRolRecurso() throws TransactionException;
    
    public void crearRolRecurso(RolRecurso rolRecurso) throws TransactionException;
    
    public void modificarEstadoRolRecurso(RolRecurso rolRecurso) throws TransactionException;
}
