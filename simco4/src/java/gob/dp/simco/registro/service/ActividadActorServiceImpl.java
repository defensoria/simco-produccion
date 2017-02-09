/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.comun.util.FunctionUtil;
import gob.dp.simco.registro.bean.FiltroCasoActor;
import gob.dp.simco.registro.dao.ActividadActorDao;
import gob.dp.simco.registro.entity.ActividadActor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActividadActorServiceImpl implements ActividadActorService{
    
    @Autowired
    private ActividadActorDao actividadActorDao;

    @Override
    public void actividadActorInsertar(ActividadActor actividadActor) {
        actividadActor.setEstado("ACT");
        actividadActorDao.actividadActorInsertar(actividadActor);
    }

    @Override
    public void actividadActorUpdate(ActividadActor actividadActor) {
        actividadActorDao.actividadActorUpdate(actividadActor);
    }

    @Override
    public void actividadActorDelete(ActividadActor actividadActor) {
        actividadActorDao.actividadActorDelete(actividadActor);
    }

    @Override
    public void saveOrUpdate(ActividadActor actividadActor) {
        Integer count = actividadActorDao.actividadActorBuscarOne(actividadActor);
        if(count == 0)
            actividadActorInsertar(actividadActor);
        else
            actividadActorUpdate(actividadActor);
    }

    @Override
    public List<ActividadActor> actividadActorXcaso(FiltroCasoActor filtroCasoActor) {
        return actividadActorDao.actividadActorXcaso(filtroCasoActor);
    }

    @Override
    public List<ActividadActor> actividadActorNivelAD(ActividadActor actividadActor) {
        return actividadActorDao.actividadActorNivelAD(actividadActor);
    }

    @Override
    public Double ponderadoAD(long idActor) {
        List<ActividadActor> lista = actividadActorDao.listaActorAD(idActor);
        Double ponderado = 0.0;
        Double suma = 0.0;
        for(ActividadActor aa : lista){
            if(aa.getNivel() != null)
                suma = suma + aa.getNivel();
        }
        if(suma != null && suma > 0.0){
            ponderado = suma/lista.size();
            return Double.parseDouble(FunctionUtil.formateaDecimal(ponderado));
        }
        return null;
    }

    
}
