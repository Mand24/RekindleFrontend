package com.example.usuario.rekindlefrontend;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class AbstractFormatCheckerUnity extends
        com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker {

    @Test
    public void check_letras() {
        assertFalse(letters("as34"));
        assertFalse(letters("cfdh%&"));
        assertTrue(letters("letters"));
    }

    @Test
    public void check_numeros() {
        assertFalse(numbers("as34"));
        assertFalse(numbers("123%&"));
        assertTrue(numbers("1234"));
    }

    @Test
    public void check_preuFormato() {
        assertTrue(priceFormat("12,34"));
        assertFalse(priceFormat("12,345"));
        assertTrue(priceFormat("12.34"));
        assertTrue(priceFormat("1234"));
    }

    @Test(expected = java.lang.Exception.class)
    public void testNombre_obli() throws Exception {
        checkName("");
    }

    @Test(expected = java.lang.Exception.class)
    public void testNombre_largo() throws Exception {
        checkName("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
    }

    @Test(expected = java.lang.Exception.class)
    public void testNombre_letras() throws Exception {
        checkName("dscbh46");
    }

    @Test(expected = java.lang.Exception.class)
    public void testEmail_obli() throws Exception {
        checkEmail("");
    }

    @Test(expected = java.lang.Exception.class)
    public void testEmail_largo() throws Exception {
        checkEmail
                ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void testEmail_format() throws Exception {
        checkEmail
                ("aaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void pass_obli() throws Exception {
        checkPassword("", "");
    }

    @Test(expected = java.lang.Exception.class)
    public void pass_larga() throws Exception {
        checkPassword("aaaaaaaaaaaaaaaaaaaa", "");
    }

    @Test(expected = java.lang.Exception.class)
    public void pass_corta() throws Exception {
        checkPassword("123", "");
    }

    @Test(expected = java.lang.Exception.class)
    public void pass_coinciden() throws Exception {
        checkPassword("1234", "1111");
    }

    @Test(expected = java.lang.Exception.class)
    public void apellido1_obli() throws Exception {
        checkSurname1("");
    }

    @Test(expected = java.lang.Exception.class)
    public void apellido1_largo() throws Exception {
        checkSurname1(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void apellido1_letras() throws Exception {
        checkSurname1("12134");
    }

    @Test(expected = java.lang.Exception.class)
    public void apellido2_largo() throws Exception {
        checkSurname2("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void apellido2_letras() throws Exception {
        checkSurname2("aaa23224a");
    }

    @Test(expected = java.lang.Exception.class)
    public void telefono_numeros() throws Exception {
        checkPhoneNumber("dcfsd");
    }

    @Test(expected = java.lang.Exception.class)
    public void telefono_largo() throws Exception {
        checkPhoneNumber("123456789123456789123456789123456789123456789");
    }

    @Test(expected = java.lang.Exception.class)
    public void procedencia_letras() throws Exception {
        checkCountry("1234dsv");
    }

    @Test(expected = java.lang.Exception.class)
    public void procedencia_largo() throws Exception {
        checkCountry("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void pueblo_largo() throws Exception {
        checkTown
                ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssssssssssssssssssssssssssssssss");
    }

    @Test(expected = java.lang.Exception.class)
    public void pueblo_letras() throws Exception {
        checkTown("dcsfvf34v");
    }

    @Test(expected = java.lang.Exception.class)
    public void etnia_largo() throws Exception {
        checkEthnic("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void etnia_letras() throws Exception {
        checkEthnic("a12aaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void nombreServioio_obli() throws Exception {
        checkServiceName("");
    }

    @Test(expected = java.lang.Exception.class)
    public void nombreServioio_largo() throws Exception {
        checkServiceName(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void telefonoServicio_obli() throws Exception {
        checkServicePhoneNumber("");
    }

    @Test(expected = java.lang.Exception.class)
    public void telefonoServicio_nuemeros() throws Exception {
        checkServicePhoneNumber("asf234r");
    }

    @Test(expected = java.lang.Exception.class)
    public void telefonoServicio_largo() throws Exception {
        checkServicePhoneNumber("123456789123456789123456789123456789123456789");
    }

    @Test(expected = java.lang.Exception.class)
    public void solicitudes_numeros() throws Exception {
        checkServicePlaces("ascdcd");
    }

    @Test(expected = java.lang.Exception.class)
    public void descripcion_obli() throws Exception {
        checkServiceDescription("");
    }

    @Test(expected = java.lang.Exception.class)
    public void descripcion_larga() throws Exception {
        checkServiceDescription(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void puesto_obli() throws Exception {
        checkServiceCharge("");
    }

    @Test(expected = java.lang.Exception.class)
    public void puesto_largo() throws Exception {
        checkServiceCharge(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void requisitos_obli() throws Exception {
        checkServiceRequirements("");
    }

    @Test(expected = java.lang.Exception.class)
    public void requisitos_largo() throws Exception {
        checkServiceRequirements(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void sueldo_obli() throws Exception {
        checkServiceSalary("");
    }

    @Test(expected = java.lang.Exception.class)
    public void sueldo_formato() throws Exception {
        checkServiceSalary("23.2324");
    }

    @Test(expected = java.lang.Exception.class)
    public void plazas_numeros() throws Exception {
        checkServicePlaces("dffvs");
    }

    @Test(expected = java.lang.Exception.class)
    public void plazas_largo() throws Exception {
        checkServicePlaces("12345678");
    }

    @Test(expected = java.lang.Exception.class)
    public void ambito_obli() throws Exception {
        checkServiceAmbit("");
    }

    @Test(expected = java.lang.Exception.class)
    public void ambito_largo() throws Exception {
        checkServiceAmbit("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void precio_formato() throws Exception {
        checkServicePrice("12.2345");
    }
}
