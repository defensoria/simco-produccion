/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.dao.ReporteDao;
import gob.dp.simco.comun.entity.Resumen;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ReporteServiceImpl implements ReporteService{
    
    @Autowired
    private ReporteDao reporteDao;

    @Override
    public List<Resumen> graficoEstadoAnho(List<Integer> anhos) {
        return reporteDao.graficoEstadoAnho(anhos);
    }

    @Override
    public Integer grafico001Activo(Resumen resumen) {
        return reporteDao.grafico001Activo(resumen);
    }

    @Override
    public List<Resumen> grafico001Meses(String anho) {
        return reporteDao.grafico001Meses(anho);
    }

    @Override
    public Integer grafico001MesesCount(Resumen resumen) {
        return reporteDao.grafico001MesesCount(resumen);
    }

    @Override
    public List<Resumen> grafico001Departamento(String anho) {
        return reporteDao.grafico001Departamento(anho);
    }

    @Override
    public Integer grafico001DepartamentoCount(Resumen resumen) {
        return reporteDao.grafico001DepartamentoCount(resumen);
    }

    @Override
    public Integer grafico004TipoCaso(Resumen resumen) {
        return reporteDao.grafico004TipoCaso(resumen);
    }

    @Override
    public Integer grafico005DepartamentoCount(Resumen resumen) {
        return reporteDao.grafico005DepartamentoCount(resumen);
    }

    @Override
    public Integer grafico006MesesCount(Resumen resumen) {
        return reporteDao.grafico006MesesCount(resumen);
    }

    @Override
    public List<Resumen> listadoAnhoCaso() {
        return reporteDao.listadoAnhoCaso();
    }

    @Override
    public void cargaCasoMes(long idCodigoCarga) {
        reporteDao.cargaCasoMes(idCodigoCarga);
    }
}
