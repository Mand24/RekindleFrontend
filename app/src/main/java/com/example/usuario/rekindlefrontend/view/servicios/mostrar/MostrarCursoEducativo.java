package com.example.usuario.rekindlefrontend.view.servicios.mostrar;


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
import com.example.usuario.rekindlefrontend.data.entity.servicio.CursoEducativo;
import com.example.usuario.rekindlefrontend.data.entity.servicio.Servicio;
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
public class MostrarCursoEducativo extends Maps implements OnMapReadyCallback {

    public MostrarCursoEducativo() {
        // Required empty public constructor
    }

    TextView titulo, descripcion, direccion, ambito, requisitos, horario, precio, numero;
    AppCompatButton chat, inscribirse;

    public CursoEducativo servicio;
    public MapFragment mMapView;
    public GoogleMap mGoogleMap;
    public Marker myMarker;
    private APIService mAPIService = APIUtils.getAPIService();
    private final String TYPE = "Education";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_mostrar_curso_educativo, container,
                false);

        super.onCreate(savedInstanceState);

        servicio = (CursoEducativo) getArguments().getSerializable("servicioFrag");

        titulo = view.findViewById(R.id.titulo_curso_educativo);
        descripcion = view.findViewById(R.id.descripcion_curso_educativo);
        direccion = view.findViewById(R.id.direccion_curso_educativo);
        ambito = view.findViewById(R.id.ambito_curso_educativo);
        requisitos = view.findViewById(R.id.requisitos_curso_educativo);
        horario = view.findViewById(R.id.horario_curso_educativo);
        precio = view.findViewById(R.id.precio_curso_educativo);
        mMapView = (MapFragment) getChildFragmentManager().findFragmentById(R.id.google_mapView);
        numero = view.findViewById(R.id.numero_contacto_servicio);
        chat = view.findViewById(R.id.chat);
        inscribirse = view.findViewById(R.id.inscribirse);

        titulo.setText(servicio.getNombre());
        descripcion.setText(servicio.getDescripcion());
        direccion.setText(servicio.getDireccion());
        ambito.setText(servicio.getAmbito());
        requisitos.setText(servicio.getRequisitos());
        horario.setText(servicio.getHorario());
        precio.setText(servicio.getPrecio());
        numero.setText(servicio.getNumero());

        mMapView.getMapAsync(this);

        inscribirse.setClickable(false);

        final String mail = Consistency.getUser(container.getContext()).getMail();

        mAPIService.isUserSubscribed(mail, servicio.getId(), TYPE).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    inscribirse.setClickable(true);
                    if(response.body()) {
                        inscribirse.setText(R.string.unsubscribe);
                    }
                    else{
                        inscribirse.setText(R.string.inscribir);
                    }
                } else {
                    System.out.println("CODIGO "+response.code());
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
                if(inscribirse.getText().toString().equals(R.string.inscribir)) {
                    mAPIService.subscribeService(mail,
                            servicio.getId(), TYPE);
                    inscribirse.setText(R.string.unsubscribe);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            view.getContext());

                    builder.setMessage(R.string.unsubscribe_confirmation);
                    builder.setCancelable(false);
                    builder.setPositiveButton(R.string.yes,
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    mAPIService.unsubscribeService(mail, servicio.getId(), TYPE);
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

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        NetworkInfo network = getNetworkInfo ();

        if (network != null && network.isConnectedOrConnecting ()) {
            try {
                myMarker = setMarker(servicio.getDireccion (), myMarker, mGoogleMap);
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
