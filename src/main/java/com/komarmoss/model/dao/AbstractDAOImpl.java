package com.komarmoss.model.dao;

import com.komarmoss.model.entity.Identifiable;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDAOImpl<T extends Identifiable,
        ID extends Serializable> implements AbstractDAO<T, ID> {

    private final transient Class<T> entityClass;

    AbstractDAOImpl(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public List<T> getAllItems() {
        return getEntityManager().createQuery("select c from " + entityClass.getSimpleName() + " c", entityClass).getResultList();
    }

    public T getItemById(final ID id) {
        return id != null ? getEntityManager().find(entityClass, id) : null;
    }

    public ID saveItem(T entity) {
        if (entity == null) return null;
        getEntityManager().persist(entity);
        return (ID) entity.getId();
    }

    public ID saveOrUpdateItem(T entity) {
        if (entity == null) return null;
        return entity.getId() == null ? saveItem(entity) : updateItem(entity);
    }

    public ID updateItem(T entity) {
        if (entity == null) return null;
        return (ID) getEntityManager().merge(entity).getId();
    }

    public void removeItem(T entity) {
        if (entity != null)
            getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
    }

    public void removeItemById(final ID id) {
        if (id != null)
            removeItem(getItemById(id));
    }


}
