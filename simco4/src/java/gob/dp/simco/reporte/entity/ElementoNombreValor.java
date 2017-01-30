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
public class ElementoNombreValor implements Serializable{

    
    private String nombre;
    
    private Integer valor;
    
    private String porcentaje;

    public String getNombre() {
        return nombre;
    }

    public ElementoNombreValor() {
    }

    public ElementoNombreValor(String nombre, Integer valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
    
    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
  
}
