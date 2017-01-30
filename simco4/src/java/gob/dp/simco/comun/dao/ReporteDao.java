/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.dao;

import gob.dp.simco.comun.entity.Resumen;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ReporteDao {
    
    public void cargaCasoMes(long idCodigoCarga);
    
    public List<Resumen> graficoEstadoAnho(List<Integer> anhos);
    
    public int grafico001Activo(Resumen resumen);
    
    public List<Resumen> grafico001Meses(String anho);
    
    public int grafico001MesesCount(Resumen resumen);
    
    public List<Resumen> grafico001Departamento(String anho);
    
    public int grafico001DepartamentoCount(Resumen resumen);
    
    public int grafico004TipoCaso(Resumen resumen);
    
    public int grafico005DepartamentoCount(Resumen resumen);
    
    public int grafico006MesesCount(Resumen resumen);
    
    public List<Resumen> listadoAnhoCaso();
    
}
