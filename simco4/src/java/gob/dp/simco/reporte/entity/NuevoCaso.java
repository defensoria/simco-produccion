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
public class NuevoCaso implements Serializable{
    
    private String mes;
    
    private Integer resuelto;
    
    private Integer nuevo;
    
    private String anho;

    public NuevoCaso() {
    }

    public NuevoCaso(String mes, Integer resuelto, Integer nuevo, String anho) {
        this.mes = mes;
        this.resuelto = resuelto;
        this.nuevo = nuevo;
        this.anho = anho;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getResuelto() {
        return resuelto;
    }

    public void setResuelto(Integer resuelto) {
        this.resuelto = resuelto;
    }

    public Integer getNuevo() {
        return nuevo;
    }

    public void setNuevo(Integer nuevo) {
        this.nuevo = nuevo;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }
    
    
    
}
