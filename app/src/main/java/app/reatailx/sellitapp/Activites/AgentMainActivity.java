package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.Adapters.AgentMain_Adapter;
import app.reatailx.sellitapp.Models.AllAjentsmodel;
import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class AgentMainActivity extends AppCompatActivity implements View.OnClickListener {
    public RelativeLayout back_des;
    public SessionManager session;
    public ProgressBar progressBar;
    public String vendorid, lead_id;
    private KProgressHUD kProgressHUD;
    public RecyclerView recyclerView;
    public AgentMain_Adapter listAdapter;
    public LinearLayoutManager linearLayoutManager;
    public AgentMainActivity activity;
    public ImageView imageView_addagent;
    public AllAjentsmodel allAjentsmodel;
    public ArrayList<AllAjentsmodel> modleonefgr_array1 = new ArrayList<AllAjentsmodel>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agent_main_activity);
        activity = this;
        session = new SessionManager(getApplicationContext());

        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            System.out.println("User_Session_Data_Vendorid:::::" + vendorid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Bundle intent = getIntent().getExtras();
        if (intent != null) {
            lead_id = intent.get("lead_id").toString();
            System.out.println("getting_lead_id " + lead_id);
        }
        imageView_addagent = findViewById(R.id.img_roungplusaddagent);
        recyclerView = findViewById(R.id.recycle_agentmain);
        back_des = findViewById(R.id.back_agentmain);
        back_des.setOnClickListener(this);
        imageView_addagent.setOnClickListener(this);

        linearLayoutManager = new LinearLayoutManager(activity);
        listAdapter = new AgentMain_Adapter(modleonefgr_array1, activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

        if (isNetworkAvailable()) {
            progressDialog();
            clientlogin();
            return;
        } else {
            dismissDialog();
            Toast.makeText(AgentMainActivity.this, getResources().getString(R.string.connecttointenet), Toast.LENGTH_LONG).show();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {//img_roungplusaddagent
            case R.id.back_agentmain:
                finish();
                break;
            case R.id.img_roungplusaddagent:
                Intent intent = new Intent(getApplicationContext(), AgentAddActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void progressDialog() {
        kProgressHUD = new KProgressHUD(AgentMainActivity.this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait...")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    private void dismissDialog() {
        if (kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    private void clientlogin() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/allajents.php",
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        if (response == null) {
                            dismissDialog();
                        } else {
                            String s = response.trim();
                            System.out.println("msglogindata:::" + s);
                            dismissDialog();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                //String message = jsonObject.getString("message");
                                if (status.equalsIgnoreCase("1")) {
                                    JSONArray jarray = jsonObject.getJSONArray("ajent_information");
                                    for (int hk = 0; hk < jarray.length(); hk++) {
                                        JSONObject d = jarray.getJSONObject(hk);
                                        allAjentsmodel = new AllAjentsmodel();
                                        allAjentsmodel.setVendorid(vendorid);
                                        allAjentsmodel.setId(d.get("id").toString());
                                        allAjentsmodel.setName(d.get("ajentname").toString());
                                        allAjentsmodel.setEmail(d.get("email").toString());
                                        allAjentsmodel.setPhone(d.get("phone").toString());
                                        allAjentsmodel.setStatus(d.get("status").toString());
                                        allAjentsmodel.setAjentimage(d.get("ajentimage").toString());
                                        allAjentsmodel.setAddress(d.get("address").toString());
                                        modleonefgr_array1.add(allAjentsmodel);
                                        listAdapter.notifyDataSetChanged();
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "No Data Found !!", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dismissDialog();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("vendorid", vendorid);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}

