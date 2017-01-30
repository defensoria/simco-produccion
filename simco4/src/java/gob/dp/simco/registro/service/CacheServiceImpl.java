/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.service;

import gob.dp.simco.registro.bean.FiltroParametro;
import gob.dp.simco.registro.entity.Parametro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class CacheServiceImpl implements CacheService{

    private static final Logger log = Logger.getLogger(CacheServiceImpl.class);

    private static final Integer CACHE_PARAMETRO_TIPOACTIVIDAD = 10;
    
    private static final Integer CACHE_PARAMETRO_TIPOACTOR = 20;
    
    private static final Integer CACHE_PARAMETRO_TIPOACTAACUERDO = 30;
    
    private static final Integer CACHE_PARAMETRO_TIPOMEDIO = 40;
    
    private static final Integer CACHE_PARAMETRO_PARTICIPACION_DEFENSORIA = 50;
    
    private static final Integer CACHE_PARAMETRO_PARTICIPACION_ACTOR = 60;
    
    private static final Integer CACHE_PARAMETRO_TIPOACONTECIMIENTO = 70;
    
    private static final Integer CACHE_PARAMETRO_CLASIFICACION_ACTOR = 80;
    
    private static final Integer CACHE_PARAMETRO_TIPO_CASO = 90;
    
    private static final Integer CACHE_PARAMETRO_GOBIERNO_CASO = 110;
    
    private static final Integer CACHE_PARAMETRO_ESTADO_CASO = 120;
    
    private static final Integer CACHE_PARAMETRO_SOCIOAMBIENTAL_CASO = 130;
    
    private static final Integer CACHE_PARAMETRO_ADJUNTIADEFENSORIAL_CASO = 140;
    
    private static final Integer CACHE_PARAMETRO_TIPO_PARTICIPACION = 150;
    
    private static final Integer CACHE_PARAMETRO_TIPO_ORGANIZACION = 160;
    
    private static final Integer CACHE_PARAMETRO_AMBITO = 170;
    
    private static final Integer CACHE_PARAMETRO_TIPO_ENTIDAD = 180;
    
    private static final Integer CACHE_PARAMETRO_TIPO_CIVIL_PNP_FFAA = 190;
    
    private static final Integer CACHE_PARAMETRO_TIPO_VICTIMA = 200;
    
    private static final Integer CACHE_PARAMETRO_TIPO_DIALOGO = 210;
    
    private static final Integer CACHE_PARAMETRO_TIPO_MECANISMO = 220;
    
    private static final Integer CACHE_PARAMETRO_TIPO_PARTICIPACION_CASO = 230;
    
    private static final Integer CACHE_PARAMETRO_MOMENTO_DIALOGO = 240;
    
    private static final Integer CACHE_PARAMETRO_TIPO_VIOLENCIA = 250;
    
    private static final Integer CACHE_PARAMETRO_RESULTADO_VIOLENCIA = 260;
    
    private static final Integer CACHE_PARAMETRO_TIPO_ROL_ACTOR = 270;
    
    private static final Integer CACHE_PARAMETRO_ANHO = 280;
    
    private static final Integer CACHE_PARAMETRO_TIPO_INVESTIGACION = 300;
    
    private static final Integer CACHE_PARAMETRO_TIPO_ARCHIVO = 310;
    
    private static final Integer CACHE_PARAMETRO_TIPO_SOPORTE = 320;

    private volatile HashMap<Integer, Object> contenedor = null;

    @Autowired
    private ParametroService parametroService;

    private List<Parametro> buscarParametro(Integer codigoPadre, Integer key) {
        return buscarParametro(codigoPadre, key, false);
    }

    private List<Parametro> buscarParametro(Integer codigoPadre, Integer key, boolean ordenValor) {
        List<Parametro> lst = (List<Parametro>) getElemento(key);
        if (lst == null) {
            try {
                FiltroParametro filtro = new FiltroParametro();
                filtro.setOrdenValor(ordenValor);
                filtro.setCodigoPadreParametro(codigoPadre);
                lst = parametroService.buscarParametro(filtro);
                putElemento(key, lst);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        if (lst != null) {
            return new ArrayList(lst);
        }
        return lst;
    }

    @Override
    public List<Parametro> buscarTipoActividad() {
        return buscarParametro(CACHE_PARAMETRO_TIPOACTIVIDAD, CACHE_PARAMETRO_TIPOACTIVIDAD);
    }
    
    @Override
    public List<Parametro> buscarPartificacionDefensoria() {
        return buscarParametro(CACHE_PARAMETRO_PARTICIPACION_DEFENSORIA, CACHE_PARAMETRO_PARTICIPACION_DEFENSORIA);
    }
    
    @Override
    public List<Parametro> buscarTipoActor() {
        return buscarParametro(CACHE_PARAMETRO_TIPOACTOR, CACHE_PARAMETRO_TIPOACTOR);
    }
    
    @Override
    public List<Parametro> buscarTipoActaAcuerdo() {
        return buscarParametro(CACHE_PARAMETRO_TIPOACTAACUERDO, CACHE_PARAMETRO_TIPOACTAACUERDO);
    }
    
    @Override
    public List<Parametro> buscarTipoMedio() {
        return buscarParametro(CACHE_PARAMETRO_TIPOMEDIO, CACHE_PARAMETRO_TIPOMEDIO);
    }
    
    @Override
    public List<Parametro> buscarTipoParticipacionActor() {
        return buscarParametro(CACHE_PARAMETRO_PARTICIPACION_ACTOR, CACHE_PARAMETRO_PARTICIPACION_ACTOR);
    }
    
    @Override
    public List<Parametro> buscarTipoAcontecimiento() {
        return buscarParametro(CACHE_PARAMETRO_TIPOACONTECIMIENTO, CACHE_PARAMETRO_TIPOACONTECIMIENTO);
    }
    
    @Override
    public List<Parametro> buscarClasificacionActor() {
        return buscarParametro(CACHE_PARAMETRO_CLASIFICACION_ACTOR, CACHE_PARAMETRO_CLASIFICACION_ACTOR);
    }
    
    @Override
    public List<Parametro> buscarTipoCaso() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_CASO, CACHE_PARAMETRO_TIPO_CASO);
    }
    
     @Override
    public List<Parametro> buscarEstadoCaso() {
        return buscarParametro(CACHE_PARAMETRO_ESTADO_CASO, CACHE_PARAMETRO_ESTADO_CASO);
    }
    
    @Override
    public List<Parametro> buscarSocioAmbientalCaso() {
        return buscarParametro(CACHE_PARAMETRO_SOCIOAMBIENTAL_CASO, CACHE_PARAMETRO_SOCIOAMBIENTAL_CASO);
    }
    
    @Override
    public List<Parametro> buscarGobiernoCaso() {
        return buscarParametro(CACHE_PARAMETRO_GOBIERNO_CASO, CACHE_PARAMETRO_GOBIERNO_CASO);
    }
    
    @Override
    public List<Parametro> buscarAdjuntiaDefensorial() {
        return buscarParametro(CACHE_PARAMETRO_ADJUNTIADEFENSORIAL_CASO, CACHE_PARAMETRO_ADJUNTIADEFENSORIAL_CASO);
    }
    
    @Override
    public List<Parametro> buscarTipoParticipacion() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_PARTICIPACION, CACHE_PARAMETRO_TIPO_PARTICIPACION);
    }
    
    @Override
    public List<Parametro> buscarTipoOrganizacion() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_ORGANIZACION, CACHE_PARAMETRO_TIPO_ORGANIZACION);
    }
   
    @Override
    public List<Parametro> buscarAmbito() {
        return buscarParametro(CACHE_PARAMETRO_AMBITO, CACHE_PARAMETRO_AMBITO);
    }
    
    @Override
    public List<Parametro> buscarTipoEntidad() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_ENTIDAD, CACHE_PARAMETRO_TIPO_ENTIDAD);
    }
    
    @Override
    public List<Parametro> buscarTipoCivilPNPFFAA() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_CIVIL_PNP_FFAA, CACHE_PARAMETRO_TIPO_CIVIL_PNP_FFAA);
    }
    
    @Override
    public List<Parametro> buscarTipoVictima() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_VICTIMA, CACHE_PARAMETRO_TIPO_VICTIMA);
    }
    
    @Override
    public List<Parametro> buscarTipoDialogo() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_DIALOGO, CACHE_PARAMETRO_TIPO_DIALOGO);
    }

    @Override
    public List<Parametro> buscarTipoMecanismo() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_MECANISMO, CACHE_PARAMETRO_TIPO_MECANISMO);
    }

    @Override
    public List<Parametro> buscarTipoParticipacionCaso() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_PARTICIPACION_CASO, CACHE_PARAMETRO_TIPO_PARTICIPACION_CASO);
    }
    
    @Override
    public List<Parametro> buscarMomentoDialogo() {
        return buscarParametro(CACHE_PARAMETRO_MOMENTO_DIALOGO, CACHE_PARAMETRO_MOMENTO_DIALOGO);
    }
    
    @Override
    public List<Parametro> buscarTipoViolencia() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_VIOLENCIA, CACHE_PARAMETRO_TIPO_VIOLENCIA);
    }

    @Override
    public List<Parametro> buscarResultadoViolencia() {
        return buscarParametro(CACHE_PARAMETRO_RESULTADO_VIOLENCIA, CACHE_PARAMETRO_RESULTADO_VIOLENCIA);
    }
    
    @Override
    public List<Parametro> buscarTipoRolActor() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_ROL_ACTOR, CACHE_PARAMETRO_TIPO_ROL_ACTOR);
    }
    
    @Override
    public List<Parametro> buscarAnhos() {
        return buscarParametro(CACHE_PARAMETRO_ANHO, CACHE_PARAMETRO_ANHO);
    }
    
    @Override
    public List<Parametro> buscarTipoInvestigacion() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_INVESTIGACION, CACHE_PARAMETRO_TIPO_INVESTIGACION);
    }
    
    @Override
    public List<Parametro> buscarTipoArchivo() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_ARCHIVO, CACHE_PARAMETRO_TIPO_ARCHIVO);
    }

    @Override
    public List<Parametro> buscarTipoSoporte() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_SOPORTE, CACHE_PARAMETRO_TIPO_SOPORTE);
    }
    
    private Object getElemento(Integer key) {
        if (this.contenedor == null) {
            return null;
        }
        return this.contenedor.get(key);
    }

    private synchronized void putElemento(Integer key, Object objeto) {
        if (this.contenedor == null) {
            this.contenedor = new HashMap<Integer, Object>();
        }
        this.contenedor.put(key, objeto);
        notifyAll();
    }

}
