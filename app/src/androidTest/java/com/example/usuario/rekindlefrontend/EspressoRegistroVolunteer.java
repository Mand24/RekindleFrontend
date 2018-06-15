package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;

import android.app.Fragment;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
import com.example.usuario.rekindlefrontend.view.users.register.RegisterUser;
import com.example.usuario.rekindlefrontend.view.users.register.RegisterVolunteer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(AndroidJUnit4.class)
public class EspressoRegistroVolunteer {

    @Rule
    public ActivityTestRule<RegisterUser> pantalla = new ActivityTestRule<RegisterUser>
            (RegisterUser.class);

    @BeforeClass
    public static void setup() {
        init();
    }

    @AfterClass
    public static void end() {
        release();
    }

    @Test
    public void testCampos() {
        onView(withId(R.id.boton_voluntario)).perform(click());


        onView(withId(R.id.nombre_voluntario)).perform(replaceText
                ("nombre"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.email_voluntario)).perform(replaceText
                ("email@email.com"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.password_voluntario)).perform(replaceText
                ("pass"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.rpassword_voluntario)).perform(replaceText
                ("pass"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.p_apellido_voluntario)).perform(replaceText
                ("primer_apellido"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.s_apellido_voluntario)).perform(replaceText
                ("segundo_apellido"), ViewActions.closeSoftKeyboard
                ());

        HashMap<String, Fragment> registerType = pantalla.getActivity().getFragment();

        RegisterVolunteer regVol = (RegisterVolunteer) registerType.get("Volunteer");

        regVol.getParams();

        Volunteer volunteer =  regVol.getVolunteer();

        assertEquals(volunteer.getName(), "nombre");
        assertEquals(volunteer.getSurname1(), "primer_apellido");
        assertEquals(volunteer.getSurname2(), "segundo_apellido");
        assertEquals(volunteer.getPassword(), "pass");
        assertEquals(volunteer.getMail(), "email@email.com");
    }
}
