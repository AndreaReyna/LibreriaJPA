package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

public class EditorialServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    EditorialDAO ed = new EditorialDAO();

    public Editorial crearEditorial(String nombre) throws Exception {
        try {
            if (nombre == null) {
                System.out.println("Ingrese el nombre de la editorial");
                nombre = leer.next();
            }
            List<Editorial> editoriales = buscarEdNombre(nombre);
            Editorial editorial = new Editorial();
            if ((editoriales.isEmpty())) {
                if (nombre == null || nombre.trim().isEmpty()) {
                    throw new Exception("El nombre es obligatorio");
                }
                editorial.setNombre(nombre);
                editorial.setAlta(true);

                ed.guardarEditorial(editorial);

                System.out.println("Editorial creada correctamente!");
            } else {
                if (editoriales.size() == 1) {
                    System.out.println("La editorial ya existe " + editoriales.get(0).getId() + " " + editoriales.get(0).getNombre());
                } else {
                    System.out.println("Lista de editoriales con ese nombre:");
                    for (Editorial e : editoriales) {
                        System.out.println("Editorial: " + e.getId() + " " + e.getNombre());
                    }
                }
            }

            return editorial;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void imprimirEditoriales() throws Exception {
        try {
            List<Editorial> editoriales = ed.buscarEditorial();
            if (editoriales.isEmpty()) {
                System.out.println("No hay editoriales disponibles!");
            } else {
                for (Editorial e : editoriales) {
                    if (e.getAlta()) {
                        System.out.println("Editorial: " + e.getId() + " " + e.getNombre());
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Editorial> buscarEdNombre(String nombre) throws Exception {
        try {
            if (nombre == null) {
                System.out.println("Ingrese el nombre: ");
                nombre = leer.next();
            }
            List<Editorial> editoriales = ed.editorialPorNombre(nombre);
            for (Editorial e : editoriales) {
                if (e.getAlta()) {
                    System.out.println(e.getId() + " " + e.getNombre());
                }
            }
            if (editoriales.isEmpty()) {
                System.out.println("No se encontraron editoriales con ese nombre");
            }
            return editoriales;
        } catch (Exception e) {
            throw e;
        }
    }

    public Editorial buscarEdPorId(Integer id) throws Exception {
        try {
            Editorial edi = ed.editorialPorId(id);
            return edi;
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarEd() throws Exception {
        try {

            imprimirEditoriales();
            System.out.println("Ingrese el id de la editorial que desea modificar");
            Editorial edi = ed.editorialPorId(leer.nextInt());
            System.out.println("Ingrese el nuevo nombre");
            String nombre = leer.next();

            edi.setNombre(nombre);
            ed.modificarEditorial(edi);
        } catch (Exception e) {
            throw e;
        }
    }

    public void bajaEd() throws Exception {
        try {
            int rta = 0;
            while (rta != 99) {
                System.out.println("Elija la opcion que desea realizar: \n1: Dar de baja. \n2: Dar de alta");
                rta = leer.nextInt();
                switch (rta) {
                    case 1:
                        imprimirEditoriales();
                        System.out.println("Seleccione el ID de la editorial que desea dar de baja");
                        Editorial edi = buscarEdPorId(leer.nextInt());
                        edi.setAlta(false);
                        ed.modificarEditorial(edi);
                        rta = 99;
                        break;
                    case 2:
                        imprimirEditoriales();
                        System.out.println("Seleccione el ID de la editorial que desea dar de alta");
                        Editorial edi2 = buscarEdPorId(leer.nextInt());
                        edi2.setAlta(false);
                        ed.modificarEditorial(edi2);
                        rta = 99;
                        break;
                    default:
                        System.out.println("Respuesta incorrecta.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la respuesta, reintente. ");
            leer.next();
            bajaEd();
        }
    }
}
