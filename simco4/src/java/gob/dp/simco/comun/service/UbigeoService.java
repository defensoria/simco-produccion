/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.entity.Departamento;
import gob.dp.simco.comun.entity.Distrito;
import gob.dp.simco.comun.entity.Provincia;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface UbigeoService {
    
    public List<Departamento> departamentoLista();
    
    public List<Provincia> provinciaLista(String idDepartamento);
    
    public List<Distrito> distritoLista(Distrito distrito);
    
    public Departamento departamentoOne(String idDepartamento);
    
    public Provincia provinciaOne(Provincia provincia);
    
    public Distrito distritoOne(Distrito distrito);
    
    public String arrayDepartamento();
}
