package app.reatailx.sellitapp.Activites;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

import app.reatailx.sellitapp.Adapters.WalletMainPayment_Adapter;
import app.reatailx.sellitapp.Models.WalletMainPaymentModel;
import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class TransactionActivity extends Activity implements View.OnClickListener {
    private static final String TAG = TransactionActivity.class.getSimpleName();
    private String vendorid, vendoremail, vendormobile, razorpayaccountid;
    private SessionManager session;
    private KProgressHUD kProgressHUD;
    private RelativeLayout back_paymentnew;

    public LinearLayoutManager linearLayoutManager, linearLayoutManager2;
    public ArrayList<WalletMainPaymentModel> modleonefgr_array1 = new ArrayList<WalletMainPaymentModel>();
    public WalletMainPaymentModel modleonefgr;
    public WalletMainPayment_Adapter listAdapter;
    public RecyclerView recycle_paymenthistory;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("CutPasteId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.transaction_activity);
        session = new SessionManager(getApplicationContext());
        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            vendoremail = user.get(SessionManager.KEY_VENDOREMAIL);
            vendormobile = user.get(SessionManager.KEY_VENDORMOBILE);
            razorpayaccountid = user.get(SessionManager.KEY_RAZORPAYACCOUNTID);
            System.out.println("User_Session_Data_Vendorid_payment:::::" + vendorid + "  " + vendoremail + "  " + vendormobile + "  " + razorpayaccountid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        swipeRefreshLayout = findViewById(R.id.swiperefreshmaintrn);
        recycle_paymenthistory = findViewById(R.id.recycle_paymenthistorytransaction);
        back_paymentnew = findViewById(R.id.back_paymentnewtransaction);
        back_paymentnew.setOnClickListener(this);
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            progressDialog();
            getJSONAgentwalletprofileforAdapter();
        } else {
            dismissDialog();
            Snackbar.make(this.findViewById(android.R.id.content), getResources().getString(R.string.connecttointenet), Snackbar.LENGTH_LONG).show();
        }
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        modleonefgr_array1.clear();
                        getJSONAgentwalletprofileforAdapter();
                    }
                }
        );

        linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        listAdapter = new WalletMainPayment_Adapter(modleonefgr_array1, getApplicationContext());
        recycle_paymenthistory.setLayoutManager(linearLayoutManager2);
        recycle_paymenthistory.setItemAnimator(new DefaultItemAnimator());
        recycle_paymenthistory.setHasFixedSize(true);
        recycle_paymenthistory.setAdapter(listAdapter);
    }

    private void getJSONAgentwalletprofileforAdapter() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/vendortransaction.php",
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
                                //String message = jsonObject.getString("message");
                                if (status.equalsIgnoreCase("1")) {
                                    JSONArray jarray = jsonObject.getJSONArray("vendor_transaction");
                                    for (int hk = 0; hk < jarray.length(); hk++) {
                                        JSONObject d = jarray.getJSONObject(hk);
                                        modleonefgr = new WalletMainPaymentModel();
                                        modleonefgr.setAmount(d.get("amount").toString());
                                        modleonefgr.setPayment_type(d.get("payment_type").toString());
                                        modleonefgr.setTransactiontype(d.get("transactiontype").toString());
                                        modleonefgr.setCreated_at(d.get("created_at").toString());
                                        modleonefgr_array1.add(modleonefgr);
                                        listAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    swipeRefreshLayout.setRefreshing(false);
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
                        swipeRefreshLayout.setRefreshing(false);
                        dismissDialog();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("vendor_id", vendorid);
                params.put("razorpayaccountid", razorpayaccountid);
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
        kProgressHUD = new KProgressHUD(TransactionActivity.this);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {//back_paymentnew
        switch (v.getId()) {
            case R.id.back_paymentnewtransaction:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            default:
                break;
        }
    }
}

