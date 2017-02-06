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
    
    private String mesPublicacion;
    
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
    
    private List<EstadoConflicto> estadoConflictos;
    
    private List<CuadroMensualAño> listaCuadroMensualAño;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private ReporteMapa mapa;
    
    private String titulo1;
    
    private String imagePath001;
    
    private List<ElementoNombreValor> listaMensualCasosTotales;
    
    private List<ElementoNombreValor> listaMensualCasosActivos;
    
    private List<ElementoNombreValor> listaNivelGobierno;
    
    private List<ElementoNombreValor> listaNivelGobiernoGrafico;
    
    private List<ElementoReporte> elementoReportesAutoridad;
    
    private List<ElementoReporte> elementoReportesDepartamentoEstado;
    
    
    
    private List<FaceTotal> faceTotals;
    
    private List<NuevoCaso> nuevoCasos;
    
    private String nombre;
    
    private Integer valor;
    
    private String valorParametro;
    
    private String cadenaNombreCaso;
    
    
    
    
    
    private Integer totalCasosActivoMes;
    
    
    private Integer totalCasosTotalMes;
    
    private Integer totalCasosLatenteMes;
    
    
    
    private Integer totalCasosActivosLatentes;
    
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
    
    private Integer totalGeneralActuacionDefensorial;
    
    private String porcentajeActuacionDefensorial;
    
    private Integer totalGeneralActuacionDefensorialSupervisionPreventiva;
    
    private Integer totalGeneralActuacionDefensorialIntermediaciones;
            
    private Integer totalGeneralActuacionDefensorialAccionHumanitaria;
    
    private Integer totalGeneralActuacionDefensorialDefensaLegal;
    
    private String estadoConflictoTexto;
    
    private String nivelGobiernoTexto;
    
    private String casosNuevosTexto;
    
    private String procedoDialogoTexto1;
    
    private String procedoDialogoTexto2;
    
    private String hechoViolenciaTexto1;
    
    private String accionesProtestaTexto1;
    
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
        private String amazonasNom;
        private String ancashNom;
        private String apurimacNom;
        private String arequipaNom;
        private String ayacuchoNom;
        private String cajamarcaNom;
        private String cuscoNom;
        private String huancavelicaNom;
        private String huanucoNom;
        private String icaNom;
        private String juninNom;
        private String laLibertadNom;
        private String lambayequeNom;
        private String limaNom;
        private String loretoNom;
        private String madreDeDiosNom;
        private String moqueguaNom;
        private String pascoNom;
        private String piuraNom;
        private String punoNom;
        private String sanMartinNom;
        private String tacnaNom;
        private String tumbesNom;
        private String callaoNom;
        private String ucayaliNom;

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

    public String getAmazonasNom() {
        return amazonasNom;
    }

    public void setAmazonasNom(String amazonasNom) {
        this.amazonasNom = amazonasNom;
    }

    public String getAncashNom() {
        return ancashNom;
    }

    public void setAncashNom(String ancashNom) {
        this.ancashNom = ancashNom;
    }

    public String getApurimacNom() {
        return apurimacNom;
    }

    public void setApurimacNom(String apurimacNom) {
        this.apurimacNom = apurimacNom;
    }

    public String getArequipaNom() {
        return arequipaNom;
    }

    public void setArequipaNom(String arequipaNom) {
        this.arequipaNom = arequipaNom;
    }

    public String getAyacuchoNom() {
        return ayacuchoNom;
    }

    public void setAyacuchoNom(String ayacuchoNom) {
        this.ayacuchoNom = ayacuchoNom;
    }

    public String getCajamarcaNom() {
        return cajamarcaNom;
    }

    public void setCajamarcaNom(String cajamarcaNom) {
        this.cajamarcaNom = cajamarcaNom;
    }

    public String getCuscoNom() {
        return cuscoNom;
    }

    public void setCuscoNom(String cuscoNom) {
        this.cuscoNom = cuscoNom;
    }

    public String getHuancavelicaNom() {
        return huancavelicaNom;
    }

    public void setHuancavelicaNom(String huancavelicaNom) {
        this.huancavelicaNom = huancavelicaNom;
    }

    public String getHuanucoNom() {
        return huanucoNom;
    }

    public void setHuanucoNom(String huanucoNom) {
        this.huanucoNom = huanucoNom;
    }

    public String getIcaNom() {
        return icaNom;
    }

    public void setIcaNom(String icaNom) {
        this.icaNom = icaNom;
    }

    public String getJuninNom() {
        return juninNom;
    }

    public void setJuninNom(String juninNom) {
        this.juninNom = juninNom;
    }

    public String getLaLibertadNom() {
        return laLibertadNom;
    }

    public void setLaLibertadNom(String laLibertadNom) {
        this.laLibertadNom = laLibertadNom;
    }

    public String getLambayequeNom() {
        return lambayequeNom;
    }

    public void setLambayequeNom(String lambayequeNom) {
        this.lambayequeNom = lambayequeNom;
    }

    public String getLimaNom() {
        return limaNom;
    }

    public void setLimaNom(String limaNom) {
        this.limaNom = limaNom;
    }

    public String getLoretoNom() {
        return loretoNom;
    }

    public void setLoretoNom(String loretoNom) {
        this.loretoNom = loretoNom;
    }

    public String getMadreDeDiosNom() {
        return madreDeDiosNom;
    }

    public void setMadreDeDiosNom(String madreDeDiosNom) {
        this.madreDeDiosNom = madreDeDiosNom;
    }

    public String getMoqueguaNom() {
        return moqueguaNom;
    }

    public void setMoqueguaNom(String moqueguaNom) {
        this.moqueguaNom = moqueguaNom;
    }

    public String getPascoNom() {
        return pascoNom;
    }

    public void setPascoNom(String pascoNom) {
        this.pascoNom = pascoNom;
    }

    public String getPiuraNom() {
        return piuraNom;
    }

    public void setPiuraNom(String piuraNom) {
        this.piuraNom = piuraNom;
    }

    public String getPunoNom() {
        return punoNom;
    }

    public void setPunoNom(String punoNom) {
        this.punoNom = punoNom;
    }

    public String getSanMartinNom() {
        return sanMartinNom;
    }

    public void setSanMartinNom(String sanMartinNom) {
        this.sanMartinNom = sanMartinNom;
    }

    public String getTacnaNom() {
        return tacnaNom;
    }

    public void setTacnaNom(String tacnaNom) {
        this.tacnaNom = tacnaNom;
    }

    public String getTumbesNom() {
        return tumbesNom;
    }

    public void setTumbesNom(String tumbesNom) {
        this.tumbesNom = tumbesNom;
    }

    public String getCallaoNom() {
        return callaoNom;
    }

    public void setCallaoNom(String callaoNom) {
        this.callaoNom = callaoNom;
    }

    public String getUcayaliNom() {
        return ucayaliNom;
    }

    public void setUcayaliNom(String ucayaliNom) {
        this.ucayaliNom = ucayaliNom;
    }
        
        
        
        
        /***departamentos**/
    

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

    

    public Integer getTotalCasosActivoMes() {
        return totalCasosActivoMes;
    }

    public void setTotalCasosActivoMes(Integer totalCasosActivoMes) {
        this.totalCasosActivoMes = totalCasosActivoMes;
    }

    public Integer getTotalCasosLatenteMes() {
        return totalCasosLatenteMes;
    }

    public void setTotalCasosLatenteMes(Integer totalCasosLatenteMes) {
        this.totalCasosLatenteMes = totalCasosLatenteMes;
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

    public Integer getTotalCasosActivosLatentes() {
        return totalCasosActivosLatentes;
    }

    public void setTotalCasosActivosLatentes(Integer totalCasosActivosLatentes) {
        this.totalCasosActivosLatentes = totalCasosActivosLatentes;
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

    public Integer getTotalGeneralActuacionDefensorial() {
        return totalGeneralActuacionDefensorial;
    }

    public void setTotalGeneralActuacionDefensorial(Integer totalGeneralActuacionDefensorial) {
        this.totalGeneralActuacionDefensorial = totalGeneralActuacionDefensorial;
    }

    public Integer getTotalGeneralActuacionDefensorialSupervisionPreventiva() {
        return totalGeneralActuacionDefensorialSupervisionPreventiva;
    }

    public void setTotalGeneralActuacionDefensorialSupervisionPreventiva(Integer totalGeneralActuacionDefensorialSupervisionPreventiva) {
        this.totalGeneralActuacionDefensorialSupervisionPreventiva = totalGeneralActuacionDefensorialSupervisionPreventiva;
    }

    public Integer getTotalGeneralActuacionDefensorialIntermediaciones() {
        return totalGeneralActuacionDefensorialIntermediaciones;
    }

    public void setTotalGeneralActuacionDefensorialIntermediaciones(Integer totalGeneralActuacionDefensorialIntermediaciones) {
        this.totalGeneralActuacionDefensorialIntermediaciones = totalGeneralActuacionDefensorialIntermediaciones;
    }

    public Integer getTotalGeneralActuacionDefensorialAccionHumanitaria() {
        return totalGeneralActuacionDefensorialAccionHumanitaria;
    }

    public void setTotalGeneralActuacionDefensorialAccionHumanitaria(Integer totalGeneralActuacionDefensorialAccionHumanitaria) {
        this.totalGeneralActuacionDefensorialAccionHumanitaria = totalGeneralActuacionDefensorialAccionHumanitaria;
    }

    public Integer getTotalGeneralActuacionDefensorialDefensaLegal() {
        return totalGeneralActuacionDefensorialDefensaLegal;
    }

    public void setTotalGeneralActuacionDefensorialDefensaLegal(Integer totalGeneralActuacionDefensorialDefensaLegal) {
        this.totalGeneralActuacionDefensorialDefensaLegal = totalGeneralActuacionDefensorialDefensaLegal;
    }

    public String getPorcentajeActuacionDefensorial() {
        return porcentajeActuacionDefensorial;
    }

    public void setPorcentajeActuacionDefensorial(String porcentajeActuacionDefensorial) {
        this.porcentajeActuacionDefensorial = porcentajeActuacionDefensorial;
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

    public List<EstadoConflicto> getEstadoConflictos() {
        return estadoConflictos;
    }

    public void setEstadoConflictos(List<EstadoConflicto> estadoConflictos) {
        this.estadoConflictos = estadoConflictos;
    }

    public List<FaceTotal> getFaceTotals() {
        return faceTotals;
    }

    public void setFaceTotals(List<FaceTotal> faceTotals) {
        this.faceTotals = faceTotals;
    }

    public String getCasosNuevosTexto() {
        return casosNuevosTexto;
    }

    public void setCasosNuevosTexto(String casosNuevosTexto) {
        this.casosNuevosTexto = casosNuevosTexto;
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

    public List<ElementoNombreValor> getListaMensualCasosTotales() {
        return listaMensualCasosTotales;
    }

    public void setListaMensualCasosTotales(List<ElementoNombreValor> listaMensualCasosTotales) {
        this.listaMensualCasosTotales = listaMensualCasosTotales;
    }

    public List<ElementoNombreValor> getListaMensualCasosActivos() {
        return listaMensualCasosActivos;
    }

    public void setListaMensualCasosActivos(List<ElementoNombreValor> listaMensualCasosActivos) {
        this.listaMensualCasosActivos = listaMensualCasosActivos;
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

    public ReporteMapa getMapa() {
        return mapa;
    }

    public void setMapa(ReporteMapa mapa) {
        this.mapa = mapa;
    }

    public Integer getTotalCasosResueltoMes() {
        return totalCasosResueltoMes;
    }

    public void setTotalCasosResueltoMes(Integer totalCasosResueltoMes) {
        this.totalCasosResueltoMes = totalCasosResueltoMes;
    }

    public String getProcedoDialogoTexto1() {
        return procedoDialogoTexto1;
    }

    public void setProcedoDialogoTexto1(String procedoDialogoTexto1) {
        this.procedoDialogoTexto1 = procedoDialogoTexto1;
    }

    public String getProcedoDialogoTexto2() {
        return procedoDialogoTexto2;
    }

    public void setProcedoDialogoTexto2(String procedoDialogoTexto2) {
        this.procedoDialogoTexto2 = procedoDialogoTexto2;
    }

    public String getHechoViolenciaTexto1() {
        return hechoViolenciaTexto1;
    }

    public void setHechoViolenciaTexto1(String hechoViolenciaTexto1) {
        this.hechoViolenciaTexto1 = hechoViolenciaTexto1;
    }

    public String getAccionesProtestaTexto1() {
        return accionesProtestaTexto1;
    }

    public void setAccionesProtestaTexto1(String accionesProtestaTexto1) {
        this.accionesProtestaTexto1 = accionesProtestaTexto1;
    }

    public String getImagePath001() {
        return imagePath001;
    }

    public void setImagePath001(String imagePath001) {
        this.imagePath001 = imagePath001;
    }

    public String getTitulo1() {
        return titulo1;
    }

    public void setTitulo1(String titulo1) {
        this.titulo1 = titulo1;
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

    public List<CuadroMensualAño> getListaCuadroMensualAño() {
        return listaCuadroMensualAño;
    }

    public void setListaCuadroMensualAño(List<CuadroMensualAño> listaCuadroMensualAño) {
        this.listaCuadroMensualAño = listaCuadroMensualAño;
    }

}
