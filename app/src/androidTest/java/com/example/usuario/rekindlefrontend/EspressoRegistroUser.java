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

import static org.hamcrest.Matchers.not;
import com.example.usuario.rekindlefrontend.R;

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

//import static org.hamcrest.core.AllOf.allOf;
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsInstanceOf.instanceOf;
//import static org.hamcrest.core.AnyOf.*;


@RunWith(AndroidJUnit4.class)
public class EspressoRegistroUser {

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
    public void testCamposVoluntario() {

        onView(withId(R.id.boton_voluntario)).perform(click());

        onView(withId(R.id.nombre_voluntario)).perform(typeText("testNombreVoluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testNombreVoluntario")));

        onView(withId(R.id.email_voluntario)).perform(typeText("test@test.com"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("test@test.com")));

        onView(withId(R.id.password_voluntario)).perform(typeText("1234"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("1234")));

        onView(withId(R.id.rpassword_voluntario)).perform(typeText("testEmailVoluntario"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testEmailVoluntario")));

        onView(withId(R.id.p_apellido_voluntario)).perform(typeText("testApellido"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testApellido")));

        onView(withId(R.id.s_apellido_voluntario)).perform(typeText("testApellidos"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("testApellidos")));
    }



    @Test
    public void testCamposRefugiado() {

        onView(withId(R.id.boton_refugiado)).perform(click());

        onView(withId(R.id.nombre_refugiado)).perform(typeText
                ("testNombrerefugiado"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testNombrerefugiado")));

        onView(withId(R.id.email_refugiado)).perform(typeText
                ("test@test.com"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("test@test.com")));

        onView(withId(R.id.password_refugiado)).perform(typeText
                ("1234"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("1234")));

        onView(withId(R.id.rpassword_refugiado)).perform(typeText
                ("testEmailrefugiado"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testEmailrefugiado")));

        onView(withId(R.id.p_apellido_refugiado)).perform(typeText

                ("testApellidoUnorefugiado"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testApellidoUnorefugiado")));

        onView(withId(R.id.s_apellido_refugiado)).perform(typeText
                ("testApellidodosrefugiado"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testApellidodosrefugiado")));

        onView(withId(R.id.telefono_refugiado)).perform(typeText("123"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("123")));

        onView(withId(R.id.sexo_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.sexo_refugiado)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.sexo_refugiado)).check(matches(withSpinnerText("Masculino")));

        onView(withId(R.id.procedencia_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.procedencia_refugiado)).perform(typeText("Turquia"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("Turquia")));

        onView(withId(R.id.pueblo_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.pueblo_refugiado)).perform(typeText("Ankara"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("Ankara")));

        onView(withId(R.id.etnia_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.etnia_refugiado)).perform(typeText("Kurdo"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("Kurdo")));

        onView(withId(R.id.grupo_sanguineo_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.grupo_sanguineo_refugiado)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.grupo_sanguineo_refugiado)).check(matches(withSpinnerText("AB+")));

        onView(withId(R.id.ojos_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.ojos_refugiado)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.ojos_refugiado)).check(matches(withSpinnerText("Castaño")));
    }

}
