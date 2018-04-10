package com.example.usuario.rekindlefrontend;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Menu;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class EspressoRegistroUsuario {

    @Rule
    public ActivityTestRule<RegistroUsuario> pantalla = new ActivityTestRule<RegistroUsuario>
            (RegistroUsuario.class);

    @BeforeClass
    public static void setup(){
        init();
    }

    @AfterClass
    public static void end(){
        release();
    }

    @Test
    public void testCambiosTextoVoluntario() {

        onView(withId(R.id.boton_voluntario)).perform(click());

        onView(withId(R.id.nombre_voluntario)).perform(typeText("testNombreVoluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testNombreVoluntario")));

        onView(withId(R.id.email_voluntario)).perform(typeText("testEmailVoluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testEmailVoluntario")));

        onView(withId(R.id.password_voluntario)).perform(typeText("testPasswordVoluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testPasswordVoluntario")));

        onView(withId(R.id.rpassword_voluntario)).perform(typeText("testEmailVoluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testEmailVoluntario")));

        onView(withId(R.id.p_apellido_voluntario)).perform(typeText("testApellido1Voluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testApellido1Voluntario")));

        onView(withId(R.id.s_apellido_voluntario)).perform(typeText("testApellido2Voluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testApellido2Voluntario")));
    }

    @Test
    public void testRegistroVoluntarioCorrecto() {

        //TODO: Terminar cuando tengamos registro voluntarios

        onView(withId(R.id.boton_voluntario)).perform(click());

        onView(withId(R.id.nombre_voluntario)).perform(typeText("testNombreVoluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testNombreVoluntario")));

        onView(withId(R.id.email_voluntario)).perform(typeText("testEmailVoluntario@test.com"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testEmailVoluntario@test.com")));

        onView(withId(R.id.password_voluntario)).perform(typeText("testPasswordVoluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testPasswordVoluntario")));

        onView(withId(R.id.rpassword_voluntario)).perform(typeText("testPasswordVoluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testPasswordVoluntario")));

        onView(withId(R.id.p_apellido_voluntario)).perform(typeText("testApellido1Voluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testApellido1Voluntario")));

        onView(withId(R.id.s_apellido_voluntario)).perform(typeText("testApellido2Voluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testApellido2Voluntario")));

        onView(withId(R.id.enviar_registro_voluntario)).perform(click());

    }

    @Test
    public void testCambiosTextoRefugiado() {

        onView(withId(R.id.boton_refugiado)).perform(click());

        onView(withId(R.id.nombre_refugiado)).perform(typeText("testNombrerefugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testNombrerefugiado")));

        onView(withId(R.id.email_refugiado)).perform(typeText("testEmailrefugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testEmailrefugiado")));

        onView(withId(R.id.password_refugiado)).perform(typeText("testPasswordrefugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testPasswordrefugiado")));

        onView(withId(R.id.rpassword_refugiado)).perform(typeText("testEmailrefugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testEmailrefugiado")));

        onView(withId(R.id.p_apellido_refugiado)).perform(typeText("testApellido1refugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testApellido1refugiado")));

        onView(withId(R.id.s_apellido_refugiado)).perform(typeText("testApellido2refugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testApellido2refugiado")));

        onView(withId(R.id.telefono_refugiado)).perform(typeText("123"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("123")));

        onView(withId(R.id.nacimiento_refugiado)).perform(typeText("1995-12-31"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("1995-12-31")));

        onView(withId(R.id.texto_sexo_refugiado)).perform(click());
        onData(hasComponent("Masculino")).perform(click());
        onView(withId(R.id.texto_sexo_refugiado)).check(matches(withText("Masculino")));

    }

    @Test
    public void testRegistroRefugiadoCorrecto() {

        //TODO: Terminar cuando tengamos registro refugiados

        onView(withId(R.id.boton_refugiado)).perform(click());

        onView(withId(R.id.nombre_refugiado)).perform(typeText("testNombrerefugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testNombrerefugiado")));

        onView(withId(R.id.email_refugiado)).perform(typeText("testEmailrefugiado@test.com"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testEmailrefugiado@test.com")));

        onView(withId(R.id.password_refugiado)).perform(typeText("testPasswordrefugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testPasswordrefugiado")));

        onView(withId(R.id.rpassword_refugiado)).perform(typeText("testPasswordrefugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testPasswordrefugiado")));

        onView(withId(R.id.p_apellido_refugiado)).perform(typeText("testApellido1refugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testApellido1refugiado")));

        onView(withId(R.id.s_apellido_refugiado)).perform(typeText("testApellido2refugiado"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testApellido2refugiado")));

        onView(withId(R.id.enviar_registro_refugiado)).perform(click());

    }

    @Test
    public void testAtras(){

        //TODO: Testear cuando tengamos boton atras

    }

}
