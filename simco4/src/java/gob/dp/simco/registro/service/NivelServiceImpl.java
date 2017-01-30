/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.PrimerNivelDao;
import gob.dp.simco.registro.dao.SegundoNivelDao;
import gob.dp.simco.registro.dao.TercerNivelDao;
import gob.dp.simco.registro.entity.PrimerNivel;
import gob.dp.simco.registro.entity.SegundoNivel;
import gob.dp.simco.registro.entity.TercerNivel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class NivelServiceImpl implements NivelService{
    
    @Autowired
    private PrimerNivelDao primerNivelDao;
    
    @Autowired
    private SegundoNivelDao segundoNivelDao;
    
    @Autowired
    private TercerNivelDao tercerNivelDao;

    @Override
    public List<PrimerNivel> listarPrimerNivel(String tipo) {
        return primerNivelDao.listarPrimerNivel(tipo);
    }

    @Override
    public List<SegundoNivel> segundoNivelBuscar(String tipo) {
        return segundoNivelDao.segundoNivelBuscar(tipo);
    }

    @Override
    public List<TercerNivel> tercerNivelBuscar(String tipo) {
        return tercerNivelDao.tercerNivelBuscar(tipo);
    }

    @Override
    public String primerNivelOne(String tipo) {
        if(tipo != null){
            PrimerNivel nivel = primerNivelDao.primerNivelOne(tipo);
        if(nivel != null)
            return nivel.getNombre();
        }
        return "";
    }

    @Override
    public String segundoNivelOne(String tipo) {
        if(tipo != null){
        SegundoNivel nivel = segundoNivelDao.segundoNivelOne(tipo);
        if(nivel != null)
            return nivel.getNombre();
        }
        return "";
    }

    @Override
    public String tercerNivelOne(String tipo) {
        if(tipo != null){
        TercerNivel nivel = tercerNivelDao.tercerNivelOne(tipo);
        if(nivel != null)
            return nivel.getNombre();
        }
        return "";
    }
    
}
