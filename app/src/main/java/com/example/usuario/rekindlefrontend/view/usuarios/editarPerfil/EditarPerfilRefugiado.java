package com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.widget.AppCompatButton;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Usuario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.example.usuario.rekindlefrontend.view.usuarios.verPerfil.VerPerfil;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ORION on 27/04/2018.
 */

public class EditarPerfilRefugiado extends AbstractFormatChecker{

    private Refugiado refugiado;
    ArrayAdapter<CharSequence> adapter1, adapter2, adapter3;

    private EditText eNombre;
    private TextView eEmail;
    private EditText ePrimer_apellido;
    private EditText eSegundo_apellido;
    private EditText eTelefono;
    private EditText eNacimiento;
    private Spinner sSexo;
    private EditText eProcedencia;
    private EditText ePueblo;
    private EditText eEtnia;
    private Spinner sGrupo_sanguineo;
    private Spinner sOjos;
    private EditText eBiografia;

    private Bitmap bitmapImage;
    private ImageView ePhoto;

    private APIService mAPIService;

    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_editar_perfil_refugiado, container,
                false);

        setVistas(view);

        refugiado = (Refugiado) getActivity().getIntent().getParcelableExtra("Refugiado");

        System.out.println(refugiado.toString());

        initializeData(view);


        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.guardar_editar_perfil);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try{
                    checkCampos(view);
                    obtenerCampos();
                }
                catch (Exception e){
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                sendActualizarRefugiado();

            }

        });

        AppCompatButton b2 = (AppCompatButton) view.findViewById(R.id.cambiar_password);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), CambiarPassword.class);
                i.putExtra("Refugiado", refugiado);
                i.putExtra("tipo", refugiado.getTipo());
                startActivity(i);
            }

        });

        SetDate setDate = new SetDate(eNacimiento, container.getContext());

        AppCompatButton setPhoto = (AppCompatButton) view.findViewById(R.id.change_photo_refugee);
        setPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto(view);
            }
        });

        return view;
    }

    public void setVistas(View view) {



        eNombre = view.findViewById(R.id.nombre_usuario_perfil);
        eEmail = view.findViewById(R.id.email_usuario_perfil);
        ePrimer_apellido = view.findViewById(R.id.apellido1_usuario_perfil);
        eSegundo_apellido = view.findViewById(R.id.apellido2_usuario_perfil);
        eTelefono = view.findViewById(R.id.telefono_usuario_perfil);
        eNacimiento = view.findViewById(R.id.naciminento_usuario_perfil);
        sSexo = view.findViewById(R.id.sexo_usuario_perfil);
        adapter1 = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_sexo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sSexo.setAdapter(adapter1);
        eProcedencia = view.findViewById(R.id.pais_usuario_perfil);
        ePueblo = view.findViewById(R.id.pueblo_usuario_perfil);
        eEtnia = view.findViewById(R.id.etnia_usuario_perfil);

        sGrupo_sanguineo = view.findViewById(R.id.sangre_usuario_perfil);

        adapter2 = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_grupo_sanguineo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sGrupo_sanguineo.setAdapter(adapter2);

        sOjos = view.findViewById(R.id.ojos_usuario_perfil);

        adapter3 = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_color_ojos, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sOjos.setAdapter(adapter3);

        eBiografia = view.findViewById(R.id.biografia_usuario_perfil);

        ePhoto = view.findViewById(R.id.refugee_photo);

        mAPIService = APIUtils.getAPIService();

    }


    public void initializeData(View view){

        eNombre.setText(refugiado.getName());
        eEmail.setText(refugiado.getMail());
        ePrimer_apellido.setText(refugiado.getSurname1());
        eSegundo_apellido.setText(refugiado.getSurname2());
        eTelefono.setText(refugiado.getPhoneNumber());
        eNacimiento.setText(refugiado.getBirthDate());

        int selectionPosition = adapter1.getPosition(refugiado.getSex());
        sSexo.setSelection(selectionPosition);

        eProcedencia.setText(refugiado.getCountry());
        ePueblo.setText(refugiado.getTown());
        eEtnia.setText(refugiado.getEthnic());

        selectionPosition = adapter2.getPosition(refugiado.getBloodType());
        sGrupo_sanguineo.setSelection(selectionPosition);

        selectionPosition = adapter3.getPosition(refugiado.getEyeColor());
        sOjos.setSelection(selectionPosition);

        eBiografia.setText(refugiado.getBiography());

        if(refugiado.getPhoto() != null) {
            ePhoto.setImageBitmap(refugiado.getDecodedPhoto());
        }else{
            ePhoto.setImageResource(R.drawable.foto_perfil);
        }
    }

    public void checkCampos(View view) throws Exception {

        checkNombre(eNombre.getText().toString());
        checkPrimer_apellido(ePrimer_apellido.getText().toString());
        checkSegundo_apellido(eSegundo_apellido.getText().toString());
        checkTelefono(eTelefono.getText().toString());
        checkProcedencia(eProcedencia.getText().toString());
        checkPueblo(ePueblo.getText().toString());
        checkEtnia(eEtnia.getText().toString());
        checkBiografia(eBiografia.getText().toString());
    }

    public void obtenerCampos() {
        refugiado.setName(eNombre.getText().toString());
        refugiado.setSurname1(ePrimer_apellido.getText().toString());
        refugiado.setSurname2(eSegundo_apellido.getText().toString());
        refugiado.setPhoneNumber(eTelefono.getText().toString());
        refugiado.setCountry(eProcedencia.getText().toString());
        refugiado.setTown(ePueblo.getText().toString());
        refugiado.setEthnic(eEtnia.getText().toString());
        refugiado.setBirthDate(eNacimiento.getText().toString());
        refugiado.setSex(sSexo.getSelectedItem().toString());
        refugiado.setBloodType(sGrupo_sanguineo.getSelectedItem().toString());
        refugiado.setEyeColor(sOjos.getSelectedItem().toString());
        refugiado.setBiography(eBiografia.getText().toString());

        if(bitmapImage != null) {
            refugiado.setPhoto(encode_photo(bitmapImage));
        }else {
            refugiado.setPhoto(null);
        }
    }

    public void sendActualizarRefugiado() {
        mAPIService.actualizarRefugiado(refugiado.getMail(), refugiado).enqueue(
                new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            tratarResultadoPeticion(true);
                        } else {
                            tratarResultadoPeticion(false);
                        }
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
                                    "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void tratarResultadoPeticion(boolean result) {

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), "Actualizado correctamente",
                    Toast
                            .LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), VerPerfil.class);
            startActivity(i);

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Actualización fallida", Toast
                    .LENGTH_SHORT).show();
        }
    }

    public void takePhoto(View view){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(i, TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PICTURE:
                if(resultCode == Activity.RESULT_OK){
                    Uri selectedImage = imageUri;
                    getActivity().getContentResolver().notifyChange(selectedImage, null);
                    ContentResolver cr = getActivity().getContentResolver();
                    try{
                        bitmapImage = android.provider.MediaStore.Images.Media.getBitmap(cr,
                                selectedImage);
                        ePhoto.setImageBitmap(bitmapImage);
                        Toast.makeText(getActivity(), selectedImage.toString(), Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }

    private String encode_photo(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] byte_arr = stream.toByteArray();
        return Base64.encodeToString(byte_arr, Base64.DEFAULT);
    }

}
