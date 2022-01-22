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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.Adapters.AgentMain_Adapter;
import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class AgentUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    public RelativeLayout back_des;
    public SessionManager session;
    public ProgressBar progressBar;
    public String vendorid, ajentid, ajentname, ajentphone, ajentemail, ajentaddress;
    private KProgressHUD kProgressHUD;
    public RecyclerView recyclerView;
    public AgentMain_Adapter listAdapter;
    public LinearLayoutManager linearLayoutManager;
    public AgentUpdateActivity activity;
    public Button button_save, button_cancel;
    private EditText editText_agentname, editText_phone, editText_mail, editText_address;
    public String name, mail, phone, address;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_update);
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
            ajentid = intent.get("ajentid").toString();
            ajentname = intent.get("ajentname").toString();
            ajentphone = intent.get("ajentphone").toString();
            ajentemail = intent.get("ajentemail").toString();
            ajentaddress = intent.get("ajentaddress").toString();
        }
        editText_agentname = findViewById(R.id.agentnameupdate);
        editText_mail = findViewById(R.id.agentmailaddupdate);
        editText_phone = findViewById(R.id.agentphoneaddupdate);
        editText_address = findViewById(R.id.agentaddressaddupdate);
        button_cancel = findViewById(R.id.cancelbtnupdate);
        button_save = findViewById(R.id.savebtnupdate);
        back_des = findViewById(R.id.back_agentadddetailupdate);
        back_des.setOnClickListener(this);
        button_save.setOnClickListener(this);

        editText_agentname.setText(ajentname);
        editText_mail.setText(ajentemail);
        editText_phone.setText(ajentphone);
        editText_address.setText(ajentaddress);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {//savebtn
            case R.id.back_agentadddetailupdate:
                finish();
                break;
            case R.id.savebtnupdate:
                if (editText_agentname.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_LONG).show();
                } else if (editText_mail.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter mail", Toast.LENGTH_LONG).show();
                } else if (editText_phone.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter phone", Toast.LENGTH_LONG).show();
                } else if (editText_address.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter address", Toast.LENGTH_LONG).show();
                } else {
                    name = editText_agentname.getText().toString().trim().toLowerCase();
                    mail = editText_mail.getText().toString().trim().toLowerCase();
                    phone = editText_phone.getText().toString().trim().toLowerCase();
                    address = editText_address.getText().toString().trim().toLowerCase();
                    if (isNetworkAvailable()) {
                        progressDialog();
                        senddatatoserveragent();
                        return;
                    } else {
                        dismissDialog();
                        Toast.makeText(AgentUpdateActivity.this, getResources().getString(R.string.connecttointenet), Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void senddatatoserveragent() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/updateajent.php",
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        if (response == null) {
                            dismissDialog();
                        } else {
                            System.out.println("getting_response" + response);
                            dismissDialog();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                if ((status.equals("1"))) {
                                    Intent intent = new Intent(AgentUpdateActivity.this, AgentMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Please Try again !", Toast.LENGTH_SHORT).show();
                        dismissDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("vendorid", vendorid);
                params.put("email", mail);
                params.put("name", name);
                params.put("phone", phone);
                params.put("address", address);
                params.put("ajentid", ajentid);
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
        kProgressHUD = new KProgressHUD(AgentUpdateActivity.this);
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

