/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.administracion.seguridad.service;

import gob.dp.simco.administracion.seguridad.constantes.ConstantesAuditoria;
import gob.dp.simco.administracion.seguridad.dao.RecursoDao;
import gob.dp.simco.administracion.seguridad.entity.Recurso;
import gob.dp.simco.administracion.seguridad.entity.Rol;
import gob.dp.simco.administracion.seguridad.entity.RolRecurso;
import gob.dp.simco.administracion.seguridad.entity.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */
@Service("recursoService")
public class RecursoServiceImpl implements RecursoService {

    @Autowired
    private RecursoDao recursoDao;

    @Autowired
    private AuditoriaService auditoriaService;

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @Override
    public List<Recurso> buscarRecurso(Recurso filtro) {
        return recursoDao.buscarRecurso(filtro);
    }

    @Override
    public List<Recurso> buscarRecursoSegunUsuario(Usuario filtro) {
        return recursoDao.buscarRecursoSegunUsuario(filtro);
    }

    @Override
    public Map<String, Recurso> buscarMapRecursoSegunUsuario(Usuario filtro) {
        return recursoDao.buscarMapRecursoSegunUsuario(filtro);
    }

    @Override
    public List<Recurso> buscarRecursosAsignados(Rol filtro) {
        List<Recurso> listaRecurso = recursoDao.buscarRecurso(new Recurso());
        List<Recurso> listaRecursoAsignado = recursoDao.buscarRecursoSegunRol(filtro);
        for (Recurso rec1 : listaRecurso) {

            for (Recurso rec2 : listaRecursoAsignado) {
                if (rec1.getCodigo().equals(rec2.getCodigo())) {
                    rec1.setSeleccionado(true);
                }
            }

        }

        return listaRecurso;
    }

    @Override
    public void asignarRecursosRol(Rol rol, List<Recurso> listaRecursoSeleccionado) {
        List<Recurso> lstRecursosNuevos = new ArrayList<>();
        List<Recurso> lstRecursosBD = recursoDao.buscarRecursoSegunRol(rol);

        boolean encontrado = false;
        Recurso val;
        Recurso recurso;
        //recorro los roles finales que deseo tener
        for (Iterator it = listaRecursoSeleccionado.iterator(); it.hasNext();) {
            val = (Recurso) it.next();
            encontrado = false;
            for (Iterator it2 = lstRecursosBD.iterator(); it2.hasNext();) {
                recurso = (Recurso) it2.next();
                if (val.getCodigo().equals(recurso.getCodigo())) {
                    it2.remove();
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                lstRecursosNuevos.add(val);
            }
        }

        //Agregamos los nuevos Roles
        RolRecurso rolRecurso;

        StringBuffer sbDetalle = new StringBuffer();
        for (int i = 0; i < lstRecursosNuevos.size(); i++) {
            sbDetalle.delete(0, sbDetalle.length());
            recurso = lstRecursosNuevos.get(i);
            rolRecurso = new RolRecurso();
            rolRecurso.setRecurso(recurso);
            rolRecurso.setRol(rol);
            this.recursoDao.insertarRolRecurso(rolRecurso);
            /*
             * Auditoria
             */
            sbDetalle.append("Rol:").append(rol.getCodigo()).append(",Asignar Recurso:").append(recurso.getCodigo());
            auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_ASIGNAR_RECURSO, sbDetalle.toString());
        }
        //Eliminamos los que sobran        
        for (int i = 0; i < lstRecursosBD.size(); i++) {
            sbDetalle.delete(0, sbDetalle.length());
            recurso = lstRecursosBD.get(i);
            rolRecurso = new RolRecurso();
            rolRecurso.setRecurso(recurso);
            rolRecurso.setRol(rol);
            this.recursoDao.deleteRolRecurso(rolRecurso);
            /*
             * Auditoria
             */
            sbDetalle.append("Rol:").append(rol.getCodigo()).append(", Quitar Recurso:").append(recurso.getCodigo());
            auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_QUITAR_RECURSO, sbDetalle.toString());
        }
    }
}
