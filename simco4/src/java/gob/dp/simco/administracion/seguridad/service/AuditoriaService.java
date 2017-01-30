/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.administracion.seguridad.service;
import gob.dp.simco.administracion.seguridad.entity.Usuario;

/**
 *
 * @author Administrador
 */
public interface AuditoriaService {

    public void auditar(String codigoAccion, String detalle);

    public void auditar(String codigoAccion, String detalle, Usuario usuario);

}
