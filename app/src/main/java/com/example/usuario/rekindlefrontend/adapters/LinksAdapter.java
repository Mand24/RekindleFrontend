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
import com.example.usuario.rekindlefrontend.data.entity.link.Link;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.LinkViewHolder> {

    private List<Link> mLinks;
    private List<Link> mFilteredLinks;
    private Context mContext;
    private CustomItemClickListener listener;

    public LinksAdapter(Context mContext, List<Link> links,
            CustomItemClickListener listener) {
        this.mContext = mContext;
        this.mLinks = links;
        this.listener = listener;
    }

    public void setLinks(List<Link> lists) {
        this.mLinks = lists;
    }

    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_view_list_links, viewGroup, false);
        final LinkViewHolder mViewHolder = new LinkViewHolder(v);
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
    public void onBindViewHolder(LinkViewHolder linkViewHolder, int i) {
        Link link = mLinks.get(i);
        String type = link.getType();
        if (type.equals("Legal")) {
            linkViewHolder.linkType.setImageResource(R.drawable.ic_legal);
        } else if (type.equals("Health")) {
            linkViewHolder.linkType.setImageResource(R.drawable.ic_health);
        } else {
            linkViewHolder.linkType.setImageResource(R.drawable.education);
        }

        linkViewHolder.url.setText(link.getUrl());
        linkViewHolder.description.setText(link.getDescription());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mLinks.size();
    }

    public class LinkViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView url, description;
        ImageView linkType;

        LinkViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            url = (TextView) itemView.findViewById(R.id.url);
            description = (TextView) itemView.findViewById(R.id.description);
            linkType = (ImageView) itemView.findViewById(R.id.photo_cardView);
        }
    }
}
