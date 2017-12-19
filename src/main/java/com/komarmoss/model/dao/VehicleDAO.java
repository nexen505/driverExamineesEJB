package com.komarmoss.model.dao;

import com.komarmoss.model.entity.VehicleEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface VehicleDAO {

    VehicleEntity getItemById(final Integer id);

    List<VehicleEntity> getAllItems();

    Integer saveItem(VehicleEntity entity);

    void saveOrUpdateItem(VehicleEntity entity);

    void updateItem(VehicleEntity entity);

    void removeItem(VehicleEntity entity);

    void removeItemById(final Integer id);
}
