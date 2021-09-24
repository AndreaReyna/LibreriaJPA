package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.persistencia.ClienteDAO;

public class ClienteServicio {

    ClienteDAO cd = new ClienteDAO();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void crearCliente() throws Exception {
        try {
            String aux = "";
            long dni = 0;
            String tel = "";
            while (!(aux.length() == 8)) {
                System.out.println("Ingrese DNI");
                dni = leer.nextLong();
                aux = String.valueOf(dni);
                if (!(aux.length() == 8)) {
                    System.out.println("El DNI debe ser de 8 digitos");
                }
            }
            Cliente c = buscarClienteDni((Long) dni);
            if (!(c == null)) {
                throw new Exception("El DNI ya está en la base de datos");
            }
            System.out.println("Ingrese el nombre");
            String nombre = leer.next();
            System.out.println("Ingrese el apellido");
            String apellido = leer.next();
            while (tel.length() < 10) {
                System.out.println("Ingrese el telefono");
                tel = (leer.next());
                if (tel.length() < 10) {
                    System.out.println("Es demasiado corto");
                }
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }
            if (apellido == null || apellido.trim().isEmpty()) {
                throw new Exception("El apellido es obligatorio");
            }

            c.setDocumento(dni);
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setTelefono(tel);
            cd.guardarCliente(c);
        } catch (IllegalArgumentException e) {
            System.out.println("El dato ingresado es incorrecto");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void imprimirClientes() throws Exception {
        try {
            List<Cliente> cl = cd.buscarCliente();
            if (cl.isEmpty()) {
                System.out.println("No hay clientes disponibles");
            }

            for (Cliente c : cl) {
                System.out.println(c.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Cliente buscarClienteDni(Long dni) throws Exception {
        Cliente c = cd.clientePorDNI(dni);
        return c;
    }

    public Cliente buscarClienteID(int id) throws Exception {
        Cliente c = cd.clientePorId(id);
        return c;
    }

    public void modificarCliente() throws Exception {
        try {
            System.out.println("Ingrese DNI");
            long dni = leer.nextLong();
            Cliente c = buscarClienteDni(dni);
            if (c == null) {
                throw new Exception("El DNI no está en la base de datos");
            }
            while (c.getTelefono().length() < 10) {
                System.out.println("Ingrese el nuevo telefono");
                c.setTelefono(leer.next());
                if (c.getTelefono().length() < 10) {
                    System.out.println("Es demasiado corto");
                }
            }
            cd.modificarCliente(c);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
