package com.example.usuario.rekindlefrontend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by ORION on 11/04/2018.
 */

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({
                        EspressoCambiarPassword.class,
                        EspressoCrearServiceLodge.class,
                        EspressoCrearServiceEducation.class,
                        EspressoCrearServiceDonation.class,
                        EspressoCrearServiceJob.class,
                        EspressoEditarPerfilRefugiado.class,
                        EspressoCambiarPassword.class,
                        EspressoEditarServiceLodge.class,
                        EspressoEditarServiceEducation.class,
                        EspressoEditarServiceDonation.class,
                        EspressoEditarServiceJob.class,
                        EspressoLogin.class,
                        EspressoRegistroUsuario.class,
                        EspressoPantallaAjustes.class,
                        EspressoRecuperarPassword.class,
                        EspressoRegistroRefugiado.class,
                        EspressoRegistroUsuario.class,
                        EspressoRegistroVoluntario.class,
                        EspressoEditarPerfilRefugiado.class,
                        })
public class SuiteEspresso {}
