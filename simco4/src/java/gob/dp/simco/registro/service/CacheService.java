/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.entity.Parametro;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface CacheService {
    
    public List<Parametro> buscarTipoActividad();
    
    public List<Parametro> buscarPartificacionDefensoria();
    
    public List<Parametro> buscarTipoActor();
    
    public List<Parametro> buscarTipoActaAcuerdo();
    
    public List<Parametro> buscarTipoMedio();
    
    public List<Parametro> buscarTipoParticipacionActor();   
    
    public List<Parametro> buscarTipoAcontecimiento();
    
    public List<Parametro> buscarClasificacionActor();
    
    public List<Parametro> buscarTipoCaso();
    
    public List<Parametro> buscarEstadoCaso();
    
    public List<Parametro> buscarSocioAmbientalCaso();
    
    public List<Parametro> buscarGobiernoCaso();
    
    public List<Parametro> buscarAdjuntiaDefensorial();
    
    public List<Parametro> buscarTipoParticipacion();
    
    public List<Parametro> buscarTipoOrganizacion();
    
    public List<Parametro> buscarAmbito();
    
    public List<Parametro> buscarTipoEntidad();
    
    public List<Parametro> buscarTipoCivilPNPFFAA();
    
    public List<Parametro> buscarTipoVictima();
    
    public List<Parametro> buscarTipoDialogo();
    
    public List<Parametro> buscarTipoMecanismo();
    
    public List<Parametro> buscarTipoParticipacionCaso();
    
    public List<Parametro> buscarMomentoDialogo();
    
    public List<Parametro> buscarTipoViolencia();
    
    public List<Parametro> buscarResultadoViolencia();
    
    public List<Parametro> buscarTipoRolActor();
    
    public List<Parametro> buscarAnhos();
    
    public List<Parametro> buscarTipoInvestigacion();
    
    public List<Parametro> buscarTipoArchivo();
    
    public List<Parametro> buscarTipoSoporte();
}
