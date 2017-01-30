/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.entity.RegistroCarga;

/**
 *
 * @author carlos
 */
public interface RegistroCargaService {
    
    public void registroCargaInsert(RegistroCarga registroCarga);
    
    public RegistroCarga registroCargaBuscarUltimo();
    
    public void registroCargaBuscarUpdate(RegistroCarga registroCarga);
    
}
