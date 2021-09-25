package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

public class LibroServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    LibroDAO ld = new LibroDAO();

    public void crearLibro(Integer isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Autor autor, Editorial editorial) throws Exception {
        try {
            if (isbn == null) {
                throw new Exception("El isbn es obligatorio");
            }
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }
            if (anio == null) {
                throw new Exception("El año del libro es obligatorio");
            }
            if (ejemplares == null) {
                throw new Exception("El numero de ejemplares es obligatorio");
            }
            if (ejemplaresPrestados == null) {
                throw new Exception("El numero de ejemplares prestados es obligatorio");
            }
            if (autor == null) {
                throw new Exception("El autor es obligatorio");
            }
            if (editorial == null) {
                throw new Exception("La editorial es obligatoria");
            }

            Libro libro = new Libro();

            libro.setIsbn(Long.valueOf(isbn));
            libro.setTitulo(titulo);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAnio(anio);
            libro.setAlta(true);
            libro.setAutor(autor);
            libro.setEditoral(editorial);
            ld.guardarLibro(libro);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void imprimirLibros() throws Exception {
        try {
            List<Libro> libros = ld.buscarLibro();
            if (libros.isEmpty()) {
                System.out.println("No hay libros disponibles!");
            } else {
                for (Libro l : libros) {
                    if (l.getAlta() && l.getAutor().getAlta() && l.getEditoral().getAlta()) {
                        System.out.println(l.toString());
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Libro buscarLibroISBN(Integer aux) throws Exception {
        try {
            if (aux == null) {
                System.out.println("Ingrese el ISBN que desea buscar");
                aux = leer.nextInt();
            }
            Libro libro = null;
           
            if (libro == null || libro.getAlta()==false) {
                System.out.println("No se encontró el libro");
            }else{
                 libro = ld.LibroPorISBN(Long.valueOf(aux));
            System.out.println(libro.toString());
            }
            return libro;
        } catch (Exception e) {
            throw e;
        }
    }

    public void buscarLibroNombre(String nombre) throws Exception {
        try {
            if (nombre == null) {
                System.out.println("Ingrese el titulo del libro que desea buscar: ");
                nombre = leer.next();
            }
            List<Libro> libros = ld.buscarLibroPorNombre(nombre);
            if (libros == null) {
                System.out.println("No se encontraron libros con ese titulo");
            } else {
                for (Libro l : libros) {
                    if (l.getAlta() && l.getAutor().getAlta() && l.getEditoral().getAlta()) {
                        System.out.println(l.toString());
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void buscarLibroAutor(String nombre) throws Exception {
        try {
            if (nombre == null) {
                System.out.println("Ingrese el nombre del autor que desea buscar");
                nombre = leer.next();
            }
            List<Libro> libros = ld.buscarLibroPorAutor(nombre);
            if (libros == null) {
                System.out.println("No se encontraron libros con ese titulo");
            } else {
                for (Libro l : libros) {
                    if (l.getAlta() && l.getAutor().getAlta() && l.getEditoral().getAlta()) {
                        System.out.println(l.toString());
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void buscarLibroEd(String nombre) throws Exception {
        try {
            if (nombre == null) {
                System.out.println("Ingrese el nombre de la editorial que desea buscar");
                nombre = leer.next();
            }
            List<Libro> libros = ld.buscarLibroPorEditorial(nombre);
            if (libros == null) {
                System.out.println("No se encontraron libros con ese titulo");
            } else {
                for (Libro l : libros) {
                    if (l.getAlta() && l.getAutor().getAlta() && l.getEditoral().getAlta()) {
                        System.out.println(l.toString());
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarLibro(Libro libro) throws Exception{
        ld.modificarLibro(libro);
    } 

    public void bajaLibro() throws Exception {
        try {
            int rta = 0;
            while (rta != 99) {
                System.out.println("Elija la opcion que desea realizar: \n1: Dar de baja. \n2: Dar de alta");
                rta = leer.nextInt();
                switch (rta) {
                    case 1:
                        imprimirLibros();
                        System.out.println("Seleccione el ISBN del libro que desea dar de baja");
                        Libro libro = buscarLibroISBN(leer.nextInt());
                        libro.setAlta(false);
                        ld.modificarLibro(libro);
                        rta = 99;
                        break;
                    case 2:
                        imprimirLibros();
                        System.out.println("Seleccione el ISBN del libro que desea dar de alta");
                        Libro libro2 = buscarLibroISBN(leer.nextInt());
                        libro2.setAlta(true);
                        ld.modificarLibro(libro2);
                        rta = 99;
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la respuesta, reintente. ");
            leer.next();
            bajaLibro();
        }
    }
}
