/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.CasoRegion;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface CasoRegionDao {
    
    public List<CasoRegion> casoRegionBuscar(Long idCaso);
    
    public void  casoRegionInsertar(CasoRegion casoRegion);
            
    public void  casoRegionUpdate(Long id);
    
    public void  casoRegionUpdatePrincipal(Long id);
}
