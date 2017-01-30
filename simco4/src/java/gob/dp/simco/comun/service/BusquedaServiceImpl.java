/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.dao.BusquedaDao;
import gob.dp.simco.comun.entity.Busqueda;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class BusquedaServiceImpl implements BusquedaService {

    @Autowired
    private BusquedaDao busquedaDao;

    @Override
    public String autocompletarBusquedaGeneral() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects = [");
        int i = 0;

        for (Busqueda busqueda : busquedaDao.busquedaLista()) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append("{value: ").append(busqueda.getCodigo()).append(",");
            stringBuilder.append("label: '").append(busqueda.getNombre() + " - " + busqueda.getDetalle()).append("' ,");
            stringBuilder.append("desc: ").append("''").append(",");
            stringBuilder.append("icon: ").append("'' },");
        }
        stringBuilder.append("];");
        return stringBuilder.toString();
    }

    @Override
    public List<Busqueda> busquedaListaxPalabra(Busqueda busqueda) {
        return busquedaDao.busquedaListaxPalabra(busqueda);
    }

}
