/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.entity;

import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class AnalisisActorTema implements Serializable{
    
    private Actor actor;
    
    private Caso caso;
    
    private Tema tema;
    
    private Integer nivel;
    
    private Date fechaRegistro;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
}
