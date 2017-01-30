/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.CasoActorDAO;
import gob.dp.simco.registro.entity.CasoActor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class CasoActorServiceImpl implements CasoActorService{
    
    @Autowired
    private CasoActorDAO casoActorDAO;

    @Override
    public List<CasoActor> casoActorBuscar(long idCaso) {
        return casoActorDAO.casoActorBuscar(idCaso);
    }

    @Override
    public void casoActorInsertar(CasoActor casoActor) {
        casoActorDAO.casoActorInsertar(casoActor);
    }

    @Override
    public void casoUpdate(CasoActor casoActor) {
        casoActorDAO.casoUpdate(casoActor);
    }

    @Override
    public Integer casoActorBuscarCount(CasoActor casoActor) {
        return casoActorDAO.casoActorBuscarCount(casoActor);
    }

    @Override
    public List<CasoActor> casoActorBuscarComplete(long idCaso) {
        return casoActorDAO.casoActorBuscarComplete(idCaso);
    }
    
}
