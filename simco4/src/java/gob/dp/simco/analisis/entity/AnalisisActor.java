/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.entity;

import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author carlos
 */
public class AnalisisActor implements Serializable{
    
    private Caso caso;
    
    private Actor actor;
    
    private String resumen;
    
    private Date fechaRegistro;
    
    private Date fechaModificacion;
    
    private String usuarioRegistro;
    
    private String tipo;
    
    private Integer nivel;
    
    private List<Tema> temas;
    
    private Integer archivado;

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getArchivado() {
        return archivado;
    }

    public void setArchivado(Integer archivado) {
        this.archivado = archivado;
    }
    
}
