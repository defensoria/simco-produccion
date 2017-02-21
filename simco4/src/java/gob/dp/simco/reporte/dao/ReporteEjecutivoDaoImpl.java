/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.reporte.dao;

import gob.dp.simco.reporte.entity.ElementoResumenEjecutivo;
import gob.dp.simco.reporte.entity.FiltroReporte;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ReporteEjecutivoDaoImpl extends SqlSessionDaoSupport implements ReporteEjecutivoDao {

    @Override
    public Integer existeReporteMes(FiltroReporte filtro) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.existeReporteMes", filtro);
    }
    
    @Override
    public Integer codigoReporteMes(FiltroReporte filtro) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.codigoReporteMes", filtro);
    }
    
    @Override
    public void cargaCasoMes(FiltroReporte filtro) {
        getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.cargaCasoMes", filtro);
    }
    
    
    @Override
    public Integer totalCasosRegistrados(FiltroReporte filtro) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosRegistrados", filtro);
    }
    
    @Override
    public Integer totalCasosActivos(FiltroReporte filtro) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosActivos", filtro);
    }

    @Override
    public Integer totalCasosLatentes(FiltroReporte filtro) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosLatentes", filtro);
    }

    @Override
    public Integer totalCasosActivosLatentes(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosActivosLatentes",filtroReporte);
    }

    @Override
    public Integer totalCasosLatentesObservacion(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosLatentesObservacion",filtroReporte);
    }
    
    @Override
    public Integer totalCasosRegistradosMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosRegistradosMes",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosDialogo(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosDialogo",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActivo(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosActivo",filtroReporte);
    }

    @Override
    public Integer totalCasosDialogo(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosDialogo", filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosReunionesPreparatorias(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosReunionesPreparatorias",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosParticipacionDefensoria(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosParticipacionDefensoria",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosEchoViolencia(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosEchoViolencia",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosAccionesProtesta(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosAccionesProtesta",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActuacionDefensorial(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosActuacionDefensorial",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActuacionDefensorialSupervisionPreventiva(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosActuacionDefensorialSupervisionPreventiva",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActuacionDefensorialIntermediaciones(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosActuacionDefensorialIntermediaciones",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActuacionDefensorialAccionHumanitaria(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosActuacionDefensorialAccionHumanitaria",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosActuacionDefensorialDefensaLegal(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosActuacionDefensorialDefensaLegal",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosFaceEscalamiento(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosFaceEscalamiento",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosFaceTemprana(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosFaceTemprana",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosFaceDesescalamiento(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosFaceDesescalamiento",filtroReporte);
    }

    @Override
    public Integer totalGeneralCasosFaceCrisis(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalGeneralCasosFaceCrisis",filtroReporte);
    }

    @Override
    public Integer totalCasosResueltos(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosResueltos",filtroReporte);
    }

    @Override
    public Integer totalCasosPropuestos(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosPropuestos",filtroReporte);
    }

    @Override
    public List<ElementoResumenEjecutivo> cadenaNombreCasosResueltos(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.cadenaNombreCasosResueltos",filtroReporte);
    }

    @Override
    public List<ElementoResumenEjecutivo> cadenaNombreCasosPropuestos(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.cadenaNombreCasosPropuestos",filtroReporte);
    }

    @Override
    public List<ElementoResumenEjecutivo> totalMensualCasosRegistrados(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalMensualCasosRegistrados",filtroReporte);
    }

    @Override
    public List<ElementoResumenEjecutivo> totalMensualCasosActivos(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalMensualCasosActivos",filtroReporte);
    }

    @Override
    public List<ElementoResumenEjecutivo> totalMensualSegunTipologiaCaso(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalMensualSegunTipologiaCaso",filtroReporte);
    }

    @Override
    public Integer totalCasosResueltoMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosResueltoMes",filtroReporte);
    }

    @Override
    public Integer totalCasosDialogoNegociacion(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosDialogoNegociacion", filtroReporte);
    }

    @Override
    public Integer totalCasosDialogoReuniones(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosDialogoReuniones", filtroReporte);
    }

    @Override
    public Integer totalCasosDialogoEspacioDialogo(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosDialogoEspacioDialogo", filtroReporte);
    }

    @Override
    public Integer totalCasosACVictimaViolencia(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosACVictimaViolencia", filtroReporte);
    }

    @Override
    public Integer totalCasosACVictimaViolenciaMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosACVictimaViolenciaMes", filtroReporte);
    }

    @Override
    public Integer totalCasosACAccionesProtestaMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosACAccionesProtestaMes", filtroReporte);
    }

    @Override
    public Integer totalCasosAD(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosAD", filtroReporte);
    }

    @Override
    public Integer totalCasosADMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosADMes", filtroReporte);
    }

    @Override
    public Integer totalActividadAccionesHumanitariasMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalActividadAccionesHumanitariasMes", filtroReporte);
    }

    @Override
    public Integer totalActividadIntermediacionMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalActividadIntermediacionMes", filtroReporte);
    }

    @Override
    public Integer totalActividadSupervisionPreventivaMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalActividadSupervisionPreventivaMes", filtroReporte);
    }

    @Override
    public Integer totalActividadAccionesDefensaLegalMes(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalActividadAccionesDefensaLegalMes", filtroReporte);
    }

    @Override
    public List<ElementoResumenEjecutivo> totalMensualSegunTipologiaCasoDialogo(FiltroReporte filtroReporte) {
        return getSqlSession().selectList("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalMensualSegunTipologiaCasoDialogo",filtroReporte);
    }

    @Override
    public Integer totalCasosSegunTipologiaCasoDialogo(FiltroReporte filtroReporte) {
        return getSqlSession().selectOne("gob.dp.simco.reporte.dao.ReporteEjecutivoDao.totalCasosSegunTipologiaCasoDialogo",filtroReporte);
    }

}