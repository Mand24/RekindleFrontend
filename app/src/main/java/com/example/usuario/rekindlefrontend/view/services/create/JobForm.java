package com.example.usuario.rekindlefrontend.view.services.create;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.data.entity.service.Job;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;
import com.example.usuario.rekindlefrontend.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobForm extends AbstractFormatChecker {

    private EditText eAdress;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private EditText eName;
    private EditText ePhoneNumber;
    private EditText eCharge;
    private EditText eRequirements;
    private EditText eHoursDay;
    private EditText eHoursWeek;
    private EditText eContractDuration;
    private EditText eSalary;
    private EditText ePlacesLimit;
    private EditText eDescription;

    private Job mJob;
    private APIService mAPIService;

    public JobForm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_formulario_oferta_empleo, container,
                false);

        //establecer las vistas
        setViews(view);

        AppCompatButton button_send = (AppCompatButton) view.findViewById(R.id.enviar_formulario_oferta_empleo);
        button_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    checkFields(view);
                    getParams();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendCreateJob();
            }
        });

        eAdress = view.findViewById(R.id.direccion_oferta_empleo);
        eAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete
                            .MODE_OVERLAY).build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                }catch (GooglePlayServicesRepairableException e) {
                } catch (GooglePlayServicesNotAvailableException e) {
                }
            }
        });

        return view;
    }

    public void setViews(View view) {

        eName = view.findViewById(R.id.nombre_oferta_empleo);
        ePhoneNumber = view.findViewById(R.id.telefono_oferta_empleo);
        eAdress = view.findViewById(R.id.direccion_oferta_empleo);
        eCharge = view.findViewById(R.id.puesto_oferta_empleo);
        eRequirements = view.findViewById(R.id.requisitos_oferta_empleo);
        eHoursDay = view.findViewById(R.id.jornada_oferta_empleo);
        eHoursWeek = view.findViewById(R.id.horas_semanales_oferta_empleo);
        eContractDuration = view.findViewById(R.id.duracion_oferta_empleo);
        eSalary = view.findViewById(R.id.sueldo_oferta_empleo);
        ePlacesLimit = view.findViewById(R.id.plazas_oferta_empleo);
        eDescription = view.findViewById(R.id.descripcion_oferta_empleo);

        mAPIService = APIUtils.getAPIService();

    }

    public void checkFields(View view) throws Exception {

        checkNombreServicio(eName.getText().toString());
        checkTelefonoServicio(ePhoneNumber.getText().toString());
        checkPuestoOfertaEmpleo(eCharge.getText().toString());
        checkRequisitosServicio(eRequirements.getText().toString());
        checkJornadaOfertaEmpleo(eHoursDay.getText().toString());
        checkHorasOfertaEmpleo(eHoursWeek.getText().toString());
        checkDuracionOfertaEmpleo(eContractDuration.getText().toString());
        checkSueldoOfertaEmpleo(eSalary.getText().toString());
        checkPlazasServicio(ePlacesLimit.getText().toString());
        checkDescripcionServicio(eDescription.getText().toString());

    }

    public void getParams(){

        Usuario user = getUser(getActivity().getApplicationContext());

        mJob = new Job(0, user.getMail(), eName.getText().toString(),
                eDescription.getText().toString(), eAdress.getText().toString(), eCharge
                .getText().toString(), eRequirements.getText().toString(), eHoursDay.getText()
                .toString(), eHoursWeek.getText().toString(), eContractDuration.getText().toString(), ePlacesLimit
                .getText().toString(), eSalary.getText().toString(), ePhoneNumber.getText().toString
                ());

    }

    public void sendCreateJob(){
        mAPIService.crearOferta(mJob).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    manageResult(true);
                }else {
                    System.out.println("codi "+response.code());
                    manageResult(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                manageResult(false);

            }
        });
    }

    public void manageResult(boolean result){

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                    .string.servicio_alojamiento_creado_correctamente), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);
            startActivity(i);

        }else Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R
                .string.servicio_alojamiento_fallido), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                Log.i("==================", "Place: " + place.getName());
                eAdress.setText(place.getAddress());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                Log.i("==================", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

}
