/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.dao;

import gob.dp.simco.registro.entity.CasoActor;
import gob.dp.simco.reporte.entity.ReporteSimcoActor;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ReporteSimcoActorDaoImpl extends SqlSessionDaoSupport implements ReporteSimcoActorDao {

    @Override
    public List<ReporteSimcoActor> reporteActor(ReporteSimcoActor reporteSimcoActor) {
        return getSqlSession().selectList("gob.dp.simco.reporte.dao.ReporteSimcoActorDao.reporteActor",reporteSimcoActor);
    }

    @Override
    public Integer contarActorAcontecimiento(long idActor) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoActorDao.contarActorAcontecimiento",idActor);
    }

    @Override
    public Integer contarActorAcuerdoComprometido(long idActor) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoActorDao.contarActorAcuerdoComprometido",idActor);
    }

    @Override
    public Integer contarActorAcuerdoBeneficiario(long idActor) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoActorDao.contarActorAcuerdoBeneficiario",idActor);
    }

    @Override
    public Integer contarCasosPorActor(Long idActor) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoActorDao.contarCasosPorActor",idActor);
    }

    @Override
    public Integer contarActorAcontecimientoProtesta(Long idActor) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoActorDao.contarActorAcontecimientoProtesta",idActor);
    }

    @Override
    public Integer contarActorAcontecimientoProtestaViolencia(Long idActor) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoActorDao.contarActorAcontecimientoProtestaViolencia",idActor);
    }

    @Override
    public Integer contarCasosPorActorParticipacion(CasoActor casoActor) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteSimcoActorDao.contarCasosPorActorParticipacion",casoActor);
    }
}
