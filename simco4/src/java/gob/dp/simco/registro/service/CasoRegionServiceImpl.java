/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.CasoRegionDao;
import gob.dp.simco.registro.entity.CasoRegion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class CasoRegionServiceImpl implements CasoRegionService{
    
    @Autowired
    private CasoRegionDao casoRegionDao;

    @Override
    public List<CasoRegion> casoRegionBuscar(Long idCaso) {
        return casoRegionDao.casoRegionBuscar(idCaso);
    }

    @Override
    public void casoRegionInsertar(CasoRegion casoRegion) {
        casoRegionDao.casoRegionInsertar(casoRegion);
    }

    @Override
    public void casoRegionUpdate(Long id) {
        casoRegionDao.casoRegionUpdate(id);
    }

    @Override
    public void casoRegionUpdatePrincipal(Long id) {
        casoRegionDao.casoRegionUpdatePrincipal(id);
    }
    
}
