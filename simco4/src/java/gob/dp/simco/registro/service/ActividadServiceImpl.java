/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.ActividadDao;
import gob.dp.simco.registro.entity.Actividad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ActividadServiceImpl implements ActividadService{
    
    @Autowired
    private ActividadDao actividadDao;

    @Override
    public void actividadNuevo(Actividad actividad) {
        actividadDao.actividadInsertar(actividad);
    }

    @Override
    public void actividadModificar(Actividad actividad) {
        actividadDao.actividadUpdate(actividad);
    }

    @Override
    public Actividad actividadBuscarOne(Long idActividad) {
        Actividad actividad = new Actividad();
        actividad.setId(idActividad);
        return actividadDao.actividadBuscarOne(actividad);
    }

    @Override
    public List<Actividad> actividadxActividadBuscar(Long idActividad) {
        return actividadDao.actividadxActividadBuscar(idActividad);
    }

    @Override
    public List<Actividad> actividadxActividadBuscarTotal(Long idActividad) {
        return actividadDao.actividadxActividadBuscarTotal(idActividad);
    }

    @Override
    public List<Actividad> actividadxActorBuscar(Long idActor) {
        return actividadDao.actividadxActorBuscar(idActor);
    }

    @Override
    public List<Actividad> actividadxActorBuscarTotal(Long idActor) {
        return actividadDao.actividadxActorBuscarTotal(idActor);
    }

    @Override
    public List<Actividad> actividadxCasoBuscar(Long idCaso) {
        return actividadDao.actividadxCasoBuscar(idCaso);
    }

    @Override
    public List<Actividad> actividadxCasoBuscarTotal(Long idCaso) {
        return actividadDao.actividadxCasoBuscarTotal(idCaso);
    }

    @Override
    public List<Actividad> actividadxActaAcuerdoBuscar(Long idActaAcuerdo) {
        return actividadDao.actividadxActaAcuerdoBuscar(idActaAcuerdo);
    }

    @Override
    public List<Actividad> actividadxActaAcuerdoBuscarTotal(Long idActaAcuerdo) {
        return actividadDao.actividadxActaAcuerdoBuscarTotal(idActaAcuerdo);
    }

    @Override
    public List<Actividad> actividadxMedioVerificacionBuscar(Long idMedioVerificacion) {
        return actividadDao.actividadxMedioVerificacionBuscar(idMedioVerificacion);
    }

    @Override
    public List<Actividad> actividadxMedioVerificacionBuscarTotal(Long idMedioVerificacion) {
        return actividadDao.actividadxMedioVerificacionBuscarTotal(idMedioVerificacion);
    }

    @Override
    public List<Actividad> actividadBusquedaPaginado() {
        return actividadDao.actividadBusquedaPaginado();
    }

    @Override
    public String actividadBusquedaPaginadoAutocompletar() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects2 = [");
        int i = 0;
        for(Actividad a:actividadDao.actividadBusquedaPaginado()){
            if(i > 0)
            stringBuilder.append(",");    
            stringBuilder.append("{value: ").append(a.getId()).append(",");
            stringBuilder.append("label: '").append(a.getNombre()).append("' ,");
            stringBuilder.append("desc: ").append("''").append(",");
            stringBuilder.append("icon: ").append("'' },");
            
        }
        stringBuilder.append("];");
        return stringBuilder.toString();
    }

    @Override
    public List<Actividad> actividadBusquedaSinCaso(int tipo) {
        if(tipo == 1){
            return actividadDao.actividadBusquedaSinCasoAC();
        }else{
            return actividadDao.actividadBusquedaSinCasoAD();
        }
        
    }

    @Override
    public List<Actividad> actividadxCasoBuscarTotalAC(Long idCaso) {
        return actividadDao.actividadxCasoBuscarTotalAC(idCaso);
    }

    @Override
    public List<Actividad> actividadxCasoBuscarTotalAD(Long idCaso) {
        return actividadDao.actividadxCasoBuscarTotalAD(idCaso);
    }

    @Override
    public Integer actividadADCodigoGenerado() {
        return actividadDao.actividadADCodigoGenerado();
    }

    @Override
    public Actividad actividadxCasoBuscarID(long idActividad) {
        return actividadDao.actividadxCasoBuscarID(idActividad);
    }

    @Override
    public List<Actividad> actividadxCodigoCasoBuscarTotal(String codigo) {
        return actividadDao.actividadxCodigoCasoBuscarTotal(codigo);
    }

    @Override
    public List<Actividad> actividadBusquedaPorCasoAC(long idCaso) {
        return actividadDao.actividadBusquedaPorCasoAC(idCaso);
    }

    @Override
    public void actividadUpdateVincular(long idActividad) {
        actividadDao.actividadUpdateVincular(idActividad);
    }

    @Override
    public void actividadUpdateDesVincular(long idActividad) {
        actividadDao.actividadUpdateDesVincular(idActividad);
    }

    @Override
    public void actividadUpdateAcontecimiento(Actividad actividad) {
        actividadDao.actividadUpdateAcontecimiento(actividad);
    }

    @Override
    public void actividadUpdateAcontecimientoQuitar(long idActividad) {
        actividadDao.actividadUpdateAcontecimientoQuitar(idActividad);
    }

    @Override
    public Actividad actividadBusquedaPorAcontecimiento(long idAcontecimiento) {
        return actividadDao.actividadBusquedaPorAcontecimiento(idAcontecimiento);
    }

    @Override
    public Integer contadorActuacionesAcontecimientos(long idCaso, int tipo) {
        if(tipo == 1)
            return actividadDao.contadorAcontecimiento(idCaso);
        if(tipo == 2)
            return actividadDao.contadorActuacion(idCaso);
        return null;
    }

    @Override
    public List<Actividad> actividadxCodigoCasoBuscarTotalAD(String codigo) {
        return actividadDao.actividadxCodigoCasoBuscarTotalAD(codigo);
    }

    @Override
    public void actividadInactivar(long idActividad) {
        actividadDao.actividadInactivar(idActividad);
    }
    
}
