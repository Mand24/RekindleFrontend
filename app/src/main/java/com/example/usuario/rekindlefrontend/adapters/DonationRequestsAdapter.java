package com.example.usuario.rekindlefrontend.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.rekindlefrontend.R;
import com.example.usuario.rekindlefrontend.data.entity.misc.DonationRequest;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import java.util.List;

public class DonationRequestsAdapter extends RecyclerView.Adapter<DonationRequestsAdapter
        .DonationRequestViewHolder> {

    private List<DonationRequest> donationRequests;
    private CustomItemClickListener listener;
    private Context mContext;

    public DonationRequestsAdapter(Context mContext, List<DonationRequest> donationRequests,
            CustomItemClickListener
            listener) {
        this.mContext = mContext;
        this.donationRequests = donationRequests;
        this.listener = listener;
    }

    public void setDonationRequests(List<DonationRequest> donationRequests) {
        this.donationRequests = donationRequests;
    }

    @Override
    public DonationRequestsAdapter.DonationRequestViewHolder onCreateViewHolder(ViewGroup
            viewGroup,
            int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                        .fragment_view_list_donation_requests,
                viewGroup,
                false);
        final DonationRequestsAdapter.DonationRequestViewHolder
                mViewHolder = new DonationRequestsAdapter.DonationRequestViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DonationRequestViewHolder
            holder, int i) {
        DonationRequest donationRequest = donationRequests.get(i);

        holder.user_mail.setText(donationRequest.getUser().getMail());
        holder.donation_name.setText(donationRequest.getDonation().getName());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return donationRequests.size();
    }

    public class DonationRequestViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView user_mail, donation_name;

        DonationRequestViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            user_mail = (TextView) itemView.findViewById(R.id.requester_user_cv);
            donation_name = (TextView) itemView.findViewById(R.id.requested_donation_cv);
        }
    }
}
