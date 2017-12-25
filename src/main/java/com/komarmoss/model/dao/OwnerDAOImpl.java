package com.komarmoss.model.dao;

import com.komarmoss.model.entity.OwnerEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OwnerDAOImpl implements OwnerDAO {

    @PersistenceContext(unitName = "exdr_examinees")
    private EntityManager entityManager;

    @Override
    public OwnerEntity getItemById(final Integer id) {
        return id != null ? entityManager.find(OwnerEntity.class, id) : null;
    }

    @Override
    public List<OwnerEntity> getAllItems() {
        return entityManager.createQuery("select c from OwnerEntity c", OwnerEntity.class).getResultList();
    }

    @Override
    public Integer saveItem(OwnerEntity entity) {
        if (entity != null) {
            EntityTransaction trx = entityManager.getTransaction();
            trx.begin();
            entityManager.persist(entity);
            trx.commit();
            return entity.getId();
        } else return null;
    }

    @Override
    public void saveOrUpdateItem(OwnerEntity entity) {
        if (entity != null) {
            if (entity.getId() == null) saveItem(entity);
            else updateItem(entity);
        }
    }

    @Override
    public void updateItem(OwnerEntity entity) {
        if (entity != null) {
            EntityTransaction trx = entityManager.getTransaction();
            trx.begin();
            entityManager.merge(entity);
            trx.commit();
        }
    }

    @Override
    public void removeItem(OwnerEntity entity) {
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
