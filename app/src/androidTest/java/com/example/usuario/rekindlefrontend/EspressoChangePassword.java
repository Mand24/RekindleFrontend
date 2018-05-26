package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
import com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil.ChangePassword;

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
public class EspressoChangePassword {

    public Volunteer mVolunteer = new Volunteer("mVolunteer@hotmail.com", "pass123",
            "voluntarioName", "surnameOne", "surnameTwo", null);
    @Rule
    public ActivityTestRule<ChangePassword> pantalla = new ActivityTestRule<ChangePassword>
            (ChangePassword.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent(InstrumentationRegistry.getContext(),
                    ChangePassword.class);
            intent.putExtra("Volunteer", mVolunteer);
            intent.putExtra("tipo", mVolunteer.getUserType());
            return intent;
        }
    };

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
}
