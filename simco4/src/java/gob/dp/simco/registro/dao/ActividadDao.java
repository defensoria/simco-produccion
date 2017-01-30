/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.Actividad;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ActividadDao {
    
   public void actividadInsertar(Actividad actividad);
   
   public void actividadUpdate(Actividad actividad);
   
   public Actividad actividadBuscarOne(Actividad actividad);
   
   public List<Actividad> actividadxActividadBuscar(Long idActividad);
    
   public List<Actividad> actividadxActividadBuscarTotal(Long idActividad);
   
   public List<Actividad> actividadxActorBuscar(Long idActor);
   
   public List<Actividad> actividadxActorBuscarTotal(Long idActor);
   
   public List<Actividad> actividadxCasoBuscar(Long idCaso);
   
   public List<Actividad> actividadxCasoBuscarTotal(Long idCaso);
   
   public List<Actividad> actividadxCasoBuscarTotalAC(Long idCaso);
   
   public List<Actividad> actividadxCasoBuscarTotalAD(Long idCaso);
   
   public List<Actividad> actividadxCodigoCasoBuscarTotal(String codigo);
   
   public List<Actividad> actividadxCodigoCasoBuscarTotalAD(String codigo);
   
   public List<Actividad> actividadxActaAcuerdoBuscar(Long idActaAcuerdo);
   
   public List<Actividad> actividadxActaAcuerdoBuscarTotal(Long idActaAcuerdo);
   
   public List<Actividad> actividadxMedioVerificacionBuscar(Long idMedioVerificacion) ;
   
   public List<Actividad> actividadxMedioVerificacionBuscarTotal(Long idMedioVerificacion);
   
   public List<Actividad> actividadBusquedaPaginado();
   
   public List<Actividad> actividadBusquedaSinCasoAC();
   
   public List<Actividad> actividadBusquedaSinCasoAD();
   
   public int actividadADCodigoGenerado();
   
   public Actividad actividadxCasoBuscarID(long idActividad);
   
   public List<Actividad> actividadBusquedaPorCasoAC(long idCaso);
   
   public void actividadUpdateAcontecimiento(Actividad actividad);
   
   public void actividadUpdateVincular(long idActividad);
   
   public void actividadUpdateDesVincular(long idActividad);
   
   public void actividadInactivar(long idActividad);
   
   public void actividadUpdateAcontecimientoQuitar(long idActividad);
   
   public Actividad actividadBusquedaPorAcontecimiento(long idAcontecimiento);
   
   public Integer contadorAcontecimiento(long idCaso);
   
   public Integer contadorActuacion(long idCaso);
   
}
