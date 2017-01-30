/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.bean.FiltroActor;
import gob.dp.simco.registro.dao.ActorDao;
import gob.dp.simco.registro.entity.Actor;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActorServiceImpl implements ActorService{
    
    @Autowired
    private ActorDao actorDao;
    
    @Override
    public void actorNuevo(Actor actor) {
        actorDao.actorInsertar(actor);
    }

    @Override
    public void actorModificar(Actor actor) {
        actorDao.actorUpdate(actor);
    }

    @Override
    public List<Actor> actorBuscar(FiltroActor filtroActor) {
        return actorDao.actorBuscar(filtroActor);
    }

    @Override
    public Integer actorTotalBuscar(FiltroActor filtroActor) {
        return actorDao.actorTotalBuscar(filtroActor);
    }

    @Override
    public List<Actor> actorxActividadBuscar(Long idActividad) {
        return actorDao.actorxActividadBuscar(idActividad);
    }

    @Override
    public List<Actor> actorxActividadBuscarTotal(Long idActividad) {
        return actorDao.actorxActividadBuscarTotal(idActividad);
    }

    @Override
    public Actor actorBuscarOne(Long idActor) {
        Actor actor = new Actor();
        actor.setId(idActor);
        return actorDao.actorBuscarOne(actor);
    }

    @Override
    public List<Actor> actorBuscarPaginado(FiltroActor filtroActor) {
        return actorDao.actorBuscarPaginado(filtroActor);
    }

    @Override
    public List<Actor> actorxAcuerdoDetalleBusqueda(Long idAcuerdoDetalle, int indicador) {
        if(indicador == 1)
            return actorDao.actorxAcuerdoDetalleBusqueda(idAcuerdoDetalle);
        else
            return actorDao.actorxAcuerdoDetalleBusquedaFin(idAcuerdoDetalle);
    }

    @Override
    public List<Actor> actorxCasoBuscar(Long idCaso) {
        return actorDao.actorxCasoBuscar(idCaso);
    }

    @Override
    public List<Actor> actorxCasoIntensidadBuscar(Long idCaso) {
        return actorDao.actorxCasoIntensidadBuscar(idCaso);
    }

    @Override
    public String actorTodosBuscarPaginado() {
        List<Actor> lst = actorDao.actorTodosBuscar();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects = [");
        int i = 0;
        for(Actor a:lst){
            if(i > 0)
            stringBuilder.append(",");    
            stringBuilder.append("{value: ").append(a.getId()).append(",");
            stringBuilder.append("label: '").append(a.getNombre());
            if(StringUtils.isNotBlank(a.getApellidoPat()))
                stringBuilder.append(" ").append(a.getApellidoPat());
            if(StringUtils.isNotBlank(a.getApellidoMat()))    
                stringBuilder.append(" ").append(a.getApellidoMat());
            stringBuilder.append("' ").append(",");
            stringBuilder.append("desc: ").append("''").append(",");
            stringBuilder.append("icon: ").append("'' },");
            
        }
        stringBuilder.append("];");
        return stringBuilder.toString();
    }
    
    @Override
    public String actorEmpresaEntidadBuscarPaginado() {
        List<Actor> lst = actorDao.actorBuscarEmpresaEntidad();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects4 = [");
        int i = 0;
        for(Actor a:lst){
            if(i > 0)
            stringBuilder.append(",");    
            stringBuilder.append("{value: ").append(a.getId()).append(",");
            stringBuilder.append("label: '").append(a.getNombre());
            if(StringUtils.isNotBlank(a.getApellidoPat()))
                stringBuilder.append(" ").append(a.getApellidoPat());
            if(StringUtils.isNotBlank(a.getApellidoMat()))    
                stringBuilder.append(" ").append(a.getApellidoMat());
            stringBuilder.append("' ").append(",");
            stringBuilder.append("desc: ").append("''").append(",");
            stringBuilder.append("icon: ").append("'' },");
            
        }
        stringBuilder.append("];");
        return stringBuilder.toString();
    }

    @Override
    public String actorXactividadSimpleBuscar(Long id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects = [");
        int i = 0;
        for(Actor a:actorDao.actorXactividadSimpleBuscar(id)){
            if(i > 0)
            stringBuilder.append(",");    
            stringBuilder.append("{value: ").append(a.getId()).append(",");
            stringBuilder.append("label: '").append(a.getNombre());
            if(StringUtils.isNotBlank(a.getApellidoPat()))
                stringBuilder.append(" ").append(a.getApellidoPat());
            if(StringUtils.isNotBlank(a.getApellidoMat()))    
                stringBuilder.append(" ").append(a.getApellidoMat());
            stringBuilder.append("' ").append(",");
            stringBuilder.append("desc: ").append("''").append(",");
            stringBuilder.append("icon: ").append("'' },");
            
        }
        stringBuilder.append("];");
        return stringBuilder.toString();
    }

    @Override
    public String actorTodosBuscarCaso(Long idCaso) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects2 = [");
        int i = 0;
        for(Actor a:actorDao.actorxCasoBuscar(1L)){
            if(i > 0)
            stringBuilder.append(",");    
            stringBuilder.append("{value: ").append(a.getId()).append(",");
            stringBuilder.append("label: '").append(a.getNombre()).append("' ,");
            stringBuilder.append("desc: ").append("''").append(",");
            stringBuilder.append("icon: ").append("'' },");
            
        }
        stringBuilder.append("];");
        return stringBuilder.toString();
    }

    @Override
    public List<Actor> actorBuscarSimple(Actor actor) {
        return actorDao.actorBuscarSimple(actor);
    }

    @Override
    public List<Actor> actorBuscarSimpleXactividad(Long idActividad) {
        return actorDao.actorXactividadSimpleBuscar(idActividad);
    }

    @Override
    public Actor actorBuscarTotalSimple(Actor actor) {
        Integer val = actorDao.actorBuscarTotalSimpleCount(actor);
        if(val > 0)
            return actorDao.actorBuscarTotalSimple(actor);
        else
            return new Actor();
    }

    @Override
    public List<Actor> actorxActaAcuerdoBuscar(long idAcuerdoDetalle) {
        return actorDao.actorxActaAcuerdoBuscar(idAcuerdoDetalle);
    }

    @Override
    public Integer actorBuscarTotalSimpleCount(Actor actor) {
        return actorDao.actorBuscarTotalSimpleCount(actor);
    }
    
    @Override
    public Integer actorBuscarTotalSimpleCountRUC(Actor actor) {
        return actorDao.actorBuscarTotalSimpleCountRUC(actor);
    }

    @Override
    public int actorValidadDNI(Actor actor) {
        return actorDao.actorValidadDNI(actor);
    }

    @Override
    public int actorValidadRUC(Actor actor) {
        return actorDao.actorValidadRUC(actor);
    }

    @Override
    public List<Actor> actoresSigues(String codigoUsuario) {
        return actorDao.actoresSigues(codigoUsuario);
    }

    @Override
    public List<Actor> actorBuscarNombrePaginado(FiltroActor filtroActor) {
        return actorDao.actorBuscarNombrePaginado(filtroActor);
    }

    @Override
    public Integer actorXactividadSimpleBuscarCount(Long idActividad) {
        return actorDao.actorXactividadSimpleBuscarCount(idActividad);
    }
    
}
