/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.entity;

import gob.dp.simco.seguimiento.entity.SeguimientoAcuerdo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ActaAcuerdoDetalle implements Serializable{
    
    private Long id;
    
    private String descripcion;
    
    private Date fechaCumplimiento;
    
    private String codigo;
    
    private Date fechaRegistro;
    
    private ActaAcuerdo actaAcuerdo;
    
    private List<Actor> listaActor;
    
    private List<Actor> listaActorFin;
    
    private Integer indiceModificacion;
    
    private SeguimientoAcuerdo seguimientoAcuerdo;
    
    private boolean indCumplimiento;
    
    private boolean indAlertar;
    
    private String color;
    
    private Actividad actividad;
    
    private Caso caso;
    
    private String usuarioRegistro;
    
    private boolean indCumplido;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCumplimiento() {
        return fechaCumplimiento;
    }

    public void setFechaCumplimiento(Date fechaCumplimiento) {
        this.fechaCumplimiento = fechaCumplimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ActaAcuerdo getActaAcuerdo() {
        return actaAcuerdo;
    }

    public void setActaAcuerdo(ActaAcuerdo actaAcuerdo) {
        this.actaAcuerdo = actaAcuerdo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Actor> getListaActor() {
        return listaActor;
    }

    public void setListaActor(List<Actor> listaActor) {
        this.listaActor = listaActor;
    }

    public Integer getIndiceModificacion() {
        return indiceModificacion;
    }

    public void setIndiceModificacion(Integer indiceModificacion) {
        this.indiceModificacion = indiceModificacion;
    }

    public SeguimientoAcuerdo getSeguimientoAcuerdo() {
        return seguimientoAcuerdo;
    }

    public void setSeguimientoAcuerdo(SeguimientoAcuerdo seguimientoAcuerdo) {
        this.seguimientoAcuerdo = seguimientoAcuerdo;
    }

    public boolean isIndAlertar() {
        return indAlertar;
    }

    public void setIndAlertar(boolean indAlertar) {
        this.indAlertar = indAlertar;
    }

    public boolean isIndCumplimiento() {
        return indCumplimiento;
    }

    public void setIndCumplimiento(boolean indCumplimiento) {
        this.indCumplimiento = indCumplimiento;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Actor> getListaActorFin() {
        return listaActorFin;
    }

    public void setListaActorFin(List<Actor> listaActorFin) {
        this.listaActorFin = listaActorFin;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public boolean isIndCumplido() {
        return indCumplido;
    }

    public void setIndCumplido(boolean indCumplido) {
        this.indCumplido = indCumplido;
    }

}
