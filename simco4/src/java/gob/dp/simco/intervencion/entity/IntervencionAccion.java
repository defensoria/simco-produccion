/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author carlos
 */
public class IntervencionAccion implements Serializable{
    
    private Long id;
    
    private String title;
    
    private String descripcion;
    
    private String color;
    
    private Intervencion intervencion;
    
    //agregados
    private String numero;
    
    private List<IntervencionEtapa> etapas;
    
    private String rutaReporte1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Intervencion getIntervencion() {
        return intervencion;
    }

    public void setIntervencion(Intervencion intervencion) {
        this.intervencion = intervencion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<IntervencionEtapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<IntervencionEtapa> etapas) {
        this.etapas = etapas;
    }

    public String getRutaReporte1() {
        return rutaReporte1;
    }

    public void setRutaReporte1(String rutaReporte1) {
        this.rutaReporte1 = rutaReporte1;
    }
    
    
}
