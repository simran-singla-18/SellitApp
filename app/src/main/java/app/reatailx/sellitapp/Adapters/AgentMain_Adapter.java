package app.reatailx.sellitapp.Adapters;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import app.reatailx.sellitapp.Activites.AgentUpdateActivity;
import app.reatailx.sellitapp.Activites.VolleySingleton;
import app.reatailx.sellitapp.Models.AllAjentsmodel;
import app.reatailx.sellitapp.R;

public class AgentMain_Adapter extends RecyclerView.Adapter<AgentMain_Adapter.MyViewHolder> {

    private List<AllAjentsmodel> electronicsItems;
    private Context context;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_agentname, tv_agent_mobile, tv_agent_mail;
        private ImageView imageView_user;
        private Button editbutton, deletbutton;
        private LinearLayout layout_covercview_mainclick_new;
        private PopupWindow pwindo;
        private Button btnOkSubmitPopup, btnCancelSubmitPopup;

        MyViewHolder(View view) {
            super(view);
            editbutton = (Button) view.findViewById(R.id.editdelet);
            deletbutton = (Button) view.findViewById(R.id.agentdelet);
            tv_agentname = (TextView) view.findViewById(R.id.agent_name);
            tv_agent_mobile = (TextView) view.findViewById(R.id.agent_mobile);
            tv_agent_mail = (TextView) view.findViewById(R.id.agent_mail);
            imageView_user = (ImageView) view.findViewById(R.id.agent_img);
            layout_covercview_mainclick_new = (LinearLayout) view.findViewById(R.id.linaercoverclickagent);
        }
    }

    public AgentMain_Adapter(List<AllAjentsmodel> electronicsItems, Context context) {
        this.electronicsItems = electronicsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.agentmain_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final AllAjentsmodel movie = electronicsItems.get(position);
        holder.tv_agentname.setText(movie.getName());
        holder.tv_agent_mobile.setText(movie.getPhone());
        holder.tv_agent_mail.setText(movie.getEmail());
        Picasso.with(context)
                .load(movie.getAjentimage())
                .resize(300, 300)
                .into(holder.imageView_user);
        holder.editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AgentUpdateActivity.class);
                intent.putExtra("ajentid", movie.getId());
                intent.putExtra("ajentname", movie.getName());
                intent.putExtra("ajentphone", movie.getPhone());
                intent.putExtra("ajentemail", movie.getEmail());
                intent.putExtra("ajentaddress", movie.getAddress());
                context.startActivity(intent);
            }
        });

        holder.deletbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*start*/
                try {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View layout = Objects.requireNonNull(inflater).inflate(R.layout.windpopupagentdelet,
                            v.findViewById(R.id.rellayoutdeletagent));
                    holder.pwindo = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
                    holder.pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
                    holder.btnCancelSubmitPopup = (Button) layout.findViewById(R.id.btn_cancelagent);
                    holder.btnOkSubmitPopup = (Button) layout.findViewById(R.id.btn_okagent);
                    holder.btnOkSubmitPopup.setOnClickListener(view -> {
                        holder.pwindo.dismiss();
                        ProgressDialog dialog = new ProgressDialog(context);
                        dialog.setMessage("Please wait.....");
                        dialog.show();
                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/deleteajent.php", new Response.Listener<String>() {
                            public void onResponse(String response) {
                                System.out.println("getting_datetimeresponse:::::::" + response);
                                if (response == null) {
                                    dialog.hide();
                                    return;
                                } else {
                                    dialog.hide();
                                    String s = response;
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        String status = jsonObject.getString("status");
                                        if (status.equals("1")) {
                                            holder.layout_covercview_mainclick_new.setVisibility(View.GONE);
                                            Toast.makeText(context, "Success !!", Toast.LENGTH_LONG).show();// 5013301000
                                        } else if (status.equals("0")) {
                                            Toast.makeText(context, "Something went wrong !!", Toast.LENGTH_LONG).show();// 5013301000
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError error) {
                                dialog.hide();
                                Toast.makeText(context, "Server not responding !", Toast.LENGTH_LONG).show();
                            }
                        }) {
                            /* access modifiers changed from: protected */
                            public Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("vendorid", movie.getVendorid());
                                params.put("ajentid", movie.getId());
                                return params;
                            }

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                return params;
                            }
                        };
                        stringRequest1.setRetryPolicy(new DefaultRetryPolicy(60000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest1);

                    });
                    holder.btnCancelSubmitPopup.setOnClickListener(view -> {
                        holder.pwindo.dismiss();
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*end*/

            }
        });
    }


    @Override
    public int getItemCount() {
        return electronicsItems.size();
    }

}
