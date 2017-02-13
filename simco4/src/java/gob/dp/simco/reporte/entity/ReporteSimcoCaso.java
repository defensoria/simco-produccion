/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.entity;

import gob.dp.simco.registro.entity.Actividad;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ReporteSimcoCaso implements Serializable{
    
    private Long anho;
    
    private String codigoCaso;
    
    private String nombreCaso;
    
    private String fechaInicioCaso;
    
    private String fechaFinCaso;
    
    private String regionPrincial;
    
    private String provinciaPrincial;
    
    private String distritoPrincial;
    
    private String tipologia;
    
    private Integer idRegion;
    
    private Integer tipoAcontecimiento;
    
    private String primerNivel;
    
    private String segundoNivel;
    
    private String tercerNivel;
    
    private String esComunidadNativa;
    
    private Integer esComunidadNativaInt;
    
    private String esEmpresaMinera;
    
    private String estado;
    
    private String actividad;
    
    private String fase;
    
    private String acuerdos;
    
    private String tipoActor;
    
    private String nombreActor;
    
    private Long idActor;
    
    private Long idCaso;
    
    private String fechaModificacion;
    
    private String momentoDialogo;
    
    private String mecanismoDialogo;
    
    private String participacion;
    
    private String fechaResolucion;
    
    private Integer mesesResolucion;
    
    private Integer cantidadAcontecimientos;
    
    private Integer cantidadActuaciones;
    
    private Integer cantidadAnalisis;
    
    private Integer cantidadIntervencion;
    
    private Integer cantidadAcuerdos;
    
    private String cantidadEmpresasMineras;
    
    private Integer cantidadMuertoCiviles;
    
    private Integer cantidadMuertoPNP;
    
    private Integer cantidadMuertoFFAA;
    
    private Integer cantidadHeridoCiviles;
    
    private Integer cantidadHeridoPNP;
    
    private Integer cantidadHeridoFFAA;
    
    private Double inversionInvolucrada;
    
    private String descripcionCaso;
    
    private String imagePath;
    
    private String actorPrimario;
    
    private String actorSecundario;
    
    private String actorTerciario;
    
    private List<Actividad> actividades;
    
    private String rutaReporte;
    

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getFechaInicioCaso() {
        return fechaInicioCaso;
    }

    public void setFechaInicioCaso(String fechaInicioCaso) {
        this.fechaInicioCaso = fechaInicioCaso;
    }

    public String getRegionPrincial() {
        return regionPrincial;
    }

    public void setRegionPrincial(String regionPrincial) {
        this.regionPrincial = regionPrincial;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public Long getAnho() {
        return anho;
    }

    public void setAnho(Long anho) {
        this.anho = anho;
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

    public String getEsComunidadNativa() {
        return esComunidadNativa;
    }

    public void setEsComunidadNativa(String esComunidadNativa) {
        this.esComunidadNativa = esComunidadNativa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getAcuerdos() {
        return acuerdos;
    }

    public void setAcuerdos(String acuerdos) {
        this.acuerdos = acuerdos;
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

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public String getEsEmpresaMinera() {
        return esEmpresaMinera;
    }

    public void setEsEmpresaMinera(String esEmpresaMinera) {
        this.esEmpresaMinera = esEmpresaMinera;
    }

    public String getProvinciaPrincial() {
        return provinciaPrincial;
    }

    public void setProvinciaPrincial(String provinciaPrincial) {
        this.provinciaPrincial = provinciaPrincial;
    }

    public String getDistritoPrincial() {
        return distritoPrincial;
    }

    public void setDistritoPrincial(String distritoPrincial) {
        this.distritoPrincial = distritoPrincial;
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

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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

    public String getParticipacion() {
        return participacion;
    }

    public void setParticipacion(String participacion) {
        this.participacion = participacion;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(String fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public Integer getMesesResolucion() {
        return mesesResolucion;
    }

    public void setMesesResolucion(Integer mesesResolucion) {
        this.mesesResolucion = mesesResolucion;
    }

    public String getFechaFinCaso() {
        return fechaFinCaso;
    }

    public void setFechaFinCaso(String fechaFinCaso) {
        this.fechaFinCaso = fechaFinCaso;
    }

    public Integer getCantidadAcontecimientos() {
        return cantidadAcontecimientos;
    }

    public void setCantidadAcontecimientos(Integer cantidadAcontecimientos) {
        this.cantidadAcontecimientos = cantidadAcontecimientos;
    }

    public Integer getCantidadActuaciones() {
        return cantidadActuaciones;
    }

    public void setCantidadActuaciones(Integer cantidadActuaciones) {
        this.cantidadActuaciones = cantidadActuaciones;
    }

    public Integer getCantidadAnalisis() {
        return cantidadAnalisis;
    }

    public void setCantidadAnalisis(Integer cantidadAnalisis) {
        this.cantidadAnalisis = cantidadAnalisis;
    }

    public Integer getCantidadIntervencion() {
        return cantidadIntervencion;
    }

    public void setCantidadIntervencion(Integer cantidadIntervencion) {
        this.cantidadIntervencion = cantidadIntervencion;
    }

    public Integer getCantidadAcuerdos() {
        return cantidadAcuerdos;
    }

    public void setCantidadAcuerdos(Integer cantidadAcuerdos) {
        this.cantidadAcuerdos = cantidadAcuerdos;
    }

    public String getCantidadEmpresasMineras() {
        return cantidadEmpresasMineras;
    }

    public void setCantidadEmpresasMineras(String cantidadEmpresasMineras) {
        this.cantidadEmpresasMineras = cantidadEmpresasMineras;
    }

    public Integer getCantidadMuertoCiviles() {
        return cantidadMuertoCiviles;
    }

    public void setCantidadMuertoCiviles(Integer cantidadMuertoCiviles) {
        this.cantidadMuertoCiviles = cantidadMuertoCiviles;
    }

    public Integer getCantidadMuertoPNP() {
        return cantidadMuertoPNP;
    }

    public void setCantidadMuertoPNP(Integer cantidadMuertoPNP) {
        this.cantidadMuertoPNP = cantidadMuertoPNP;
    }

    public Integer getCantidadMuertoFFAA() {
        return cantidadMuertoFFAA;
    }

    public void setCantidadMuertoFFAA(Integer cantidadMuertoFFAA) {
        this.cantidadMuertoFFAA = cantidadMuertoFFAA;
    }

    public Integer getCantidadHeridoCiviles() {
        return cantidadHeridoCiviles;
    }

    public void setCantidadHeridoCiviles(Integer cantidadHeridoCiviles) {
        this.cantidadHeridoCiviles = cantidadHeridoCiviles;
    }

    public Integer getCantidadHeridoPNP() {
        return cantidadHeridoPNP;
    }

    public void setCantidadHeridoPNP(Integer cantidadHeridoPNP) {
        this.cantidadHeridoPNP = cantidadHeridoPNP;
    }

    public Integer getCantidadHeridoFFAA() {
        return cantidadHeridoFFAA;
    }

    public void setCantidadHeridoFFAA(Integer cantidadHeridoFFAA) {
        this.cantidadHeridoFFAA = cantidadHeridoFFAA;
    }

    public Integer getEsComunidadNativaInt() {
        return esComunidadNativaInt;
    }

    public void setEsComunidadNativaInt(Integer esComunidadNativaInt) {
        this.esComunidadNativaInt = esComunidadNativaInt;
    }

    public Double getInversionInvolucrada() {
        return inversionInvolucrada;
    }

    public void setInversionInvolucrada(Double inversionInvolucrada) {
        this.inversionInvolucrada = inversionInvolucrada;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescripcionCaso() {
        return descripcionCaso;
    }

    public void setDescripcionCaso(String descripcionCaso) {
        this.descripcionCaso = descripcionCaso;
    }

    public String getActorPrimario() {
        return actorPrimario;
    }

    public void setActorPrimario(String actorPrimario) {
        this.actorPrimario = actorPrimario;
    }

    public String getActorSecundario() {
        return actorSecundario;
    }

    public void setActorSecundario(String actorSecundario) {
        this.actorSecundario = actorSecundario;
    }

    public String getActorTerciario() {
        return actorTerciario;
    }

    public void setActorTerciario(String actorTerciario) {
        this.actorTerciario = actorTerciario;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public String getRutaReporte() {
        return rutaReporte;
    }

    public void setRutaReporte(String rutaReporte) {
        this.rutaReporte = rutaReporte;
    }
    
}
