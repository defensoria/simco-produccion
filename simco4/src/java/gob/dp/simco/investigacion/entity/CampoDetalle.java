/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class CampoDetalle implements Serializable{
    
    private Long id;
    
    private Long idCampo;
    
    private String comentario;
    
    private Date fechaRegistro;
    
    private String nombreDocumento;
    
    private String ruta;
    
    private String usuarioRegistro;
    
    private String nombreRegistro;
    
    private String nombreArchivo;
    
    private String descripcionAdministrador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(Long idCampo) {
        this.idCampo = idCampo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getNombreRegistro() {
        return nombreRegistro;
    }

    public void setNombreRegistro(String nombreRegistro) {
        this.nombreRegistro = nombreRegistro;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getDescripcionAdministrador() {
        return descripcionAdministrador;
    }

    public void setDescripcionAdministrador(String descripcionAdministrador) {
        this.descripcionAdministrador = descripcionAdministrador;
    }

}
