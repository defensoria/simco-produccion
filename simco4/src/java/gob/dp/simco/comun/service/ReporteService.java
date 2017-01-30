/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.entity.Resumen;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ReporteService {
    
    public void cargaCasoMes(long idCodigoCarga);
    
    public List<Resumen> graficoEstadoAnho(List<Integer> anhos);
    
    public Integer grafico001Activo(Resumen resumen);
    
    public List<Resumen> grafico001Meses(String anho);
    
    public Integer grafico001MesesCount(Resumen resumen);
    
    public List<Resumen> grafico001Departamento(String anho);
    
    public Integer grafico001DepartamentoCount(Resumen resumen);
    
    public Integer grafico004TipoCaso(Resumen resumen);
    
    public Integer grafico005DepartamentoCount(Resumen resumen);
    
    public Integer grafico006MesesCount(Resumen resumen);
    
    public List<Resumen> listadoAnhoCaso();
}
