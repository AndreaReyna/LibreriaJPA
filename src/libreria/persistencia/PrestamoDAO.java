package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Prestamo;

public class PrestamoDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void guardarPrestamo(Prestamo p) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al guardar el prestamo");
        }
    }

    public void modificarPrestamo(Prestamo p) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al modificar el prestamo");
        }
    }

    public List<Prestamo> imprimirPrestamos() throws Exception {
        try {
            List<Prestamo> pres = em.createQuery("SELECT p FROM Prestamo p", Prestamo.class).getResultList();
            return pres;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar los prestamos");
        }
    }

    public Prestamo prestamosPorId(Integer id) throws Exception {
        try {
            Prestamo p = em.find(Prestamo.class, id);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar el prestamo por id");
        }
    }

    public List<Prestamo> prestamosPorClientes(int id) throws Exception {
        try {
            List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p JOIN FETCH p.cliente c WHERE c.id LIKE :id", Prestamo.class).setParameter("id", id).getResultList();
            return prestamos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar los prestamos por cliente");
        }
    }
}
