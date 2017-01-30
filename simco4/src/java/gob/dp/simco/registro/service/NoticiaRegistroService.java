/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.entity.NoticiaRegistro;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface NoticiaRegistroService {
    
    public List<NoticiaRegistro> noticiaRegistroBuscar(long idActividad);
            
    public void noticiaRegistroInsertar(NoticiaRegistro noticiaRegistro);
    
    public void noticiaRegistroUpdate(NoticiaRegistro noticiaRegistro);
}
