/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.entity;

/**
 *
 * @author carlos
 */
public class ActividadActor {
    
    private Actividad actividad;
    
    private Actor actor;
    
    private String tipoPartActor;
    
    private String estado;
    
    private String comentario;
    
    private String demanda;
    
    private String posicion;
    
    private Integer nivel;
    
    private String tipoRol;
    
    //tempora;
    private Long idCaso;

    public ActividadActor() {
    }

    public ActividadActor(Actividad actividad, Actor actor, String estado) {
        this.actividad = actividad;
        this.actor = actor;
        this.estado = estado;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getTipoPartActor() {
        return tipoPartActor;
    }

    public void setTipoPartActor(String tipoPartActor) {
        this.tipoPartActor = tipoPartActor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getDemanda() {
        return demanda;
    }

    public void setDemanda(String demanda) {
        this.demanda = demanda;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
    
    
}
