/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.service;

import gob.dp.simco.registro.bean.FiltroParametro;
import gob.dp.simco.registro.entity.Parametro;
import java.util.List;
/**
 *
 * @author Administrador
 */
public interface ParametroService {
    
    public Parametro consultarParametro(FiltroParametro filtro);
    
    public Parametro consultarParametroValor(FiltroParametro filtro);
    
    public List<Parametro> buscarParametro(FiltroParametro filtro);
    
    public void crearParametro(Parametro obj);
    
    public void modificarParametro(Parametro obj);
    
    public void eliminarParametro(FiltroParametro filtro);
}
