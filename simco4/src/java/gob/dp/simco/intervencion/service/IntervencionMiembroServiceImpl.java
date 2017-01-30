/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.intervencion.service;

import gob.dp.simco.intervencion.dao.IntervencionMiembroDao;
import gob.dp.simco.intervencion.entity.IntervencionMiembro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class IntervencionMiembroServiceImpl implements IntervencionMiembroService{
    
    @Autowired
    private IntervencionMiembroDao intervencionMiembroDao;

    @Override
    public void intervencionMiembroInsertar(IntervencionMiembro intervencionMiembro) {
        if(intervencionMiembroDao.intervencionMiembroValidaInsert(intervencionMiembro) == 0){
            intervencionMiembroDao.intervencionMiembroInsertar(intervencionMiembro);
        }else{
            intervencionMiembro.setEstado("ACT");
            if(intervencionMiembro.getId() == null){
                long id = intervencionMiembroDao.intervencionMiembroCodigoMiembro(intervencionMiembro);
                intervencionMiembro.setId(id);
            }
            intervencionMiembroDao.intervencionMiembroUpdate(intervencionMiembro);
        }
    }

    @Override
    public List<IntervencionMiembro> intervencionMiembroBuscar(Long idEtapa) {
        return intervencionMiembroDao.intervencionMiembroBuscar(idEtapa);
    }

    @Override
    public Integer intervencionMiembroCountEtapa(Long idEtapa) {
        return intervencionMiembroDao.intervencionMiembroCountEtapa(idEtapa);
    }

    @Override
    public void intervencionMiembroUpdate(IntervencionMiembro intervencionMiembro) {
        intervencionMiembroDao.intervencionMiembroUpdate(intervencionMiembro);
    }
    
}
