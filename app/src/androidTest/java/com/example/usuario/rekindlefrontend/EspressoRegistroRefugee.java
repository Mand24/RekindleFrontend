package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.instanceOf;
import com.example.user.rekindlefrontend.R;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.usuario.rekindlefrontend.view.usuarios.registro.RegistroUsuario;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoRegistroRefugee {

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
    public void comprobarCampos ()
    {
        onView(withId(R.id.boton_refugiado)).perform(click());

        onView(withId(R.id.nombre_refugiado)).perform(typeText
                ("nombre"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("nombre")));

        onView(withId(R.id.email_refugiado)).perform(typeText
                ("email@email.com"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("email@email.com")));

        onView(withId(R.id.password_refugiado)).perform(typeText
                ("pass"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("pass")));

        onView(withId(R.id.rpassword_refugiado)).perform(typeText
                ("pass2"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("pass2")));

        onView(withId(R.id.p_apellido_refugiado)).perform(typeText
                ("ap1"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("ap1")));

        onView(withId(R.id.s_apellido_refugiado)).perform(typeText("ap2"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("ap2")));

        onView(withId(R.id.telefono_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.telefono_refugiado)).perform(typeText("1111"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("1111")));

        /*onView(withId(R.id.nacimiento_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.nacimiento_refugiado)).perform(typeText("21-07-2018"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("21-07-2018")));*/

        onView(withId(R.id.sexo_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.sexo_refugiado)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.sexo_refugiado)).check(matches(withSpinnerText("Masculino")));

        onView(withId(R.id.procedencia_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.procedencia_refugiado)).perform(typeText("Catalunya"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("Catalunya")));

        onView(withId(R.id.pueblo_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.pueblo_refugiado)).perform(typeText("Cabrils"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("Cabrils")));

        onView(withId(R.id.etnia_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.etnia_refugiado)).perform(typeText("Catala"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("Catala")));

        onView(withId(R.id.grupo_sanguineo_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.grupo_sanguineo_refugiado)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(2).perform(click());
        onView(withId(R.id.grupo_sanguineo_refugiado)).check(matches(withSpinnerText("AB-")));

        onView(withId(R.id.ojos_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.ojos_refugiado)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.ojos_refugiado)).check(matches(withSpinnerText("Castaño")));
    }
}
