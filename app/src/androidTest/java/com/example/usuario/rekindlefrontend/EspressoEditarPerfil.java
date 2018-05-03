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

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
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
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;


import com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil.CambiarPassword;
import com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil.EditarPerfil;
import com.example.usuario.rekindlefrontend.view.usuarios.verPerfil.VerPerfilRefugiado;


@RunWith(AndroidJUnit4.class)
public class EspressoEditarPerfil {

    @Rule
    public ActivityTestRule<EditarPerfil> pantalla = new ActivityTestRule<EditarPerfil>
            (EditarPerfil.class);

    @BeforeClass
    public static void setup(){
        init();
    }

    @AfterClass
    public static void end(){
        release();
    }

    @Test
    public void testCampos() {

        onView(withId(R.id.nombre_usuario_perfil)).perform(replaceText
                ("testNombrerefugiado"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testNombrerefugiado")));

        onView(withId(R.id.apellido1_usuario_perfil)).perform(replaceText
                ("testApellidorefugiado"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testApellidorefugiado")));

        onView(withId(R.id.apellido2_usuario_perfil)).perform(replaceText
                ("testApellido2refugiado"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testApellido2refugiado")));

        onView(withId(R.id.email_usuario_perfil)).perform(replaceText
                ("testEmailrefugiado"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testEmailrefugiado")));

        onView(withId(R.id.telefono_usuario_perfil)).perform(replaceText
                ("123"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("123")));

        onView(withId(R.id.naciminento_usuario_perfil)).perform(replaceText
                ("1995-12-30"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("1995-12-30")));

        onView(withId(R.id.sexo_usuario_perfil)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.sexo_usuario_perfil)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.sexo_usuario_perfil)).check(matches(withSpinnerText("Masculino")));

        onView(withId(R.id.pais_usuario_perfil)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.pais_usuario_perfil)).perform(replaceText("Turquia"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("Turquia")));

        onView(withId(R.id.pueblo_usuario_perfil)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.pueblo_usuario_perfil)).perform(replaceText("Ankara"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("Ankara")));

        onView(withId(R.id.etnia_usuario_perfil)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.etnia_usuario_perfil)).perform(replaceText("Kurdo"),
                ViewActions.closeSoftKeyboard()).check(matches(withText("Kurdo")));

        onView(withId(R.id.sangre_usuario_perfil)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.sangre_usuario_perfil)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.sangre_usuario_perfil)).check(matches(withSpinnerText("AB+")));

        onView(withId(R.id.ojos_usuario_perfil)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.ojos_usuario_perfil)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.ojos_usuario_perfil)).check(matches(withSpinnerText("Castaño")));
    }

    @Test
    public void testBotonCambiarContraseña() {

        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.cambiar_password)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));

        onView(withId(R.id.cambiar_password)).perform(click());

        intended(hasComponent(CambiarPassword.class.getName()));

    }

    @Test
    public void testBotonGuardar() {

        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());

//        onView(withId(R.id.guardar_editar_perfil)).perform(scrollTo()).check(ViewAssertions
//                .matches(isDisplayed()));

        onView(withId(R.id.guardar_editar_perfil)).perform(click());

        intended(hasComponent(VerPerfilRefugiado.class.getName()));

    }

    @Test
    public void testAtras(){

        //TODO: Testear cuando tengamos boton atras

//        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(isRoot()).perform(pressBack());
//        intended(hasComponent(VerPerfilRefugiado.class.getName()));

    }

}
