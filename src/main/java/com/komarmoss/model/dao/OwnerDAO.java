package com.komarmoss.model.dao;

import com.komarmoss.model.entity.OwnerEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OwnerDAO {

    OwnerEntity getItemById(final Integer id);

    List<OwnerEntity> getAllItems();

    Integer saveItem(OwnerEntity entity);

    void saveOrUpdateItem(OwnerEntity entity);

    void updateItem(OwnerEntity entity);

    void removeItem(OwnerEntity entity);

    void removeItemById(final Integer id);
}
