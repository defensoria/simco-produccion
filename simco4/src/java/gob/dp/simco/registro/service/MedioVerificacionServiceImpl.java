/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.bean.FiltroMedioVerificacion;
import gob.dp.simco.registro.dao.MedioVerificacionDao;
import gob.dp.simco.registro.entity.MedioVerificacion;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class MedioVerificacionServiceImpl implements MedioVerificacionService{
     
    @Autowired
    private MedioVerificacionDao medioVerificacionDao;

    @Override
    public void medioVerificacionNuevo(MedioVerificacion medioVerificacion){
        medioVerificacionDao.medioVerificacionInsertar(medioVerificacion);
    }

    @Override
    public void medioVerificacionModificar(MedioVerificacion medioVerificacion){
        medioVerificacion.setFechaModificacion(new Date());
        medioVerificacionDao.medioVerificacionUpdate(medioVerificacion);
    }
    
    @Override
    public Integer medioVerificacionTotalBuscar(FiltroMedioVerificacion filtroMedioVerificacion){
        return medioVerificacionDao.medioVerificacionTotalBuscar(filtroMedioVerificacion);
    }

    @Override
    public List<MedioVerificacion> medioVerificacionxActividadBuscar(long idActividad){
        return medioVerificacionDao.medioVerificacionxActividadBuscar(idActividad);
    }

    @Override
    public List<MedioVerificacion> medioVerificacionxActividadBuscarTotal(long idActividad){
        return medioVerificacionDao.medioVerificacionxActividadBuscarTotal(idActividad);
    }

    @Override
    public MedioVerificacion medioVerificacionBuscarOne(long idMedioVerificacion){
        MedioVerificacion medioVerificacion = new MedioVerificacion();
        medioVerificacion.setId(idMedioVerificacion);
        return medioVerificacionDao.medioVerificacionBuscarOne(medioVerificacion);
    }

    @Override
    public Integer medioVerificacionCodigoGenerado() {
        return medioVerificacionDao.medioVerificacionCodigoGenerado();
    }
    
}
