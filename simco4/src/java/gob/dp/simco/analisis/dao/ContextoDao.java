/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.dao;

import gob.dp.simco.analisis.entity.Contexto;

/**
 *
 * @author carlos
 */
public interface ContextoDao {
    
    public void contextoInsertar(Contexto contexto);
    
    public void contextoUpdate(Contexto contexto);
    
    public Contexto contextoBuscar(Contexto contexto);
}


