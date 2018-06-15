package com.example.usuario.rekindlefrontend.view.users.edit;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.users.show.ShowProfile;

import java.util.HashMap;

public class EditProfile extends AppBaseActivity {

    HashMap<String, Fragment> profileTypes;
    Volunteer mVolunteer;
    Refugee mRefugee;

    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setTitle(R.string.editProfile);

        profileTypes = new HashMap<>();

        profileTypes.put("Refugee", new EditProfileRefugee());
        profileTypes.put("Volunteer", new EditProfileVolunteer());

        User user = getUser(this);

        String userType = user.getUserType();

        System.out.println("editar" + userType);

        if (userType.equals("Refugee")) {
            mRefugee = (Refugee) getIntent().getParcelableExtra("Refugee");
        } else {
            mVolunteer = (Volunteer) getIntent().getParcelableExtra("Volunteer");
            System.out.println("editaraa" + mVolunteer);
        }
        menu(userType);
    }

    public void menu(String userType) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (userType.equals("Refugee")) {
            bundle.putParcelable("Refugee", mRefugee);
        } else {
            System.out.println("editara" + mVolunteer.toString());
            bundle.putParcelable("Volunteer", mVolunteer);
        }

        transaction.replace(R.id.perfilUsuario, profileTypes.get(userType));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, ShowProfile.class);
        startActivity(i);
    }

    @Override
    protected void gotoLaunch() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
