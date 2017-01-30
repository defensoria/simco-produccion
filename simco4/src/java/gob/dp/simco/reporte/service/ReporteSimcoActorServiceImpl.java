/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.service;

import gob.dp.simco.registro.entity.CasoActor;
import gob.dp.simco.reporte.dao.ReporteSimcoActorDao;
import gob.dp.simco.reporte.entity.ReporteSimcoActor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ReporteSimcoActorServiceImpl implements ReporteSimcoActorService{
    
    @Autowired
    private ReporteSimcoActorDao reporteSimcoActorDao;

    @Override
    public List<ReporteSimcoActor> reporteActor(ReporteSimcoActor reporteSimcoActor) {
        return reporteSimcoActorDao.reporteActor(reporteSimcoActor);
    }

    @Override
    public Integer contarActorAcontecimiento(long idActor) {
        return reporteSimcoActorDao.contarActorAcontecimiento(idActor);
    }

    @Override
    public Integer contarActorAcuerdoComprometido(long idActor) {
        return reporteSimcoActorDao.contarActorAcuerdoComprometido(idActor);
    }

    @Override
    public Integer contarActorAcuerdoBeneficiario(long idActor) {
        return reporteSimcoActorDao.contarActorAcuerdoBeneficiario(idActor);
    }

    @Override
    public Integer contarCasosPorActor(Long idCaso) {
        return reporteSimcoActorDao.contarCasosPorActor(idCaso);
    }

    @Override
    public Integer contarActorAcontecimientoProtesta(Long idActor) {
        return reporteSimcoActorDao.contarActorAcontecimientoProtesta(idActor);
    }

    @Override
    public Integer contarActorAcontecimientoProtestaViolencia(Long idActor) {
        return reporteSimcoActorDao.contarActorAcontecimientoProtestaViolencia(idActor);
    }

    @Override
    public Integer contarCasosPorActorParticipacion(CasoActor casoActor) {
        return reporteSimcoActorDao.contarCasosPorActorParticipacion(casoActor);
    }
    
}
