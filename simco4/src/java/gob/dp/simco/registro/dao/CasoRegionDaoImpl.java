/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.dao;

import gob.dp.simco.registro.entity.CasoRegion;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class CasoRegionDaoImpl extends SqlSessionDaoSupport  implements CasoRegionDao{

    @Override
    public List<CasoRegion> casoRegionBuscar(Long idCaso) {
        return getSqlSession().selectList("gob.dp.simco.registro.dao.CasoRegionDao.casoRegionBuscar",idCaso);
    }

    @Override
    public void casoRegionInsertar(CasoRegion casoRegion) {
        getSqlSession().insert("gob.dp.simco.registro.dao.CasoRegionDao.casoRegionInsertar", casoRegion);
    }

    @Override
    public void casoRegionUpdate(Long id) {
        getSqlSession().update("gob.dp.simco.registro.dao.CasoRegionDao.casoRegionUpdate", id);
    }

    @Override
    public void casoRegionUpdatePrincipal(Long id) {
        getSqlSession().update("gob.dp.simco.registro.dao.CasoRegionDao.casoRegionUpdatePrincipal", id);
    }
    
}
