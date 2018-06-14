package com.example.usuario.rekindlefrontend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by ORION on 11/04/2018.
 */

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EspressoLogin.class,
        EspressoRegistroRefugee.class,
        EspressoRegistroVolunteer.class,
        EspressoRecoverPassword.class
})
public class SuiteEspresso {
}
