package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class ItemdescriptionActivity extends AppCompatActivity implements View.OnClickListener {
    public RelativeLayout back_des;
    public SessionManager session;
    public ProgressBar progressBar;
    public TextView productname_des, productlocation_des, pickup_time_des, phone_price_des, comiision_price_des, location_des_summary, pickup_time_des_summary, screentouchcalibration_des, screendisplayspot_des, screendisplayline_des, screenphysical_des, frontcamera_des, backcamera_des, wifi_des, volume_des, battery_des, speaker_des, fingertouchsenser_des, chargingporter_des, chargingport_des, powerbutton_des, facesenser_des, audioic_des, earphone_des, validbill_des, charger_des, box_des, mobileage_des, mobilewarranty_des, simnetwork_des, physicalconditionscratch_des, physicalconditiondent_des, physicalconditionpanel_des, physicalconditionbent_des, bluetooth_des, cameraglass_desip, silentbutton_desip, mircophone_desip, vibrator_desip, copydisplay_desip;
    public String vendorid, lead_id, phoneamount;
    private KProgressHUD kProgressHUD;
    public ImageView imageView;
    public EditText editText_captchacode;
    public Button button_accept;
    public CheckBox cb_captch;
    public TextView tv_robotres;
    String TAG = ItemdescriptionActivity.class.getSimpleName();
    String SITE_KEY = "6LfOkQwdAAAAACRihhDEVPUM7AH02JzezpEknWN-";
    String SECRET_KEY = "6LfOkQwdAAAAAHcL9z7M09d1e_X8AqwTOH_5ohg7";
    RequestQueue queue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_item_list_detail);
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
        queue = Volley.newRequestQueue(getApplicationContext());

        //editText_captchacode = findViewById(R.id.et_captchacodedes);
        tv_robotres = findViewById(R.id.tv_robotres);
        cb_captch = findViewById(R.id.cb_captch);
        button_accept = findViewById(R.id.btn_accept);
        imageView = findViewById(R.id.image_des);
        productname_des = findViewById(R.id.productname_des);
        productlocation_des = findViewById(R.id.productlocation_des);
        pickup_time_des = findViewById(R.id.pickup_time_des);
        phone_price_des = findViewById(R.id.phone_price_des);
        comiision_price_des = findViewById(R.id.comiision_price_des);
        location_des_summary = findViewById(R.id.location_des);
        pickup_time_des_summary = findViewById(R.id.pickup_time_des_summary);
        frontcamera_des = findViewById(R.id.frontcamera_des);
        backcamera_des = findViewById(R.id.backcamera_des);
        wifi_des = findViewById(R.id.wifi_des);
        volume_des = findViewById(R.id.volume_des);
        battery_des = findViewById(R.id.battery_des);
        speaker_des = findViewById(R.id.speaker_des);
        screentouchcalibration_des = findViewById(R.id.screentouchcalibration_des);
        screendisplayspot_des = findViewById(R.id.screendisplayspot_des);
        screendisplayline_des = findViewById(R.id.screendisplayline_des);
        screenphysical_des = findViewById(R.id.screenphysical_des);
        fingertouchsenser_des = findViewById(R.id.fingertouchsenser_des);
        chargingport_des = findViewById(R.id.chargingport_des);
        powerbutton_des = findViewById(R.id.powerbutton_des);
        facesenser_des = findViewById(R.id.facesenser_des);
        audioic_des = findViewById(R.id.audioic_des);
        earphone_des = findViewById(R.id.earphone_des);
        validbill_des = findViewById(R.id.validbill_des);
        charger_des = findViewById(R.id.charger_des);
        box_des = findViewById(R.id.box_des);
        mobileage_des = findViewById(R.id.mobileage_des);
        mobilewarranty_des = findViewById(R.id.mobilewarranty_des);
        //simnetwork_des = findViewById(R.id.simnetwork_des);
        physicalconditionscratch_des = findViewById(R.id.physicalconditionscratch_des);
        physicalconditiondent_des = findViewById(R.id.physicalconditiondent_des);
        physicalconditionpanel_des = findViewById(R.id.physicalconditionpanel_des);
        physicalconditionbent_des = findViewById(R.id.physicalconditionbent_des);
        bluetooth_des = findViewById(R.id.bluetooth_des);//new
        cameraglass_desip = findViewById(R.id.cameraglass_des);
        silentbutton_desip = findViewById(R.id.silentbutton_des);
        mircophone_desip = findViewById(R.id.mircophone_des);
        vibrator_desip = findViewById(R.id.vibrator_des);
        copydisplay_desip = findViewById(R.id.copydisplay_des);
        back_des = findViewById(R.id.back_des);
        back_des.setOnClickListener(this);
        button_accept.setOnClickListener(this);
        cb_captch.setOnClickListener(this);
        if (isNetworkAvailable()) {
            progressDialog();
            clientlogin();
            return;
        } else {
            dismissDialog();
            Toast.makeText(ItemdescriptionActivity.this, getResources().getString(R.string.connecttointenet), Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {//
            case R.id.back_des:
                finish();
                break;
            case R.id.cb_captch:
                Catcha();
                break;
            case R.id.btn_accept:
                if (cb_captch.isChecked()) {
                    progressDialog();
                    sendcaptchacodeserver();
                } else if (!cb_captch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Please verify you are not a robot", Toast.LENGTH_LONG).show();
                }

                /*
                if (editText_captchacode.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please verify captcha code ", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog();
                    sendcaptchacodeserver();
                }*/
                break;
            default:
                break;
        }
    }

    private void Catcha() {
        SafetyNet.getClient(this).verifyWithRecaptcha(SITE_KEY)
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                        if (!response.getTokenResult().isEmpty()) {
                            handleCaptchaResult(response.getTokenResult());
                            cb_captch.setChecked(true);
                            cb_captch.setEnabled(false);
                            tv_robotres.setText("You're not a Robot");
                            tv_robotres.setTextColor(Color.parseColor("#27ae60"));
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            Log.d(TAG, "Error message: " +
                                    CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                            cb_captch.setChecked(true);
                            cb_captch.setEnabled(false);
                            tv_robotres.setText("You're not a Robot");
                            tv_robotres.setTextColor(Color.parseColor("#27ae60"));
                        } else {
                            cb_captch.setChecked(true);
                            cb_captch.setEnabled(false);
                            Log.d(TAG, "Unknown type of error: " + e.getMessage());
                            tv_robotres.setText("You're not a Robot");
                            tv_robotres.setTextColor(Color.parseColor("#27ae60"));
                        }
                    }
                });

        /*SafetyNet.getClient(ItemdescriptionActivity.this).verifyWithRecaptcha("6LfaaQwdAAAAABSIlyqh3JdZjJ57tYEC5rtz3e0A")//site key
                .addOnSuccessListener((Activity) ItemdescriptionActivity.this,
                        new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                // Indicates communication with reCAPTCHA service was
                                // successful.
                                String userResponseToken = response.getTokenResult();
                                System.out.println("getting_userResponseToken " + userResponseToken);
                                if (!userResponseToken.isEmpty()) {
                                    handleCaptchaResult(userResponseToken);
                                    // Validate the user response token using the
                                    // reCAPTCHA siteverify API.
                                } else {
                                    cb_captch.setChecked(false);
                                }
                            }
                        })
                .addOnFailureListener((Activity) ItemdescriptionActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            // An error occurred when communicating with the
                            // reCAPTCHA service. Refer to the status code to
                            // handle the error appropriately.
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            cb_captch.setChecked(false);
                            System.out.println("getting_userResponseToken_fail " + statusCode);
                            System.out.println("getting_userResponseToken_fail_message " + e.getMessage());
                        } else {
                            Log.e("TAG", "Error: " + e.getMessage());
                        }
                    }
                });*/
        /*SafetyNet.getClient(ItemdescriptionActivity.this).verifyWithRecaptcha("6LfbaQwdAAAAAJzpqVvRQl4D38D-jVmh1QC0Wruz")
                .addOnSuccessListener(new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse recaptchaTokenResponse) {
                        // Indicates communication with reCAPTCHA service was
                        // successful.
                        String userResponseToken = recaptchaTokenResponse.getTokenResult();
                        System.out.println("getting_userResponseToken " + userResponseToken);
                        if (!userResponseToken.isEmpty()) {
                            handleCaptchaResult(userResponseToken);
                            // Validate the user response token using the
                            // reCAPTCHA siteverify API.
                            //Log.e(TAG, "VALIDATION STEP NEEDED");
                        } else {
                            cb_captch.setChecked(false);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            // An error occurred when communicating with the
                            // reCAPTCHA service. Refer to the status code to
                            // handle the error appropriately.
                            cb_captch.setChecked(false);
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            Log.e("TAG", "Error: " + CommonStatusCodes
                                    .getStatusCodeString(statusCode));
                        } else {
                            // A different, unknown type of error occurred.
                            Log.e("TAG", "Error: " + e.getMessage());
                        }
                    }
                });*/
    }

    private void handleCaptchaResult(String userResponseToken) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("getting_response " + response);
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("success")) {
                                tv_robotres.setText("You're not a Robot");
                                cb_captch.setChecked(true);
                                tv_robotres.setTextColor(Color.parseColor("#27ae60"));
                            }
                        } catch (Exception ex) {
                            Log.d("TAG", "Error message: " + ex.getMessage());
                            cb_captch.setChecked(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        cb_captch.setChecked(false);
                        Log.d("TAG", "Error message: " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("secret", SECRET_KEY);//secret key
                params.put("response", userResponseToken);
                //params.put("secret", "SITE_SECRET_KEY");
                //params.put("response", "responseToken");
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).
                addToRequestQueue(request);
        /*request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);*/
    }

    private void sendcaptchacodeserver() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/leadupdate.php",
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
                                String message = jsonObject.getString("message");
                                if (status.equalsIgnoreCase("1")) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
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
                params.put("lead_id", lead_id);
                params.put("flag", "inprogress");
                params.put("amount", phoneamount);
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
        kProgressHUD = new KProgressHUD(ItemdescriptionActivity.this);
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/leaddescription.php",
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
                                    JSONArray jarray = jsonObject.getJSONArray("leads_description");
                                    for (int hk = 0; hk < jarray.length(); hk++) {
                                        JSONObject d = jarray.getJSONObject(hk);
                                        //d.get("lead_id").toString()
                                        Picasso.with(getApplicationContext())
                                                .load(d.get("imageurl").toString())
                                                .resize(300, 300)
                                                .into(imageView);
                                        productname_des.setText(d.get("model_name").toString() + " " + "(" + d.get("varientname").toString() + ")");
                                        productlocation_des.setText(d.get("city").toString());
                                        pickup_time_des.setText(d.get("lead_pick_date").toString() + " " + d.get("lead_pick_month").toString() + " " + d.get("lead_pick_year").toString() + " , " + d.get("lead_pick_time").toString());
                                        phone_price_des.setText(d.get("price").toString());
                                        phoneamount = d.get("price").toString();
                                        comiision_price_des.setText("");
                                        location_des_summary.setText(d.get("city").toString());
                                        pickup_time_des_summary.setText(d.get("lead_pick_date").toString() + " " + d.get("lead_pick_month").toString() + " " + d.get("lead_pick_year").toString() + " , " + d.get("lead_pick_time").toString());
                                        screentouchcalibration_des.setText(d.get("touch_screen").toString());
                                        screendisplayspot_des.setText(d.get("display_spot").toString());
                                        screendisplayline_des.setText(d.get("physical_tablet_conditions").toString());
                                        screenphysical_des.setText(d.get("physical_issue").toString());
                                        frontcamera_des.setText(d.get("front_camera").toString());
                                        backcamera_des.setText(d.get("back_camera").toString());
                                        wifi_des.setText(d.get("wifi").toString());
                                        volume_des.setText(d.get("volume_button").toString());
                                        battery_des.setText(d.get("battery").toString());
                                        speaker_des.setText(d.get("speaker").toString());
                                        fingertouchsenser_des.setText(d.get("finger_touch").toString());
                                        chargingport_des.setText(d.get("charging_port").toString());
                                        powerbutton_des.setText(d.get("power_btn").toString());
                                        facesenser_des.setText(d.get("face_sensor").toString());
                                        audioic_des.setText(d.get("audio_receiver").toString());
                                        bluetooth_des.setText(d.get("bluetooth").toString());
                                        cameraglass_desip.setText(d.get("camera_glass").toString());
                                        silentbutton_desip.setText(d.get("silent_btn").toString());
                                        mircophone_desip.setText(d.get("microphone").toString());
                                        vibrator_desip.setText(d.get("vibrator").toString());
                                        //copydisplay_desip.setText("");
                                        if (d.get("copy_display").toString().equalsIgnoreCase("no copy display")) {
                                            copydisplay_desip.setText("Orignal Display");//varientname
                                        } else {
                                            copydisplay_desip.setText(d.get("copy_display").toString());//varientname
                                        }

                                        if (d.get("earphone").toString().equalsIgnoreCase("Original Earphones of Device")) {
                                            earphone_des.setText("Available");
                                        } else {
                                            earphone_des.setText("Not Available");
                                        }
                                        if (d.get("charger").toString().equalsIgnoreCase("Original Charger of Device")) {
                                            charger_des.setText("Available");
                                        } else {
                                            charger_des.setText("Not Available");
                                        }
                                        if (d.get("bill").toString().equalsIgnoreCase("Bill with same IMEI")) {
                                            validbill_des.setText("Available");
                                        } else {
                                            validbill_des.setText("Not Available");
                                        }
                                        if (d.get("box").toString().equalsIgnoreCase("Box with same IMEI")) {
                                            box_des.setText("Available");
                                        } else {
                                            box_des.setText("Not Available");
                                        }

                                       /* earphone_des.setText(d.get("earphone").toString());
                                        validbill_des.setText(d.get("bill").toString());
                                        charger_des.setText(d.get("charger").toString());
                                        box_des.setText(d.get("box").toString());*/
                                        mobileage_des.setText(d.get("mobile_age").toString());
                                        mobilewarranty_des.setText(d.get("warrenty").toString());
                                        //simnetwork_des.setText("");
                                        physicalconditionscratch_des.setText(d.get("body_scratches").toString());
                                        physicalconditiondent_des.setText(d.get("body_dents").toString());
                                        physicalconditionpanel_des.setText(d.get("side_back_panel").toString());
                                        physicalconditionbent_des.setText(d.get("body_bents").toString());
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Something went wrong !!", Toast.LENGTH_LONG).show();
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
                params.put("lead_id", lead_id);
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

