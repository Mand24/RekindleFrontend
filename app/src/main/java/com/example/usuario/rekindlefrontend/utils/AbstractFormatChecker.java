package com.example.usuario.rekindlefrontend.utils;

import android.app.Fragment;

import com.example.usuario.rekindlefrontend.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractFormatCheckerFragment extends Fragment {
    public  boolean letras(String texto) {
        Pattern patron = Pattern.compile("^[a-zA-Z]+$");
        Matcher valid  = patron.matcher(texto);
        return valid.matches();
    }

    public  boolean numeros(String texto) {
        Pattern patron = Pattern.compile("^[0-9]+$");
        Matcher valid = patron.matcher(texto);
        return valid.matches();
    }

    public  boolean preuFormat(String texto)
    {
        Pattern patron = Pattern.compile ("^\\d+(.\\d{1,2})?$");
        Matcher valid  = patron.matcher (texto);
        return valid.matches ();
    }

    public void checkNombre(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception(getString(R.string
                .nombre_obligatorio));
        else if (texto.length() > 20) throw new Exception(getString(R.string
                .nombre_largo));
        else if (!letras(texto)) throw new Exception(getString(R.string.nombre_letras));
    }

    public  void checkEmail(String texto) throws Exception {
        if (texto.length () == 0) throw new Exception(getString(R.string.email_obligatorio));
        else if (texto.length () > 30) throw new Exception(getString(R.string.email_largo));
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher (texto).matches()) throw new
                Exception(getString(R.string.email_formato));
    }

    public  void checkPassword(String texto, String texto_aux) throws Exception{
        if (texto.length() == 0) throw new Exception(getString(R.string.contraseña_obligatoria));
        else if (texto.length() > 15) throw new Exception(getString(R.string.contraseña_larga));
        else if (texto.length() < 4) throw new Exception(getString(R.string.contraseña_corta));
        else if (!texto.equals(texto_aux)) throw new Exception(getString(R.string
                .contraseña_distinta));
    }

    public  void checkPrimer_apellido(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception(getString(R.string.apellido1_obligatorio));
        else if (texto.length() > 20) throw new Exception(getString(R.string.apellido1_largo));
        else if (!letras(texto)) throw new Exception(getString(R.string.apellido1_letras));
    }

    public  void checkSegundo_apellido(String texto) throws Exception {
        if (texto.length() > 20) throw new Exception(getString(R.string.apellido2_largo));
        else if (!letras(texto) && texto.length() > 0) throw new Exception(getString(R.string
                .apellido2_letras));
    }

    public  void checkTelefono(String texto) throws Exception {
        if (!numeros(texto) && texto.length() > 0) throw new Exception(getString(R.string
                .telefonoUsuario_numeros));
        else if (texto.length() > 40) throw new Exception(getString(R.string.telefonoUsuario_largo));
    }

    public  void checkProcedencia(String texto) throws Exception {
        if (!letras(texto) && texto.length() > 0) throw new Exception
                (getString(R.string.procedencia_letras));
        else if (texto.length() > 20) throw new Exception
                (getString(R.string.procedencia_largo));
    }

    public  void checkPueblo(String texto) throws Exception {
        if (texto.length() > 40) throw new Exception(getString(R.string.pueblo_largo));
        else if (!letras(texto) && texto.length() > 0) throw new Exception(getString(R.string
                .pueblo_letras));
    }

    public  void checkEtnia(String texto) throws Exception {
        if (texto.length() > 20) throw new Exception(getString(R.string.etnia_largo));
        else if (!letras(texto) && texto.length() > 0) throw new Exception(getString(R.string
                .etnia_letras));
    }

    public  void checkNombreServicio(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception(getString(R.string
                .nombreServicio_obligatorio));
        else if (texto.length() > 50) throw new Exception(getString(R.string.nombreServicio_largo));
        else if (!letras(texto)) throw new Exception(getString(R.string.nombreServicio_letras));
    }

    public  void checkTelefonoServicio(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception(getString(R.string
                .telefonoServicio_obligatorio));
        else if (!numeros (texto) && texto.length () > 0) throw new Exception(getString(R.string
                .telefonoUsuario_numeros));
        else if (texto.length() > 40) throw new Exception(getString(R.string
                .telefonoUsuario_largo));
    }
    public  void checkSolicitudesServicio(String texto) throws Exception {
        if (!numeros (texto) && texto.length () > 0) throw new Exception(getString(R.string
                .solicitudes_numeros));
    }

    public  void checkDescripcionServicio(String texto) throws Exception {
        if (texto.length () == 0) throw new Exception(getString(R.string.descripcion_obligatoria));
        else if (texto.length () > 300) throw new Exception
                (getString(R.string.descricpion_larga));
        else if (!letras (texto)) throw new Exception(getString(R.string.descripcion_letras));
    }

    public  void checkPuestoOfertaEmpleo(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception(getString(R.string
                .puestoTrabajo_obligatorio));
        else if (texto.length() > 50) throw new Exception(getString(R.string.puestoTrabajo_largo));
    }

    public  void checkRequisitosServicio(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception(getString(R.string.requisitos_obligatorios));
        else if (texto.length() > 100) throw new Exception(getString(R.string.requisitos_largo));
    }

    public  void checkJornadaOfertaEmpleo(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception("Indicar horas de la joranada");
        else if (!numeros(texto) && texto.length() > 0) throw new Exception("Formato incorrecto; "
                + "formato correcto = 10 digitos máximo con una precisión máxima de dos "
                + "deciamales, uso del punto como separador");
    }

    public  void checkHorasOfertaEmpleo(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception("Indicar horas semanales");
        else if (!numeros(texto) && texto.length() > 0) throw new Exception("Formato incorrecto; "
                + "formato correcto = 10 digitos máximo con una precisión máxima de dos "
                + "deciamales, uso del punto como separador");
    }

    public  void checkDuracionOfertaEmpleo(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception("Indicar duración del contrato");
        else if (!numeros(texto) && texto.length() > 0) throw new Exception("Duración solo puede "
                + "contener dígitos");
        else if (texto.length() > 3) throw new Exception("Duración demasiado larga, máximo 3 "
                + "números");
    }

    public  void checkSueldoOfertaEmpleo(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception(getString(R.string.sueldo_obligatorio));
        else if (!preuFormat(texto) && texto.length() > 0) throw new Exception(getString(R.string
                .sueldo_formto));
    }

    public  void checkPlazasServicio(String texto) throws Exception {
        if (!numeros(texto) && texto.length() > 0) throw new Exception(getString(R.string
                .plazas_numeros));
        else if (texto.length() > 5) throw new Exception(getString(R.string.plazas_largo));
    }

    public  void checkAmbitoCursoEducativo(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception(getString(R.string.ambito_obligatorio));
        else if (texto.length() > 50) throw new Exception(getString(R.string.ambito_largo));
    }

    public  void checkHorarioCursoEducativo(String texto) throws Exception {
        if (texto.length() == 0) throw new Exception(getString(R.string.horario_obligatorio));
        else if (texto.length () > 30) throw new Exception(getString(R.string.horario_largo));
    }

    public  void checkPrice (String texto) throws Exception {
        if (texto.length () > 0 && !preuFormat(texto)) throw new Exception (getString(R.string
                .precio_formato));
    }

}
