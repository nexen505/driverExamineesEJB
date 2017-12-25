package com.komarmoss.model.dao;

import com.komarmoss.model.entity.VehicleEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VehicleDAOImpl implements VehicleDAO {

    @PersistenceContext(unitName = "exdr_examinees")
    private EntityManager entityManager;

    @Override
    public VehicleEntity getItemById(final Integer id) {
        return id != null ? entityManager.find(VehicleEntity.class, id) : null;
    }

    @Override
    public List<VehicleEntity> getAllItems() {
        return entityManager.createQuery("select c from VehicleEntity c", VehicleEntity.class).getResultList();
    }

    @Override
    public Integer saveItem(VehicleEntity entity) {
        if (entity != null) {
            EntityTransaction trx = entityManager.getTransaction();
            trx.begin();
            entityManager.persist(entity);
            trx.commit();
            return entity.getId();
        } else return null;
    }

    @Override
    public void saveOrUpdateItem(VehicleEntity entity) {
        if (entity != null) {
            if (entity.getId() == null) saveItem(entity);
            else updateItem(entity);
        }
    }

    @Override
    public void updateItem(VehicleEntity entity) {
        if (entity != null) {
            EntityTransaction trx = entityManager.getTransaction();
            trx.begin();
            entityManager.merge(entity);
            trx.commit();
        }
    }

    @Override
    public void removeItem(VehicleEntity entity) {
        if (entity != null) {
            EntityTransaction trx = entityManager.getTransaction();
            trx.begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            trx.commit();
        }
    }

    @Override
    public void removeItemById(final Integer id) {
        if (id != null) {
            removeItem(getItemById(id));
        }
    }
}
