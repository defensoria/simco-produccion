/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.ActaAcuerdoDetalleDao;
import gob.dp.simco.registro.dao.ActorDao;
import gob.dp.simco.registro.entity.ActaAcuerdoDetalle;
import gob.dp.simco.registro.entity.Actor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActaAcuerdoDetalleServiceImpl implements ActaAcuerdoDetalleService{
    
    @Autowired
    private ActaAcuerdoDetalleDao actaAcuerdoDetalleDao;
    
    @Autowired
    private ActorDao actorDao;

    @Override
    public void actaAcuerdoDetalleInsertar(ActaAcuerdoDetalle actaAcuerdoDetalle) {
        actaAcuerdoDetalle.setFechaRegistro(new Date());
        actaAcuerdoDetalleDao.actaAcuerdoDetalleInsertar(actaAcuerdoDetalle);
    }

    @Override
    public void actaAcuerdoDetalleUpdate(ActaAcuerdoDetalle actaAcuerdoDetalle) {
        actaAcuerdoDetalleDao.actaAcuerdoDetalleUpdate(actaAcuerdoDetalle);
    }

    @Override
    public List<ActaAcuerdoDetalle> actaAcuerdoDetalleBuscarxActa(Long idActaAcuerdoDetalle) {
        return actaAcuerdoDetalleDao.actaAcuerdoDetalleBuscarxActa(idActaAcuerdoDetalle);
    }

    @Override
    public List<ActaAcuerdoDetalle> actaAcuerdoDetalleSeguimiento(ActaAcuerdoDetalle actaAcuerdoDetalle) {
        List<ActaAcuerdoDetalle> acuerdoDetalles = actaAcuerdoDetalleDao.actaAcuerdoDetalleSeguimiento(actaAcuerdoDetalle);
        List<ActaAcuerdoDetalle> aads = new ArrayList<>();
        for(ActaAcuerdoDetalle aad : acuerdoDetalles){
            List<Actor> list = actorDao.actorxAcuerdoDetalleBusqueda(aad.getId());
            aad.setListaActor(list);
            aads.add(aad);
        }
        return aads;
    }

    @Override
    public void actaAcuerdoDetalleDelete(long id) {
        actaAcuerdoDetalleDao.actaAcuerdoDetalleDelete(id);
    }

    @Override
    public void actaAcuerdoDetalleUpdateColor(ActaAcuerdoDetalle aad) {
        actaAcuerdoDetalleDao.actaAcuerdoDetalleUpdateColor(aad);
    }

    @Override
    public List<ActaAcuerdoDetalle> actaAcuerdoDetalleListaxActa(Long idActaAcuerdo) {
        return actaAcuerdoDetalleDao.actaAcuerdoDetalleListaxActa(idActaAcuerdo);
    }

    @Override
    public Integer actaAcuerdoDetalleCodigoGenerado() {
        return actaAcuerdoDetalleDao.actaAcuerdoDetalleCodigoGenerado();
    }

    @Override
    public Integer actaAcuerdoDetalleCount(Long idActividad) {
        return actaAcuerdoDetalleDao.actaAcuerdoDetalleCount(idActividad);
    }

    @Override
    public List<ActaAcuerdoDetalle> actaAcuerdoDetalleSeguimientoCaso(long idCaso) {
        List<ActaAcuerdoDetalle> acuerdoDetalles = actaAcuerdoDetalleDao.actaAcuerdoDetalleSeguimientoCaso(idCaso);
        List<ActaAcuerdoDetalle> aads = new ArrayList<>();
        for(ActaAcuerdoDetalle aad : acuerdoDetalles){
            List<Actor> list = actorDao.actorxAcuerdoDetalleBusqueda(aad.getId());
            aad.setListaActor(list);
            aads.add(aad);
        }
        return aads;
    }
}
