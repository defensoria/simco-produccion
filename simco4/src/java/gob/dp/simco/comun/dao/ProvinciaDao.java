/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.dao;

import gob.dp.simco.comun.entity.Provincia;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ProvinciaDao {
    
    public List<Provincia> provinciaLista(String idDepartamento);
    
    public Provincia provinciaOne(Provincia provincia);
    
}
