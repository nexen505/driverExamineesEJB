package com.komarmoss.model.dao;

import com.komarmoss.model.entity.OwnerEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "ownerDAO")
public class OwnerDAOImpl extends AbstractDAOImpl<OwnerEntity, Integer> implements OwnerDAO {

    @PersistenceContext(unitName = "exdr_examinees")
    private EntityManager entityManager;

    public OwnerDAOImpl() {
        super(OwnerEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
