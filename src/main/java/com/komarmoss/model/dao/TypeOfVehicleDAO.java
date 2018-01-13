package com.komarmoss.model.dao;

import com.komarmoss.model.entity.TypeOfVehicleEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TypeOfVehicleDAO {

    TypeOfVehicleEntity getItemById(final Integer id);

    List<TypeOfVehicleEntity> getAllItems();

    Integer saveItem(TypeOfVehicleEntity entity);

    void saveOrUpdateItem(TypeOfVehicleEntity entity);

    void updateItem(TypeOfVehicleEntity entity);

    void removeItem(TypeOfVehicleEntity entity);

    void removeItemById(final Integer id);
}
