/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.ContextoPregunta;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ContextoPreguntaDao {
    
    public List<ContextoPregunta> contextoPreguntaBuscar(Long idTipo);

}


