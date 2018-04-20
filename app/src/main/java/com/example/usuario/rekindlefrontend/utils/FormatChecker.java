package com.example.usuario.rekindlefrontend.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatChecker {
    public static boolean letras(String texto) {
        Pattern patron = Pattern.compile("^[a-zA-Z]+$");
        Matcher valid = patron.matcher(texto);
        return valid.matches();
    }

    public static boolean numeros(String texto) {
        Pattern patron = Pattern.compile("^[0-9]+$");
        Matcher valid = patron.matcher(texto);
        return valid.matches();
    }

    public static boolean fecha_valida(String fecha) {
        Pattern patron = Pattern.compile("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
        Matcher valid = patron.matcher(fecha);
        return valid.matches();
    }

    public static void checkNombre(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception("Nombre obligatorio");
        else if (texto.length() > 20) throw new Exception("Nombre es demasiado largo, máximo 20 "
                + "letras");
        else if (!letras(texto)) throw new Exception("El nombre solo puede contener letras");
    }

    public static void checkEmail(String texto) throws Exception {
        if (texto.length () == 0) throw new Exception("Email obligatorio");
        else if (texto.length () > 30) throw new Exception("Email demasiado largo, máximo 30 "
                + "caracteres");
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher (texto).matches()) throw new
                Exception("Formato de email no valido");
    }

    public static void checkPassword(String texto, String texto_aux) throws Exception{
        if (texto.length() == 0) throw new Exception("Contraseña obligatoria");
        else if (texto.length() > 15) throw new Exception("Contraseña demasiada larga, máximo 15 "
                + "caracteres");
        else if (texto.length() < 4) throw new Exception("Contraseña demasiada corta, mínimo 4 "
                + "caracteres");
        else if (!texto.equals(texto_aux)) throw new Exception("Las contraseñas no coinciden");
    }

    public static void checkPrimer_apellido(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception("Primer apellido obligatorio");
        else if (texto.length() > 20) throw new Exception("Primer apellido demasiado largo, "
                + "máximo 20 letras");
        else if (!letras(texto)) throw new Exception("El primer apellido solo puede contener "
                + "letras");
    }

    public static void checkSegundo_apellido(String texto) throws Exception {
        if (texto.length() > 20) throw new Exception("Segundo apellido demasiado largo, máximo 20"
                + " letras");
        else if (!letras(texto)) throw new Exception("El segundo apellido solo puede contener "
                + "letras");
    }

    public static void checkTelefono(String texto) throws Exception {
        if (!numeros(texto) && texto.length() > 0) throw new Exception("Teléfono solo puede "
                + "contener dígitos");
        else if (texto.length() > 40) throw new Exception("Teléfono demasiado largo, máximo 50 "
                + "números");
    }

    public static void checkProcedencia(String texto) throws Exception {
        if (!letras(texto) && texto.length() > 0) throw new Exception("El nombre del país de "
                + "origen solo puede contener letras");
        else if (texto.length() > 20) throw new Exception("País de origen demasiado largo, máximo"
                + " 20 letras");
    }

    public static void checkPueblo(String texto) throws Exception {
        if (texto.length() > 40) throw new Exception("Nombre del pueblo demasiado largo, máximo "
                + "40 letras");
        else if (!letras(texto) && texto.length() > 0) throw new Exception("El nombre del pueblo "
                + "solo puede contener letras");
    }

    public static void checkEtnia(String texto) throws Exception {
        if (texto.length() > 20) throw new Exception("Nombre de la etnia demasiado largo, máximo "
                + "20 letras");
        else if (!letras(texto) && texto.length() > 0) throw new Exception("El nombre de la etnia"
                + " de origen solo puede contener letras");
    }

}
