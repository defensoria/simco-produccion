/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.dao;

import gob.dp.simco.comun.entity.Menu;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class MenuDaoImpl extends SqlSessionDaoSupport implements MenuDao{
    
    @Override
    public List<Menu> menuPadre() {
        return getSqlSession().selectList("gob.dp.simco.comun.dao.MenuDao.menuPadre");
    }

    @Override
    public List<Menu> menuHijo(int padre) {
        return getSqlSession().selectList("gob.dp.simco.comun.dao.MenuDao.menuHijo", padre);
    }

    @Override
    public Menu menuPadreOne(int codigo) {
        return getSqlSession().selectOne("gob.dp.simco.comun.dao.MenuDao.menuPadreOne", codigo);
    }
    
}
