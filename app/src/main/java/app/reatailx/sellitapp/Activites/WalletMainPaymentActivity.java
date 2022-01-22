package app.reatailx.sellitapp.Activites;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.Adapters.Inprogress_Adapter;
import app.reatailx.sellitapp.Adapters.Main_Adapter;
import app.reatailx.sellitapp.Adapters.WalletMainPayment_Adapter;
import app.reatailx.sellitapp.Models.Inprogressmodel;
import app.reatailx.sellitapp.Models.Mainactivitymodel;
import app.reatailx.sellitapp.Models.WalletMainPaymentModel;
import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class WalletMainPaymentActivity extends Activity implements View.OnClickListener {
    private static final String TAG = WalletMainPaymentActivity.class.getSimpleName();
    private String vendorid, lead_id, vendoremail, vendormobile, amount, id, razorpayaccountid;
    private SessionManager session;
    private RelativeLayout relativeLayout;
    private String orderId, paymentId, signature;
    private KProgressHUD kProgressHUD;
    private String description;
    private LinearLayout lnr_mainwallet, lnr_commissionwallet;
    private RelativeLayout back_paymentnew;
    private ImageView imagevnd;
    private TextView tv_namevnd, tv_parteridvnd, tv_mainwalletbalance, tv_commissionwallet;

    public LinearLayoutManager linearLayoutManager, linearLayoutManager2;
    public ArrayList<WalletMainPaymentModel> modleonefgr_array1 = new ArrayList<WalletMainPaymentModel>();
    public WalletMainPaymentModel modleonefgr;
    public WalletMainPayment_Adapter listAdapter;
    public RecyclerView recycle_paymenthistory;

    @SuppressLint("CutPasteId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.payment_new_activity);
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
        recycle_paymenthistory = findViewById(R.id.recycle_paymenthistory);
        imagevnd = findViewById(R.id.imagevnd);
        tv_namevnd = findViewById(R.id.tv_namevnd);
        tv_parteridvnd = findViewById(R.id.tv_parteridvnd);
        tv_mainwalletbalance = findViewById(R.id.tv_mainwalletbalance);
        tv_commissionwallet = findViewById(R.id.tv_commissionwallet);

        back_paymentnew = findViewById(R.id.back_paymentnew);
        lnr_mainwallet = findViewById(R.id.lnr_mainwallet);
        lnr_commissionwallet = findViewById(R.id.lnr_commissionwallet);
        lnr_mainwallet.setOnClickListener(this);
        lnr_commissionwallet.setOnClickListener(this);
        back_paymentnew.setOnClickListener(this);
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            progressDialog();
            getJSONAgentwalletprofile();
            getJSONAgentwalletprofileforAdapter();
        } else {
            dismissDialog();
            Snackbar.make(this.findViewById(android.R.id.content), getResources().getString(R.string.connecttointenet), Snackbar.LENGTH_LONG).show();
        }
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
                        } else {
                            String s = response.trim();
                            System.out.println("msg_fonemain:::" + s);
                            dismissDialog();
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

    private void getJSONAgentwalletprofile() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/vendordetail.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Result_verify_fragment_onemain:::::" + response);
                        if (response == null) {
                            dismissDialog();
                        } else {
                            String s = response.trim();
                            System.out.println("msg_fonemain:::" + s);
                            dismissDialog();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                //String message = jsonObject.getString("message");
                                if (status.equalsIgnoreCase("1")) {
                                    Picasso.with(getApplicationContext())
                                            .load(jsonObject.getString("image"))
                                            .resize(300, 300)
                                            .into(imagevnd);
                                    tv_namevnd.setText(jsonObject.getString("name"));
                                    tv_parteridvnd.setText(jsonObject.getString("partner_id"));
                                    tv_mainwalletbalance.setText("Rs." + jsonObject.getString("main_balance"));
                                    tv_commissionwallet.setText("Rs." + jsonObject.getString("commission_balance"));
                                } else {
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
        kProgressHUD = new KProgressHUD(WalletMainPaymentActivity.this);
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
            case R.id.lnr_mainwallet:
                Intent intentmainwallet = new Intent(getApplicationContext(), BankTransferPayOnlineActivity.class);
                //intentmainwallet.putExtra("paymentype", "commissionbalance");
                intentmainwallet.putExtra("paymentype", "mainwallet");
                startActivity(intentmainwallet);
                finish();
                break;
            case R.id.lnr_commissionwallet:
                Intent intentcommissionwallet = new Intent(getApplicationContext(), BankTransferPayOnlineActivity.class);
                intentcommissionwallet.putExtra("paymentype", "commissionwallet");
                startActivity(intentcommissionwallet);
                finish();
                break;
            case R.id.back_paymentnew:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            default:
                break;
        }
    }
}

