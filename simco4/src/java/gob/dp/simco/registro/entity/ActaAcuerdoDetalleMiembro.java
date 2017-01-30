/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class ActaAcuerdoDetalleMiembro implements Serializable{
    
    private Long id;
    
    private Long idActaAcuerdoDetalle;
    
    private String codigo;
    
    private String nombre;
    
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdActaAcuerdoDetalle() {
        return idActaAcuerdoDetalle;
    }

    public void setIdActaAcuerdoDetalle(Long idActaAcuerdoDetalle) {
        this.idActaAcuerdoDetalle = idActaAcuerdoDetalle;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
