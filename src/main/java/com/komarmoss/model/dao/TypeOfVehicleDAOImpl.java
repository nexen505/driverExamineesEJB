package com.komarmoss.model.dao;

import com.komarmoss.model.entity.TypeOfVehicleEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TypeOfVehicleDAOImpl implements TypeOfVehicleDAO {

    @PersistenceContext(unitName = "exdr_examinees")
    private EntityManager entityManager;

    @Override
    public TypeOfVehicleEntity getItemById(final Integer id) {
        return id != null ? entityManager.find(TypeOfVehicleEntity.class, id) : null;
    }

    @Override
    public List<TypeOfVehicleEntity> getAllItems() {
        return entityManager.createQuery("select c from TypeOfVehicleEntity c", TypeOfVehicleEntity.class).getResultList();
    }

    @Override
    public Integer saveItem(TypeOfVehicleEntity entity) {
        if (entity != null) {
            entityManager.persist(entity);
            return entity.getId();
        } else return null;
    }

    @Override
    public void saveOrUpdateItem(TypeOfVehicleEntity entity) {
        if (entity != null) {
            if (entity.getId() == null) saveItem(entity);
            else updateItem(entity);
        }
    }

    @Override
    public void updateItem(TypeOfVehicleEntity entity) {
        if (entity != null) entityManager.merge(entity);
    }

    @Override
    public void removeItem(TypeOfVehicleEntity entity) {
        if (entity != null) entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public void removeItemById(final Integer id) {
        if (id != null) {
            removeItem(getItemById(id));
        }
    }
}
