/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.controller;

import gob.dp.simco.analisis.entity.AnalisisActorTema;
import gob.dp.simco.analisis.service.AnalisisActorTemaService;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class CanvasController extends AbstractManagedBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private static final Logger log = Logger.getLogger(AnalisisController.class);
    
    List<AnalisisActorTema> listaActoresXCasoGrafico;
    
    private Long idTemaGrafico = 0L;
    
    @Autowired
    private AnalisisActorTemaService analisisActorTemaService;

    public void cambiarCanvas(){
        listaActoresXCasoGrafico = new ArrayList<>();
        List<AnalisisActorTema> list = analisisActorTemaService.analisisActorTemaXactorBuscar(idTemaGrafico);
        if(list.size() > 0){
            listaActoresXCasoGrafico.addAll(list);
        }else{
            listaActoresXCasoGrafico.clear();
        }  
    }
    
    public void limpiarCanvasProblemas(){
        listaActoresXCasoGrafico = new ArrayList<>();
    }
    
    public void saveGraficTema(){
        if(listaActoresXCasoGrafico != null){
            for(AnalisisActorTema analisisActorTema : listaActoresXCasoGrafico){
                String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nivel"+analisisActorTema.getActor().getId());
                if(StringUtils.isNotBlank(value)){
                    Integer nivel = Integer.parseInt(value);
                    analisisActorTema.setNivel(nivel);
                    analisisActorTema.setFechaRegistro(new Date());
                    analisisActorTemaService.analisisActorTemaUpdate(analisisActorTema);
                    msg.messageInfo("Se ha actualizado el grafico de Temas y Posiciones", null);
                }
            }
            if(listaActoresXCasoGrafico.isEmpty())
                msg.messageAlert("No existen actores", null);
        }else{
            msg.messageAlert("No existen actores", null);
        } 
    }

    public List<AnalisisActorTema> getListaActoresXCasoGrafico() {
        return listaActoresXCasoGrafico;
    }

    public void setListaActoresXCasoGrafico(List<AnalisisActorTema> listaActoresXCasoGrafico) {
        this.listaActoresXCasoGrafico = listaActoresXCasoGrafico;
    }

    public Long getIdTemaGrafico() {
        return idTemaGrafico;
    }

    public void setIdTemaGrafico(Long idTemaGrafico) {
        this.idTemaGrafico = idTemaGrafico;
    }
}
