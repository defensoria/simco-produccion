/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.service;

import gob.dp.simco.analisis.dao.ContextoDao;
import gob.dp.simco.analisis.entity.Contexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ContextoServiceImpl implements ContextoService{
    
    @Autowired
    private ContextoDao contextoDao;

    @Override
    public void contextoInsertar(Contexto contexto) {
        contextoDao.contextoInsertar(contexto);
    }

    @Override
    public void contextoUpdate(Contexto contexto) {
        contextoDao.contextoUpdate(contexto);
    }

    @Override
    public Contexto contextoBuscar(Contexto contexto) {
        return contextoDao.contextoBuscar(contexto);
    }
    
}
