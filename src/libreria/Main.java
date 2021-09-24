package libreria;

import libreria.menu.Menu;

public class Main {

    public static void main(String[] args) {
        try {
            Menu menu = new Menu();
            menu.menuPrincipal();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
