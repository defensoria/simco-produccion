/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.bean.FiltroCaso;
import gob.dp.simco.registro.dao.CasoDao;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.entity.Parametro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class CasoServiceImpl implements CasoService {

    @Autowired
    private CasoDao casoDao;

    @Autowired
    private CacheService cacheService;

    @Override
    public void casoNuevo(Caso caso) {
        casoDao.casoInsertar(caso);
    }

    @Override
    public void casoModificar(Caso caso) {
        casoDao.casoUpdate(caso);
    }

    @Override
    public List<Caso> casoBuscar(Caso caso) {
        return casoDao.casoBuscar(caso);
    }

    @Override
    public Integer casoTotalBuscar(FiltroCaso filtroCaso) {
        return casoDao.casoTotalBuscar(filtroCaso);
    }

    @Override
    public List<Caso> casoxActividadBuscar(long idActividad) {
        return casoDao.casoxActividadBuscar(idActividad);
    }

    @Override
    public List<Caso> casoxActividadBuscarTotal(long idActividad) {
        return casoDao.casoxActividadBuscarTotal(idActividad);
    }

    @Override
    public Caso casoBuscarOne(Long idCaso) {
        return casoDao.casoBuscarOne(idCaso);
    }

    @Override
    public String casoBuscarAutocomplete(Caso cas) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects = [");
        int i = 0;
        for (Caso a : casoDao.casoBuscar(cas)) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append("{value: ").append(a.getId()).append(",");
            stringBuilder.append("label: '").append(a.getNombre()).append("' ,");
            stringBuilder.append("desc: ").append("''").append(",");
            stringBuilder.append("icon: ").append("'' },");
        }
        stringBuilder.append("];");
        return stringBuilder.toString();
    }

    @Override
    public Integer casoCodigoGenerado() {
        return casoDao.casoCodigoGenerado();
    }

    @Override
    public String adjuntiaBuscarAutocomplete() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects1 = [");
        int i = 0;
        for (Parametro a : cacheService.buscarAdjuntiaDefensorial()) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append("{value: '").append(a.getValorParametro()).append("' ,");
            stringBuilder.append("label: '").append(a.getNombreParametro()).append("' ,");
            stringBuilder.append("desc: ").append("''").append(",");
            stringBuilder.append("icon: ").append("'' },");
        }
        stringBuilder.append("];");

        return stringBuilder.toString();
    }

    @Override
    public Caso casoxActaAcuerdoDetalle(long idActaAcuerdoDetalle) {
        return casoDao.casoxActaAcuerdoDetalle(idActaAcuerdoDetalle);
    }

    @Override
    public void casoUpdateIndicador(Caso caso) {
        casoDao.casoUpdateIndicador(caso);
    }

    @Override
    public void casoUpdateSistesis(Caso caso) {
        casoDao.casoUpdateSistesis(caso);
    }

    @Override
    public List<Caso> buscarCasoXnombreCodigo(FiltroCaso filtroCaso) {
        return casoDao.buscarCasoXnombreCodigo(filtroCaso);
    }

    @Override
    public void casoUpdateAprobar(Caso caso) {
        casoDao.casoUpdateAprobar(caso);
    }

}