package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class BankTransferPayOnlineActivity extends AppCompatActivity implements View.OnClickListener {

    private SessionManager session;
    private RelativeLayout relativeLayout_back;
    private EditText et_payonline;
    private Button btn_banktransferonline;
    private KProgressHUD kProgressHUD;
    private String vendorid, lead_id, vendoremail, vendormobile, razorpayaccountid = "", paymentype;
    private String amount;
    private int amountnew;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banktransfer_activity);
        session = new SessionManager(getApplicationContext());
        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            vendoremail = user.get(SessionManager.KEY_VENDOREMAIL);
            vendormobile = user.get(SessionManager.KEY_VENDORMOBILE);
            razorpayaccountid = user.get(SessionManager.KEY_RAZORPAYACCOUNTID);
            System.out.println("User_Session_Data_Vendorid:::::" + vendorid + "  " + vendoremail + "  " + vendormobile + "  " + razorpayaccountid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Bundle intent = getIntent().getExtras();
        if (intent != null) {
            paymentype = intent.get("paymentype").toString();//order_id
            System.out.println("paymentype:::::" + paymentype);
        }
        et_payonline = findViewById(R.id.et_payonline);
        btn_banktransferonline = findViewById(R.id.btn_banktransferonline);
        relativeLayout_back = findViewById(R.id.back_banktransfer);
        relativeLayout_back.setOnClickListener(this);
        btn_banktransferonline.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_banktransfer:
                Intent intent = new Intent(getApplicationContext(), WalletMainPaymentActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_banktransferonline:
                if (isNetworkAvailable()) {
                    if (et_payonline.length() == 0) {
                        Toast.makeText(BankTransferPayOnlineActivity.this, "Please Enter Amount !!", Toast.LENGTH_LONG).show();
                    } else {
                        progressDialog();
                        amount = et_payonline.getText().toString().trim();
                        amountnew = Integer.parseInt(amount) * 100;
                        banktransferonline(amountnew);
                    }
                    return;
                } else {
                    dismissDialog();
                    Toast.makeText(BankTransferPayOnlineActivity.this, getResources().getString(R.string.connecttointenet), Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void progressDialog() {
        kProgressHUD = new KProgressHUD(BankTransferPayOnlineActivity.this);
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

    private void banktransferonline(int amountnew) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/payment.php",
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        if (response == null) {
                            dismissDialog();
                        } else {
                            String s = response.trim();
                            System.out.println("msg_paycomission:::" + s);
                            dismissDialog();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                String id = jsonObject.getString("id");
                                String amount = jsonObject.getString("amount");
                                if (status.equalsIgnoreCase("created")) {
                                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                                    intent.putExtra("amount", amount);
                                    intent.putExtra("id", id);//order_id
                                    intent.putExtra("paymentype", paymentype);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Not Created !!", Toast.LENGTH_LONG).show();
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
                params.put("paymentype", paymentype);
                //params.put("paymentype", "commissionbalance");
                params.put("amount", String.valueOf(amountnew));
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), WalletMainPaymentActivity.class);
        startActivity(intent);
        finish();
    }

}

