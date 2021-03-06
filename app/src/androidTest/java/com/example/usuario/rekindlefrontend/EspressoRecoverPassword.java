package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.usuario.rekindlefrontend.view.menu.login.RecoverPassword;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoRecoverPassword {

    @Rule
    public ActivityTestRule<RecoverPassword> pantalla = new ActivityTestRule<RecoverPassword>
            (RecoverPassword.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, RecoverPassword.class);
            result.putExtra("email", "email@email.com");
            result.putExtra("code", "code");
            return result;
        }
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
    public void comprobarCampos() {
        onView(withId(R.id.codigo)).perform(replaceText
                ("actual"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.contrasena)).perform(replaceText
                ("newpass"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.confirmar_contrasena)).perform(replaceText
                ("newpass2"), ViewActions.closeSoftKeyboard
                ());

        pantalla.getActivity().setParams();

        assertEquals(pantalla.getActivity().getCode(), "actual");
        assertEquals(pantalla.getActivity().getPassword(), "newpass");
        assertEquals(pantalla.getActivity().getConfirmPassword(), "newpass2");
    }
}
