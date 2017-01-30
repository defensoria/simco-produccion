/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author carlos
 */
public class Campo implements Serializable{
    
    private Long id;
    
    private Long idInvestigacion;
    
    private String titulo;
    
    private String descripcion;
    
    private String comentario;
    
    private String estado;
    
    private Date fechaRegistro;
    
    private String usuarioRegistro;
    
    private List<CampoDetalle> listaDetalle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Long getIdInvestigacion() {
        return idInvestigacion;
    }

    public void setIdInvestigacion(Long idInvestigacion) {
        this.idInvestigacion = idInvestigacion;
    }

    public List<CampoDetalle> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<CampoDetalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    
}
