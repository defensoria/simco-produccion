/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class ReporteSimcoActor implements Serializable {

    private Integer anho;

    private String codigoCaso;

    private String nombreCaso;

    private Integer tipologia;

    private Integer idRegion;

    private Integer tipoAcontecimiento;

    private Integer estado;

    private String tipoActor;
    
    private String descripcionTipoActor;

    private String nombreActor;

    private Long idActor;

    private String esComunidadNativa;

    private String nombreDepartamento;
    
    private String nombreProvincia;
    
    private String nombreDistrito;
    
    private Integer contadorActorAcontecimiento;
    
    private Integer contadorActorAcontecimientoProtesta;
    
    private Integer contadorActorAcontecimientoProtestaViolencia;
    
    private Integer contadorCasosPorActorPrimario;
    
    private Integer contadorCasosPorActorSecundario;
    
    private Integer contadorCasosPorActorTerciario;
    
    private Integer contadorActorAcuerdoComprometido;
    
    private Integer contadorActorAcuerdoBeneficiario;
    
    private Integer contadorActorCaso;
    
    private Double ponderado;
    
    private String documento;
    
    private String ruc;
    
    private String tipoPoblacion;
    
    private String subTipo1Poblacion;
    
    private String subTipo2Poblacion;
    
    private String tipoEmpresa;
    
    private String subTipo1Empresa;
    
    private String subTipo2Empresa;
    
    private String subTipo3Empresa;
    
    private String tipoOrganizacion;
    
    private String subTipo1Organizacion;
    
    private String subTipo2Organizacion;
    
    private String tipoEntidad;
    
    private String subTipo1Entidad;
    
    private String subTipo2Entidad;
    
    private String clasificacion;
    
    private String empresaAmbito;
    
    private String sexo;
    
    private String subtipo;
    
    private String subtipo1;
    
    private String subtipo2;
    
    private String subtipo3;
    
    private Double inversionInvolucrada;
    
    
    /**
     *
     * @return
     */
    
    public Integer getAnho() {
        return anho;
    }

    public void setAnho(Integer anho) {
        this.anho = anho;
    }

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public Integer getTipologia() {
        return tipologia;
    }

    public void setTipologia(Integer tipologia) {
        this.tipologia = tipologia;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public Integer getTipoAcontecimiento() {
        return tipoAcontecimiento;
    }

    public void setTipoAcontecimiento(Integer tipoAcontecimiento) {
        this.tipoAcontecimiento = tipoAcontecimiento;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getTipoActor() {
        return tipoActor;
    }

    public void setTipoActor(String tipoActor) {
        this.tipoActor = tipoActor;
    }

    public String getNombreActor() {
        return nombreActor;
    }

    public void setNombreActor(String nombreActor) {
        this.nombreActor = nombreActor;
    }

    public Long getIdActor() {
        return idActor;
    }

    public void setIdActor(Long idActor) {
        this.idActor = idActor;
    }

    public String getEsComunidadNativa() {
        return esComunidadNativa;
    }

    public void setEsComunidadNativa(String esComunidadNativa) {
        this.esComunidadNativa = esComunidadNativa;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Integer getContadorActorAcontecimiento() {
        return contadorActorAcontecimiento;
    }

    public void setContadorActorAcontecimiento(Integer contadorActorAcontecimiento) {
        this.contadorActorAcontecimiento = contadorActorAcontecimiento;
    }

    public Integer getContadorActorAcuerdoComprometido() {
        return contadorActorAcuerdoComprometido;
    }

    public void setContadorActorAcuerdoComprometido(Integer contadorActorAcuerdoComprometido) {
        this.contadorActorAcuerdoComprometido = contadorActorAcuerdoComprometido;
    }

    public Integer getContadorActorAcuerdoBeneficiario() {
        return contadorActorAcuerdoBeneficiario;
    }

    public void setContadorActorAcuerdoBeneficiario(Integer contadorActorAcuerdoBeneficiario) {
        this.contadorActorAcuerdoBeneficiario = contadorActorAcuerdoBeneficiario;
    }

    public Double getPonderado() {
        return ponderado;
    }

    public void setPonderado(Double ponderado) {
        this.ponderado = ponderado;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
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

    public String getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public String getSubTipo1Entidad() {
        return subTipo1Entidad;
    }

    public void setSubTipo1Entidad(String subTipo1Entidad) {
        this.subTipo1Entidad = subTipo1Entidad;
    }

    public String getSubTipo2Entidad() {
        return subTipo2Entidad;
    }

    public void setSubTipo2Entidad(String subTipo2Entidad) {
        this.subTipo2Entidad = subTipo2Entidad;
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

    public String getDescripcionTipoActor() {
        return descripcionTipoActor;
    }

    public void setDescripcionTipoActor(String descripcionTipoActor) {
        this.descripcionTipoActor = descripcionTipoActor;
    }

    public String getEmpresaAmbito() {
        return empresaAmbito;
    }

    public void setEmpresaAmbito(String empresaAmbito) {
        this.empresaAmbito = empresaAmbito;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSubtipo1() {
        return subtipo1;
    }

    public void setSubtipo1(String subtipo1) {
        this.subtipo1 = subtipo1;
    }

    public String getSubtipo2() {
        return subtipo2;
    }

    public void setSubtipo2(String subtipo2) {
        this.subtipo2 = subtipo2;
    }

    public String getSubtipo3() {
        return subtipo3;
    }

    public void setSubtipo3(String subtipo3) {
        this.subtipo3 = subtipo3;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
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

    public Integer getContadorActorCaso() {
        return contadorActorCaso;
    }

    public void setContadorActorCaso(Integer contadorActorCaso) {
        this.contadorActorCaso = contadorActorCaso;
    }

    public Integer getContadorActorAcontecimientoProtesta() {
        return contadorActorAcontecimientoProtesta;
    }

    public void setContadorActorAcontecimientoProtesta(Integer contadorActorAcontecimientoProtesta) {
        this.contadorActorAcontecimientoProtesta = contadorActorAcontecimientoProtesta;
    }

    public Integer getContadorActorAcontecimientoProtestaViolencia() {
        return contadorActorAcontecimientoProtestaViolencia;
    }

    public void setContadorActorAcontecimientoProtestaViolencia(Integer contadorActorAcontecimientoProtestaViolencia) {
        this.contadorActorAcontecimientoProtestaViolencia = contadorActorAcontecimientoProtestaViolencia;
    }

    public Integer getContadorCasosPorActorPrimario() {
        return contadorCasosPorActorPrimario;
    }

    public void setContadorCasosPorActorPrimario(Integer contadorCasosPorActorPrimario) {
        this.contadorCasosPorActorPrimario = contadorCasosPorActorPrimario;
    }

    public Integer getContadorCasosPorActorSecundario() {
        return contadorCasosPorActorSecundario;
    }

    public void setContadorCasosPorActorSecundario(Integer contadorCasosPorActorSecundario) {
        this.contadorCasosPorActorSecundario = contadorCasosPorActorSecundario;
    }

    public Integer getContadorCasosPorActorTerciario() {
        return contadorCasosPorActorTerciario;
    }

    public void setContadorCasosPorActorTerciario(Integer contadorCasosPorActorTerciario) {
        this.contadorCasosPorActorTerciario = contadorCasosPorActorTerciario;
    }

    public Double getInversionInvolucrada() {
        return inversionInvolucrada;
    }

    public void setInversionInvolucrada(Double inversionInvolucrada) {
        this.inversionInvolucrada = inversionInvolucrada;
    }

}
