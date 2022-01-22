package app.reatailx.sellitapp.Activites;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

import app.reatailx.sellitapp.Adapters.Todayslead_Adapter;
import app.reatailx.sellitapp.Models.Inprogressmodel;
import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class TodaysTommarrowLeadActivity extends AppCompatActivity implements View.OnClickListener {

    public SessionManager session;
    public LinearLayoutManager linearLayoutManager;
    public ArrayList<Inprogressmodel> modleonefgr_array1 = new ArrayList<Inprogressmodel>();
    public Inprogressmodel modleonefgr;
    public Todayslead_Adapter listAdapter;
    public RecyclerView recyclerView;
    public TodaysTommarrowLeadActivity activity;
    public String vendorid, agentid = "", role;
    private KProgressHUD kProgressHUD;
    public RelativeLayout relativeLayout_back;
    public String date, header;
    public TextView textView_tt;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todayslead_activity);
        activity = this;

        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            agentid = user.get(SessionManager.KEY_AGENTID);
            role = user.get(SessionManager.KEY_ROLE);
            System.out.println("User_Session_Data_Vendorid:::::" + vendorid + "   " + agentid + "   " + role);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Bundle intent = getIntent().getExtras();
        if (intent != null) {
            date = intent.get("date").toString();
            header = intent.get("header").toString();
            System.out.println("getting_date " + date + "  " + header);

        }
        swipeRefreshLayout = findViewById(R.id.swiperefreshmaintt);
        textView_tt = findViewById(R.id.tv_tt);
        textView_tt.setText(header);
        recyclerView = findViewById(R.id.recycle_todaysleadlist);
        relativeLayout_back = findViewById(R.id.back_todayslead);
        relativeLayout_back.setOnClickListener(this);

        linearLayoutManager = new LinearLayoutManager(activity);
        listAdapter = new Todayslead_Adapter(modleonefgr_array1, activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);

        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (role.equalsIgnoreCase("vendor")) {
            if (networkInfo != null && networkInfo.isConnected()) {
                progressDialog();
                getJSON();
            } else {
                Snackbar.make(this.findViewById(android.R.id.content), getResources().getString(R.string.connecttointenet), Snackbar.LENGTH_LONG).show();
            }
        } else {
            if (networkInfo != null && networkInfo.isConnected()) {
                progressDialog();
                getJSONagentdata();
            } else {
                Snackbar.make(this.findViewById(android.R.id.content), getResources().getString(R.string.connecttointenet), Snackbar.LENGTH_LONG).show();
            }
        }

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(true);
                        if (role.equalsIgnoreCase("vendor")) {
                            modleonefgr_array1.clear();
                            getJSON();
                        } else {
                            modleonefgr_array1.clear();
                            getJSONagentdata();
                        }
                    }
                }
        );

    }

    private void getJSONagentdata() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/datewiseleads.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Result_verify_fragment_onemaindataaaaa:::::" + response);
                        if (response == null) {
                            dismissDialog();
                            swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                        } else {
                            String s = response.trim();
                            System.out.println("msg_fonemain:::" + s);
                            dismissDialog();
                            swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
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
                                        modleonefgr.setVarientname(d.get("varientname").toString());
                                        modleonefgr.setVendorname(d.get("vendorname").toString());
                                        modleonefgr.setAjentname(d.get("ajentname").toString());
                                        modleonefgr.setModel_name(d.get("model_name").toString());
                                        modleonefgr.setImageurl(d.get("imageurl").toString());
                                        modleonefgr.setPrice(d.get("price").toString());
                                        modleonefgr.setLead_pick_status(d.get("lead_pick_status").toString());
                                        modleonefgr.setLead_pick_date(d.get("lead_pick_date").toString());
                                        modleonefgr.setLead_pick_time(d.get("lead_pick_time").toString());
                                        modleonefgr.setModify_date(d.get("modify_date").toString());
                                        modleonefgr.setCity(d.get("city").toString());
                                        modleonefgr.setLeadstatus(d.get("lead_status").toString());
                                        modleonefgr_array1.add(modleonefgr);
                                        listAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
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
                        swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                        dismissDialog();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ajentid", agentid);
                params.put("flag", "inprogress");
                params.put("date", date);
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

    private void progressDialog() {
        kProgressHUD = new KProgressHUD(TodaysTommarrowLeadActivity.this);
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

    private void getJSON() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/datewiseleads.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Result_verify_fragment_onemain:::::" + response);
                        if (response == null) {
                            dismissDialog();
                            swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                        } else {
                            String s = response.trim();
                            System.out.println("msg_fonemain:::" + s);
                            dismissDialog();
                            swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                // String message = jsonObject.getString("message");
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
                                        modleonefgr.setLead_pick_time(d.get("lead_pick_time").toString());
                                        modleonefgr.setModify_date(d.get("modify_date").toString());
                                        modleonefgr.setCity(d.get("city").toString());
                                        modleonefgr.setLeadstatus(d.get("lead_status").toString());
                                        modleonefgr_array1.add(modleonefgr);
                                        listAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                                    Toast.makeText(getApplicationContext(), "No Record Found !!", Toast.LENGTH_LONG).show();
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
                        swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                        dismissDialog();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("vendorid", vendorid);
                params.put("flag", "Inprogress");
                params.put("date", date);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_todayslead:
                finish();
                break;
            default:
                break;
        }
    }
}
