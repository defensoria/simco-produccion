/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.noticia.service;

import gob.dp.simco.noticia.dao.NoticiaDAO;
import gob.dp.simco.noticia.entity.Noticia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class NoticiaServiceImpl implements NoticiaService{
    
    @Autowired
    private NoticiaDAO noticiaDAO;
    
    @Override
    public List<Noticia> listaNoticias(Noticia noticia){
        return noticiaDAO.listaNoticias(noticia);
    }
    
}
