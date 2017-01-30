/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.administracion.parametro.controller;

import gob.dp.simco.administracion.parametro.entity.Catalogo;
import gob.dp.simco.administracion.parametro.service.CatalogoService;
import gob.dp.simco.comun.mb.AbstractManagedBean;
import java.io.Serializable;
import java.util.List;
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
public class ListasController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(ListasController.class);

    @Autowired
    private CatalogoService catalogoService;

    public List<Catalogo> listarCatalogoPadre(){
        List <Catalogo> lst =null;
        try {
            lst = catalogoService.listarCatalogoPadre();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lst==null) return null;

        return lst;
    }

}
