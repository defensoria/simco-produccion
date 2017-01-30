/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.seguimiento.controller;

import gob.dp.simco.administracion.seguridad.controller.LoginController;
import gob.dp.simco.administracion.seguridad.controller.MenuController;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import gob.dp.simco.registro.entity.ActaAcuerdoDetalle;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.service.ActaAcuerdoDetalleService;
import gob.dp.simco.registro.service.ActorService;
import gob.dp.simco.registro.service.CasoService;
import gob.dp.simco.seguimiento.entity.Alerta;
import gob.dp.simco.seguimiento.entity.SeguimientoAcuerdo;
import gob.dp.simco.seguimiento.service.AlertaService;
import gob.dp.simco.seguimiento.service.SeguimientoAcuerdoService;
import gob.dp.simco.seguimiento.type.AntesDespuesType;
import gob.dp.simco.seguimiento.type.DiasProximosType;
import gob.dp.simco.seguimiento.type.DiasUltimosType;
import gob.dp.simco.seguimiento.type.RepeticionType;
import gob.dp.simco.seguimiento.type.TiempoType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class SeguimientoAcuerdoController implements Serializable {

    private static final Logger log = Logger.getLogger(SeguimientoAcuerdoController.class);

    private List<SelectItem> listaTiempo;

    private List<SelectItem> listaAntesDespues;

    private List<SelectItem> listaRepeticion;
    
    private List<SelectItem> listaProximosDias;
    
    private List<SelectItem> listaUltimosDias;

    private List<ActaAcuerdoDetalle> acuerdoDetalles;

    private List<Alerta> listaAlertaEjecutadas;

    private List<Alerta> listaAlertaProgramadas;
    
    private List<Actor> listaActorAlerta;

    private SeguimientoAcuerdo seguimientoAcuerdo;
    
    private Alerta alerta;
    
    private Usuario usuarioSession;
    
    private ActaAcuerdoDetalle actaAcuerdoDetalle;
    
    private Caso caso;
    
    private Integer diasAlertaProgramada;
    
    private Integer diasAlertaEjecutadas;
    
    private Boolean checActivo;

    @Autowired
    private ActaAcuerdoDetalleService actaAcuerdoDetalleService;

    @Autowired
    private AlertaService alertaService;
    
    @Autowired
    private ActorService actorService;
    
    @Autowired
    private SeguimientoAcuerdoService seguimientoAcuerdoService;
    
    @Autowired
    private CasoService casoService;

    public SeguimientoAcuerdoController() {
        seguimientoAcuerdo = new SeguimientoAcuerdo();
    }
    
    private void usuarioSession(){
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }

    public String cargarPagina() {
        usuarioSession();
        listaAlertaEjecutadas = new ArrayList<>();
        listaAlertaProgramadas = new ArrayList<>();
        actaAcuerdoDetalle = new ActaAcuerdoDetalle();
        ActaAcuerdoDetalle ad = new ActaAcuerdoDetalle();
        ad.setUsuarioRegistro(usuarioSession.getCodigo());
        acuerdoDetalles = actaAcuerdoDetalleService.actaAcuerdoDetalleSeguimiento(ad);
        for(ActaAcuerdoDetalle aad : acuerdoDetalles){
            if(aad.getFechaCumplimiento().after(new Date())){
                aad.setColor("yelow");
            }
            if(aad.getFechaCumplimiento().before(new Date())){
                aad.setColor("red");
            }
            if(aad.isIndCumplido())
                aad.setColor("green");
            
            verAlertas(aad);
        }
        seteaValoresInicio();
        return "seguimientoAcuerdo";
    }
    
    public String cargarPaginaCaso(Long idCaso) {
        usuarioSession();
        caso = new Caso();
        caso.setId(idCaso);
        listaAlertaEjecutadas = new ArrayList<>();
        listaAlertaProgramadas = new ArrayList<>();
        acuerdoDetalles = actaAcuerdoDetalleService.actaAcuerdoDetalleSeguimientoCaso(idCaso);
        for(ActaAcuerdoDetalle aad : acuerdoDetalles){
            if(aad.getFechaCumplimiento() != null){
                if(aad.getFechaCumplimiento().after(new Date())){
                    aad.setColor("yelow");
                }
                
                if(aad.getFechaCumplimiento().before(new Date())){
                    aad.setColor("red");
                }
            }
            if(aad.isIndCumplido())
                aad.setColor("green");
            
            verAlertas(aad);
        }
        seteaValoresInicio();
        return "seguimientoAcuerdo";
    }
    
    private void seteaValoresInicio(){
        diasAlertaProgramada = 0;
        diasAlertaEjecutadas = 0;
        checActivo = false;
    }
    
    public void cambiarColor(ActaAcuerdoDetalle aad){
        actaAcuerdoDetalleService.actaAcuerdoDetalleUpdateColor(aad);
    }
    
    public void setearColor(ActaAcuerdoDetalle aad){
        actaAcuerdoDetalleService.actaAcuerdoDetalleUpdateColor(aad);
    }
    
    public void verPendientes(){
        boolean nro = checActivo;
        listaAlertaEjecutadas = new ArrayList<>();
        for(ActaAcuerdoDetalle aad : acuerdoDetalles){
            if(nro){
                verAlertasPendientes(aad);
            }else{
                verAlertas(aad);
            }
        } 
    }
    
    public void verAlertaDetalle(Alerta alert){
        FacesContext context = FacesContext.getCurrentInstance();
        MenuController menuController = (MenuController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "menuController");
        listaActorAlerta = null;
        alert.setEstado("ACT");
        alertaService.alertaUpdate(alert.getId());
        listaActorAlerta = actorService.actorxAcuerdoDetalleBusqueda(alert.getActaAcuerdoDetalle().getId(), 1);
        menuController.cargarAlertasCabecera();
        setAlerta(alert);
    }
    
    public void proximosDias(){
        listaAlertaProgramadas = new ArrayList<>();
        for(ActaAcuerdoDetalle aad : acuerdoDetalles){
            verAlertasProgramadas(aad.getId());
        }
        Integer nro = diasAlertaProgramada;
        if(nro != 0){
            Date fin = sumarRestarDiasFecha(new Date(), nro);
            List<Alerta> as = new ArrayList<>();
            for(Alerta aler : listaAlertaProgramadas){
                if(aler.getFecha().before(fin)){
                    as.add(aler);
                }
            }
            listaAlertaProgramadas.clear();
            listaAlertaProgramadas.addAll(as);
        }
    }
    
    
    public void proximosDiasCaso(){
        listaAlertaProgramadas = new ArrayList<>();
        for(ActaAcuerdoDetalle aad : acuerdoDetalles){
            verAlertasProgramadas(aad.getId());
        }
        Integer nro = diasAlertaProgramada;
        if(nro != 0){
            Date fin = sumarRestarDiasFecha(new Date(), nro+1);
            List<Alerta> as = new ArrayList<>();
            for(Alerta aler : listaAlertaProgramadas){
                if(aler.getFecha().before(fin)){
                    as.add(aler);
                }
            }
            listaAlertaProgramadas.clear();
            listaAlertaProgramadas.addAll(as);
        }
    }
    
    public void ultimosDias(){
        Integer nro = diasAlertaEjecutadas;
        listaAlertaEjecutadas = new ArrayList<>();
        for(ActaAcuerdoDetalle aad : acuerdoDetalles){
            listaAlertaEjecutadas.addAll(alertaService.alertaBuscar(aad.getId()));
        }
        if(nro != 0){
            Date fin = sumarRestarDiasFecha(new Date(), -nro);
            List<Alerta> as = new ArrayList<>();
            for(Alerta aler : listaAlertaEjecutadas){
                if(aler.getFecha().after(fin)){
                    as.add(aler);
                }
            }
            listaAlertaEjecutadas.clear();
            listaAlertaEjecutadas.addAll(as);
        }
    }
    
    public void verAlertas(ActaAcuerdoDetalle acuerdoDetalle) {
        setActaAcuerdoDetalle(acuerdoDetalle);
            listaAlertaEjecutadas.addAll(alertaService.alertaBuscar(acuerdoDetalle.getId()));
            verAlertasProgramadas(acuerdoDetalle.getId());
    }
    
    public void verAlertasPendientes(ActaAcuerdoDetalle acuerdoDetalle) {
        setActaAcuerdoDetalle(acuerdoDetalle);
            listaAlertaEjecutadas.addAll(alertaService.alertaBuscarUsuario(acuerdoDetalle.getId()));
            verAlertasProgramadas(acuerdoDetalle.getId());
    }

    public void verAlertasProgramadas(Long idAcuerdo) {
        Caso cas = casoService.casoxActaAcuerdoDetalle(idAcuerdo);
        Date fechaFinAlerta = new Date();
        if(cas != null){
            SeguimientoAcuerdo seguimientoAcu = seguimientoAcuerdoService.seguimientoAcuerdoBuscarAcuerdo(idAcuerdo);
            if(seguimientoAcu != null){
                if(fechaFinAlerta != null){
                    long dias = diferenciaFechas(new Date(), fechaFinAlerta)+1L;
                    if(dias > 0){
                        for ( int i = 1; i <= dias; i ++ ) {
                            Date fec = sumarRestarDiasFecha(new Date(), i);
                            Alerta a = new Alerta();
                            a.setCaso(cas);
                            a.setDescripcion(null);
                            a.setFecha(fec);
                            listaAlertaProgramadas.add(a);
                        }
                    }
                }
            }
        }
    }
    
    
    private long diferenciaFechas(Date fechaIni,Date fechaFin){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fechaIni);
        cal2.setTime(fechaFin);
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();
        long diff = milis2 - milis1;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays;
    }

    public Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe	
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0	
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos	
    }

    public SeguimientoAcuerdo getSeguimientoAcuerdo() {
        return seguimientoAcuerdo;
    }

    public void setSeguimientoAcuerdo(SeguimientoAcuerdo seguimientoAcuerdo) {
        this.seguimientoAcuerdo = seguimientoAcuerdo;
    }

    public List<SelectItem> getListaTiempo() {
        listaTiempo = TiempoType.getList();
        return listaTiempo;
    }

    public void setListaTiempo(List<SelectItem> listaTiempo) {
        this.listaTiempo = listaTiempo;
    }

    public List<SelectItem> getListaAntesDespues() {
        listaAntesDespues = AntesDespuesType.getList();
        return listaAntesDespues;
    }

    public void setListaAntesDespues(List<SelectItem> listaAntesDespues) {
        this.listaAntesDespues = listaAntesDespues;
    }

    public List<SelectItem> getListaRepeticion() {
        listaRepeticion = RepeticionType.getList();
        return listaRepeticion;
    }

    public void setListaRepeticion(List<SelectItem> listaRepeticion) {
        this.listaRepeticion = listaRepeticion;
    }

    public List<ActaAcuerdoDetalle> getAcuerdoDetalles() {
        return acuerdoDetalles;
    }

    public void setAcuerdoDetalles(List<ActaAcuerdoDetalle> acuerdoDetalles) {
        this.acuerdoDetalles = acuerdoDetalles;
    }

    public List<Alerta> getListaAlertaEjecutadas() {
        return listaAlertaEjecutadas;
    }

    public void setListaAlertaEjecutadas(List<Alerta> listaAlertaEjecutadas) {
        this.listaAlertaEjecutadas = listaAlertaEjecutadas;
    }

    public List<Alerta> getListaAlertaProgramadas() {
        return listaAlertaProgramadas;
    }

    public void setListaAlertaProgramadas(List<Alerta> listaAlertaProgramadas) {
        this.listaAlertaProgramadas = listaAlertaProgramadas;
    }
    
    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public List<Actor> getListaActorAlerta() {
        return listaActorAlerta;
    }

    public void setListaActorAlerta(List<Actor> listaActorAlerta) {
        this.listaActorAlerta = listaActorAlerta;
    }

    public List<SelectItem> getListaProximosDias() {
        listaProximosDias = DiasProximosType.getList();        
        return listaProximosDias;
    }

    public void setListaProximosDias(List<SelectItem> listaProximosDias) {
        this.listaProximosDias = listaProximosDias;
    }

    public List<SelectItem> getListaUltimosDias() {
        listaUltimosDias = DiasUltimosType.getList();
        return listaUltimosDias;
    }

    public void setListaUltimosDias(List<SelectItem> listaUltimosDias) {
        this.listaUltimosDias = listaUltimosDias;
    }

    public ActaAcuerdoDetalle getActaAcuerdoDetalle() {
        return actaAcuerdoDetalle;
    }

    public void setActaAcuerdoDetalle(ActaAcuerdoDetalle actaAcuerdoDetalle) {
        this.actaAcuerdoDetalle = actaAcuerdoDetalle;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public Integer getDiasAlertaProgramada() {
        return diasAlertaProgramada;
    }

    public void setDiasAlertaProgramada(Integer diasAlertaProgramada) {
        this.diasAlertaProgramada = diasAlertaProgramada;
    }

    public Integer getDiasAlertaEjecutadas() {
        return diasAlertaEjecutadas;
    }

    public void setDiasAlertaEjecutadas(Integer diasAlertaEjecutadas) {
        this.diasAlertaEjecutadas = diasAlertaEjecutadas;
    }

    public Boolean getChecActivo() {
        return checActivo;
    }

    public void setChecActivo(Boolean checActivo) {
        this.checActivo = checActivo;
    }
    
    

}
