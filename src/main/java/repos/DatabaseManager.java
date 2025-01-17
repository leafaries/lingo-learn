package repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseManager {
    private static final DatabaseManager instance = new DatabaseManager();
    private final EntityManagerFactory emf;

    private DatabaseManager() {
        emf = Persistence.createEntityManagerFactory("LingoLearnPU");
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
