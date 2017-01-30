/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.vo;

import gob.dp.simco.registro.entity.CasoRegion;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ReporteCasoVO {
    
    private String nombre;
    
    private String codigo;
    
    private String descripcion;
    
    private String justificacion;
    
    private String observacion;
    
    private String estado;
    
    private String competenciaEstatal;
    
    private String face;
    
    private String momentoDialogo;
    
    private String mecanismoDialogo;
    
    private String participacionDialogo;
    
    private String tipologia;
    
    private String tipoActividad;
    
    private String primerNivel;
    
    private String segundoNivel;
    
    private String tercerNivel;
    
    private String departamento;
    
    private String provincia;
    
    private String distrito;
    
    private List<ReporteActorVO> actores;
    
    private List<ReporteActuacionDefensorialVO> actuacionesDefensoriales;
    
    private List<ReporteActuacionDefensorialVO> acontecimientos;
    
    private List<CasoRegion> listaCasoRegiones;
    
    private String imagePath;

    public ReporteCasoVO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCompetenciaEstatal() {
        return competenciaEstatal;
    }

    public void setCompetenciaEstatal(String competenciaEstatal) {
        this.competenciaEstatal = competenciaEstatal;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getMomentoDialogo() {
        return momentoDialogo;
    }

    public void setMomentoDialogo(String momentoDialogo) {
        this.momentoDialogo = momentoDialogo;
    }

    public String getMecanismoDialogo() {
        return mecanismoDialogo;
    }

    public void setMecanismoDialogo(String mecanismoDialogo) {
        this.mecanismoDialogo = mecanismoDialogo;
    }

    public String getParticipacionDialogo() {
        return participacionDialogo;
    }

    public void setParticipacionDialogo(String participacionDialogo) {
        this.participacionDialogo = participacionDialogo;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getPrimerNivel() {
        return primerNivel;
    }

    public void setPrimerNivel(String primerNivel) {
        this.primerNivel = primerNivel;
    }

    public String getSegundoNivel() {
        return segundoNivel;
    }

    public void setSegundoNivel(String segundoNivel) {
        this.segundoNivel = segundoNivel;
    }

    public String getTercerNivel() {
        return tercerNivel;
    }

    public void setTercerNivel(String tercerNivel) {
        this.tercerNivel = tercerNivel;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public List<ReporteActorVO> getActores() {
        return actores;
    }

    public void setActores(List<ReporteActorVO> actores) {
        this.actores = actores;
    }

    public List<ReporteActuacionDefensorialVO> getActuacionesDefensoriales() {
        return actuacionesDefensoriales;
    }

    public void setActuacionesDefensoriales(List<ReporteActuacionDefensorialVO> actuacionesDefensoriales) {
        this.actuacionesDefensoriales = actuacionesDefensoriales;
    }

    public List<ReporteActuacionDefensorialVO> getAcontecimientos() {
        return acontecimientos;
    }

    public void setAcontecimientos(List<ReporteActuacionDefensorialVO> acontecimientos) {
        this.acontecimientos = acontecimientos;
    }

    public List<CasoRegion> getListaCasoRegiones() {
        return listaCasoRegiones;
    }

    public void setListaCasoRegiones(List<CasoRegion> listaCasoRegiones) {
        this.listaCasoRegiones = listaCasoRegiones;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    
}
