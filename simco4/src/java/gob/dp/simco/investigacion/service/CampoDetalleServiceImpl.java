/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.service;

import gob.dp.simco.investigacion.dao.CampoDetalleDAO;
import gob.dp.simco.investigacion.entity.CampoDetalle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class CampoDetalleServiceImpl implements CampoDetalleService{
    
    @Autowired
    private CampoDetalleDAO campoDetalleDAO;

    @Override
    public void campoDetalleInsertar(CampoDetalle campoDetalle) {
        campoDetalleDAO.campoDetalleInsertar(campoDetalle);
    }

    @Override
    public List<CampoDetalle> campoDetalleBuscar(Long idCampo) {
        return campoDetalleDAO.campoDetalleBuscar(idCampo);
    }

    @Override
    public void campoDetalleUpdate(CampoDetalle campoDetalle) {
        campoDetalleDAO.campoDetalleUpdate(campoDetalle);
    }
    
}
