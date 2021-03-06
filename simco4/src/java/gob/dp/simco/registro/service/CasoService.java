/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.bean.FiltroCaso;
import gob.dp.simco.registro.entity.Caso;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface CasoService {
    
   public void casoNuevo(Caso caso);
   
   public void casoModificar(Caso caso);
   
   public List<Caso> casoBuscar(Caso caso);
   
   public Integer casoTotalBuscar(FiltroCaso filtroCaso);
   
   public List<Caso> casoxActividadBuscar(long idActividad);
   
   public List<Caso> casoxActividadBuscarTotal(long idActividad);
   
   public Caso casoBuscarOne(Long idCaso);
   
   public String casoBuscarAutocomplete(Caso cas);
    
   public Integer casoCodigoGenerado();
   
   public String adjuntiaBuscarAutocomplete();
   
   public Caso casoxActaAcuerdoDetalle(long idActaAcuerdoDetalle);
   
   public void casoUpdateIndicador(Caso caso);
   
   public void casoUpdateSistesis(Caso caso);
   
   public List<Caso> buscarCasoXnombreCodigo(FiltroCaso filtroCaso);
   
   public void casoUpdateAprobar(Caso caso);
   
   public Caso casoxVersion(Caso caso);
   
}
