package com.komarmoss.model.dao;

import com.komarmoss.model.entity.OwnerEntity;

import javax.ejb.Local;

@Local
public interface OwnerDAO extends AbstractDAO<OwnerEntity, Integer> {
}
