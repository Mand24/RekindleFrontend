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
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.view.users.edit.EditProfile;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowProfileRefugee extends Fragment {

    private TextView userType;
    private TextView name;
    private TextView surname1;
    private TextView surname2;
    private TextView email;
    private TextView phoneNumber;
    private TextView birthdate;
    private TextView sex;
    private TextView country;
    private TextView town;
    private TextView ethnic;
    private TextView blood;
    private TextView eyes;
    private TextView biography;
    private ImageView photoUser;

    private APIService mAPIService;
    private Refugee mRefugee;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_ver_perfil_refugiado, container,
                false);

        setViews(view);
        sendGetRefugee();

        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.editar_ver_perfil_refugiado);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), EditProfile.class);
                i.putExtra("Refugee", mRefugee);
                startActivity(i);
            }

        });

        return view;

    }

    public void setViews(View view) {

        userType = view.findViewById(R.id.tipo_usuario_perfil_refugiado);
        name = view.findViewById(R.id.nombre_usuario_perfil_refugiado);
        surname1 = view.findViewById(R.id.apellido1_usuario_perfil_refugiado);
        surname2 = view.findViewById(R.id.apellido2_usuario_perfil_refugiado);
        email = view.findViewById(R.id.email_usuario_perfil_refugiado);
        phoneNumber = view.findViewById(R.id.telefono_usuario_perfil_refugiado);
        birthdate = view.findViewById(R.id.naciminento_usuario_perfil_refugiado);
        sex = view.findViewById(R.id.sexo_usuario_perfil_refugiado);
        country = view.findViewById(R.id.pais_usuario_perfil_refugiado);
        town = view.findViewById(R.id.pueblo_usuario_perfil_refugiado);
        ethnic = view.findViewById(R.id.etnia_usuario_perfil_refugiado);
        blood = view.findViewById(R.id.sangre_usuario_perfil_refugiado);
        eyes = view.findViewById(R.id.ojos_usuario_perfil_refugiado);
        biography = view.findViewById(R.id.biografia_usuario_perfil_refugiado);
        photoUser = view.findViewById(R.id.foto_perfil_refugiado);

        mAPIService = APIUtils.getAPIService();
    }

    public void sendGetRefugee() {

        User user = getUser(getActivity().getApplicationContext());

        System.out.println("tipo app: " + user.getMail());
        String mail = user.getMail();

        mAPIService.obtenerRefugiado(mail).enqueue(new Callback<Refugee>() {
            @Override
            public void onResponse(Call<Refugee> call, Response<Refugee> response) {
                if (response.isSuccessful()) {
                    System.out.println("dentro respuesta");
                    if (response.body() != null) System.out.println("dentro respuesta ok");
                    mRefugee = response.body();
//                    mRefugee.setServiceType(0);
                    manageResult(true);
                } else {
                    System.out.println("mRefugee null");
                    System.out.println("Mensaje: " + response.message());
                    System.out.println("codi: " + response.code());
                    System.out.println("dentro respuesta failed");
                    manageResult(false);
                }
            }

            @Override
            public void onFailure(Call<Refugee> call, Throwable t) {
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

            Toast.makeText(getActivity().getApplicationContext(), "ver perfil correctamente",
                    Toast
                            .LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "ver perfil fallida", Toast
                    .LENGTH_SHORT).show();
        }
    }

    public void initializeFields() {

        userType.setText("Refugee");
        name.setText(mRefugee.getName());
        surname1.setText(mRefugee.getSurname1());
        surname2.setText(mRefugee.getSurname2());
        email.setText(mRefugee.getMail());
        phoneNumber.setText(mRefugee.getPhoneNumber());
        birthdate.setText(mRefugee.getBirthDate());
        sex.setText(mRefugee.getSex());
        country.setText(mRefugee.getCountry());
        town.setText(mRefugee.getTown());
        ethnic.setText(mRefugee.getEthnic());
        blood.setText(mRefugee.getBloodType());
        eyes.setText(mRefugee.getEyeColor());
        biography.setText(mRefugee.getBiography());
        if (mRefugee.getPhoto() != null) {
            photoUser.setImageBitmap(mRefugee.getDecodedPhoto());
        } else {
            photoUser.setImageResource(R.drawable.ic_usuario);
        }
    }

}
