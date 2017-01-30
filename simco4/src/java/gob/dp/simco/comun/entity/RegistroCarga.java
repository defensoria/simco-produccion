/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class RegistroCarga implements Serializable{
    
    private Long id;
    
    private Date fecha;
    
    private String ultimoMes;
    
    private String ultimoAnho;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUltimoMes() {
        return ultimoMes;
    }

    public void setUltimoMes(String ultimoMes) {
        this.ultimoMes = ultimoMes;
    }

    public String getUltimoAnho() {
        return ultimoAnho;
    }

    public void setUltimoAnho(String ultimoAnho) {
        this.ultimoAnho = ultimoAnho;
    }
    
}
