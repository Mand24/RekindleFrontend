package com.example.usuario.rekindlefrontend.adapters;

import static com.example.usuario.rekindlefrontend.utils.Consistency.getUser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.chat.Chat;
import com.example.usuario.rekindlefrontend.data.entity.user.User;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatViewHolder> {

    private List<Chat> chats;
    private CustomItemClickListener listener;
    private Context mContext;

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name, surname1;
//      ImageView userImage;
        ChatViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            name = (TextView) itemView.findViewById(R.id.name_cv);
            surname1 = (TextView) itemView.findViewById(R.id.surname1_cv);
        }
    }

    public ChatsAdapter(List<Chat> chats, CustomItemClickListener listener, Context context) {
        this.chats = chats;
        this.listener = listener;
        mContext = context;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_view_list_chats,
                viewGroup,
                false);
        final ChatViewHolder mViewHolder = new ChatViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsAdapter.ChatViewHolder chatViewHolder, int i) {
        Chat chat = chats.get(i);
        User user, currentUser;
        currentUser = getUser(mContext);
        if (!chat.getUser1().getMail().equals(currentUser.getMail())){
            user = chat.getUser1();
        }else {
            user = chat.getUser2();
        }

        chatViewHolder.name.setText(user.getName());
        chatViewHolder.surname1.setText(user.getSurname1());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return this.chats.size();
    }


}
