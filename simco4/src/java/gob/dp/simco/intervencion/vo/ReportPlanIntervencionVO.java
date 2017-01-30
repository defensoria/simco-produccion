/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.vo;

import gob.dp.simco.intervencion.entity.IntervencionAccion;
import gob.dp.simco.intervencion.entity.IntervencionEtapa;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ReportPlanIntervencionVO {
    
    private String descripcion;

    private String nombre;
    
    private List<IntervencionAccion> acciones;
    
    private List<IntervencionEtapa> etapas;
    
    private List<IntervencionAccion> accionesSeleccionadas;
    
    private List<IntervencionEtapa> etapasTotales;
    
    private String imagePath;
    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<IntervencionAccion> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<IntervencionAccion> acciones) {
        this.acciones = acciones;
    }

    public List<IntervencionEtapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<IntervencionEtapa> etapas) {
        this.etapas = etapas;
    }

    public List<IntervencionAccion> getAccionesSeleccionadas() {
        return accionesSeleccionadas;
    }

    public void setAccionesSeleccionadas(List<IntervencionAccion> accionesSeleccionadas) {
        this.accionesSeleccionadas = accionesSeleccionadas;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<IntervencionEtapa> getEtapasTotales() {
        return etapasTotales;
    }

    public void setEtapasTotales(List<IntervencionEtapa> etapasTotales) {
        this.etapasTotales = etapasTotales;
    }
    
    
    
    
}
