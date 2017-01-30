/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.service;

import gob.dp.simco.intervencion.dao.IntervencionEtapaDao;
import gob.dp.simco.intervencion.entity.IntervencionEtapa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class IntervencionEtapaServiceImpl implements IntervencionEtapaService{
    
    @Autowired
    private IntervencionEtapaDao intervencionEtapaDao;

    @Override
    public void intervencionEtapaInsertar(IntervencionEtapa intervencionEtapa) {
        intervencionEtapaDao.intervencionEtapaInsertar(intervencionEtapa);
    }

    @Override
    public IntervencionEtapa intervencionEtapaBuscar(Long id) {
        return intervencionEtapaDao.intervencionEtapaBuscar(id);
    }

    @Override
    public List<IntervencionEtapa> intervencionEtapaBuscarTipo(IntervencionEtapa intervencionEtapa) {
        return intervencionEtapaDao.intervencionEtapaBuscarTipo(intervencionEtapa);
    }

    @Override
    public void intervencionEtapaUpdate(IntervencionEtapa intervencionEtapa) {
        intervencionEtapaDao.intervencionEtapaUpdate(intervencionEtapa);
    }

    @Override
    public void intervencionEtapaUpdateDetalle(IntervencionEtapa intervencionEtapa) {
        intervencionEtapaDao.intervencionEtapaUpdateDetalle(intervencionEtapa);
    }

    @Override
    public void intervencionEtapaDetalleDelete(long idIntervencionEtapa) {
        intervencionEtapaDao.intervencionEtapaDetalleDelete(idIntervencionEtapa);
    }

    @Override
    public List<IntervencionEtapa> intervencionEtapaxAccion(long idEtapaAccion) {
        return intervencionEtapaDao.intervencionEtapaxAccion(idEtapaAccion);
    }

    @Override
    public List<IntervencionEtapa> intervencionEtapaxIntervencion(long idIntervencion) {
        return intervencionEtapaDao.intervencionEtapaxIntervencion(idIntervencion);
    }

   
}
