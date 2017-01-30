/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.controller;

import java.util.Date;

/**
 *
 * @author carlos
 */
public class ActorTemp {
    
    private Long id;
    
    private String nombre;
    
    private String apellido;
    
    private String dni;
    
    private String ruc;
    
    private Boolean IndicadorActorExiste;
    
    private Date fechaNacimiento;
    
    private String lugarNacimiento;
    
    private String infoContacto;
    
    private String cargo;
    
    private String tipoActor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Boolean isIndicadorActorExiste() {
        return IndicadorActorExiste;
    }

    public void setIndicadorActorExiste(Boolean IndicadorActorExiste) {
        this.IndicadorActorExiste = IndicadorActorExiste;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getInfoContacto() {
        return infoContacto;
    }

    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipoActor() {
        return tipoActor;
    }

    public void setTipoActor(String tipoActor) {
        this.tipoActor = tipoActor;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    
}
