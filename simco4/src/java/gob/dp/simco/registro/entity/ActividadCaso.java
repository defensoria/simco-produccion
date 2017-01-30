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
public class ActividadCaso implements Serializable{
    
    private Actividad actividad;
    
    private Caso caso;
    
    private String estado;

    public ActividadCaso(Actividad actividad, Caso caso, String estado) {
        this.actividad = actividad;
        this.caso = caso;
        this.estado = estado;
    }

    public ActividadCaso() {
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
