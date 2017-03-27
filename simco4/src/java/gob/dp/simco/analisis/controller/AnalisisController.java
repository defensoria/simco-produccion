/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.analisis.controller;

import gob.dp.simco.seguridad.controller.LoginController;
import gob.dp.simco.seguridad.entity.Usuario;
import gob.dp.simco.analisis.entity.AnalisisActor;
import gob.dp.simco.analisis.entity.AnalisisActorIntensidad;
import gob.dp.simco.analisis.entity.AnalisisActorTema;
import gob.dp.simco.analisis.entity.AnalisisRelacion;
import gob.dp.simco.analisis.entity.Contexto;
import gob.dp.simco.analisis.entity.ContextoPregunta;
import gob.dp.simco.analisis.entity.ContextoTipo;
import gob.dp.simco.analisis.entity.Problema;
import gob.dp.simco.analisis.entity.ProblemaDetalle;
import gob.dp.simco.analisis.entity.Tema;
import gob.dp.simco.analisis.service.AnalisisActorIntensidadService;
import gob.dp.simco.analisis.service.AnalisisActorService;
import gob.dp.simco.analisis.service.AnalisisActorTemaService;
import gob.dp.simco.analisis.service.AnalisisRelacionService;
import gob.dp.simco.analisis.service.ContextoPreguntaService;
import gob.dp.simco.analisis.service.ContextoService;
import gob.dp.simco.analisis.service.ContextoTipoService;
import gob.dp.simco.analisis.service.ProblemaDetalleService;
import gob.dp.simco.analisis.service.ProblemaService;
import gob.dp.simco.analisis.service.TemaService;
import gob.dp.simco.analisis.vo.RelacionActorVO;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import gob.dp.simco.registro.bean.FiltroCasoActor;
import gob.dp.simco.registro.controller.RegistroController;
import gob.dp.simco.registro.entity.Actividad;
import gob.dp.simco.registro.entity.ActividadActor;
import gob.dp.simco.registro.entity.Actor;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.entity.CasoActor;
import gob.dp.simco.registro.service.ActividadActorService;
import gob.dp.simco.registro.service.ActorService;
import gob.dp.simco.registro.service.CasoActorService;
import gob.dp.simco.registro.service.CasoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
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
public class AnalisisController extends AbstractManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(AnalisisController.class);

    List<Actor> listaActoresXCaso;

    List<Actor> listaActoresXCasoNivelAD;

    List<Actor> listaActoresXCasoIntensidad;

    List<CasoActor> listCasoActor;

    List<AnalisisActorTema> listaActoresXCasoGrafico;

    List<AnalisisActor> analisisActors;

    List<SelectItem> selectItemActoresXCaso;

    List<RelacionActorVO> listaActorAlianza;

    List<RelacionActorVO> listaActorVinculoCercano;

    List<RelacionActorVO> listaActorVinculoDebil;

    List<RelacionActorVO> listaActorTension;

    List<RelacionActorVO> listaActorConflicto;

    List<ActividadActor> actividadActors;

    List<ProblemaDetalle> listaProblemaDetalle;

    List<Tema> temas;

    List<Tema> temasSeleccinados;

    List<AnalisisActorIntensidad> analisisActorIntensidads;

    private int tipoLista = 0;

    private String activarAlianzas = "panel-default";

    private String activarVinculoCercano = "panel-default";

    private String activarVinculoDebil = "panel-default";

    private String activarTension = "panel-default";

    private String activarConflicto = "panel-default";

    private RelacionActorVO relacionActorVOAlianza;

    private RelacionActorVO relacionActorVOVinculoCercano;

    private RelacionActorVO relacionActorVOVinculoDebil;

    private RelacionActorVO relacionActorVOTension;

    private RelacionActorVO relacionActorVOConflicto;

    private Long idActor;

    private Long idTema = 0L;

    private Long idTemaGrafico = 0L;

    private String cadenaAutocomplete;

    private Tema tema;

    private Actor actor;

    private boolean verModalResumenDemandasxActor = false;

    private AnalisisActor analisisActor;

    private AnalisisActorTema aat;

    private Caso caso;

    private Problema problema;

    private boolean verAutoCompleteCaso = true;

    private String graficaInner;

    private List<ContextoTipo> listaTipoContexto;
    
    private Usuario usuarioSession;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActividadActorService actividadActorService;

    @Autowired
    private TemaService temaService;

    @Autowired
    private AnalisisActorService analisisActorService;

    @Autowired
    private AnalisisActorTemaService analisisActorTemaService;

    @Autowired
    private AnalisisActorIntensidadService analisisActorIntensidadService;

    @Autowired
    private AnalisisRelacionService analisisRelacionService;

    @Autowired
    private CasoService casoService;

    @Autowired
    private ProblemaService problemaService;

    @Autowired
    private ProblemaDetalleService problemaDetalleService;

    @Autowired
    private CasoActorService casoActorService;

    @Autowired
    private ContextoTipoService contextoTipoService;

    @Autowired
    private ContextoPreguntaService contextoPreguntaService;

    @Autowired
    private ContextoService contextoService;

    public AnalisisController() {
        limpiarListas();
    }
    
    public void replicarAnalisis(Long idCasoOld, Long idCasoNew){
        List<AnalisisActorIntensidad> aais = analisisActorIntensidadService.analisisActorIntensidadBuscar(idCasoOld);
        Caso c = new Caso();
        c.setId(idCasoNew);
        for(AnalisisActorIntensidad aai : aais){    
            aai.setCaso(c);
            analisisActorIntensidadService.analisisActorIntensidadInsertar(aai);
        }
        List<AnalisisRelacion> ars = analisisRelacionService.analisisRelacionBuscarTodos(idCasoOld);
        for(AnalisisRelacion ar : ars){
            ar.setCaso(c);
            analisisRelacionService.analisisRelacionInsertar(ar);
        }
        List<Tema> temas = temaService.temaBuscar(idCasoOld);
        for(Tema t : temas){
            t.setCaso(c);
            temaService.temaInsertar(t);
        }
        List<AnalisisActor> aas = analisisActorService.analisisActorxcasoBuscar(idCasoOld);
        for(AnalisisActor aa : aas){
            aa.setCaso(c);
            analisisActorService.analisisActorInsertar(aa);
        }
        List<AnalisisActorTema> aats = analisisActorTemaService.analisisActorTemaPorCasoActor(idCasoOld);
        for(AnalisisActorTema aat : aats){
            aat.setCaso(c);
            for(Tema t : temas){
                if(StringUtils.equals(aat.getTema().getDetalle(), t.getDetalle())){
                    aat.setTema(t);
                }
            }
            analisisActorTemaService.analisisActorTemaInsertar(aat);
        }
        Problema ps = problemaService.problemaBuscar(idCasoOld);
        if(ps != null){
            ps.setIdCaso(idCasoNew);
            Long idProblemaOld = ps.getId();
            problemaService.problemaInsertar(ps);
            List<ProblemaDetalle> detalles = problemaDetalleService.problemaDetalleBuscar(idProblemaOld);
            for(ProblemaDetalle pd : detalles){
                pd.setIdProblema(ps.getId());
                problemaDetalleService.problemaDetalleInsertar(pd);
            }
        }
    }

    public String cargarContexto(Caso caso) {
        setCaso(caso);
        listaTipoContexto = contextoTipoService.contextoTipoBuscar();
        for (ContextoTipo tipo : listaTipoContexto) {
            List<Contexto> list = new ArrayList<>();
            List<ContextoPregunta> preguntas = contextoPreguntaService.contextoPreguntaBuscar(tipo.getId());
            for (ContextoPregunta pregunta : preguntas) {
                Contexto filtro = new Contexto();
                filtro.setIdCaso(caso.getId());
                filtro.setIdPregunta(pregunta.getId());
                Contexto contexto;
                contexto = contextoService.contextoBuscar(filtro);
                if(contexto == null){
                    contexto = new Contexto();
                    contexto.setIdPregunta(pregunta.getId());
                    contexto.setIdCaso(caso.getId());
                }
                contexto.setPregunta(pregunta.getDetalle());
                list.add(contexto);
            }
            tipo.setRespuesta(list);
        }
        return "contexto";
    }

    public void guardarContexto() {
        for (ContextoTipo tipo : listaTipoContexto) {
            for (Contexto contex : tipo.getRespuesta()) {
                if (StringUtils.isNotBlank(contex.getRespuesta())) {
                    if (contex.getId() == null) {
                        contextoService.contextoInsertar(contex);
                    } else {
                        contextoService.contextoUpdate(contex);
                    }
                }
            }
        }
        msg.messageInfo("Se realizaron los cambios", null);
    }

    public String cargarPaginaProblemas() {
        actividadActors = null;
        idActor = null;
        idTema = null;
        tema = new Tema();
        temasSeleccinados = new ArrayList<>();
        analisisActor = new AnalisisActor();
        analisisActors = new ArrayList<>();
        caso = new Caso();
        problema = new Problema();
        listaProblemaDetalle = new ArrayList<>();
        listaActoresXCaso = null;
        verAutoCompleteCaso = true;
        graficaInner = "";
        return "problemas";
    }

    private void problemaDatos(Caso c) {
        problema = problemaService.problemaBuscar(c.getId());
        listaProblemaDetalle = new ArrayList<>();
        graficaInner = "";
        if (problema == null) {
            problema = new Problema();
        } else {
            List<ProblemaDetalle> detalles = problemaDetalleService.problemaDetalleBuscar(problema.getId());
            if (detalles.size() > 0) {
                listaProblemaDetalle.addAll(detalles);
                generaGrafica();
            }
        }
    }

    public String verActividad(long idActividad) {
        FacesContext context = FacesContext.getCurrentInstance();
        RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        Actividad a = registroController.cargarActividadId(idActividad);
        if (a.getIdCaso() == null) {
            return registroController.irModificarRegistro2(a);
        } else {
            return registroController.irModificarRegistro(a);
        }
    }

    public String cargarPaginaProblemasCaso(Caso c) {
        cargarPaginaProblemas();
        cargarComboActoresXcaso(c);
        verAutoCompleteCaso = false;
        return "problemas";
    }

    public void limpiarModalTema() {
        tema = new Tema();
        idTema = null;
    }

    public void cargarComboActoresXcaso(Caso c) {
        if (c.getId() != null) {
            setCaso(c);
            listaActoresXCaso = actorService.actorxCasoBuscar(c.getId());
            analisisActors = analisisActorService.analisisActorxcasoBuscar(c.getId());
            problemaDatos(c);
            listarTemas(c.getId());
            limpiarCanvasActores();
        } else {
            msg.messageAlert("Debe seleccionar un caso registrado", null);
        }
    }

    public void filtarXactor() {
        if (idActor == 0) {
            cargarComboActoresXcaso(caso);
        } else {
            if (caso.getId() != null) {
                AnalisisActor aa1 = new AnalisisActor();
                aa1.setCaso(caso);
                Actor a = new Actor();
                a.setId(idActor);
                aa1.setActor(a);
                listaActoresXCaso = actorService.actorxCasoBuscar(caso.getId());
                analisisActors = analisisActorService.analisisActorxcasoBuscarxActor(aa1);
                problemaDatos(caso);
                listarTemas(caso.getId());
                limpiarCanvasActores();
            } else {
                msg.messageAlert("Debe seleccionar un caso registrado", null);
            }
        }
    }

    private void limpiarCanvasActores() {
        FacesContext context = FacesContext.getCurrentInstance();
        CanvasController canvasController = (CanvasController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "canvasController");
        canvasController.limpiarCanvasProblemas();
        canvasController.setIdTemaGrafico(0L);
    }

    public void cargarCanvasXcaso(Caso c) {
        if (c.getId() != null) {
            try {
                Caso c1 = casoService.casoBuscarOne(c.getId());
                c.setSintesisAnalisis(c1.getSintesisAnalisis());
                setCaso(c);
                limpiarRelaciones();
                if (c.getId() != null) {
                    listaActoresXCasoIntensidad = actorService.actorxCasoIntensidadBuscar(c.getId());
                    listCasoActor = casoActorService.casoActorBuscarComplete(c.getId());
                    obtenerPromedioNivelAD(c);
                    listaActoresXCaso = actorService.actorxCasoBuscar(c.getId());
                    analisisActorIntensidads = null;
                    analisisActorIntensidads = analisisActorIntensidadService.analisisActorIntensidadBuscar(c.getId());
                    cargarListasRelacion(c);
                } else {
                    msg.messageAlert("Debe seleccionar un caso registrado", null);
                }
            } catch (Exception ex) {
                log.error(ex);
            }
        } else {
            msg.messageAlert("Debe seleccionar un caso registrado", null);
        }
    }

    private void obtenerPromedioNivelAD(Caso c) {
        listaActoresXCasoNivelAD = new ArrayList<>();
        List<Actor> list = actorService.actorxCasoBuscar(c.getId());
        for (Actor a : list) {
            ActividadActor aa = new ActividadActor();
            aa.setIdCaso(c.getId());
            Actor act = new Actor();
            act.setId(a.getId());
            aa.setActor(act);
            List<ActividadActor> listaNivelAD = actividadActorService.actividadActorNivelAD(aa);
            Double promedio = 0.0;
            Integer count = 0;
            Double sum = 0.0;
            for (ActividadActor aa1 : listaNivelAD) {
                if (aa1.getNivel() != null) {
                    sum = sum + aa1.getNivel();
                }
                count++;
            }
            promedio = sum / count;
            a.setPromedio(redondear(promedio, 2));
            if (promedio > 0) {
                a.setValidaPromedio(true);
            } else {
                a.setValidaPromedio(false);
            }
            listaActoresXCasoNivelAD.add(a);
            Collections.sort(listaActoresXCasoNivelAD, new Comparator<Actor>() {
                @Override
                public int compare(Actor o1, Actor o2) {
                    return o1.getNombre().compareTo(o2.getNombre());
                }
            });
        }
    }

    private double redondear(double numero, int decimales) {
        return Math.round(numero * Math.pow(10, decimales)) / Math.pow(10, decimales);
    }

    public String cargarPagina() {
        try {
            caso = new Caso();
            listaActoresXCaso = null;
            activarAlianzas = "panel-default";
            activarVinculoCercano = "panel-default";
            activarVinculoDebil = "panel-default";
            activarTension = "panel-default";
            activarConflicto = "panel-default";
            limpiarListas();
            verAutoCompleteCaso = true;
        } catch (Exception e) {
            log.error(e.getCause());
        }
        return "analisis";
    }

    public String cargarPaginaCaso(Caso c) {
        try {
            usuarioSession();
            listaActoresXCaso = null;
            activarAlianzas = "panel-default";
            activarVinculoCercano = "panel-default";
            activarVinculoDebil = "panel-default";
            activarTension = "panel-default";
            activarConflicto = "panel-default";
            limpiarListas();
            cargarCanvasXcaso(c);
            verAutoCompleteCaso = false;
        } catch (Exception e) {
            log.error(e.getCause());
        }
        return "analisis";
    }

    public void addProblemaDetalle() {
        listaProblemaDetalle.add(new ProblemaDetalle());
    }

    public void agregarTema() {
        if (idTema > 0) {
            if (temasSeleccinados.size() > 0) {
                int i = 0;
                for (Tema tem : temasSeleccinados) {
                    if (Objects.equals(tem.getId(), idTema)) {
                        i++;
                    }
                }
                if (i == 0) {
                    temasSeleccinados.add(temaService.temaxidBuscar(idTema));
                }
            } else {
                temasSeleccinados.add(temaService.temaxidBuscar(idTema));
            }
        }
    }

    public boolean saveProblema() {
        if (caso.getId() != null) {
            problema.setIdCaso(caso.getId());
        } else {
            msg.messageAlert("Debe de seleccionar un caso registrado", null);
            return false;
        }
        if (problema.getIdCaso() != null) {
            if (problema.getEstado() == null) {
                problema.setEstado("ACT");
                problema.setIdCaso(caso.getId());
                problema.setFechaRegistro(new Date());
                problemaService.problemaInsertar(problema);
            } else {
                problemaService.problemaUpdate(problema);
            }

            for (ProblemaDetalle pd : listaProblemaDetalle) {
                if (pd.getId() == null) {
                    pd.setEstado("ACT");
                    pd.setIdProblema(problema.getId());
                    problemaDetalleService.problemaDetalleInsertar(pd);
                } else {
                    problemaDetalleService.problemaDetalleUpdateDatos(pd);
                }
            }
            graficaInner = "";
            if (listaProblemaDetalle.size() > 0) {
                generaGrafica();
            }
        }
        msg.messageInfo("Se han realizado todos los cambios", null);
        return true;
    }

    public void removeDetalleProblema(ProblemaDetalle pd) {
        listaProblemaDetalle.remove(pd);
        if (pd.getId() != null) {
            problemaDetalleService.problemaDetalleUpdate(pd);
        }
        generaGrafica();
    }

    private void generaGrafica() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb.append("<div style=\"position: relative; top: 0px\">");
        sb.append("<div class=\"circle circle-border\" >");
        sb.append("<span class=\"badge numer\">" + listaProblemaDetalle.size() + "</span>");
        int j = listaProblemaDetalle.size();
        for (int i = 1; i < listaProblemaDetalle.size(); i++) {
            j--;
            sb.append("<div class=\"circle circle-border\" >");
            sb.append("<div class=\"circle-inner\" >");
            sb.append("<span class=\"badge numer\" >" + j + "</span>");
            sb2.append("</div></div>");
        }
        sb2.append("</div></div>");

        graficaInner = sb.toString() + "" + sb2.toString();
    }

    public void cambiarCanvas(ValueChangeEvent event) {
        Long id = Long.parseLong(event.getNewValue().toString());
        List<AnalisisActorTema> list = analisisActorTemaService.analisisActorTemaXactorBuscar(id);
        listaActoresXCasoGrafico = new ArrayList<>();
        listaActoresXCasoGrafico.clear();
        listaActoresXCasoGrafico.addAll(list);
    }

    public void cambiarGrafico() {
        listaActoresXCasoGrafico = null;
        listaActoresXCasoGrafico = analisisActorTemaService.analisisActorTemaXactorBuscar(idTemaGrafico);
    }

    public void saveAnalisisActor() {
        if (actor != null) {
            if (temasSeleccinados.size() > 0) {
                analisisActor.setCaso(caso);
                analisisActor.setActor(actor);
                insertUpdateAnalisisActor(analisisActor);
                for (Tema t : temasSeleccinados) {
                    AnalisisActorTema aat1 = new AnalisisActorTema();
                    aat1.setTema(t);
                    aat1.setActor(actor);
                    aat1.setCaso(caso);
                    int validaTema = analisisActorTemaService.analisisActorCasoTemaValida(aat1);
                    if (validaTema == 0) {
                        saveAnalisisActorTema(aat1);
                    }
                }
                msg.messageInfo("Se han registrado los cambios", null);
                verModalResumenDemandasxActor = false;
                analisisActors = analisisActorService.analisisActorxcasoBuscar(caso.getId());
            } else {
                msg.messageAlert("Debe de seleccionar por lo menos un tema", null);
            }
        }
    }

    private void insertUpdateAnalisisActor(AnalisisActor aa) {
        AnalisisActor aa1 = analisisActorService.analisisActorxcasoBuscarOne(aa);
        if (aa1 == null) {
            analisisActor.setFechaRegistro(new Date());
            analisisActorService.analisisActorInsertar(analisisActor);
        } else {
            analisisActor.setFechaModificacion(new Date());
            analisisActorService.analisisActorxcasoUpdate(analisisActor);
        }
    }

    public void deleteAnalisisActor(AnalisisActor aa) {
        try {
            deleteTemasxActor(aa);
            analisisActorService.analisisActorDelete(aa);
            analisisActors = analisisActorService.analisisActorxcasoBuscar(caso.getId());
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void archivarAnalisisActor(AnalisisActor aa) {
        aa.setArchivado(1);
        analisisActorService.analisisActorArchivar(aa);
        msg.messageInfo("Se ha archivado el resumen de la demanda", null);
    }

    public void desarchivarTodas() {
        if (analisisActors.size() > 0) {
            for (AnalisisActor aa : analisisActors) {
                aa.setArchivado(0);
                analisisActorService.analisisActorArchivar(aa);
            }
        }
        msg.messageInfo("Se ha habilitado la visualización de todos los resúmenes de demandas", null);
    }

    private void deleteTemasxActor(AnalisisActor aa) {
        AnalisisActorTema aat1 = new AnalisisActorTema();
        aat1.setCaso(aa.getCaso());
        aat1.setActor(aa.getActor());
        analisisActorTemaService.analisisActorTemaDeletexActor(aat1);
    }

    private void saveAnalisisActorTema(AnalisisActorTema analisisActorTema) {
        analisisActorTemaService.analisisActorTemaInsertar(analisisActorTema);
    }

    public void removeTema(Tema tema) {
        temasSeleccinados.remove(tema);
        AnalisisActorTema aat2 = new AnalisisActorTema();
        Actor a = new Actor();
        a.setId(idActor);
        aat2.setCaso(caso);
        aat2.setActor(a);
        aat2.setTema(tema);
        int valida = analisisActorTemaService.analisisActorCasoTemaValida(aat2);
        if (valida > 0) {
            analisisActorTemaService.analisisActorTemaDelete(aat2);
        }
    }

    public void analisisDemandaxActor() {
        limpiarModalTema();
        if (idActor != null && idActor != 0) {
            try {
                obtenerAnalisisActor();
                actor = new Actor();
                actor = actorService.actorBuscarOne(idActor);
                listarTemas(caso.getId());
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        } else {
            temas = null;
            actor = null;
        }
    }

    private void obtenerAnalisisActor() {
        AnalisisActor aa = new AnalisisActor();
        Actor a = new Actor();
        a.setId(idActor);
        aa.setActor(a);
        aa.setCaso(caso);
        analisisActor = analisisActorService.analisisActorxcasoBuscarOne(aa);
        if (analisisActor == null) {
            analisisActor = new AnalisisActor();
        }
        Tema t = new Tema();
        t.setActor(a);
        t.setCaso(caso);
        temasSeleccinados = temaService.temasxActor(t);
    }

    public void listener() {
        FiltroCasoActor filtro = new FiltroCasoActor();
        filtro.setIdActor(idActor);
        filtro.setIdCaso(caso.getId());
        actividadActors = actividadActorService.actividadActorXcaso(filtro);
    }

    public boolean saveTema() {
        for (Tema t : temas) {
            if (StringUtils.equalsIgnoreCase(tema.getDetalle(), t.getDetalle())) {
                msg.messageAlert("El tema ingresado ya se encuentra registrado", null);
                tema = new Tema();
                return false;
            }
        }
        tema.setCaso(caso);
        temaService.temaInsertar(tema);
        listarTemas(caso.getId());
        tema = new Tema();
        msg.messageInfo("Se registró un nuevo tema para el análisis", null);
        return true;
    }

    public void listarTemas(Long idCaso) {
        temas = temaService.temaBuscar(idCaso);
    }

    private void limpiarListas() {
        listaActorAlianza = new ArrayList<>();
        listaActorVinculoCercano = new ArrayList<>();
        listaActorVinculoDebil = new ArrayList<>();
        listaActorTension = new ArrayList<>();
        listaActorConflicto = new ArrayList<>();
        initRelacionActorVOAlianza();
        initRelacionActorVOVinculoCercano();
        initRelacionActorVOVinculoDebil();
        initRelacionActorVOTension();
        initRelacionActorVOConflicto();
        listaActoresXCasoNivelAD = new ArrayList<>();
        listCasoActor = null;
        listaActoresXCasoIntensidad = null;

    }

    public void initRelacionActorVOAlianza() {
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        relacionActorVOAlianza = new RelacionActorVO();
        relacionActorVOAlianza.setActor1(actor1);
        relacionActorVOAlianza.setActor2(actor2);
    }

    public void initRelacionActorVOVinculoCercano() {
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        relacionActorVOVinculoCercano = new RelacionActorVO();
        relacionActorVOVinculoCercano.setActor1(actor1);
        relacionActorVOVinculoCercano.setActor2(actor2);
    }

    public void initRelacionActorVOVinculoDebil() {
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        relacionActorVOVinculoDebil = new RelacionActorVO();
        relacionActorVOVinculoDebil.setActor1(actor1);
        relacionActorVOVinculoDebil.setActor2(actor2);
    }

    public void initRelacionActorVOTension() {
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        relacionActorVOTension = new RelacionActorVO();
        relacionActorVOTension.setActor1(actor1);
        relacionActorVOTension.setActor2(actor2);
    }

    public void initRelacionActorVOConflicto() {
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        relacionActorVOConflicto = new RelacionActorVO();
        relacionActorVOConflicto.setActor1(actor1);
        relacionActorVOConflicto.setActor2(actor2);
    }

    public void cargarListasRelacion(Caso c) {
        List<AnalisisRelacion> analisisRelacions = analisisRelacionService.analisisRelacionBuscar(c.getId());
        listaActorAlianza = new ArrayList<>();
        listaActorVinculoCercano = new ArrayList<>();
        listaActorVinculoDebil = new ArrayList<>();
        listaActorTension = new ArrayList<>();
        listaActorConflicto = new ArrayList<>();
        for (AnalisisRelacion analisisRelacion : analisisRelacions) {
            RelacionActorVO o = new RelacionActorVO();
            Actor a1 = new Actor();
            a1.setId(analisisRelacion.getActor1().getId());
            a1.setNombre(analisisRelacion.getActor1().getNombre());
            a1.setApellidoPat(analisisRelacion.getActor1().getApellidoPat());
            a1.setApellidoMat(analisisRelacion.getActor1().getApellidoMat());
            Actor a2 = new Actor();
            a2.setId(analisisRelacion.getActor2().getId());
            a2.setNombre(analisisRelacion.getActor2().getNombre());
            a2.setApellidoPat(analisisRelacion.getActor2().getApellidoPat());
            a2.setApellidoMat(analisisRelacion.getActor2().getApellidoMat());
            o.setActor1(a1);
            o.setActor2(a2);

            switch (analisisRelacion.getTipo()) {
                case "ALI":
                    listaActorAlianza.add(o);
                    break;

                case "CER":
                    listaActorVinculoCercano.add(o);
                    break;

                case "DEB":
                    listaActorVinculoDebil.add(o);
                    break;

                case "TEN":
                    listaActorTension.add(o);
                    break;

                case "CON":
                    listaActorConflicto.add(o);
                    break;
            }
        }
    }

    public void saveAnalisisActorIntensidad() {
        if (listaActoresXCasoIntensidad != null) {
            try {
                for (Actor ac : listaActoresXCasoIntensidad) {
                    AnalisisActorIntensidad aai = new AnalisisActorIntensidad();
                    Actor a = new Actor();
                    a.setId(ac.getId());
                    aai.setActor(a);
                    aai.setCaso(caso);
                    aai.setNivel(ac.getAnalisisActorIntensidad().getNivel());
                    if (buscarActorCasoIntensidad(aai) == 0) {
                        aai.setFechaRegistro(new Date());
                        aai.setUsuarioRegistro(usuarioSession.getCodigo());
                        analisisActorIntensidadService.analisisActorIntensidadInsertar(aai);
                    } else {
                        analisisActorIntensidadService.analisisActorIntensidadUpdate(aai);
                    }
                    if(StringUtils.isNotBlank(caso.getSintesisAnalisis())){
                        casoService.casoUpdateSistesis(caso);
                    }
                    ac.setAnalisisActorIntensidad(aai);
                }
                msg.messageInfo("Se han registrado los cambios", null);
            } catch (Exception ex) {
                log.error(ex);
            }
        }
    }

    private Integer buscarActorCasoIntensidad(AnalisisActorIntensidad intensidad) {
        return analisisActorIntensidadService.analisisActorIntensidadBuscarUno(intensidad);
    }

    public void saveRelaciones() {
        int i = 0;
        for (RelacionActorVO o : listaActorAlianza) {
            AnalisisRelacion analisisRelacion = new AnalisisRelacion();
            Actor actor1 = new Actor();
            Actor actor2 = new Actor();

            actor1.setId(o.getActor1().getId());
            actor2.setId(o.getActor2().getId());

            if (actor1.getId() != null && actor2.getId() != null) {
                analisisRelacion.setCaso(caso);
                analisisRelacion.setActor1(actor1);
                analisisRelacion.setActor2(actor2);
                analisisRelacion.setTipo("ALI");
                AnalisisRelacion ar = analisisRelacionService.analisisRelacionBuscarOne(analisisRelacion);
                if (ar == null) {
                    analisisRelacion.setFechaRegistro(new Date());
                    analisisRelacionService.analisisRelacionInsertar(analisisRelacion);
                }
            } else {
                i++;
            }
        }

        for (RelacionActorVO o : listaActorVinculoCercano) {
            AnalisisRelacion analisisRelacion = new AnalisisRelacion();
            Actor actor1 = new Actor();
            Actor actor2 = new Actor();

            actor1.setId(o.getActor1().getId());
            actor2.setId(o.getActor2().getId());

            if (actor1.getId() != null && actor2.getId() != null) {
                actor1.setId(o.getActor1().getId());
                actor2.setId(o.getActor2().getId());

                analisisRelacion.setCaso(caso);
                analisisRelacion.setActor1(actor1);
                analisisRelacion.setActor2(actor2);
                analisisRelacion.setTipo("CER");
                AnalisisRelacion ar = analisisRelacionService.analisisRelacionBuscarOne(analisisRelacion);
                if (ar == null) {
                    analisisRelacion.setFechaRegistro(new Date());
                    analisisRelacionService.analisisRelacionInsertar(analisisRelacion);
                }
            } else {
                i++;
            }
        }

        for (RelacionActorVO o : listaActorVinculoDebil) {
            AnalisisRelacion analisisRelacion = new AnalisisRelacion();
            Actor actor1 = new Actor();
            Actor actor2 = new Actor();

            actor1.setId(o.getActor1().getId());
            actor2.setId(o.getActor2().getId());

            if (actor1.getId() != null && actor2.getId() != null) {
                analisisRelacion.setCaso(caso);
                analisisRelacion.setActor1(actor1);
                analisisRelacion.setActor2(actor2);
                analisisRelacion.setTipo("DEB");
                AnalisisRelacion ar = analisisRelacionService.analisisRelacionBuscarOne(analisisRelacion);
                if (ar == null) {
                    analisisRelacion.setFechaRegistro(new Date());
                    analisisRelacionService.analisisRelacionInsertar(analisisRelacion);
                }
            } else {
                i++;
            }
        }

        for (RelacionActorVO o : listaActorTension) {
            AnalisisRelacion analisisRelacion = new AnalisisRelacion();
            Actor actor1 = new Actor();
            Actor actor2 = new Actor();

            actor1.setId(o.getActor1().getId());
            actor2.setId(o.getActor2().getId());

            if (actor1.getId() != null && actor2.getId() != null) {
                analisisRelacion.setCaso(caso);
                analisisRelacion.setActor1(actor1);
                analisisRelacion.setActor2(actor2);
                analisisRelacion.setTipo("TEN");
                AnalisisRelacion ar = analisisRelacionService.analisisRelacionBuscarOne(analisisRelacion);
                if (ar == null) {
                    analisisRelacion.setFechaRegistro(new Date());
                    analisisRelacionService.analisisRelacionInsertar(analisisRelacion);
                }
            } else {
                i++;
            }
        }

        for (RelacionActorVO o : listaActorConflicto) {
            AnalisisRelacion analisisRelacion = new AnalisisRelacion();
            Actor actor1 = new Actor();
            Actor actor2 = new Actor();

            actor1.setId(o.getActor1().getId());
            actor2.setId(o.getActor2().getId());

            if (actor1.getId() != null && actor2.getId() != null) {
                analisisRelacion.setCaso(caso);
                analisisRelacion.setActor1(actor1);
                analisisRelacion.setActor2(actor2);
                analisisRelacion.setTipo("CON");
                AnalisisRelacion ar = analisisRelacionService.analisisRelacionBuscarOne(analisisRelacion);
                if (ar == null) {
                    analisisRelacion.setFechaRegistro(new Date());
                    analisisRelacionService.analisisRelacionInsertar(analisisRelacion);
                }
            } else {
                i++;
            }
        }
        if (i > 0) {
            msg.messageAlert("Las relaciones con un solo actor no se han registrado", null);
        } else {
            msg.messageInfo("Se registraron todas las relaciones correctamente", null);
        }
    }

    public void cargarSeccionAlianzas() {
        tipoLista = 1;
        activarAlianzas = "panel-primary";
        activarVinculoCercano = "panel-default";
        activarVinculoDebil = "panel-default";
        activarTension = "panel-default";
        activarConflicto = "panel-default";
    }

    public void cargarSeccionVinculoCercano() {
        tipoLista = 2;
        activarVinculoCercano = "panel-primary";
        activarAlianzas = "panel-default";
        activarVinculoDebil = "panel-default";
        activarTension = "panel-default";
        activarConflicto = "panel-default";
    }

    public void cargarSeccionVinculoDebil() {
        tipoLista = 3;
        activarVinculoDebil = "panel-primary";
        activarAlianzas = "panel-default";
        activarVinculoCercano = "panel-default";
        activarTension = "panel-default";
        activarConflicto = "panel-default";
    }

    public void cargarSeccionTension() {
        tipoLista = 4;
        activarTension = "panel-primary";
        activarAlianzas = "panel-default";
        activarVinculoCercano = "panel-default";
        activarVinculoDebil = "panel-default";
        activarConflicto = "panel-default";
    }

    public void cargarSeccionConflicto() {
        tipoLista = 5;
        activarConflicto = "panel-primary";
        activarAlianzas = "panel-default";
        activarVinculoCercano = "panel-default";
        activarVinculoDebil = "panel-default";
        activarTension = "panel-default";
    }

    public void cargarListas(Actor actor) {

        switch (tipoLista) {
            case 1:
                cargarListasAlianza(actor);
                break;
            case 2:
                cargarListasVinculoCercano(actor);
                break;
            case 3:
                cargarListasVinculoDebil(actor);
                break;
            case 4:
                cargarListasTension(actor);
                break;
            case 5:
                cargarListasConflicto(actor);
                break;
            case 0:
                msg.messageAlert("Debe de seleccionar un tipo de relación", null);
                break;
        }
    }

    private void limpiarRelaciones() {
        initRelacionActorVOAlianza();
        initRelacionActorVOConflicto();
        initRelacionActorVOVinculoCercano();
        initRelacionActorVOTension();
        initRelacionActorVOVinculoDebil();
    }

    private boolean validaRelacionExiste(Actor act, RelacionActorVO vo) {
        for (RelacionActorVO o : listaActorAlianza) {
            if (Objects.equals(o.getActor1().getId(), vo.getActor1().getId())) {
                if (Objects.equals(o.getActor2().getId(), act.getId())) {
                    msg.messageAlert("Esta relación ya existe en el tipo alianza", null);
                    return false;
                }
            }
        }
        for (RelacionActorVO o : listaActorConflicto) {
            if (Objects.equals(o.getActor1().getId(), vo.getActor1().getId())) {
                if (Objects.equals(o.getActor2().getId(), act.getId())) {
                    msg.messageAlert("Esta relación ya existe en el tipo conflicto", null);
                    return false;
                }
            }
        }
        for (RelacionActorVO o : listaActorTension) {
            if (Objects.equals(o.getActor1().getId(), vo.getActor1().getId())) {
                if (Objects.equals(o.getActor2().getId(), act.getId())) {
                    msg.messageAlert("Esta relación ya existe en el tipo vínculo tensión", null);
                    return false;
                }
            }
        }
        for (RelacionActorVO o : listaActorVinculoCercano) {
            if (Objects.equals(o.getActor1().getId(), vo.getActor1().getId())) {
                if (Objects.equals(o.getActor2().getId(), act.getId())) {
                    msg.messageAlert("Esta relación ya existe en el tipo vínculo cercano", null);
                    return false;
                }
            }
        }
        for (RelacionActorVO o : listaActorVinculoDebil) {
            if (Objects.equals(o.getActor1().getId(), vo.getActor1().getId())) {
                if (Objects.equals(o.getActor2().getId(), act.getId())) {
                    msg.messageAlert("Esta relación ya existe en el tipo vínculo debil", null);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean cargarListasAlianza(Actor actor) {
        int indicador = 0;
        if (relacionActorVOAlianza.getActor1().getNombre() == null) {
            relacionActorVOAlianza.setActor1(actor);
            listaActorAlianza.add(relacionActorVOAlianza);
        } else {
            if (relacionActorVOAlianza.getActor2().getNombre() == null) {
                boolean valid = validaRelacionExiste(actor, relacionActorVOAlianza);
                if (!valid) {
                    return false;
                }
                relacionActorVOAlianza.setActor2(actor);
                msg.messageInfo("Se ha agregado la relación de alianzas", null);
                indicador++;
            }
        }
        if (indicador > 0) {
            relacionActorVOAlianza = new RelacionActorVO();
            initRelacionActorVOAlianza();
        }
        return true;
    }

    public boolean cargarListasVinculoCercano(Actor actor) {
        int indicador = 0;
        if (relacionActorVOVinculoCercano.getActor1().getNombre() == null) {
            relacionActorVOVinculoCercano.setActor1(actor);
            listaActorVinculoCercano.add(relacionActorVOVinculoCercano);
        } else {
            if (relacionActorVOVinculoCercano.getActor2().getNombre() == null) {
                boolean valid = validaRelacionExiste(actor, relacionActorVOVinculoCercano);
                if (!valid) {
                    return false;
                }
                relacionActorVOVinculoCercano.setActor2(actor);
                msg.messageInfo("Se ha agregado la relación de vínculo cercano", null);
                indicador++;
            }
        }
        if (indicador > 0) {
            relacionActorVOVinculoCercano = new RelacionActorVO();
            initRelacionActorVOVinculoCercano();
        }
        return true;
    }

    public boolean cargarListasVinculoDebil(Actor actor) {
        int indicador = 0;
        if (relacionActorVOVinculoDebil.getActor1().getNombre() == null) {
            relacionActorVOVinculoDebil.setActor1(actor);
            listaActorVinculoDebil.add(relacionActorVOVinculoDebil);
        } else {
            if (relacionActorVOVinculoDebil.getActor2().getNombre() == null) {
                boolean valid = validaRelacionExiste(actor, relacionActorVOVinculoDebil);
                if (!valid) {
                    return false;
                }
                relacionActorVOVinculoDebil.setActor2(actor);
                msg.messageInfo("Se ha agregado la relación de vínculo débil", null);
                indicador++;
            }
        }
        if (indicador > 0) {
            relacionActorVOVinculoDebil = new RelacionActorVO();
            initRelacionActorVOVinculoDebil();
        }
        return true;
    }

    public boolean cargarListasTension(Actor actor) {
        int indicador = 0;
        if (relacionActorVOTension.getActor1().getNombre() == null) {
            relacionActorVOTension.setActor1(actor);
            listaActorTension.add(relacionActorVOTension);
        } else {
            if (relacionActorVOTension.getActor2().getNombre() == null) {
                boolean valid = validaRelacionExiste(actor, relacionActorVOTension);
                if (!valid) {
                    return false;
                }
                relacionActorVOTension.setActor2(actor);
                msg.messageInfo("Se ha agregado la relación vínculo de tensión", null);
                indicador++;
            }
        }
        if (indicador > 0) {
            relacionActorVOTension = new RelacionActorVO();
            initRelacionActorVOTension();
        }
        return true;
    }

    public boolean cargarListasConflicto(Actor actor) {
        int indicador = 0;
        if (relacionActorVOConflicto.getActor1().getNombre() == null) {
            relacionActorVOConflicto.setActor1(actor);
            listaActorConflicto.add(relacionActorVOConflicto);
        } else {
            if (relacionActorVOConflicto.getActor2().getNombre() == null) {
                boolean valid = validaRelacionExiste(actor, relacionActorVOConflicto);
                if (!valid) {
                    return false;
                }
                relacionActorVOConflicto.setActor2(actor);
                msg.messageInfo("Se ha agregado la relación de conflicto", null);
                indicador++;
            }
        }
        if (indicador > 0) {
            relacionActorVOConflicto = new RelacionActorVO();
            initRelacionActorVOConflicto();
        }
        return true;
    }

    public void removeListasAlianzas(RelacionActorVO relacionActorVO) {
        listaActorAlianza.remove(relacionActorVO);
        if (relacionActorVO.getActor1().getId() != null && relacionActorVO.getActor2().getId() != null) {
            AnalisisRelacion analisisRelacion = new AnalisisRelacion();
            analisisRelacion.setActor1(relacionActorVO.getActor1());
            analisisRelacion.setActor2(relacionActorVO.getActor2());
            analisisRelacion.setCaso(caso);
            analisisRelacionService.analisisRelacioEliminar(analisisRelacion);
        }
        initRelacionActorVOAlianza();
    }

    public void removeListasVinculoCercano(RelacionActorVO relacionActorVO) {
        listaActorVinculoCercano.remove(relacionActorVO);
        if (relacionActorVO.getActor1().getId() != null && relacionActorVO.getActor2().getId() != null) {
            AnalisisRelacion analisisRelacion = new AnalisisRelacion();
            analisisRelacion.setActor1(relacionActorVO.getActor1());
            analisisRelacion.setActor2(relacionActorVO.getActor2());
            analisisRelacion.setCaso(caso);
            analisisRelacionService.analisisRelacioEliminar(analisisRelacion);
        }
        initRelacionActorVOVinculoCercano();
    }

    public void removeListasVinculoDebil(RelacionActorVO relacionActorVO) {
        listaActorVinculoDebil.remove(relacionActorVO);
        if (relacionActorVO.getActor1().getId() != null && relacionActorVO.getActor2().getId() != null) {
            AnalisisRelacion analisisRelacion = new AnalisisRelacion();
            analisisRelacion.setActor1(relacionActorVO.getActor1());
            analisisRelacion.setActor2(relacionActorVO.getActor2());
            analisisRelacion.setCaso(caso);
            analisisRelacionService.analisisRelacioEliminar(analisisRelacion);
        }
        initRelacionActorVOVinculoDebil();
    }

    public void removeListasTension(RelacionActorVO relacionActorVO) {
        listaActorTension.remove(relacionActorVO);
        if (relacionActorVO.getActor1().getId() != null && relacionActorVO.getActor2().getId() != null) {
            AnalisisRelacion analisisRelacion = new AnalisisRelacion();
            analisisRelacion.setActor1(relacionActorVO.getActor1());
            analisisRelacion.setActor2(relacionActorVO.getActor2());
            analisisRelacion.setCaso(caso);
            analisisRelacionService.analisisRelacioEliminar(analisisRelacion);
        }
        initRelacionActorVOTension();
    }

    public void removeListasConflicto(RelacionActorVO relacionActorVO) {
        listaActorConflicto.remove(relacionActorVO);
        if (relacionActorVO.getActor1().getId() != null && relacionActorVO.getActor2().getId() != null) {
            AnalisisRelacion analisisRelacion = new AnalisisRelacion();
            analisisRelacion.setActor1(relacionActorVO.getActor1());
            analisisRelacion.setActor2(relacionActorVO.getActor2());
            analisisRelacion.setCaso(caso);
            analisisRelacionService.analisisRelacioEliminar(analisisRelacion);
        }
        initRelacionActorVOConflicto();
    }
    
    private void usuarioSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }

    public List<Actor> getListaActoresXCaso() {
        return listaActoresXCaso;
    }

    public void setListaActoresXCaso(List<Actor> listaActoresXCaso) {
        this.listaActoresXCaso = listaActoresXCaso;
    }

    public List<RelacionActorVO> getListaActorAlianza() {
        return listaActorAlianza;
    }

    public void setListaActorAlianza(List<RelacionActorVO> listaActorAlianza) {
        this.listaActorAlianza = listaActorAlianza;
    }

    public Integer getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(Integer tipoLista) {
        this.tipoLista = tipoLista;
    }

    public String getActivarAlianzas() {
        return activarAlianzas;
    }

    public void setActivarAlianzas(String activarAlianzas) {
        this.activarAlianzas = activarAlianzas;
    }

    public String getActivarVinculoDebil() {
        return activarVinculoDebil;
    }

    public void setActivarVinculoDebil(String activarVinculoDebil) {
        this.activarVinculoDebil = activarVinculoDebil;
    }

    public String getActivarVinculoCercano() {
        return activarVinculoCercano;
    }

    public void setActivarVinculoCercano(String activarVinculoCercano) {
        this.activarVinculoCercano = activarVinculoCercano;
    }

    public List<RelacionActorVO> getListaActorVinculoCercano() {
        return listaActorVinculoCercano;
    }

    public void setListaActorVinculoCercano(List<RelacionActorVO> listaActorVinculoCercano) {
        this.listaActorVinculoCercano = listaActorVinculoCercano;
    }

    public List<RelacionActorVO> getListaActorVinculoDebil() {
        return listaActorVinculoDebil;
    }

    public void setListaActorVinculoDebil(List<RelacionActorVO> listaActorVinculoDebil) {
        this.listaActorVinculoDebil = listaActorVinculoDebil;
    }

    public List<RelacionActorVO> getListaActorTension() {
        return listaActorTension;
    }

    public void setListaActorTension(List<RelacionActorVO> listaActorTension) {
        this.listaActorTension = listaActorTension;
    }

    public List<RelacionActorVO> getListaActorConflicto() {
        return listaActorConflicto;
    }

    public void setListaActorConflicto(List<RelacionActorVO> listaActorConflicto) {
        this.listaActorConflicto = listaActorConflicto;
    }

    public String getActivarTension() {
        return activarTension;
    }

    public void setActivarTension(String activarTension) {
        this.activarTension = activarTension;
    }

    public String getActivarConflicto() {
        return activarConflicto;
    }

    public void setActivarConflicto(String activarConflicto) {
        this.activarConflicto = activarConflicto;
    }

    public RelacionActorVO getRelacionActorVOAlianza() {
        return relacionActorVOAlianza;
    }

    public void setRelacionActorVOAlianza(RelacionActorVO relacionActorVOAlianza) {
        this.relacionActorVOAlianza = relacionActorVOAlianza;
    }

    public RelacionActorVO getRelacionActorVOVinculoCercano() {
        return relacionActorVOVinculoCercano;
    }

    public void setRelacionActorVOVinculoCercano(RelacionActorVO relacionActorVOVinculoCercano) {
        this.relacionActorVOVinculoCercano = relacionActorVOVinculoCercano;
    }

    public RelacionActorVO getRelacionActorVOVinculoDebil() {
        return relacionActorVOVinculoDebil;
    }

    public void setRelacionActorVOVinculoDebil(RelacionActorVO relacionActorVOVinculoDebil) {
        this.relacionActorVOVinculoDebil = relacionActorVOVinculoDebil;
    }

    public RelacionActorVO getRelacionActorVOTension() {
        return relacionActorVOTension;
    }

    public void setRelacionActorVOTension(RelacionActorVO relacionActorVOTension) {
        this.relacionActorVOTension = relacionActorVOTension;
    }

    public RelacionActorVO getRelacionActorVOConflicto() {
        return relacionActorVOConflicto;
    }

    public void setRelacionActorVOConflicto(RelacionActorVO relacionActorVOConflicto) {
        this.relacionActorVOConflicto = relacionActorVOConflicto;
    }

    public List<SelectItem> getSelectItemActoresXCaso() {
        listaActoresXCaso = actorService.actorxCasoBuscar(1L);

        return selectItemActoresXCaso;
    }

    public void setSelectItemActoresXCaso(List<SelectItem> selectItemActoresXCaso) {
        this.selectItemActoresXCaso = selectItemActoresXCaso;
    }

    public Long getIdActor() {
        return idActor;
    }

    public void setIdActor(Long idActor) {
        this.idActor = idActor;
    }

    public List<ActividadActor> getActividadActors() {
        return actividadActors;
    }

    public void setActividadActors(List<ActividadActor> actividadActors) {
        this.actividadActors = actividadActors;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    public List<Tema> getTemasSeleccinados() {
        return temasSeleccinados;
    }

    public void setTemasSeleccinados(List<Tema> temasSeleccinados) {
        this.temasSeleccinados = temasSeleccinados;
    }

    public boolean isVerModalResumenDemandasxActor() {
        return verModalResumenDemandasxActor;
    }

    public void setVerModalResumenDemandasxActor(boolean verModalResumenDemandasxActor) {
        this.verModalResumenDemandasxActor = verModalResumenDemandasxActor;
    }

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    public AnalisisActor getAnalisisActor() {
        return analisisActor;
    }

    public void setAnalisisActor(AnalisisActor analisisActor) {
        this.analisisActor = analisisActor;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public List<AnalisisActor> getAnalisisActors() {
        return analisisActors;
    }

    public void setAnalisisActors(List<AnalisisActor> analisisActors) {
        this.analisisActors = analisisActors;
    }

    public Long getIdTemaGrafico() {
        return idTemaGrafico;
    }

    public void setIdTemaGrafico(Long idTemaGrafico) {
        this.idTemaGrafico = idTemaGrafico;
    }

    public List<AnalisisActorTema> getListaActoresXCasoGrafico() {
        return listaActoresXCasoGrafico;
    }

    public void setListaActoresXCasoGrafico(List<AnalisisActorTema> listaActoresXCasoGrafico) {
        this.listaActoresXCasoGrafico = listaActoresXCasoGrafico;
    }

    public AnalisisActorTema getAat() {
        return aat;
    }

    public void setAat(AnalisisActorTema aat) {
        this.aat = aat;
    }

    public List<AnalisisActorIntensidad> getAnalisisActorIntensidads() {
        return analisisActorIntensidads;
    }

    public void setAnalisisActorIntensidads(List<AnalisisActorIntensidad> analisisActorIntensidads) {
        this.analisisActorIntensidads = analisisActorIntensidads;
    }

    public List<Actor> getListaActoresXCasoIntensidad() {
        return listaActoresXCasoIntensidad;
    }

    public void setListaActoresXCasoIntensidad(List<Actor> listaActoresXCasoIntensidad) {
        this.listaActoresXCasoIntensidad = listaActoresXCasoIntensidad;
    }

    public String getCadenaAutocomplete() {
        return cadenaAutocomplete;
    }

    public void setCadenaAutocomplete(String cadenaAutocomplete) {
        this.cadenaAutocomplete = cadenaAutocomplete;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public boolean isVerAutoCompleteCaso() {
        return verAutoCompleteCaso;
    }

    public void setVerAutoCompleteCaso(boolean verAutoCompleteCaso) {
        this.verAutoCompleteCaso = verAutoCompleteCaso;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    public List<ProblemaDetalle> getListaProblemaDetalle() {
        return listaProblemaDetalle;
    }

    public void setListaProblemaDetalle(List<ProblemaDetalle> listaProblemaDetalle) {
        this.listaProblemaDetalle = listaProblemaDetalle;
    }

    public String getGraficaInner() {
        return graficaInner;
    }

    public void setGraficaInner(String graficaInner) {
        this.graficaInner = graficaInner;
    }

    public List<CasoActor> getListCasoActor() {
        return listCasoActor;
    }

    public void setListCasoActor(List<CasoActor> listCasoActor) {
        this.listCasoActor = listCasoActor;
    }

    public List<Actor> getListaActoresXCasoNivelAD() {
        return listaActoresXCasoNivelAD;
    }

    public void setListaActoresXCasoNivelAD(List<Actor> listaActoresXCasoNivelAD) {
        this.listaActoresXCasoNivelAD = listaActoresXCasoNivelAD;
    }

    public List<ContextoTipo> getListaTipoContexto() {
        return listaTipoContexto;
    }

    public void setListaTipoContexto(List<ContextoTipo> listaTipoContexto) {
        this.listaTipoContexto = listaTipoContexto;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

}
