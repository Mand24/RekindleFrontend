package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.usuario.rekindlefrontend.view.usuarios.RegistroUsuario;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoRegistroVoluntario {

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
    public void testCampos ()
    {
        onView(withId(R.id.boton_voluntario)).perform(click());

        onView(withId(R.id.nombre_voluntario)).perform(replaceText
                ("nombre"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("nombre")));

        onView(withId(R.id.email_voluntario)).perform(replaceText
                ("email@email.com"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("email@email.com")));

        onView(withId(R.id.password_voluntario)).perform(replaceText
                ("pass"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("pass")));

        onView(withId(R.id.rpassword_voluntario)).perform(replaceText
                ("pass"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("pass")));

        onView(withId(R.id.p_apellido_voluntario)).perform(replaceText
                ("primer_apellido"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("primer_apellido")));

        onView(withId(R.id.s_apellido_voluntario)).perform(replaceText
                ("segundo_apellido"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("segundo_apellido")));


    }
}
