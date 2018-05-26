package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.usuario.rekindlefrontend.view.menu.login.Login;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoEditarServiceEducation {

    @Rule

    public ActivityTestRule<Login> pantalla = new ActivityTestRule<Login>
            (Login.class) {
    };


    @BeforeClass
    public static void setup() {
        init();
    }

    @AfterClass
    public static void end() {
        release();
    }

    @Test
    public void testCamposCurso() {

        // set : login

        onView(withId(R.id.input_email)).perform(typeText("dummy@voluntario.com"));

        onView(withId(R.id.input_password)).perform(typeText("1234"));

        // login

        onView(withId(R.id.btn_login)).perform(click());

        // edit services

        onView(withId(R.id.listar_servicios_MenuPrincipalVoluntario)).perform(click());

        // edit : educative

        onView(withId(R.id.boton_tipo_curso_educativo)).perform(click());

        // select : first element

        onView(withId(R.id.rv)).perform(actionOnItemAtPosition(0, click()));

        // check campos

        onView(withId(R.id.nombre_curso_educativo)).perform(replaceText
                ("testNombreCurso"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testNombreCurso")));

        onView(withId(R.id.telefono_curso_educativo)).perform(replaceText
                ("123"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("123")));

        onView(withId(R.id.direccion_curso_educativo)).perform(replaceText
                ("testdireccion_Curso"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testdireccion_Curso")));

        onView(withId(R.id.ambito_curso_educativo)).perform(replaceText
                ("testAmbito_Curso"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testAmbito_Curso")));

        onView(withId(R.id.requisitos_curso_educativo)).perform(replaceText
                ("testRequisitos_Curso"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testRequisitos_Curso")));

        onView(withId(R.id.horario_curso_educativo)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.horario_curso_educativo)).perform(replaceText
                ("tardes"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("tardes")));

        onView(withId(R.id.plazas_curso_educativo)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.plazas_curso_educativo)).perform(replaceText
                ("10"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("10")));

        onView(withId(R.id.precio_curso_educativo)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.precio_curso_educativo)).perform(replaceText
                ("0.00"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("0.00")));

        onView(withId(R.id.descripcion_curso_educativo)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.descripcion_curso_educativo)).perform(replaceText
                ("testDescription"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testDescription")));

    }
}
