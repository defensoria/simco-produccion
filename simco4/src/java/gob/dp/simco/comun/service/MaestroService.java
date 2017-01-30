/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.entity.Maestro;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface MaestroService {
    
    public List<Maestro> listaSimple(Maestro maestro);
    
    public List<Maestro> listaCompuesta(Maestro maestro);
    
    public Integer padreParametro(Maestro maestro);
    
}
