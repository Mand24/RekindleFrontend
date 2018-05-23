package com.example.usuario.rekindlefrontend;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.instanceOf;

import android.support.test.rule.ActivityTestRule;

import com.example.usuario.rekindlefrontend.view.menu.menuLateral.Ajustes;
import com.example.user.rekindlefrontend.R;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Locale;

public class EspressoPantallaAjustes {

    @Rule
    public ActivityTestRule<Ajustes> pantalla = new ActivityTestRule<Ajustes>
            (Ajustes.class);

    @BeforeClass
    public static void setup(){
        init();
    }

    @AfterClass
    public static void end(){
        release();
    }

    @Test
    public void testSpiner ()
    {

        String codLenguaje = Locale.getDefault().getLanguage();
        int index = 0;

        switch (codLenguaje) {
            case "en":
                codLenguaje = "English";
                index = 0;
                break;
            case "ca":
                codLenguaje = "Catalan";
                index = 1;
                break;
            case "es":
                codLenguaje = "Spanish";
                index = 2;
        }

        onView(withId(R.id.idiomas_ajustes)).perform(click());
        onData((is(instanceOf(String.class)))).atPosition(index).perform(click());
        onView(withId(R.id.idiomas_ajustes)).check(matches(withSpinnerText(codLenguaje)));
    }
}
