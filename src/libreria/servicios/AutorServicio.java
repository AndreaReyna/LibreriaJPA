package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

public class AutorServicio {

    AutorDAO ad = new AutorDAO();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void crearAutor(String nombre) throws Exception {
        try {
            if (nombre == null) {
                System.out.println("Ingrese el nombre del autor");
                nombre = leer.next();
            }
            List<Autor> autores = buscarAutorNombre(nombre);
            if ((autores.isEmpty())) {
                Autor autor = new Autor();
                autor.setNombre(nombre);
                autor.setAlta(true);
                ad.guardarAutor(autor);
                System.out.println("Autor creado con exito!");
            } else {

                if (autores.size() == 1) {
                    System.out.println("Ya existe ese autor: \n" + autores.get(0).toString());
                } else {
                    System.out.println("Lista de autores con ese nombre:");
                    for (Autor a : autores) {
                        System.out.println(a.toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void imprimirAutores() throws Exception {
        try {
            List<Autor> autores = ad.buscarAutor();
            if (autores.isEmpty()) {
                System.out.println("No hay autores disponibles!");
            } else {
                for (Autor a : autores) {
                    if (a.getAlta()) {
                        System.out.println(a.toString());
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Autor> buscarAutorNombre(String nombre) throws Exception {
        try {
            if (nombre == null) {
                System.out.println("Ingrese el nombre: ");
                nombre = leer.next();
            }
            List<Autor> autores = ad.autorPornombre(nombre);
            for (Autor a : autores) {
                if (a.getAlta()) {
                    System.out.println(a.toString());
                }
            }
            if (autores.isEmpty()) {
                System.out.println("No hay ningun autor registrado con ese nombre");
            }
            return autores;
        } catch (Exception e) {
            throw e;
        }
    }

    public Autor buscarAutorPorId(Integer id) throws Exception {
        try {
            Autor autor = ad.autorPorId(id);
            return autor;
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarAutor() throws Exception {
        imprimirAutores();
        System.out.println("Seleccione el id del autor que desea modificar");
        Autor autor = buscarAutorPorId(leer.nextInt());
        System.out.println("Ingrese el nuevo nombre");
        String nombre = leer.next();

        autor.setNombre(nombre);
        ad.modificarAutor(autor);
    }

    public void bajaAutor() throws Exception {
        try {
            int rta = 0;
            while (rta != 99) {
                System.out.println("Elija la opcion que desea realizar: \n1: Dar de baja. \n2: Dar de alta");
                rta = leer.nextInt();
                switch (rta) {
                    case 1:
                        imprimirAutores();
                        System.out.println("Seleccione el ID del autor que desea dar de baja");
                        Autor autor = buscarAutorPorId(leer.nextInt());
                        autor.setAlta(false);
                        ad.modificarAutor(autor);
                        rta = 99;
                        break;
                    case 2:
                        imprimirAutores();
                        System.out.println("Seleccione el ID del autor que desea dar de alta");
                        Autor autor2 = buscarAutorPorId(leer.nextInt());
                        autor2.setAlta(true);
                        ad.modificarAutor(autor2);
                        rta = 99;
                        break;
                    default:
                        System.out.println("Respuesta incorrecta.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la respuesta, reintente. ");
            leer.next();
            bajaAutor();
        }
    }

}
