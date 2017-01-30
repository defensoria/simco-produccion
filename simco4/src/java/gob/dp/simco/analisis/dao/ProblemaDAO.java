/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.Problema;

/**
 *
 * @author carlos
 */
public interface ProblemaDAO {
    
    public void problemaInsertar(Problema problema);
            
    public void problemaUpdate(Problema problema);
            
    public Problema problemaBuscar(long id);
    
}
