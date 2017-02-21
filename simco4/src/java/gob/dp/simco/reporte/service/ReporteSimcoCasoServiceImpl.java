/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.service;

import gob.dp.simco.reporte.dao.ReporteSimcoCasoDao;
import gob.dp.simco.reporte.entity.FiltroReporte;
import gob.dp.simco.reporte.entity.ReporteSimcoCaso;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ReporteSimcoCasoServiceImpl implements ReporteSimcoCasoService{
    
    @Autowired
    private ReporteSimcoCasoDao reporteSimcoDao;

    @Override
    public List<ReporteSimcoCaso> reporteCasos(ReporteSimcoCaso reporteSimcoCaso) {
        return reporteSimcoDao.reporteCasos(reporteSimcoCaso);
    }

    @Override
    public Integer cantidadAcuerdosCaso(long idCaso) {
        return reporteSimcoDao.cantidadAcuerdosCaso(idCaso);
    }

    @Override
    public Integer cantidadEmpresaMineraCaso(String codigoCaso) {
        return reporteSimcoDao.cantidadEmpresaMineraCaso(codigoCaso);
    }

    @Override
    public Integer cantidadMuertosHeridos(String codigoCaso, String estado, String estadoTipo) {
        if(StringUtils.equals(estado, "01")){
            if(StringUtils.equals(estadoTipo, "01")){
                return reporteSimcoDao.cantidadMuertosCiviles(codigoCaso);
            }
            if(StringUtils.equals(estadoTipo, "02")){
                return reporteSimcoDao.cantidadMuertosPNP(codigoCaso);
            }
            if(StringUtils.equals(estadoTipo, "03")){
                return reporteSimcoDao.cantidadMuertosFFAA(codigoCaso);
            }
        }
        if(StringUtils.equals(estado, "02")){
            if(StringUtils.equals(estadoTipo, "01")){
                return reporteSimcoDao.cantidadHeridosCiviles(codigoCaso);
            }
            if(StringUtils.equals(estadoTipo, "02")){
                return reporteSimcoDao.cantidadHeridosPNP(codigoCaso);
            }
            if(StringUtils.equals(estadoTipo, "03")){
                return reporteSimcoDao.cantidadHeridosFFAA(codigoCaso);
            }
        }
        return null;
    }

    @Override
    public List<ReporteSimcoCaso> listaCasosNuevosPorMes(FiltroReporte filtroReporte) {
        return reporteSimcoDao.listaCasosNuevosPorMes(filtroReporte);
    }

    @Override
    public List<ReporteSimcoCaso> listaCasosResueltosPorMes(FiltroReporte filtroReporte) {
        return reporteSimcoDao.listaCasosResueltosPorMes(filtroReporte);
    }

    @Override
    public List<ReporteSimcoCaso> listaCasosActivosTotales(FiltroReporte filtroReporte) {
        return reporteSimcoDao.listaCasosActivosTotales(filtroReporte);
    }
    
}
