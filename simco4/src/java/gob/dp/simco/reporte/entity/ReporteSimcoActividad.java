/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.entity;

import java.util.List;

/**
 *
 * @author carlos
 */
public class ReporteSimcoActividad {
    
    private Long idActividad;
    
    private String anho;
    
    private String nombreCaso;
            
    private String codigoCaso;
            
    private String tipologiaCaso;
    
    private String detalleTipologiaCaso;
    
    private String actividadCaso;
                    
    private String idRegion;
                    
    private String estado;
                            
    private String tipoAcontecimiento;
    
    private String tipoActuacionDefensorial;
                            
    private String esComunidadNativa;
                                    
    private String nombreActor;
                                    
    private Long idActor;
                                            
    private String tipoActor;
    
    private String codigoActividad;
    
    private String nombreActividad;
    
    private String claseActividad;
    
    private String fecha;
    
    private String tipoActuacionDefensorialDetalle;
    
    private String tipoAcontecimientoDetalle;
    
    private String subTipoAcontecimientoDetalle;
    
    private String grupoAcontecimientoDetalle;
    
    private String tipoActividadDetalle;
    
    private String grupoActividadDetalle;
    
    private String tipoViolencia;
    
    private String resultadoViolencia;
    
    private List<String> listaTipoViolencia;
    
    private List<String> listaResultadoViolencia;
    
    private String nombreRegion;
    
    private Integer cantidadAcuerdos;
    
    private Integer cantidadActores;
    
    private String codigoAcontecimientoVinculado;
    
    private String nombreAcontecimientoVinculado;
    
    private String nombreProvincia;
    
    private String nombreDistrito;
    
    private Integer esComunidadNativaInt;
    
    private String tipoViolenciaItem1;
    
    private String tipoViolenciaItem2;
    
    private String tipoViolenciaItem3;
    
    private String tipoViolenciaItem4;
    
    private String tipoViolenciaItem5;
    
    private String tipoViolenciaItem6;
    
    private String tipoViolenciaItem7;
    
    private Integer resultadoViolenciaItem1;
    
    private Integer resultadoViolenciaItem2;
    
    private Integer resultadoViolenciaItem3;
    
    private Integer resultadoViolenciaItem4;
    
    private Integer resultadoViolenciaItem5;
    
    private Integer resultadoViolenciaItem6;
    
    private Integer resultadoViolenciaItem7;
    
    private Double inversionInvolucrada;
    
    
    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public String getTipologiaCaso() {
        return tipologiaCaso;
    }

    public void setTipologiaCaso(String tipologiaCaso) {
        this.tipologiaCaso = tipologiaCaso;
    }

    public String getActividadCaso() {
        return actividadCaso;
    }

    public void setActividadCaso(String actividadCaso) {
        this.actividadCaso = actividadCaso;
    }
    
    public String getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoAcontecimiento() {
        return tipoAcontecimiento;
    }

    public void setTipoAcontecimiento(String tipoAcontecimiento) {
        this.tipoAcontecimiento = tipoAcontecimiento;
    }

    public String getEsComunidadNativa() {
        return esComunidadNativa;
    }

    public void setEsComunidadNativa(String esComunidadNativa) {
        this.esComunidadNativa = esComunidadNativa;
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

    public String getTipoActor() {
        return tipoActor;
    }

    public void setTipoActor(String tipoActor) {
        this.tipoActor = tipoActor;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getClaseActividad() {
        return claseActividad;
    }

    public void setClaseActividad(String claseActividad) {
        this.claseActividad = claseActividad;
    }

    public String getTipoActuacionDefensorialDetalle() {
        return tipoActuacionDefensorialDetalle;
    }

    public void setTipoActuacionDefensorialDetalle(String tipoActuacionDefensorialDetalle) {
        this.tipoActuacionDefensorialDetalle = tipoActuacionDefensorialDetalle;
    }

    public String getTipoAcontecimientoDetalle() {
        return tipoAcontecimientoDetalle;
    }

    public void setTipoAcontecimientoDetalle(String tipoAcontecimientoDetalle) {
        this.tipoAcontecimientoDetalle = tipoAcontecimientoDetalle;
    }

    public String getTipoActividadDetalle() {
        return tipoActividadDetalle;
    }

    public void setTipoActividadDetalle(String tipoActividadDetalle) {
        this.tipoActividadDetalle = tipoActividadDetalle;
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

    public List<String> getListaTipoViolencia() {
        return listaTipoViolencia;
    }

    public void setListaTipoViolencia(List<String> listaTipoViolencia) {
        this.listaTipoViolencia = listaTipoViolencia;
    }

    public List<String> getListaResultadoViolencia() {
        return listaResultadoViolencia;
    }

    public void setListaResultadoViolencia(List<String> listaResultadoViolencia) {
        this.listaResultadoViolencia = listaResultadoViolencia;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public Integer getCantidadAcuerdos() {
        return cantidadAcuerdos;
    }

    public void setCantidadAcuerdos(Integer cantidadAcuerdos) {
        this.cantidadAcuerdos = cantidadAcuerdos;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public String getDetalleTipologiaCaso() {
        return detalleTipologiaCaso;
    }

    public void setDetalleTipologiaCaso(String detalleTipologiaCaso) {
        this.detalleTipologiaCaso = detalleTipologiaCaso;
    }

    public String getCodigoAcontecimientoVinculado() {
        return codigoAcontecimientoVinculado;
    }

    public void setCodigoAcontecimientoVinculado(String codigoAcontecimientoVinculado) {
        this.codigoAcontecimientoVinculado = codigoAcontecimientoVinculado;
    }

    public String getNombreAcontecimientoVinculado() {
        return nombreAcontecimientoVinculado;
    }

    public void setNombreAcontecimientoVinculado(String nombreAcontecimientoVinculado) {
        this.nombreAcontecimientoVinculado = nombreAcontecimientoVinculado;
    }

    public String getSubTipoAcontecimientoDetalle() {
        return subTipoAcontecimientoDetalle;
    }

    public void setSubTipoAcontecimientoDetalle(String subTipoAcontecimientoDetalle) {
        this.subTipoAcontecimientoDetalle = subTipoAcontecimientoDetalle;
    }

    public String getGrupoAcontecimientoDetalle() {
        return grupoAcontecimientoDetalle;
    }

    public void setGrupoAcontecimientoDetalle(String grupoAcontecimientoDetalle) {
        this.grupoAcontecimientoDetalle = grupoAcontecimientoDetalle;
    }

    public String getGrupoActividadDetalle() {
        return grupoActividadDetalle;
    }

    public void setGrupoActividadDetalle(String grupoActividadDetalle) {
        this.grupoActividadDetalle = grupoActividadDetalle;
    }

    public String getTipoActuacionDefensorial() {
        return tipoActuacionDefensorial;
    }

    public void setTipoActuacionDefensorial(String tipoActuacionDefensorial) {
        this.tipoActuacionDefensorial = tipoActuacionDefensorial;
    }

    public String getTipoViolenciaItem1() {
        return tipoViolenciaItem1;
    }

    public void setTipoViolenciaItem1(String tipoViolenciaItem1) {
        this.tipoViolenciaItem1 = tipoViolenciaItem1;
    }

    public String getTipoViolenciaItem2() {
        return tipoViolenciaItem2;
    }

    public void setTipoViolenciaItem2(String tipoViolenciaItem2) {
        this.tipoViolenciaItem2 = tipoViolenciaItem2;
    }

    public String getTipoViolenciaItem3() {
        return tipoViolenciaItem3;
    }

    public void setTipoViolenciaItem3(String tipoViolenciaItem3) {
        this.tipoViolenciaItem3 = tipoViolenciaItem3;
    }

    public String getTipoViolenciaItem4() {
        return tipoViolenciaItem4;
    }

    public void setTipoViolenciaItem4(String tipoViolenciaItem4) {
        this.tipoViolenciaItem4 = tipoViolenciaItem4;
    }

    public String getTipoViolenciaItem5() {
        return tipoViolenciaItem5;
    }

    public void setTipoViolenciaItem5(String tipoViolenciaItem5) {
        this.tipoViolenciaItem5 = tipoViolenciaItem5;
    }

    public String getTipoViolenciaItem6() {
        return tipoViolenciaItem6;
    }

    public void setTipoViolenciaItem6(String tipoViolenciaItem6) {
        this.tipoViolenciaItem6 = tipoViolenciaItem6;
    }

    public String getTipoViolenciaItem7() {
        return tipoViolenciaItem7;
    }

    public void setTipoViolenciaItem7(String tipoViolenciaItem7) {
        this.tipoViolenciaItem7 = tipoViolenciaItem7;
    }

    public Integer getResultadoViolenciaItem1() {
        return resultadoViolenciaItem1;
    }

    public void setResultadoViolenciaItem1(Integer resultadoViolenciaItem1) {
        this.resultadoViolenciaItem1 = resultadoViolenciaItem1;
    }

    public Integer getResultadoViolenciaItem2() {
        return resultadoViolenciaItem2;
    }

    public void setResultadoViolenciaItem2(Integer resultadoViolenciaItem2) {
        this.resultadoViolenciaItem2 = resultadoViolenciaItem2;
    }

    public Integer getResultadoViolenciaItem3() {
        return resultadoViolenciaItem3;
    }

    public void setResultadoViolenciaItem3(Integer resultadoViolenciaItem3) {
        this.resultadoViolenciaItem3 = resultadoViolenciaItem3;
    }

    public Integer getResultadoViolenciaItem4() {
        return resultadoViolenciaItem4;
    }

    public void setResultadoViolenciaItem4(Integer resultadoViolenciaItem4) {
        this.resultadoViolenciaItem4 = resultadoViolenciaItem4;
    }

    public Integer getResultadoViolenciaItem5() {
        return resultadoViolenciaItem5;
    }

    public void setResultadoViolenciaItem5(Integer resultadoViolenciaItem5) {
        this.resultadoViolenciaItem5 = resultadoViolenciaItem5;
    }

    public Integer getResultadoViolenciaItem6() {
        return resultadoViolenciaItem6;
    }

    public void setResultadoViolenciaItem6(Integer resultadoViolenciaItem6) {
        this.resultadoViolenciaItem6 = resultadoViolenciaItem6;
    }

    public Integer getResultadoViolenciaItem7() {
        return resultadoViolenciaItem7;
    }

    public void setResultadoViolenciaItem7(Integer resultadoViolenciaItem7) {
        this.resultadoViolenciaItem7 = resultadoViolenciaItem7;
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

    public Integer getCantidadActores() {
        return cantidadActores;
    }

    public void setCantidadActores(Integer cantidadActores) {
        this.cantidadActores = cantidadActores;
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

}
