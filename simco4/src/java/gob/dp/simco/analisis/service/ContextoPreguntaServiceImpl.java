/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.service;

import gob.dp.simco.analisis.dao.ContextoPreguntaDao;
import gob.dp.simco.analisis.entity.ContextoPregunta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ContextoPreguntaServiceImpl implements ContextoPreguntaService{
    
    @Autowired
    private ContextoPreguntaDao contextoPreguntaDao;

    @Override
    public List<ContextoPregunta> contextoPreguntaBuscar(Long idTipo) {
        return contextoPreguntaDao.contextoPreguntaBuscar(idTipo);
    }
    
}
