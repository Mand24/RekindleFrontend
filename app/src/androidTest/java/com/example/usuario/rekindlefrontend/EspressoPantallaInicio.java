package com.example.usuario.rekindlefrontend;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import com.example.usuario.rekindlefrontend.view.menu.MenuPrincipal;
import com.example.usuario.rekindlefrontend.view.menu.PantallaInicio;
import com.example.usuario.rekindlefrontend.view.usuarios.RegistroUsuario;


@RunWith(AndroidJUnit4.class)
public class EspressoPantallaInicio {

    @Rule
    public ActivityTestRule<PantallaInicio> pantalla = new ActivityTestRule<PantallaInicio>
            (PantallaInicio.class);

    @BeforeClass
    public static void setup(){
        init();
    }

    @AfterClass
    public static void end(){
        release();
    }

    @Test
    public void testCambiosTexto() {

        onView(withId(R.id.input_email)).perform(typeText("testEmail"), ViewActions
                .closeSoftKeyboard()).check(matches(withText("testEmail")));

        onView(withId(R.id.input_password)).perform(typeText("testPassword"), ViewActions
                .closeSoftKeyboard()).check(matches(withText("testPassword")));
    }

    @Test
    public void testBotonRecuperar() {

        //TODO: Testear recuperar password cuando funcione

    }

    @Test
    public void testBotonLogin(){

        //TODO: Testear Login cuando funcione
        onView(withId(R.id.input_email)).perform(typeText("testEmail@est.com"), ViewActions
                .closeSoftKeyboard());

        onView(withId(R.id.input_password)).perform(typeText("testPassword"), ViewActions
                .closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());
        intended(hasComponent(MenuPrincipal.class.getName()));
    }

    @Test
    public void testBotonLoginFail(){

        onView(withId(R.id.input_email)).perform(typeText("testEmailMalo"), ViewActions
                .closeSoftKeyboard());

        onView(withId(R.id.input_password)).perform(typeText("testPassword"), ViewActions
                .closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());
        intended(hasComponent(PantallaInicio.class.getName()));
    }

    @Test
    public void testLinkRegistro(){

        onView(withId(R.id.link_signup)).perform(click());
        intended(hasComponent(RegistroUsuario.class.getName()));

    }

    @Test
    public void testAtras(){

        //TODO: Testear cuando tengamos boton atras

    }

}
