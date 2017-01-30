/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.dao.DepartamentoDao;
import gob.dp.simco.comun.dao.DistritoDao;
import gob.dp.simco.comun.dao.ProvinciaDao;
import gob.dp.simco.comun.entity.Departamento;
import gob.dp.simco.comun.entity.Distrito;
import gob.dp.simco.comun.entity.Provincia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class UbigeoServiceImpl implements UbigeoService{
    
    @Autowired
    private DepartamentoDao departamentoDao;
    
    @Autowired
    private ProvinciaDao provinciaDao;
    
    @Autowired
    private DistritoDao distritoDao;
    
    
    @Override
    public List<Departamento> departamentoLista() {
        return departamentoDao.departamentoLista();
    }

    @Override
    public List<Provincia> provinciaLista(String idDepartamento) {
        return provinciaDao.provinciaLista(idDepartamento);
    }

    @Override
    public List<Distrito> distritoLista(Distrito distrito) {
        return distritoDao.distritoLista(distrito);
    }

    @Override
    public Departamento departamentoOne(String idDepartamento) {
        return departamentoDao.departamentoOne(idDepartamento);
    }

    @Override
    public Provincia provinciaOne(Provincia provincia) {
        return provinciaDao.provinciaOne(provincia);
    }

    @Override
    public Distrito distritoOne(Distrito distrito) {
        return distritoDao.distritoOne(distrito);
    }
    
    @Override
    public String arrayDepartamento(){
        StringBuilder sb = new StringBuilder();
        List<Departamento> departamentos= departamentoDao.departamentoLista();
        if(departamentos.size() > 0){
            sb.append("var departamento = new Array()");
            int i = 0;
            for(Departamento departamento : departamentos){
                i++;
                sb.append("departamento["+i+"] = new slctr('"+departamento.getIdDepartamento()+"','"+departamento.getDescripcion()+"') ");
            }
        }
        return sb.toString();
    }
    
}
