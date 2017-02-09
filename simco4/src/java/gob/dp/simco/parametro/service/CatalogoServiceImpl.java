/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.parametro.service;
import gob.dp.simco.parametro.entity.Catalogo;
import gob.dp.simco.parametro.bean.FiltroCatalogo;

import gob.dp.simco.parametro.dao.CatalogoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */

@Service("catalogoService")
public class CatalogoServiceImpl implements CatalogoService
{
    @Autowired
    private CatalogoDao catalogoDao;

    @Override
    public List<Catalogo> buscarCatalogoPadre(FiltroCatalogo filtroCatalogo){
        return catalogoDao.buscarCatalogoPadre(filtroCatalogo);
    }

    @Override
    public Integer getTotalBuscarCatalogoPadre(FiltroCatalogo filtroCatalogo){
        return catalogoDao.getTotalBuscarCatalogoPadre(filtroCatalogo);
    }

    @Override
    public List<Catalogo> buscarCatalogoHijo(FiltroCatalogo filtroCatalogo){
        return catalogoDao.buscarCatalogoHijo(filtroCatalogo);
    }

    @Override
    public Integer getTotalBuscarCatalogoHijo(FiltroCatalogo filtroCatalogo){
        return catalogoDao.getTotalBuscarCatalogoHijo(filtroCatalogo);
    }

    @Override
    public Catalogo viewCatalogoPadre(Catalogo catalogo){
        return catalogoDao.viewCatalogoPadre(catalogo);
    }

    @Override
    public Catalogo viewCatalogoHijo(Catalogo catalogo){
        return catalogoDao.viewCatalogoHijo(catalogo);
    }

    @Override
    public void nuevoCatalogoPadre(Catalogo catalogo){
        catalogo.setNumParametro(catalogoDao.generarCodigoCatalogoPadre());
        catalogoDao.nuevoCatalogoPadre(catalogo);
    }

    @Override
    public void nuevoCatalogoHijo(Catalogo catalogo){
        catalogo.setNumParametro(catalogoDao.generarCodigoCatalogoHijo());
        catalogoDao.nuevoCatalogoHijo(catalogo);
    }

    @Override
    public void updateCatalogoPadre(Catalogo catalogo){
        catalogoDao.updateCatalogoPadre(catalogo);
    }

    @Override
    public void updateCatalogoHijo(Catalogo catalogo){
        catalogoDao.updateCatalogoHijo(catalogo);
    }

    @Override
    public void updateEstadoCatalogoHijo(Catalogo catalogo){
        catalogoDao.updateEstadoCatalogoHijo(catalogo);
    }

    /**
     * @return the parametroDao
     */
    public CatalogoDao getCatalogoDao() {
        return catalogoDao;
    }

    public void setCatalogoDao(CatalogoDao catalogoDao) {
        this.catalogoDao = catalogoDao;
    }

    @Override
    public List<Catalogo> listarCatalogoPadre() {
        return catalogoDao.listarCatalogoPadre();
    }

    @Override
    public List<Catalogo> parametroPorPadre(long idPadre) {
        return catalogoDao.parametroPorPadre(idPadre);
    }
    
}