/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.controller;

import gob.dp.simco.administracion.seguridad.controller.LoginController;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import gob.dp.simco.analisis.entity.AnalisisActor;
import gob.dp.simco.analisis.entity.AnalisisRelacion;
import gob.dp.simco.analisis.entity.Tema;
import gob.dp.simco.analisis.service.AnalisisActorService;
import gob.dp.simco.analisis.service.AnalisisRelacionService;
import gob.dp.simco.analisis.service.TemaService;
import gob.dp.simco.analisis.type.ColorType;
import gob.dp.simco.analisis.type.ColorVinculoType;
import gob.dp.simco.analisis.type.TipoRelacionType;
import gob.dp.simco.registro.controller.CasoController;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.service.ActorService;
import gob.dp.simco.registro.service.CasoService;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class GraficController implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private static final Logger log = Logger.getLogger(AnalisisController.class);
    
    private String edges;
    
    private String nodes;
    
    private String cadenaAutocomplete;
    
    private Caso caso;
    
    private Usuario usuarioSession;
    
    private Boolean verModalActor = false;
    
    @Autowired
    private ActorService actorService;
    
    @Autowired
    private AnalisisRelacionService analisisRelacionService;
    
    @Autowired
    private AnalisisActorService analisisActorService;
    
    @Autowired
    private TemaService temaService;
    
    @Autowired
    private CasoService casoService;
    
    public String cargarPaginaRelacion(){
        caso = new Caso();
        generarCadenaCasos();
        edges="";
        nodes="";
        return "relationActor";
    }
    
    public String cargarPaginaRelacionCaso(Caso c){
        caso = new Caso();
        edges="";
        nodes="";
        generarGraficosActores(c);
        return "relationActor";
    }
    
    public void cargarPaginaRelacionCasoI(){
        FacesContext context = FacesContext.getCurrentInstance();
        CasoController casoController = (CasoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "casoController");
        verModalActor = true;
        edges="";
        nodes="";
        generarGraficosActores(casoController.getCaso());
    }
    
    public String cargarPaginaRelacionCaso2(Caso c){
        caso = new Caso();
        edges="";
        nodes="";
        generarGraficosActores(c);
        return null;
    }
    
    public String cargarPaginasRelacionTemas(){
        caso = new Caso();
        edges="";
        nodes="";
        generarCadenaCasos();
        return "relationTema";
    }
    
    public String cargarPaginasRelacionTemasCaso(Caso c){
        caso = new Caso();
        edges="";
        nodes="";
        generarGraficosTemas(c);
        return "relationTema";
    }
    
    public void generarGraficosTemas(Caso c){
        try {
        edges="";
        nodes="";
        List<Actor> listaActoresXCaso = actorService.actorxCasoBuscar(c.getId());
        List<Tema> listaTemasXCaso = temaService.temaBuscar(c.getId());
        
        StringBuilder js = new StringBuilder();
        
        js.append("var nodes = [");
        int i = 0;
        for(Actor actor : listaActoresXCaso){
            if(actor.getApellidoPat() == null)
                actor.setApellidoPat("");
            if(actor.getApellidoMat() == null)
                actor.setApellidoMat("");
            i++;
           // js.append("{id: "+actor.getId()+1000+", label: '"+actor.getNombre()+"', color: '"+ColorType.getValues(i+1)+"'},");
             js.append("{id: "+actor.getId()+1000+", label: '"+actor.getNombre()+" "+actor.getApellidoPat()+" "+actor.getApellidoMat()+"',image: 'http://simco2.no-ip.biz/simco/faces/vis/images/' + 'actor.png', shape: 'image' },");
            
        }
        
        int j = i;
        int f = 0;
        for(Tema tema : listaTemasXCaso){
            f++;
            if(j > i){
                js.append(",");
            }
            //js.append("{id: "+tema.getId()+3000+", label: '"+tema.getDetalle()+"', color: '"+ColorType.getValues(f)+"', shape: 'box'}");
            js.append("{id: "+tema.getId()+3000+", label: '"+tema.getDetalle()+"', color: '"+ColorType.getValues(7)+"', shape: 'box'}");
            j++;
        }
         js.append("];");
         
         nodes = js.toString();
         
        List<AnalisisActor> analisisActors = analisisActorService.analisisActorxcasoBuscar(c.getId()); 
        StringBuilder es = new StringBuilder();
        es.append("var edges = [");
        
        int k = 0;
        for(AnalisisActor analisisActor : analisisActors){
            for(Tema tema: analisisActor.getTemas()){
                if(k > 0){
                es.append(",");
                }
                es.append("{from: "+analisisActor.getActor().getId()+1000+", to: "+tema.getId()+3000+", width:3, style: 'line', color: '"+ColorType.getValues(9)+"', length: 250}");
                k++;
            }
        }
        es.append("];");
        edges = es.toString();
        } catch (Exception e) {
            log.error(e.getCause());
        }
    }
    
    public void generarGraficosActores(Caso c){
        try {
            setCaso(c);
        edges="";
        nodes="";
        List<Actor> listaActoresXCaso = actorService.actorxCasoBuscar(caso.getId());
        
        StringBuilder js = new StringBuilder();
        
        js.append("var nodes = [");
        int i = 0;
        for(Actor actor : listaActoresXCaso){
            if(actor.getApellidoPat() == null)
                actor.setApellidoPat("");
            if(actor.getApellidoMat() == null)
                actor.setApellidoMat("");
            if(i > 0){
                js.append(",");
            }
            //js.append("{id: "+actor.getId()+", label: '"+actor.getNombre()+"', color: '"+ColorType.getValues(7)+"'}");
            js.append("{id: "+actor.getId()+", label: '"+actor.getNombre()+" "+actor.getApellidoPat()+" "+actor.getApellidoMat()+"',image: 'http://simco2.no-ip.biz/simco/faces/vis/images/' + 'actor.png', shape: 'image' }");
            i++;
        }
         js.append("];");
         
         nodes = js.toString();
        
        List<AnalisisRelacion> analisisRelacions = analisisRelacionService.analisisRelacionBuscar(caso.getId());
        
        StringBuilder es = new StringBuilder();
        es.append("var edges = [");
        
        int j = 0;
        for(AnalisisRelacion analisisRelacion : analisisRelacions){
            if(j > 0){
                es.append(",");
            }
            es.append("{from: "+analisisRelacion.getActor1().getId()+", to: "+analisisRelacion.getActor2().getId()+", width:3, style: 'line', color: '"+ColorVinculoType.getValues(TipoRelacionType.getKeys(analisisRelacion.getTipo()))+"', length: 250}");
            //es.append("{from: "+analisisRelacion.getActor1().getId()+", to: "+analisisRelacion.getActor2().getId()+", width:3, style: 'line', color: '"+ColorType.getValues(9)+"', length: 250}");
            j++;
        }
        es.append("];");
        edges = es.toString();
        } catch (Exception e) {
            log.error(e.getCause());
        }
    }
    
    private void usuarioSession() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
            usuarioSession = loginController.getUsuarioSesion();
        } catch (Exception e) {
            log.error("ERROR - usuarioSession()" + e);
        }
    }
    
    private void generarCadenaCasos() {
        try {
            usuarioSession();
            Caso cas = new Caso();
            cas.setUsuarioRegistro(usuarioSession.getCodigo());
            cadenaAutocomplete = casoService.casoBuscarAutocomplete(cas);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public String getEdges() {
        return edges;
    }

    public void setEdges(String edges) {
        this.edges = edges;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getCadenaAutocomplete() {
        return cadenaAutocomplete;
    }

    public void setCadenaAutocomplete(String cadenaAutocomplete) {
        this.cadenaAutocomplete = cadenaAutocomplete;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public Boolean getVerModalActor() {
        return verModalActor;
    }

    public void setVerModalActor(Boolean verModalActor) {
        this.verModalActor = verModalActor;
    }
    
    
    
}
