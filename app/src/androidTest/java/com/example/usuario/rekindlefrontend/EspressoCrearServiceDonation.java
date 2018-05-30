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

import static com.example.usuario.rekindlefrontend.data.pusher.Comm.setUpPusher;
import static com.example.usuario.rekindlefrontend.utils.Consistency.saveUser;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;

import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.view.services.create.CreateService;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(AndroidJUnit4.class)
public class EspressoCrearServiceDonation {

    @Mock
    static private SharedPreferences sharedPrefs = Mockito.mock(SharedPreferences.class);
    @Mock
    static private SharedPreferences.Editor sharedPrefsEditor = Mockito.mock(SharedPreferences
            .Editor
            .class);
    @Mock
    static private Context context = Mockito.mock(Context.class);
    @Mock
    static private Consistency mConsistency = Mockito.mock(Consistency.class);

    //@Mock
    //AppBaseActivity base = Mockito.mock(AppBaseActivity.class);

    @Rule
    public ActivityTestRule<CreateService> pantalla = new ActivityTestRule<CreateService>
            (CreateService.class) {
    };

    @BeforeClass
    public static void setup() {

        Volunteer voluntario = new Volunteer ("voluntario@mail.com", "pass123",
                "voluntarioName", "surnameOne", "surnameTwo", "foto");

        sharedPrefs  = Mockito.mock(SharedPreferences.class);
        context      = Mockito.mock(Context.class);
        mConsistency = Mockito.mock(Consistency.class);

        Mockito.when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPrefs);
        Mockito.when(sharedPrefs.edit()).thenReturn(sharedPrefsEditor);
        Mockito.when(mConsistency.getUser(context)).thenReturn(voluntario);

        saveUser(voluntario, context);
        setUpPusher();
        init();
    }

    @AfterClass
    public static void end() {
        release();
    }

    @Test
    public void testCamposDonacion() {

        // go : fragment donation

        onView(withId(R.id.boton_tipo_donacion)).perform(click());

        // check : campos

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

    @Test
    public void testBotonCrearDonacion() {

        //TODO: Testear cuando funcione crear donacion
    }

    @Test
    public void testAtras() {

        //TODO: Testear cuando tengamos boton atras

    }

}
