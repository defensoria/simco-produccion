/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.entity.PrimerNivel;
import gob.dp.simco.registro.entity.SegundoNivel;
import gob.dp.simco.registro.entity.TercerNivel;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface NivelService {
    
    public List<PrimerNivel> listarPrimerNivel(String tipo);
    
    public List<SegundoNivel> segundoNivelBuscar(String tipo);
    
    public List<TercerNivel> tercerNivelBuscar(String tipo);
    
    public String primerNivelOne(String tipo);
    
    public String segundoNivelOne(String tipo);
    
    public String tercerNivelOne(String tipo);
}
