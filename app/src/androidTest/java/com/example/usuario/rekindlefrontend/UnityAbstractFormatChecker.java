package com.example.usuario.rekindlefrontend;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;

import org.junit.Test;

public class UnityAbstractFormatChecker extends AbstractFormatChecker{

    @Test
    public void check_letras () {
        assertFalse (letras ("as34"));
        assertFalse (letras ("cfdh%&"));
        assertTrue  (letras ("letras"));
    }

    @Test
    public void check_numeros () {
        assertFalse (numeros ("as34"));
        assertFalse (numeros ("123%&"));
        assertTrue  (numeros ("1234"));
    }

    @Test
    public void check_preuFormato () {
        assertTrue (preuFormat ("12,34"));
        assertFalse (preuFormat ("12,345"));
        assertTrue  (preuFormat ("12.34"));
        assertTrue  (preuFormat ("1234"));
    }

    @Test (expected = java.lang.Exception.class)
    public void testNombre_obli () throws Exception {
        checkNombre ("");
    }

    @Test (expected = java.lang.Exception.class)
    public void testNombre_largo () throws Exception {
        checkNombre ("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
    }

    @Test (expected = java.lang.Exception.class)
    public void testNombre_letras () throws Exception {
        checkNombre ("dscbh46");
    }

    @Test (expected = java.lang.Exception.class)
    public void testEmail_obli () throws Exception {
        checkEmail ("");
    }

    @Test (expected = java.lang.Exception.class)
    public void testEmail_largo () throws Exception {
        checkEmail
                ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void testEmail_format () throws Exception {
        checkEmail
                ("aaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void pass_obli () throws Exception {
        checkPassword ("","");
    }

    @Test (expected = java.lang.Exception.class)
    public void pass_larga () throws Exception {
        checkPassword ("aaaaaaaaaaaaaaaaaaaa","");
    }

    @Test (expected = java.lang.Exception.class)
    public void pass_corta () throws Exception {
        checkPassword ("123","");
    }

    @Test (expected = java.lang.Exception.class)
    public void pass_coinciden () throws Exception {
        checkPassword ("1234","1111");
    }

    @Test (expected = java.lang.Exception.class)
    public void apellido1_obli () throws Exception {
        checkPrimer_apellido ("");
    }

    @Test (expected = java.lang.Exception.class)
    public void apellido1_largo () throws Exception {
        checkPrimer_apellido("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void apellido1_letras () throws Exception {
        checkPrimer_apellido ("12134");
    }

    @Test (expected = java.lang.Exception.class)
    public void apellido2_largo () throws Exception {
        checkSegundo_apellido ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void apellido2_letras () throws Exception {
        checkSegundo_apellido ("aaa23224a");
    }

    @Test (expected = java.lang.Exception.class)
    public void telefono_numeros () throws Exception {
        checkTelefono ("dcfsd");
    }

    @Test (expected = java.lang.Exception.class)
    public void telefono_largo () throws Exception {
        checkTelefono ("123456789123456789123456789123456789123456789");
    }

    @Test (expected = java.lang.Exception.class)
    public void procedencia_letras () throws Exception {
        checkProcedencia ("1234dsv");
    }

    @Test (expected = java.lang.Exception.class)
    public void procedencia_largo () throws Exception {
        checkProcedencia ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void pueblo_largo () throws Exception {
        checkPueblo
                ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssssssssssssssssssssssssssssssss");
    }

    @Test (expected = java.lang.Exception.class)
    public void pueblo_letras () throws Exception {
        checkPueblo ("dcsfvf34v");
    }

    @Test (expected = java.lang.Exception.class)
    public void etnia_largo () throws Exception{
        checkEtnia ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void etnia_letras () throws Exception{
        checkEtnia ("a12aaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void nombreServioio_obli () throws Exception {
        checkNombreServicio ("");
    }

    @Test (expected = java.lang.Exception.class)
    public void nombreServioio_letras () throws Exception {
        checkNombreServicio ("cdsv234");
    }

    @Test (expected = java.lang.Exception.class)
    public void nombreServioio_largo () throws Exception {
        checkNombreServicio ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void telefonoServicio_obli () throws Exception {
        checkTelefonoServicio ("");
    }

    @Test (expected = java.lang.Exception.class)
    public void telefonoServicio_nuemeros () throws Exception {
        checkTelefonoServicio ("asf234r");
    }

    @Test (expected = java.lang.Exception.class)
    public void telefonoServicio_largo () throws Exception {
        checkTelefonoServicio ("123456789123456789123456789123456789123456789");
    }

    @Test (expected = java.lang.Exception.class)
    public void solicitudes_numeros () throws Exception {
        checkSolicitudesServicio ("ascdcd");
    }

    @Test (expected = java.lang.Exception.class)
    public void descripcion_obli () throws Exception {
        checkDescripcionServicio ("");
    }

    @Test (expected = java.lang.Exception.class)
    public void descripcion_larga () throws Exception {
        checkDescripcionServicio ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void puesto_obli () throws Exception {
        checkPuestoOfertaEmpleo ("");
    }

    @Test (expected = java.lang.Exception.class)
    public void puesto_largo () throws Exception {
        checkPuestoOfertaEmpleo ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void requisitos_obli () throws Exception {
        checkRequisitosServicio ("");
    }

    @Test (expected = java.lang.Exception.class)
    public void requisitos_largo () throws Exception {
        checkRequisitosServicio ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void sueldo_obli () throws Exception  {
        checkSueldoOfertaEmpleo ("");
    }

    @Test (expected = java.lang.Exception.class)
    public void sueldo_formato () throws Exception  {
        checkSueldoOfertaEmpleo ("23.2324");
    }

    @Test (expected = java.lang.Exception.class)
    public void plazas_numeros () throws Exception {
        checkPlazasServicio ("dffvs");
    }

    @Test(expected = java.lang.Exception.class)
    public void plazas_largo () throws Exception {
        checkPlazasServicio ("12345678");
    }

    @Test (expected = java.lang.Exception.class)
    public void ambito_obli () throws Exception {
        checkAmbitoCursoEducativo ("");
    }

    @Test (expected = java.lang.Exception.class)
    public void ambito_largo () throws Exception {
        checkAmbitoCursoEducativo ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test (expected = java.lang.Exception.class)
    public void precio_formato () throws Exception {
        checkPrice ("12.2345");
    }
}
