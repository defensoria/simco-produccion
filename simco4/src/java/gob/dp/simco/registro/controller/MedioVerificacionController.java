/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.controller;

import gob.dp.simco.comun.FunctionUtil;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import gob.dp.simco.registro.entity.Actividad;
import gob.dp.simco.registro.entity.ActividadMedioVerificacion;
import gob.dp.simco.registro.entity.MedioVerificacion;
import gob.dp.simco.registro.service.ActividadMedioVerificacionService;
import gob.dp.simco.registro.service.MedioVerificacionService;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class MedioVerificacionController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(MedioVerificacionController.class);

    private MedioVerificacion medioVerificacion;

    private List<MedioVerificacion> medioVerificacions;

    private Actividad actividad;

    private Part file1;

    @Autowired
    private MedioVerificacionService medioVerificacionService;

    @Autowired
    private ActividadMedioVerificacionService actividadMedioVerificacionService;

    public String cargarPagina(Long idActividad) {
        try {
            medioVerificacion = new MedioVerificacion();
            actividad = new Actividad();
            actividad.setId(idActividad);
            listarMedioVerificacion(idActividad);
            return "medioVerificacionNuevo";
        } catch (Exception e) {
            log.error("ERROR - cargarPagina()" + e);
        }
        return null;
    }

    public void listarMedioVerificacion(Long idActividad) {
        try {
            medioVerificacions = medioVerificacionService.medioVerificacionxActividadBuscar(idActividad);
        } catch (Exception ex) {
            log.error("ERROR - cargarPagina()" + ex);
        }
    }

    public void limpiarListaMedios() {
        medioVerificacions = new ArrayList<>();
    }

    public boolean saveMedioVerificacion() {
        try {
            String ruta = FunctionUtil.uploadArchive(file1);
            medioVerificacion.setRuta(ruta);
            if (medioVerificacion.getId() != null) {
                medioVerificacionService.medioVerificacionModificar(medioVerificacion);
                msg.messageInfo("Se ha modificado el medio", null);
            } else {
                medioVerificacion.setFechaRegistro(new Date());
                medioVerificacion.setCodigo(generarCodigoMedioVerificacion());
                medioVerificacionService.medioVerificacionNuevo(medioVerificacion);
                saveMedioVerificacionActividad(medioVerificacion);
                msg.messageInfo("Se ha registro el nuevo medio", null);
            }
            listarMedioVerificacion(actividad.getId());
            medioVerificacion = new MedioVerificacion();
        } catch (Exception ex) {
            log.error("ERROR - saveMedioVerificacion()" + ex);
        }
        return true;
    }

    public void setearMedioVerificacion(MedioVerificacion mv) {
        setMedioVerificacion(mv);
    }
    
    public String generarCodigoMedioVerificacion() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMM");
        String codigo = formato.format(new Date());
        String numero = String.format("%2s", medioVerificacionService.medioVerificacionCodigoGenerado().toString()).replace(' ', '0');
        return "MV" + codigo + numero;
    }

    public void saveMedioVerificacionActividad(MedioVerificacion mv) {
        try {
            ActividadMedioVerificacion actividadMedioVerificacion = new ActividadMedioVerificacion();
            actividadMedioVerificacion.setActividad(actividad);
            actividadMedioVerificacion.setMedioVerificacion(medioVerificacion);
            actividadMedioVerificacion.setEstado("ACT");
            actividadMedioVerificacionService.actividadMedioVerificacionInsertar(actividadMedioVerificacion);
        } catch (Exception ex) {
            log.error("ERROR - saveMedioVerificacionActividad()" + ex);
        }
    }

    public void removeMedioVerificacion(MedioVerificacion mv) {
        try {
            ActividadMedioVerificacion actividadMedioVerificacion = new ActividadMedioVerificacion();
            actividadMedioVerificacion.setActividad(actividad);
            actividadMedioVerificacion.setMedioVerificacion(mv);
            actividadMedioVerificacion.setEstado("INA");
            actividadMedioVerificacionService.actividadMedioVerificacionUpdate(actividadMedioVerificacion);
            listarMedioVerificacion(actividad.getId());
            msg.messageInfo("Se ha eliminado el medio", null);
        } catch (Exception ex) {
            log.error("ERROR - removeMedioVerificacion()" + ex);
        }
    }

    public MedioVerificacion getMedioVerificacion() {
        return medioVerificacion;
    }

    public void setMedioVerificacion(MedioVerificacion medioVerificacion) {
        this.medioVerificacion = medioVerificacion;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public List<MedioVerificacion> getMedioVerificacions() {
        return medioVerificacions;
    }

    public void setMedioVerificacions(List<MedioVerificacion> medioVerificacions) {
        this.medioVerificacions = medioVerificacions;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

}