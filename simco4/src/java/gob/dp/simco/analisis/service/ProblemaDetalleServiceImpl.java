/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.service;

import gob.dp.simco.analisis.dao.ProblemaDetalleDAO;
import gob.dp.simco.analisis.entity.ProblemaDetalle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ProblemaDetalleServiceImpl implements ProblemaDetalleService{
    
    @Autowired
    private ProblemaDetalleDAO problemaDetalleDAO;

    @Override
    public void problemaDetalleInsertar(ProblemaDetalle problemaDetalle) {
        problemaDetalleDAO.problemaDetalleInsertar(problemaDetalle);
    }

    @Override
    public void problemaDetalleUpdate(ProblemaDetalle problemaDetalle) {
        problemaDetalleDAO.problemaDetalleUpdate(problemaDetalle);
    }

    @Override
    public List<ProblemaDetalle> problemaDetalleBuscar(long idProblema) {
        return problemaDetalleDAO.problemaDetalleBuscar(idProblema);
    }

    @Override
    public void problemaDetalleUpdateDatos(ProblemaDetalle problemaDetalle) {
        problemaDetalleDAO.problemaDetalleUpdateDatos(problemaDetalle);
    }
    
}
