/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.entity;

import gob.dp.simco.seguridad.entity.Usuario;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class Actividad implements Serializable{
    
    private Long id;
    
    private String nombre;
    
    private String descripcion;
    
    private String lugarRealizacion;
    
    private Date fechaRealizacionIni;
    
    private Date fechaRealizacionFin;
    
    private Date horaRealizacionIni;
    
    private Date horaRealizacionFin;
    
    private String tipoActividad;
    
    private String justificacionIntervencion;
    
    private String comentario;
    
    private String codigoActividad;
    
    private Boolean indCurso;
    
    private String idDepartamento;
    
    private String idProvincia;
    
    private String idDistrito;
    
    private Date fechaRegistro;
    
    private String tipo;
    
    private Date fechaModificacion;
    
    private String usuarioRegistro;
    
    private String usuarioModificacion;
    
    private Integer version;
    
    private Double coordenadaX;
    
    private Double coordenadaY;
    
    private Integer zoom;
    
    private String ruta;
    
    private ActividadActividad actividadActividad;
    
    private ActividadActor actividadActor;
    
    private ActividadCaso actividadCaso;
    
    private ActividadActaAcuerdo actividadActaAcuerdo;
    
    private ActividadMedioVerificacion actividadMedioVerificacion;
    
    private Usuario usuario;
    
    private String tipoAcontecimiento;
    
    private String tipoViolencia;
    
    private String resultadoViolencia;
    
    private Integer indiceActivo;
    
    //agregados
    
    private String nombreCaso;
    
    private Long idCaso;
    
    private Long idAcontecimiento;
    
    private Integer indiceVinculado;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugarRealizacion() {
        return lugarRealizacion;
    }

    public void setLugarRealizacion(String lugarRealizacion) {
        this.lugarRealizacion = lugarRealizacion;
    }
    
    public String getJustificacionIntervencion() {
        return justificacionIntervencion;
    }

    public void setJustificacionIntervencion(String justificacionIntervencion) {
        this.justificacionIntervencion = justificacionIntervencion;
    }
    
    public Date getFechaRealizacionIni() {
        return fechaRealizacionIni;
    }

    public void setFechaRealizacionIni(Date fechaRealizacionIni) {
        this.fechaRealizacionIni = fechaRealizacionIni;
    }

    public Date getFechaRealizacionFin() {
        return fechaRealizacionFin;
    }

    public void setFechaRealizacionFin(Date fechaRealizacionFin) {
        this.fechaRealizacionFin = fechaRealizacionFin;
    }

    public Date getHoraRealizacionIni() {
        return horaRealizacionIni;
    }

    public void setHoraRealizacionIni(Date horaRealizacionIni) {
        this.horaRealizacionIni = horaRealizacionIni;
    }

    public Date getHoraRealizacionFin() {
        return horaRealizacionFin;
    }

    public void setHoraRealizacionFin(Date horaRealizacionFin) {
        this.horaRealizacionFin = horaRealizacionFin;
    }

    public ActividadActividad getActividadActividad() {
        return actividadActividad;
    }

    public void setActividadActividad(ActividadActividad actividadActividad) {
        this.actividadActividad = actividadActividad;
    }

    public ActividadActor getActividadActor() {
        return actividadActor;
    }

    public void setActividadActor(ActividadActor actividadActor) {
        this.actividadActor = actividadActor;
    }

    public ActividadCaso getActividadCaso() {
        return actividadCaso;
    }

    public void setActividadCaso(ActividadCaso actividadCaso) {
        this.actividadCaso = actividadCaso;
    }

    public ActividadActaAcuerdo getActividadActaAcuerdo() {
        return actividadActaAcuerdo;
    }

    public void setActividadActaAcuerdo(ActividadActaAcuerdo actividadActaAcuerdo) {
        this.actividadActaAcuerdo = actividadActaAcuerdo;
    }

    public ActividadMedioVerificacion getActividadMedioVerificacion() {
        return actividadMedioVerificacion;
    }

    public void setActividadMedioVerificacion(ActividadMedioVerificacion actividadMedioVerificacion) {
        this.actividadMedioVerificacion = actividadMedioVerificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public Boolean getIndCurso() {
        return indCurso;
    }

    public void setIndCurso(Boolean indCurso) {
        this.indCurso = indCurso;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipoAcontecimiento() {
        return tipoAcontecimiento;
    }

    public void setTipoAcontecimiento(String tipoAcontecimiento) {
        this.tipoAcontecimiento = tipoAcontecimiento;
    }

    public String getTipoViolencia() {
        return tipoViolencia;
    }

    public void setTipoViolencia(String tipoViolencia) {
        this.tipoViolencia = tipoViolencia;
    }

    public String getResultadoViolencia() {
        return resultadoViolencia;
    }

    public void setResultadoViolencia(String resultadoViolencia) {
        this.resultadoViolencia = resultadoViolencia;
    }

    public Long getIdAcontecimiento() {
        return idAcontecimiento;
    }

    public void setIdAcontecimiento(Long idAcontecimiento) {
        this.idAcontecimiento = idAcontecimiento;
    }

    public Integer getIndiceVinculado() {
        return indiceVinculado;
    }

    public void setIndiceVinculado(Integer indiceVinculado) {
        this.indiceVinculado = indiceVinculado;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public Integer getIndiceActivo() {
        return indiceActivo;
    }

    public void setIndiceActivo(Integer indiceActivo) {
        this.indiceActivo = indiceActivo;
    }
    
}
