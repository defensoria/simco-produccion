/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.noticia.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class Noticia implements Serializable{

    private Long id;
    
    private String link; 
    
    private String descripcion; 
    
    private String empresa; 
    
    private String fechaPublica; 
    
    private Date fechaRegistro; 
    
    private String titulo; 
    
    private String region; 
    
    private Long indexCount;
    
    private Date fechaPublicFormat;
    
    //temporales
    
    private long ini;
    
    private long fin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFechaPublica() {
        return fechaPublica;
    }

    public void setFechaPublica(String fechaPublica) {
        this.fechaPublica = fechaPublica;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getIni() {
        return ini;
    }

    public void setIni(long ini) {
        this.ini = ini;
    }

    public long getFin() {
        return fin;
    }

    public void setFin(long fin) {
        this.fin = fin;
    }

    public Long getIndexCount() {
        return indexCount;
    }

    public void setIndexCount(Long indexCount) {
        this.indexCount = indexCount;
    }

    public Date getFechaPublicFormat() {
        return fechaPublicFormat;
    }

    public void setFechaPublicFormat(Date fechaPublicFormat) {
        this.fechaPublicFormat = fechaPublicFormat;
    }
    
}
