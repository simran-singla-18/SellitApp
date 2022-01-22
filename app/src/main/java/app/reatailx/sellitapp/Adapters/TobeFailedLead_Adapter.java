package app.reatailx.sellitapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.reatailx.sellitapp.Activites.InprogressItemdescriptionActivity;
import app.reatailx.sellitapp.Models.Inprogressmodel;
import app.reatailx.sellitapp.R;

public class TobeFailedLead_Adapter extends RecyclerView.Adapter<TobeFailedLead_Adapter.MyViewHolder> {

    private List<Inprogressmodel> electronicsItems;
    private Context context;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productname_ip, vendorname_ip, agent_name_ip, name_ip, datetime_ip, location_ip, phone_price_des, commision_price_ip;
        ImageView image_ip;
        LinearLayout layout_covercview_mainclick_new;

        MyViewHolder(View view) {
            super(view);
            productname_ip = (TextView) view.findViewById(R.id.productname_tbf);
            vendorname_ip = (TextView) view.findViewById(R.id.vendorname_tbf);
            agent_name_ip = (TextView) view.findViewById(R.id.agent_name_tbf);
            datetime_ip = (TextView) view.findViewById(R.id.datetime_tbf);
            location_ip = (TextView) view.findViewById(R.id.location_tbf);
            phone_price_des = (TextView) view.findViewById(R.id.phone_price_tbf);
            commision_price_ip = (TextView) view.findViewById(R.id.commision_price_tbf);
            image_ip = (ImageView) view.findViewById(R.id.image_tbf);
            layout_covercview_mainclick_new = (LinearLayout) view.findViewById(R.id.linaercoverclicktbf);
        }
    }

    public TobeFailedLead_Adapter(List<Inprogressmodel> electronicsItems, Context context) {
        this.electronicsItems = electronicsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tobefailedlead_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Inprogressmodel movie = electronicsItems.get(position);
        holder.productname_ip.setText(movie.getModel_name() + " " + "(" + movie.getVarientname() + ")");
        //holder.vendorname_ip.setText(movie.getVendorname());
        if (movie.getRole().equals("vendor")) {
            holder.vendorname_ip.setVisibility(View.GONE);
        } else {
            holder.vendorname_ip.setText(movie.getVendorname());
        }

        if (movie.getAjentname().equals("null")) {
            holder.agent_name_ip.setVisibility(View.GONE);
        } else {
            holder.agent_name_ip.setText(movie.getAjentname());
        }
        holder.datetime_ip.setText(movie.getLead_pick_date() + " " + movie.getLead_pick_month() + movie.getLead_pick_year() + " , " + movie.getLead_pick_time());
        holder.location_ip.setText(movie.getCity());
        holder.phone_price_des.setText(movie.getPrice());
        holder.commision_price_ip.setText("");
        Picasso.with(context)
                .load(movie.getImageurl())
                .resize(300, 300)
                .into(holder.image_ip);

        holder.layout_covercview_mainclick_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(context, InprogressItemdescriptionActivity.class);
                page.putExtra("lead_id", movie.getLead_id());
                page.putExtra("ajentassign", movie.getAjentname());
                page.putExtra("flag", "tobefailed");
                page.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(page);
               /* Intent page = new Intent(context, CompleteleadItemdescriptionActivity.class);
                page.putExtra("lead_id", movie.getLead_id());
                page.putExtra("ajentassign", movie.getAjentname());
                page.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(page);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return electronicsItems.size();
    }

}
