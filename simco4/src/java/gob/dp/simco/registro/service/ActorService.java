/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.bean.FiltroActor;
import gob.dp.simco.registro.entity.Actor;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ActorService {
    
    public void actorNuevo(Actor actor);
    
    public void actorModificar(Actor actor);
    
    public List<Actor> actorBuscar(FiltroActor filtroActor);
   
    public Integer actorTotalBuscar(FiltroActor filtroActor);
    
    public List<Actor> actorxActividadBuscar(Long idActividad);
    
    public List<Actor> actorxActividadBuscarTotal(Long idActividad);
    
    public Actor actorBuscarOne(Long idActor);
    
    public List<Actor> actorBuscarPaginado(FiltroActor filtroActor);
    
    public List<Actor> actorxAcuerdoDetalleBusqueda(Long idAcuerdoDetalle, int indicador);
    
    public List<Actor> actorxCasoBuscar(Long idCaso);
    
    public List<Actor> actorxCasoIntensidadBuscar(Long idCaso);
    
    public String actorTodosBuscarPaginado();
    
    public String actorEmpresaEntidadBuscarPaginado();
    
    public String actorTodosBuscarCaso(Long idCaso);
    
    public String actorXactividadSimpleBuscar(Long id);
    
    public List<Actor> actorBuscarSimple(Actor actor);
    
    public List<Actor> actorBuscarSimpleXactividad(Long idActividad);
    
    public Actor actorBuscarTotalSimple(Actor actor);
    
    public Integer actorBuscarTotalSimpleCount(Actor actor);
    
    public Integer actorBuscarTotalSimpleCountRUC(Actor actor);
    
    public List<Actor> actorxActaAcuerdoBuscar(long idAcuerdoDetalle);
    
    public int actorValidadDNI(Actor actor);
    
    public int actorValidadRUC(Actor actor);
    
    public List<Actor> actoresSigues(String codigoUsuario);
    
    public List<Actor> actorBuscarNombrePaginado(FiltroActor filtroActor);
    
    public Integer actorXactividadSimpleBuscarCount(Long idActividad);
}
