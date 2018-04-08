package com.example.usuario.rekindlefrontend;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>{

    private List<Servicio> services;

    public class ServiceViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView serviceName, serviceAddress, expirationDate;
        ImageView serviceType;

        ServiceViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            serviceName = (TextView) itemView.findViewById(R.id.titulo_servicio);
            serviceAddress = (TextView) itemView.findViewById(R.id.direccion_servicio);
            expirationDate = (TextView) itemView.findViewById(R.id.fecha_limite_servicio);
            serviceType = (ImageView) itemView.findViewById(R.id.photo_cardView);
        }
    }

    public ServicesAdapter(List<Servicio> services){
        this.services = services;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_vista_lista_servicios, viewGroup, false);
        return new ServiceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder serviceViewHolder, int i) {
        Servicio servicio = services.get(i);

        serviceViewHolder.serviceName.setText(servicio.getNombre());
        serviceViewHolder.serviceAddress.setText(servicio.getDireccion());
        serviceViewHolder.expirationDate.setText(servicio.getFecha());
        serviceViewHolder.serviceType.setImageResource(servicio.getTipo());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount(){
        return services.size();
    }
}
