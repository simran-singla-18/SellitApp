package app.reatailx.sellitapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.reatailx.sellitapp.Activites.AgentUpdateActivity;
import app.reatailx.sellitapp.Activites.WalletMainPaymentDescriptionActivity;
import app.reatailx.sellitapp.Models.AllAjentsmodel;
import app.reatailx.sellitapp.Models.WalletMainPaymentModel;
import app.reatailx.sellitapp.R;

public class WalletMainPayment_Adapter extends RecyclerView.Adapter<WalletMainPayment_Adapter.MyViewHolder> {

    private List<WalletMainPaymentModel> electronicsItems;
    private Context context;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_paymenttype, tv_dateandtime, tv_amountwalletcommission, tv_creaditfrom;
        private LinearLayout layout_covercview_mainclick_new;

        MyViewHolder(View view) {
            super(view);
            tv_paymenttype = (TextView) view.findViewById(R.id.tv_paymenttype);
            tv_dateandtime = (TextView) view.findViewById(R.id.tv_dateandtime);
            tv_amountwalletcommission = (TextView) view.findViewById(R.id.tv_amountwalletcommission);
            tv_creaditfrom = (TextView) view.findViewById(R.id.tv_creaditfrom);
            layout_covercview_mainclick_new = (LinearLayout) view.findViewById(R.id.linaercoverclickagentwalltcommsi);
        }
    }

    public WalletMainPayment_Adapter(List<WalletMainPaymentModel> electronicsItems, Context context) {
        this.electronicsItems = electronicsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.walletmainpayment_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final WalletMainPaymentModel movie = electronicsItems.get(position);
        holder.tv_paymenttype.setText(movie.getPayment_type());
        holder.tv_dateandtime.setText(movie.getCreated_at());
        holder.tv_amountwalletcommission.setText("Rs." + movie.getAmount() + "/-");
        holder.tv_creaditfrom.setText(movie.getTransactiontype() + " from");
        /*Picasso.with(context)
                .load(movie.getAjentimage())
                .resize(300, 300)
                .into(holder.imageView_user);*/
        holder.layout_covercview_mainclick_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(context, WalletMainPaymentDescriptionActivity.class);
                intent.putExtra("amount", movie.getAmount());
                intent.putExtra("payment_type", movie.getPayment_type());
                intent.putExtra("transactiontype", movie.getTransactiontype());
                intent.putExtra("created_at", movie.getCreated_at());
                context.startActivity(intent);*/
            }
        });
    }


    @Override
    public int getItemCount() {
        return electronicsItems.size();
    }

}
