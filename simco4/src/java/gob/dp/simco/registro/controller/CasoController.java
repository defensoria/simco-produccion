/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.controller;

import gob.dp.simco.analisis.controller.AnalisisController;
import gob.dp.simco.registro.vo.ReporteCasoVO;
import gob.dp.simco.parametro.constantes.InforVO;
import gob.dp.simco.parametro.service.CatalogoService;
import gob.dp.simco.seguridad.bean.FiltroUsuario;
import gob.dp.simco.seguridad.controller.LoginController;
import gob.dp.simco.seguridad.controller.MenuController;
import gob.dp.simco.seguridad.entity.Usuario;
import gob.dp.simco.seguridad.service.UsuarioService;
import gob.dp.simco.comun.entity.Departamento;
import gob.dp.simco.comun.entity.Distrito;
import gob.dp.simco.comun.entity.Provincia;
import gob.dp.simco.comun.service.UbigeoService;
import gob.dp.simco.registro.bean.AdjuntiaDefensorialVO;
import gob.dp.simco.registro.bean.FiltroParametro;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import gob.dp.simco.comun.util.ConstantesUtil;
import gob.dp.simco.registro.entity.Actividad;
import gob.dp.simco.registro.entity.ActividadCaso;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.entity.CasoActor;
import gob.dp.simco.registro.entity.CasoRegion;
import gob.dp.simco.registro.entity.Parametro;
import gob.dp.simco.registro.entity.PrimerNivel;
import gob.dp.simco.registro.entity.SegundoNivel;
import gob.dp.simco.registro.entity.TercerNivel;
import gob.dp.simco.registro.service.ActividadCasoService;
import gob.dp.simco.registro.service.ActividadService;
import gob.dp.simco.registro.service.ActorService;
import gob.dp.simco.registro.service.CasoActorService;
import gob.dp.simco.registro.service.CasoRegionService;
import gob.dp.simco.registro.service.CasoService;
import gob.dp.simco.registro.service.NivelService;
import gob.dp.simco.registro.service.ParametroService;
import gob.dp.simco.registro.type.MesType;
import gob.dp.simco.registro.type.TipologiaCasoType;
import gob.dp.simco.registro.vo.ReporteActorVO;
import gob.dp.simco.registro.vo.ReporteActuacionDefensorialVO;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
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
public class CasoController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(CasoController.class);

    private CasoBusquedaTemp casoBusquedaTemp;

    private String cadenaAdjuntiaAutocomplete;

    private Caso caso;

    private List<Caso> casos;

    private List<AdjuntiaDefensorialVO> listaAdjuntiasDefensoriales;

    private List<Actor> listaActoresXcaso;

    private List<Actividad> listaActividadesCaso;

    private List<Actividad> listaActividadesSinCaso;

    private List<Actividad> listaActividadesCasoAC;

    private List<Actividad> listaActividadesCasoAD;

    private List<CasoActor> listCasoActor;

    private Usuario usuarioSession;

    private int nroAcontecimiento = 0;

    private int nroActuacionesDefensoriales = 0;

    private boolean verTitulo = false;

    private List<SelectItem> listaDepartamento;

    private List<SelectItem> listaProvincia;

    private List<SelectItem> listaDistrito;

    private List<SelectItem> listaPrimerNivel;

    private List<SelectItem> listaSegundoNivel;

    private List<SelectItem> listaTercerNivel;

    private InforVO inforVO;

    JasperPrint jasperPrint;

    private CasoRegion casoRegion;

    private List<CasoRegion> listaCasoRegion;

    private Boolean esComisionadoRegistro;

    @Autowired
    private CasoService casoService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActividadCasoService actividadCasoService;

    @Autowired
    private CasoActorService casoActorService;

    @Autowired
    private UbigeoService ubigeoService;

    @Autowired
    private NivelService nivelService;

    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private CasoRegionService casoRegionService;

    @Autowired
    private UsuarioService usuarioService;

    public String cargarPagina() {
        try {
            caso = new Caso();
            casos = casoService.casoBuscar(caso);
        } catch (Exception ex) {
            caso = new Caso();
            log.error(ex.getMessage());
        }
        return "casoNuevo";
    }

    public String cargarPaginaCasosSigues() {
        try {
            usuarioSession();
            Caso c1 = new Caso();
            casos = casoService.casoBuscar(c1);
            for (Caso c : casos) {
                FiltroUsuario u = new FiltroUsuario();
                u.setCodigo(c.getUsuarioRegistro());
                Usuario u1 = usuarioService.consultarUsuario(u);
                c.setNombreComisionadoRegistro(u1.getNombre() + " " + u1.getApellidoPaterno() + " " + u1.getApellidoMaterno());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return "casoSigues";
    }

    public String cargarFichaCaso(Caso c) {
        caso = new Caso();
        caso = c;
        generarCadenaAdjuntia();
        return "casoFicha";
    }

    public String cargarCasoFicha() {
        FacesContext context = FacesContext.getCurrentInstance();
        ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
        listaAdjuntiasDefensoriales = new ArrayList<>();
        listaActividadesCaso = null;
        caso = new Caso();
        casoRegion = new CasoRegion();
        listaActividadesCasoAC = null;
        listaActividadesCasoAD = null;
        listCasoActor = null;
        nroAcontecimiento = 0;
        nroActuacionesDefensoriales = 0;
        listaActoresXcaso = null;
        generarCadenaAdjuntia();
        actorController.cadenaActores();
        cargarListaCasoRegionLimpiar();
        cargarInfo();
        setearEsComosionadoRegistro();
        return "casoNuevo";
    }

    private void setearEsComosionadoRegistro() {
        esComisionadoRegistro = StringUtils.equals(usuarioSession.getCodigo(), caso.getUsuarioRegistro());
    }

    public String setearFichaCaso(Caso c) {
        FacesContext context = FacesContext.getCurrentInstance();
        MenuController menuController = (MenuController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "menuController");
        ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
        menuController.cargarPagina(27);
        try {
            setCaso(c);
            contarActividades();
            if (StringUtils.isNotBlank(c.getAdjuntiaDefensorial())) {
                String[] adArray = c.getAdjuntiaDefensorial().split(";");
                for (String adArray1 : adArray) {
                    FiltroParametro fp = new FiltroParametro();
                    fp.setValorParametro(adArray1);
                    fp.setCodigoPadreParametro(140);
                    Parametro AD = parametroService.consultarParametroValor(fp);
                    String nombreParametro = AD.getNombreParametro();
                    AdjuntiaDefensorialVO adjuntiaDefensorialVO = new AdjuntiaDefensorialVO();
                    adjuntiaDefensorialVO.setNombre(nombreParametro);
                    adjuntiaDefensorialVO.setId(adArray1);
                    listaAdjuntiasDefensoriales.add(adjuntiaDefensorialVO);
                }
            }
            setCaso(c);
            generarCadenaAdjuntia();
            cargarNiveles();
            actorController.cadenaActores();
            cargarListaCasoRegion();
            cargarInfo();
            setearEsComosionadoRegistro();
        } catch (Exception ex) {
            log.error(ex.getCause());
        }
        return "casoNuevo";
    }

    public boolean addRegion() {
        try {
            if (StringUtils.isNotBlank(casoRegion.getIdDepartamento()) && !StringUtils.equals(casoRegion.getIdDepartamento(), "0")) {
                casoRegion.setEstado("ACT");
                casoRegion.setIdCaso(caso.getId());
                casoRegionService.casoRegionInsertar(casoRegion);
            } else {
                msg.messageAlert("Debe de ingresar como mínimo un departamento", null);
                return false;
            }
            if (StringUtils.isNotBlank(casoRegion.getIdDepartamento()) && !StringUtils.equals(casoRegion.getIdDepartamento(), "0")) {
                String depar = ubigeoService.departamentoOne(casoRegion.getIdDepartamento()).getDescripcion();
                casoRegion.setNombreDepartamento(depar);
            }
            if (StringUtils.isNotBlank(casoRegion.getIdProvincia()) && !StringUtils.equals(casoRegion.getIdProvincia(), "0")) {
                Provincia p = new Provincia();
                p.setIdDepartamento(casoRegion.getIdDepartamento());
                p.setIdProvincia(casoRegion.getIdProvincia());
                String prov = ubigeoService.provinciaOne(p).getDescripcion();
                casoRegion.setNombreProvincia(prov);
            }
            if (StringUtils.isNotBlank(casoRegion.getIdDistrito()) && !StringUtils.equals(casoRegion.getIdDistrito(), "0")) {
                Distrito d = new Distrito();
                d.setIdDepartamento(casoRegion.getIdDepartamento());
                d.setIdProvincia(casoRegion.getIdProvincia());
                d.setIdDistrito(casoRegion.getIdDistrito());
                String dist = ubigeoService.distritoOne(d).getDescripcion();
                casoRegion.setNombreDistrito(dist);
            }
            listaCasoRegion.add(casoRegion);
            casoRegion = new CasoRegion();
        } catch (Exception e) {
            log.error(e);
        }
        return true;
    }

    private void updateListaRegion() {
        for (CasoRegion casoRe : listaCasoRegion) {
            casoRe.setIdCaso(caso.getId());
            casoRegionService.casoRegionInsertar(casoRe);
        }
    }

    private void updateListasActividades() {
        if (listaActividadesCasoAD != null) {
            for (Actividad a : listaActividadesCasoAD) {
                ActividadCaso ac = new ActividadCaso();
                ac.setActividad(a);
                ac.setCaso(caso);
                ac.setEstado("ACT");
                updateRelacionActividadCaso(a);
                actividadCasoService.actividadCasoInsertar(ac);
            }
        }
        if (listaActividadesCasoAC != null) {
            for (Actividad a : listaActividadesCasoAC) {
                ActividadCaso ac = new ActividadCaso();
                ac.setActividad(a);
                ac.setCaso(caso);
                ac.setEstado("ACT");
                updateRelacionActividadCaso(a);
                actividadCasoService.actividadCasoInsertar(ac);
            }
        }
    }

    private void updateRelacionActividadCaso(Actividad a) {
        ActividadCaso ac = new ActividadCaso();
        ac.setActividad(a);
        Caso c = new Caso();
        c.setId(a.getIdCaso());
        ac.setCaso(c);
        ac.setEstado("INA");
        actividadCasoService.actividadesCasoUpdate(ac);
    }

    public void removeRegion(long idCasoRegion) {
        casoRegionService.casoRegionUpdate(idCasoRegion);
        cargarListaCasoRegion();
    }

    public void ubigeoPrincipal(CasoRegion region) {
        try {
            caso.setIdDepartamento(region.getIdDepartamento());
            caso.setIdProvincia(region.getIdProvincia());
            caso.setIdDistrito(region.getIdDistrito());
            for (CasoRegion cr : listaCasoRegion) {
                if (Objects.equals(cr.getId(), region.getId())) {
                    cr.setPrincipal("A");
                } else {
                    cr.setPrincipal("I");
                }
            }
        } catch (Exception ex) {
            log.error(ex);
        }

    }

    private void cargarListaCasoRegion() {
        try {
            listaCasoRegion = new ArrayList<>();
            List<CasoRegion> list = casoRegionService.casoRegionBuscar(caso.getId());
            for (CasoRegion cr : list) {
                if (StringUtils.isNotBlank(cr.getIdDepartamento()) && !StringUtils.equals(cr.getIdDepartamento(), "0")) {
                    String depar = ubigeoService.departamentoOne(cr.getIdDepartamento()).getDescripcion();
                    cr.setNombreDepartamento(depar);
                }
                if (StringUtils.isNotBlank(cr.getIdProvincia()) && !StringUtils.equals(cr.getIdProvincia(), "0")) {
                    Provincia p = new Provincia();
                    p.setIdDepartamento(cr.getIdDepartamento());
                    p.setIdProvincia(cr.getIdProvincia());
                    String prov = ubigeoService.provinciaOne(p).getDescripcion();
                    cr.setNombreProvincia(prov);
                } else {
                    cr.setNombreProvincia("");
                }
                if (StringUtils.isNotBlank(cr.getIdDistrito()) && !StringUtils.equals(cr.getIdDistrito(), "0")) {
                    Distrito d = new Distrito();
                    d.setIdDepartamento(cr.getIdDepartamento());
                    d.setIdProvincia(cr.getIdProvincia());
                    d.setIdDistrito(cr.getIdDistrito());
                    String dist = ubigeoService.distritoOne(d).getDescripcion();
                    cr.setNombreDistrito(dist);
                } else {
                    cr.setNombreDistrito("");
                }
            }
            listaCasoRegion.addAll(list);
            casoRegion = new CasoRegion();
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void cargarListaCasoRegionLimpiar() {
        try {
            listaCasoRegion = new ArrayList<>();
            casoRegion = new CasoRegion();
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void cargarInfo() {
        inforVO = new InforVO();
        inforVO.setListaEstadoCaso(catalogoService.parametroPorPadre(120));
        inforVO.setListaFaseCaso(catalogoService.parametroPorPadre(210));
        inforVO.setListaTipoCaso(catalogoService.parametroPorPadre(90));
        inforVO.setListaCompetenciaCaso(catalogoService.parametroPorPadre(110));
    }

    public void eliminarActividadCaso(Actividad a) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            String tipo = a.getTipo();
            ActividadCaso actividCaso = new ActividadCaso();
            actividCaso.setActividad(a);
            actividCaso.setCaso(caso);
            actividadCasoService.actividadCasoDelete(actividCaso);
            contarActividades();
            if (tipo.equals("AD")) {
                registroController.removeAcontecimientoVinculadoFichaAD(a);
                msg.messageInfo("Se eliminó la actuación defensorial", null);
            } else {
                registroController.removeAcontecimientoVinculadoFichaAC(a);
                msg.messageInfo("Se eliminó el acontecimiento", null);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void cargarNiveles() {
        if (StringUtils.isNoneBlank(caso.getSubTipo())) {
            cargarPrimerNivel();
            cargarSegundoNivel();
            cargarTercerNivel();
        }
    }

    private void cargarPrimerNivel() {
        List<PrimerNivel> list = nivelService.listarPrimerNivel(caso.getSubTipo());
        listaPrimerNivel = new ArrayList<>();
        if (list.size() > 0) {
            for (PrimerNivel nivel : list) {
                listaPrimerNivel.add(new SelectItem(nivel.getId(), nivel.getNombre()));
            }
        }
    }

    private void cargarSegundoNivel() {
        List<SegundoNivel> list = nivelService.segundoNivelBuscar(caso.getSubTipo());
        listaSegundoNivel = new ArrayList<>();
        if (list.size() > 0) {
            for (SegundoNivel nivel : list) {
                listaSegundoNivel.add(new SelectItem(nivel.getId(), nivel.getNombre()));
            }
        }
    }

    private void cargarTercerNivel() {
        List<TercerNivel> list = nivelService.tercerNivelBuscar(caso.getSubTipo());
        listaTercerNivel = new ArrayList<>();
        if (list.size() > 0) {
            for (TercerNivel nivel : list) {
                listaTercerNivel.add(new SelectItem(nivel.getId(), nivel.getNombre()));
            }
        }
    }

    public String cargarPanel(long idCaso) {
        try {
            caso = new Caso();
            caso = casoService.casoBuscarOne(idCaso);
            setearFichaCaso(caso);
        } catch (Exception ex) {
            log.error(ex.getCause());
        }
        return "casoNuevo";
    }

    public void contarActividades() {
        listaAdjuntiasDefensoriales = new ArrayList<>();
        listaActividadesCasoAC = new ArrayList<>();
        listaActividadesCasoAD = new ArrayList<>();
        try {
            listaActividadesCaso = actividadService.actividadxCasoBuscarTotal(caso.getId());
            nroAcontecimiento = 0;
            nroActuacionesDefensoriales = 0;
            validaListasActoresCasos(caso.getId());
            listaActoresXcaso = actorService.actorxCasoBuscar(caso.getId());
            for (Actividad a : listaActividadesCaso) {
                if (StringUtils.equals(a.getTipo(), "AC")) {
                    listaActividadesCasoAC.add(a);
                    nroAcontecimiento++;
                }
                if (StringUtils.equals(a.getTipo(), "AD")) {
                    listaActividadesCasoAD.add(a);
                    nroActuacionesDefensoriales++;
                }
            }
        } catch (Exception ex) {
            log.error(ex.getCause());
        }
    }

    private void validaListasActoresCasos(long idCaso) {
        listaActoresXcaso = null;
        listCasoActor = null;
        listaActoresXcaso = actorService.actorxCasoBuscar(caso.getId());
        listCasoActor = casoActorService.casoActorBuscar(caso.getId());
        List<CasoActor> list = new ArrayList<>();
        List<CasoActor> listRemove = new ArrayList<>();
        if (listaActoresXcaso.size() > 0) {
            if (listCasoActor.size() > 0) {
                //agregar
                for (Actor a : listaActoresXcaso) {
                    int i = 1;
                    for (CasoActor ca : listCasoActor) {
                        if (Objects.equals(ca.getIdActor(), a.getId())) {
                            i = i * (-1);
                        }
                    }
                    if (i > 0) {
                        CasoActor ca1 = new CasoActor();
                        ca1.setIdCaso(idCaso);
                        ca1.setIdActor(a.getId());
                        ca1.setEstado("ACT");
                        ca1.setFechaRegistro(new Date());
                        list.add(ca1);
                    }
                }

                for (CasoActor ca : listCasoActor) {
                    int i = 1;
                    for (Actor a : listaActoresXcaso) {
                        if (Objects.equals(ca.getIdActor(), a.getId())) {
                            i = i * (-1);
                        }
                    }
                    if (i > 0) {
                        CasoActor ca0 = new CasoActor();
                        ca0.setIdCaso(idCaso);
                        ca0.setIdActor(ca.getIdActor());
                        ca0.setEstado("INA");
                        listRemove.add(ca0);
                    }
                }

                if (list.size() > 0) {
                    for (CasoActor ca2 : list) {
                        int val = 0;
                        val = casoActorService.casoActorBuscarCount(ca2);
                        if (val == 0) {
                            casoActorService.casoActorInsertar(ca2);
                        } else {
                            ca2.setEstado("ACT");
                            casoActorService.casoUpdate(ca2);
                        }
                    }
                }
                if (listRemove.size() > 0) {
                    for (CasoActor ca2 : listRemove) {
                        casoActorService.casoUpdate(ca2);
                    }
                }
            } else {
                for (Actor a : listaActoresXcaso) {
                    CasoActor casoActor = new CasoActor();
                    casoActor.setIdCaso(idCaso);
                    casoActor.setIdActor(a.getId());
                    casoActor.setEstado("ACT");
                    casoActor.setFechaRegistro(new Date());
                    casoActorService.casoActorInsertar(casoActor);
                }
            }
        }
        listCasoActor = casoActorService.casoActorBuscarComplete(idCaso);
    }

    public void initJasper() throws JRException {
        List<ReporteCasoVO> lista = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ReporteCasoVO vo = new ReporteCasoVO();
        vo.setNombre(caso.getNombre());
        vo.setCodigo(caso.getCodigo());
        vo.setDescripcion(caso.getDescripcionPreliminar());
        vo.setJustificacion(caso.getJustificacion());
        vo.setObservacion(caso.getObservaciones());
        Parametro parametro;
        parametro = parametroService.consultarParametroValor(new FiltroParametro(120, caso.getTipoEstado()));
        vo.setEstado(parametro == null ? "" : parametro.getNombreParametro());
        parametro = parametroService.consultarParametroValor(new FiltroParametro(110, caso.getTipoAsunto()));
        vo.setCompetenciaEstatal(parametro == null ? "" : parametro.getNombreParametro());
        parametro = parametroService.consultarParametroValor(new FiltroParametro(210, caso.getTipoDialogo()));
        vo.setFace(parametro == null ? "" : parametro.getNombreParametro());
        parametro = parametroService.consultarParametroValor(new FiltroParametro(240, caso.getTipoMomentoDialogo()));
        vo.setMomentoDialogo(parametro == null ? "" : parametro.getNombreParametro());
        parametro = parametroService.consultarParametroValor(new FiltroParametro(220, caso.getTipoMecanismo()));
        vo.setMecanismoDialogo(parametro == null ? "" : parametro.getNombreParametro());
        parametro = parametroService.consultarParametroValor(new FiltroParametro(230, caso.getTipoParticipacionCaso()));
        vo.setParticipacionDialogo(parametro == null ? "" : parametro.getNombreParametro());
        parametro = parametroService.consultarParametroValor(new FiltroParametro(90, caso.getTipo()));
        vo.setTipologia(parametro == null ? "" : parametro.getNombreParametro());
        parametro = parametroService.consultarParametroValor(new FiltroParametro(130, caso.getSubTipo() == null ? "" : caso.getSubTipo()));
        vo.setTipoActividad(parametro == null ? "" : parametro.getNombreParametro());
        vo.setPrimerNivel(nivelService.primerNivelOne(caso.getPrimerNivel()));
        vo.setSegundoNivel(nivelService.segundoNivelOne(caso.getSegundoNivel()));
        vo.setTercerNivel(nivelService.tercerNivelOne(caso.getTercerNivel()));

        List<ReporteActorVO> ravos = new ArrayList<>();
        int c = 0;
        if (listCasoActor != null) {
            for (CasoActor a : listCasoActor) {
                ReporteActorVO ravo = new ReporteActorVO();
                String nombre = a.getActor().getNombre() + " " + (a.getActor().getApellidoPat() == null ? " " : a.getActor().getApellidoPat()) + " " + (a.getActor().getApellidoMat() == null ? " " : a.getActor().getApellidoMat());
                switch (a.getTipo()) {
                    case "01":
                        nombre += " (Primario)";
                        break;
                    case "02":
                        nombre += " (Secundario)";
                        break;
                    case "03":
                        nombre += " (Terciario)";
                        break;
                    default:
                        nombre += " (Sin clasificacion)";
                        break;
                }
                ravo.setNumero(Integer.toString(++c) + ".");
                ravo.setNombreCompleto(nombre);
                ravos.add(ravo);
            }
        }
        vo.setActores(ravos);
        List<ReporteActuacionDefensorialVO> radvos = new ArrayList<>();
        if (listaActividadesCasoAD != null) {
            for (Actividad a : listaActividadesCasoAD) {
                ReporteActuacionDefensorialVO radvo = new ReporteActuacionDefensorialVO();
                radvo.setComentarios(a.getComentario());
                radvo.setDescripcion(a.getDescripcion());
                radvo.setNombre(a.getNombre());
                radvo.setFechaInicio(a.getFechaRealizacionIni() == null ? "" : simpleDateFormat.format(a.getFechaRealizacionIni()));
                radvo.setFechaFin(a.getFechaRealizacionFin() == null ? "" : simpleDateFormat.format(a.getFechaRealizacionFin()));
                Departamento departamen = ubigeoService.departamentoOne(a.getIdDepartamento());
                Provincia p = new Provincia();
                p.setIdDepartamento(a.getIdDepartamento());
                p.setIdProvincia(a.getIdProvincia());
                Provincia provin = ubigeoService.provinciaOne(p);
                Distrito d = new Distrito();
                d.setIdDepartamento(a.getIdDepartamento());
                d.setIdProvincia(a.getIdProvincia());
                d.setIdDistrito(a.getIdDistrito());
                Distrito distri = ubigeoService.distritoOne(d);
                radvo.setDepartamento(departamen == null ? "" : departamen.getDescripcion());
                radvo.setProvincia(provin == null ? "" : provin.getDescripcion());
                radvo.setDistrito(distri == null ? "" : distri.getDescripcion());
                //radvo.setImagePath(a.getRuta() == null ? null : hostAddress().concat(a.getRuta()));
                radvo.setImagePath(a.getRuta() == null ? null : ConstantesUtil.FILE_DONWLOAD+"/"+a.getRuta().trim());
                radvos.add(radvo);
            }
            vo.setActuacionesDefensoriales(radvos);
        }

        List<ReporteActuacionDefensorialVO> radvos2 = new ArrayList<>();
        if (listaActividadesCasoAC != null) {
            for (Actividad a : listaActividadesCasoAC) {
                ReporteActuacionDefensorialVO radvo = new ReporteActuacionDefensorialVO();
                radvo.setComentarios(a.getComentario());
                radvo.setDescripcion(a.getDescripcion());
                radvo.setNombre(a.getNombre());
                radvo.setFechaInicio(a.getFechaRealizacionIni() == null ? "" : simpleDateFormat.format(a.getFechaRealizacionIni()));
                radvo.setFechaFin(a.getFechaRealizacionFin() == null ? "" : simpleDateFormat.format(a.getFechaRealizacionFin()));
                Departamento departamen = ubigeoService.departamentoOne(a.getIdDepartamento());
                Provincia p = new Provincia();
                p.setIdDepartamento(a.getIdDepartamento());
                p.setIdProvincia(a.getIdProvincia());
                Provincia provin = ubigeoService.provinciaOne(p);
                Distrito d = new Distrito();
                d.setIdDepartamento(a.getIdDepartamento());
                d.setIdProvincia(a.getIdProvincia());
                d.setIdDistrito(a.getIdDistrito());
                Distrito distri = ubigeoService.distritoOne(d);
                radvo.setDepartamento(departamen == null ? "" : departamen.getDescripcion());
                radvo.setProvincia(provin == null ? "" : provin.getDescripcion());
                radvo.setDistrito(distri == null ? "" : distri.getDescripcion());
                radvo.setImagePath(a.getRuta() == null ? null : ConstantesUtil.FILE_DONWLOAD+"/"+a.getRuta().trim());
                radvos2.add(radvo);
            }
            vo.setAcontecimientos(radvos2);
        }
        vo.setImagePath(retornaRutaPath().concat("/images/logoPlanIntervencion.png"));
        vo.setRutaReporte1(retornaRutaPath().concat("/jasper/casoAcontecimientos.jasper"));
        vo.setRutaReporte2(retornaRutaPath().concat("/jasper/casoActor.jasper"));
        vo.setRutaReporte3(retornaRutaPath().concat("/jasper/casoActuacionDefensorial.jasper"));
        vo.setRutaReporte4(retornaRutaPath().concat("/jasper/casoUbigeo.jasper"));
        if (listaCasoRegion != null) {
            if (listaCasoRegion.size() > 0) {
                vo.setListaCasoRegiones(listaCasoRegion);
            }
        }
        lista.add(vo);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);

        jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/documentoCaso.jasper"), new HashMap(), beanCollectionDataSource);
    }

    public void pdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasper();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_caso.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    @SuppressWarnings("static-access")
    public void docx() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasper();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_documento_caso.docx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    private void generarCadenaAdjuntia() {
        try {
            cadenaAdjuntiaAutocomplete = casoService.adjuntiaBuscarAutocomplete();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void cancelar() {
        caso = new Caso();
    }

    public boolean registrarCaso() {
        Long idCasoOld = caso.getId();
        if (StringUtils.equals(caso.getTipoEstado(), "0")) {
            msg.messageAlert("Debe ingresar el estado del caso", null);
            return false;
        }
        if (StringUtils.equals(caso.getTipo(), "0")) {
            msg.messageAlert("Debe ingresar la tipología del caso", null);
            return false;
        }
        if (caso.getFechaInicio() == null) {
            msg.messageAlert("Debe ingresar la fecha de inicio del caso", null);
            return false;
        }
        usuarioSession();
        try {
            StringBuilder sb = new StringBuilder();
            for (AdjuntiaDefensorialVO o : listaAdjuntiasDefensoriales) {
                sb.append(o.getId()).append(";");
            }
            caso.setAdjuntiaDefensorial(sb.toString());
            if (caso.getVersion() == null) {
                caso.setCreacion(new Date());
                caso.setVersion(1);
                caso.setUsuarioRegistro(usuarioSession.getCodigo());
            } else {
                caso.setVersion(caso.getVersion() + 1);
                casoService.casoUpdateIndicador(caso);
            }
            caso.setIndicador("A");
            caso.setUsuarioModificacion(usuarioSession.getCodigo());
            caso.setModificacion(new Date());
            caso.setEstadoRegistro("PEN");
            definirFechaPublicacion();
            ordenarParametros();
            casoService.casoNuevo(caso);
            /*if(StringUtils.equals(caso.getTipoEstado(), "02"))
                enviarSolicitudAprobacion();*/
            msg.messageInfo("Se registró correctamente el caso.", null);
            if (listaCasoRegion.size() > 0) {
                updateListaRegion();
            }
            updateListasActividades();
            actualizarActoresFicha();
            validaListasActoresCasos(caso.getId());
            setearEsComosionadoRegistro();
            if(idCasoOld != null)
                generarVersionAnalisis(idCasoOld, caso.getId());
        } catch (Exception ex) {
            log.error(ex);
        }
        return true;
    }

    private void generarVersionAnalisis(Long idCasoOld, Long idCasoNew){
        FacesContext context = FacesContext.getCurrentInstance();
        AnalisisController analisisController = (AnalisisController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "analisisController");
        analisisController.replicarAnalisis(idCasoOld, idCasoNew);
    }
    

    public String aprobarSolicitudAprobacion() {
        if (caso.getIndAprobado() == null) {
            if (StringUtils.isBlank(caso.getCodigo())) {
                caso.setCodigo(generarCodigoCaso());
                caso.setTipoEstado("04");
                Caso c = new Caso();
                c.setIndAprobado("A");
                c.setUsuAprobado(usuarioSession.getCodigo());
                c.setFechaAprobado(new Date());
                c.setCodigo(caso.getCodigo());
                c.setTipoEstado(caso.getTipoEstado());
                c.setId(caso.getId());
                c.setFechaPublicacion(new Date());
                casoService.casoUpdateAprobar(c);
            }
        }
        return cargarPaginaCasosSigues();
    }

    private void ordenarParametros() {
        if (!caso.getTipoEstado().equals("04")) {
            caso.setTipoDialogo("0");
            caso.setTipoMecanismo("0");
            caso.setTipoMomentoDialogo("0");
            caso.setTipoParticipacionCaso("0");
        }
        if (!caso.getTipoDialogo().equals("05")) {
            caso.setTipoMecanismo("0");
            caso.setTipoMomentoDialogo("0");
            caso.setTipoParticipacionCaso("0");
        }
        if (!caso.getTipo().equals("09")) {
            caso.setSubTipo("0");
            caso.setPrimerNivel("0");
            caso.setSegundoNivel("0");
            caso.setTercerNivel("0");
        }
        if (!caso.getSubTipo().equals("02") && !caso.getSubTipo().equals("03") && !caso.getSubTipo().equals("01")) {
            caso.setPrimerNivel("0");
            caso.setSegundoNivel("0");
            caso.setTercerNivel("0");
        }
    }

    private void definirFechaPublicacion() {
        if (caso.getFechaPublicacion() == null) {
            if (caso.getTipoEstado().equals("04") || caso.getTipoEstado().equals("05") || caso.getTipoEstado().equals("06")) {
                caso.setFechaPublicacion(new Date());
            }
        }
    }

    private void actualizarActoresFicha() {
        if (listCasoActor != null) {
            for (CasoActor ca : listCasoActor) {
                if (ca.getTipo().length() == 2) {
                    ca.setIdCaso(caso.getId());
                    casoActorService.casoActorInsertar(ca);
                }
            }
            listCasoActor = casoActorService.casoActorBuscarComplete(caso.getId());
        }
    }

    private void usuarioSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }

    public boolean generarListaAdjuntias() {
        if (caso.getAdjuntiaDefensorial() != null) {
            if (StringUtils.equals(caso.getAdjuntiaDefensorial(), "")) {
                caso.setNombreAdjuntiaDefensorial(null);
                caso.setAdjuntiaDefensorial(null);
                return false;
            }

            for (AdjuntiaDefensorialVO advo : listaAdjuntiasDefensoriales) {

                if (StringUtils.equals(advo.getId(), caso.getAdjuntiaDefensorial())) {
                    caso.setNombreAdjuntiaDefensorial(null);
                    caso.setAdjuntiaDefensorial(null);
                    return false;
                }
            }
            AdjuntiaDefensorialVO vo = new AdjuntiaDefensorialVO();
            vo.setId(caso.getAdjuntiaDefensorial());
            vo.setNombre(caso.getNombreAdjuntiaDefensorial());
            listaAdjuntiasDefensoriales.add(vo);
            caso.setNombreAdjuntiaDefensorial(null);
            caso.setAdjuntiaDefensorial(null);
        }
        return true;
    }

    public void removeListaAdjuntias(AdjuntiaDefensorialVO advo) {
        listaAdjuntiasDefensoriales.remove(advo);
    }

    private String generarCodigoCaso() {
        SimpleDateFormat simpleDateFormatMes = new SimpleDateFormat("MM");
        SimpleDateFormat simpleDateFormatAnho = new SimpleDateFormat("yyyy");
        String mes = simpleDateFormatMes.format(caso.getCreacion());
        String anho = simpleDateFormatAnho.format(caso.getCreacion());
        String numero = String.format("%4s", casoService.casoCodigoGenerado().toString()).replace(' ', '0');
        String mesDescripcion = MesType.get(mes).getDescription();
        String tipo = TipologiaCasoType.tipoClasificacionNombre(caso.getTipo());
        return anho + mesDescripcion + numero + "-" + tipo;
    }

    public void cargarListaActividadesSinCaso(int tipo) {
        verTitulo = tipo == 1;
        List<Actividad> lista = new ArrayList<>();
        listaActividadesSinCaso = actividadService.actividadBusquedaSinCaso(tipo);
        for (Actividad a : listaActividadesSinCaso) {
            if (StringUtils.equals(a.getUsuarioRegistro(), usuarioSession.getCodigo())) {
                lista.add(a);
            }
        }
        listaActividadesSinCaso = lista;
    }

    public void vincularActividadCaso(Actividad activida) {
        ActividadCaso ac = new ActividadCaso();
        String tipo = activida.getTipo();
        try {
            ac.setActividad(activida);
            ac.setCaso(caso);
            ac.setEstado("ACT");
            actividadCasoService.actividadCasoInsertar(ac);
            contarActividades();
            if (tipo.equals("AD")) {
                msg.messageInfo("Se ha vinculado la actuación defensorial", null);
            } else {
                msg.messageInfo("Se ha vinculado el acontecimiento", null);
            }
        } catch (Exception ex) {
            log.error(ex.getCause());
        }
    }

    public void listarActividadesxCaso() {
        try {
            listaActividadesCaso = actividadService.actividadxCasoBuscarTotal(caso.getId());
        } catch (Exception ex) {
            log.error(ex.getCause());
        }
    }

    public void comboProvincia() {
        listaProvincia = new ArrayList<>();
        listaDistrito = new ArrayList<>();
        String id = casoRegion.getIdDepartamento();
        if (StringUtils.equals(id, "0")) {
            listaDistrito.clear();
        } else {
            List<Provincia> list = ubigeoService.provinciaLista(id);
            if (list.size() > 0) {
                for (Provincia provincia : list) {
                    listaProvincia.add(new SelectItem(provincia.getIdProvincia(), provincia.getDescripcion()));
                }
            }
        }
    }

    public void comboDistrito() {
        listaDistrito = new ArrayList<>();
        String idDepartamento = casoRegion.getIdDepartamento();
        String idProvincia = casoRegion.getIdProvincia();
        if (StringUtils.equals(idDepartamento, "0") || StringUtils.equals(idProvincia, "0")) {
            listaDistrito.clear();
        } else {
            Distrito d = new Distrito();
            d.setIdDepartamento(idDepartamento);
            d.setIdProvincia(idProvincia);
            List<Distrito> list = ubigeoService.distritoLista(d);
            if (list.size() > 0) {
                for (Distrito distrito : list) {
                    listaDistrito.add(new SelectItem(distrito.getIdDistrito(), distrito.getDescripcion()));
                }
            }
        }
    }

    public CasoBusquedaTemp getCasoBusquedaTemp() {
        if (casoBusquedaTemp == null) {
            casoBusquedaTemp = new CasoBusquedaTemp();
        }
        return casoBusquedaTemp;
    }

    public void setCasoBusquedaTemp(CasoBusquedaTemp casoBusquedaTemp) {
        this.casoBusquedaTemp = casoBusquedaTemp;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        caso.setTipoCaso(caso.getTipo());
        this.caso = caso;
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public String getCadenaAdjuntiaAutocomplete() {
        return cadenaAdjuntiaAutocomplete;
    }

    public void setCadenaAdjuntiaAutocomplete(String cadenaAdjuntiaAutocomplete) {
        this.cadenaAdjuntiaAutocomplete = cadenaAdjuntiaAutocomplete;
    }

    public List<AdjuntiaDefensorialVO> getListaAdjuntiasDefensoriales() {
        return listaAdjuntiasDefensoriales;
    }

    public void setListaAdjuntiasDefensoriales(List<AdjuntiaDefensorialVO> listaAdjuntiasDefensoriales) {
        this.listaAdjuntiasDefensoriales = listaAdjuntiasDefensoriales;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public List<Actividad> getListaActividadesCaso() {
        return listaActividadesCaso;
    }

    public void setListaActividadesCaso(List<Actividad> listaActividadesCaso) {
        this.listaActividadesCaso = listaActividadesCaso;
    }

    public int getNroAcontecimiento() {
        return nroAcontecimiento;
    }

    public void setNroAcontecimiento(int nroAcontecimiento) {
        this.nroAcontecimiento = nroAcontecimiento;
    }

    public int getNroActuacionesDefensoriales() {
        return nroActuacionesDefensoriales;
    }

    public void setNroActuacionesDefensoriales(int nroActuacionesDefensoriales) {
        this.nroActuacionesDefensoriales = nroActuacionesDefensoriales;
    }

    public List<Actor> getListaActoresXcaso() {
        return listaActoresXcaso;
    }

    public void setListaActoresXcaso(List<Actor> listaActoresXcaso) {
        this.listaActoresXcaso = listaActoresXcaso;
    }

    public List<Actividad> getListaActividadesSinCaso() {
        return listaActividadesSinCaso;
    }

    public void setListaActividadesSinCaso(List<Actividad> listaActividadesSinCaso) {
        this.listaActividadesSinCaso = listaActividadesSinCaso;
    }

    public List<Actividad> getListaActividadesCasoAC() {
        return listaActividadesCasoAC;
    }

    public void setListaActividadesCasoAC(List<Actividad> listaActividadesCasoAC) {
        this.listaActividadesCasoAC = listaActividadesCasoAC;
    }

    public List<Actividad> getListaActividadesCasoAD() {
        return listaActividadesCasoAD;
    }

    public void setListaActividadesCasoAD(List<Actividad> listaActividadesCasoAD) {
        this.listaActividadesCasoAD = listaActividadesCasoAD;
    }

    public boolean isVerTitulo() {
        return verTitulo;
    }

    public void setVerTitulo(boolean verTitulo) {
        this.verTitulo = verTitulo;
    }

    public List<CasoActor> getListCasoActor() {
        return listCasoActor;
    }

    public void setListCasoActor(List<CasoActor> listCasoActor) {
        this.listCasoActor = listCasoActor;
    }

    public List<SelectItem> getListaDepartamento() {
        listaDepartamento = new ArrayList<>();
        List<Departamento> list = ubigeoService.departamentoLista();
        if (list.size() > 0) {
            for (Departamento departamento : list) {
                listaDepartamento.add(new SelectItem(departamento.getIdDepartamento(), departamento.getDescripcion()));
            }
        }
        return listaDepartamento;
    }

    public void setListaDepartamento(List<SelectItem> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<SelectItem> getListaProvincia() {
        return listaProvincia;
    }

    public void setListaProvincia(List<SelectItem> listaProvincia) {
        this.listaProvincia = listaProvincia;
    }

    public List<SelectItem> getListaDistrito() {
        return listaDistrito;
    }

    public void setListaDistrito(List<SelectItem> listaDistrito) {
        this.listaDistrito = listaDistrito;
    }

    public List<SelectItem> getListaPrimerNivel() {
        return listaPrimerNivel;
    }

    public void setListaPrimerNivel(List<SelectItem> listaPrimerNivel) {
        this.listaPrimerNivel = listaPrimerNivel;
    }

    public List<SelectItem> getListaSegundoNivel() {
        return listaSegundoNivel;
    }

    public void setListaSegundoNivel(List<SelectItem> listaSegundoNivel) {
        this.listaSegundoNivel = listaSegundoNivel;
    }

    public List<SelectItem> getListaTercerNivel() {
        return listaTercerNivel;
    }

    public void setListaTercerNivel(List<SelectItem> listaTercerNivel) {
        this.listaTercerNivel = listaTercerNivel;
    }

    public InforVO getInforVO() {
        return inforVO;
    }

    public void setInforVO(InforVO inforVO) {
        this.inforVO = inforVO;
    }

    public CasoRegion getCasoRegion() {
        return casoRegion;
    }

    public void setCasoRegion(CasoRegion casoRegion) {
        this.casoRegion = casoRegion;
    }

    public List<CasoRegion> getListaCasoRegion() {
        return listaCasoRegion;
    }

    public void setListaCasoRegion(List<CasoRegion> listaCasoRegion) {
        this.listaCasoRegion = listaCasoRegion;
    }

    public Boolean getEsComisionadoRegistro() {
        return esComisionadoRegistro;
    }

    public void setEsComisionadoRegistro(Boolean esComisionadoRegistro) {
        this.esComisionadoRegistro = esComisionadoRegistro;
    }

}