package com.example.usuario.rekindlefrontend.view.usuarios.editarPerfil;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
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
import com.example.usuario.rekindlefrontend.data.entity.user.Volunteer;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.Consistency;
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

public class EditProfileVolunteer extends AbstractFormatChecker{

    private Volunteer mVolunteer;

    private EditText eName;
    private TextView eEmail;
    private EditText eSurname1;
    private EditText eSurname2;

    private Bitmap bitmapImage;
    private ImageView ePhoto;
    private AppCompatButton mPhotoButton;

    private APIService mAPIService;

    private static final int TAKE_PICTURE = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_editar_perfil_voluntario, container,
                false);


        setVistas(view);

        mVolunteer = (Volunteer) getActivity().getIntent().getParcelableExtra("Volunteer");

        System.out.println("editarv"+ mVolunteer.toString());

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
                Intent i = new Intent(getActivity().getApplicationContext(), ChangePassword.class);
                i.putExtra("Volunteer", mVolunteer);
                i.putExtra("tipo", mVolunteer.getUserType());
                System.out.println(mVolunteer.toString());
                startActivity(i);
            }

        });

        mPhotoButton = (AppCompatButton) view.findViewById(R.id.change_photo_volunteer);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryTakePhoto(view);
            }
        });

        return view;
    }

    public void setVistas(View view) {

        eName = view.findViewById(R.id.nombre_usuario_perfil);
        eEmail = view.findViewById(R.id.email_usuario_perfil);
        eSurname1 = view.findViewById(R.id.apellido1_usuario_perfil);
        eSurname2 = view.findViewById(R.id.apellido2_usuario_perfil);
        ePhoto = view.findViewById(R.id.volunteer_photo);

        mAPIService = APIUtils.getAPIService();

    }


    public void initializeData(View view){

        eName.setText(mVolunteer.getName());
        eEmail.setText(mVolunteer.getMail());
        eSurname1.setText(mVolunteer.getSurname1());
        eSurname2.setText(mVolunteer.getSurname2());

        if(mVolunteer.getPhoto() != null) {
            ePhoto.setImageBitmap(mVolunteer.getDecodedPhoto());
        }else{
            ePhoto.setImageResource(R.drawable.ic_usuario);
        }

    }

    public void checkCampos(View view) throws Exception {

        checkName(eName.getText().toString());;
        checkSurname1(eSurname1.getText().toString());
        checkSurname2(eSurname2.getText().toString());

    }

    public void obtenerCampos() {
        mVolunteer.setName(eName.getText().toString());
        mVolunteer.setSurname1(eSurname1.getText().toString());
        mVolunteer.setSurname2(eSurname2.getText().toString());

        if(bitmapImage != null) {
            mVolunteer.setPhoto(encode_photo(bitmapImage));
        }
        else{
            mVolunteer.setPhoto(null);
        }
        Consistency.saveUser(voluntario, getActivity());
    }

    public void sendActualizarVoluntario(){
        mAPIService.actualizarVoluntario(mVolunteer.getMail(), mVolunteer).enqueue(
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

    public void tryTakePhoto(View view) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                takePhoto();
            } else {
                requestPermission();
            }
        }else{
            takePhoto();
        }
    }

    private void takePhoto() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "Pic"
                + ".jpg");
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(i, TAKE_PICTURE);
    }

    private void requestPermission() {
        if(Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{android.Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission
                .WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getActivity().getContentResolver().notifyChange(selectedImage, null);
                    ContentResolver cr = getActivity().getContentResolver();
                    try {
                        bitmapImage = android.provider.MediaStore.Images.Media.getBitmap(cr,
                                selectedImage);
                        bitmapImage = Bitmap.createScaledBitmap(bitmapImage, 200, 200, false);
                        ePhoto.setImageBitmap(bitmapImage);
                        Toast.makeText(getActivity(), selectedImage.toString(),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }

    private String encode_photo(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        byte[] byte_arr = stream.toByteArray();
        return Base64.encodeToString(byte_arr, Base64.DEFAULT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v("====================","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
            mPhotoButton.performClick();
        }
    }


}
