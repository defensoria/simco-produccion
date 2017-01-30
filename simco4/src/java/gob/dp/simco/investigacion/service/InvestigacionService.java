/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.service;

import gob.dp.simco.investigacion.entity.Investigacion;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface InvestigacionService {
    
    public void investigacionInsertar(Investigacion investigacion);
    
    public void investigacionUpdate(Investigacion investigacion);
    
    public List<Investigacion> investigacionBuscar(Investigacion investigacion);
    
}
