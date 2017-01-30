/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.dao;

import gob.dp.simco.investigacion.entity.Participacion;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ParticipacionDAO {
    
    public void participacionInsertar(Participacion participacion);
    
    public void participacionUpdate(Participacion participacion);
    
    public List<Participacion> participacionBuscar(Long idInvestigacion);
}