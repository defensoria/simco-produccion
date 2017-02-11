/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class Caso implements Serializable{
    
    private Long id;
    
    private String nombre;
    
    private String descripcionPreliminar;
    
    private String codigo;
    
    private String tipo;
    
    private String subTipo;
    
    private String justificacion;
    
    private String observaciones;
    
    private String estadoRegistro;
    
    private Date creacion;
    
    private Date modificacion;
    
    private String tipoAsunto;
    
    private String tipoEstado;
    
    private String adjuntiaDefensorial;
    
    private String usuarioRegistro;
    
    private String usuarioModificacion;
    
    private ActividadCaso actividadCaso;
    
    private String sintesisAnalisis;
    
    private String tipoDialogo;
    
    private String tipoMecanismo;
    
    private String tipoParticipacionCaso;
    
    private String idDepartamento;
    
    private String idProvincia;
    
    private String idDistrito;
    
    private String primerNivel;
    
    private String segundoNivel;
    
    private String tercerNivel;
    
    private String tipoMomentoDialogo;
    
    private Integer version;
    
    private String indicador;
    
    private Date fechaPublicacion;
    
    private Double inversionInvolucrada;
    
    private Date fechaInicio;
    
    private Date fechaFin;
    
    private String indAprobado;
    
    private String usuAprobado;
            
    private Date fechaAprobado;
    
    //AGREGADOS
    private String tipoCaso;
    
    private String nombreAdjuntiaDefensorial;
    
    private String nombreComisionadoRegistro;
    
    private String nombreDepartamento;
    
    private String nombreProvincia;
    
    private String nombreDistrito;
    

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

    public String getDescripcionPreliminar() {
        return descripcionPreliminar;
    }

    public void setDescripcionPreliminar(String descripcionPreliminar) {
        this.descripcionPreliminar = descripcionPreliminar;
    }

    public ActividadCaso getActividadCaso() {
        return actividadCaso;
    }

    public void setActividadCaso(ActividadCaso actividadCaso) {
        this.actividadCaso = actividadCaso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoCaso() {
        return tipoCaso;
    }

    public void setTipoCaso(String tipoCaso) {
        this.tipoCaso = tipoCaso;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public Date getModificacion() {
        return modificacion;
    }

    public void setModificacion(Date modificacion) {
        this.modificacion = modificacion;
    }

    public String getTipoAsunto() {
        return tipoAsunto;
    }

    public void setTipoAsunto(String tipoAsunto) {
        this.tipoAsunto = tipoAsunto;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public String getAdjuntiaDefensorial() {
        return adjuntiaDefensorial;
    }

    public void setAdjuntiaDefensorial(String adjuntiaDefensorial) {
        this.adjuntiaDefensorial = adjuntiaDefensorial;
    }

    public String getNombreAdjuntiaDefensorial() {
        return nombreAdjuntiaDefensorial;
    }

    public void setNombreAdjuntiaDefensorial(String nombreAdjuntiaDefensorial) {
        this.nombreAdjuntiaDefensorial = nombreAdjuntiaDefensorial;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public String getSintesisAnalisis() {
        return sintesisAnalisis;
    }

    public void setSintesisAnalisis(String sintesisAnalisis) {
        this.sintesisAnalisis = sintesisAnalisis;
    }

    public String getTipoDialogo() {
        return tipoDialogo;
    }

    public void setTipoDialogo(String tipoDialogo) {
        this.tipoDialogo = tipoDialogo;
    }

    public String getTipoMecanismo() {
        return tipoMecanismo;
    }

    public void setTipoMecanismo(String tipoMecanismo) {
        this.tipoMecanismo = tipoMecanismo;
    }

    public String getTipoParticipacionCaso() {
        return tipoParticipacionCaso;
    }

    public void setTipoParticipacionCaso(String tipoParticipacionCaso) {
        this.tipoParticipacionCaso = tipoParticipacionCaso;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(String subTipo) {
        this.subTipo = subTipo;
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

    public String getTipoMomentoDialogo() {
        return tipoMomentoDialogo;
    }

    public void setTipoMomentoDialogo(String tipoMomentoDialogo) {
        this.tipoMomentoDialogo = tipoMomentoDialogo;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNombreComisionadoRegistro() {
        return nombreComisionadoRegistro;
    }

    public void setNombreComisionadoRegistro(String nombreComisionadoRegistro) {
        this.nombreComisionadoRegistro = nombreComisionadoRegistro;
    }

    public Double getInversionInvolucrada() {
        return inversionInvolucrada;
    }

    public void setInversionInvolucrada(Double inversionInvolucrada) {
        this.inversionInvolucrada = inversionInvolucrada;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getIndAprobado() {
        return indAprobado;
    }

    public void setIndAprobado(String indAprobado) {
        this.indAprobado = indAprobado;
    }

    public String getUsuAprobado() {
        return usuAprobado;
    }

    public void setUsuAprobado(String usuAprobado) {
        this.usuAprobado = usuAprobado;
    }

    public Date getFechaAprobado() {
        return fechaAprobado;
    }

    public void setFechaAprobado(Date fechaAprobado) {
        this.fechaAprobado = fechaAprobado;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }
    
}
