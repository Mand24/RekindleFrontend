package com.example.usuario.rekindlefrontend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by ORION on 11/04/2018.
 */

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AbstractFormatCheckerUnity.class,
        ChatsAdapterUnity.class,
        LinksAdapterUnity.class,
        MessageAdapterUnity.class,
        RefugeeAdapterUnity.class,
        ReportsAdapterUnity.class,
        ServiceAdapterUnity.class,
        CodeGeneratorUnity.class,
        GMailUnity.class
})
public class SuiteUnity {
}