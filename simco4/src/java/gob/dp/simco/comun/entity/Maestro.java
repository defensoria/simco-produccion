/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class Maestro implements Serializable{
    
    private String valor;
    
    private String nombre;
    
    private Integer grupo;
    
    private Integer padre;

    public Maestro() {
    }

    public Maestro(Integer grupo) {
        this.grupo = grupo;
    }

    public Maestro(Integer grupo, Integer padre) {
        this.grupo = grupo;
        this.padre = padre;
    }

    public Maestro(String valor, Integer grupo) {
        this.valor = valor;
        this.grupo = grupo;
    }

    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Integer getPadre() {
        return padre;
    }

    public void setPadre(Integer padre) {
        this.padre = padre;
    }
    
    
    
}
