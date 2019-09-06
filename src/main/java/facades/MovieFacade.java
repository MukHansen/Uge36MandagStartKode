package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getAllMovieCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long movieCount = (long) em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return movieCount;
        } finally {
            em.close();
        }

    }

    public List<MovieDTO> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            List<MovieDTO> movies = em.createQuery("SELECT m FROM Movie m").getResultList();
            return movies;
        } finally {
            em.close();
        }

    }

    public List<MovieDTO> getMovieByName(String name) {
        
                EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MovieDTO> query = em.createQuery("SELECT m FROM Movie m WHERE m.name = :name", MovieDTO.class)
                    .setParameter("name", name);
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public MovieDTO getMovieById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Movie movie = em.find(Movie.class, id);
            MovieDTO movieDTO = new MovieDTO(movie);
            return movieDTO;
        } finally {
            em.close();
        }
    }

    public void fillMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(new Movie(2004, "TogC", new String[]{"Torben", "Chris"}));
            em.persist(new Movie(1999, "IogB", new String[]{"Ib", "Bo"}));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
