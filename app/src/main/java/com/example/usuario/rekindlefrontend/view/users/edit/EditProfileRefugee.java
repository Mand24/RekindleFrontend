package com.example.usuario.rekindlefrontend.view.users.edit;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.data.remote.APIService;
import com.example.usuario.rekindlefrontend.data.remote.APIUtils;
import com.example.usuario.rekindlefrontend.utils.AbstractFormatChecker;
import com.example.usuario.rekindlefrontend.utils.Consistency;
import com.example.usuario.rekindlefrontend.utils.SetDate;
import com.example.usuario.rekindlefrontend.view.users.show.ShowProfile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ORION on 27/04/2018.
 */

public class EditProfileRefugee extends AbstractFormatChecker {

    private static final int TAKE_PICTURE = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    ArrayAdapter<CharSequence> adapter1, adapter2, adapter3;
    private Refugee mRefugee;
    private EditText eName;
    private TextView eEmail;
    private EditText eSurname1;
    private EditText eSurname2;
    private EditText ePhoneNumber;
    private EditText eBirthdate;
    private Spinner sSex;
    private EditText eCountry;
    private EditText eTown;
    private EditText eEthnic;
    private Spinner sBloodType;
    private Spinner sEyes;
    private EditText eBiography;
    private AppCompatButton mPhotoButton;
    private Bitmap bitmapImage;
    private ImageView ePhoto;
    private APIService mAPIService;
    private Uri imageUri;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_edit_refugee, container,
                false);

        setViews(view);

        mRefugee = (Refugee) getActivity().getIntent().getParcelableExtra("Refugee");

        System.out.println(mRefugee.toString());

        initializeData(view);


        AppCompatButton b = (AppCompatButton) view.findViewById(R.id.guardar_editar_perfil);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    checkFields(view);
                    getFields();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                sendUpdateRefugee();

            }

        });

        AppCompatButton b2 = (AppCompatButton) view.findViewById(R.id.cambiar_password);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ChangePassword.class);
                i.putExtra("Refugee", mRefugee);
                i.putExtra("tipo", mRefugee.getUserType());
                startActivity(i);
            }

        });

        SetDate setDate = new SetDate(eBirthdate, container.getContext());

        mPhotoButton = (AppCompatButton) view.findViewById(R.id.change_photo_refugee);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryTakePhoto(view);
            }
        });

        return view;
    }

    public void setViews(View view) {


        eName = view.findViewById(R.id.nombre_usuario_perfil);
        eEmail = view.findViewById(R.id.email_usuario_perfil);
        eSurname1 = view.findViewById(R.id.apellido1_usuario_perfil);
        eSurname2 = view.findViewById(R.id.apellido2_usuario_perfil);
        ePhoneNumber = view.findViewById(R.id.telefono_usuario_perfil);
        eBirthdate = view.findViewById(R.id.naciminento_usuario_perfil);
        sSex = view.findViewById(R.id.sexo_usuario_perfil);
        adapter1 = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_sexo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sSex.setAdapter(adapter1);
        eCountry = view.findViewById(R.id.pais_usuario_perfil);
        eTown = view.findViewById(R.id.pueblo_usuario_perfil);
        eEthnic = view.findViewById(R.id.etnia_usuario_perfil);

        sBloodType = view.findViewById(R.id.sangre_usuario_perfil);

        adapter2 = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_grupo_sanguineo, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sBloodType.setAdapter(adapter2);

        sEyes = view.findViewById(R.id.ojos_usuario_perfil);

        adapter3 = ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.lista_color_ojos, R.layout.spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        sEyes.setAdapter(adapter3);

        eBiography = view.findViewById(R.id.biografia_usuario_perfil);

        ePhoto = view.findViewById(R.id.refugee_photo);

        mAPIService = APIUtils.getAPIService();

    }


    public void initializeData(View view) {

        eName.setText(mRefugee.getName());
        eEmail.setText(mRefugee.getMail());
        eSurname1.setText(mRefugee.getSurname1());
        eSurname2.setText(mRefugee.getSurname2());
        ePhoneNumber.setText(mRefugee.getPhoneNumber());
        eBirthdate.setText(mRefugee.getBirthDate());

        int selectionPosition = adapter1.getPosition(mRefugee.getSex());
        sSex.setSelection(selectionPosition);

        eCountry.setText(mRefugee.getCountry());
        eTown.setText(mRefugee.getTown());
        eEthnic.setText(mRefugee.getEthnic());

        selectionPosition = adapter2.getPosition(mRefugee.getBloodType());
        sBloodType.setSelection(selectionPosition);

        selectionPosition = adapter3.getPosition(mRefugee.getEyeColor());
        sEyes.setSelection(selectionPosition);

        eBiography.setText(mRefugee.getBiography());

        if (mRefugee.getPhoto() != null) {
            ePhoto.setImageBitmap(mRefugee.getDecodedPhoto());
        } else {
            ePhoto.setImageResource(R.drawable.ic_usuario);
        }

    }

    public void checkFields(View view) throws Exception {

        checkName(eName.getText().toString());
        checkSurname1(eSurname1.getText().toString());
        checkSurname2(eSurname2.getText().toString());
        checkPhoneNumber(ePhoneNumber.getText().toString());
        checkCountry(eCountry.getText().toString());
        checkTown(eTown.getText().toString());
        checkEthnic(eEthnic.getText().toString());
        checkBiography(eBiography.getText().toString());
    }

    public void getFields() {
        mRefugee.setName(eName.getText().toString());
        mRefugee.setSurname1(eSurname1.getText().toString());
        mRefugee.setSurname2(eSurname2.getText().toString());
        mRefugee.setPhoneNumber(ePhoneNumber.getText().toString());
        mRefugee.setCountry(eCountry.getText().toString());
        mRefugee.setTown(eTown.getText().toString());
        mRefugee.setEthnic(eEthnic.getText().toString());
        mRefugee.setBirthDate(eBirthdate.getText().toString());
        mRefugee.setSex(sSex.getSelectedItem().toString());
        mRefugee.setBloodType(sBloodType.getSelectedItem().toString());
        mRefugee.setEyeColor(sEyes.getSelectedItem().toString());
        mRefugee.setBiography(eBiography.getText().toString());

        if (bitmapImage != null) {
            mRefugee.setPhoto(encode_photo(bitmapImage));
        } else {
            mRefugee.setPhoto(null);
        }
        Consistency.saveUser(mRefugee, getActivity());
    }

    public void sendUpdateRefugee() {
        mAPIService.actualizarRefugiado(mRefugee.getMail(), mRefugee).enqueue(
                new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            manageResult(true);
                        } else {
                            manageResult(false);
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

    public void manageResult(boolean result) {

        if (result) {

            Toast.makeText(getActivity().getApplicationContext(), "Actualizado correctamente",
                    Toast
                            .LENGTH_SHORT).show();
            Intent i = new Intent(getActivity().getApplicationContext(), ShowProfile.class);
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
        } else {
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
        if (Build.VERSION.SDK_INT >= 23) {
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
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("====================",
                    "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
            mPhotoButton.performClick();
        }
    }

}
