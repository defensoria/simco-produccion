/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.administracion.parametro.dao;

import gob.dp.simco.administracion.parametro.entity.Catalogo;
import gob.dp.simco.administracion.parametro.bean.FiltroCatalogo;

import java.util.List;

/**
 *
 * @author Administrador
 */
public interface CatalogoDao {
    
    public List<Catalogo> buscarCatalogoPadre(FiltroCatalogo filtroCatalogo);
    
    public Integer getTotalBuscarCatalogoPadre(FiltroCatalogo filtroCatalogo);
    
    public List<Catalogo> buscarCatalogoHijo(FiltroCatalogo filtroCatalogo);
    
    public Integer getTotalBuscarCatalogoHijo(FiltroCatalogo filtroCatalogo);

    public Catalogo viewCatalogoPadre(Catalogo catalogo);
    
    public Catalogo viewCatalogoHijo(Catalogo catalogo);
    
    public void nuevoCatalogoPadre(Catalogo catalogo);
    
    public Integer generarCodigoCatalogoPadre();

    public void nuevoCatalogoHijo(Catalogo catalogo);
    
    public Integer generarCodigoCatalogoHijo();

    public void updateCatalogoPadre(Catalogo catalogo);
    
    public void updateCatalogoHijo(Catalogo catalogo);
    
    public void updateEstadoCatalogoHijo(Catalogo catalogo);

    public List<Catalogo> listarCatalogoPadre();
    
    public List<Catalogo> parametroPorPadre(long idPadre);
    
}
