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
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> mUsers;
    private CustomItemClickListener listener;
    private Context mContext;

    public UsersAdapter(Context mContext, List<User> users, CustomItemClickListener
            listener) {
        this.mContext = mContext;
        this.mUsers = users;
        this.listener = listener;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        this.mUsers = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup,
            int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_view_list_users,
                viewGroup,
                false);
        final UserViewHolder mViewHolder = new UserViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder,
            int i) {
        User user = mUsers.get(i);

        userViewHolder.name.setText(user.getName());
        userViewHolder.surname1.setText(user.getSurname1());
        userViewHolder.surname2.setText(user.getSurname2());
        userViewHolder.userType.setText(user.getUserType());
        if (user.getPhoto() != null) {
            userViewHolder.photo.setImageBitmap(user.getDecodedPhoto());
        } else {
            userViewHolder.photo.setImageResource(R.drawable.ic_usuario);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView photo;
        TextView name, surname1, surname2, userType;

        UserViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            photo = (ImageView) itemView.findViewById(R.id.photo_cardView);
            name = (TextView) itemView.findViewById(R.id.name_cv);
            surname1 = (TextView) itemView.findViewById(R.id.surname1_cv);
            surname2 = (TextView) itemView.findViewById(R.id.surname2_cv);
            userType = (TextView) itemView.findViewById(R.id.userType_cv);
        }
    }

}
