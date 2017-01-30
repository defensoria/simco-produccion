/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class ActorAcuerdo implements Serializable{
    
    private ActaAcuerdoDetalle actaAcuerdoDetalle;
    
    private Actor actor;
    
    private String estado;
    
    private String tipo;

    public ActaAcuerdoDetalle getActaAcuerdoDetalle() {
        return actaAcuerdoDetalle;
    }

    public void setActaAcuerdoDetalle(ActaAcuerdoDetalle actaAcuerdoDetalle) {
        this.actaAcuerdoDetalle = actaAcuerdoDetalle;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
