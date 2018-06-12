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
import com.example.usuario.rekindlefrontend.data.entity.reports.Report;
import com.example.usuario.rekindlefrontend.interfaces.CustomItemClickListener;

import java.util.List;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ReportViewHolder> {

    private List<Report> reports;
    private CustomItemClickListener listener;
    private Context mContext;


    public ReportsAdapter(Context mContext, List<Report> reports, CustomItemClickListener
            listener) {
        this.mContext = mContext;
        this.reports = reports;
        this.listener = listener;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    @Override
    public ReportsAdapter.ReportViewHolder onCreateViewHolder(ViewGroup viewGroup,
            int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                        .fragment_view_list_reports,
                viewGroup,
                false);
        final ReportsAdapter.ReportViewHolder
                mViewHolder = new ReportsAdapter.ReportViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int i) {
        Report report = reports.get(i);

        holder.informer_mail.setText(report.getInformerUserMail());
        holder.reported_mail.setText(report.getReportedUserMail());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView informer_mail, reported_mail;

        ReportViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            informer_mail = (TextView) itemView.findViewById(R.id.informer_mail_cv);
            reported_mail = (TextView) itemView.findViewById(R.id.reported_mail_cv);
        }
    }
}
