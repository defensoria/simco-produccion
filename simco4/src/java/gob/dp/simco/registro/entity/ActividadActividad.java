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
public class ActividadActividad implements Serializable{

    private Actividad padre;
    
    private Actividad hijo;
    
    private String estado;

    public ActividadActividad(Actividad padre, Actividad hijo, String estado) {
        this.padre = padre;
        this.hijo = hijo;
        this.estado = estado;
    }

    public ActividadActividad() {
    }

    public Actividad getPadre() {
        return padre;
    }

    public void setPadre(Actividad padre) {
        this.padre = padre;
    }

    public Actividad getHijo() {
        return hijo;
    }

    public void setHijo(Actividad hijo) {
        this.hijo = hijo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
