/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.entity;

import gob.dp.simco.analisis.entity.AnalisisActorIntensidad;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author carlos
 */
public class Actor implements Serializable{
    
    private Long id;
    
    private String nombre;
    
    private String apellidoPat;
    
    private String apellidoMat;
    
    private String direccion;
    
    private String sexo;
    
    private String dni;
    
    private String ruc;
    
    private Date fechaNacimiento;
    
    private String lugarNacimiento;
    
    private String infoContacto;
    
    private String cargo;
    
    private String telefono;
    
    private String anexo;
    
    private String email;
    
    private String idDepartamento;
    
    private String idProvincia;
    
    private String idDistrito;
    
    private Parametro tipoActor;
    
    private ActividadActor actividadActor;
    
    private Caso caso;
    
    private Actividad actividad;
    
    private AnalisisActorIntensidad analisisActorIntensidad;
    
    private String tipoAcuerdoDetalle;
    
    private String empresaTipo;
    
    private String empresaAmbito;
    
    private String observaciones;
    
    private String entidadTipo;
    
    private String tipoGeneral;
    
    private List<ActorMiembro> actorMiembros; 
    
    private String tipoPoblacion;
    
    private String subTipo1Poblacion;
    
    private String subTipo2Poblacion;
    
    private String tipoEstado;
    
    private String subTipo1Estado;
    
    private String subTipo2Estado;
    
    private String tipoEmpresa;
    
    private String subTipo1Empresa;
    
    private String subTipo2Empresa;
    
    private String tipoOrganizacion;
    
    private String subTipo1Organizacion;
    
    private String subTipo2Organizacion;
    
    private String subTipo3Empresa;
    
    private String clasificacion;
    
    private String usuarioRegistro;
    
    private Date fechaRegistro;
    
    //temporal
    private Double promedio;
    
    private Boolean validaPromedio;
    
    private String nombreCaso;
    
    private String nombreActividad;

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getInfoContacto() {
        return infoContacto;
    }

    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Parametro getTipoActor() {
        return tipoActor;
    }

    public void setTipoActor(Parametro tipoActor) {
        this.tipoActor = tipoActor;
    }

    public ActividadActor getActividadActor() {
        return actividadActor;
    }

    public void setActividadActor(ActividadActor actividadActor) {
        this.actividadActor = actividadActor;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public AnalisisActorIntensidad getAnalisisActorIntensidad() {
        return analisisActorIntensidad;
    }

    public void setAnalisisActorIntensidad(AnalisisActorIntensidad analisisActorIntensidad) {
        this.analisisActorIntensidad = analisisActorIntensidad;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
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

    public String getTipoAcuerdoDetalle() {
        return tipoAcuerdoDetalle;
    }

    public void setTipoAcuerdoDetalle(String tipoAcuerdoDetalle) {
        this.tipoAcuerdoDetalle = tipoAcuerdoDetalle;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public Boolean getValidaPromedio() {
        return validaPromedio;
    }

    public void setValidaPromedio(Boolean validaPromedio) {
        this.validaPromedio = validaPromedio;
    }

    public String getEmpresaTipo() {
        return empresaTipo;
    }

    public void setEmpresaTipo(String empresaTipo) {
        this.empresaTipo = empresaTipo;
    }

    public String getEmpresaAmbito() {
        return empresaAmbito;
    }

    public void setEmpresaAmbito(String empresaAmbito) {
        this.empresaAmbito = empresaAmbito;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEntidadTipo() {
        return entidadTipo;
    }

    public void setEntidadTipo(String entidadTipo) {
        this.entidadTipo = entidadTipo;
    }

    public String getTipoGeneral() {
        return tipoGeneral;
    }

    public void setTipoGeneral(String tipoGeneral) {
        this.tipoGeneral = tipoGeneral;
    }

    public List<ActorMiembro> getActorMiembros() {
        return actorMiembros;
    }

    public void setActorMiembros(List<ActorMiembro> actorMiembros) {
        this.actorMiembros = actorMiembros;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getTipoPoblacion() {
        return tipoPoblacion;
    }

    public void setTipoPoblacion(String tipoPoblacion) {
        this.tipoPoblacion = tipoPoblacion;
    }

    public String getSubTipo1Poblacion() {
        return subTipo1Poblacion;
    }

    public void setSubTipo1Poblacion(String subTipo1Poblacion) {
        this.subTipo1Poblacion = subTipo1Poblacion;
    }

    public String getSubTipo2Poblacion() {
        return subTipo2Poblacion;
    }

    public void setSubTipo2Poblacion(String subTipo2Poblacion) {
        this.subTipo2Poblacion = subTipo2Poblacion;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public String getSubTipo1Estado() {
        return subTipo1Estado;
    }

    public void setSubTipo1Estado(String subTipo1Estado) {
        this.subTipo1Estado = subTipo1Estado;
    }

    public String getSubTipo2Estado() {
        return subTipo2Estado;
    }

    public void setSubTipo2Estado(String subTipo2Estado) {
        this.subTipo2Estado = subTipo2Estado;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getSubTipo1Empresa() {
        return subTipo1Empresa;
    }

    public void setSubTipo1Empresa(String subTipo1Empresa) {
        this.subTipo1Empresa = subTipo1Empresa;
    }

    public String getSubTipo2Empresa() {
        return subTipo2Empresa;
    }

    public void setSubTipo2Empresa(String subTipo2Empresa) {
        this.subTipo2Empresa = subTipo2Empresa;
    }

    public String getTipoOrganizacion() {
        return tipoOrganizacion;
    }

    public void setTipoOrganizacion(String tipoOrganizacion) {
        this.tipoOrganizacion = tipoOrganizacion;
    }

    public String getSubTipo1Organizacion() {
        return subTipo1Organizacion;
    }

    public void setSubTipo1Organizacion(String subTipo1Organizacion) {
        this.subTipo1Organizacion = subTipo1Organizacion;
    }

    public String getSubTipo2Organizacion() {
        return subTipo2Organizacion;
    }

    public void setSubTipo2Organizacion(String subTipo2Organizacion) {
        this.subTipo2Organizacion = subTipo2Organizacion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getSubTipo3Empresa() {
        return subTipo3Empresa;
    }

    public void setSubTipo3Empresa(String subTipo3Empresa) {
        this.subTipo3Empresa = subTipo3Empresa;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
        
}
