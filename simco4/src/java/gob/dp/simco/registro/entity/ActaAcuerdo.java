/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class ActaAcuerdo implements Serializable{
    
    private Long id;
    
    private String descripcionItem;
    
    private Date fechaRegistro;
    
    private String comentario;
    
    private String codigo;
    
    private String tipo;
    
    private ActividadActaAcuerdo actividadActaAcuerdo;
    
    private String ruta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcionItem() {
        return descripcionItem;
    }

    public void setDescripcionItem(String descripcionItem) {
        this.descripcionItem = descripcionItem;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ActividadActaAcuerdo getActividadActaAcuerdo() {
        return actividadActaAcuerdo;
    }

    public void setActividadActaAcuerdo(ActividadActaAcuerdo actividadActaAcuerdo) {
        this.actividadActaAcuerdo = actividadActaAcuerdo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
}
