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
public class Participacion implements Serializable{
    
    private Long id;
    
    private Long idInvestigacion;
    
    private String estado;
    
    private String tipo;
    
    private String usuRegistro;
    
    private String usuarioCodigo;
    
    private String usuarioNombre;
    
    private String usuarioCargo;
    
    private Date fechaRegistro;
    
    private Date fechaModifica;

    public Long getIdInvestigacion() {
        return idInvestigacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public void setIdInvestigacion(Long idInvestigacion) {
        this.idInvestigacion = idInvestigacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuRegistro() {
        return usuRegistro;
    }

    public void setUsuRegistro(String usuRegistro) {
        this.usuRegistro = usuRegistro;
    }

    public String getUsuarioCodigo() {
        return usuarioCodigo;
    }

    public void setUsuarioCodigo(String usuarioCodigo) {
        this.usuarioCodigo = usuarioCodigo;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioCargo() {
        return usuarioCargo;
    }

    public void setUsuarioCargo(String usuarioCargo) {
        this.usuarioCargo = usuarioCargo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }
    
    
}
