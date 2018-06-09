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

public class RefugeeAdapter extends RecyclerView.Adapter<RefugeeAdapter.RefugeeViewHolder> {

    private List<Refugee> mRefugees;
    private CustomItemClickListener listener;
    private Context mContext;

    public RefugeeAdapter(Context mContext, List<Refugee> refugees, CustomItemClickListener
            listener) {
        this.mContext = mContext;
        this.mRefugees = refugees;
        this.listener = listener;
    }

    public List<Refugee> getRefugees () { return mRefugees; }

    public void setRefugees(List<Refugee> refugees) {
        this.mRefugees = refugees;
    }

    @Override
    public RefugeeViewHolder onCreateViewHolder(ViewGroup viewGroup,
            int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_list_refugees,
                viewGroup,
                false);
        final RefugeeViewHolder mViewHolder = new RefugeeViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RefugeeViewHolder refugeeViewHolder,
            int i) {
        Refugee refugee = mRefugees.get(i);

        refugeeViewHolder.name.setText(refugee.getName());
        refugeeViewHolder.surname1.setText(refugee.getSurname1());
        refugeeViewHolder.surname2.setText(refugee.getSurname2());
        refugeeViewHolder.sex.setText(refugee.getSex());
        refugeeViewHolder.birthdate.setText(refugee.getBirthDate());
        if (refugee.getPhoto() != null) {
            refugeeViewHolder.photo.setImageBitmap(refugee.getDecodedPhoto());
        } else {
            refugeeViewHolder.photo.setImageResource(R.drawable.ic_usuario);
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

    public class RefugeeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView photo;
        TextView name, surname1, surname2, sex, birthdate;
//        ImageView serviceType;

        RefugeeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            photo = (ImageView) itemView.findViewById(R.id.photo_cardView);
            name = (TextView) itemView.findViewById(R.id.nombre_cv);
            surname1 = (TextView) itemView.findViewById(R.id.apellido1_cv);
            surname2 = (TextView) itemView.findViewById(R.id.apellido2_cv);
            sex = (TextView) itemView.findViewById(R.id.sexo_cv);
            birthdate = (TextView) itemView.findViewById(R.id.nacimiento_cv);
        }
    }
}
