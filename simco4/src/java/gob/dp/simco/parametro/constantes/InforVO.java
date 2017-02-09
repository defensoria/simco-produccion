/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.parametro.constantes;

import gob.dp.simco.parametro.entity.Catalogo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author carlos
 */
public class InforVO implements Serializable{
    
    private List<Catalogo> listaEstadoCaso;
    
    private List<Catalogo> listaFaseCaso;
    
    private List<Catalogo> listaTipoCaso;
    
    private List<Catalogo> listaCompetenciaCaso;

    public List<Catalogo> getListaEstadoCaso() {
        return listaEstadoCaso;
    }

    public void setListaEstadoCaso(List<Catalogo> listaEstadoCaso) {
        this.listaEstadoCaso = listaEstadoCaso;
    }

    public List<Catalogo> getListaFaseCaso() {
        return listaFaseCaso;
    }

    public void setListaFaseCaso(List<Catalogo> listaFaseCaso) {
        this.listaFaseCaso = listaFaseCaso;
    }

    public List<Catalogo> getListaTipoCaso() {
        return listaTipoCaso;
    }

    public void setListaTipoCaso(List<Catalogo> listaTipoCaso) {
        this.listaTipoCaso = listaTipoCaso;
    }

    public List<Catalogo> getListaCompetenciaCaso() {
        return listaCompetenciaCaso;
    }

    public void setListaCompetenciaCaso(List<Catalogo> listaCompetenciaCaso) {
        this.listaCompetenciaCaso = listaCompetenciaCaso;
    }
    
}
