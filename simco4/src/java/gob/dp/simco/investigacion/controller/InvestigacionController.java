/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.investigacion.controller;

import gob.dp.simco.seguridad.controller.LoginController;
import gob.dp.simco.seguridad.entity.Usuario;
import gob.dp.simco.seguridad.service.UsuarioService;
import gob.dp.simco.investigacion.entity.Campo;
import gob.dp.simco.investigacion.entity.CampoDetalle;
import gob.dp.simco.investigacion.entity.HistorialActividad;
import gob.dp.simco.investigacion.entity.Investigacion;
import gob.dp.simco.investigacion.entity.Participacion;
import gob.dp.simco.investigacion.service.CampoDetalleService;
import gob.dp.simco.investigacion.service.CampoService;
import gob.dp.simco.investigacion.service.HistorialActividadService;
import gob.dp.simco.investigacion.service.InvestigacionService;
import gob.dp.simco.investigacion.service.ParticipacionService;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class InvestigacionController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(InvestigacionController.class);

    private String cadenaUsuarioTotal;

    private Usuario usuario;
    
    private CampoDetalle campoDetalle;

    private Campo campo;

    private List<Participacion> listaParticipantes;
    
    private List<HistorialActividad> listaHistorialActividad;
    
    private List<CampoDetalle> listaCampoDetalle;
    
    private List<Investigacion> listaInvestigacion;

    private List<Campo> listaCampos;

    private Investigacion investigacion;

    private HistorialActividad historialActividad;
    
    private Usuario usuarioSession;
    
    private Part file1;
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private InvestigacionService investigacionService;

    @Autowired
    private ParticipacionService participacionService;

    @Autowired
    private CampoService campoService;
    
    @Autowired
    private CampoDetalleService campoDetalleService;

    @Autowired
    private HistorialActividadService historialActividadService;

    public String cargarCrearInvestigacion() {
        investigacion = new Investigacion();
        campo = new Campo();
        campoDetalle = new CampoDetalle();
        cadenaUsuarioTotal = usuarioService.autocompletarUsuario();
        usuarioSession();
        inicializarParticipante();
        usuario = new Usuario();
        historialActividad = new HistorialActividad();
        listaCampos = null;
        listaHistorialActividad = null;
        listaCampoDetalle = null;
        return "investigacionCrear";
    }
    
    private void inicializarParticipante(){
        listaParticipantes = new ArrayList<>();
        Participacion participacion = new Participacion();
        participacion.setEstado("ACT");
        participacion.setFechaRegistro(new Date());
        participacion.setUsuRegistro(usuarioSession.getCodigo());
        participacion.setUsuarioCargo(usuarioSession.getCargo());
        participacion.setUsuarioNombre(usuarioSession.getNombre()+" "+usuarioSession.getApellidoPaterno()+" "+usuarioSession.getApellidoMaterno());
        participacion.setUsuarioCodigo(usuarioSession.getCodigo());
        listaParticipantes.add(participacion);
    }
    
    public String cargarSigues(){
        listaInvestigacion = null;
        Investigacion i = new Investigacion();
        usuarioSession();
        i.setUsuarioRegistro(usuarioSession.getCodigo());
        listaInvestigacion = investigacionService.investigacionBuscar(i);
        return "investigacionSigues";
    }
    
    public String irModificar(Investigacion inves){
        setInvestigacion(inves);
        listaParticipantes = participacionService.participacionBuscar(inves.getId());
        listarCampos();
        listarHistorialActividad();
        return "investigacionCrear";
    }
    
    private void usuarioSession(){
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }

    private void saveActividad(int tipo) {
        String descripcion = "";
        switch (tipo) {
            case 1:
                descripcion = "ha creado la investigacion";
                break;
            case 2:
                descripcion = "ha modificado la investigacion";
                break;
            case 3:
                descripcion = "ha agregado un nuevo registro sobre la investigacion ";
                break;
        }
        historialActividad.setDescripcion(descripcion);
        historialActividad.setUsuario(investigacion.getUsuarioRegistro());
        historialActividad.setFechaRegistro(new Date());
        historialActividad.setIdInvestigacion(investigacion.getId());
        historialActividadService.historialActividadInsert(historialActividad);
    }
    
    private void listarHistorialActividad(){
        listaHistorialActividad = historialActividadService.historialActividadBuscar(investigacion.getId());
    }
    
    public List<CampoDetalle> listarCampoDetalle(Long idCampo){
        listaCampoDetalle = campoDetalleService.campoDetalleBuscar(idCampo);
        return listaCampoDetalle;
    }
    
    public void comentarAdministrador(CampoDetalle campoDet){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String txtProperty = request.getParameter("descripcionADM"+campoDet.getId());
        campoDet.setDescripcionAdministrador(txtProperty);
        campoDetalleService.campoDetalleUpdate(campoDet);
    }
    
    public void guardarCampoDetalle(){
        try {
            DateFormat fechaHora = new SimpleDateFormat("yyyyMMddHHmmss");
            String formato = fechaHora.format(new Date());
            String ruta = FILE_SYSTEM+"investigacion"+campo.getId().toString();
            //+"/"+formato + getFilename(file1)
            File file = new File(ruta);
            if (!file.exists()) {
                file.mkdir();
            }
            String filenameArchive = getFilename(file1);
            file = new File(ruta+"/"+formato+filenameArchive);
            try (InputStream input = file1.getInputStream()) {
                Files.copy(input, file.toPath());
            }

            campoDetalle.setFechaRegistro(new Date());
            campoDetalle.setIdCampo(campo.getId());
            campoDetalle.setNombreArchivo(filenameArchive);
            campoDetalle.setNombreDocumento(formato+filenameArchive);
            campoDetalle.setRuta(campo.getId().toString()+"/"+formato+filenameArchive);
            campoDetalle.setUsuarioRegistro(investigacion.getUsuarioRegistro());
            campoDetalle.setNombreRegistro(historialActividad.getNombre());
            campoDetalleService.campoDetalleInsertar(campoDetalle);
               
            saveActividad(3);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    
    public void limpiarCampoDetalle(Campo c){
        setCampo(c);
        campoDetalle = new CampoDetalle();
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

    public void limpiarModalCampo() {
        campo = new Campo();
    }

    public void listarCampos() {
        listaCampos = campoService.campoxInvestigacionBuscar(investigacion.getId());
        List<Campo> list = new ArrayList<>();
        for(Campo c : listaCampos){
            c.setListaDetalle(listarCampoDetalle(c.getId()));
            list.add(c);
        }
        listaCampos = list;
    }

    public void saveCampo() {
        if (campo.getId() == null) {
            campo.setEstado("ACT");
            campo.setFechaRegistro(new Date());
            campo.setUsuarioRegistro(investigacion.getUsuarioRegistro());
            campo.setIdInvestigacion(investigacion.getId());
            campoService.campoInsertar(campo);
        } else {
            campoService.campoUpdate(campo);
        }
        listarCampos();
    }

    public void seteaCampo(Campo c) {
        setCampo(c);
    }

    public void saveInvestigacion() {
        if (investigacion.getId() == null) {
            investigacion.setFechaRegistro(new Date());
            investigacion.setEstado("ACT");
            investigacionService.investigacionInsertar(investigacion);
            saveActividad(1);
        } else {
            investigacion.setFechaModificacion(new Date());
            investigacion.setEstado("ACT");
            investigacionService.investigacionUpdate(investigacion);
            saveActividad(2);
        }
        for (Participacion participacion : listaParticipantes) {
            participacion.setIdInvestigacion(investigacion.getId());
            if (participacion.getId() == null) {
                participacionService.participacionInsertar(participacion);
            } else {
                participacion.setFechaModifica(new Date());
                participacionService.participacionUpdate(participacion);
            }
        }
        listarHistorialActividad();
        msg.messageInfo("Se registró la investigación", null);
    }

    public void addUsuario() {
        if(StringUtils.isNotBlank(usuario.getCodigo())){
            Participacion participacion = new Participacion();
            participacion.setEstado("ACT");
            participacion.setFechaRegistro(new Date());
            participacion.setUsuRegistro(investigacion.getUsuarioRegistro());
            participacion.setUsuarioNombre(usuario.getNombre());
            participacion.setUsuarioCodigo(usuario.getCodigo());
            int i = 0;
            for(Participacion p : listaParticipantes){
                if(StringUtils.equals(p.getUsuarioCodigo(), usuario.getCodigo())){
                    i++;
                }
            }
            if(i == 0){
                listaParticipantes.add(participacion);
            }
        }else{
            msg.messageAlert("Debe seleccionar un usuario", null);
        }
        usuario = new Usuario();
    }

    public void removeUsuario(Participacion partici) {
        if(!StringUtils.equals(usuarioSession.getCodigo(), partici.getUsuarioCodigo())){
            listaParticipantes.remove(partici);
            Participacion p = new Participacion();
            if (partici.getId() != null) {
                p.setEstado("INA");
                p.setId(partici.getId());
                participacionService.participacionUpdate(p);
            }
        }
    }

    public String getCadenaUsuarioTotal() {
        return cadenaUsuarioTotal;
    }

    public void setCadenaUsuarioTotal(String cadenaUsuarioTotal) {
        this.cadenaUsuarioTotal = cadenaUsuarioTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Investigacion getInvestigacion() {
        return investigacion;
    }

    public void setInvestigacion(Investigacion investigacion) {
        this.investigacion = investigacion;
    }

    public List<Participacion> getListaParticipantes() {
        return listaParticipantes;
    }

    public void setListaParticipantes(List<Participacion> listaParticipantes) {
        this.listaParticipantes = listaParticipantes;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public List<Campo> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(List<Campo> listaCampos) {
        this.listaCampos = listaCampos;
    }

    public HistorialActividad getHistorialActividad() {
        return historialActividad;
    }

    public void setHistorialActividad(HistorialActividad historialActividad) {
        this.historialActividad = historialActividad;
    }

    public List<HistorialActividad> getListaHistorialActividad() {
        return listaHistorialActividad;
    }

    public void setListaHistorialActividad(List<HistorialActividad> listaHistorialActividad) {
        this.listaHistorialActividad = listaHistorialActividad;
    }

    public CampoDetalle getCampoDetalle() {
        return campoDetalle;
    }

    public void setCampoDetalle(CampoDetalle campoDetalle) {
        this.campoDetalle = campoDetalle;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public List<CampoDetalle> getListaCampoDetalle() {
        return listaCampoDetalle;
    }

    public void setListaCampoDetalle(List<CampoDetalle> listaCampoDetalle) {
        this.listaCampoDetalle = listaCampoDetalle;
    }

    public List<Investigacion> getListaInvestigacion() {
        return listaInvestigacion;
    }

    public void setListaInvestigacion(List<Investigacion> listaInvestigacion) {
        this.listaInvestigacion = listaInvestigacion;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

}
