/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author carlos
 */
public class Problema implements Serializable{
    
    private Long id;
    
    private Long idCaso;
    
    private String sintesis;
    
    private String usuarioRegistro;
    
    private Date fechaRegistro;
    
    private String estado;
    
    private List<ProblemaDetalle> detalles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public String getSintesis() {
        return sintesis;
    }

    public void setSintesis(String sintesis) {
        this.sintesis = sintesis;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ProblemaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<ProblemaDetalle> detalles) {
        this.detalles = detalles;
    }
    
    
    
}
