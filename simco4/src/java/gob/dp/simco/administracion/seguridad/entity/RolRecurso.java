package gob.dp.simco.administracion.seguridad.entity;

import java.io.Serializable;

/**
 * @author Carlos
 *
 * Clase utilizada en el registro de Rol
 */
public class RolRecurso implements Serializable{
    private Rol rol=new Rol();
    private Recurso recurso=new Recurso();

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
   
}