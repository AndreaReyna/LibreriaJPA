package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Cliente;

public class ClienteDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    public void guardarCliente(Cliente c) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al guardar el cliente");
        }
    }

    public void modificarCliente(Cliente c) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al modificar el cliente");
        }
    }

    public List<Cliente> buscarCliente() throws Exception {
        try {
            List<Cliente> cli = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
            return cli;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar los clientes");
        }
    }

    public Cliente clientePorId(int id) throws Exception {
        try {
            Cliente c = em.find(Cliente.class, id);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar el cliente por ID");
        }
    }

    public Cliente clientePorDNI(Long dni) throws Exception {
        try {
            List<Cliente> cl = em.createQuery("SELECT l FROM Cliente l WHERE l.documento LIKE :dni", Cliente.class).setParameter("dni", dni).getResultList();
            Cliente c = null;
            if (cl.size() > 0) {
                c = cl.get(0);
            }
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar el cliente por DNI");
        }
    }
}
