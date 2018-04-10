package com.example.usuario.rekindlefrontend;

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
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class EspressoMenuPrincipal {

    @Rule
    public ActivityTestRule<MenuPrincipal> pantalla = new ActivityTestRule<MenuPrincipal>
            (MenuPrincipal.class);

    @BeforeClass
    public static void setup(){
        init();
    }

    @AfterClass
    public static void end(){
        release();
    }

    @Test
    public void testListarServicios() {

        onView(withId(R.id.listar_servicios_MenuPrincipal)).perform(click());
        intended(hasComponent(ListarServicios.class.getName()));

    }

    @Test
    public void testBotonCrearServicios(){

        onView(withId(R.id.crear_servicio_MenuPrincipal)).perform(click());
        intended(hasComponent(CrearServicio.class.getName()));
    }

    @Test
    public void testBotonVerPerfil(){

        onView(withId(R.id.ver_perfil_MenuPrincipal)).perform(click());
        intended(hasComponent(VerPerfil.class.getName()));
    }

    @Test
    public void testAtras(){

        //TODO: Testear cuando tengamos boton atras

    }

}
