package com.example.usuario.rekindlefrontend.view.services.mostrar;


import android.app.Fragment;
import android.content.DialogInterface;
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
import com.example.usuario.rekindlefrontend.data.entity.service.Job;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.utils.Maps;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarOfertaEmpleo extends Maps implements OnMapReadyCallback {

    public MostrarOfertaEmpleo() {
        // Required empty public constructor
    }


    TextView titulo, descripcion, direccion, numero, puesto, requisitos, jornada, horas, duracion;
    AppCompatButton chat, inscribirse;

    public Job servicio;
    public MapFragment mMapView;
    public GoogleMap mGoogleMap;
    public Marker myMarker;
    private APIService mAPIService = APIUtils.getAPIService();
    private final String TYPE = "Job";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_mostrar_oferta_empleo, container,
                false);

        super.onCreate(savedInstanceState);

        servicio = (Job) getArguments().getSerializable("servicioFrag");

        titulo = view.findViewById(R.id.titulo_oferta_empleo);
        descripcion = view.findViewById(R.id.descripcion_oferta_empleo);
        direccion = view.findViewById(R.id.direccion_oferta_empleo);
        puesto = view.findViewById(R.id.puesto_oferta_empleo);
        requisitos = view.findViewById(R.id.requisitos_oferta_empleo);
        jornada = view.findViewById(R.id.jornada_oferta_empleo);
        horas = view.findViewById(R.id.horas_semanales_oferta_empleo);
        duracion = view.findViewById(R.id.duracion_oferta_empleo);
        mMapView = (MapFragment) getChildFragmentManager()
                .findFragmentById(R.id.google_mapView);
        numero = view.findViewById(R.id.numero_contacto_servicio);
        chat = view.findViewById(R.id.chat);
        inscribirse = view.findViewById(R.id.inscribirse);

        titulo.setText(servicio.getName());
        descripcion.setText(servicio.getDescription());
        direccion.setText(servicio.getAdress());
        puesto.setText(servicio.getCharge());
        requisitos.setText(servicio.getRequirements());
        jornada.setText(servicio.getHoursDay());
        horas.setText(servicio.getHoursWeek());
        duracion.setText(servicio.getContractDuration());
        numero.setText(servicio.getPhoneNumber());

        mMapView.getMapAsync(this);

        inscribirse.setClickable(false);

        Usuario user = Consistency.getUser(container.getContext());
        final String mail = user.getMail();
        String type = user.getTipo();

        if(type.equals("Refugee")) {

            mAPIService.isUserSubscribed(mail, servicio.getId(), TYPE).enqueue(
                    new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (response.isSuccessful()) {
                                inscribirse.setClickable(true);
                                if (response.body()) {
                                    inscribirse.setText(R.string.unsubscribe);
                                } else {
                                    inscribirse.setText(R.string.inscribir);
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

            inscribirse.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View view) {
                    if (inscribirse.getText().toString().equals(R.string.inscribir)) {
                        mAPIService.subscribeService(mail,
                                servicio.getId(), TYPE);
                        inscribirse.setText(R.string.unsubscribe);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                view.getContext());

                        builder.setMessage(R.string.unsubscribe_confirmation);
                        builder.setCancelable(false);
                        builder.setPositiveButton(R.string.yes,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog,
                                            int which) {
                                        mAPIService.unsubscribeService(mail, servicio.getId(),
                                                TYPE);
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
                        inscribirse.setText(R.string.inscribir);
                    }
                }
            });
        }else{
            inscribirse.setText(R.string.not_available);
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        NetworkInfo network = getNetworkInfo ();

        if (network != null && network.isConnectedOrConnecting ()) {
            try {
                myMarker = setMarker(servicio.getAdress(), myMarker, mGoogleMap, servicio.getName());
            } catch (Exception e) // Conectats però sense internet (p.e. falta logejar-nos)
            {
                Toast.makeText(getActivity ().getApplicationContext (), getString(R.string
                        .nointernet), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getActivity ().getApplicationContext (), getString (R.string.nomap),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void failure(){
        Toast.makeText(getActivity(), getResources().getString(R
                .string.error), Toast.LENGTH_SHORT).show();
    }

}
