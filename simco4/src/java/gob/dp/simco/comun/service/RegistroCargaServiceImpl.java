/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.entity.RegistroCarga;
import gob.dp.simco.comun.dao.RegistroCargaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class RegistroCargaServiceImpl implements RegistroCargaService{
    
    
    @Autowired
    private RegistroCargaDao registroCargaDao;

    @Override
    public void registroCargaInsert(RegistroCarga registroCarga) {
        registroCargaDao.registroCargaInsert(registroCarga);
    }

    @Override
    public RegistroCarga registroCargaBuscarUltimo() {
        return registroCargaDao.registroCargaBuscarUltimo();
    }

    @Override
    public void registroCargaBuscarUpdate(RegistroCarga registroCarga) {
        registroCargaDao.registroCargaBuscarUpdate(registroCarga);
    }
    
    
}
