/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.SegundoNivel;
import java.util.List;


public interface SegundoNivelDao {
    
    public List<SegundoNivel> segundoNivelBuscar(String tipo);
    
    public SegundoNivel segundoNivelOne(String tipo);

}
