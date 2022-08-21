package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {

    private static EntityManagerFactory emf;

    private static EntityManager entityManager;

    private HibernateUtil() {
    }

    public static EntityManagerFactory getEmf() {

        if (emf == null)
            emf = Persistence.createEntityManagerFactory("bus_terminal");

        return emf;

    }

    public static EntityManager getEntityManager() {

        if (entityManager == null) {
            entityManager = getEmf().createEntityManager();
        }

        return entityManager;

    }

    public static void closeEntityManager() {

        if (entityManager != null)
            entityManager.close();

    }

}