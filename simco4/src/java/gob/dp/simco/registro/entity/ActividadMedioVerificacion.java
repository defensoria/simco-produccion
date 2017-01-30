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
public class ActividadMedioVerificacion implements Serializable{
    
    private Actividad actividad;
    
    private MedioVerificacion medioVerificacion;
    
    private String estado;

    public ActividadMedioVerificacion(Actividad actividad, MedioVerificacion medioVerificacion, String estado) {
        this.actividad = actividad;
        this.medioVerificacion = medioVerificacion;
        this.estado = estado;
    }

    public ActividadMedioVerificacion() {
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public MedioVerificacion getMedioVerificacion() {
        return medioVerificacion;
    }

    public void setMedioVerificacion(MedioVerificacion medioVerificacion) {
        this.medioVerificacion = medioVerificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
