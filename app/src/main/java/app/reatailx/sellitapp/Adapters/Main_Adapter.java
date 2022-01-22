package app.reatailx.sellitapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.reatailx.sellitapp.Activites.ItemdescriptionActivity;
import app.reatailx.sellitapp.Models.Mainactivitymodel;
import app.reatailx.sellitapp.R;

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.MyViewHolder> {

    private List<Mainactivitymodel> electronicsItems;
    private Context context;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_phonename, tv_location_name, tv_pickup_time, tv_phone_price, tv_comiision_price;
        ImageView imageView_user;
        Button button_wallet, button_new;
        LinearLayout layout_covercview_mainclick_new;

        MyViewHolder(View view) {
            super(view);
            tv_phonename = (TextView) view.findViewById(R.id.phone_name);
            tv_location_name = (TextView) view.findViewById(R.id.location_name);
            tv_pickup_time = (TextView) view.findViewById(R.id.pickup_time);
            tv_phone_price = (TextView) view.findViewById(R.id.phone_price);
            tv_comiision_price = (TextView) view.findViewById(R.id.comiision_price);
            button_wallet = (Button) view.findViewById(R.id.walletbtn);
            imageView_user = (ImageView) view.findViewById(R.id.phone_img);
            layout_covercview_mainclick_new = (LinearLayout) view.findViewById(R.id.linaercoverclick);
        }
    }

    public Main_Adapter(List<Mainactivitymodel> electronicsItems, Context context) {
        this.electronicsItems = electronicsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mainpage_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Mainactivitymodel movie = electronicsItems.get(position);
        //holder.text_locationpick_main.setText(electronicsItems.get(position).getLocatoincode().toUpperCase());

        String date = movie.getLead_pick_date() + "" + movie.getLead_pick_year();
        int dateint = Integer.parseInt(date);
        String date2 = movie.getDatecustome();
        int dateintcc = Integer.parseInt(date2);
        System.out.println("getting_date " + dateint);
        System.out.println("getting_dateint " + dateintcc);

        if (dateintcc <= dateint) {
            System.out.println("getting_date_if ");
            holder.layout_covercview_mainclick_new.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_nonred));
        } else {
            System.out.println("getting_date_else ");
            holder.layout_covercview_mainclick_new.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient_red));
        }


        holder.tv_phonename.setText(movie.getModel_name() + " " + "(" + movie.getVarientname() + ")");
        holder.tv_phone_price.setText(movie.getPrice());
        holder.tv_pickup_time.setText(movie.getLead_pick_date() + " " + movie.getLead_pick_month() + " " + movie.getLead_pick_year() + " , " + movie.getLead_pick_time());
        holder.tv_location_name.setText(movie.getCity());
        holder.tv_comiision_price.setText("");
        Picasso.with(context)
                .load(movie.getImageurl())
                .resize(300, 300)
                .into(holder.imageView_user);

        holder.layout_covercview_mainclick_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(context, ItemdescriptionActivity.class);
                page.putExtra("lead_id", movie.getLead_id());
                page.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(page);
            }
        });
    }

    @Override
    public int getItemCount() {
        return electronicsItems.size();
    }

}
