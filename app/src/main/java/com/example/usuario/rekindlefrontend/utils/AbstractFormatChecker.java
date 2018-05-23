package com.example.usuario.rekindlefrontend.utils;

import android.app.Fragment;

import com.example.user.rekindlefrontend.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractFormatChecker extends Fragment {

    public boolean letters(String texto) {
        Pattern patron = Pattern.compile("^[a-zA-Z]+$");
        Matcher valid = patron.matcher(texto);
        return valid.matches();
    }

    public boolean numbers(String text) {
        Pattern patron = Pattern.compile("^[0-9]+$");
        Matcher valid = patron.matcher(text);
        return valid.matches();
    }

    public boolean priceFormat(String text) {
        Pattern patron = Pattern.compile("^\\d+(.\\d{1,2})?$");
        Matcher valid = patron.matcher(text);
        return valid.matches();
    }

    public void checkName(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string
                    .nombre_obligatorio));
        } else if (text.length() > 20) {
            throw new Exception(getString(R.string
                    .nombre_largo));
        } else if (!letters(text)) throw new Exception(getString(R.string.nombre_letras));
    }

    public void checkEmail(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string.email_obligatorio));
        } else if (text.length() > 30) {
            throw new Exception(getString(R.string.email_largo));
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            throw new
                    Exception(getString(R.string.email_formato));
        }
    }

    public void checkPassword(String text, String aux) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string.contraseña_obligatoria));
        } else if (text.length() > 15) {
            throw new Exception(getString(R.string.contraseña_larga));
        } else if (text.length() < 4) {
            throw new Exception(getString(R.string.contraseña_corta));
        } else if (!text.equals(aux)) {
            throw new Exception(getString(R.string
                    .contraseña_distinta));
        }
    }

    public void checkSurname1(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string.apellido1_obligatorio));
        } else if (text.length() > 20) {
            throw new Exception(getString(R.string.apellido1_largo));
        } else if (!letters(text)) throw new Exception(getString(R.string.apellido1_letras));
    }

    public void checkSurname2(String text) throws Exception {
        if (text.length() > 20) {
            throw new Exception(getString(R.string.apellido2_largo));
        } else if (!letters(text) && text.length() > 0) {
            throw new Exception(getString(R.string
                    .apellido2_letras));
        }
    }

    public void checkPhoneNumber(String text) throws Exception {
        if (!numbers(text) && text.length() > 0) {
            throw new Exception(getString(R.string
                    .telefonoUsuario_numeros));
        } else if (text.length() > 40) {
            throw new Exception(
                    getString(R.string.telefonoUsuario_largo));
        }
    }

    public void checkCountry(String text) throws Exception {
        if (!letters(text) && text.length() > 0) {
            throw new Exception
                    (getString(R.string.procedencia_letras));
        } else if (text.length() > 20) {
            throw new Exception
                    (getString(R.string.procedencia_largo));
        }
    }

    public void checkTown(String text) throws Exception {
        if (text.length() > 40) {
            throw new Exception(getString(R.string.pueblo_largo));
        } else if (!letters(text) && text.length() > 0) {
            throw new Exception(getString(R.string
                    .pueblo_letras));
        }
    }

    public void checkEthnic(String text) throws Exception {
        if (text.length() > 20) {
            throw new Exception(getString(R.string.etnia_largo));
        } else if (!letters(text) && text.length() > 0) {
            throw new Exception(getString(R.string
                    .etnia_letras));
        }
    }

    public void checkBiography(String text) throws Exception {
        if (text.length() > 300) throw new Exception(getString(R.string.biografia_largo));
    }

    public void checkServiceName(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string
                    .nombreServicio_obligatorio));
        } else if (text.length() > 50) {
            throw new Exception(getString(R.string.nombreServicio_largo));
        } else if (!letters(text)) throw new Exception(getString(R.string.nombreServicio_letras));
    }

    public void checkServicePhoneNumber(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string
                    .telefonoServicio_obligatorio));
        } else if (!numbers(text) && text.length() > 0) {
            throw new Exception(getString(R.string
                    .telefonoUsuario_numeros));
        } else if (text.length() > 40) {
            throw new Exception(getString(R.string
                    .telefonoUsuario_largo));
        }
    }

    public void checkServicePlaces(String text) throws Exception {
        if (!numbers(text) && text.length() > 0) {
            throw new Exception(getString(R.string
                    .solicitudes_numeros));
        } else if (text.length() > 5) throw new Exception(getString(R.string.plazas_largo));
    }

    public void checkServiceDescription(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string.descripcion_obligatoria));
        } else if (text.length() > 300) {
            throw new Exception
                    (getString(R.string.descricpion_larga));
        }
    }

    public void checkServiceCharge(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string
                    .puestoTrabajo_obligatorio));
        } else if (text.length() > 50) {
            throw new Exception(
                    getString(R.string.puestoTrabajo_largo));
        }
    }

    public void checkServiceRequirements(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string.requisitos_obligatorios));
        } else if (text.length() > 100) throw new Exception(getString(R.string.requisitos_largo));
    }

    public void checkServiceHoursDay(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception("Indicar horas de la joranada");
        } else if (!numbers(text) && text.length() > 0) {
            throw new Exception("Formato incorrecto; "
                    + "formato correcto = 10 digitos máximo con una precisión máxima de dos "
                    + "deciamales, uso del punto como separador");
        }
    }

    public void checkServiceHoursWeek(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception("Indicar horas semanales");
        } else if (!numbers(text) && text.length() > 0) {
            throw new Exception("Formato incorrecto; "
                    + "formato correcto = 10 digitos máximo con una precisión máxima de dos "
                    + "deciamales, uso del punto como separador");
        }
    }

    public void checkServiceContractDuration(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception("Indicar duración del contrato");
        } else if (!numbers(text) && text.length() > 0) {
            throw new Exception("Duración solo puede "
                    + "contener dígitos");
        } else if (text.length() > 3) {
            throw new Exception("Duración demasiado larga, máximo 3 "
                    + "números");
        }
    }

    public void checkServiceSalary(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string.sueldo_obligatorio));
        } else if (!priceFormat(text) && text.length() > 0) {
            throw new Exception(
                    getString(R.string.sueldo_formto));
        }
    }

    public void checkServiceAmbit(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string.ambito_obligatorio));
        } else if (text.length() > 50) throw new Exception(getString(R.string.ambito_largo));
    }

    public void checkServiceSchedule(String text) throws Exception {
        if (text.length() == 0) {
            throw new Exception(getString(R.string.horario_obligatorio));
        } else if (text.length() > 30) throw new Exception(getString(R.string.horario_largo));
    }

    public void checkServicePrice(String text) throws Exception {
        if (text.length() > 0 && !priceFormat(text)) {
            throw new Exception(getString(R.string
                    .precio_formato));
        }
    }

    public void checkServiceIncreasePlaces(String _news, String _old) throws Exception {
        Integer news = Integer.parseInt(_news);
        Integer old = Integer.parseInt(_old);
        if (old > news) throw new Exception(getString(R.string.plazas_aumento));
    }

}
