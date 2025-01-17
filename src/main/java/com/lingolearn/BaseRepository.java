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
        return executeInTransaction(entityManager -> entityManager.merge(entity));
    }

    public Optional<T> findById(ID id) {
        return executeWithEntityManager(entityManager -> Optional.ofNullable(entityManager.find(entityClass, id)));
    }

    public List<T> findAll() {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return executeWithEntityManager(entityManager -> entityManager.createQuery(jpql, entityClass).getResultList());
    }

    public void delete(T entity) {
        executeInTransaction(entityManager -> {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            return null; // Void return
        });
    }

    private <R> R executeWithEntityManager(EntityManagerCallback<R> action) {
        try (EntityManager em = dbManager.createEntityManager()) {
            return action.execute(em);
        } catch (Exception e) {
            throw new RuntimeException("Operation failed", e);
        }
    }

    private <R> R executeInTransaction(EntityManagerCallback<R> action) {
        try (EntityManager em = dbManager.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                R result = action.execute(em);
                tx.commit();
                return result;
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw new RuntimeException("Transaction failed", e);
            }
        }
    }

    @FunctionalInterface
    private interface EntityManagerCallback<R> {
        R execute(EntityManager em);
    }
}
