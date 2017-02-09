/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.seguridad.controller;

import gob.dp.simco.seguridad.bean.FiltroUsuario;
import gob.dp.simco.seguridad.constantes.ConstantesAuditoria;
import gob.dp.simco.seguridad.entity.Usuario;
import gob.dp.simco.seguridad.entity.UsuarioLogin;
import gob.dp.simco.seguridad.service.AuditoriaService;
import gob.dp.simco.seguridad.service.UsuarioLoginService;
import gob.dp.simco.seguridad.service.UsuarioService;
import gob.dp.simco.comun.util.MEncript;
import gob.dp.simco.comun.util.SessionUtil;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import java.io.Serializable;
import javax.faces.context.FacesContext;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Administrador
 */
@Named
@Scope("session")
public class LoginController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(LoginController.class);

    private Usuario usuario;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    public String ingresarSistema() {
        try {
            if (usuario.getCodigo() != null) {
                usuario.setCodigo(usuario.getCodigo().toUpperCase());
            }
            FiltroUsuario filtro = new FiltroUsuario();
            filtro.setCodigo(usuario.getCodigo());
            filtro.setIncluirLstRol(true);
            filtro.setIncluirMapRol(true);
            filtro.setIncluirMapRecurso(true);
            UsuarioLogin login = new UsuarioLogin();
            login.setCodigo(usuario.getCodigo());
            String encPass = MEncript.getStringMessageDigest(usuario.getClave().trim());
            login.setClave(encPass);
            Integer val = usuarioLoginService.loginUsuario(login);
            if (val > 0) {
                Usuario objUsuario = usuarioService.consultarUsuario(filtro);
                if(objUsuario != null){
                    SessionUtil.setUsuario(objUsuario);
                    auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_LOGIN_CORRECTO, "Login correcto");
                    cargarMenu();
                    return "ingresarSistema";
                }
            } else {
                msg.messageAlert("El usuario o la contraseña no coinciden", null);
                auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_LOGIN_INCORRECTO, "Login incorrecto", usuario);
                return "login";
            }
        } catch (Exception ex) {
            log.error("Ocurrió un error", ex);
        }
        return null;
    }

    private void cargarMenu() {
        FacesContext context = FacesContext.getCurrentInstance();
        MenuController menuController = (MenuController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "menuController");
        menuController.cargarMenu();
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario getUsuario() {
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSesion() {
        return SessionUtil.getUsuario();
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }
    
    public void cambiarImagen(String ruta) {
        getUsuarioSesion().setRuta(ruta);
    }
}