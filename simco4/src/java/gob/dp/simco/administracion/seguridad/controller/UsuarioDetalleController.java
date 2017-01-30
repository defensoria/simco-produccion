/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.administracion.seguridad.controller;

import gob.dp.simco.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.simco.administracion.seguridad.entity.Rol;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import gob.dp.simco.administracion.seguridad.entity.UsuarioLogin;
import gob.dp.simco.administracion.seguridad.service.RolService;
import gob.dp.simco.administracion.seguridad.service.UsuarioService;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import gob.dp.simco.comun.ConstantesUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Administrador
 */

@Named
@Scope("session")
public class UsuarioDetalleController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(UsuarioDetalleController.class);

    private Usuario usuario;

    private List<SelectItem> lstRoles;

    private String rolSeleccionado;

    private boolean verGuardar = true;

    private boolean habilitado = true;

    private List<String> lstRolesSeleccionados;

    private Part file1;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;
    
    public String verDetalleUsuario(UsuarioLogin usuarioLogin) {
        FiltroUsuario filter = new FiltroUsuario();
        filter.setCodigo(usuarioLogin.getCodigo());
        Integer contador = usuarioService.listaUsuarioCount(usuarioLogin.getCodigo());
        if (contador > 0) {
            usuario = usuarioService.buscarUsuario(filter).get(0);
            List<Rol> lstRolesTodos = rolService.buscarRol(new Rol());
            SelectItem item;
            this.lstRoles = new ArrayList<>();
            for (Rol obj : lstRolesTodos) {
                item = new SelectItem(obj.getCodigo(), obj.getNombre());
                this.getLstRoles().add(item);
            }
            Usuario filter2 = new Usuario();
            filter2.setCodigo(usuarioLogin.getCodigo());
            List<Rol> lstRolUsuario = rolService.buscarRolSegunUsuario(filter2);
            this.lstRolesSeleccionados = new ArrayList<>();
            for (Rol obj : lstRolUsuario) {
                rolSeleccionado = obj.getCodigo();
            }
        } else {
            usuario = new Usuario();
            usuario.setCodigo(usuarioLogin.getCodigo());
            usuario.setNombre(usuarioLogin.getNombre());
            usuario.setApellidoPaterno(usuarioLogin.getApellidoPaterno());
            usuario.setApellidoMaterno(usuarioLogin.getApellidoMaterno());
            usuario.setDni(usuarioLogin.getDocumento());
            List<Rol> lstRolesTodos = rolService.buscarRol(new Rol());
            SelectItem item;
            this.lstRoles = new ArrayList<>();
            for (Rol obj : lstRolesTodos) {
                item = new SelectItem(obj.getCodigo(), obj.getNombre());
                this.getLstRoles().add(item);
            }
            rolSeleccionado = null;
        }

        return "usuarioDetalle";
    }

    public String verDetallePerfil() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        Usuario usuarioSession = loginController.getUsuarioSesion();
        UsuarioLogin usuarioLogin = new UsuarioLogin();
        usuarioLogin.setCodigo(usuarioSession.getCodigo());
        verDetalleUsuario(usuarioLogin);
        return "perfil";
    }

    public String guardarUsuario() {
        Integer contador = usuarioService.listaUsuarioCount(usuario.getCodigo().toUpperCase());
        if(StringUtils.isBlank(rolSeleccionado)){
            msg.messageAlert("Debe seleccionar el rol del usuario", null);
            return null;
        }
        if (contador > 0) {
            guardar();
        } else {
            insertarUsuario();
        }
        FacesContext context = FacesContext.getCurrentInstance();
        BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
        busquedaUsuarioController.limpiarBusqueda();
        return "usuarioLista";
    }

    private void guardar() {
        Usuario filter = new Usuario();
        this.llenarFiltro(filter);
        try {
            List<Rol> lstRolSel = new ArrayList<>();
            Rol rol = new Rol(rolSeleccionado);
            lstRolSel.add(rol);
            usuarioService.modificarUsuario(filter, lstRolSel);
            msg.messageInfo("Se realizaron los cambios correctamente", null);
        } catch (Exception ex) {
            log.error("Ocurrió un error", ex);
        }
    }

    private void insertarUsuario() {
        Usuario filter = new Usuario();
        this.llenarFiltro(filter);
        try {
            List<Rol> lstRolSel = new ArrayList<>();
            Rol rol;
            //for (String codigo : this.getLstRolesSeleccionados()) {
            rol = new Rol();
            rol.setCodigo(rolSeleccionado);
            lstRolSel.add(rol);
            //}
            usuarioService.insertarUsuario(filter, lstRolSel);
            usuario.setCodigo(filter.getCodigo());
            Usuario usu = usuario;
            rolSeleccionado = null;
            usuario = new Usuario();
            FacesContext context = FacesContext.getCurrentInstance();
            BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
            msg.messageInfo("Se registro el usuario", null);
            busquedaUsuarioController.addUsuarioLista(usu);
        } catch (Exception e) {
            log.error("ERROR: insertarUsuario "+e);
        }
    }

    public String regresar() {
        return "usuarioLista";
    }

    private void llenarFiltro(Usuario filter) {

        if (usuario.getCodigo() != null && !usuario.getCodigo().trim().equals("")) {
            filter.setCodigo(usuario.getCodigo().trim());
        }
        if (usuario.getClave() != null && !usuario.getClave().trim().equals("")) {
            filter.setClave(usuario.getClave().trim());
        }
        if (usuario.getConfirmacionClave() != null && !usuario.getConfirmacionClave().trim().equals("")) {
            filter.setConfirmacionClave(usuario.getConfirmacionClave().trim());
        }
        if (usuario.getDni() != null && !usuario.getDni().trim().equals("")) {
            filter.setDni(usuario.getDni().trim());
        }
        if (usuario.getNombre() != null && !usuario.getNombre().trim().equals("")) {
            filter.setNombre(usuario.getNombre().trim());
        }
        if (usuario.getApellidoPaterno() != null && !usuario.getApellidoPaterno().trim().equals("")) {
            filter.setApellidoPaterno(usuario.getApellidoPaterno().trim());
        }
        if (usuario.getApellidoMaterno() != null && !usuario.getApellidoMaterno().trim().equals("")) {
            filter.setApellidoMaterno(usuario.getApellidoMaterno().trim());
        }
        if (usuario.getEmail() != null && !usuario.getEmail().trim().equals("")) {
            filter.setEmail(usuario.getEmail().trim());
        }
        if (usuario.getTelefonoMovil() != null && !usuario.getTelefonoMovil().trim().equals("")) {
            filter.setTelefonoMovil(usuario.getTelefonoMovil().trim());
        }
        if (usuario.getTelefonoFijo() != null && !usuario.getTelefonoFijo().trim().equals("")) {
            filter.setTelefonoFijo(usuario.getTelefonoFijo().trim());
        }
        
        if (StringUtils.isNotBlank(rolSeleccionado)) {
            if (StringUtils.equals(rolSeleccionado, "ROL0000001")) {
                filter.setCargo("Comisionado");
            }
            if (StringUtils.equals(rolSeleccionado, "ROL0000002")) {
                filter.setCargo("Jefe");
            }
            if (StringUtils.equals(rolSeleccionado, "ROL0000003")) {
                filter.setCargo("Comisionado");
            }
            if (StringUtils.equals(rolSeleccionado, "ROL0000004")) {
                filter.setCargo("Adjunto");
            }
            if (StringUtils.equals(rolSeleccionado, "ROL0000005")) {
                filter.setCargo("Practicante/Secigra");
            }
            if (StringUtils.equals(rolSeleccionado, "ROL0000006")) {
                filter.setCargo("Practicante/Secigra");
            }
            if (StringUtils.equals(rolSeleccionado, "ROL0000007")) {
                filter.setCargo("Comisionado");
            }
            if (StringUtils.equals(rolSeleccionado, "ROL0000008")) {
                filter.setCargo("Comisionado");
            }
        }
    }

    public void agregarImagen() {
        String nameArchive = getFilename(file1);
        String extencion = "";
        if (StringUtils.isNotBlank(nameArchive)) {
            switch (file1.getContentType()) {
                case "image/png":
                    extencion = ".png";
                    break;
                case "image/jpeg":
                    extencion = ".jpg";
                    break;
                case "image/gif":
                    extencion = ".gif";
                    break;
            }

            DateFormat fechaHora = new SimpleDateFormat("yyyyMMddHHmmss");
            String formato = fechaHora.format(new Date());
            String ruta = formato + extencion;
            File file = new File(ConstantesUtil.FILE_SYSTEM + ruta);
            try (InputStream input = file1.getInputStream()) {
                Files.copy(input, file.toPath());
            } catch (IOException ex) {
                log.error("agregarImagen()" + ex);
            }
            usuario.setRuta("/filesystem/" + ruta);
            usuarioService.modificarUsuarioSimple(usuario);
            verDetallePerfil();
        }
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<SelectItem> getLstRoles() {
        if (this.lstRoles == null) {
            this.lstRoles = new ArrayList<>();
        }
        return lstRoles;
    }

    public void setLstRoles(List<SelectItem> lstRoles) {
        this.lstRoles = lstRoles;
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

    public boolean isVerGuardar() {
        return verGuardar;
    }

    public void setVerGuardar(boolean verGuardar) {
        this.verGuardar = verGuardar;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public String getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public List<String> getLstRolesSeleccionados() {
        return lstRolesSeleccionados;
    }

    public void setLstRolesSeleccionados(List<String> lstRolesSeleccionados) {
        this.lstRolesSeleccionados = lstRolesSeleccionados;
    }
}
