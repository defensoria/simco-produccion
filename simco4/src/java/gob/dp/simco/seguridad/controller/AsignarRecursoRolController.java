package gob.dp.simco.seguridad.controller;

import java.util.ArrayList;
import java.util.List;

import gob.dp.simco.seguridad.entity.Recurso;
import gob.dp.simco.seguridad.entity.Rol;
import gob.dp.simco.seguridad.service.RecursoService;
import gob.dp.simco.seguridad.service.RolService;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import java.io.Serializable;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class AsignarRecursoRolController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(AsignarRecursoRolController.class);
    
    private Rol rol;
    
    private List<Recurso> listaRecurso;
    
    @Autowired
    private RecursoService recursoService;
    
    @Autowired
    private RolService rolService;

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    public void setRecursoService(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    public List<Recurso> getListaRecurso() {
        if (this.listaRecurso == null) {
            this.listaRecurso = new ArrayList<>();
        }
        return listaRecurso;
    }

    public void setListaRecurso(List<Recurso> listaRecurso) {
        this.listaRecurso = listaRecurso;
    }

    public String verAsignarRecursoRol(String codigo) {
        Rol filter = new Rol();
        filter.setCodigo(codigo);
        rol = rolService.buscarRol(filter).get(0);
        listaRecurso = new ArrayList<>();
        listaRecurso = recursoService.buscarRecursosAsignados(rol);
        return "verAsignarRecursoRol";
    }

    public void asignarRecursosRol() {
        List<Recurso> listaSeleccionados = new ArrayList<>();
        for (Recurso rec : this.listaRecurso) {
            if (rec.isSeleccionado()) {
                listaSeleccionados.add(rec);
            }
        }
        try {
            recursoService.asignarRecursosRol(rol, listaSeleccionados);
            msg.messageInfo("Se realizaron los cambios correctamente", null);
        } catch (Exception ex) {
            log.error("Ocurrio un error:" + ex.getMessage(), ex);
        }
    }

    public String regresar() {
        return "rolLista";
    }

    public Rol getRol() {
        if (this.rol == null) {
            this.rol = new Rol();
        }
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
