/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class EstadoConflicto implements Serializable{
    
    private String mes;
    
    private Integer activo;
    
    private Integer latente;
    
    private Integer total;
    
    private String anho;

    public EstadoConflicto() {
    }

    public EstadoConflicto(String mes, Integer activo, Integer latente, Integer total, String anho) {
        this.mes = mes;
        this.activo = activo;
        this.latente = latente;
        this.total = total;
        this.anho = anho;
    }


    

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getLatente() {
        return latente;
    }

    public void setLatente(Integer latente) {
        this.latente = latente;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }
    
}
