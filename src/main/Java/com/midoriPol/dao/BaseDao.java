package com.midoriPol.dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BaseDao {
    private static final Logger log = LogManager.getLogger(BaseDao.class);
    private static EntityManagerFactory factory;
    protected EntityManager entityManager;

    BaseDao() {
        entityManager = factory.createEntityManager();
    }

    /**
     * Initializer the EntityManagerFactory with the specified persistence unit name.
     *
     * @param persistenceUnitName The name of the persistence unit to be used
     */
    public static void initFactory(String persistenceUnitName) {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        log.trace("Persistence unit factory opened");
    }

    /**
     * Retrieves an entity object by its ID from the database.
     *
     * @param entityClass The class representing the type of the entity object.
     * @param id          The ID of the entity object.
     * @param <T>         The type of the entity object to retrieve.
     * @return The entity object corresponding to the provided identifier, or null.
     */
    public <T> T getById(Class<T> entityClass, Long id) {
        return entityManager.find(entityClass, id);
    }

    /**
     * Retrieves a list of all entity objects from the database.
     *
     * @param entityClass The classe representing the type of entity objects.
     * @param <T>         The type if entity object to retrieve.
     * @return A list of entity objects.
     */
    public <T> List<T> getAll(Class<T> entityClass) {
        String query = "select t from " + entityClass.getName() + " t";
        return entityManager.createQuery(query, entityClass)
                .getResultList();
    }

    /**
     * Saves an entity object in the database.
     *
     * @param entity The entity object to save.
     */
    public void save(Object entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive() || entityManager.getTransaction().getRollbackOnly())
                entityManager.getTransaction().rollback();
        }
    }

    /**
     * Removes an entity object from the database.
     *
     * @param entity The entity object to remove.
     */
    public void remove(Object entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive() || entityManager.getTransaction().getRollbackOnly())
                entityManager.getTransaction().rollback();
        }
    }

    /**
     * Closes entity manager.
     */
    public void closeEntityManager() {
        entityManager.close();
    }

    /**
     * Closes the EntityManagerFactory if it is open.
     */
    public static void closeFactory() {
        if (factory != null && factory.isOpen())
            factory.close();
        log.trace("Persistence unit factory closed");
    }
}