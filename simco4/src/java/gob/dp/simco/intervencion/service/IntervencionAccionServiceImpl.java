/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.service;

import gob.dp.simco.intervencion.dao.IntervencionAccionDao;
import gob.dp.simco.intervencion.entity.IntervencionAccion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class IntervencionAccionServiceImpl implements IntervencionAccionService{
    
    @Autowired
    private IntervencionAccionDao intervencionAccionDao;

    @Override
    public void intervencionAccionInsertar(IntervencionAccion intervencionAccion) {
        intervencionAccionDao.intervencionAccionInsertar(intervencionAccion);
    }

    @Override
    public IntervencionAccion intervencionAccionBuscar(Long idIntervencionAccion) {
        return intervencionAccionDao.intervencionAccionBuscar(idIntervencionAccion);
    }

    @Override
    public List<IntervencionAccion> intervencionAccionBuscarxCaso(Long idIntervencion) {
        return intervencionAccionDao.intervencionAccionBuscarxCaso(idIntervencion);
    }

    @Override
    public List<IntervencionAccion> intervencionAccionBuscarxIntervencion(Long idIntervencion) {
        return intervencionAccionDao.intervencionAccionBuscarxIntervencion(idIntervencion);
    }
    
}
