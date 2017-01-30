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
public class FiltroReporte {
    
    private int tipoReporte;

    private String titulo;
    
    private String departamento;
    
    private List<Integer> listaDepartamentos;
    
    private Integer listaDepartamentosSize;
    
    private String clasificacion;
    
    private String fila;
    
    private String columna;
    
    private String anhos;
    
    private List<String> listaAnhos;
    
    private Integer listaAnhosSize;
    
    private String mes;
    
    private List<String> listaMeses;
    
    private Integer listaMesesSize;
    
    private String codigoCaso;
    
    private String tipoEstadoCaso;
    
    public int getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(int tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Integer> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<Integer> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public Integer getListaDepartamentosSize() {
        return listaDepartamentosSize;
    }

    public void setListaDepartamentosSize(Integer listaDepartamentosSize) {
        this.listaDepartamentosSize = listaDepartamentosSize;
    }

    public String getAnhos() {
        return anhos;
    }

    public void setAnhos(String anhos) {
        this.anhos = anhos;
    }

    public List<String> getListaAnhos() {
        return listaAnhos;
    }

    public void setListaAnhos(List<String> listaAnhos) {
        this.listaAnhos = listaAnhos;
    }

    public Integer getListaAnhosSize() {
        return listaAnhosSize;
    }

    public void setListaAnhosSize(Integer listaAnhosSize) {
        this.listaAnhosSize = listaAnhosSize;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public List<String> getListaMeses() {
        return listaMeses;
    }

    public void setListaMeses(List<String> listaMeses) {
        this.listaMeses = listaMeses;
    }

    public Integer getListaMesesSize() {
        return listaMesesSize;
    }

    public void setListaMesesSize(Integer listaMesesSize) {
        this.listaMesesSize = listaMesesSize;
    }

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public String getTipoEstadoCaso() {
        return tipoEstadoCaso;
    }

    public void setTipoEstadoCaso(String tipoEstadoCaso) {
        this.tipoEstadoCaso = tipoEstadoCaso;
    }
    
}
