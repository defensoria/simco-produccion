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
public class FiltroMedioVerificacion{
    
    private Long id;
    
    private String observacion;
    
    private String palabraClave;

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
