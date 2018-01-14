package com.komarmoss.model.dao;

import com.komarmoss.model.entity.TypeOfVehicleEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "typeOfVehicleDAO")
public class TypeOfVehicleDAOImpl extends AbstractDAOImpl<TypeOfVehicleEntity, Integer> implements TypeOfVehicleDAO {

    @PersistenceContext(unitName = "exdr_examinees")
    private EntityManager entityManager;

    public TypeOfVehicleDAOImpl() {
        super(TypeOfVehicleEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
