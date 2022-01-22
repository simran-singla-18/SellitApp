package app.reatailx.sellitapp.Activites;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public Button button_submit;
    public EditText editText_id;
    public EditText editText_password;
    public ProgressBar progressBar;
    public String name, password;
    public SessionManager session;
    public String agentid = "", vendoremail = "", vendormobile = "", razorpayaccountid = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());

        progressBar = findViewById(R.id.progressBarlogin);
        button_submit = findViewById(R.id.loginbtn);
        editText_id = findViewById(R.id.userEmail);
        editText_password = findViewById(R.id.userPassword);
        button_submit.setOnClickListener(this);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginbtn:
                if (isNetworkAvailable()) {
                    clientlogin();
                    return;
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.connecttointenet), Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    private void clientlogin() {

        progressBar.setVisibility(View.VISIBLE);
        name = editText_id.getText().toString();
        password = editText_password.getText().toString();
        System.out.println("Data_user" + name + "  " + password);
        //validating inputs
        if (TextUtils.isEmpty(name)) {
            editText_id.setError(getResources().getString(R.string.id));
            editText_id.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        //validating inputs
        if (TextUtils.isEmpty(password)) {
            editText_password.setError(getResources().getString(R.string.pwd));
            editText_password.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/vendorlogin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response == null) {
                            progressBar.setVisibility(View.GONE);
                        } else {
                            String s = response.trim();
                            System.out.println("msglogindata:::" + s);
                            progressBar.setVisibility(View.GONE);
                            try {
                                JSONObject mJsonObject = new JSONObject(s);
                                String status = mJsonObject.getString("status");
                                String message = mJsonObject.getString("message");
                                if (status.equalsIgnoreCase("1")) {
                                    String roleis = mJsonObject.getString("role");
                                    System.out.println("getting_roleis  " + roleis);
                                    if (roleis.equalsIgnoreCase("vendor")) {
                                        vendoremail = mJsonObject.getString("vendoremail");
                                        vendormobile = mJsonObject.getString("vendormobile");
                                        razorpayaccountid = mJsonObject.getString("razorpayaccountid");
                                        Intent intentlogin = new Intent(LoginActivity.this, MainActivity.class);
                                        session.createuserdataSession(mJsonObject.getString("vendorid"), mJsonObject.getString("role"), agentid, vendoremail, vendormobile,razorpayaccountid);//desgid
                                        startActivity(intentlogin);
                                    } else {
                                        agentid = mJsonObject.getString("ajantid");
                                        vendormobile = mJsonObject.getString("vendormobile");
                                        System.out.println("getting_agentid   " + agentid);
                                        Intent intentlogin = new Intent(LoginActivity.this, MainActivity.class);
                                        session.createuserdataSession(mJsonObject.getString("vendorid"), mJsonObject.getString("role"), agentid, vendoremail, vendormobile,razorpayaccountid);//desgid
                                        startActivity(intentlogin);
                                    }
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                                    finish();

                                } else {
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
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
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tryagain), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", name);
                params.put("password", password);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}

