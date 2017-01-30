/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.service;

import gob.dp.simco.analisis.dao.ContextoTipoDao;
import gob.dp.simco.analisis.entity.ContextoTipo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ContextoTipoServiceImpl implements ContextoTipoService{
    
    @Autowired
    private ContextoTipoDao contextoTipoDao;

    @Override
    public List<ContextoTipo> contextoTipoBuscar() {
        return contextoTipoDao.contextoTipoBuscar();
    }
    
}
