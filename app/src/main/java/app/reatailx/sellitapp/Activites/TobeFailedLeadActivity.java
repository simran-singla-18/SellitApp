package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import app.reatailx.sellitapp.Adapters.TobeFailedLead_Adapter;
import app.reatailx.sellitapp.Models.Inprogressmodel;
import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class TobeFailedLeadActivity extends AppCompatActivity implements View.OnClickListener {

    public SessionManager session;
    public LinearLayoutManager linearLayoutManager;
    public ArrayList<Inprogressmodel> modleonefgr_array1 = new ArrayList<Inprogressmodel>();
    public Inprogressmodel modleonefgr;
    public TobeFailedLead_Adapter listAdapter;
    public RecyclerView recyclerView;
    public TobeFailedLeadActivity activity;
    public String vendorid, agentid, role;
    private KProgressHUD kProgressHUD;
    public RelativeLayout relativeLayout_back;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tobefailedlead_activity);
        activity = this;

        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            agentid = user.get(SessionManager.KEY_AGENTID);
            role = user.get(SessionManager.KEY_ROLE);
            System.out.println("User_Session_Data_inprogress:::::" + vendorid + "  " + agentid + "  " + role);
        } catch (Exception e) {
            e.printStackTrace();
        }

        swipeRefreshLayout = findViewById(R.id.swiperefreshmaintobefailed);
        recyclerView = findViewById(R.id.recycle_tobefailed);
        relativeLayout_back = findViewById(R.id.back_tbf);
        relativeLayout_back.setOnClickListener(this);

        linearLayoutManager = new LinearLayoutManager(activity);
        listAdapter = new TobeFailedLead_Adapter(modleonefgr_array1, activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            if (role.equalsIgnoreCase("ajent")) {
                progressDialog();
                getJSONAgent();
            } else {
                progressDialog();
                getJSON();
            }

        } else {
            Snackbar.make(this.findViewById(android.R.id.content), getResources().getString(R.string.connecttointenet), Snackbar.LENGTH_LONG).show();
        }

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(true);
                        if (role.equalsIgnoreCase("ajent")) {
                            modleonefgr_array1.clear();
                            getJSONAgent();
                        } else {
                            modleonefgr_array1.clear();
                            getJSON();
                        }
                    }
                }
        );
    }

    private void progressDialog() {
        kProgressHUD = new KProgressHUD(TobeFailedLeadActivity.this);
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

    private void getJSONAgent() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/ajantleads.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Result_verify_fragment_onemain:::::" + response);
                        if (response == null) {
                            swipeRefreshLayout.setRefreshing(false);
                            dismissDialog();
                        } else {
                            String s = response.trim();
                            System.out.println("msg_fonemain:::" + s);
                            dismissDialog();
                            swipeRefreshLayout.setRefreshing(false);
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                if (status.equalsIgnoreCase("1")) {
                                    JSONArray jarray = jsonObject.getJSONArray("leads_information");
                                    for (int hk = 0; hk < jarray.length(); hk++) {
                                        JSONObject d = jarray.getJSONObject(hk);
                                        modleonefgr = new Inprogressmodel();
                                        modleonefgr.setLead_id(d.get("lead_id").toString());
                                        modleonefgr.setVendorname(d.get("vendorname").toString());
                                        modleonefgr.setVarientname(d.get("varientname").toString());
                                        modleonefgr.setAjentname(d.get("ajentname").toString());
                                        modleonefgr.setModel_name(d.get("model_name").toString());
                                        modleonefgr.setImageurl(d.get("imageurl").toString());
                                        modleonefgr.setPrice(d.get("price").toString());
                                        modleonefgr.setLead_pick_status(d.get("lead_pick_status").toString());
                                        modleonefgr.setLead_pick_date(d.get("lead_pick_date").toString());
                                        modleonefgr.setLead_pick_month(d.get("lead_pick_month").toString());
                                        modleonefgr.setLead_pick_year(d.get("lead_pick_year").toString());
                                        modleonefgr.setLead_pick_time(d.get("lead_pick_time").toString());
                                        modleonefgr.setModify_date(d.get("modify_date").toString());
                                        modleonefgr.setCity(d.get("city").toString());
                                        modleonefgr.setLeadstatus(d.get("lead_status").toString());
                                        modleonefgr.setRole(role);
                                        modleonefgr_array1.add(modleonefgr);
                                        listAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    swipeRefreshLayout.setRefreshing(false);
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                        dismissDialog();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("vendorid", vendorid);
                params.put("ajentid", agentid);
                params.put("flag", "Complete");
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

    private void getJSON() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/allleads.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Result_verify_fragment_onemain:::::" + response);
                        if (response == null) {
                            dismissDialog();
                            swipeRefreshLayout.setRefreshing(false);
                        } else {
                            String s = response.trim();
                            System.out.println("msg_fonemain:::" + s);
                            dismissDialog();
                            swipeRefreshLayout.setRefreshing(false);
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                if (status.equalsIgnoreCase("1")) {
                                    JSONArray jarray = jsonObject.getJSONArray("leads_information");
                                    for (int hk = 0; hk < jarray.length(); hk++) {
                                        JSONObject d = jarray.getJSONObject(hk);
                                        modleonefgr = new Inprogressmodel();
                                        modleonefgr.setLead_id(d.get("lead_id").toString());
                                        modleonefgr.setVarientname(d.get("varientname").toString());
                                        modleonefgr.setVendorname(d.get("vendorname").toString());
                                        modleonefgr.setAjentname(d.get("ajentname").toString());
                                        modleonefgr.setModel_name(d.get("model_name").toString());
                                        modleonefgr.setImageurl(d.get("imageurl").toString());
                                        modleonefgr.setPrice(d.get("price").toString());
                                        modleonefgr.setLead_pick_status(d.get("lead_pick_status").toString());
                                        modleonefgr.setLead_pick_date(d.get("lead_pick_date").toString());
                                        modleonefgr.setLead_pick_month(d.get("lead_pick_month").toString());
                                        modleonefgr.setLead_pick_year(d.get("lead_pick_year").toString());
                                        modleonefgr.setLead_pick_time(d.get("lead_pick_time").toString());
                                        modleonefgr.setModify_date(d.get("modify_date").toString());
                                        modleonefgr.setCity(d.get("city").toString());
                                        modleonefgr.setLeadstatus(d.get("lead_status").toString());
                                        modleonefgr.setRole(role);
                                        modleonefgr_array1.add(modleonefgr);
                                        listAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    swipeRefreshLayout.setRefreshing(false);
                                    Toast.makeText(getApplicationContext(), "Something wents wrong ", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                        dismissDialog();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("vendorid", vendorid);
                params.put("flag", "Tobefailed");
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_tbf:
                finish();
                break;
            default:
                break;
        }
    }
}
