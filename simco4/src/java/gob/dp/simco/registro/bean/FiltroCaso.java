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
public class FiltroCaso {
    
    private Long id;
    
    private String nombre;
    
    private String descripcionPreliminar;
    
    private Long ini;
    
    private Long fin;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionPreliminar() {
        return descripcionPreliminar;
    }

    public void setDescripcionPreliminar(String descripcionPreliminar) {
        this.descripcionPreliminar = descripcionPreliminar;
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
