/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.seguimiento.dao;

import gob.dp.simco.seguimiento.entity.Alerta;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface AlertaDao {
    
    public void alertaInsertar(Alerta alerta);
    
    public List<Alerta> alertaBuscar(Long idSeguimientoAcuerdo);
    
    public Alerta alertaBuscarDetalle(Long id);
    
    public List<Alerta> alertaBuscarUsuario(Long idAcuerdo);
    
    public void alertaUpdate(long id);

}
