/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class CasoActor implements Serializable{
    
    private Long idCaso;
    
    private Long idActor;
    
    private String tipo;
    
    private String estado;
    
    private Date fechaRegistro;
    
    private Actor actor;

    public CasoActor(Long idActor, String tipo) {
        this.idActor = idActor;
        this.tipo = tipo;
    }

    public CasoActor() {
    }
    
    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public Long getIdActor() {
        return idActor;
    }

    public void setIdActor(Long idActor) {
        this.idActor = idActor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    


    
    
}
