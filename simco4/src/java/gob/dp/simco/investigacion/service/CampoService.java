/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.service;

import gob.dp.simco.investigacion.entity.Campo;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface CampoService {
    
    public void campoInsertar(Campo campo);
    
    public void campoUpdate(Campo campo);
    
    public List<Campo> campoxInvestigacionBuscar(Long idInvestigacion);
    
}
