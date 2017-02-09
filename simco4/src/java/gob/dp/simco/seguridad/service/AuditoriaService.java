/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.seguridad.service;
import gob.dp.simco.seguridad.entity.Usuario;

/**
 *
 * @author Administrador
 */
public interface AuditoriaService {

    public void auditar(String codigoAccion, String detalle);

    public void auditar(String codigoAccion, String detalle, Usuario usuario);

}
