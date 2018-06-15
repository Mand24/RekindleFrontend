package com.example.usuario.rekindlefrontend.view.users.show;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.users.edit.EditProfile;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowProfileVolunteer extends Fragment {

    private TextView userType;
    private TextView name;
    private TextView surname1;
    private TextView surname2;
    private TextView email;
    private ImageView photoUser;

    private APIService mAPIService;
    private Volunteer mVolunteer;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_show_volunteer, container,
                false);

        setViews(view);

        sendGetVolunteer();

        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.editar_ver_perfil_voluntario);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), EditProfile.class);
                i.putExtra("Volunteer", mVolunteer);
                System.out.println("ver" + mVolunteer.toString());
                startActivity(i);
            }

        });

        return view;

    }

    public void setViews(View view) {

        userType = view.findViewById(R.id.tipo_usuario_perfil_voluntario);
        name = view.findViewById(R.id.nombre_usuario_perfil_voluntario);
        surname1 = view.findViewById(R.id.apellido1_usuario_perfil_voluntario);
        surname2 = view.findViewById(R.id.apellido2_usuario_perfil_voluntario);
        email = view.findViewById(R.id.email_usuario_perfil_voluntario);
        photoUser = view.findViewById(R.id.foto_perfil_voluntario);

        mAPIService = APIUtils.getAPIService();
    }

    public void sendGetVolunteer() {

        User user = getUser(getActivity().getApplicationContext());

        System.out.println("tipo app: " + user.getMail());
        String mail = user.getMail();

        mAPIService.obtenerVoluntario(mail).enqueue(new Callback<Volunteer>() {
            @Override
            public void onResponse(Call<Volunteer> call, Response<Volunteer> response) {
                if (response.isSuccessful()) {
                    mVolunteer = response.body();
                    System.out.println("llamadab" + response.body().toString());
                    System.out.println("llamada" + mVolunteer);
//                    mVolunteer.setServiceType(1);
                    manageResult(true);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Error al pedir "
                            + "información mVolunteer", Toast
                            .LENGTH_SHORT).show();
                    manageResult(false);
                }
            }

            @Override
            public void onFailure(Call<Volunteer> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "this is an actual network failure"
                                    + " :( inform "
                                    + "the user and "
                                    + "possibly retry", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void manageResult(boolean result) {

        if (result) {

            initializeFields();

        } else {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string
                    .ver_perfil_error), Toast
                    .LENGTH_SHORT).show();
        }
    }

    public void initializeFields() {

        userType.setText("Volunteer");
        name.setText(mVolunteer.getName());
        surname1.setText(mVolunteer.getSurname1());
        surname2.setText(mVolunteer.getSurname2());
        email.setText(mVolunteer.getMail());
        if (mVolunteer.getPhoto() != null) {
            photoUser.setImageBitmap(mVolunteer.getDecodedPhoto());
        } else {
            photoUser.setImageResource(R.drawable.ic_usuario);
        }

    }

}
