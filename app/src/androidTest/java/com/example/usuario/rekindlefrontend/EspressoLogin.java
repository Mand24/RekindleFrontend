package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MainMenu;


import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.view.usuarios.registro.RegistroUsuario;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class EspressoLogin {

    @Rule
    public ActivityTestRule<Login> pantalla = new ActivityTestRule<Login>
            (Login.class);

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
    public void testBotonLogin(){

        //TODO: Testear Login cuando funcione
        onView(withId(R.id.input_email)).perform(typeText("dummy@voluntario.com"), ViewActions
                .closeSoftKeyboard());

        onView(withId(R.id.input_password)).perform(typeText("1234"), ViewActions
                .closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());
        intended(hasComponent(MainMenu.class.getName()));
    }

    @Test
    public void testBotonLoginFail(){

        onView(withId(R.id.input_email)).perform(typeText("testEmailMalo"), ViewActions
                .closeSoftKeyboard());

        onView(withId(R.id.input_password)).perform(typeText("testPassword"), ViewActions
                .closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());
        intended(hasComponent(Login.class.getName()));
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
