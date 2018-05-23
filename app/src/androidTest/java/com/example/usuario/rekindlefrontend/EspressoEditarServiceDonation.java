package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onView;
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

import com.example.usuario.rekindlefrontend.view.services.editar.EditarServicio;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoEditarServiceDonation {

    @Rule
    public ActivityTestRule<EditarServicio> pantalla = new ActivityTestRule<EditarServicio>
            (EditarServicio.class);

    @BeforeClass
    public static void setup(){
        init();
    }

    @AfterClass
    public static void end(){
        release();
    }

    @Test
    public void testCamposDonacion() {

        pantalla.getActivity().menu(1);

        onView(withId(R.id.nombre_donacion)).perform(replaceText
                ("testNombreDonacion"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testNombreDonacion")));

        onView(withId(R.id.telefono_donacion)).perform(replaceText
                ("123"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("123")));

        onView(withId(R.id.direccion_donacion)).perform(replaceText
                ("testdireccion_Donacion"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testdireccion_Donacion")));

        onView(withId(R.id.solicitudes_donacion)).perform(replaceText
                ("10"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("10")));

        onView(withId(R.id.franja_horaria_inicio_donacion)).perform(replaceText
                ("12:00"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("12:00")));

        onView(withId(R.id.franja_horaria_fin_donacion)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.franja_horaria_fin_donacion)).perform(replaceText
                ("15:00"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("15:00")));

        onView(withId(R.id.descripcion_donacion)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.descripcion_donacion)).perform(replaceText
                ("testDescription"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testDescription")));

    }
}