package com.lingolearn;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import repos.DatabaseManager;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T, ID> {
    protected final Class<T> entityClass;
    protected final DatabaseManager dbManager;

    protected BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.dbManager = DatabaseManager.getInstance();
    }

    public T save(T entity) {
        EntityManager em = dbManager.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            entity = em.merge(entity);
            tx.commit();
            return entity;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Failed to save entity", e);
        } finally {
            em.close();
        }
    }

    public Optional<T> findById(ID id) {
        EntityManager em = dbManager.createEntityManager();
        try {
            T entity = em.find(entityClass, id);
            return Optional.ofNullable(entity);
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = dbManager.createEntityManager();
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            return em.createQuery(jpql, entityClass).getResultList();
        } finally {
            em.close();
        }
    }

    public void delete(T entity) {
        EntityManager em = dbManager.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.merge(entity));
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Failed to delete entity", e);
        } finally {
            em.close();
        }
    }
}
