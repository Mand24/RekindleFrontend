package com.example.usuario.rekindlefrontend.view.services.show;


import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.service.Job;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.utils.Maps;
import com.example.usuario.rekindlefrontend.view.chat.ShowChat;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobShow extends Maps implements OnMapReadyCallback {

    private final String TYPE = "Job";
    public Job service;
    public MapFragment mMapView;
    public GoogleMap mGoogleMap;
    public Marker myMarker;
    TextView title, description, adress, phoneNumber, charge, requirements, hoursDay, hoursWeek,
            contractDuration;
    AppCompatButton chat, enroll, endButton;
    private APIService mAPIService = APIUtils.getAPIService();
    private User currentUser;
    private Chat newChat;
    public JobShow() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_show_job, container,
                false);

        super.onCreate(savedInstanceState);

        service = (Job) getArguments().getSerializable("Job");

        title = view.findViewById(R.id.titulo_oferta_empleo);
        description = view.findViewById(R.id.descripcion_oferta_empleo);
        adress = view.findViewById(R.id.direccion_oferta_empleo);
        charge = view.findViewById(R.id.puesto_oferta_empleo);
        requirements = view.findViewById(R.id.requisitos_oferta_empleo);
        hoursDay = view.findViewById(R.id.jornada_oferta_empleo);
        hoursWeek = view.findViewById(R.id.horas_semanales_oferta_empleo);
        contractDuration = view.findViewById(R.id.duracion_oferta_empleo);
        mMapView = (MapFragment) getChildFragmentManager()
                .findFragmentById(R.id.google_mapView);
        phoneNumber = view.findViewById(R.id.numero_contacto_servicio);
        chat = view.findViewById(R.id.chat);
        enroll = view.findViewById(R.id.inscribirse);
        endButton = view.findViewById(R.id.endButton);

        title.setText(service.getName());
        description.setText(service.getDescription());
        adress.setText(getString(R.string.address_show, service.getAdress()));
        charge.setText(getString(R.string.charge_show,service.getCharge()));
        requirements.setText(getString(R.string.requirements_show, service.getRequirements()));
        hoursDay.setText(getString(R.string.hours_day_show,service.getHoursDay()));
        hoursWeek.setText(getString(R.string.hours_week_show,service.getHoursWeek()));
        contractDuration.setText(getString(R.string.contract_duration_show,service
                .getContractDuration()));
        phoneNumber.setText(getString(R.string.phone_show,service.getPhoneNumber()));

        mMapView.getMapAsync(this);

        enroll.setClickable(false);
        endButton.setClickable(false);

        currentUser = Consistency.getUser(container.getContext());
        final String mail = currentUser.getMail();
        String type = currentUser.getUserType();

        if (type.equals("Refugee")) {

            endButton.setVisibility(View.INVISIBLE);
            chat.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    sendGetChat();
                }
            });

            mAPIService.isUserSubscribed(mail, service.getId(), TYPE).enqueue(

                    new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (response.isSuccessful()) {
                                enroll.setClickable(true);
                                if (response.body()) {
                                    enroll.setText(R.string.unsubscribe);
                                } else {
                                    enroll.setText(R.string.inscribir);

                                }
                            } else {
                                System.out.println("CODIGO " + response.code());
                                failure();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Log.e("on Failure", t.toString());
                            failure();
                        }
                    });

            enroll.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View view) {

                    if (enroll.getText().toString().equals(getResources().getString(R
                            .string.inscribir))) {
                        mAPIService.subscribeService(currentUser.getApiKey(), mail,

                                service.getId(), TYPE).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    enroll.setText(R.string.unsubscribe);
                                } else {
                                    failure();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                failure();
                            }
                        });
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                view.getContext());

                        builder.setMessage(R.string.unsubscribe_confirmation);
                        builder.setCancelable(false);
                        builder.setPositiveButton(R.string.yes,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog,
                                            int which) {

                                        mAPIService.unsubscribeService(currentUser.getApiKey(),
                                                mail, service
                                                        .getId(),
                                                TYPE).enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call,
                                                    Response<Void> response) {
                                                if (response.isSuccessful()) {
                                                    enroll.setText(R.string.inscribir);
                                                } else {
                                                    failure();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                failure();
                                            }
                                        });
                                    }
                                });

                        builder.setNegativeButton(R.string.no,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog,
                                            int which) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert = builder.create();
                        alert.show();
                        enroll.setText(R.string.inscribir);
                    }
                }
            });
        } else if(type.equals("Volunteer") && currentUser.getMail().equals(service.getEmail())){
            enroll.setVisibility(View.INVISIBLE);
            chat.setVisibility(View.INVISIBLE);

            endButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            view.getContext());

                    builder.setMessage(R.string.close_service_confirmation);
                    builder.setCancelable(false);
                    builder.setPositiveButton(R.string.yes,
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    service.setEnded(true);
                                    endButton.setText(R.string.closedService);
                                    endButton.setClickable(false);
                                    endButton.setBackgroundColor(getResources().getColor(R.color.colorIron));
                                    sendEditService(service);
                                }
                            });

                    builder.setNegativeButton(R.string.no,
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();

                }

            });
        }
        else {
            enroll.setVisibility(View.INVISIBLE);
            chat.setVisibility(View.INVISIBLE);
            endButton.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    public void sendEditService(Job service){

        mAPIService.editarEmpleo(currentUser.getApiKey(), service.getId(), service).enqueue(
                new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "this is an actual network failure"
                                            + " :( inform "
                                            + "the user and "
                                            + "possibly retry", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "conversion issue! big problems :(", Toast
                                            .LENGTH_SHORT).show();

                        }
                    }
                }
        );
    }

    public void sendGetChat() {
        String mail1, mail2;
        if (currentUser.getMail().compareToIgnoreCase(service.getEmail()) <= 0 ){
            mail1 = currentUser.getMail();
            mail2 = service.getEmail();
        }else {
            mail1 = service.getEmail();
            mail2 = currentUser.getMail();
        }
        mAPIService.getChat(mail1, mail2)
                .enqueue(
                        new Callback<Chat>() {
                            @Override
                            public void onResponse(Call<Chat> call, Response<Chat> response) {
                                System.out.println("getchat code: " + response.code());
                                if (response.isSuccessful()) {
                                    System.out.println("getchat");
                                    System.out.println(response.body().toString());
                                    manageResultGetChat(true, response.body());
                                } else {
                                    manageResultGetChat(false, null);
                                }
                            }

                            @Override
                            public void onFailure(Call<Chat> call, Throwable t) {
                                if (t instanceof IOException) {
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "this is an actual network failure"
                                                    + " :( inform "
                                                    + "the user and "
                                                    + "possibly retry", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "getchat!! conversion issue! big problems :(", Toast
                                                    .LENGTH_SHORT).show();

                                }
                            }
                        });
    }

    public void manageResultGetChat(boolean resultado, Chat chat) {
        if (resultado) {
            Intent i = new Intent(getActivity().getApplicationContext(), ShowChat.class);
            i.putExtra("Chat", chat);
            startActivity(i);
        } else {
            mAPIService.obtenerVoluntario(service.getEmail()).enqueue(new Callback<Volunteer>() {
                @Override
                public void onResponse(Call<Volunteer> call, Response<Volunteer> response) {
                    if (response.isSuccessful()) {
                        manageResultGetVolunteer(true, response.body());
                    } else {
                        manageResultGetVolunteer(false, null);
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
                                "getchat!! conversion issue! big problems :(", Toast
                                        .LENGTH_SHORT).show();

                    }
                }
            });

        }
    }

    public void manageResultGetVolunteer(boolean result, Volunteer volunteer) {
        if (result) {
            User user1, user2;
            if (currentUser.getMail().compareToIgnoreCase(volunteer.getMail()) <= 0){
                user1 = currentUser;
                user2 = volunteer;
            }else {
                user1 = volunteer;
                user2 = currentUser;
            }
            newChat = new Chat(user1, user2);
            sendNewChat(newChat);
        } else {
            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.error), Toast.LENGTH_SHORT).show();
        }
    }

    public void sendNewChat(Chat chat) {

        mAPIService.newChat(currentUser.getApiKey(), currentUser.getMail(), chat).enqueue(new
                                                                                                 Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                System.out.println("newchat code: " + response.code());
                if (response.isSuccessful()) {
                    System.out.println("newchat");
                    System.out.println(response.body().toString());
                    manageResultNewChat(true, response.body());
                } else {
                    manageResultNewChat(false, null);
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "this is an actual network failure"
                                    + " :( inform "
                                    + "the user and "
                                    + "possibly retry", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "newchat!! conversion issue! big problems :(", Toast.LENGTH_SHORT)
                            .show();

                }
            }
        });

    }

    public void manageResultNewChat(boolean result, Chat chat) {
        if (result) {
            Intent i = new Intent(getActivity().getApplicationContext(), ShowChat.class);
            i.putExtra("Chat", chat);
            startActivity(i);
        } else {
            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        NetworkInfo network = getNetworkInfo();

        if (network != null && network.isConnectedOrConnecting()) {
            try {
                myMarker = setMarker(service.getAdress(), myMarker, mGoogleMap, service.getName());
            } catch (Exception e) // Conectats però sense internet (p.e. falta logejar-nos)
            {
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string
                        .nointernet), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.nomap),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void failure() {
        Toast.makeText(getActivity(), getResources().getString(R
                .string.error), Toast.LENGTH_SHORT).show();
    }

}
