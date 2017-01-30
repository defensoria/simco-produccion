/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.dao.NoticiaRegistroDAO;
import gob.dp.simco.registro.entity.NoticiaRegistro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class NoticiaRegistroServiceImpl implements NoticiaRegistroService{
    
    @Autowired
    private NoticiaRegistroDAO noticiaRegistroDAO;

    @Override
    public List<NoticiaRegistro> noticiaRegistroBuscar(long idActividad) {
        return noticiaRegistroDAO.noticiaRegistroBuscar(idActividad);
    }

    @Override
    public void noticiaRegistroInsertar(NoticiaRegistro noticiaRegistro) {
        noticiaRegistroDAO.noticiaRegistroInsertar(noticiaRegistro);
    }

    @Override
    public void noticiaRegistroUpdate(NoticiaRegistro noticiaRegistro) {
        noticiaRegistroDAO.noticiaRegistroUpdate(noticiaRegistro);
    }
}
