/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ElementoResumenEjecutivo implements Serializable{
    
    private String imagePath001;
    
    private String mesPublicacion;
    
    private String anhoPublicacion;
    
    private Integer totalCasosRegistrados;
    
    private Integer totalCasosActivos;
    
    private Integer totalCasosLatentes;
    
    private Integer totalCasosRegistradosMes;
    
    private Integer totalCasosDeLatenteAActivoMes;
    
    private Integer totalCasosDeActivosALatenteMes;
    
    private Integer totalCasosDeRetiradoAActivoMes;
    
    private String porcentajeTotalCasosActivos;
    
    private String porcentajeTotalCasosLatentes;
    
    private Integer totalCasosResueltoMes;
    
    private Integer totalCasosDeLatenteARetiradoMes;
    
    private Integer totalCasosFaseDialogo;
    
    private Integer totalCasosDialogoNegociacion;
    
    private Integer totalCasosDialogoReuniones;
    
    private Integer totalCasosDialogoEspacioDialogo;
    
    private String porcentajeCasosFaseDialogo;
    
    private String porcentajeCasosFaseDialogoNegociacion;
    
    private String porcentajeCasosFaseDialogoReuniones;
    
    private String porcentajeCasosFaseDialogoEspacionDialogo;
    
    private Integer totalCasosACVictimaViolencia;
    
    private Integer totalCasosACVictimaViolenciaMes;
    
    private String porcentajeCasosACVictimaViolencia;

    private String porcentajeCasosACVictimaViolenciaMes;
    
    private Integer totalCasosACAccionesProtestaMes;
    
    private Integer totalCasosAD;
    
    private Integer totalCasosADMes; 
    
    private Integer totalActividadAccionesDefensaLegalMes; 
    
    private Integer totalActividadAccionesHumanitariasMes; 
    
    private Integer totalActividadIntermediacionMes; 
    
    private Integer totalActividadSupervisionPreventivaMes; 
    
    private Integer totalActividadAccionesDifusionMes; 
    
    private List<CuadroGenericoMes> listaCasosRegistradosMES;
    
    private List<CuadroGenericoMes> listaCasosResueltosMES;
    
    private List<ElementoNombreValor> registradoTotals;
    
    private List<ReporteSimcoCaso> listaCasosNuevosPorMes;
    
    private List<ReporteSimcoCaso> listaCasosResueltosPorMes;
    
    private List<ReporteSimcoCaso> listaCasosActivosTotales;
    
    private List<ElementoNombreValor> listaNivelGobierno;
    
    private List<ElementoNombreValor> listaNivelGobiernoGrafico;
    
    private List<ElementoNombreValor> listaNivelTipo;
    
    private List<ElementoNombreValor> listaNivelTipoGrafico;
    
    private List<ElementoNombreValor> listaNivelSubTipo;
    
    private List<ElementoNombreValor> listaNivelSubTipoGrafico;
    
    private List<ElementoNombreValor> listaNivelTipoDialogo;
    
    private List<ElementoNombreValor> listaNivelTipoDialogoGrafico;
    
    private List<ElementoReporte> elementoReportesAutoridad;
    
    private List<ElementoReporte> elementoReportesDepartamentoEstado;
    
    private List<NuevoCaso> nuevoCasos;
    
    private String nombre;
    
    private Integer valor;
    
    private String valorParametro;
    
    private String cadenaNombreCaso;
    
    private Integer totalCasosTotalMes;
    
    private Integer totalCasosLatentesObservacion;
    
    private Integer totalCasosGeneralDiagolo;
    
    private String porcentajeGeneralDiagolo;
    
    private Integer totalCasosGeneralEscalamiento;
    
    private String porcentajeGeneralEscalamiento;
    
    private Integer totalCasosGeneralTemprana;
    
    private String porcentajeGeneralTemprana;
    
    private Integer totalCasosGeneralDesescalamiento;
    
    private String porcentajeGeneralDesescalamiento;
    
    private Integer totalCasosGeneralCrisis;
    
    private String porcentajeGeneralCrisis;
    
    private Integer totalCasosGeneralActivo;
    
    private Integer totalCasosGeneralReunionesPreparatorias;
    
    private String porcentajeGeneralReunionesPreparatorias;
    
    private Integer totalCasosGeneralParticipacionDefensoria;
    
    private String porcentajeGeneralParticipacionDefensoria;
    
    private Integer totalCasosGeneralEchosViolencia;
    
    private String porcentajeGeneralEchosViolencia;
    
    private Integer totalCasosAccionesProtesta;
    
    private String porcentajeAccionesProtesta;
    
    private String estadoConflictoTexto;
    
    private String nivelGobiernoTexto;

    private List<ReporteSimcoCaso> listaCasosReactivadosPorMes;
    
    private List<ReporteSimcoCaso> listaCasosLatentesPorMes;
    
    private List<ReporteSimcoCaso> listaCasosActivoALatentes;
    
    /***departamentos**/
    private	Integer amazonasAct;
        private Integer ancashAct;
        private Integer apurimacAct;
        private Integer arequipaAct;
        private Integer ayacuchoAct;
        private Integer cajamarcaAct;
        private Integer cuscoAct;
        private Integer huancavelicaAct;
        private Integer huanucoAct;
        private Integer icaAct;
        private Integer juninAct;
        private Integer laLibertadAct;
        private Integer lambayequeAct;
        private Integer limaAct;
        private Integer loretoAct;
        private Integer madreDeDiosAct;
        private Integer moqueguaAct;
        private Integer pascoAct;
        private Integer piuraAct;
        private Integer punoAct;
        private Integer sanMartinAct;
        private Integer tacnaAct;
        private Integer tumbesAct;
        private Integer callaoAct;
        private Integer ucayaliAct;
        private Integer amazonasLat;
        private Integer ancashLat;
        private Integer apurimacLat;
        private Integer arequipaLat;
        private Integer ayacuchoLat;
        private Integer cajamarcaLat;
        private Integer cuscoLat;
        private Integer huancavelicaLat;
        private Integer huanucoLat;
        private Integer icaLat;
        private Integer juninLat;
        private Integer laLibertadLat;
        private Integer lambayequeLat;
        private Integer limaLat;
        private Integer loretoLat;
        private Integer madreDeDiosLat;
        private Integer moqueguaLat;
        private Integer pascoLat;
        private Integer piuraLat;
        private Integer punoLat;
        private Integer sanMartinLat;
        private Integer tacnaLat;
        private Integer tumbesLat;
        private Integer callaoLat;
        private Integer ucayaliLat;
       

    public Integer getAmazonasAct() {
        return amazonasAct;
    }

    public void setAmazonasAct(Integer amazonasAct) {
        this.amazonasAct = amazonasAct;
    }

    public Integer getAncashAct() {
        return ancashAct;
    }

    public void setAncashAct(Integer ancashAct) {
        this.ancashAct = ancashAct;
    }

    public Integer getApurimacAct() {
        return apurimacAct;
    }

    public void setApurimacAct(Integer apurimacAct) {
        this.apurimacAct = apurimacAct;
    }

    public Integer getArequipaAct() {
        return arequipaAct;
    }

    public void setArequipaAct(Integer arequipaAct) {
        this.arequipaAct = arequipaAct;
    }

    public Integer getAyacuchoAct() {
        return ayacuchoAct;
    }

    public void setAyacuchoAct(Integer ayacuchoAct) {
        this.ayacuchoAct = ayacuchoAct;
    }

    public Integer getCajamarcaAct() {
        return cajamarcaAct;
    }

    public void setCajamarcaAct(Integer cajamarcaAct) {
        this.cajamarcaAct = cajamarcaAct;
    }

    public Integer getCuscoAct() {
        return cuscoAct;
    }

    public void setCuscoAct(Integer cuscoAct) {
        this.cuscoAct = cuscoAct;
    }

    public Integer getHuancavelicaAct() {
        return huancavelicaAct;
    }

    public void setHuancavelicaAct(Integer huancavelicaAct) {
        this.huancavelicaAct = huancavelicaAct;
    }

    public Integer getHuanucoAct() {
        return huanucoAct;
    }

    public void setHuanucoAct(Integer huanucoAct) {
        this.huanucoAct = huanucoAct;
    }

    public Integer getIcaAct() {
        return icaAct;
    }

    public void setIcaAct(Integer icaAct) {
        this.icaAct = icaAct;
    }

    public Integer getJuninAct() {
        return juninAct;
    }

    public void setJuninAct(Integer juninAct) {
        this.juninAct = juninAct;
    }

    public Integer getLaLibertadAct() {
        return laLibertadAct;
    }

    public void setLaLibertadAct(Integer laLibertadAct) {
        this.laLibertadAct = laLibertadAct;
    }

    public Integer getLambayequeAct() {
        return lambayequeAct;
    }

    public void setLambayequeAct(Integer lambayequeAct) {
        this.lambayequeAct = lambayequeAct;
    }

    public Integer getLimaAct() {
        return limaAct;
    }

    public void setLimaAct(Integer limaAct) {
        this.limaAct = limaAct;
    }

    public Integer getLoretoAct() {
        return loretoAct;
    }

    public void setLoretoAct(Integer loretoAct) {
        this.loretoAct = loretoAct;
    }

    public Integer getMadreDeDiosAct() {
        return madreDeDiosAct;
    }

    public void setMadreDeDiosAct(Integer madreDeDiosAct) {
        this.madreDeDiosAct = madreDeDiosAct;
    }

    public Integer getMoqueguaAct() {
        return moqueguaAct;
    }

    public void setMoqueguaAct(Integer moqueguaAct) {
        this.moqueguaAct = moqueguaAct;
    }

    public Integer getPascoAct() {
        return pascoAct;
    }

    public void setPascoAct(Integer pascoAct) {
        this.pascoAct = pascoAct;
    }

    public Integer getPiuraAct() {
        return piuraAct;
    }

    public void setPiuraAct(Integer piuraAct) {
        this.piuraAct = piuraAct;
    }

    public Integer getPunoAct() {
        return punoAct;
    }

    public void setPunoAct(Integer punoAct) {
        this.punoAct = punoAct;
    }

    public Integer getSanMartinAct() {
        return sanMartinAct;
    }

    public void setSanMartinAct(Integer sanMartinAct) {
        this.sanMartinAct = sanMartinAct;
    }

    public Integer getTacnaAct() {
        return tacnaAct;
    }

    public void setTacnaAct(Integer tacnaAct) {
        this.tacnaAct = tacnaAct;
    }

    public Integer getTumbesAct() {
        return tumbesAct;
    }

    public void setTumbesAct(Integer tumbesAct) {
        this.tumbesAct = tumbesAct;
    }

    public Integer getCallaoAct() {
        return callaoAct;
    }

    public void setCallaoAct(Integer callaoAct) {
        this.callaoAct = callaoAct;
    }

    public Integer getUcayaliAct() {
        return ucayaliAct;
    }

    public void setUcayaliAct(Integer ucayaliAct) {
        this.ucayaliAct = ucayaliAct;
    }

    public Integer getAmazonasLat() {
        return amazonasLat;
    }

    public void setAmazonasLat(Integer amazonasLat) {
        this.amazonasLat = amazonasLat;
    }

    public Integer getAncashLat() {
        return ancashLat;
    }

    public void setAncashLat(Integer ancashLat) {
        this.ancashLat = ancashLat;
    }

    public Integer getApurimacLat() {
        return apurimacLat;
    }

    public void setApurimacLat(Integer apurimacLat) {
        this.apurimacLat = apurimacLat;
    }

    public Integer getArequipaLat() {
        return arequipaLat;
    }

    public void setArequipaLat(Integer arequipaLat) {
        this.arequipaLat = arequipaLat;
    }

    public Integer getAyacuchoLat() {
        return ayacuchoLat;
    }

    public void setAyacuchoLat(Integer ayacuchoLat) {
        this.ayacuchoLat = ayacuchoLat;
    }

    public Integer getCajamarcaLat() {
        return cajamarcaLat;
    }

    public void setCajamarcaLat(Integer cajamarcaLat) {
        this.cajamarcaLat = cajamarcaLat;
    }

    public Integer getCuscoLat() {
        return cuscoLat;
    }

    public void setCuscoLat(Integer cuscoLat) {
        this.cuscoLat = cuscoLat;
    }

    public Integer getHuancavelicaLat() {
        return huancavelicaLat;
    }

    public void setHuancavelicaLat(Integer huancavelicaLat) {
        this.huancavelicaLat = huancavelicaLat;
    }

    public Integer getHuanucoLat() {
        return huanucoLat;
    }

    public void setHuanucoLat(Integer huanucoLat) {
        this.huanucoLat = huanucoLat;
    }

    public Integer getIcaLat() {
        return icaLat;
    }

    public void setIcaLat(Integer icaLat) {
        this.icaLat = icaLat;
    }

    public Integer getJuninLat() {
        return juninLat;
    }

    public void setJuninLat(Integer juninLat) {
        this.juninLat = juninLat;
    }

    public Integer getLaLibertadLat() {
        return laLibertadLat;
    }

    public void setLaLibertadLat(Integer laLibertadLat) {
        this.laLibertadLat = laLibertadLat;
    }

    public Integer getLambayequeLat() {
        return lambayequeLat;
    }

    public void setLambayequeLat(Integer lambayequeLat) {
        this.lambayequeLat = lambayequeLat;
    }

    public Integer getLimaLat() {
        return limaLat;
    }

    public void setLimaLat(Integer limaLat) {
        this.limaLat = limaLat;
    }

    public Integer getLoretoLat() {
        return loretoLat;
    }

    public void setLoretoLat(Integer loretoLat) {
        this.loretoLat = loretoLat;
    }

    public Integer getMadreDeDiosLat() {
        return madreDeDiosLat;
    }

    public void setMadreDeDiosLat(Integer madreDeDiosLat) {
        this.madreDeDiosLat = madreDeDiosLat;
    }

    public Integer getMoqueguaLat() {
        return moqueguaLat;
    }

    public void setMoqueguaLat(Integer moqueguaLat) {
        this.moqueguaLat = moqueguaLat;
    }

    public Integer getPascoLat() {
        return pascoLat;
    }

    public void setPascoLat(Integer pascoLat) {
        this.pascoLat = pascoLat;
    }

    public Integer getPiuraLat() {
        return piuraLat;
    }

    public void setPiuraLat(Integer piuraLat) {
        this.piuraLat = piuraLat;
    }

    public Integer getPunoLat() {
        return punoLat;
    }

    public void setPunoLat(Integer punoLat) {
        this.punoLat = punoLat;
    }

    public Integer getSanMartinLat() {
        return sanMartinLat;
    }

    public void setSanMartinLat(Integer sanMartinLat) {
        this.sanMartinLat = sanMartinLat;
    }

    public Integer getTacnaLat() {
        return tacnaLat;
    }

    public void setTacnaLat(Integer tacnaLat) {
        this.tacnaLat = tacnaLat;
    }

    public Integer getTumbesLat() {
        return tumbesLat;
    }

    public void setTumbesLat(Integer tumbesLat) {
        this.tumbesLat = tumbesLat;
    }

    public Integer getCallaoLat() {
        return callaoLat;
    }

    public void setCallaoLat(Integer callaoLat) {
        this.callaoLat = callaoLat;
    }

    public Integer getUcayaliLat() {
        return ucayaliLat;
    }

    public void setUcayaliLat(Integer ucayaliLat) {
        this.ucayaliLat = ucayaliLat;
    }

    public String getMesPublicacion() {
        return mesPublicacion;
    }

    public void setMesPublicacion(String mesPublicacion) {
        this.mesPublicacion = mesPublicacion;
    }

    public Integer getTotalCasosRegistradosMes() {
        return totalCasosRegistradosMes;
    }

    public void setTotalCasosRegistradosMes(Integer totalCasosRegistradosMes) {
        this.totalCasosRegistradosMes = totalCasosRegistradosMes;
    }

    public String getPorcentajeTotalCasosActivos() {
        return porcentajeTotalCasosActivos;
    }

    public void setPorcentajeTotalCasosActivos(String porcentajeTotalCasosActivos) {
        this.porcentajeTotalCasosActivos = porcentajeTotalCasosActivos;
    }

    public String getPorcentajeTotalCasosLatentes() {
        return porcentajeTotalCasosLatentes;
    }

    public void setPorcentajeTotalCasosLatentes(String porcentajeTotalCasosLatentes) {
        this.porcentajeTotalCasosLatentes = porcentajeTotalCasosLatentes;
    }

    public Integer getTotalCasosLatentesObservacion() {
        return totalCasosLatentesObservacion;
    }

    public void setTotalCasosLatentesObservacion(Integer totalCasosLatentesObservacion) {
        this.totalCasosLatentesObservacion = totalCasosLatentesObservacion;
    }

    public Integer getTotalCasosGeneralDiagolo() {
        return totalCasosGeneralDiagolo;
    }

    public void setTotalCasosGeneralDiagolo(Integer totalCasosGeneralDiagolo) {
        this.totalCasosGeneralDiagolo = totalCasosGeneralDiagolo;
    }

    public String getPorcentajeGeneralDiagolo() {
        return porcentajeGeneralDiagolo;
    }

    public void setPorcentajeGeneralDiagolo(String porcentajeGeneralDiagolo) {
        this.porcentajeGeneralDiagolo = porcentajeGeneralDiagolo;
    }

    public Integer getTotalCasosGeneralActivo() {
        return totalCasosGeneralActivo;
    }

    public void setTotalCasosGeneralActivo(Integer totalCasosGeneralActivo) {
        this.totalCasosGeneralActivo = totalCasosGeneralActivo;
    }

    public String getPorcentajeCasosFaseDialogo() {
        return porcentajeCasosFaseDialogo;
    }

    public void setPorcentajeCasosFaseDialogo(String porcentajeCasosFaseDialogo) {
        this.porcentajeCasosFaseDialogo = porcentajeCasosFaseDialogo;
    }

    public Integer getTotalCasosGeneralReunionesPreparatorias() {
        return totalCasosGeneralReunionesPreparatorias;
    }

    public void setTotalCasosGeneralReunionesPreparatorias(Integer totalCasosGeneralReunionesPreparatorias) {
        this.totalCasosGeneralReunionesPreparatorias = totalCasosGeneralReunionesPreparatorias;
    }

    public String getPorcentajeGeneralReunionesPreparatorias() {
        return porcentajeGeneralReunionesPreparatorias;
    }

    public void setPorcentajeGeneralReunionesPreparatorias(String porcentajeGeneralReunionesPreparatorias) {
        this.porcentajeGeneralReunionesPreparatorias = porcentajeGeneralReunionesPreparatorias;
    }

    public Integer getTotalCasosGeneralParticipacionDefensoria() {
        return totalCasosGeneralParticipacionDefensoria;
    }

    public void setTotalCasosGeneralParticipacionDefensoria(Integer totalCasosGeneralParticipacionDefensoria) {
        this.totalCasosGeneralParticipacionDefensoria = totalCasosGeneralParticipacionDefensoria;
    }

    public String getPorcentajeGeneralParticipacionDefensoria() {
        return porcentajeGeneralParticipacionDefensoria;
    }

    public void setPorcentajeGeneralParticipacionDefensoria(String porcentajeGeneralParticipacionDefensoria) {
        this.porcentajeGeneralParticipacionDefensoria = porcentajeGeneralParticipacionDefensoria;
    }

    public Integer getTotalCasosGeneralEchosViolencia() {
        return totalCasosGeneralEchosViolencia;
    }

    public void setTotalCasosGeneralEchosViolencia(Integer totalCasosGeneralEchosViolencia) {
        this.totalCasosGeneralEchosViolencia = totalCasosGeneralEchosViolencia;
    }

    public String getPorcentajeGeneralEchosViolencia() {
        return porcentajeGeneralEchosViolencia;
    }

    public void setPorcentajeGeneralEchosViolencia(String porcentajeGeneralEchosViolencia) {
        this.porcentajeGeneralEchosViolencia = porcentajeGeneralEchosViolencia;
    }

    public Integer getTotalCasosAccionesProtesta() {
        return totalCasosAccionesProtesta;
    }

    public void setTotalCasosAccionesProtesta(Integer totalCasosAccionesProtesta) {
        this.totalCasosAccionesProtesta = totalCasosAccionesProtesta;
    }

    public String getPorcentajeAccionesProtesta() {
        return porcentajeAccionesProtesta;
    }

    public void setPorcentajeAccionesProtesta(String porcentajeAccionesProtesta) {
        this.porcentajeAccionesProtesta = porcentajeAccionesProtesta;
    }

    public String getEstadoConflictoTexto() {
        return estadoConflictoTexto;
    }

    public void setEstadoConflictoTexto(String estadoConflictoTexto) {
        this.estadoConflictoTexto = estadoConflictoTexto;
    }

    public Integer getTotalCasosGeneralEscalamiento() {
        return totalCasosGeneralEscalamiento;
    }

    public void setTotalCasosGeneralEscalamiento(Integer totalCasosGeneralEscalamiento) {
        this.totalCasosGeneralEscalamiento = totalCasosGeneralEscalamiento;
    }

    public String getPorcentajeGeneralEscalamiento() {
        return porcentajeGeneralEscalamiento;
    }

    public void setPorcentajeGeneralEscalamiento(String porcentajeGeneralEscalamiento) {
        this.porcentajeGeneralEscalamiento = porcentajeGeneralEscalamiento;
    }

    public Integer getTotalCasosGeneralTemprana() {
        return totalCasosGeneralTemprana;
    }

    public void setTotalCasosGeneralTemprana(Integer totalCasosGeneralTemprana) {
        this.totalCasosGeneralTemprana = totalCasosGeneralTemprana;
    }

    public String getPorcentajeGeneralTemprana() {
        return porcentajeGeneralTemprana;
    }

    public void setPorcentajeGeneralTemprana(String porcentajeGeneralTemprana) {
        this.porcentajeGeneralTemprana = porcentajeGeneralTemprana;
    }

    public Integer getTotalCasosGeneralDesescalamiento() {
        return totalCasosGeneralDesescalamiento;
    }

    public void setTotalCasosGeneralDesescalamiento(Integer totalCasosGeneralDesescalamiento) {
        this.totalCasosGeneralDesescalamiento = totalCasosGeneralDesescalamiento;
    }

    public String getPorcentajeGeneralDesescalamiento() {
        return porcentajeGeneralDesescalamiento;
    }

    public void setPorcentajeGeneralDesescalamiento(String porcentajeGeneralDesescalamiento) {
        this.porcentajeGeneralDesescalamiento = porcentajeGeneralDesescalamiento;
    }

    public Integer getTotalCasosGeneralCrisis() {
        return totalCasosGeneralCrisis;
    }

    public void setTotalCasosGeneralCrisis(Integer totalCasosGeneralCrisis) {
        this.totalCasosGeneralCrisis = totalCasosGeneralCrisis;
    }

    public String getPorcentajeGeneralCrisis() {
        return porcentajeGeneralCrisis;
    }

    public void setPorcentajeGeneralCrisis(String porcentajeGeneralCrisis) {
        this.porcentajeGeneralCrisis = porcentajeGeneralCrisis;
    }

    public String getCadenaNombreCaso() {
        return cadenaNombreCaso;
    }

    public void setCadenaNombreCaso(String cadenaNombreCaso) {
        this.cadenaNombreCaso = cadenaNombreCaso;
    }

    public List<NuevoCaso> getNuevoCasos() {
        return nuevoCasos;
    }

    public void setNuevoCasos(List<NuevoCaso> nuevoCasos) {
        this.nuevoCasos = nuevoCasos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getTotalCasosTotalMes() {
        return totalCasosTotalMes;
    }

    public void setTotalCasosTotalMes(Integer totalCasosTotalMes) {
        this.totalCasosTotalMes = totalCasosTotalMes;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    public String getNivelGobiernoTexto() {
        return nivelGobiernoTexto;
    }

    public void setNivelGobiernoTexto(String nivelGobiernoTexto) {
        this.nivelGobiernoTexto = nivelGobiernoTexto;
    }

    public List<ElementoNombreValor> getListaNivelGobierno() {
        return listaNivelGobierno;
    }

    public void setListaNivelGobierno(List<ElementoNombreValor> listaNivelGobierno) {
        this.listaNivelGobierno = listaNivelGobierno;
    }

    public List<ElementoNombreValor> getListaNivelGobiernoGrafico() {
        return listaNivelGobiernoGrafico;
    }

    public void setListaNivelGobiernoGrafico(List<ElementoNombreValor> listaNivelGobiernoGrafico) {
        this.listaNivelGobiernoGrafico = listaNivelGobiernoGrafico;
    }

    public List<ElementoReporte> getElementoReportesAutoridad() {
        return elementoReportesAutoridad;
    }

    public void setElementoReportesAutoridad(List<ElementoReporte> elementoReportesAutoridad) {
        this.elementoReportesAutoridad = elementoReportesAutoridad;
    }

    public List<ElementoReporte> getElementoReportesDepartamentoEstado() {
        return elementoReportesDepartamentoEstado;
    }

    public void setElementoReportesDepartamentoEstado(List<ElementoReporte> elementoReportesDepartamentoEstado) {
        this.elementoReportesDepartamentoEstado = elementoReportesDepartamentoEstado;
    }

    public Integer getTotalCasosResueltoMes() {
        return totalCasosResueltoMes;
    }

    public void setTotalCasosResueltoMes(Integer totalCasosResueltoMes) {
        this.totalCasosResueltoMes = totalCasosResueltoMes;
    }

    public String getImagePath001() {
        return imagePath001;
    }

    public void setImagePath001(String imagePath001) {
        this.imagePath001 = imagePath001;
    }

    public Integer getTotalCasosRegistrados() {
        return totalCasosRegistrados;
    }

    public void setTotalCasosRegistrados(Integer totalCasosRegistrados) {
        this.totalCasosRegistrados = totalCasosRegistrados;
    }

    public Integer getTotalCasosActivos() {
        return totalCasosActivos;
    }

    public void setTotalCasosActivos(Integer totalCasosActivos) {
        this.totalCasosActivos = totalCasosActivos;
    }

    public Integer getTotalCasosLatentes() {
        return totalCasosLatentes;
    }

    public void setTotalCasosLatentes(Integer totalCasosLatentes) {
        this.totalCasosLatentes = totalCasosLatentes;
    }

    public Integer getTotalCasosDeLatenteAActivoMes() {
        return totalCasosDeLatenteAActivoMes;
    }

    public void setTotalCasosDeLatenteAActivoMes(Integer totalCasosDeLatenteAActivoMes) {
        this.totalCasosDeLatenteAActivoMes = totalCasosDeLatenteAActivoMes;
    }

    public Integer getTotalCasosDeRetiradoAActivoMes() {
        return totalCasosDeRetiradoAActivoMes;
    }

    public void setTotalCasosDeRetiradoAActivoMes(Integer totalCasosDeRetiradoAActivoMes) {
        this.totalCasosDeRetiradoAActivoMes = totalCasosDeRetiradoAActivoMes;
    }

    public Integer getTotalCasosDeActivosALatenteMes() {
        return totalCasosDeActivosALatenteMes;
    }

    public void setTotalCasosDeActivosALatenteMes(Integer totalCasosDeActivosALatenteMes) {
        this.totalCasosDeActivosALatenteMes = totalCasosDeActivosALatenteMes;
    }

    public Integer getTotalCasosDeLatenteARetiradoMes() {
        return totalCasosDeLatenteARetiradoMes;
    }

    public void setTotalCasosDeLatenteARetiradoMes(Integer totalCasosDeLatenteARetiradoMes) {
        this.totalCasosDeLatenteARetiradoMes = totalCasosDeLatenteARetiradoMes;
    }

    public Integer getTotalCasosFaseDialogo() {
        return totalCasosFaseDialogo;
    }

    public void setTotalCasosFaseDialogo(Integer totalCasosFaseDialogo) {
        this.totalCasosFaseDialogo = totalCasosFaseDialogo;
    }

    public Integer getTotalCasosDialogoNegociacion() {
        return totalCasosDialogoNegociacion;
    }

    public void setTotalCasosDialogoNegociacion(Integer totalCasosDialogoNegociacion) {
        this.totalCasosDialogoNegociacion = totalCasosDialogoNegociacion;
    }

    public Integer getTotalCasosDialogoReuniones() {
        return totalCasosDialogoReuniones;
    }

    public void setTotalCasosDialogoReuniones(Integer totalCasosDialogoReuniones) {
        this.totalCasosDialogoReuniones = totalCasosDialogoReuniones;
    }

    public Integer getTotalCasosDialogoEspacioDialogo() {
        return totalCasosDialogoEspacioDialogo;
    }

    public void setTotalCasosDialogoEspacioDialogo(Integer totalCasosDialogoEspacioDialogo) {
        this.totalCasosDialogoEspacioDialogo = totalCasosDialogoEspacioDialogo;
    }

    public String getPorcentajeCasosFaseDialogoNegociacion() {
        return porcentajeCasosFaseDialogoNegociacion;
    }

    public void setPorcentajeCasosFaseDialogoNegociacion(String porcentajeCasosFaseDialogoNegociacion) {
        this.porcentajeCasosFaseDialogoNegociacion = porcentajeCasosFaseDialogoNegociacion;
    }

    public String getPorcentajeCasosFaseDialogoReuniones() {
        return porcentajeCasosFaseDialogoReuniones;
    }

    public void setPorcentajeCasosFaseDialogoReuniones(String porcentajeCasosFaseDialogoReuniones) {
        this.porcentajeCasosFaseDialogoReuniones = porcentajeCasosFaseDialogoReuniones;
    }

    public String getPorcentajeCasosFaseDialogoEspacionDialogo() {
        return porcentajeCasosFaseDialogoEspacionDialogo;
    }

    public void setPorcentajeCasosFaseDialogoEspacionDialogo(String porcentajeCasosFaseDialogoEspacionDialogo) {
        this.porcentajeCasosFaseDialogoEspacionDialogo = porcentajeCasosFaseDialogoEspacionDialogo;
    }

    public Integer getTotalCasosACVictimaViolencia() {
        return totalCasosACVictimaViolencia;
    }

    public void setTotalCasosACVictimaViolencia(Integer totalCasosACVictimaViolencia) {
        this.totalCasosACVictimaViolencia = totalCasosACVictimaViolencia;
    }

    public Integer getTotalCasosACVictimaViolenciaMes() {
        return totalCasosACVictimaViolenciaMes;
    }

    public void setTotalCasosACVictimaViolenciaMes(Integer totalCasosACVictimaViolenciaMes) {
        this.totalCasosACVictimaViolenciaMes = totalCasosACVictimaViolenciaMes;
    }

    public String getPorcentajeCasosACVictimaViolencia() {
        return porcentajeCasosACVictimaViolencia;
    }

    public void setPorcentajeCasosACVictimaViolencia(String porcentajeCasosACVictimaViolencia) {
        this.porcentajeCasosACVictimaViolencia = porcentajeCasosACVictimaViolencia;
    }

    public String getPorcentajeCasosACVictimaViolenciaMes() {
        return porcentajeCasosACVictimaViolenciaMes;
    }

    public void setPorcentajeCasosACVictimaViolenciaMes(String porcentajeCasosACVictimaViolenciaMes) {
        this.porcentajeCasosACVictimaViolenciaMes = porcentajeCasosACVictimaViolenciaMes;
    }

    public Integer getTotalCasosACAccionesProtestaMes() {
        return totalCasosACAccionesProtestaMes;
    }

    public void setTotalCasosACAccionesProtestaMes(Integer totalCasosACAccionesProtestaMes) {
        this.totalCasosACAccionesProtestaMes = totalCasosACAccionesProtestaMes;
    }

    public Integer getTotalCasosAD() {
        return totalCasosAD;
    }

    public void setTotalCasosAD(Integer totalCasosAD) {
        this.totalCasosAD = totalCasosAD;
    }

    public Integer getTotalCasosADMes() {
        return totalCasosADMes;
    }

    public void setTotalCasosADMes(Integer totalCasosADMes) {
        this.totalCasosADMes = totalCasosADMes;
    }

    public Integer getTotalActividadAccionesHumanitariasMes() {
        return totalActividadAccionesHumanitariasMes;
    }

    public void setTotalActividadAccionesHumanitariasMes(Integer totalActividadAccionesHumanitariasMes) {
        this.totalActividadAccionesHumanitariasMes = totalActividadAccionesHumanitariasMes;
    }

    public Integer getTotalActividadIntermediacionMes() {
        return totalActividadIntermediacionMes;
    }

    public void setTotalActividadIntermediacionMes(Integer totalActividadIntermediacionMes) {
        this.totalActividadIntermediacionMes = totalActividadIntermediacionMes;
    }

    public Integer getTotalActividadSupervisionPreventivaMes() {
        return totalActividadSupervisionPreventivaMes;
    }

    public void setTotalActividadSupervisionPreventivaMes(Integer totalActividadSupervisionPreventivaMes) {
        this.totalActividadSupervisionPreventivaMes = totalActividadSupervisionPreventivaMes;
    }

    public Integer getTotalActividadAccionesDefensaLegalMes() {
        return totalActividadAccionesDefensaLegalMes;
    }

    public void setTotalActividadAccionesDefensaLegalMes(Integer totalActividadAccionesDefensaLegalMes) {
        this.totalActividadAccionesDefensaLegalMes = totalActividadAccionesDefensaLegalMes;
    }

    public List<CuadroGenericoMes> getListaCasosRegistradosMES() {
        return listaCasosRegistradosMES;
    }

    public void setListaCasosRegistradosMES(List<CuadroGenericoMes> listaCasosRegistradosMES) {
        this.listaCasosRegistradosMES = listaCasosRegistradosMES;
    }
    
    public List<ElementoNombreValor> getRegistradoTotals() {
        return registradoTotals;
    }

    public void setRegistradoTotals(List<ElementoNombreValor> registradoTotals) {
        this.registradoTotals = registradoTotals;
    }

    public List<ReporteSimcoCaso> getListaCasosNuevosPorMes() {
        return listaCasosNuevosPorMes;
    }

    public void setListaCasosNuevosPorMes(List<ReporteSimcoCaso> listaCasosNuevosPorMes) {
        this.listaCasosNuevosPorMes = listaCasosNuevosPorMes;
    }

    public List<ReporteSimcoCaso> getListaCasosResueltosPorMes() {
        return listaCasosResueltosPorMes;
    }

    public void setListaCasosResueltosPorMes(List<ReporteSimcoCaso> listaCasosResueltosPorMes) {
        this.listaCasosResueltosPorMes = listaCasosResueltosPorMes;
    }

    public List<CuadroGenericoMes> getListaCasosResueltosMES() {
        return listaCasosResueltosMES;
    }

    public void setListaCasosResueltosMES(List<CuadroGenericoMes> listaCasosResueltosMES) {
        this.listaCasosResueltosMES = listaCasosResueltosMES;
    }

    public List<ElementoNombreValor> getListaNivelTipo() {
        return listaNivelTipo;
    }

    public void setListaNivelTipo(List<ElementoNombreValor> listaNivelTipo) {
        this.listaNivelTipo = listaNivelTipo;
    }

    public List<ElementoNombreValor> getListaNivelTipoGrafico() {
        return listaNivelTipoGrafico;
    }

    public void setListaNivelTipoGrafico(List<ElementoNombreValor> listaNivelTipoGrafico) {
        this.listaNivelTipoGrafico = listaNivelTipoGrafico;
    }

    public List<ElementoNombreValor> getListaNivelSubTipo() {
        return listaNivelSubTipo;
    }

    public void setListaNivelSubTipo(List<ElementoNombreValor> listaNivelSubTipo) {
        this.listaNivelSubTipo = listaNivelSubTipo;
    }

    public List<ElementoNombreValor> getListaNivelSubTipoGrafico() {
        return listaNivelSubTipoGrafico;
    }

    public void setListaNivelSubTipoGrafico(List<ElementoNombreValor> listaNivelSubTipoGrafico) {
        this.listaNivelSubTipoGrafico = listaNivelSubTipoGrafico;
    }

    public List<ReporteSimcoCaso> getListaCasosActivosTotales() {
        return listaCasosActivosTotales;
    }

    public void setListaCasosActivosTotales(List<ReporteSimcoCaso> listaCasosActivosTotales) {
        this.listaCasosActivosTotales = listaCasosActivosTotales;
    }

    public String getAnhoPublicacion() {
        return anhoPublicacion;
    }

    public void setAnhoPublicacion(String anhoPublicacion) {
        this.anhoPublicacion = anhoPublicacion;
    }

    public List<ElementoNombreValor> getListaNivelTipoDialogo() {
        return listaNivelTipoDialogo;
    }

    public void setListaNivelTipoDialogo(List<ElementoNombreValor> listaNivelTipoDialogo) {
        this.listaNivelTipoDialogo = listaNivelTipoDialogo;
    }

    public List<ElementoNombreValor> getListaNivelTipoDialogoGrafico() {
        return listaNivelTipoDialogoGrafico;
    }

    public void setListaNivelTipoDialogoGrafico(List<ElementoNombreValor> listaNivelTipoDialogoGrafico) {
        this.listaNivelTipoDialogoGrafico = listaNivelTipoDialogoGrafico;
    }

    public List<ReporteSimcoCaso> getListaCasosReactivadosPorMes() {
        return listaCasosReactivadosPorMes;
    }

    public void setListaCasosReactivadosPorMes(List<ReporteSimcoCaso> listaCasosReactivadosPorMes) {
        this.listaCasosReactivadosPorMes = listaCasosReactivadosPorMes;
    }

    public List<ReporteSimcoCaso> getListaCasosLatentesPorMes() {
        return listaCasosLatentesPorMes;
    }

    public void setListaCasosLatentesPorMes(List<ReporteSimcoCaso> listaCasosLatentesPorMes) {
        this.listaCasosLatentesPorMes = listaCasosLatentesPorMes;
    }

    public List<ReporteSimcoCaso> getListaCasosActivoALatentes() {
        return listaCasosActivoALatentes;
    }

    public void setListaCasosActivoALatentes(List<ReporteSimcoCaso> listaCasosActivoALatentes) {
        this.listaCasosActivoALatentes = listaCasosActivoALatentes;
    }

    public Integer getTotalActividadAccionesDifusionMes() {
        return totalActividadAccionesDifusionMes;
    }

    public void setTotalActividadAccionesDifusionMes(Integer totalActividadAccionesDifusionMes) {
        this.totalActividadAccionesDifusionMes = totalActividadAccionesDifusionMes;
    }

}
