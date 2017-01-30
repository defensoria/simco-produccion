/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.entity;

import gob.dp.simco.registro.entity.Actividad;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class IntervencionEtapaActuacion implements Serializable{
    
    private Long id;
    
    private String descripcion;
    
    private String estado;
    
    private IntervencionEtapa intervencionEtapa;
    
    private Actividad actividad;
    
    private Boolean indCheck;
    
    private Boolean indGSA;
    
    private Date fechaRegistro;
    
    private Date fechaCulminacion;
    
    //Agregados
    private Long ActividadId;
    
    private String nombreActividad;
    
    private String codigoActividad;
    
    private String fechaStringCulminacion;
    
    private String detalleReporte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public IntervencionEtapa getIntervencionEtapa() {
        return intervencionEtapa;
    }

    public void setIntervencionEtapa(IntervencionEtapa intervencionEtapa) {
        this.intervencionEtapa = intervencionEtapa;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getActividadId() {
        return ActividadId;
    }

    public void setActividadId(Long ActividadId) {
        this.ActividadId = ActividadId;
    }

    public Boolean getIndCheck() {
        return indCheck;
    }

    public void setIndCheck(Boolean indCheck) {
        this.indCheck = indCheck;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaCulminacion() {
        return fechaCulminacion;
    }

    public void setFechaCulminacion(Date fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getFechaStringCulminacion() {
        return fechaStringCulminacion;
    }

    public void setFechaStringCulminacion(String fechaStringCulminacion) {
        this.fechaStringCulminacion = fechaStringCulminacion;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public String getDetalleReporte() {
        return detalleReporte;
    }

    public void setDetalleReporte(String detalleReporte) {
        this.detalleReporte = detalleReporte;
    }

    public Boolean getIndGSA() {
        return indGSA;
    }

    public void setIndGSA(Boolean indGSA) {
        this.indGSA = indGSA;
    }

}
