/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.service;

import gob.dp.simco.intervencion.dao.IntervencionEtapaActuacionDao;
import gob.dp.simco.intervencion.entity.IntervencionEtapaActuacion;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class IntervencionEtapaActuacionServiceImpl implements IntervencionEtapaActuacionService{
    
    @Autowired
    private IntervencionEtapaActuacionDao intervencionEtapaActuacionDao;

    @Override
    public void intervencionEtapaActuacionInsertar(IntervencionEtapaActuacion intervencionEtapaActuacion) {
        intervencionEtapaActuacion.setFechaRegistro(new Date());
        intervencionEtapaActuacionDao.intervencionEtapaActuacionInsertar(intervencionEtapaActuacion);
    }

    @Override
    public List<IntervencionEtapaActuacion> intervencionEtapaActuacionBuscar(Long idEtapa) {
        return intervencionEtapaActuacionDao.intervencionEtapaActuacionBuscar(idEtapa);
    }

    @Override
    public void intervencionEtapaActuacionUpdate(IntervencionEtapaActuacion intervencionEtapaActuacion) {
        if(intervencionEtapaActuacion.getIndCheck() && intervencionEtapaActuacion.getActividadId() != null){
            intervencionEtapaActuacion.setFechaCulminacion(new Date());
        }
        intervencionEtapaActuacionDao.intervencionEtapaActuacionUpdate(intervencionEtapaActuacion);
    }

    @Override
    public void intervencionEtapaActuacionEliminar(long id) {
        intervencionEtapaActuacionDao.intervencionEtapaActuacionEliminar(id);
    }

    @Override
    public List<IntervencionEtapaActuacion> intervencionEtapaActuacionBuscarActividad(Long idEtapa) {
        return intervencionEtapaActuacionDao.intervencionEtapaActuacionBuscarActividad(idEtapa);
    }

    @Override
    public List<IntervencionEtapaActuacion> intervencionEtapaActuacionBuscarActividadGSA(Long idEtapa) {
        return intervencionEtapaActuacionDao.intervencionEtapaActuacionBuscarActividadGSA(idEtapa);
    }
    
}
