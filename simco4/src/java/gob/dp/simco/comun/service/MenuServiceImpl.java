/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.dao.MenuDao;
import gob.dp.simco.comun.entity.Menu;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> menuPadre() {
        return menuDao.menuPadre();
    }

    @Override
    public List<Menu> menuHijo(int padre) {
        return menuDao.menuHijo(padre);
    }

    @Override
    public Menu menuPadreOne(int codigo) {
        return menuDao.menuPadreOne(codigo);
    }
    
}
