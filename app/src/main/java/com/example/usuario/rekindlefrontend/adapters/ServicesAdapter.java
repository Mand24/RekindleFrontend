package com.example.usuario.rekindlefrontend.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.service.Service;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

    private List<Service> mServices;
    private List<Service> mServiciosFiltered;
    private Context mContext;
    private CustomItemClickListener listener;

    public ServicesAdapter(Context mContext, List<Service> services,
            CustomItemClickListener listener) {
        this.mContext = mContext;
        this.mServices = services;
        this.listener = listener;
    }

    public void setServices(List<Service> services) {
        this.mServices = services;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_vista_lista_servicios, viewGroup, false);
        final ServiceViewHolder mViewHolder = new ServiceViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, mViewHolder.getAdapterPosition());
            }
        });
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onItemLongClick(view, mViewHolder.getAdapterPosition());
                return false;
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder serviceViewHolder, int i) {
        Service service = mServices.get(i);
        String tipo = service.getServiceType();
        if (tipo.equals("Lodge")) {
            service.setImage(R.drawable.lodging);
        } else if (tipo.equals("Donation")) {
            service.setImage(R.drawable.donation);
        } else if (tipo.equals("Education")) {
            service.setImage(R.drawable.education);
        } else {
            service.setImage(R.drawable.job);
        }

        serviceViewHolder.serviceName.setText(service.getName());
        serviceViewHolder.serviceAddress.setText(service.getAdress());
        serviceViewHolder.serviceType.setImageResource(service.getImage());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mServices.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView serviceName, serviceAddress, expirationDate;
        ImageView serviceType;

        ServiceViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            serviceName = (TextView) itemView.findViewById(R.id.titulo_servicio);
            serviceAddress = (TextView) itemView.findViewById(R.id.direccion_servicio);
            serviceType = (ImageView) itemView.findViewById(R.id.photo_cardView);
        }
    }
}
