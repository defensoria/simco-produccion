/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.bean;

/**
 *
 * @author carlos
 */
public class FiltroActor {
    
    private Long id;
    
    private String nombre;
    
    private String apellido;
    
    private String dni;
    
    private String ruc;
    
    private Long ini;
    
    private Long fin;

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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIni() {
        return ini;
    }

    public void setIni(Long ini) {
        this.ini = ini;
    }

    public Long getFin() {
        return fin;
    }

    public void setFin(Long fin) {
        this.fin = fin;
    }
    
}
