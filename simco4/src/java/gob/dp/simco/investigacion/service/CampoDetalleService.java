/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.service;

import gob.dp.simco.investigacion.entity.CampoDetalle;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface CampoDetalleService {
    
    public void campoDetalleInsertar(CampoDetalle campoDetalle);
    
    public void campoDetalleUpdate(CampoDetalle campoDetalle);
    
    public List<CampoDetalle> campoDetalleBuscar(Long idCampo);
}
