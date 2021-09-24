package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;

public class EditorialDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void guardarEditorial(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al guardar la editorial");
        }
    }

    public void modificarEditorial(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al modificar la editorial");
        }
    }

    public List<Editorial> buscarEditorial() throws Exception {
        try {
            List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e", Editorial.class).getResultList();
            return editoriales;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar las editoriales");
        }
    }

    public Editorial editorialPorId(Integer id) throws Exception {
        try {
            Editorial editorial = em.find(Editorial.class, id);
            return editorial;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar editorial por id");
        }
    }

    public List<Editorial> editorialPorNombre(String nombre) throws Exception {
        try {
            List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre", Editorial.class).setParameter("nombre", nombre).getResultList();
            return editoriales;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar editoriales por nombre");
        }
    }
}
