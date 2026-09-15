package com.raul.prueba.util;

public class SpaceValidator {

    private SpaceValidator() {
    }

    public static boolean tieneEspacios(String palabra) {
       if (palabra.contains(" ")) {
            return true;
        }
        return false;
    }

}
