package libreria.menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.AutorServicio;
import libreria.servicios.ClienteServicio;
import libreria.servicios.EditorialServicio;
import libreria.servicios.LibroServicio;
import libreria.servicios.PrestamoServicio;

public class Menu {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    AutorServicio as = new AutorServicio();
    EditorialServicio es = new EditorialServicio();
    LibroServicio ls = new LibroServicio();
    ClienteServicio cs = new ClienteServicio();
    PrestamoServicio ps = new PrestamoServicio();

    public void menuPrincipal() throws Exception {
        int r = 0;

        while (r != 99) {
            try {
                System.out.println("  ");
                System.out.println("---------OPCIONES---------"
                        + "\n1:Libros"
                        + "\n2:Editoriales"
                        + "\n3:Autores"
                        + "\n4:Clientes"
                        + "\n5:Prestamos"
                        + "\n99:Salir");
                System.out.println("--------------------------");
                r = leer.nextInt();
                switch (r) {
                    case 1:
                        opcionesLibro();
                        break;
                    case 2:
                        opcionesEditoriales();
                        break;
                    case 3:
                        opcionesAutor();
                        break;
                    case 4:
                        opcionesClientes();
                        break;
                    case 5:
                        opcionesPrestamos();
                        break;
                    default:
                        if (r != 99) {
                            System.out.println("Opcion incorrecta");
                        }
                }

            } catch (InputMismatchException e) {
                System.out.println("Eso no es un numero!");
                leer.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    public void opcionesLibro() throws Exception {
        int r = 0;

        while (r != 99) {
            try {
                System.out.println("  ");
                System.out.println("---------OPCIONES---------"
                        + "\n1:Ingresar un Libro"
                        + "\n2:Imprimir libros"
                        + "\n3:Buscar libros por ISBN"
                        + "\n4:Buscar libros por autor"
                        + "\n5:Buscar libros por editorial"
                        + "\n6:Buscar libros por titulo"
                        + "\n7:Dar de alta/baja un libro"
                        + "\n99:Salir");
                System.out.println("--------------------------");
                r = leer.nextInt();
                switch (r) {
                    case 1:
                        crearLibro();
                        break;
                    case 2:
                        ls.imprimirLibros();
                        break;
                    case 3:
                        ls.buscarLibroISBN(null);
                        break;
                    case 4:
                        ls.buscarLibroAutor(null);
                        break;
                    case 5:
                        ls.buscarLibroEd(null);
                        break;
                    case 6:
                        ls.buscarLibroNombre(null);
                        break;
                    case 7:
                        ls.bajaLibro();
                        break;
                    default:
                        if (r != 99) {
                            System.out.println("Opcion incorrecta");
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un numero!");
                leer.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void opcionesAutor() throws Exception {
        int r = 0;

        while (r != 99) {
            try {
                System.out.println("  ");
                System.out.println("---------OPCIONES---------"
                        + "\n1:Ingresar un autor"
                        + "\n2:Imprir autores"
                        + "\n3:Buscar autores por nombre"
                        + "\n4:Modificar autor"
                        + "\n5:Dar de alta/baja un autor"
                        + "\n99:Salir");
                System.out.println("--------------------------");
                r = leer.nextInt();
                switch (r) {
                    case 1:
                        as.crearAutor(null);
                        break;
                    case 2:
                        as.imprimirAutores();
                        break;
                    case 3:
                        as.buscarAutorNombre(null);
                        break;
                    case 4:
                        as.modificarAutor();
                        break;
                    case 5:
                        as.bajaAutor();
                        break;
                    default:
                        if (r != 99) {
                            System.out.println("Opcion incorrecta");
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un numero!");
                leer.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void opcionesEditoriales() throws Exception {
        int r = 0;

        while (r != 99) {
            try {
                System.out.println("  ");
                System.out.println("---------OPCIONES---------"
                        + "\n1:Ingresar una Editorial"
                        + "\n2:Imprimir editoriales"
                        + "\n3:Buscar editorial por nombre"
                        + "\n4:Modificar editorial"
                        + "\n5:Dar de baja/alta una editorial"
                        + "\n99:Salir");
                System.out.println("--------------------------");
                r = leer.nextInt();
                switch (r) {
                    case 1:
                        es.crearEditorial(null);
                        break;
                    case 2:
                        es.imprimirEditoriales();
                        break;
                    case 3:
                        es.buscarEdNombre(null);
                        break;
                    case 4:
                        es.modificarEd();
                        break;
                    case 5:
                        es.bajaEd();
                    default:
                        if (r != 99) {
                            System.out.println("Opcion incorrecta");
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un numero!");
                leer.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void opcionesClientes() throws Exception {
        int r = 0;

        while (r != 99) {
            try {
                System.out.println("  ");
                System.out.println("---------OPCIONES---------"
                        + "\n1:Crear cliente"
                        + "\n2:Imprimr clientes"
                        + "\n3:Modificar cliente"
                        + "\n99:Salir");
                System.out.println("--------------------------");
                r = leer.nextInt();
                switch (r) {
                    case 1:
                        cs.crearCliente();
                        break;
                    case 2:
                        cs.imprimirClientes();
                        break;
                    case 3:
                        cs.modificarCliente();
                        break;
                    default:
                        if (r != 99) {
                            System.out.println("Opcion incorrecta");
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un numero!");
                leer.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void opcionesPrestamos() throws Exception {
        int r = 0;

        while (r != 99) {
            try {
                System.out.println("  ");
                System.out.println("---------OPCIONES---------"
                        + "\n1:Crear un prestamo"
                        + "\n2:Ver informacion de los prestamos"
                        + "\n3:Devolver un libro"
                        + "\n4:Buscar prestamos por nombre de cliente"
                        + "\n99:Salir");
                System.out.println("--------------------------");
                r = leer.nextInt();
                switch (r) {
                    case 1:
                        ps.crearPrestamo();
                        break;
                    case 2:
                        ps.imprimirPrestamos();
                        break;
                    case 3:
                        ps.modificarPrestamo();
                        break;
                    case 4:
                        ps.buscarPorCliente();
                        break;
                    default:
                        if (r != 99) {
                            System.out.println("Opcion incorrecta");
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un numero!");
                leer.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void crearLibro() throws Exception {
        try {
            Autor autor = new Autor();
            autor = null;
            Editorial ed = new Editorial();
            ed = null;

            System.out.println("Ingrese el ISBN del libro");
            Integer isbn = leer.nextInt();

            Libro libro = ls.buscarLibroISBN(isbn);
            if (!(libro == null)) {
                throw new Exception("El libro ya existe");
            } else {
                System.out.println("Ingrese el titulo del libro");
                String titulo = leer.next();
                System.out.println("Ingrese el anio del libro");
                Integer anio = leer.nextInt();
                System.out.println("Cantidad de ejemplares");
                Integer ejemplares = leer.nextInt();
                if (ejemplares <= 0) {
                    throw new Exception("Debe existir al menos un ejemplar para ingresar el libro.");
                }
                System.out.println("Cantidad de ejemplares prestados");
                Integer ejemplaresPrestados = leer.nextInt();
                if (ejemplaresPrestados > ejemplares) {
                    System.out.println("Error! El numero de ejemplares prestados no puede ser menor a los ejemplares. \nReingrese los ejemplares prestados..");
                    while (ejemplaresPrestados > ejemplares) {
                        ejemplaresPrestados = leer.nextInt();
                    }
                }
                Integer ejemplaresRestantes = ejemplares - ejemplaresPrestados;

                while (autor == null) {
                    System.out.println("Ingrese el nombre del autor");
                    String nombreAutor = leer.next();
                    List<Autor> autores = as.buscarAutorNombre(nombreAutor);
                    if ((autores.isEmpty())) {
                        as.crearAutor(nombreAutor);
                    } else {
                        if (autores.size() == 1) {
                            autor = autores.get(0);
                        } else {
                            System.out.println("Lista de autores con ese nombre:");
                            for (Autor a : autores) {
                                System.out.println("ID: " + a.getId() + " Nombre: " + a.getNombre());
                            }
                            System.out.println("Elija el autor correspondiente por ID");
                            autor = as.buscarAutorPorId(leer.nextInt());
                        }
                    }
                }

                while (ed == null) {
                    System.out.println("Ingrese el nombre de la editorial");
                    String nombreEd = leer.next();
                    List<Editorial> editoriales = es.buscarEdNombre(nombreEd);
                    if ((editoriales.isEmpty())) {
                        es.crearEditorial(nombreEd);
                    } else {
                        if (editoriales.size() == 1) {
                            ed = editoriales.get(0);
                        } else {
                            System.out.println("Lista de editoriales con ese nombre:");
                            for (Editorial e : editoriales) {
                                System.out.println(e.getNombre() + " " + e.getId());
                                System.out.println("---");
                            }
                            System.out.println("Elija la editorial correspondiente por ID");
                            ed = es.buscarEdPorId(leer.nextInt());
                        }
                    }
                }

                ls.crearLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, ed);
            }

        } catch (InputMismatchException e) {
            System.out.println("Debe ser un numero!");
            leer.next();
        } catch (Exception e) {
            throw e;
        }
    }
}
