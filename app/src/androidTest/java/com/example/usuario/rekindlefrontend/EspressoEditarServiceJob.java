package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.usuario.rekindlefrontend.view.services.edit.ServiceEdit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoEditarServiceJob {

    @Rule
    public ActivityTestRule<ServiceEdit> pantalla = new ActivityTestRule<ServiceEdit>
            (ServiceEdit.class);

    @BeforeClass
    public static void setup(){
        init();
    }

    @AfterClass
    public static void end(){
        release();
    }

    @Test
    public void testCamposOfertaEmpleo() {

        pantalla.getActivity ().menu  (3);

        onView(withId(R.id.boton_tipo_oferta_empleo)).perform(click());

        onView(withId(R.id.nombre_oferta_empleo)).perform(replaceText
                ("testNombreEmpleo"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testNombreEmpleo")));

        onView(withId(R.id.telefono_oferta_empleo)).perform(replaceText
                ("123"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("123")));

        onView(withId(R.id.direccion_oferta_empleo)).perform(replaceText
                ("testdireccion_Empleo"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testdireccion_Empleo")));

        onView(withId(R.id.puesto_oferta_empleo)).perform(replaceText
                ("testPuestoEmpleo"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testPuestoEmpleo")));

        onView(withId(R.id.requisitos_oferta_empleo)).perform(replaceText
                ("testRequisitos_Empleo"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testRequisitos_Empleo")));

        onView(withId(R.id.jornada_oferta_empleo)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.jornada_oferta_empleo)).perform(replaceText
                ("parcial - tardes"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("parcial - tardes")));

        onView(withId(R.id.horas_semanales_oferta_empleo)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.horas_semanales_oferta_empleo)).perform(replaceText
                ("20"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("20")));

        onView(withId(R.id.duracion_oferta_empleo)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.duracion_oferta_empleo)).perform(replaceText
                ("2 Meses"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("2 Meses")));

        onView(withId(R.id.plazas_oferta_empleo)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.plazas_oferta_empleo)).perform(replaceText
                ("10"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("10")));

        onView(withId(R.id.sueldo_oferta_empleo)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.sueldo_oferta_empleo)).perform(replaceText
                ("400"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("400")));

        onView(withId(R.id.descripcion_oferta_empleo)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.descripcion_oferta_empleo)).perform(replaceText
                ("testDescription"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testDescription")));

    }
}
