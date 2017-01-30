/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.entity;

import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class AnalisisRelacion {
    
    private Caso caso;
    
    private Actor actor1;
    
    private Actor actor2;
    
    private String tipo;
    
    private Date fechaRegistro;

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public Actor getActor1() {
        return actor1;
    }

    public void setActor1(Actor actor1) {
        this.actor1 = actor1;
    }

    public Actor getActor2() {
        return actor2;
    }

    public void setActor2(Actor actor2) {
        this.actor2 = actor2;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
}
