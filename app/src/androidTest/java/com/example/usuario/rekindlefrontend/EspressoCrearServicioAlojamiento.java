package com.example.usuario.rekindlefrontend;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
//import static org.hamcrest.core.AllOf.allOf;
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsInstanceOf.instanceOf;
//import static org.hamcrest.core.AnyOf.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;


import com.example.usuario.rekindlefrontend.view.servicios.crear.CrearServicio;


@RunWith(AndroidJUnit4.class)
public class EspressoCrearServicioAlojamiento {

    @Rule
    public ActivityTestRule<CrearServicio> pantalla = new ActivityTestRule<CrearServicio>
            (CrearServicio.class);

    @BeforeClass
    public static void setup(){
        init();
    }

    @AfterClass
    public static void end(){
        release();
    }

    @Test
    public void testCamposAlojamiento() {

        onView(withId(R.id.boton_tipo_alojamiento)).perform(click());

        onView(withId(R.id.nombre_alojamiento)).perform(replaceText
                ("testNombreAlojamiento"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testNombreAlojamiento")));

        onView(withId(R.id.correo_alojamiento)).perform(replaceText
                ("testCorreoAlojamiento"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testCorreoAlojamiento")));

        onView(withId(R.id.telefono_alojamiento)).perform(replaceText
                ("123"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("123")));

        onView(withId(R.id.direccion_alojamiento)).perform(replaceText
                ("testdireccion_alojamiento"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testdireccion_alojamiento")));

        onView(withId(R.id.solicitudes_alojamiento)).perform(replaceText
                ("10"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("10")));

        onView(withId(R.id.fecha_limite_alojamiento)).perform(replaceText
                ("2018-12-30"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("2018-12-30")));

        onView(withId(R.id.descripcion_alojamiento)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.descripcion_alojamiento)).perform(replaceText
                ("testDescription"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testDescription")));

    }

    @Test
    public void testBotonCrearAlojamiento() {

        //TODO: Testear cuando funcione crear alojamiento

        onView(withId(R.id.boton_tipo_alojamiento)).perform(click());

        onView(withId(R.id.nombre_alojamiento)).perform(replaceText
                ("testNombreAlojamiento"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testNombreAlojamiento")));

        onView(withId(R.id.correo_alojamiento)).perform(replaceText
                ("testCorreoAlojamiento"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testCorreoAlojamiento")));

        onView(withId(R.id.telefono_alojamiento)).perform(replaceText
                ("123"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("123")));

        onView(withId(R.id.direccion_alojamiento)).perform(replaceText
                ("testdireccion_alojamiento"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testdireccion_alojamiento")));

        onView(withId(R.id.solicitudes_alojamiento)).perform(replaceText
                ("10"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("10")));

        onView(withId(R.id.fecha_limite_alojamiento)).perform(replaceText
                ("2018-12-30"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("2018-12-30")));

        onView(withId(R.id.descripcion_alojamiento)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.descripcion_alojamiento)).perform(replaceText
                ("testDescription"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testDescription")));

        onView(withId(R.id.enviar_formulario_alojamiento)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.enviar_formulario_alojamiento)).perform(click());

    }

    @Test
    public void testAtras(){

        //TODO: Testear cuando tengamos boton atras

    }

}
