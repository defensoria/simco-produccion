/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.service;

import gob.dp.simco.investigacion.dao.ParticipacionDAO;
import gob.dp.simco.investigacion.entity.Participacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ParticipacionServiceImpl implements ParticipacionService{
    
    @Autowired
    private ParticipacionDAO participacionDAO;

    @Override
    public void participacionInsertar(Participacion participacion) {
        participacionDAO.participacionInsertar(participacion);
    }

    @Override
    public void participacionUpdate(Participacion participacion) {
        participacionDAO.participacionUpdate(participacion);
    }

    @Override
    public List<Participacion> participacionBuscar(Long idInvestigacion) {
        return participacionDAO.participacionBuscar(idInvestigacion);
    }
    
}
