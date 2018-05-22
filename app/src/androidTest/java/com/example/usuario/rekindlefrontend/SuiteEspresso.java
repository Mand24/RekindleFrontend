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
                        EspressoCrearServicioAlojamiento.class,
                        EspressoCrearServicioCursoEducativo.class,
                        EspressoCrearServicioDonacion.class,
                        EspressoCrearServicioOfertaEmpleo.class,
                        EspressoEditarPerfilRefugiado.class,
                        EspressoCambiarPassword.class,
                        EspressoEditarServicioAlojamiento.class,
                        EspressoEditarServicioCursoEducativo.class,
                        EspressoEditarServicioDonacion.class,
                        EspressoEditarServicioOfertaEmpleo.class,
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
