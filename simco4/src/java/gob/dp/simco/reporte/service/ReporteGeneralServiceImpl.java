/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.service;

import gob.dp.simco.registro.dao.CasoDao;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.reporte.entity.FiltroReporte;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ReporteGeneralServiceImpl implements ReporteGeneralService{
    
    @Autowired
    private CasoDao casoDao;

    @Override
    public List<Caso> reporteCaso(FiltroReporte filtroReporte) {
        List<Integer> listDeparamentos = new ArrayList<>();
        if(StringUtils.isNotBlank(filtroReporte.getDepartamento())){
            String[] adArray = filtroReporte.getDepartamento().split(",");
            for (String adArray1 : adArray) {
                listDeparamentos.add(Integer.parseInt(adArray1));
            }
            filtroReporte.setListaDepartamentos(listDeparamentos);
            filtroReporte.setListaDepartamentosSize(listDeparamentos.size());
        }else{
            filtroReporte.setListaDepartamentos(listDeparamentos);
            filtroReporte.setListaDepartamentosSize(null);
        }
        
        List<String> listAnhos = new ArrayList<>();
        if(StringUtils.isNotBlank(filtroReporte.getAnhos())){
            String[] adArray = filtroReporte.getAnhos().split(",");
            for (String adArray1 : adArray) {
                listAnhos.add(adArray1);
            }
            filtroReporte.setListaAnhos(listAnhos);
            filtroReporte.setListaAnhosSize(listAnhos.size());
        }else{
            filtroReporte.setListaAnhos(listAnhos);
            filtroReporte.setListaAnhosSize(null);
        }
        
        List<String> listMes = new ArrayList<>();
        if(StringUtils.isNotBlank(filtroReporte.getMes())){
            String[] adArray = filtroReporte.getMes().split(",");
            for (String adArray1 : adArray) {
                listMes.add(adArray1);
            }
            filtroReporte.setListaMeses(listMes);
            filtroReporte.setListaMesesSize(listMes.size());
        }else{
            filtroReporte.setListaMeses(listMes);
            filtroReporte.setListaMesesSize(null);
        }
        
        return casoDao.reporteCaso(filtroReporte);
    }
    
}
