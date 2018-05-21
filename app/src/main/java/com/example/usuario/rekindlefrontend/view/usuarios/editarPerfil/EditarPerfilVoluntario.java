package com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.widget.AppCompatButton;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Voluntario;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.view.usuarios.verPerfil.VerPerfil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ORION on 27/04/2018.
 */

public class EditarPerfilVoluntario extends AbstractFormatChecker{

    private Voluntario voluntario;

    private EditText eNombre;
    private TextView eEmail;
    private EditText ePrimer_apellido;
    private EditText eSegundo_apellido;

    private Bitmap bitmapImage;
    private ImageView ePhoto;

    private APIService mAPIService;

    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_editar_perfil_voluntario, container,
                false);


        setVistas(view);

        voluntario = (Voluntario) getActivity().getIntent().getParcelableExtra("Voluntario");

        System.out.println("editarv"+ voluntario.toString());

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
                sendActualizarVoluntario();
            }

        });

        AppCompatButton b2 = (AppCompatButton) view.findViewById(R.id.cambiar_password);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), CambiarPassword.class);
                i.putExtra("Voluntario", voluntario);
                i.putExtra("tipo", voluntario.getTipo());
                System.out.println(voluntario.toString());
                startActivity(i);
            }

        });

        AppCompatButton setPhoto = (AppCompatButton) view.findViewById(R.id.change_photo_volunteer);
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
        ePhoto = view.findViewById(R.id.volunteer_photo);

        mAPIService = APIUtils.getAPIService();

    }


    public void initializeData(View view){

        eNombre.setText(voluntario.getName());
        eEmail.setText(voluntario.getMail());
        ePrimer_apellido.setText(voluntario.getSurname1());
        eSegundo_apellido.setText(voluntario.getSurname2());

        if(voluntario.getPhoto() != null) {
            ePhoto.setImageBitmap(voluntario.getDecodedPhoto());
        }else{
            ePhoto.setImageResource(R.drawable.foto_perfil);
        }

    }

    public void checkCampos(View view) throws Exception {

        checkNombre(eNombre.getText().toString());;
        checkPrimer_apellido(ePrimer_apellido.getText().toString());
        checkSegundo_apellido(eSegundo_apellido.getText().toString());

    }

    public void obtenerCampos() {
        voluntario.setName(eNombre.getText().toString());
        voluntario.setSurname1(ePrimer_apellido.getText().toString());
        voluntario.setSurname2(eSegundo_apellido.getText().toString());

        if(bitmapImage != null) {
            voluntario.setPhoto(encode_photo(bitmapImage));
        }
        else{
            voluntario.setPhoto(null);
        }
    }

    public void sendActualizarVoluntario(){
        mAPIService.actualizarVoluntario(voluntario.getMail(), voluntario).enqueue(
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
