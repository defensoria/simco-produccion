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
public class HistorialActividad implements Serializable{
    
    private Long id;
    
    private Long idInvestigacion;
    
    private String usuario;
    
    private String nombre;
    
    private String descripcion;
    
    private Date fechaRegistro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdInvestigacion() {
        return idInvestigacion;
    }

    public void setIdInvestigacion(Long idInvestigacion) {
        this.idInvestigacion = idInvestigacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
    
}
