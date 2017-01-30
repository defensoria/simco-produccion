/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.seguimiento.entity;

import gob.dp.simco.registro.entity.ActaAcuerdoDetalle;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class SeguimientoAcuerdo implements Serializable{
    
    private Long id;
    
    private Date inicioDefinitivo;
    
    private Date finDefinitivo;
    
    private Date ultimaEjecucion;
    
    private String estado;
    
    private ActaAcuerdoDetalle actaAcuerdoDetalle;
    
    private String opcionales;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInicioDefinitivo() {
        return inicioDefinitivo;
    }

    public void setInicioDefinitivo(Date inicioDefinitivo) {
        this.inicioDefinitivo = inicioDefinitivo;
    }

    public Date getFinDefinitivo() {
        return finDefinitivo;
    }

    public void setFinDefinitivo(Date finDefinitivo) {
        this.finDefinitivo = finDefinitivo;
    }

    public Date getUltimaEjecucion() {
        return ultimaEjecucion;
    }

    public void setUltimaEjecucion(Date ultimaEjecucion) {
        this.ultimaEjecucion = ultimaEjecucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ActaAcuerdoDetalle getActaAcuerdoDetalle() {
        return actaAcuerdoDetalle;
    }

    public void setActaAcuerdoDetalle(ActaAcuerdoDetalle actaAcuerdoDetalle) {
        this.actaAcuerdoDetalle = actaAcuerdoDetalle;
    }

    public String getOpcionales() {
        return opcionales;
    }

    public void setOpcionales(String opcionales) {
        this.opcionales = opcionales;
    }
    
}
