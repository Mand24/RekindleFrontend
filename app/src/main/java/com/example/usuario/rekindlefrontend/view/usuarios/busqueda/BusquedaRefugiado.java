package com.example.usuario.rekindlefrontend.view.usuarios.busqueda;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.AppBaseActivity;
import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.example.usuario.rekindlefrontend.view.menu.login.Login;
import com.example.usuario.rekindlefrontend.view.menu.menuPrincipal.MenuPrincipal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ORION on 04/05/2018.
 */

public class BusquedaRefugiado extends AppBaseActivity{


    private ArrayList<String> param = new ArrayList<String>();

    private EditText eNombre;
    private EditText ePrimer_apellido;
    private EditText eSegundo_apellido;
    private EditText eNacimiento;
    private Spinner sSexo;
    private EditText eProcedencia;
    private EditText ePueblo;
    private EditText eEtnia;
    private Spinner sGrupo_sanguineo;
    private Spinner sOjos;

    private String eNombreString;
    private String ePrimer_apellidoString;
    private String eSegundo_apellidoString;
    private String eNacimientoString;
    private String sSexoString;
    private String eProcedenciaString;
    private String ePuebloString;
    private String eEtniaString;
    private String sGrupo_sanguineoString;
    private String sOjosString;

    private APIService mAPIService;
    private ArrayList<Refugiado> listRefugiados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_refugiado);
        //establecer las vistas
        setVistas();



        AppCompatButton button_send = (AppCompatButton) findViewById(R.id
                .enviar_buscar_personas);
        button_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    //checkCampos(view);
                    obtenerParametros();
                    /*boolean result = new AsyncTaskCall().execute().get();
                    manageRequestResult(result);
                    //manageRequestResult(true);*/
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                sendBuscarRefugiados();


            }
        });


        SetDate setDate = new SetDate(eNacimiento, this);

    }


    public void setVistas() {

        eNombre = findViewById(R.id.nombre_refugiado);
        ePrimer_apellido = findViewById(R.id.p_apellido_refugiado);
        eSegundo_apellido = findViewById(R.id.s_apellido_refugiado);
        eNacimiento = findViewById(R.id.nacimiento_refugiado);
        sSexo = findViewById(R.id.sexo_refugiado);
        ArrayAdapter<CharSequence> adapter_sexo = ArrayAdapter.createFromResource
                (getApplicationContext(), R.array.lista_sexo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_sexo.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sSexo.setAdapter(adapter_sexo);
        eProcedencia = findViewById(R.id.procedencia_refugiado);
        ePueblo = findViewById(R.id.pueblo_refugiado);
        eEtnia = findViewById(R.id.etnia_refugiado);

        sGrupo_sanguineo = findViewById(R.id.grupo_sanguineo_refugiado);

        ArrayAdapter<CharSequence> adapter_gs = ArrayAdapter.createFromResource
                (getApplicationContext(), R.array.lista_grupo_sanguineo, R.layout
                        .spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_gs.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sGrupo_sanguineo.setAdapter(adapter_gs);

        sOjos = findViewById(R.id.ojos_refugiado);

        ArrayAdapter<CharSequence> adapter_ojos = ArrayAdapter.createFromResource
                (getApplicationContext(), R.array.lista_color_ojos, R.layout
                        .spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_ojos.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sOjos.setAdapter(adapter_ojos);

        mAPIService = APIUtils.getAPIService();
    }

    public void obtenerParametros() {

        eNombreString = eNombre.getText().toString();
        ePrimer_apellidoString = ePrimer_apellido.getText().toString();
        eSegundo_apellidoString = eSegundo_apellido.getText().toString();
        eNacimientoString = eNacimiento.getText().toString();
        if (eNacimientoString.equals("")) eNacimientoString = "1890-01-01";
        sSexoString = sSexo.getSelectedItem().toString();
        eProcedenciaString = eProcedencia.getText().toString();
        ePuebloString = ePueblo.getText().toString();
        eEtniaString = eEtnia.getText().toString();
        sGrupo_sanguineoString = sGrupo_sanguineo.getSelectedItem().toString();
        sOjosString = sOjos.getSelectedItem().toString();
    }

    /*public void checkCampos(View view) throws Exception {

        checkNombre(eNombre.getText().toString());
        checkPrimer_apellido(ePrimer_apellido.getText().toString());
        checkSegundo_apellido(eSegundo_apellido.getText().toString());
        checkProcedencia(eProcedencia.getText().toString());
        checkPueblo(ePueblo.getText().toString());
        checkEtnia(eEtnia.getText().toString());
    }*/

    public void sendBuscarRefugiados() {

        mAPIService.buscarRefugiados(
                eNombreString,
                ePrimer_apellidoString,
                eSegundo_apellidoString,
                eNacimientoString,
                sSexoString,
                eProcedenciaString,
                ePuebloString,
                eEtniaString,
                sGrupo_sanguineoString,
                sOjosString).enqueue(new Callback<ArrayList<Refugiado>>() {
            @Override
            public void onResponse(Call<ArrayList<Refugiado>> call, Response<ArrayList<Refugiado>> response) {
                System.out.println("url " +call.request().url());

                if (response.isSuccessful()) {
                    System.out.println("dentro respuesta ok");
                    listRefugiados = response.body();

                    tratarResultadoPeticion(true);
//                    showResponse(response.body().toString());
//                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    if (response.body() != null) System.out.println("Resposta: " + response.toString
                            ());
                    else System.out.println("refugiado null");
                    System.out.println("Mensaje: " + response.message());
                    System.out.println("codi: " + response.code());
                    System.out.println("dentro respuesta failed");
                    tratarResultadoPeticion(false);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Refugiado>> call, Throwable t) {
                System.out.println("url " +call.request().url());
//                Log.e(TAG, "Unable to submit post to API.");
                /*if (t instanceof IOException) {
                    Toast.makeText(getActivity().getApplicationContext(), "this is an actual network failure"
                            + " :( inform "
                            + "the user and "
                            + "possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                }*/
                tratarResultadoPeticion(false);
            }
        });
    }

    public void tratarResultadoPeticion(boolean result) {

        if (result) {

            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.busqueda_correcta), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), ListarRefugiados.class);
//            i.putExtra("listRefugiados", listRefugiados);
            i.putParcelableArrayListExtra("listRefugiados", listRefugiados);
            startActivity(i);

        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R
                    .string.busqueda_fallida), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void gotoInicio() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }


}
