/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.entity;

import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class Tema implements Serializable{
    
    private Long id;
    
    private String detalle;
    
    private Caso caso;
    
    //ANADIDOS
    private Actor actor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
    
    
    
}
