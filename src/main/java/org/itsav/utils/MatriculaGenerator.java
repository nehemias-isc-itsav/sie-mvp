package org.itsav.utils;

import java.time.LocalDate;
import java.util.Locale;

public class MatriculaGenerator {
    private static final String NIVEL_UNIVERSIDAD = "5";

    public static String generarPrefijo(){
        int year = LocalDate.now().getYear();
        return String.valueOf(year).substring(2) + NIVEL_UNIVERSIDAD;
    }

    public static int extraerSecuencial(String numControl){
        if (numControl == null){
            return 0;
        }

        return Integer.parseInt(numControl.substring(numControl.length() - 4));
    }

    public static String generarMatricula(String nombre, String apellidoPaterno, int ultimoSecuencial) {
        int numero = ultimoSecuencial + 1;

        return generarPrefijo() + nombre.substring(0, 1).toUpperCase() +
                apellidoPaterno.substring(0, 1).toUpperCase() + String.format("%04d", numero);

    }
}
