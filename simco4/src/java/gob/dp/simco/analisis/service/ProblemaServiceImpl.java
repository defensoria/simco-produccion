/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.service;

import gob.dp.simco.analisis.dao.ProblemaDAO;
import gob.dp.simco.analisis.entity.Problema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ProblemaServiceImpl implements ProblemaService{
    
    @Autowired
    private ProblemaDAO problemaDAO;

    @Override
    public void problemaInsertar(Problema problema) {
        problemaDAO.problemaInsertar(problema);
    }

    @Override
    public void problemaUpdate(Problema problema) {
        problemaDAO.problemaUpdate(problema);
    }

    @Override
    public Problema problemaBuscar(long id) {
        return problemaDAO.problemaBuscar(id);
    }
}
