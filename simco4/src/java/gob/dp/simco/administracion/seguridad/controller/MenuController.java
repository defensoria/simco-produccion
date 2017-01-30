package gob.dp.simco.administracion.seguridad.controller;

import gob.dp.simco.administracion.parametro.controller.CatalogoController;
import gob.dp.simco.administracion.seguridad.entity.Usuario;
import gob.dp.simco.analisis.controller.AnalisisController;
import gob.dp.simco.analisis.controller.GraficController;
import gob.dp.simco.comun.controller.BusquedaController;
import gob.dp.simco.comun.controller.SessionStorageController;
import gob.dp.simco.comun.entity.Menu;
import gob.dp.simco.comun.service.MenuService;
import gob.dp.simco.intervencion.controller.IntervencionController;
import gob.dp.simco.investigacion.controller.InvestigacionController;
import gob.dp.simco.registro.controller.ActorController;
import gob.dp.simco.registro.controller.CasoController;
import gob.dp.simco.registro.controller.RegistroController;
import gob.dp.simco.registro.entity.ActaAcuerdoDetalle;
import gob.dp.simco.registro.entity.Caso;
import gob.dp.simco.registro.service.ActaAcuerdoDetalleService;
import gob.dp.simco.reporte.controller.ReporteSimcoController;
import gob.dp.simco.reporte.controller.ReporteGeneralController;
import gob.dp.simco.seguimiento.controller.SeguimientoAcuerdoController;
import gob.dp.simco.seguimiento.entity.Alerta;
import gob.dp.simco.seguimiento.service.AlertaService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class MenuController implements Serializable {

    private List<Menu> menuPadre;

    private List<Menu> menuHijo;

    private List<Menu> menuNieto;

    private Caso caso;

    private Usuario usuarioSession;

    private List<Alerta> listaAlertaEjecutadas;

    private List<ActaAcuerdoDetalle> acuerdoDetalles;

    @Autowired
    private MenuService menuService;

    @Autowired
    private AlertaService alertaService;

    @Autowired
    private ActaAcuerdoDetalleService actaAcuerdoDetalleService;

    public void cargarMenu() {
        cargarBusquedaGeneral();
        caso = new Caso();
        menuPadre = cargarMenuPadre();
        cargarPagina(0);
    }
    
    private List<Menu> cargarMenuPadre(){
        FacesContext context = FacesContext.getCurrentInstance();
        SeguridadUtilController seguridadUtilController = (SeguridadUtilController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "seguridadUtilController");
        List<Menu> listaMenu = new ArrayList<>();
        
            if(seguridadUtilController.tieneRecurso("REC_001")){
                listaMenu.add(menuService.menuPadreOne(1));
            }
            if(seguridadUtilController.tieneRecurso("REC_002")){
                listaMenu.add(menuService.menuPadreOne(2));
            }
            if(seguridadUtilController.tieneRecurso("REC_003")){
                listaMenu.add(menuService.menuPadreOne(5));
            }
            if(seguridadUtilController.tieneRecurso("REC_004")){
                listaMenu.add(menuService.menuPadreOne(19));
            }
            if(seguridadUtilController.tieneRecurso("REC_005")){
                listaMenu.add(menuService.menuPadreOne(12));
            }
            if(seguridadUtilController.tieneRecurso("REC_006")){
                listaMenu.add(menuService.menuPadreOne(15));
            }
            if(seguridadUtilController.tieneRecurso("REC_008")){
                listaMenu.add(menuService.menuPadreOne(35));
            }
            if(seguridadUtilController.tieneRecurso("REC_009")){
                listaMenu.add(menuService.menuPadreOne(36));
            }    
        
        return listaMenu;
    }

    private void cargarBusquedaGeneral() {
        FacesContext context = FacesContext.getCurrentInstance();
        BusquedaController busquedaController = (BusquedaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaController");
        busquedaController.inicioBusqueda();
    }

    public String cargarAlertasCabecera() {
        usuarioSession();
        listaAlertaEjecutadas = new ArrayList<>();
        ActaAcuerdoDetalle ad = new ActaAcuerdoDetalle();
        ad.setUsuarioRegistro(usuarioSession.getCodigo());
        acuerdoDetalles = actaAcuerdoDetalleService.actaAcuerdoDetalleSeguimiento(ad);
        for (ActaAcuerdoDetalle aad : acuerdoDetalles) {
            verAlertas(aad);
        }
        return "seguimientoAcuerdo";
    }

    public void verAlertas(ActaAcuerdoDetalle acuerdoDetalle) {
        listaAlertaEjecutadas.addAll(alertaService.alertaBuscarUsuario(acuerdoDetalle.getId()));
    }

    private void usuarioSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }

    public String cargarPagina(int codigoPagina) {
        if (codigoPagina == 0) {
            cargarAlertasCabecera();
        }

        FacesContext context = FacesContext.getCurrentInstance();
        menuHijo = null;
        menuNieto = new ArrayList<>();
        if (codigoPagina == 0) {
            SessionStorageController sessionStorageController = (SessionStorageController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "sessionStorageController");
            sessionStorageController.cargarPagina();
        }

        if (codigoPagina == 1) {
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            return registroController.cargarPagina();
        }

        if (codigoPagina == 2) {
            ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
            menuHijo = menuService.menuHijo(2);
            return actorController.cargarPagina();
        }

        if (codigoPagina == 3) {
            ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
            menuHijo = menuService.menuHijo(2);
            return actorController.cargarPagina();
        }

        if (codigoPagina == 4) {
            ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
            menuHijo = menuService.menuHijo(2);
            return actorController.cargarPaginaBusqueda();
        }

        if (codigoPagina == 11) {
            ActorController actorController = (ActorController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "actorController");
            menuHijo = menuService.menuHijo(2);
            return actorController.cargarPaginaActoresSigues();
        }

        if (codigoPagina == 5) {
            CasoController casoController = (CasoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "casoController");
            menuHijo = menuService.menuHijo(5);
            return casoController.cargarPaginaCasosSigues();
        }

        if (codigoPagina == 6) {
            IntervencionController intervencionController = (IntervencionController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "intervencionController");
            return intervencionController.cargarPaginaIntervencion();
        }

        if (codigoPagina == 7) {
            SeguimientoAcuerdoController seguimientoAcuerdoController = (SeguimientoAcuerdoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "seguimientoAcuerdoController");
            return seguimientoAcuerdoController.cargarPagina();
        }

        if (codigoPagina == 8) {
            AnalisisController analisisController = (AnalisisController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "analisisController");
            menuHijo = menuService.menuHijo(8);
            return analisisController.cargarPagina();
        }

        if (codigoPagina == 9) {
            AnalisisController analisisController = (AnalisisController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "analisisController");
            menuHijo = menuService.menuHijo(8);
            return analisisController.cargarPagina();
        }

        if (codigoPagina == 10) {
            AnalisisController analisisController = (AnalisisController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "analisisController");
            menuHijo = menuService.menuHijo(8);
            return analisisController.cargarPaginaProblemas();
        }

        if (codigoPagina == 12) {
            BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
            menuHijo = menuService.menuHijo(12);
            return busquedaUsuarioController.cargarPagina();
        }

        if (codigoPagina == 13) {
            BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
            menuHijo = menuService.menuHijo(12);
            return busquedaUsuarioController.cargarPagina();
        }

        if (codigoPagina == 14) {
            RolController rolController = (RolController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "rolController");
            menuHijo = menuService.menuHijo(12);
            return rolController.cargarPagina();
        }

        if (codigoPagina == 15) {
            CatalogoController catalogoController = (CatalogoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "catalogoController");
            menuHijo = menuService.menuHijo(15);
            return catalogoController.cargarPagina();
        }

        if (codigoPagina == 33) {
            CatalogoController catalogoController = (CatalogoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "catalogoController");
            menuHijo = menuService.menuHijo(15);
            return catalogoController.cargarPagina();
        }

        if (codigoPagina == 34) {
            CatalogoController catalogoController = (CatalogoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "catalogoController");
            menuHijo = menuService.menuHijo(15);
            return catalogoController.cargarPaginaDerivados();
        }

        if (codigoPagina == 16) {
            CasoController casoController = (CasoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "casoController");
            menuHijo = menuService.menuHijo(5);
            return casoController.cargarPaginaCasosSigues();
        }

        if (codigoPagina == 17) {
            GraficController graficController = (GraficController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "graficController");
            menuHijo = menuService.menuHijo(8);
            return graficController.cargarPaginaRelacion();
        }

        if (codigoPagina == 32) {
            AnalisisController analisisController = (AnalisisController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "analisisController");
            menuHijo = menuService.menuHijo(27);
            menuNieto = menuService.menuHijo(26);
            return analisisController.cargarContexto(caso);
        }

        if (codigoPagina == 18) {
            GraficController graficController = (GraficController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "graficController");
            menuHijo = menuService.menuHijo(8);
            return graficController.cargarPaginasRelacionTemas();
        }

        if (codigoPagina == 19) {
            InvestigacionController investigacionController = (InvestigacionController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "investigacionController");
            menuHijo = menuService.menuHijo(19);
            return investigacionController.cargarCrearInvestigacion();
        }

        if (codigoPagina == 20) {
            InvestigacionController investigacionController = (InvestigacionController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "investigacionController");
            menuHijo = menuService.menuHijo(19);
            return investigacionController.cargarCrearInvestigacion();
        }

        if (codigoPagina == 21) {
            InvestigacionController investigacionController = (InvestigacionController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "investigacionController");
            menuHijo = menuService.menuHijo(19);
            return investigacionController.cargarSigues();
        }

        if (codigoPagina == 22) {
            CasoController casoController = (CasoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "casoController");
            menuHijo = menuService.menuHijo(27);
            //return casoController.cargarPanel();
            return casoController.cargarPanel(caso.getId());

        }

        if (codigoPagina == 23) {
            AnalisisController analisisController = (AnalisisController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "analisisController");
            menuHijo = menuService.menuHijo(27);
            menuNieto = menuService.menuHijo(26);
            return analisisController.cargarPaginaCaso(caso);

        }

        if (codigoPagina == 24) {
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            menuHijo = menuService.menuHijo(27);
            return registroController.cargarNoticiasCaso();
        }

        if (codigoPagina == 25) {
            IntervencionController intervencionController = (IntervencionController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "intervencionController");
            menuHijo = menuService.menuHijo(27);
            return intervencionController.cargarPaginaIntervencionDetalleCaso(caso.getId());
        }

        if (codigoPagina == 26) {
            SeguimientoAcuerdoController seguimientoAcuerdoController = (SeguimientoAcuerdoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "seguimientoAcuerdoController");
            menuHijo = menuService.menuHijo(27);
            return seguimientoAcuerdoController.cargarPaginaCaso(caso.getId());
        }

        if (codigoPagina == 27) {
            menuHijo = menuService.menuHijo(27);
            return null;
        }

        if (codigoPagina == 28) {
            AnalisisController analisisController = (AnalisisController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "analisisController");
            menuHijo = menuService.menuHijo(27);
            menuNieto = menuService.menuHijo(26);
            return analisisController.cargarPaginaCaso(caso);
        }

        if (codigoPagina == 29) {
            AnalisisController analisisController = (AnalisisController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "analisisController");
            menuHijo = menuService.menuHijo(27);
            menuNieto = menuService.menuHijo(26);
            return analisisController.cargarPaginaProblemasCaso(caso);
        }

        if (codigoPagina == 30) {
            GraficController graficController = (GraficController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "graficController");
            menuHijo = menuService.menuHijo(27);
            menuNieto = menuService.menuHijo(26);
            return graficController.cargarPaginaRelacionCaso2(caso);
        }

        if (codigoPagina == 31) {
            GraficController graficController = (GraficController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "graficController");
            menuHijo = menuService.menuHijo(27);
            menuNieto = menuService.menuHijo(26);
            return graficController.cargarPaginasRelacionTemasCaso(caso);
        }

        if (codigoPagina == 35) {
            ReporteSimcoController reporteSimcoController = (ReporteSimcoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "reporteSimcoController");
            return reporteSimcoController.cargarReporteCaso();
        }

        if (codigoPagina == 36) {
            ReporteGeneralController reporteGeneralController = (ReporteGeneralController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "reporteGeneralController");
            return reporteGeneralController.reportePublic();
        }

        return null;
    }

    public List<Menu> getMenuPadre() {
        return menuPadre;
    }

    public void setMenuPadre(List<Menu> menuPadre) {
        this.menuPadre = menuPadre;
    }

    public List<Menu> getMenuHijo() {
        return menuHijo;
    }

    public void setMenuHijo(List<Menu> menuHijo) {
        this.menuHijo = menuHijo;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public List<Menu> getMenuNieto() {
        return menuNieto;
    }

    public void setMenuNieto(List<Menu> menuNieto) {
        this.menuNieto = menuNieto;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public List<Alerta> getListaAlertaEjecutadas() {
        return listaAlertaEjecutadas;
    }

    public void setListaAlertaEjecutadas(List<Alerta> listaAlertaEjecutadas) {
        this.listaAlertaEjecutadas = listaAlertaEjecutadas;
    }

    public List<ActaAcuerdoDetalle> getAcuerdoDetalles() {
        return acuerdoDetalles;
    }

    public void setAcuerdoDetalles(List<ActaAcuerdoDetalle> acuerdoDetalles) {
        this.acuerdoDetalles = acuerdoDetalles;
    }

}
