/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.service;

import gob.dp.simco.investigacion.dao.CampoDAO;
import gob.dp.simco.investigacion.entity.Campo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class CampoServiceImpl implements CampoService{
    
    @Autowired
    private CampoDAO campoDAO;

    @Override
    public void campoInsertar(Campo campo) {
        campoDAO.campoInsertar(campo);
    }

    @Override
    public void campoUpdate(Campo campo) {
        campoDAO.campoUpdate(campo);
    }

    @Override
    public List<Campo> campoxInvestigacionBuscar(Long idInvestigacion) {
        return campoDAO.campoxInvestigacionBuscar(idInvestigacion);
    }
    
}
