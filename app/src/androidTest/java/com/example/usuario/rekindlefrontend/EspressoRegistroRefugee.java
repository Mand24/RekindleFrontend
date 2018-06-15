package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

import android.app.Fragment;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.view.users.register.RegisterRefugee;
import com.example.usuario.rekindlefrontend.view.users.register.RegisterUser;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(AndroidJUnit4.class)
public class EspressoRegistroRefugee {

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
    public void comprobarCampos() {

        onView(withId(R.id.boton_refugiado)).perform(click());

        onView(withId(R.id.nombre_refugiado)).perform(typeText
                ("nombre"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.email_refugiado)).perform(typeText
                ("email@email.com"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.password_refugiado)).perform(typeText
                ("pass"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.rpassword_refugiado)).perform(typeText
                ("pass2"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.p_apellido_refugiado)).perform(typeText
                ("ap1"), ViewActions.closeSoftKeyboard
                ());

        onView(withId(R.id.s_apellido_refugiado)).perform(typeText("ap2"),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.telefono_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.telefono_refugiado)).perform(typeText("1111"),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.sexo_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.sexo_refugiado)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.sexo_refugiado));

        onView(withId(R.id.procedencia_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.procedencia_refugiado)).perform(typeText("Catalunya"),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.pueblo_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.pueblo_refugiado)).perform(typeText("Cabrils"),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.etnia_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.etnia_refugiado)).perform(typeText("Catala"),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.grupo_sanguineo_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.grupo_sanguineo_refugiado)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(2).perform(click());
        onView(withId(R.id.grupo_sanguineo_refugiado));

        onView(withId(R.id.ojos_refugiado)).perform(scrollTo()).check(ViewAssertions
                .matches(isDisplayed()));
        onView(withId(R.id.ojos_refugiado)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.ojos_refugiado));

        HashMap<String, Fragment> registerType = pantalla.getActivity().getFragment();

        RegisterRefugee regRef = (RegisterRefugee) registerType.get("Refugee");

        regRef.getParams();

        Refugee refugee = regRef.getRefugee();

        assertEquals(refugee.getName(), "nombre");
        assertEquals(refugee.getSurname1(), "ap1");
        assertEquals(refugee.getSurname2(), "ap2");
        assertEquals(refugee.getMail(), "email@email.com");
        assertEquals(refugee.getPassword(), "pass");
        assertEquals(refugee.getPhoneNumber(), "1111");
        assertEquals(refugee.getSex(), "Masculino");
        assertEquals(refugee.getBloodType(), "AB-");
        assertEquals(refugee.getTown(), "Cabrils");
        assertEquals(refugee.getCountry(), "Catalunya");
        assertEquals(refugee.getEyeColor(), "Castaño");
        assertEquals(refugee.getEthnic(), "Catala");
    }
}
