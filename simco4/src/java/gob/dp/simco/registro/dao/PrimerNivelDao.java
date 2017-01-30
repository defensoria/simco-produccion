/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.PrimerNivel;
import java.util.List;


public interface PrimerNivelDao {
    
    public List<PrimerNivel> listarPrimerNivel(String tipo);
    
    public PrimerNivel primerNivelOne(String tipo);

}
