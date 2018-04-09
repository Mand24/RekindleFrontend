package com.example.usuario.rekindlefrontend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

public class MostrarServicio extends AppCompatActivity {

    TextView titulo, descripcion, direccion, fecha, numero, valoracion;
    MapView googleMaps;
    AppCompatButton chat, opiniones, inscribirse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_servicio);

        titulo = (TextView) findViewById(R.id.titulo_servicio);
        descripcion = (TextView) findViewById(R.id.descripcion_servicio);
        direccion = (TextView) findViewById(R.id.direccion_servicio);
        fecha = (TextView) findViewById(R.id.fecha_limite_servicio);
        googleMaps = (MapView) findViewById(R.id.google_mapView);
        numero = (TextView) findViewById(R.id.numero_contacto_servicio);
        chat = (AppCompatButton) findViewById(R.id.chat);
        valoracion = (TextView) findViewById(R.id.valoracion_servicio);
        opiniones = (AppCompatButton) findViewById(R.id.opiniones);
        inscribirse = (AppCompatButton) findViewById(R.id.inscribirse);

        Servicio servicio = (Servicio) getIntent().getSerializableExtra("Servicio");

        titulo.setText(servicio.getNombre());
        descripcion.setText(servicio.getDescripcion());
        direccion.setText(servicio.getDireccion());
        fecha.setText(servicio.getFecha());
        numero.setText(servicio.getNumero());
        valoracion.setText("Valoracion: " + servicio.getValoracion());
    }
}
