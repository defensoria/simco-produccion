/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.entity;

import gob.dp.simco.registro.entity.Actividad;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author carlos
 */
public class IntervencionEtapa implements Serializable{
    
    private Long id;
    
    private String detalle;
    
    private String tipo;
    
    private String estado;
    
    private IntervencionAccion intervencionAccion;
    
    private Actividad actividad;
    
    private String descripcion;
    
    private Date fechaLimite;
    
    private Integer avance;
    
    private Long idIntervencion;
    
    //agregados o temporales
    private Long intervencionAccionId;
    
    private String intervencionAccionNombre;
    
    private String fechaLimiteString;
    
    private Integer listaActuacionesTotal;
    
    private Integer listaActuacionesEjecutadas;
    
    private String avanceString;
    
    private List<IntervencionEtapaActuacion> ieas;
    
    private List<IntervencionMiembro> ims;
    
    private String numero1;
    
    private String numero2;
    
    private String numero3;
    
    private String color;

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

    public IntervencionAccion getIntervencionAccion() {
        return intervencionAccion;
    }

    public void setIntervencionAccion(IntervencionAccion intervencionAccion) {
        this.intervencionAccion = intervencionAccion;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Integer getAvance() {
        return avance;
    }

    public void setAvance(Integer avance) {
        this.avance = avance;
    }

    public Long getIntervencionAccionId() {
        return intervencionAccionId;
    }

    public void setIntervencionAccionId(Long intervencionAccionId) {
        this.intervencionAccionId = intervencionAccionId;
    }

    public Long getIdIntervencion() {
        return idIntervencion;
    }

    public void setIdIntervencion(Long idIntervencion) {
        this.idIntervencion = idIntervencion;
    }

    public Integer getListaActuacionesTotal() {
        return listaActuacionesTotal;
    }

    public void setListaActuacionesTotal(Integer listaActuacionesTotal) {
        this.listaActuacionesTotal = listaActuacionesTotal;
    }

    public Integer getListaActuacionesEjecutadas() {
        return listaActuacionesEjecutadas;
    }

    public void setListaActuacionesEjecutadas(Integer listaActuacionesEjecutadas) {
        this.listaActuacionesEjecutadas = listaActuacionesEjecutadas;
    }

    public String getIntervencionAccionNombre() {
        return intervencionAccionNombre;
    }

    public void setIntervencionAccionNombre(String intervencionAccionNombre) {
        this.intervencionAccionNombre = intervencionAccionNombre;
    }

    public String getFechaLimiteString() {
        return fechaLimiteString;
    }

    public void setFechaLimiteString(String fechaLimiteString) {
        this.fechaLimiteString = fechaLimiteString;
    }

    public List<IntervencionEtapaActuacion> getIeas() {
        return ieas;
    }

    public void setIeas(List<IntervencionEtapaActuacion> ieas) {
        this.ieas = ieas;
    }

    public String getAvanceString() {
        return avanceString;
    }

    public void setAvanceString(String avanceString) {
        this.avanceString = avanceString;
    }

    public List<IntervencionMiembro> getIms() {
        return ims;
    }

    public void setIms(List<IntervencionMiembro> ims) {
        this.ims = ims;
    }

    public String getNumero1() {
        return numero1;
    }

    public void setNumero1(String numero1) {
        this.numero1 = numero1;
    }

    public String getNumero2() {
        return numero2;
    }

    public void setNumero2(String numero2) {
        this.numero2 = numero2;
    }

    public String getNumero3() {
        return numero3;
    }

    public void setNumero3(String numero3) {
        this.numero3 = numero3;
    }  

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
