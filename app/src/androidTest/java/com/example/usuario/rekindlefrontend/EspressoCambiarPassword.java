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
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
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


import com.example.usuario.rekindlefrontend.view.CambiarPassword;


@RunWith(AndroidJUnit4.class)
public class EspressoCambiarPassword {

    @Rule
    public ActivityTestRule<CambiarPassword> pantalla = new ActivityTestRule<CambiarPassword>
            (CambiarPassword.class);

    @BeforeClass
    public static void setup(){
        init();
    }

    @AfterClass
    public static void end(){
        release();
    }

    @Test
    public void testCamposContraseña() {

        onView(withId(R.id.actual_password)).perform(replaceText
                ("testPasswordActual"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testPasswordActual")));

        onView(withId(R.id.new_password)).perform(replaceText
                ("testPasswordNuevo"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testPasswordNuevo")));

        onView(withId(R.id.repeat_password)).perform(replaceText
                ("testPasswordNuevo"), ViewActions.closeSoftKeyboard
                ()).check(matches(withText("testPasswordNuevo")));


    }

    @Test
    public void testBotonGuardar() {

        //TODO: Testear cuando funcione cambiar password

    }

    @Test
    public void testAtras(){

        //TODO: Testear cuando tengamos boton atras

    }

}
