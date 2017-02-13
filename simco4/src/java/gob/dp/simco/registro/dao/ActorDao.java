/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.bean.FiltroActor;
import gob.dp.simco.registro.entity.Actor;
import java.util.List;


/**
 *
 * @author carlos
 */
public interface ActorDao {
    
    public void actorInsertar(Actor actor);
    
    public void actorUpdate(Actor actor);
    
    public List<Actor> actorBuscar(FiltroActor filtroActor);
   
    public Integer actorTotalBuscar(FiltroActor filtroActor);
    
    public List<Actor> actorxActividadBuscar(Long idActividad);
    
    public List<Actor> actorxActividadBuscarTotal(Long idActividad);
    
    public Actor actorBuscarOne(Actor actor);
    
    public List<Actor> actorBuscarPaginado(FiltroActor filtroActor);
    
    public List<Actor> actorxAcuerdoDetalleBusqueda(Long idAcuerdoDetalle);
    
    public List<Actor> actorxAcuerdoDetalleBusquedaFin(Long idAcuerdoDetalle);
    
    public List<Actor> actorxCasoBuscar(Long idCaso);
    
    public List<Actor> actorxCasoIntensidadBuscar(Long idCaso);
    
    public List<Actor> actorTodosBuscar();
    
    public List<Actor> actorBuscarEmpresaEntidad();
    
    public List<Actor> actorXactividadSimpleBuscar(Long id);
    
    public List<Actor> actorBuscarSimple(Actor actor);
    
    public Actor actorBuscarTotalSimple(Actor actor);
    
    public Integer actorBuscarTotalSimpleCount(Actor actor);
    
    public Integer actorBuscarTotalSimpleCountRUC(Actor actor);
    
    public Integer actorXactividadSimpleBuscarCount(Long idActividad);
    
    public List<Actor> actorxActaAcuerdoBuscar(long idAcuerdoDetalle);
    
    public int actorValidadDNI(Actor actor);
    
    public int actorValidadRUC(Actor actor);
    
    public List<Actor> actoresSigues(String codigoUsuario);
    
    public List<Actor> actorBuscarNombrePaginado(FiltroActor filtroActor);
    
    public List<Actor> actoresPorCodigoCaso(String codigoUsuario);
    
}
