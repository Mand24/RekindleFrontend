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
import com.example.usuario.rekindlefrontend.data.entity.user.Refugee;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import java.util.List;

public class RefugeeAdapter extends RecyclerView.Adapter<RefugeeAdapter.RefugiadoViewHolder> {

    private List<Refugee> mRefugees;
    private CustomItemClickListener listener;
    private Context mContext;

    public RefugeeAdapter(Context mContext, List<Refugee> refugees, CustomItemClickListener
            listener) {
        this.mContext = mContext;
        this.mRefugees = refugees;
        this.listener = listener;
    }

    public void setRefugees(List<Refugee> refugees) {
        this.mRefugees = refugees;
    }

    @Override
    public RefugiadoViewHolder onCreateViewHolder(ViewGroup viewGroup,
            int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_list_refugees,
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
    public void onBindViewHolder(@NonNull RefugeeAdapter.RefugiadoViewHolder refugiadoViewHolder,
            int i) {
        Refugee refugee = mRefugees.get(i);

        refugiadoViewHolder.nombre.setText(refugee.getName());
        refugiadoViewHolder.apellido1.setText(refugee.getSurname1());
        refugiadoViewHolder.apellido2.setText(refugee.getSurname2());
        refugiadoViewHolder.sexo.setText(refugee.getSex());
        refugiadoViewHolder.nacimiento.setText(refugee.getBirthDate());
        if (refugee.getPhoto() != null) {
            refugiadoViewHolder.photo.setImageBitmap(refugee.getDecodedPhoto());
        } else {
            refugiadoViewHolder.photo.setImageResource(R.drawable.ic_usuario);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mRefugees.size();
    }

    public class RefugiadoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView photo;
        TextView nombre, apellido1, apellido2, sexo, nacimiento;
//        ImageView serviceType;

        RefugiadoViewHolder(View itemView) {
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
}
