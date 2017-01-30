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
public class ElementoReporte implements Serializable {

    private String tipoConflicto;

    private Integer total;

    private String porcentaje;

    /**
     * *TIPO**
     */
    private Integer gobiernoLocal;

    private Integer gobiernoRegional;

    private Integer gobiernoNacional;

    private Integer comunal;

    private Integer cultivoCoca;

    private Integer demarcacionTerritorial;

    private Integer electoral;

    private Integer laboral;

    private Integer socioambienta;

    private Integer otros;

    /**
     * *FASE**
     */
    private Integer temprana;

    private Integer escalamiento;

    private Integer crisis;

    private Integer desescalamiento;

    private Integer dialogo;

    /**
     * *AUTORIDAD COMPETENTE**
     */
    private Integer gobNacional;

    private Integer gobRegional;

    private Integer gobLocal;

    private Integer poderLegislativo;

    private Integer poderJudicial;

    private Integer organismoAutonomos;

    /**
     * *MECANISMO DE DIALOGO**
     */
    private Integer negociacionDirecta;

    private Integer negociacionMediada;

    private Integer comisionAlto;

    private Integer reunionesPreparatorias;

    /**
     * *ESTADO**
     */
    private Integer borrador;

    private Integer propuesta;

    private Integer observacion;

    private Integer activo;

    private Integer latente;

    private Integer resuelto;

    private Integer extra;

    private Integer extra1;

    /**
     * *POR PARTICIPACION DE LA DEFENSORIA**
     */
    private Integer intermediador;

    private Integer promotor;

    private Integer observador;

    private Integer mediador;

    private Integer participacion;

    /**
     * *POR DEPARTAMENTOS**
     */
    private Integer amazonas;
    
private Integer ancash;

private Integer apurimac;

private Integer arequipa;

private Integer ayacucho;

private Integer cajamarca;

private Integer cusco;

private Integer huancavelica;

private Integer huanuco;

private Integer ica;

private Integer junin;

private Integer laLibertad;

private Integer lambayeque;

private Integer lima;

private Integer loreto;

private Integer madreDeDios;

private Integer moquegua;

private Integer pasco;

private Integer piura;

private Integer puno;

private Integer sanMartin;

private Integer tacna;

private Integer tumbes;

private Integer callao;

private Integer ucayali;

    public ElementoReporte() {
    }

    public ElementoReporte(String tipoConflicto, Integer total, String porcentaje, Integer temprana, Integer escalamiento, Integer crisis, Integer desescalamiento, Integer dialogo) {
        this.tipoConflicto = tipoConflicto;
        this.total = total;
        this.porcentaje = porcentaje;
        this.temprana = temprana;
        this.escalamiento = escalamiento;
        this.crisis = crisis;
        this.desescalamiento = desescalamiento;
        this.dialogo = dialogo;
    }

    public ElementoReporte(String tipoConflicto, Integer total, String porcentaje, Integer gobiernoLocal, Integer gobiernoRegional, Integer gobiernoNacional, Integer comunal, Integer cultivoCoca, Integer demarcacionTerritorial, Integer electoral, Integer laboral, Integer socioambienta, Integer otros) {
        this.tipoConflicto = tipoConflicto;
        this.total = total;
        this.porcentaje = porcentaje;
        this.gobiernoLocal = gobiernoLocal;
        this.gobiernoRegional = gobiernoRegional;
        this.gobiernoNacional = gobiernoNacional;
        this.comunal = comunal;
        this.cultivoCoca = cultivoCoca;
        this.demarcacionTerritorial = demarcacionTerritorial;
        this.electoral = electoral;
        this.laboral = laboral;
        this.socioambienta = socioambienta;
        this.otros = otros;
    }

    public ElementoReporte(String tipoConflicto, Integer total, String porcentaje, Integer gobNacional, Integer gobRegional, Integer gobLocal, Integer poderLegislativo, Integer poderJudicial, Integer organismoAutonomos) {
        this.tipoConflicto = tipoConflicto;
        this.total = total;
        this.porcentaje = porcentaje;
        this.gobNacional = gobNacional;
        this.gobRegional = gobRegional;
        this.gobLocal = gobLocal;
        this.poderLegislativo = poderLegislativo;
        this.poderJudicial = poderJudicial;
        this.organismoAutonomos = organismoAutonomos;
    }

    public ElementoReporte(String tipoConflicto, Integer total, String porcentaje, Integer negociacionDirecta, Integer negociacionMediada, Integer comisionAlto, Integer reunionesPreparatorias) {
        this.tipoConflicto = tipoConflicto;
        this.total = total;
        this.porcentaje = porcentaje;
        this.negociacionDirecta = negociacionDirecta;
        this.negociacionMediada = negociacionMediada;
        this.comisionAlto = comisionAlto;
        this.reunionesPreparatorias = reunionesPreparatorias;
    }

    public ElementoReporte(String tipoConflicto, Integer total, String porcentaje, Integer borrador, Integer propuesta, Integer observacion, Integer activo, Integer latente, Integer resuelto, Integer extra) {
        this.tipoConflicto = tipoConflicto;
        this.total = total;
        this.porcentaje = porcentaje;
        this.borrador = borrador;
        this.propuesta = propuesta;
        this.observacion = observacion;
        this.activo = activo;
        this.latente = latente;
        this.resuelto = resuelto;
        this.extra = extra;
    }

    public ElementoReporte(String tipoConflicto, Integer total, String porcentaje, Integer intermediador, Integer promotor, Integer observador, Integer mediador, Integer participacion, Integer latente, Integer resuelto, Integer extra) {
        this.tipoConflicto = tipoConflicto;
        this.total = total;
        this.porcentaje = porcentaje;
        this.intermediador = intermediador;
        this.promotor = promotor;
        this.observador = observador;
        this.mediador = mediador;
        this.participacion = participacion;
        this.latente = latente;
        this.resuelto = resuelto;
        this.extra = extra;
    }

    public ElementoReporte(String tipoConflicto, Integer total, String porcentaje, Integer amazonas, Integer ancash, Integer apurimac, Integer arequipa, Integer ayacucho, Integer cajamarca, Integer cusco, Integer huancavelica, Integer huanuco, Integer ica, Integer junin, Integer laLibertad, Integer lambayeque, Integer lima, Integer loreto, Integer madreDeDios, Integer moquegua, Integer pasco, Integer piura, Integer puno, Integer sanMartin, Integer tacna, Integer tumbes, Integer callao, Integer ucayali) {
        this.tipoConflicto = tipoConflicto;
        this.total = total;
        this.porcentaje = porcentaje;
        this.amazonas = amazonas;
        this.ancash = ancash;
        this.apurimac = apurimac;
        this.arequipa = arequipa;
        this.ayacucho = ayacucho;
        this.cajamarca = cajamarca;
        this.cusco = cusco;
        this.huancavelica = huancavelica;
        this.huanuco = huanuco;
        this.ica = ica;
        this.junin = junin;
        this.laLibertad = laLibertad;
        this.lambayeque = lambayeque;
        this.lima = lima;
        this.loreto = loreto;
        this.madreDeDios = madreDeDios;
        this.moquegua = moquegua;
        this.pasco = pasco;
        this.piura = piura;
        this.puno = puno;
        this.sanMartin = sanMartin;
        this.tacna = tacna;
        this.tumbes = tumbes;
        this.callao = callao;
        this.ucayali = ucayali;
    }
    
    public String getTipoConflicto() {
        return tipoConflicto;
    }

    public void setTipoConflicto(String tipoConflicto) {
        this.tipoConflicto = tipoConflicto;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getTemprana() {
        return temprana;
    }

    public void setTemprana(Integer temprana) {
        this.temprana = temprana;
    }

    public Integer getEscalamiento() {
        return escalamiento;
    }

    public void setEscalamiento(Integer escalamiento) {
        this.escalamiento = escalamiento;
    }

    public Integer getCrisis() {
        return crisis;
    }

    public void setCrisis(Integer crisis) {
        this.crisis = crisis;
    }

    public Integer getDesescalamiento() {
        return desescalamiento;
    }

    public void setDesescalamiento(Integer desescalamiento) {
        this.desescalamiento = desescalamiento;
    }

    public Integer getDialogo() {
        return dialogo;
    }

    public void setDialogo(Integer dialogo) {
        this.dialogo = dialogo;
    }

    public Integer getGobiernoLocal() {
        return gobiernoLocal;
    }

    public void setGobiernoLocal(Integer gobiernoLocal) {
        this.gobiernoLocal = gobiernoLocal;
    }

    public Integer getGobiernoRegional() {
        return gobiernoRegional;
    }

    public void setGobiernoRegional(Integer gobiernoRegional) {
        this.gobiernoRegional = gobiernoRegional;
    }

    public Integer getGobiernoNacional() {
        return gobiernoNacional;
    }

    public void setGobiernoNacional(Integer gobiernoNacional) {
        this.gobiernoNacional = gobiernoNacional;
    }

    public Integer getComunal() {
        return comunal;
    }

    public void setComunal(Integer comunal) {
        this.comunal = comunal;
    }

    public Integer getCultivoCoca() {
        return cultivoCoca;
    }

    public void setCultivoCoca(Integer cultivoCoca) {
        this.cultivoCoca = cultivoCoca;
    }

    public Integer getDemarcacionTerritorial() {
        return demarcacionTerritorial;
    }

    public void setDemarcacionTerritorial(Integer demarcacionTerritorial) {
        this.demarcacionTerritorial = demarcacionTerritorial;
    }

    public Integer getElectoral() {
        return electoral;
    }

    public void setElectoral(Integer electoral) {
        this.electoral = electoral;
    }

    public Integer getLaboral() {
        return laboral;
    }

    public void setLaboral(Integer laboral) {
        this.laboral = laboral;
    }

    public Integer getSocioambienta() {
        return socioambienta;
    }

    public void setSocioambienta(Integer socioambienta) {
        this.socioambienta = socioambienta;
    }

    public Integer getOtros() {
        return otros;
    }

    public void setOtros(Integer otros) {
        this.otros = otros;
    }

    public Integer getGobNacional() {
        return gobNacional;
    }

    public void setGobNacional(Integer gobNacional) {
        this.gobNacional = gobNacional;
    }

    public Integer getGobRegional() {
        return gobRegional;
    }

    public void setGobRegional(Integer gobRegional) {
        this.gobRegional = gobRegional;
    }

    public Integer getGobLocal() {
        return gobLocal;
    }

    public void setGobLocal(Integer gobLocal) {
        this.gobLocal = gobLocal;
    }

    public Integer getPoderLegislativo() {
        return poderLegislativo;
    }

    public void setPoderLegislativo(Integer poderLegislativo) {
        this.poderLegislativo = poderLegislativo;
    }

    public Integer getPoderJudicial() {
        return poderJudicial;
    }

    public void setPoderJudicial(Integer poderJudicial) {
        this.poderJudicial = poderJudicial;
    }

    public Integer getOrganismoAutonomos() {
        return organismoAutonomos;
    }

    public void setOrganismoAutonomos(Integer organismoAutonomos) {
        this.organismoAutonomos = organismoAutonomos;
    }

    public Integer getNegociacionDirecta() {
        return negociacionDirecta;
    }

    public void setNegociacionDirecta(Integer negociacionDirecta) {
        this.negociacionDirecta = negociacionDirecta;
    }

    public Integer getNegociacionMediada() {
        return negociacionMediada;
    }

    public void setNegociacionMediada(Integer negociacionMediada) {
        this.negociacionMediada = negociacionMediada;
    }

    public Integer getComisionAlto() {
        return comisionAlto;
    }

    public void setComisionAlto(Integer comisionAlto) {
        this.comisionAlto = comisionAlto;
    }

    public Integer getReunionesPreparatorias() {
        return reunionesPreparatorias;
    }

    public void setReunionesPreparatorias(Integer reunionesPreparatorias) {
        this.reunionesPreparatorias = reunionesPreparatorias;
    }

    public Integer getBorrador() {
        return borrador;
    }

    public void setBorrador(Integer borrador) {
        this.borrador = borrador;
    }

    public Integer getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(Integer propuesta) {
        this.propuesta = propuesta;
    }

    public Integer getObservacion() {
        return observacion;
    }

    public void setObservacion(Integer observacion) {
        this.observacion = observacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getLatente() {
        return latente;
    }

    public void setLatente(Integer latente) {
        this.latente = latente;
    }

    public Integer getResuelto() {
        return resuelto;
    }

    public void setResuelto(Integer resuelto) {
        this.resuelto = resuelto;
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

    public Integer getIntermediador() {
        return intermediador;
    }

    public void setIntermediador(Integer intermediador) {
        this.intermediador = intermediador;
    }

    public Integer getPromotor() {
        return promotor;
    }

    public void setPromotor(Integer promotor) {
        this.promotor = promotor;
    }

    public Integer getObservador() {
        return observador;
    }

    public void setObservador(Integer observador) {
        this.observador = observador;
    }

    public Integer getMediador() {
        return mediador;
    }

    public void setMediador(Integer mediador) {
        this.mediador = mediador;
    }

    public Integer getParticipacion() {
        return participacion;
    }

    public void setParticipacion(Integer participacion) {
        this.participacion = participacion;
    }

    public Integer getExtra1() {
        return extra1;
    }

    public void setExtra1(Integer extra1) {
        this.extra1 = extra1;
    }

    public Integer getAmazonas() {
        return amazonas;
    }

    public void setAmazonas(Integer amazonas) {
        this.amazonas = amazonas;
    }

    public Integer getAncash() {
        return ancash;
    }

    public void setAncash(Integer ancash) {
        this.ancash = ancash;
    }

    public Integer getApurimac() {
        return apurimac;
    }

    public void setApurimac(Integer apurimac) {
        this.apurimac = apurimac;
    }

    public Integer getArequipa() {
        return arequipa;
    }

    public void setArequipa(Integer arequipa) {
        this.arequipa = arequipa;
    }

    public Integer getAyacucho() {
        return ayacucho;
    }

    public void setAyacucho(Integer ayacucho) {
        this.ayacucho = ayacucho;
    }

    public Integer getCajamarca() {
        return cajamarca;
    }

    public void setCajamarca(Integer cajamarca) {
        this.cajamarca = cajamarca;
    }

    public Integer getCusco() {
        return cusco;
    }

    public void setCusco(Integer cusco) {
        this.cusco = cusco;
    }

    public Integer getHuancavelica() {
        return huancavelica;
    }

    public void setHuancavelica(Integer huancavelica) {
        this.huancavelica = huancavelica;
    }

    public Integer getHuanuco() {
        return huanuco;
    }

    public void setHuanuco(Integer huanuco) {
        this.huanuco = huanuco;
    }

    public Integer getIca() {
        return ica;
    }

    public void setIca(Integer ica) {
        this.ica = ica;
    }

    public Integer getJunin() {
        return junin;
    }

    public void setJunin(Integer junin) {
        this.junin = junin;
    }

    public Integer getLaLibertad() {
        return laLibertad;
    }

    public void setLaLibertad(Integer laLibertad) {
        this.laLibertad = laLibertad;
    }

    public Integer getLambayeque() {
        return lambayeque;
    }

    public void setLambayeque(Integer lambayeque) {
        this.lambayeque = lambayeque;
    }

    public Integer getLima() {
        return lima;
    }

    public void setLima(Integer lima) {
        this.lima = lima;
    }

    public Integer getLoreto() {
        return loreto;
    }

    public void setLoreto(Integer loreto) {
        this.loreto = loreto;
    }

    public Integer getMadreDeDios() {
        return madreDeDios;
    }

    public void setMadreDeDios(Integer madreDeDios) {
        this.madreDeDios = madreDeDios;
    }

    public Integer getMoquegua() {
        return moquegua;
    }

    public void setMoquegua(Integer moquegua) {
        this.moquegua = moquegua;
    }

    public Integer getPasco() {
        return pasco;
    }

    public void setPasco(Integer pasco) {
        this.pasco = pasco;
    }

    public Integer getPiura() {
        return piura;
    }

    public void setPiura(Integer piura) {
        this.piura = piura;
    }

    public Integer getPuno() {
        return puno;
    }

    public void setPuno(Integer puno) {
        this.puno = puno;
    }

    public Integer getSanMartin() {
        return sanMartin;
    }

    public void setSanMartin(Integer sanMartin) {
        this.sanMartin = sanMartin;
    }

    public Integer getTacna() {
        return tacna;
    }

    public void setTacna(Integer tacna) {
        this.tacna = tacna;
    }

    public Integer getTumbes() {
        return tumbes;
    }

    public void setTumbes(Integer tumbes) {
        this.tumbes = tumbes;
    }

    public Integer getCallao() {
        return callao;
    }

    public void setCallao(Integer callao) {
        this.callao = callao;
    }

    public Integer getUcayali() {
        return ucayali;
    }

    public void setUcayali(Integer ucayali) {
        this.ucayali = ucayali;
    }

    
}
