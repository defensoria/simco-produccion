/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.administracion.seguridad.service;

import gob.dp.simco.administracion.seguridad.constantes.ConstantesAuditoria;
import gob.dp.simco.administracion.seguridad.dao.AuditoriaDao;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import gob.dp.simco.comun.Auditoria;
import gob.dp.simco.comun.SessionUtil;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */
@Service("auditoriaService")
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaDao auditoriaDao;

    public void setAuditoriaDao(AuditoriaDao auditoriaDao) {
        this.auditoriaDao = auditoriaDao;
    }

    @Override
    public void auditar(String codigoAccion, String detalle) {

        Auditoria filter = new Auditoria();
        Usuario usuario = SessionUtil.getUsuario();
        filter.setUsuario(usuario);
        filter.setIp(usuario.getIp());
        filter.setCodigoAccion(codigoAccion);
        if (detalle != null) {
            if (detalle.trim().length() > ConstantesAuditoria.TAMANIO_MAXIMO_DETALLE) {
                filter.setDetalle(detalle.trim().substring(0, ConstantesAuditoria.TAMANIO_MAXIMO_DETALLE));
            } else {
                filter.setDetalle(detalle.trim());
            }
        }

        this.auditoriaDao.insertarAuditoria(filter);

    }

    @Override
    public void auditar(String codigoAccion, String detalle, Usuario usuario) {

        Auditoria filter = new Auditoria();
        filter.setUsuario(usuario);

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        usuario.setIp(request.getRemoteAddr());

        filter.setIp(usuario.getIp());
        filter.setCodigoAccion(codigoAccion);

        //Validando k no sobrepase el máximo permitido
        if (detalle != null) {
            if (detalle.trim().length() > ConstantesAuditoria.TAMANIO_MAXIMO_DETALLE) {
                filter.setDetalle(detalle.trim().substring(0, ConstantesAuditoria.TAMANIO_MAXIMO_DETALLE));
            } else {
                filter.setDetalle(detalle.trim());
            }
        }

        this.auditoriaDao.insertarAuditoria(filter);

    }
}
