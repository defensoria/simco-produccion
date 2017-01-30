/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.dao.MaestroDao;
import gob.dp.simco.comun.entity.Maestro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class MaestroServiceImpl implements MaestroService{
    
    @Autowired
    private MaestroDao maestroDao;

    @Override
    public List<Maestro> listaSimple(Maestro maestro) {
        return maestroDao.listaSimple(maestro);
    }

    @Override
    public List<Maestro> listaCompuesta(Maestro maestro) {
        return maestroDao.listaCompuesta(maestro);
    }

    @Override
    public Integer padreParametro(Maestro maestro) {
        return maestroDao.padreParametro(maestro);
    }

    
}
