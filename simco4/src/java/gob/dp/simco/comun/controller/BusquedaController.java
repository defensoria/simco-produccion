/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.controller;

import gob.dp.simco.comun.entity.Busqueda;
import gob.dp.simco.comun.service.BusquedaService;
import gob.dp.simco.registro.controller.ActorController;
import gob.dp.simco.registro.controller.CasoController;
import gob.dp.simco.registro.controller.RegistroController;
import gob.dp.simco.registro.entity.Actividad;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class BusquedaController implements Serializable{
    
    private String cadenaAutocomplete;
    
    private List<Busqueda> listadoGeneral;
    
    private Busqueda busqueda;
    
    @Autowired
    private BusquedaService busquedaService;

    public void inicioBusqueda(){
        busqueda = new Busqueda();
    }

    public String buscar(){
        listadoGeneral = busquedaService.busquedaListaxPalabra(busqueda);
        return "busquedaGeneral";
    }
    
    public String enlazar(Busqueda b){
        FacesContext context = FacesContext.getCurrentInstance();
        ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
        CasoController  casoController= (CasoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "casoController");
        RegistroController  registroController= (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        if(b.getTipo() == 3){
            actorController.busquedaGeneralActor(b.getCodigo());
            return "actorEdit";
        }
        
        if(b.getTipo() == 2){
            return casoController.cargarPanel(b.getCodigo());
        }
        
        if(b.getTipo() == 1){
            Actividad a = registroController.cargarActividadId(b.getCodigo());
            if(a.getIdCaso() == null){
                return registroController.irModificarRegistro2(a);
            }else{
                return registroController.irModificarRegistro(a);
            }
        }
        return null;
    }
    
    public String getCadenaAutocomplete() {
        return cadenaAutocomplete;
    }

    public void setCadenaAutocomplete(String cadenaAutocomplete) {
        this.cadenaAutocomplete = cadenaAutocomplete;
    }

    public List<Busqueda> getListadoGeneral() {
        return listadoGeneral;
    }

    public void setListadoGeneral(List<Busqueda> listadoGeneral) {
        this.listadoGeneral = listadoGeneral;
    }

    public Busqueda getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Busqueda busqueda) {
        this.busqueda = busqueda;
    }

}
