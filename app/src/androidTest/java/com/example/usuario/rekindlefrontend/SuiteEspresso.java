package com.example.usuario.rekindlefrontend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by ORION on 11/04/2018.
 */

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({   EspressoMenuPrincipal.class,
                        EspressoPantallaInicio.class,
                        EspressoRegistroUsuario.class,
                        EspressoVerPerfil.class,
                        EspressoCambiarPassword.class,
                        EspressoEditarPerfil.class,
                        EspressoCrearServicioAlojamiento.class,
                        EspressoCrearServicioCursoEducativo.class,
                        EspressoCrearServicioDonacion.class,
                        EspressoCrearServicioOfertaEmpleo.class})
public class SuiteEspresso {}
