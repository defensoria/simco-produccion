/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ContextoTipo implements Serializable{
    
    private Long id;
    
    private String detalle;
    
    private String estado;
    
    private List<Contexto> respuesta;

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Contexto> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<Contexto> respuesta) {
        this.respuesta = respuesta;
    }

    
}
