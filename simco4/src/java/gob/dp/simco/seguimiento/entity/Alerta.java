/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.seguimiento.entity;

import gob.dp.simco.registro.entity.ActaAcuerdoDetalle;
import gob.dp.simco.registro.entity.Actividad;
import gob.dp.simco.registro.entity.Caso;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class Alerta implements Serializable{
    
    private Long id;
    
    private String descripcion;
    
    private Date fecha;
    
    private String estado;
    
    private SeguimientoAcuerdo seguimientoAcuerdo;
    
    private Caso caso;
    
    private Actividad actividad;
    
    private ActaAcuerdoDetalle actaAcuerdoDetalle;
    
    private Boolean esHoy;
    
    //agregados

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public SeguimientoAcuerdo getSeguimientoAcuerdo() {
        return seguimientoAcuerdo;
    }

    public void setSeguimientoAcuerdo(SeguimientoAcuerdo seguimientoAcuerdo) {
        this.seguimientoAcuerdo = seguimientoAcuerdo;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public Boolean getEsHoy() {
        return esHoy;
    }

    public void setEsHoy(Boolean esHoy) {
        this.esHoy = esHoy;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public ActaAcuerdoDetalle getActaAcuerdoDetalle() {
        return actaAcuerdoDetalle;
    }

    public void setActaAcuerdoDetalle(ActaAcuerdoDetalle actaAcuerdoDetalle) {
        this.actaAcuerdoDetalle = actaAcuerdoDetalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
