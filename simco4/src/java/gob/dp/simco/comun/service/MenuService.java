/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.service;

import gob.dp.simco.comun.entity.Menu;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface MenuService {
    
    public List<Menu> menuPadre();
    
    public List<Menu> menuHijo(int padre);
    
    public Menu menuPadreOne(int codigo);
}
