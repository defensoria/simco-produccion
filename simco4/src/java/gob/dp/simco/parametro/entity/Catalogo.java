/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.parametro.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class Catalogo implements Serializable{
    
  private Integer numParametro;
  
  private Integer padreParametro;
  
  private String nombreParametro;
  
  private String valorParametro;
  
  private String codEstado;

  private String nombreEstado;
  
  private String descripcion;
  
  private String grupo;

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    /**
     * @return the numParametro
     */
    public Integer getNumParametro() {
        return numParametro;
    }

    /**
     * @param numParametro the numParametro to set
     */
    public void setNumParametro(Integer numParametro) {
        this.numParametro = numParametro;
    }

    /**
     * @return the padreParametro
     */
    public Integer getPadreParametro() {
        return padreParametro;
    }

    /**
     * @param padreParametro the padreParametro to set
     */
    public void setPadreParametro(Integer padreParametro) {
        this.padreParametro = padreParametro;
    }

    /**
     * @return the nombreParametro
     */
    public String getNombreParametro() {
        return nombreParametro;
    }

    /**
     * @param nombreParametro the nombreParametro to set
     */
    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    /**
     * @return the valorParametro
     */
    public String getValorParametro() {
        return valorParametro;
    }

    /**
     * @param valorParametro the valorParametro to set
     */
    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    /**
     * @return the codEstado
     */
    public String getCodEstado() {
        return codEstado;
    }

    /**
     * @param codEstado the codEstado to set
     */
    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }



}