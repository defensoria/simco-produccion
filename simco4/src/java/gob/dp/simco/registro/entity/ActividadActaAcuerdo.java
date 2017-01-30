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
public class ActividadActaAcuerdo {
    
    private Actividad actividad;
    
    private ActaAcuerdo actaAcuerdo;
    
    private String estado;

    public ActividadActaAcuerdo(Actividad actividad, ActaAcuerdo actaAcuerdo, String estado) {
        this.actividad = actividad;
        this.actaAcuerdo = actaAcuerdo;
        this.estado = estado;
    }

    public ActividadActaAcuerdo() {
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public ActaAcuerdo getActaAcuerdo() {
        return actaAcuerdo;
    }

    public void setActaAcuerdo(ActaAcuerdo actaAcuerdo) {
        this.actaAcuerdo = actaAcuerdo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
