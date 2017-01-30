/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class IntervencionMiembro implements Serializable{
    
    private Long id;
    
    private String codigoUsuario;
    
    private String nombre;
    
    private String estado;
    
    private IntervencionEtapa intervencionEtapa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public IntervencionEtapa getIntervencionEtapa() {
        return intervencionEtapa;
    }

    public void setIntervencionEtapa(IntervencionEtapa intervencionEtapa) {
        this.intervencionEtapa = intervencionEtapa;
    }
    
    
    
}
