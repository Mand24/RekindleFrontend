package com.example.usuario.rekindlefrontend;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil.EditarPerfil;
import com.example.usuario.rekindlefrontend.view.usuarios.verPerfil.VerPerfilRefugiado;


@RunWith(AndroidJUnit4.class)
public class EspressoVerPerfilRefugiado {

    @Rule
    public ActivityTestRule<VerPerfilRefugiado> pantalla = new ActivityTestRule<VerPerfilRefugiado>
            (VerPerfilRefugiado.class);

    @BeforeClass
    public static void setup(){
        init();
    }

    @AfterClass
    public static void end(){
        release();
    }

    @Test
    public void testBotonEditar() {


        onView(withId(R.id.editar_ver_perfil)).perform(click());
        intended(hasComponent(EditarPerfil.class.getName()));

    }

    @Test
    public void testAtras(){

        //TODO: Testear cuando tengamos boton atras

    }

}
