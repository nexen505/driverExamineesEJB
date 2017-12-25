package com.komarmoss.bean;

import com.komarmoss.model.dao.OwnerDAO;
import com.komarmoss.model.entity.OwnerEntity;
import com.komarmoss.model.vo.OwnerVO;

import javax.ejb.*;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OwnerServiceImpl implements OwnerService {

    @EJB(beanName = "ownerDAO")
    private OwnerDAO ownerDAO;

    @Override
    public List<OwnerVO> findOwners() {
        return ownerDAO.getAllItems()
                .parallelStream()
                .map(OwnerVO::new)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerVO findOwner(Integer id) {
        return new OwnerVO(ownerDAO.getItemById(id));
    }

    @Override
    public OwnerVO saveOrUpdateOwner(OwnerVO owner) {
        OwnerEntity ownerEntity = owner.createEntity();
        ownerDAO.saveOrUpdateItem(ownerEntity);
        Integer id = ownerEntity.getId();
        return findOwner(id);
    }

    @Override
    public boolean removeOwner(Integer id) {
        if (id != null) {
            ownerDAO.removeItemById(id);
            return true;
        }
        return false;
    }
}
