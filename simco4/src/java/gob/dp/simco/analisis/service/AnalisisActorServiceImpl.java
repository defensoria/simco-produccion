/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.service;

import gob.dp.simco.analisis.dao.AnalisisActorDao;
import gob.dp.simco.analisis.dao.TemaDao;
import gob.dp.simco.analisis.entity.AnalisisActor;
import gob.dp.simco.analisis.entity.Tema;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class AnalisisActorServiceImpl implements AnalisisActorService{
    
    @Autowired
    private AnalisisActorDao analisisActorDao;
    
    @Autowired
    private TemaDao temaDao;

    @Override
    public void analisisActorInsertar(AnalisisActor analisisActor) {
        analisisActorDao.analisisActorInsertar(analisisActor);
    }

    @Override
    public List<AnalisisActor> analisisActorxcasoBuscar(long idCaso) {
        List<AnalisisActor> analisisActores = new ArrayList<>();
        List<AnalisisActor> analisisActors = analisisActorDao.analisisActorxcasoBuscar(idCaso);
        for(AnalisisActor aa:analisisActors){
            Tema t = new Tema();
            Caso c = new Caso();
            Actor a = new Actor();
            c.setId(aa.getCaso().getId());
            a.setId(aa.getActor().getId());
            t.setCaso(c);
            t.setActor(a);
            aa.setTemas(temaDao.temaxcasoxactorBuscar(t));
            analisisActores.add(aa);
        }
        return analisisActores;
    }
    
    @Override
    public List<AnalisisActor> analisisActorxcasoBuscarxActor(AnalisisActor analisisActor) {
        List<AnalisisActor> analisisActores = new ArrayList<>();
        List<AnalisisActor> analisisActors = analisisActorDao.analisisActorxcasoBuscarxActor(analisisActor);
        for(AnalisisActor aa:analisisActors){
            Tema t = new Tema();
            Caso c = new Caso();
            Actor a = new Actor();
            c.setId(aa.getCaso().getId());
            a.setId(aa.getActor().getId());
            t.setCaso(c);
            t.setActor(a);
            aa.setTemas(temaDao.temaxcasoxactorBuscar(t));
            analisisActores.add(aa);
        }
        return analisisActores;
    }

    @Override
    public AnalisisActor analisisActorxcasoBuscarOne(AnalisisActor analisisActor) {
        return analisisActorDao.analisisActorxcasoBuscarOne(analisisActor);
    }

    @Override
    public void analisisActorxcasoUpdate(AnalisisActor analisisActor) {
        analisisActorDao.analisisActorxcasoUpdate(analisisActor);
    }

    @Override
    public void analisisActorDelete(AnalisisActor analisisActor) {
        analisisActorDao.analisisActorDelete(analisisActor);
    }

    @Override
    public void analisisActorArchivar(AnalisisActor analisisActor) {
        analisisActorDao.analisisActorArchivar(analisisActor);
    }

    

    
    
}
