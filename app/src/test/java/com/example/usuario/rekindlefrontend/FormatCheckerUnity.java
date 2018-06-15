package com.example.usuario.rekindlefrontend;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.res.Resources;

import com.example.usuario.rekindlefrontend.utils.FormatChecker;

import org.junit.Before;
import org.junit.Test;

public class FormatCheckerUnity {

    Resources r;
    FormatChecker fc = new FormatChecker(r);

    @Before
    public void before() {
        r = mock(Resources.class);
        when(r.getString(any(int.class))).thenReturn("");
    }

    @Test
    public void check_letras() {
        assertFalse(fc.letters("as34"));
        assertFalse(fc.letters("cfdh%&"));
        assertTrue(fc.letters("letters"));
    }

    @Test
    public void check_numeros() {
        assertFalse(fc.numbers("as34"));
        assertFalse(fc.numbers("123%&"));
        assertTrue(fc.numbers("1234"));
    }

    @Test
    public void check_preuFormato() {
        assertTrue(fc.priceFormat("12,34"));
        assertFalse(fc.priceFormat("12,345"));
        assertTrue(fc.priceFormat("12.34"));
        assertTrue(fc.priceFormat("1234"));
    }

    @Test(expected = java.lang.Exception.class)
    public void testNombre_obli() throws Exception {
        fc.checkName("");
    }

    @Test(expected = java.lang.Exception.class)
    public void testNombre_largo() throws Exception {
        fc.checkName("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
    }

    @Test(expected = java.lang.Exception.class)
    public void testNombre_letras() throws Exception {
        fc.checkName("dscbh46");
    }

    @Test(expected = java.lang.Exception.class)
    public void testEmail_obli() throws Exception {
        fc.checkEmail("");
    }

    @Test(expected = java.lang.Exception.class)
    public void testEmail_largo() throws Exception {
        fc.checkEmail
                ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void testEmail_format() throws Exception {
        fc.checkEmail
                ("aaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void pass_obli() throws Exception {
        fc.checkPassword("", "");
    }

    @Test(expected = java.lang.Exception.class)
    public void pass_larga() throws Exception {
        fc.checkPassword("aaaaaaaaaaaaaaaaaaaa", "");
    }

    @Test(expected = java.lang.Exception.class)
    public void pass_corta() throws Exception {
        fc.checkPassword("123", "");
    }

    @Test(expected = java.lang.Exception.class)
    public void pass_coinciden() throws Exception {
        fc.checkPassword("1234", "1111");
    }

    @Test(expected = java.lang.Exception.class)
    public void apellido1_obli() throws Exception {
        fc.checkSurname1("");
    }

    @Test(expected = java.lang.Exception.class)
    public void apellido1_largo() throws Exception {
        fc.checkSurname1(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void apellido1_letras() throws Exception {
        fc.checkSurname1("12134");
    }

    @Test(expected = java.lang.Exception.class)
    public void apellido2_largo() throws Exception {
        fc.checkSurname2("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void apellido2_letras() throws Exception {
        fc.checkSurname2("aaa23224a");
    }

    @Test(expected = java.lang.Exception.class)
    public void telefono_numeros() throws Exception {
        fc.checkPhoneNumber("dcfsd");
    }

    @Test(expected = java.lang.Exception.class)
    public void telefono_largo() throws Exception {
        fc.checkPhoneNumber("123456789123456789123456789123456789123456789");
    }

    @Test(expected = java.lang.Exception.class)
    public void procedencia_letras() throws Exception {
        fc.checkCountry("1234dsv");
    }

    @Test(expected = java.lang.Exception.class)
    public void procedencia_largo() throws Exception {
        fc.checkCountry("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void pueblo_largo() throws Exception {
        fc.checkTown
                ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssssssssssssssssssssssssssssssss");
    }

    @Test(expected = java.lang.Exception.class)
    public void pueblo_letras() throws Exception {
        fc.checkTown("dcsfvf34v");
    }

    @Test(expected = java.lang.Exception.class)
    public void etnia_largo() throws Exception {
        fc.checkEthnic("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void etnia_letras() throws Exception {
        fc.checkEthnic("a12aaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void nombreServioio_obli() throws Exception {
        fc.checkServiceName("");
    }

    @Test(expected = java.lang.Exception.class)
    public void nombreServioio_largo() throws Exception {
        fc.checkServiceName(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void telefonoServicio_obli() throws Exception {
        fc.checkServicePhoneNumber("");
    }

    @Test(expected = java.lang.Exception.class)
    public void telefonoServicio_nuemeros() throws Exception {
        fc.checkServicePhoneNumber("asf234r");
    }

    @Test(expected = java.lang.Exception.class)
    public void telefonoServicio_largo() throws Exception {
        fc.checkServicePhoneNumber("123456789123456789123456789123456789123456789");
    }

    @Test(expected = java.lang.Exception.class)
    public void solicitudes_numeros() throws Exception {
        fc.checkServicePlaces("ascdcd");
    }

    @Test(expected = java.lang.Exception.class)
    public void descripcion_obli() throws Exception {
        fc.checkServiceDescription("");
    }

    @Test(expected = java.lang.Exception.class)
    public void descripcion_larga() throws Exception {
        fc.checkServiceDescription(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void puesto_obli() throws Exception {
        fc.checkServiceCharge("");
    }

    @Test(expected = java.lang.Exception.class)
    public void puesto_largo() throws Exception {
        fc.checkServiceCharge(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void requisitos_obli() throws Exception {
        fc.checkServiceRequirements("");
    }

    @Test(expected = java.lang.Exception.class)
    public void requisitos_largo() throws Exception {
        fc.checkServiceRequirements(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                        +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void sueldo_obli() throws Exception {
        fc.checkServiceSalary("");
    }

    @Test(expected = java.lang.Exception.class)
    public void sueldo_formato() throws Exception {
        fc.checkServiceSalary("23.2324");
    }

    @Test(expected = java.lang.Exception.class)
    public void plazas_numeros() throws Exception {
        fc.checkServicePlaces("dffvs");
    }

    @Test(expected = java.lang.Exception.class)
    public void plazas_largo() throws Exception {
        fc.checkServicePlaces("12345678");
    }

    @Test(expected = java.lang.Exception.class)
    public void ambito_obli() throws Exception {
        fc.checkServiceAmbit("");
    }

    @Test(expected = java.lang.Exception.class)
    public void ambito_largo() throws Exception {
        fc.checkServiceAmbit("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = java.lang.Exception.class)
    public void precio_formato() throws Exception {
        fc.checkServicePrice("12.2345");
    }

    @Test(expected = java.lang.Exception.class)
    public void URL_format() throws Exception {
        fc.checkURL("htt");
    }
}
