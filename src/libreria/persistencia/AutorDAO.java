package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Autor;

public class AutorDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void guardarAutor(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al guardar el autor");
        }
    }

    public void modificarAutor(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al modificar el autor");
        }
    }

    public List<Autor> buscarAutor() throws Exception {
        try {
            List<Autor> autores = em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
            return autores;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar los autores");
        }
    }

    public Autor autorPorId(Integer id) throws Exception {
        try {
            Autor autor = em.find(Autor.class, id);
            return autor;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar el autor por id");
        }
    }

    public List<Autor> autorPornombre(String nombre) throws Exception {
        try {
            List<Autor> autores = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre", Autor.class).setParameter("nombre", nombre).getResultList();
            return autores;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar el autor por nombre");
        }
    }
}
