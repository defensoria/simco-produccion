/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.controller;

import gob.dp.simco.comun.ConstantesUtil;
import gob.dp.simco.registro.entity.Parametro;
import gob.dp.simco.registro.service.CacheService;
import java.util.List;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class ListasComunesController {

    private static final Logger log = Logger.getLogger(ListasComunesController.class);

    @Autowired
    private CacheService cacheService;

    public List<Parametro> buscarTipoActividad(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoActividad(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarParticipacionDefensoria(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarPartificacionDefensoria(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoActor(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoActor(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoActaAcuerdo(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoActaAcuerdo(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoMedio(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoMedio(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoParticipacionActor(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoParticipacionActor(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoAcontecimiento(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoAcontecimiento(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarClasificacionActor(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarClasificacionActor(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoCaso(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoCaso(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarEstadoCaso(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarEstadoCaso(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarSocioAmbientalCaso(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarSocioAmbientalCaso(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarGobiernoCaso(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarGobiernoCaso(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarAdjuntiaDefensorial(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarAdjuntiaDefensorial(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoParticipacion(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoParticipacion(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoOrganizacion(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoOrganizacion(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarAmbito(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarAmbito(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }

    public List<Parametro> buscarTipoEntidad(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoEntidad(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoCivilPNPFFAA(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoCivilPNPFFAA(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoVictima(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoVictima(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoDialogo(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoDialogo(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoMecanismo(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoMecanismo(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoParticipacionCaso(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoParticipacionCaso(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarMomentoDialogo(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarMomentoDialogo(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoViolencia(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoViolencia(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarResultadoViolencia(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarResultadoViolencia(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoRolActor(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoRolActor(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarAnhos(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarAnhos(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }

    public List<Parametro> buscarTipoInvestigacion(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoInvestigacion(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoArchivo(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoArchivo(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }

    public List<Parametro> buscarTipoSoporte(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarTipoArchivo(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }

    private List insertarValoresDefectoParametro(List lst, boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        if (insertarTODOS) {
            Parametro par = new Parametro();
            par.setValorParametro(ConstantesUtil.LISTA_VALOR_TODOS_CODIGO);
            par.setNombreParametro(ConstantesUtil.LISTA_VALOR_TODOS_NOMBRE);
            lst.add(0, par);
        }

        if (insertarNINGUNO) {
            Parametro par = new Parametro();
            par.setValorParametro(ConstantesUtil.LISTA_VALOR_NINGUNO_CODIGO);
            par.setNombreParametro(ConstantesUtil.LISTA_VALOR_NINGUNO_NOMBRE);
            lst.add(0, par);
        }

        if (insertarSELECCIONE) {
            Parametro par = new Parametro();
            par.setValorParametro(ConstantesUtil.LISTA_VALOR_SELECCIONE_CODIGO);
            par.setNombreParametro(ConstantesUtil.LISTA_VALOR_SELECCIONE_NOMBRE);
            lst.add(0, par);
        }

        return lst;
    }
}
