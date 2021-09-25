package libreria.servicios;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.PrestamoDAO;

public class PrestamoServicio {

    PrestamoDAO pd = new PrestamoDAO();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    LibroServicio ls = new LibroServicio();
    ClienteServicio cs = new ClienteServicio();

    public void crearPrestamo() throws Exception {
        try {
            System.out.println("Ingrese la fecha del prestamo");

            System.out.println("Ingrese el dia");
            int dia = leer.nextInt();
            System.out.println("Ingrese el mes");
            int mes = leer.nextInt();
            System.out.println("Ingrese el año");
            int anio = leer.nextInt();
            Date fecha = new Date();
            fecha.setDate(dia);
            fecha.setMonth(mes - 1);
            fecha.setYear(anio - 1900);
            
 
            Libro l = new Libro();
            l.setEjemplaresRestantes(0);
            while (l.getEjemplaresRestantes()<1) {
            ls.imprimirLibros();
            System.out.println("Ingrese el libro que desea segun su ISBN");
            l = ls.buscarLibroISBN(leer.nextInt());
            if (l.getEjemplaresRestantes()<1) {
                System.out.println("No hay ejemplares para prestar, elija otro libro.");
            }  
           }
            
            cs.imprimirClientes();
            System.out.println("Ingrese su ID");
            Cliente c = cs.buscarClienteID(leer.nextInt());

            if (fecha == null) {
                throw new Exception("La fecha de prestamo es obligatoria");
            }
            if (l == null) {
                throw new Exception("El libro es obligatorio");
            }
            if (c == null) {
                throw new Exception("El cliente es obligatorio");
            }
            
            l.setEjemplaresPrestados(l.getEjemplaresPrestados()+1);
            l.setEjemplaresRestantes(l.getEjemplaresRestantes()-1);
            ls.modificarLibro(l);
            
            Prestamo p = new Prestamo();
            p.setFechaDevolucion(null);
            p.setFechaPrestamo(fecha);
            p.setCliente(c);
            p.setLibro(l);
            pd.guardarPrestamo(p);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void imprimirPrestamos() throws Exception {
        try {
            List<Prestamo> pr = pd.imprimirPrestamos();
            if (pr.isEmpty()) {
                System.out.println("No hay prestamos disponibles");
            }
            for (Prestamo p : pr) {
                System.out.println(p.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarPrestamo() throws Exception {

        try {

            imprimirPrestamos();
            System.out.println("Elija el ID del prestamo al que corresponde la devolucion");
            Prestamo p = pd.prestamosPorId(leer.nextInt());

            do {
                System.out.println("Ingrese la fecha de devolución");
                System.out.println("Ingrese el dia");
                int dia = leer.nextInt();
                System.out.println("Ingrese el mes");
                int mes = leer.nextInt();
                System.out.println("Ingrese el año");
                int anio = leer.nextInt();

                Date fecha = new Date();
                fecha.setDate(dia);
                fecha.setMonth(mes - 1);
                fecha.setYear(anio - 1900);

                p.setFechaDevolucion(fecha);
                if (p.getFechaDevolucion().before(p.getFechaPrestamo())) {
                    System.out.println("La fecha de devolucion NO puede ser anterior al prestamo, reintente.");
                }
            } while (p.getFechaDevolucion().before(p.getFechaPrestamo()));
            pd.modificarPrestamo(p);

        } catch (Exception e) {
            throw e;
        }
    }

    public void buscarPorCliente() throws Exception {
        try {
            cs.imprimirClientes();
            System.out.println("Elija el ID del cliente que desee buscar");
            pd.prestamosPorClientes(leer.nextInt());
        } catch (Exception e) {
            throw e;
        }
    }
}
