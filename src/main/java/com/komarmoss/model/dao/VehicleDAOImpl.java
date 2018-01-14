package com.komarmoss.model.dao;

import com.komarmoss.model.entity.VehicleEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "vehicleDAO")
public class VehicleDAOImpl extends AbstractDAOImpl<VehicleEntity, Integer> implements VehicleDAO {

    @PersistenceContext(unitName = "exdr_examinees")
    private EntityManager entityManager;

    public VehicleDAOImpl() {
        super(VehicleEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
