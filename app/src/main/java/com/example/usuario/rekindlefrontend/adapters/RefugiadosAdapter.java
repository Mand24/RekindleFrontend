package com.example.usuario.rekindlefrontend.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.usuario.Refugiado;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import java.util.List;

public class RefugiadosAdapter extends RecyclerView.Adapter<RefugiadosAdapter.RefugiadoViewHolder> {

    private List<Refugiado> refugiados;
    private CustomItemClickListener listener;
    private Context mContext;

    public class RefugiadoViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        ImageView photo;
        TextView nombre, apellido1, apellido2, sexo, nacimiento;
//        ImageView serviceType;

        RefugiadoViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            photo = (ImageView) itemView.findViewById(R.id.photo_cardView);
            nombre = (TextView) itemView.findViewById(R.id.nombre_cv);
            apellido1 = (TextView) itemView.findViewById(R.id.apellido1_cv);
            apellido2 = (TextView) itemView.findViewById(R.id.apellido2_cv);
            sexo = (TextView) itemView.findViewById(R.id.sexo_cv);
            nacimiento = (TextView) itemView.findViewById(R.id.nacimiento_cv);
        }
    }

    public RefugiadosAdapter(Context mContext, List<Refugiado> refugiados, CustomItemClickListener
            listener){
        this.mContext = mContext;
        this.refugiados = refugiados;
        this.listener = listener;
    }

    public void setRefugiados(List<Refugiado> refugiados) {
        this.refugiados = refugiados;
    }


    @Override
    public RefugiadoViewHolder onCreateViewHolder(ViewGroup viewGroup,
            int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_vista_lista_refugiados,
                viewGroup,
                false);
        final RefugiadoViewHolder mViewHolder = new RefugiadoViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RefugiadosAdapter.RefugiadoViewHolder refugiadoViewHolder,
            int i) {
        Refugiado refugiado = refugiados.get(i);

        refugiadoViewHolder.nombre.setText(refugiado.getName());
        refugiadoViewHolder.apellido1.setText(refugiado.getSurname1());
        refugiadoViewHolder.apellido2.setText(refugiado.getSurname2());
        refugiadoViewHolder.sexo.setText(refugiado.getSex());
        refugiadoViewHolder.nacimiento.setText(refugiado.getBirthDate());
        if(refugiado.getPhoto() != null){
            refugiadoViewHolder.photo.setImageBitmap(refugiado.getDecodedPhoto());
        }
        else{
            refugiadoViewHolder.photo.setImageResource(R.drawable.foto_perfil);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return refugiados.size();
    }
}
