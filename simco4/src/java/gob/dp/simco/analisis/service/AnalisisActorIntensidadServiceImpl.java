/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.service;

import gob.dp.simco.analisis.dao.AnalisisActorIntensidadDao;
import gob.dp.simco.analisis.entity.AnalisisActorIntensidad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class AnalisisActorIntensidadServiceImpl implements AnalisisActorIntensidadService{
    
    @Autowired
    private AnalisisActorIntensidadDao analisisActorIntensidadDao;

    @Override
    public void analisisActorIntensidadInsertar(AnalisisActorIntensidad analisisActorIntensidad) {
        analisisActorIntensidadDao.analisisActorIntensidadInsertar(analisisActorIntensidad);
    }

    @Override
    public List<AnalisisActorIntensidad> analisisActorIntensidadBuscar(Long idCaso) {
        return analisisActorIntensidadDao.analisisActorIntensidadBuscar(idCaso);
    }

    @Override
    public void analisisActorIntensidadUpdate(AnalisisActorIntensidad analisisActorIntensidad) {
        analisisActorIntensidadDao.analisisActorIntensidadUpdate(analisisActorIntensidad);
    }

    @Override
    public Integer analisisActorIntensidadBuscarUno(AnalisisActorIntensidad analisisActorIntensidad) {
        return analisisActorIntensidadDao.analisisActorIntensidadBuscarUno(analisisActorIntensidad);
    }
    
}
