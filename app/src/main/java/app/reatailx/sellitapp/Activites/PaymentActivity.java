package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class PaymentActivity extends Activity implements PaymentResultWithDataListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();
    private String vendorid, lead_id, vendoremail, vendormobile, amount, id, razorpayaccountid, paymentype, amountposts;
    private int amountpost;
    private SessionManager session;
    private RelativeLayout relativeLayout;
    private String orderId, paymentId, signature;
    private KProgressHUD kProgressHUD;
    private String description;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);
        session = new SessionManager(getApplicationContext());
        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            vendoremail = user.get(SessionManager.KEY_VENDOREMAIL);
            vendormobile = user.get(SessionManager.KEY_VENDORMOBILE);
            razorpayaccountid = user.get(SessionManager.KEY_RAZORPAYACCOUNTID);
            System.out.println("User_Session_Data_final_payment:::::" + vendorid + "  " + vendoremail + "  " + vendormobile + "  " + razorpayaccountid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Bundle intent = getIntent().getExtras();
        if (intent != null) {
            amount = intent.get("amount").toString();
            amountpost = Integer.parseInt(amount) * 100;
            amountposts = String.valueOf(amountpost);
            id = intent.get("id").toString();//order_id
            paymentype = intent.get("paymentype").toString();//order_id
            System.out.println("amount_Data_payment_In_Paisa:::::" + amountpost);
            System.out.println("amount_Data_payment:::::" + amount + "  " + id + "    " + paymentype);
        }
        relativeLayout = findViewById(R.id.back_payment);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BankTransferPayOnlineActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getApplicationContext());
        TextView tv_orderid = (TextView) findViewById(R.id.tv_orderid);
        TextView tv_amount = (TextView) findViewById(R.id.tv_amount);
        int dvdamt = Integer.parseInt(amount) / 100;
        tv_amount.setText("INR " + String.valueOf(dvdamt) + ".00");
        tv_orderid.setText(id);
        // Payment button created by you in XML layout
        Button button = (Button) findViewById(R.id.btn_pay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        TextView privacyPolicy = (TextView) findViewById(R.id.txt_privacy_policy);

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://razorpay.com/sample-application/"));
                startActivity(httpIntent);
            }
        });
    }

    public void startPayment() {
        /* You need to pass current activity in order to let Razorpay create CheckoutActivity*/
        final Checkout co = new Checkout();
        co.setKeyID("rzp_live_4Gabs2fSUnGih5");
        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Sellit");
            options.put("description", "Paying Amount");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            options.put("image", "https://sellit.co.in/assets/images/logo-1.png");
            options.put("order_id", id);//from response of
            options.put("currency", "INR");
            options.put("amount", amountposts);//i am passing amount in currency subunits example  100 paisa =1 rupai
            options.put("theme.color", "#3399cc");
            options.put("prefill.email", vendoremail);
            options.put("prefill.contact", vendormobile);
            /*new start*/
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);
            /*end*/
            co.open(activity, options);
           /* String lstPendingItemsItemsnew = new Gson().toJson(co); // send to server
            System.out.println("getting_lstPendingItemsItemsnew " + lstPendingItemsItemsnew);*/
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID, PaymentData paymentData) {
        try {
            orderId = paymentData.getOrderId();
            paymentId = paymentData.getPaymentId();
            signature = paymentData.getSignature();
            if (isNetworkAvailable()) {
                progressDialog();
                paymentdatasave("completed");
                return;
            } else {
                dismissDialog();
                Toast.makeText(PaymentActivity.this, getResources().getString(R.string.connecttointenet), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String response, PaymentData paymentData) {
        try {
            System.out.println("getting_Error_Response " + response);

            JSONObject jsonObject = new JSONObject(response);
            JSONObject jsonObjectnew = jsonObject.getJSONObject("error");
            description = jsonObjectnew.getString("description");

            orderId = paymentData.getOrderId();
            paymentId = paymentData.getPaymentId();
            signature = paymentData.getSignature();

            if (isNetworkAvailable()) {
                progressDialog();
                paymentdatasave("Fail");
                return;
            } else {
                dismissDialog();
                Toast.makeText(PaymentActivity.this, getResources().getString(R.string.connecttointenet), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void paymentdatasave(String status) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/successpayment.php",
                //StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://postman-echo.com/post",
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        if (response == null) {
                            dismissDialog();
                        } else {
                            String s = response.trim();
                            System.out.println("msg_paycomissionresponsedatahere:::" + s);
                            dismissDialog();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                if (status.equals("1")) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), "Payment Successful: " + message, Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), "Payment failed: " + message, Toast.LENGTH_SHORT).show();
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
                params.put("vendor_id", vendorid);
                params.put("paymenttype", paymentype);
                params.put("razorpay_payment_id", paymentId);
                params.put("razorpay_order_id", orderId);
                params.put("razorpay_signature", signature);
                params.put("razorpayaccountid", razorpayaccountid);
                params.put("status", status);
                params.put("amount", amount);
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
        Intent intent = new Intent(getApplicationContext(), BankTransferPayOnlineActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void progressDialog() {
        kProgressHUD = new KProgressHUD(PaymentActivity.this);
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

}
