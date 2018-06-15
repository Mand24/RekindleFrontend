package com.example.usuario.rekindlefrontend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by ORION on 11/04/2018.
 */

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        FormatCheckerUnity.class,
        ChatsAdapterUnity.class,
        LinksAdapterUnity.class,
        MessageAdapterUnity.class,
        RefugeeAdapterUnity.class,
        ReportsAdapterUnity.class,
        ServiceAdapterUnity.class,
        CodeGeneratorUnity.class,
        GMailUnity.class,
        ChatUnity.class,
        MessageUnity.class,
        LinkUnity.class,
        DonationRequestUnity.class,
        ReportUnity.class,
        DonationUnity.class,
        EducationUnity.class,
        JobUnity.class,
        LodgeUnity.class,
        ServiceUnity.class,
        ValorationUnity.class,
        RefugeeUnity.class,
        UserUnity.class,
        VolunteerUnity.class
})
public class SuiteUnity {
}