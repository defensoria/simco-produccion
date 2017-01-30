/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.service;

import gob.dp.simco.investigacion.dao.InvestigacionDAO;
import gob.dp.simco.investigacion.entity.Investigacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class InvestigacionServiceImpl implements InvestigacionService{

    @Autowired
    private InvestigacionDAO investigacionDAO;
    
    @Override
    public void investigacionInsertar(Investigacion investigacion) {
        investigacionDAO.investigacionInsertar(investigacion);
    }

    @Override
    public void investigacionUpdate(Investigacion investigacion) {
        investigacionDAO.investigacionUpdate(investigacion);
    }

    @Override
    public List<Investigacion> investigacionBuscar(Investigacion investigacion) {
        return investigacionDAO.investigacionBuscar(investigacion);
    }
    
}
