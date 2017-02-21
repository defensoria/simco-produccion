/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.bean.FiltroCaso;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.reporte.entity.FiltroReporte;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface CasoDao {
    
   public void casoInsertar(Caso caso);
   
   public void casoUpdate(Caso caso);
   
   public List<Caso> casoBuscar(Caso caso);
   
   public Integer casoTotalBuscar(FiltroCaso filtroCaso);
    
   public List<Caso> casoxActividadBuscar(long idActividad);
   
   public List<Caso> casoxActividadBuscarTotal(long idActividad);
   
   public Caso casoBuscarOne(long idCaso);
   
   public Integer casoCodigoGenerado();
   
   public Caso casoxActaAcuerdoDetalle(long idActaAcuerdoDetalle);
   
   public void casoUpdateIndicador(Caso caso);
   
   public List<Caso> reporteCaso(FiltroReporte filtroReporte);
   
   public void casoUpdateSistesis(Caso caso);
   
   public List<Caso> buscarCasoXnombreCodigo(FiltroCaso filtroCaso);
   
   public void casoUpdateAprobar(Caso caso);
   
   public List<Caso> listadoCasosEstadoMes(FiltroReporte filtroReporte);
   
   public List<Caso> listaCasosAntesDeAprobado(FiltroReporte filtroReporte);
   
   
   
}
