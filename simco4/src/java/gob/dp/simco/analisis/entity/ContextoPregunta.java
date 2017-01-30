/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class ContextoPregunta implements Serializable{
    
    private Long id;
    
    private String idTipo;
    
    private String detalle;
    
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(String idTipo) {
        this.idTipo = idTipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
