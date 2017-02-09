/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.entity;

import gob.dp.simco.comun.util.Auditoria;
import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class Parametro extends Auditoria implements Serializable
{
    private Integer codigoParametro;
    
    private String nombreParametro;
    
    private String valorParametro;
    
    private Parametro padreParametro;
    
    private String descripcion;

    public Integer getCodigoParametro() {
        return codigoParametro;
    }

    public void setCodigoParametro(Integer codigoParametro) {
        this.codigoParametro = codigoParametro;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public Parametro getPadreParametro() {
        return padreParametro;
    }

    public void setPadreParametro(Parametro padreParametro) {
        this.padreParametro = padreParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

}
