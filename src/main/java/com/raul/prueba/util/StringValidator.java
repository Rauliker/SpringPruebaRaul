package com.raul.prueba.util;

public final class StringValidator {

    private StringValidator() {
    }

    public static boolean tieneMayuscula(String palabra) {
        if (Character.isUpperCase(palabra.charAt(0))) {
                return true;
            }
        return false;
    }
}
