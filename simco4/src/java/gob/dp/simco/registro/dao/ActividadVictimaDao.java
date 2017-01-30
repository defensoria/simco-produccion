/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.ActividadVictima;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ActividadVictimaDao {
    
    public void actividadVictimaInsertar(ActividadVictima victimas);
            
    public void actividadVictimaUpdate(ActividadVictima victimas);
            
    public List<ActividadVictima> actividadVictimaBuscar(long idActividad);
    
    public void actividadVictimaEliminar(long idActividad);
    
}
