/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.administracion.seguridad.service;

import gob.dp.simco.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.simco.administracion.seguridad.constantes.ConstantesAuditoria;
import gob.dp.simco.administracion.seguridad.dao.UsuarioDao;
import gob.dp.simco.administracion.seguridad.entity.Rol;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import gob.dp.simco.comun.MEncript;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Administrador
 */
@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private RolService rolService;

    @Autowired
    private RecursoService recursoService;

    @Autowired
    private AuditoriaService auditoriaService;

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    public void setRecursoService(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @Override
    public List<Usuario> buscarUsuario(FiltroUsuario filtro) {
        return usuarioDao.buscarUsuario(filtro);
    }

    @Override
    public void insertarUsuario(Usuario usuario, List<Rol> listaRol) {
        usuarioDao.insertarUsuario(usuario);
        auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_REGISTRAR_USUARIO, "Registrar Usu:" + usuario.getCodigo());
        rolService.asignarRolUsuario(usuario, listaRol);
    }

    @Override
    public void modificarUsuario(Usuario usuario, List<Rol> listaRol) {
        usuarioDao.modificarUsuario(usuario);

        /**
         * Auditoria
         */
        auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_MODIFICAR_USUARIO, "Mofificar Usu:" + usuario.getCodigo());
        rolService.asignarRolUsuario(usuario, listaRol);
    }

    @Override
    public void cambiarClave(Usuario usuario) {
        //String encPass = CryptoAES.getInstance().encriptar(usuario.getClave().trim());
        String encPass = MEncript.getStringMessageDigest(usuario.getClave().trim());
        usuario.setClave(encPass);
        usuarioDao.cambiarClaveUsuario(usuario);
        /**
         * Auditoria
         */
        auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_MODIFICAR_USUARIO, "Cambiar Clave:" + usuario.getCodigo());
    }

    @Override
    public Integer loginUsuario(Usuario usuario) {
        String encPass = MEncript.getStringMessageDigest(usuario.getClave().trim());
        usuario.setClave(encPass);
        return usuarioDao.loginUsuario(usuario);
    }

    @Override
    public Usuario consultarUsuario(FiltroUsuario filtro) {
        Usuario u = usuarioDao.consultarUsuario(filtro);
        if (u != null) {
            if (filtro.isIncluirLstRol()) {
                u.setListaRol(rolService.buscarRolSegunUsuario(u));
            }
            if (filtro.isIncluirMapRol()) {
                u.setMapRol(rolService.buscarMapRolSegunUsuario(u));
            }
            if (filtro.isIncluirMapRecurso()) {
                u.setMapRecurso(recursoService.buscarMapRecursoSegunUsuario(u));
            }
        }
        return u;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @Override
    public Integer getTotalBuscarUsuario(FiltroUsuario filtro) {
        return usuarioDao.getTotalBuscarUsuario(filtro);
    }

    @Override
    public List<Usuario> buscarUsuarioTotal() {
        return usuarioDao.buscarUsuarioTotal();
    }

    @Override
    public String autocompletarUsuario() {
        List<Usuario> lst = usuarioDao.buscarUsuarioTotal();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects = [");
        int i = 0;
        for(Usuario a:lst){
            if(i > 0)
            stringBuilder.append(",");    
            stringBuilder.append("{value: '").append(a.getCodigo()).append("' ,");
            stringBuilder.append("label: '").append(a.getNombre()+" "+a.getApellidoPaterno()+" "+a.getApellidoMaterno()).append("' ,");
            stringBuilder.append("desc: ").append("''").append(",");
            stringBuilder.append("icon: ").append("'' },");
        }
        stringBuilder.append("];");
        return stringBuilder.toString();
    }

    @Override
    public void modificarUsuarioSimple(Usuario usuario) {
        usuarioDao.modificarUsuario(usuario);
    }
    
    @Override
    public Integer listaUsuarioCount(String codigoUsuario) {
        return usuarioDao.listaUsuarioCount(codigoUsuario);
    }

}
