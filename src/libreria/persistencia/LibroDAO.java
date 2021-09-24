package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Libro;

public class LibroDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void guardarLibro(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al guardar el libro");
        }
    }

    public void modificarLibro(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al modificar el libro");
        }
    }

    public List<Libro> buscarLibro() throws Exception {
        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
            return libros;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar los libros");
        }
    }

    public Libro LibroPorISBN(Long isbn) throws Exception {
        try {
            Libro libro = em.find(Libro.class, isbn);
            return libro;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar el libro por isbn");
        }
    }

    public List<Libro> buscarLibroPorAutor(String nombreAutor) throws Exception {
        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN FETCH l.autor a WHERE a.nombre LIKE :nombreAutor", Libro.class).setParameter("nombreAutor", nombreAutor).getResultList();
            return libros;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar los libros");
        }
    }

    public List<Libro> buscarLibroPorEditorial(String nombreEd) throws Exception {
        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN FETCH l.editoral e WHERE e.nombre LIKE :nombreEd", Libro.class).setParameter("nombreEd", nombreEd).getResultList();
            return libros;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar los libros");
        }
    }

    public List<Libro> buscarLibroPorNombre(String nombre) throws Exception {
        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :nombre", Libro.class).setParameter("nombre", nombre).getResultList();
            return libros;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar los libros");
        }
    }
}
