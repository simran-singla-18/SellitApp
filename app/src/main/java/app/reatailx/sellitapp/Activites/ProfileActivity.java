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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class ProfileActivity extends Activity implements View.OnClickListener {
    private static final String TAG = ProfileActivity.class.getSimpleName();
    private String vendorid, vendoremail, vendormobile, razorpayaccountid;
    private SessionManager session;
    private KProgressHUD kProgressHUD;
    private RelativeLayout back_paymentnew;
    private ImageView imagevnd;
    private TextView tv_namevnd, tv_parteridvnd, tv_mainwalletbalance, tv_commissionwallet;

    @SuppressLint("CutPasteId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_activity);
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
        imagevnd = findViewById(R.id.imageprofile);
        tv_namevnd = findViewById(R.id.tv_nameprofile);
        tv_parteridvnd = findViewById(R.id.tv_parteridprofile);
        tv_mainwalletbalance = findViewById(R.id.tv_mainwalletbalanceprofile);
        tv_commissionwallet = findViewById(R.id.tv_commissionwalletprofile);

        back_paymentnew = findViewById(R.id.back_profilenew);
        back_paymentnew.setOnClickListener(this);
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            progressDialog();
            getJSONAgentwalletprofile();
        } else {
            dismissDialog();
            Snackbar.make(this.findViewById(android.R.id.content), getResources().getString(R.string.connecttointenet), Snackbar.LENGTH_LONG).show();
        }
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
        kProgressHUD = new KProgressHUD(ProfileActivity.this);
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
            case R.id.back_profilenew:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            default:
                break;
        }
    }
}

