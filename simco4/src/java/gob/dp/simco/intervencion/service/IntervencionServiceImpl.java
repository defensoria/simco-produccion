/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.service;

import gob.dp.simco.intervencion.dao.IntervencionDao;
import gob.dp.simco.intervencion.entity.Intervencion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class IntervencionServiceImpl implements IntervencionService{
    
    @Autowired
    private IntervencionDao intervencionDao;

    @Override
    public void intervencionInsertar(Intervencion intervencion) {
        intervencionDao.intervencionInsertar(intervencion);
    }

    @Override
    public List<Intervencion> intervencionBuscar(String codigoUsuario) {
        return intervencionDao.intervencionBuscar(codigoUsuario);
    }

    @Override
    public void intervencionUpdate(Intervencion intervencion) {
        intervencionDao.intervencionUpdate(intervencion);
    }

    @Override
    public List<Intervencion> intervencionBuscarPriorizados() {
        return intervencionDao.intervencionBuscarPriorizados();
    }

    @Override
    public List<Intervencion> intervencionBuscarArchivados() {
        return intervencionDao.intervencionBuscarArchivados();
    }

    @Override
    public List<Intervencion> intervencionBuscarActivas() {
        return intervencionDao.intervencionBuscarActivas();
    }

    @Override
    public Intervencion intervencionBuscarCaso(String codigo) {
        return intervencionDao.intervencionBuscarCaso(codigo);
    }

    @Override
    public Integer intervencionBuscarCasoCount(String codigo) {
        return intervencionDao.intervencionBuscarCasoCount(codigo);
    }
    
}
