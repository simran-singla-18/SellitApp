package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import app.reatailx.sellitapp.Models.AllAjentsmodel;
import app.reatailx.sellitapp.Models.Inprogressmodel;
import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class InprogressItemdescriptionActivity extends AppCompatActivity implements View.OnClickListener {
    public RelativeLayout back_des;
    public SessionManager session;
    public ProgressBar progressBar;
    public TextView productname_des, productlocation_des, pickup_time_des, phone_price_des, comiision_price_des, location_des_summary, pickup_agentassignedname, screentouchcalibration_des, screendisplayspot_des, screendisplayline_des, screenphysical_des, frontcamera_des, backcamera_des, wifi_des, volume_des, battery_des, speaker_des, fingertouchsenser_des, bluetooth_desip, chargingporter_des, chargingport_des, powerbutton_des, facesenser_des, audioic_des, earphone_des, validbill_des, charger_des, box_des, mobileage_des, mobilewarranty_des, simnetwork_des, physicalconditionscratch_des, physicalconditiondent_des, physicalconditionpanel_des, physicalconditionbent_des, customername_desip, customermobile_desip, address_desip, tvSelectDate, cameraglass_desip, silentbutton_desip, mircophone_desip, vibrator_desip, copydisplay_desip, pickup_locationip, pickup_timedateyearip;
    public String vendorid, agentid, role, mobileboth = "", lead_id, ajentassign = "", flag, agentnamep, agentidp, mobileno, imagebmp = "";
    private KProgressHUD kProgressHUD;
    public ImageView imageView;
    public EditText editText_captchacode;
    public Button button_accept, btn_reschedule, btn_requoteprice;
    public Spinner spinner_agent;
    public LinearLayout linearLayout;
    public AllAjentsmodel allAjentsmodel;
    public ArrayList<AllAjentsmodel> modleonefgr_array1 = new ArrayList<AllAjentsmodel>();
    public HashMap<String, String> mapDatatptlist;
    public String dateTime = "", timeto = "", timefrom = "", reason = "", timesboth = "", agentmobile;
    public String warrenty = "", cat_id, deviceid, varientid, copy_display, ajentid, brandid, front_camera, back_camera, wifi, volume_button, battery, speaker, finger_touch, bluetooth, charging_porter, charging_port, powerbutton, facesensor, audioic, cameraglass, silentbutton, microphone, vibrator, copydisplay, earphone, validbill, charger, box, mobileage, abletotakecall, touchworking, display_spot, screenlines, screen_Condition, body_scratches, body_dents, side_back_panel, body_bents, model_name, imageurl, price, location, lead_pick_time, bank_name, accountnumber, ifsccode, beneficiary_name, upiid;
    public Button btn_faileddcr;
    /*start*/
    public PopupWindow pwindo, pwindoselfie, pwindoshortlycall, pwindo1;
    Spinner spinner1, spnr_time;
    String[] reasons = new String[]{"--Select Reason--", "Not available", "Out of station", "Waiting for new phone", "Not having accessories"};
    String[] times = new String[]{"--Select Time--", "10:00 Am - 12:00 Pm", "12:00 PM - 02:00 Pm", "02:00 Pm - 04:00 Pm", "04:00 Pm - 06:00 Pm", "06:00 Pm - 08:00 Pm"};
    RelativeLayout date, time, time1;
    DatePickerDialog datePickerDialog;
    TimePicker timePicker;
    TextView text1, text2, text3, text4;
    public int year;
    public int day;
    public int month;
    /*new*/
    public Spinner spinnerreasonfailed;
    public TextView textspinner;
    public String[] reasonsfailed = new String[]{"--Select Reason--", "Not willing to sell", "Not satisfied with price"};
    public EditText et_requoteprice, et_customerprice;
    public String reasonfailed = "", customerprice;

    public LinearLayout iv_captureone, iv_capturetwo, iv_capturethree, iv_capturefour;
    public ImageView civ_mobileone, civ_mobiletwo, civ_mobilethree, civ_mobilefour;
    public File file1, file2, file3, file4;
    public Uri outPutfileUri1, outPutfileUri2, outPutfileUri3, outPutfileUri4;
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE1 = 100;
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE2 = 101;
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE3 = 102;
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE4 = 103;
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE5SELFIE = 104;
    public Bitmap bitmap1, bitmap2, bitmap3, bitmap4, bitmap5selfie;
    private Bitmap bitmapmobilephoto1, bitmapmobilephoto2, bitmapmobilephoto3, bitmapmobilephoto4;
    public String mobilepicturePath1, mobilepicturePath2, mobilepicturePath3, mobilepicturePath4, mobilepicturePath5selfie;
    public ImageView imageView1, imageView2, imageView3, imageView4, imageView5selfie;
    public TextView tv_leadcancel, tv_leadfailed;
    public TextView tv_canclrechedule, tv_saverechedule;
    public Button btn_completedipdcr, btncalltocustomer, btncalltocustomeralternateno;
    public TextView tvrequotenewprice, tvSechedulenewTimeDate;
    public LinearLayout lrn_leadassignspnr, lnr_failsubmitip;
    public CardView cv_calltocustomerip, cv_recheduleip, cv_requotepriceip;
    public String jsonString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inprogressavailable_item_list_detail);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (shouldAskPermissions()) {
            askPermissions();
        }

        try {
            session = new SessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserdata();
            vendorid = user.get(SessionManager.KEY_VERDORID);
            agentid = user.get(SessionManager.KEY_AGENTID);
            role = user.get(SessionManager.KEY_ROLE);
            mobileboth = user.get(SessionManager.KEY_VENDORMOBILE);
            System.out.println("User_Session_Data_Vendorid:::::" + vendorid);
            System.out.println("User_Session_Data_role_mobile:::::" + role + "  " + mobileboth);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Bundle intent = getIntent().getExtras();
        if (intent != null) {
            lead_id = intent.get("lead_id").toString();
            ajentassign = intent.get("ajentassign").toString();
            flag = intent.get("flag").toString();
            imagebmp = intent.get("imagebmp").toString();
            System.out.println("getting_lead_id " + lead_id + "::::::::" + ajentassign + ":::::::::" + flag + "::::::::::::" + imagebmp);
        }
        cv_requotepriceip = findViewById(R.id.cv_requotepriceip);
        cv_recheduleip = findViewById(R.id.cv_recheduleip);
        cv_calltocustomerip = findViewById(R.id.cv_calltocustomerip);
        lnr_failsubmitip = findViewById(R.id.lnr_failsubmitip);
        lrn_leadassignspnr = findViewById(R.id.lrn_leadassignspnr);
        btncalltocustomer = findViewById(R.id.btncalltocustomer);
        btncalltocustomeralternateno = findViewById(R.id.btncalltocustomeralternateno);
        tvrequotenewprice = findViewById(R.id.tvrequotenewprice);
        tvSechedulenewTimeDate = findViewById(R.id.tvSechedulenewTimeDate);
        btn_completedipdcr = findViewById(R.id.btn_completedipdcr);
        btn_faileddcr = findViewById(R.id.btn_faileddcr);
        linearLayout = findViewById(R.id.linrlayoutdcr);
        spinner_agent = findViewById(R.id.spnr_assignedtoagent);
        editText_captchacode = findViewById(R.id.et_captchacodedesip);
        btn_reschedule = findViewById(R.id.btn_reschedule);
        button_accept = findViewById(R.id.btn_acceptip);
        imageView = findViewById(R.id.image_desip);
        productname_des = findViewById(R.id.productname_desip);
        productlocation_des = findViewById(R.id.productlocation_desip);
        pickup_time_des = findViewById(R.id.pickup_time_desip);
        phone_price_des = findViewById(R.id.phone_price_desip);
        comiision_price_des = findViewById(R.id.comiision_price_desip);
        location_des_summary = findViewById(R.id.location_desip);
        pickup_agentassignedname = findViewById(R.id.pickup_agentassigned_name);
        frontcamera_des = findViewById(R.id.frontcamera_desip);
        backcamera_des = findViewById(R.id.backcamera_desip);
        wifi_des = findViewById(R.id.wifi_desip);
        volume_des = findViewById(R.id.volume_desip);
        battery_des = findViewById(R.id.battery_desip);
        speaker_des = findViewById(R.id.speaker_desip);
        fingertouchsenser_des = findViewById(R.id.fingertouchsenser_desip);
        bluetooth_desip = findViewById(R.id.bluetooth_desip);

        chargingport_des = findViewById(R.id.chargingport_desip);
        powerbutton_des = findViewById(R.id.powerbutton_desip);
        facesenser_des = findViewById(R.id.facesenser_desip);
        audioic_des = findViewById(R.id.audioic_desip);

        cameraglass_desip = findViewById(R.id.cameraglass_desip);
        silentbutton_desip = findViewById(R.id.silentbutton_desip);
        mircophone_desip = findViewById(R.id.mircophone_desip);
        vibrator_desip = findViewById(R.id.vibrator_desip);
        copydisplay_desip = findViewById(R.id.copydisplay_desip);
        pickup_locationip = findViewById(R.id.pickup_locationip);
        pickup_timedateyearip = findViewById(R.id.pickup_timedateyearip);


        screentouchcalibration_des = findViewById(R.id.screentouchcalibration_desip);
        screendisplayspot_des = findViewById(R.id.screendisplayspot_desip);
        screendisplayline_des = findViewById(R.id.screendisplayline_desip);
        screenphysical_des = findViewById(R.id.screenphysical_desip);


        earphone_des = findViewById(R.id.earphone_desip);
        validbill_des = findViewById(R.id.validbill_desip);
        charger_des = findViewById(R.id.charger_desip);
        box_des = findViewById(R.id.box_desip);
        mobileage_des = findViewById(R.id.mobileage_desip);
        mobilewarranty_des = findViewById(R.id.mobilewarranty_desip);
        //simnetwork_des = findViewById(R.id.simnetwork_desip);
        physicalconditionscratch_des = findViewById(R.id.physicalconditionscratch_desip);
        physicalconditiondent_des = findViewById(R.id.physicalconditiondent_desip);
        physicalconditionpanel_des = findViewById(R.id.physicalconditionpanel_desip);
        physicalconditionbent_des = findViewById(R.id.physicalconditionbent_desip);
        customername_desip = findViewById(R.id.customername_desip);
        customermobile_desip = findViewById(R.id.customermobile_desip);
        address_desip = findViewById(R.id.address_desip);
        btn_requoteprice = findViewById(R.id.btn_requoteprice);
        back_des = findViewById(R.id.back_desipneww);

        btncalltocustomer.setOnClickListener(this);
        btncalltocustomeralternateno.setOnClickListener(this);
        back_des.setOnClickListener(this);
        button_accept.setOnClickListener(this);
        btn_reschedule.setOnClickListener(this);
        btn_requoteprice.setOnClickListener(this);
        btn_faileddcr.setOnClickListener(this);
        btn_completedipdcr.setOnClickListener(this);

        if (flag.equalsIgnoreCase("complete")) {
            lrn_leadassignspnr.setVisibility(View.GONE);
            cv_calltocustomerip.setVisibility(View.GONE);
            cv_recheduleip.setVisibility(View.GONE);
            cv_requotepriceip.setVisibility(View.GONE);
            lnr_failsubmitip.setVisibility(View.GONE);
        } else {
            lrn_leadassignspnr.setVisibility(View.VISIBLE);
            cv_calltocustomerip.setVisibility(View.VISIBLE);
            cv_recheduleip.setVisibility(View.VISIBLE);
            cv_requotepriceip.setVisibility(View.VISIBLE);
            lnr_failsubmitip.setVisibility(View.VISIBLE);
        }

        if (ajentassign.equals("null")) {
            pickup_agentassignedname.setText("");
        } else {
            pickup_agentassignedname.setText(ajentassign);
        }

        if (role.equalsIgnoreCase("vendor")) {
            //spinner_agent.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            btn_reschedule.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.GONE);
            btn_reschedule.setVisibility(View.VISIBLE);
        }

        if (isNetworkAvailable()) {
            progressDialog();
            clientlogin();//vendor
            getagentassignedlist();//agent
            return;
        } else {
            dismissDialog();
            Toast.makeText(InprogressItemdescriptionActivity.this, getResources().getString(R.string.connecttointenet), Toast.LENGTH_LONG).show();
        }
    }

    private void getagentassignedlist() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/allajents.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Result_verify_fragment_tarning:::::" + response);
                        if (response == null) {
                            dismissDialog();
                        } else {
                            try {
                                dismissDialog();
                                List<Map<String, String>> items = new ArrayList<Map<String, String>>();
                                JSONObject object = new JSONObject(response);
                                JSONArray arr = object.getJSONArray("ajent_information");
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject jsonObject = arr.getJSONObject(i);
                                    allAjentsmodel = new AllAjentsmodel();
                                    allAjentsmodel.setVendorid(vendorid);
                                    allAjentsmodel.setId(jsonObject.get("id").toString());
                                    allAjentsmodel.setName(jsonObject.get("ajentname").toString());
                                    modleonefgr_array1.add(allAjentsmodel);
                                    mapDatatptlist = new HashMap<String, String>();
                                    mapDatatptlist.put("id", jsonObject.getString("id").toString());
                                    mapDatatptlist.put("ajentname", jsonObject.getString("ajentname").toString());
                                    items.add(mapDatatptlist);
                                }
                                String[] transporternameall;
                                transporternameall = new String[items.size() + 1];
                                transporternameall[0] = "Re-Assigned"; //item at pos 0
                                int j = 1;
                                for (int index = 0; index < items.size(); index++) {
                                    Map<String, String> map = items.get(index);
                                    transporternameall[j++] = map.get("ajentname");
                                }
                                ArrayAdapter adapteuvltpt = new ArrayAdapter<String>(InprogressItemdescriptionActivity.this, R.layout.simple_spinner_git, transporternameall);
                                spinner_agent.setAdapter(adapteuvltpt);
                                spinner_agent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long idis) {
                                        for (AllAjentsmodel gipTransporterModel : modleonefgr_array1) {
                                            if (gipTransporterModel.getName().contains(spinner_agent.getSelectedItem().toString())) {
                                                agentnamep = gipTransporterModel.getName();
                                                agentidp = gipTransporterModel.getId();
                                                System.out.println("getting_agentnameid:::" + agentnamep + "     " + agentidp);
                                                progressDialog();
                                                assignedleadtoagent();
                                                break;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                    }
                                });
                            } catch (JSONException e) {
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

    private void assignedleadtoagent() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/assignlead.php",
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
                                if (status.equalsIgnoreCase("1")) {
                                    Intent intent = new Intent(getApplicationContext(), InprogressActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(InprogressItemdescriptionActivity.this, "Success !!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(InprogressItemdescriptionActivity.this, "Someting went wrong !", Toast.LENGTH_LONG).show();
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
                params.put("ajentid", agentidp);
                params.put("leadid", lead_id);
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

    @SuppressLint("CutPasteId")
    private void leadfailed() {//leadfailed
        try {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.windowpopup_leadfailed,
                    findViewById(R.id.relativefailed));
            pwindo1 = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            pwindo1.showAtLocation(layout, Gravity.CENTER, 0, 0);
            textspinner = layout.findViewById(R.id.textspinner);
            et_requoteprice = layout.findViewById(R.id.et_requoteprice);
            et_requoteprice.setText(price);
            et_requoteprice.setEnabled(false);
            et_customerprice = layout.findViewById(R.id.et_customerprice);

            iv_captureone = layout.findViewById(R.id.iv_captureonefailed);
            iv_capturetwo = layout.findViewById(R.id.iv_capturetwofailed);
            iv_capturethree = layout.findViewById(R.id.iv_capturethreefailed);
            iv_capturefour = layout.findViewById(R.id.iv_capturefourfailed);

            civ_mobileone = layout.findViewById(R.id.civ_mobileonefailed);
            civ_mobiletwo = layout.findViewById(R.id.civ_mobiletwofailed);
            civ_mobilethree = layout.findViewById(R.id.civ_mobilethreefailed);
            civ_mobilefour = layout.findViewById(R.id.civ_mobilefourfailed);

            imageView1 = layout.findViewById(R.id.civ_mobileonefailed);
            imageView2 = layout.findViewById(R.id.civ_mobiletwofailed);
            imageView3 = layout.findViewById(R.id.civ_mobilethreefailed);
            imageView4 = layout.findViewById(R.id.civ_mobilefourfailed);

            tv_leadcancel = layout.findViewById(R.id.tv_leadcancel);
            tv_leadfailed = layout.findViewById(R.id.tv_leadfailed);

            spinnerreasonfailed = layout.findViewById(R.id.spinnerreasonfailed);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, reasonsfailed);
            spinnerreasonfailed.setAdapter(adapter);
            //spinnerreasonfailed.setPrompt("--Select Reason--");
            spinnerreasonfailed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spinnerreasonfailed.getFirstVisiblePosition() == 0) {
                        textspinner.setVisibility(View.VISIBLE);
                        reasonfailed = spinnerreasonfailed.getSelectedItem().toString();
                        System.out.println("getting_reason_if " + reason);
                    } else {
                        reasonfailed = spinnerreasonfailed.getSelectedItem().toString();
                        System.out.println("getting_reason_else " + reason);
                        textspinner.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    textspinner.setVisibility(View.VISIBLE);
                }
            });

            iv_captureone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                  /*  file1 = new File(Environment.getExternalStorageDirectory(), "MyPhoto1.jpg");
                    outPutfileUri1 = Uri.fromFile(file1);
                    System.out.println("getting_captureprofle  " + outPutfileUri1);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri1);*/
                    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE1);
                }
            });
            iv_capturetwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                   /* file2 = new File(Environment.getExternalStorageDirectory(), "MyPhoto2.jpg");
                    outPutfileUri2 = Uri.fromFile(file2);
                    System.out.println("getting_captureprofle  " + outPutfileUri2);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri2);*/
                    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE2);
                }
            });
            iv_capturethree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                   /* file3 = new File(Environment.getExternalStorageDirectory(), "MyPhoto3.jpg");
                    outPutfileUri3 = Uri.fromFile(file3);
                    System.out.println("getting_captureprofle  " + outPutfileUri3);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri3);*/
                    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE3);
                }
            });
            iv_capturefour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                   /* file4 = new File(Environment.getExternalStorageDirectory(), "MyPhoto4.jpg");
                    outPutfileUri4 = Uri.fromFile(file4);
                    System.out.println("getting_captureprofle  " + outPutfileUri4);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri4);*/
                    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE4);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
// postdatahereofleadfail//111111111111111111111111111111111111


        tv_leadcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwindo1.dismiss();
            }
        });

        tv_leadfailed.setOnClickListener(new View.OnClickListener() {//000000000000000000000000000000
            @Override
            public void onClick(View v) {
                if (reasonfailed.equals("") || reasonfailed.length() == 0 || reasonfailed.equals("--Select Reason--")) {
                    Toast.makeText(getApplicationContext(), "Please select reason", Toast.LENGTH_LONG).show();
                } else if (et_customerprice.getText().length() == 0) {
                    customerprice = et_customerprice.getText().toString();
                    System.out.println("getting_customeprice " + customerprice);
                    Toast.makeText(getApplicationContext(), "Please Enter Custome Price", Toast.LENGTH_LONG).show();
                } else if (mobilepicturePath1 == null || mobilepicturePath1.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please capture all images", Toast.LENGTH_LONG).show();
                } else if (mobilepicturePath2 == null || mobilepicturePath2.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please capture all images", Toast.LENGTH_LONG).show();
                } else if (mobilepicturePath3 == null || mobilepicturePath3.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please capture all images", Toast.LENGTH_LONG).show();
                } else if (mobilepicturePath4 == null || mobilepicturePath4.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please capture all images", Toast.LENGTH_LONG).show();
                } else {
                    pwindo1.dismiss();
                    progressDialog();
                    senddataforfailed();
                }
            }
        });
    }

    private void senddataforfailed() {
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/failedlead.php",
                //VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, "https://postman-echo.com/post",
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            jsonString = new String(response.data, "UTF-8");
                            System.out.println("getting_data_from_server" + jsonString);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (jsonString == null) {
                            dismissDialog();
                        } else {
                            System.out.println("getting_response" + jsonString);
                            dismissDialog();
                            try {
                                JSONObject jsonObject = new JSONObject(jsonString);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                if ((status.equals("1"))) {
                                    dismissDialog();
                                    Intent intent = new Intent(getApplicationContext(), InprogressActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                } else {
                                    dismissDialog();
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
                Map<String, String> params = new HashMap<>(); // candidatename
                params.put("vendorid", vendorid);
                params.put("lead_id", lead_id);
                params.put("flag", "Tobefailed");
                params.put("offerprice", price);
                params.put("customerprice", customerprice);
                params.put("ajent_id", ajentassign);
                params.put("reason", reasonfailed);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                params.put("pic1", new DataPart(mobilepicturePath1 + ".png", getFileDataFromDrawable(bitmap1)));
                params.put("pic2", new DataPart(mobilepicturePath2 + ".png", getFileDataFromDrawable(bitmap2)));
                params.put("pic3", new DataPart(mobilepicturePath3 + ".png", getFileDataFromDrawable(bitmap3)));
                params.put("pic4", new DataPart(mobilepicturePath4 + ".png", getFileDataFromDrawable(bitmap4)));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).
                addToRequestQueue(volleyMultipartRequest);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    /*create end*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("getting_requestCode  " + requestCode);
        System.out.println("getting_resultCode  " + resultCode);

        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE1 && resultCode == -1) {
            try {
                bitmap1 = (Bitmap) data.getExtras().get("data");
                mobilepicturePath1 = String.valueOf(bitmap1);
               /* bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri1);
                int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
                File file = new File(String.valueOf(bitmap1));
                try {
                    bitmapmobilephoto1 = BitmapFactory.decodeFile(file.getPath());
                    bitmapmobilephoto1.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                    getResizedBitmap(bitmapmobilephoto1, 200);
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
                mobilepicturePath1 = String.valueOf(bitmapmobilephoto1);
                BitmapFactory.Options bounds = new BitmapFactory.Options();
                bounds.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file1.toString(), bounds);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(file1.toString(), opts);
                ExifInterface exif = new ExifInterface(file1.toString());
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                bitmapmobilephoto1 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
                imageView1.setVisibility(View.VISIBLE);
                imageView1.setImageBitmap(bitmapmobilephoto1);*/
                imageView1.setVisibility(View.VISIBLE);
                imageView1.setImageBitmap(bitmap1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE2 && resultCode == -1) {
            try {
                bitmap2 = (Bitmap) data.getExtras().get("data");
                mobilepicturePath2 = String.valueOf(bitmap2);
/*                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri2);

                int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
                File file = new File(String.valueOf(bitmap2));

                try {
                    bitmapmobilephoto2 = BitmapFactory.decodeFile(file.getPath());
                    bitmapmobilephoto2.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                    getResizedBitmap(bitmapmobilephoto2, 200);
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
                mobilepicturePath2 = String.valueOf(bitmapmobilephoto2);
                BitmapFactory.Options bounds = new BitmapFactory.Options();
                bounds.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file2.toString(), bounds);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(file2.toString(), opts);
                ExifInterface exif = new ExifInterface(file2.toString());
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                bitmapmobilephoto2 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
                imageView2.setVisibility(View.VISIBLE);
                imageView2.setImageBitmap(bitmapmobilephoto2);*/
                imageView2.setVisibility(View.VISIBLE);
                imageView2.setImageBitmap(bitmap2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE3 && resultCode == -1) {
            try {
                bitmap3 = (Bitmap) data.getExtras().get("data");
                mobilepicturePath3 = String.valueOf(bitmap3);
             /*   bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri3);

                int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
                File file = new File(String.valueOf(bitmap3));
                try {
                    bitmapmobilephoto3 = BitmapFactory.decodeFile(file.getPath());
                    bitmapmobilephoto3.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                    getResizedBitmap(bitmapmobilephoto3, 200);
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
                mobilepicturePath3 = String.valueOf(bitmapmobilephoto3);
                BitmapFactory.Options bounds = new BitmapFactory.Options();
                bounds.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file3.toString(), bounds);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(file3.toString(), opts);
                ExifInterface exif = new ExifInterface(file3.toString());
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                bitmapmobilephoto3 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
                imageView3.setVisibility(View.VISIBLE);
                imageView3.setImageBitmap(bitmapmobilephoto3);*/
                imageView3.setVisibility(View.VISIBLE);
                imageView3.setImageBitmap(bitmap3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE4 && resultCode == -1) {
            try {
                bitmap4 = (Bitmap) data.getExtras().get("data");
                mobilepicturePath4 = String.valueOf(bitmap4);
              /*  bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outPutfileUri4);

                int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
                File file = new File(String.valueOf(bitmap4));
                try {
                    bitmapmobilephoto4 = BitmapFactory.decodeFile(file.getPath());
                    bitmapmobilephoto4.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                    getResizedBitmap(bitmapmobilephoto4, 200); // old code
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
                mobilepicturePath4 = String.valueOf(bitmapmobilephoto4);
                BitmapFactory.Options bounds = new BitmapFactory.Options();
                bounds.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file4.toString(), bounds);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(file4.toString(), opts);
                ExifInterface exif = new ExifInterface(file4.toString());
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                bitmapmobilephoto4 = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
                imageView4.setVisibility(View.VISIBLE);
                imageView4.setImageBitmap(bitmapmobilephoto4);*/
                imageView4.setVisibility(View.VISIBLE);
                imageView4.setImageBitmap(bitmap4);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE5SELFIE && resultCode == -1) {
            try {
                bitmap5selfie = (Bitmap) data.getExtras().get("data");
                mobilepicturePath5selfie = String.valueOf(bitmap5selfie);
              /*  imageView4.setVisibility(View.VISIBLE);
                imageView4.setImageBitmap(bitmap4);*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "File Not Found , Please Re-Capture !!", Toast.LENGTH_SHORT).show();
        }
    }

    private String getResizedBitmap(Bitmap bitmap1, int i) {
        int width = bitmap1.getWidth();
        int height = bitmap1.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = i;
            height = (int) (width / bitmapRatio);
        } else {
            height = i;
            width = (int) (height * bitmapRatio);
        }
        return String.valueOf(Bitmap.createScaledBitmap(bitmap1, width, height, true));
    }

    private void datePicker() {
        try {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.windowpopup_rechedule,
                    findViewById(R.id.rellayoutwindrechedule));
            pwindo = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
            text1 = layout.findViewById(R.id.text1);
            text2 = layout.findViewById(R.id.text2);
            text3 = layout.findViewById(R.id.text3);
            tv_saverechedule = layout.findViewById(R.id.tv_saverechedule);
            tv_canclrechedule = layout.findViewById(R.id.tv_canclrechedule);
            spinner1 = layout.findViewById(R.id.reason);
            spnr_time = layout.findViewById(R.id.spnr_time);

            text4 = layout.findViewById(R.id.text4);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, reasons);
            spinner1.setAdapter(adapter);
            //spinner1.setPrompt("--Select Reason--");
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spinner1.getFirstVisiblePosition() == 0) {
                        text1.setVisibility(View.VISIBLE);
                        reason = spinner1.getSelectedItem().toString();
                        System.out.println("getting_reason " + reason);
                    } else {
                        reason = spinner1.getSelectedItem().toString();
                        System.out.println("getting_reason " + reason);
                        text1.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    text1.setVisibility(View.VISIBLE);
                }
            });

            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times);
            spnr_time.setAdapter(adapter1);
            //spinner1.setPrompt("--Select Reason--");
            spnr_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spnr_time.getFirstVisiblePosition() == 0) {
                        //text1.setVisibility(View.VISIBLE);
                        timesboth = spnr_time.getSelectedItem().toString();
                        System.out.println("getting_timesboth " + timesboth);
                    } else {
                        timesboth = spnr_time.getSelectedItem().toString();
                        System.out.println("getting_timesboth_else " + timesboth);
                        // text1.setVisibility(View.GONE);
                        try {
                            String pbarcode = timesboth;
                            String splitChrfeatchsrno = "\\-";
                            String[] fmgStrngfetchsrno = pbarcode.split(splitChrfeatchsrno);
                            timefrom = fmgStrngfetchsrno[0].trim();
                            timeto = fmgStrngfetchsrno[1].trim();
                            System.out.println("getting_timefrom " + timefrom);
                            System.out.println("getting_timeto " + timeto);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // text1.setVisibility(View.VISIBLE);
                }
            });

            tv_canclrechedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                }
            });

            tv_saverechedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                }
            });



            /*time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timePicker = new TimePicker(InprogressItemdescriptionActivity.this);
                    showHourPicker();

                }
            });

            time1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showHourPicker1();

                }
            });*/

            date = layout.findViewById(R.id.date);
            date.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {

/*
                    final Calendar myCalendar = Calendar.getInstance();
                    year = myCalendar.get(Calendar.YEAR);
                    month = myCalendar.get(Calendar.MONTH);
                    day = myCalendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(InprogressItemdescriptionActivity.this, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            String datelr = selectedyear + "-" + (selectedmonth + 1) + "-" + selectedday;
                            try {
                                @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                                @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");
                                Date date = inputFormat.parse(datelr);
                                String outputDateStrlr = null;
                                if (date != null) {
                                    outputDateStrlr = outputFormat.format(date);
                                }
                                dateTime = outputDateStrlr;
                                System.out.println("getting_dateTime " + dateTime);
                                text2.setText(outputDateStrlr);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }, year, month, day);
                    datePickerDialog.setTitle("Please select date");
                    datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                    datePickerDialog.show();
*/
                    final Calendar calendar = Calendar.getInstance();
                    DatePickerDialog dialog = new DatePickerDialog(InprogressItemdescriptionActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker arg0, int year, int month, int day_of_month) {
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, (month));
                            calendar.set(Calendar.DAY_OF_MONTH, day_of_month);
                            String myFormat = "dd-MMM-yyyy";
                            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                            dateTime = sdf.format(calendar.getTime());
                            System.out.println("getting_dateTime " + dateTime);
                            text2.setText(sdf.format(calendar.getTime()));
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());// TODO: used to hide previous date,month and year
                    calendar.add(Calendar.YEAR, 1);
                    dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());// TODO: used to hide future date,month and year
                    dialog.show();
                }
            });

            tv_saverechedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("getting_reason::::::::: " + reason);
                    System.out.println("getting_dateTime::::::: " + dateTime);
                    System.out.println("getting_timeto::::::::: " + timeto);
                    System.out.println("getting_timefrom::::::: " + timefrom);
                    if (reason.equals("") || reason.length() == 0 || reason.equals("--Select Reason--")) {
                        Toast.makeText(getApplicationContext(), "Please select reason !!", Toast.LENGTH_LONG).show();
                    } else if (dateTime.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Please select Date !!", Toast.LENGTH_LONG).show();
                    } else if (timesboth.equals("") || timesboth.length() == 0 || timesboth.equals("--Select Time--")) {
                        Toast.makeText(getApplicationContext(), "Please select Time !!", Toast.LENGTH_LONG).show();
                    }
                    /*else if (timeto.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Please select Time To !!", Toast.LENGTH_LONG).show();
                    } else if (timefrom.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Please select Time From !!", Toast.LENGTH_LONG).show();
                    } */
                    else {
                        reshesduledatetime();
                    }
                }
            });

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    private void showHourPicker() {
        final Calendar myCalender = Calendar.getInstance();
        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay > 12) {
                    text3.setText(String.valueOf(hourOfDay - 12) + ":" + (String.valueOf(minute) + "PM"));
                    timefrom = String.valueOf(hourOfDay - 12) + ":" + (String.valueOf(minute) + "PM");
                } else if (hourOfDay == 12) {
                    text3.setText("12" + ":" + (String.valueOf(minute) + "PM"));
                    timefrom = "12" + ":" + (String.valueOf(minute) + "PM");
                } else if (hourOfDay < 12) {
                    if (hourOfDay != 0) {
                        text3.setText(String.valueOf(hourOfDay) + ":" + (String.valueOf(minute) + "AM"));
                        timefrom = String.valueOf(hourOfDay - 12) + ":" + (String.valueOf(minute) + "AM");
                    } else {
                        text3.setText("12" + ":" + (String.valueOf(minute) + "AM"));
                        timefrom = "12" + ":" + (String.valueOf(minute) + "AM");
                    }
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(InprogressItemdescriptionActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    private void showHourPicker1() {
        final Calendar myCalender = Calendar.getInstance();
        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay > 12) {
                    text4.setText(String.valueOf(hourOfDay - 12) + ":" + (String.valueOf(minute) + "PM"));
                    timeto = String.valueOf(hourOfDay - 12) + ":" + (String.valueOf(minute) + "PM");
                } else if (hourOfDay == 12) {
                    text4.setText("12" + ":" + (String.valueOf(minute) + "PM"));
                    timeto = "12" + ":" + (String.valueOf(minute) + "PM");
                } else if (hourOfDay < 12) {
                    if (hourOfDay != 0) {
                        text4.setText(String.valueOf(hourOfDay) + ":" + (String.valueOf(minute) + "AM"));
                        timeto = String.valueOf(hourOfDay - 12) + ":" + (String.valueOf(minute) + "AM");
                    } else {
                        text4.setText("12" + ":" + (String.valueOf(minute) + "AM"));
                        timeto = "12" + ":" + (String.valueOf(minute) + "AM");
                    }
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(InprogressItemdescriptionActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    private void reshesduledatetime() {
        //StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://postman-echo.com/post",
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/scheduledlead.php",
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        if (response == null) {
                            dismissDialog();
                            pwindo.dismiss();
                        } else {
                            String s = response.trim();
                            System.out.println("msglogindata:::" + s);
                            dismissDialog();
                            pwindo.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                if (status.equalsIgnoreCase("1")) {
                                    pwindo.dismiss();
                                    Intent intent = new Intent(getApplicationContext(), InprogressActivity.class);
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
                //params.put("vendorid", vendorid);
                params.put("lead_id", lead_id);
                params.put("date", dateTime);
                params.put("timeto", timeto);
                params.put("timefrom", timefrom);
                params.put("reason", reason);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        VolleySingleton.getInstance(this).

                addToRequestQueue(stringRequest);
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
                                    Intent intent = new Intent(getApplicationContext(), InprogressActivity.class);
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
        kProgressHUD = new KProgressHUD(InprogressItemdescriptionActivity.this);
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
                            System.out.println("msglogindata_inprogressmaindata:::" + s);
                            dismissDialog();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String status = jsonObject.getString("status");

                                if (status.equalsIgnoreCase("1")) {
                                    agentmobile = jsonObject.getString("ajentmobnum");
                                    JSONArray jarray = jsonObject.getJSONArray("leads_description");
                                    for (int hk = 0; hk < jarray.length(); hk++) {
                                        JSONObject d = jarray.getJSONObject(hk);
                                        Picasso.with(getApplicationContext())
                                                .load(d.get("imageurl").toString())
                                                .resize(300, 300)
                                                .into(imageView);
                                        cat_id = d.get("cat_id").toString();
                                        deviceid = d.get("deviceid").toString();
                                        varientid = d.get("varientid").toString();
                                        brandid = d.get("brandid").toString();
                                        ajentid = d.get("ajentid").toString();
                                        front_camera = d.get("front_camera").toString();
                                        back_camera = d.get("back_camera").toString();
                                        wifi = d.get("wifi").toString();
                                        volume_button = d.get("volume_button").toString();
                                        battery = d.get("battery").toString();
                                        speaker = d.get("speaker").toString();
                                        finger_touch = d.get("finger_touch").toString();
                                        bluetooth = d.get("bluetooth").toString();
                                        charging_port = d.get("charging_port").toString();
                                        powerbutton = d.get("power_btn").toString();
                                        facesensor = d.get("face_sensor").toString();
                                        audioic = d.get("audio_receiver").toString();
                                        cameraglass = d.get("camera_glass").toString();
                                        silentbutton = d.get("silent_btn").toString();
                                        microphone = d.get("microphone").toString();
                                        vibrator = d.get("vibrator").toString();
                                        earphone = d.get("earphone").toString();
                                        validbill = d.get("bill").toString();
                                        charger = d.get("charger").toString();
                                        box = d.get("box").toString();
                                        mobileage = d.get("mobile_age").toString();
                                        abletotakecall = d.get("phonecall").toString();
                                        touchworking = d.get("touch_screen").toString();
                                        display_spot = d.get("display_spot").toString();
                                        screenlines = d.get("screenlines").toString();
                                        screen_Condition = d.get("Screen_Condition").toString();
                                        body_scratches = d.get("body_scratches").toString();
                                        body_dents = d.get("body_dents").toString();
                                        side_back_panel = d.get("side_back_panel").toString();
                                        body_bents = d.get("body_bents").toString();

                                        model_name = d.get("model_name").toString();
                                        imageurl = d.get("imageurl").toString();
                                        price = d.get("price").toString();
                                        location = d.get("location").toString();
                                        lead_pick_time = d.get("lead_pick_time").toString();
                                        bank_name = d.get("bank_name").toString();
                                        accountnumber = d.get("accountnumber").toString();
                                        ifsccode = d.get("ifsccode").toString();
                                        beneficiary_name = d.get("beneficiary_name").toString();
                                        upiid = d.get("upiid").toString();
                                        copy_display = d.get("copy_display").toString();
                                        warrenty = d.get("warrenty").toString();
                                        if (d.get("copy_display").toString().equalsIgnoreCase("no copy display")) {
                                            copydisplay_desip.setText("Orignal Display");//varientname
                                        } else {
                                            copydisplay_desip.setText(d.get("copy_display").toString());//varientname
                                        }
                                        //copydisplay_desip.setText(d.get("copy_display").toString());//varientname
                                        productname_des.setText(d.get("model_name").toString() + " " + "(" + d.get("varientname").toString() + ")");
                                        productlocation_des.setText(d.get("city").toString());
                                        tvSechedulenewTimeDate.setText(d.get("lead_pick_date").toString() + " " + d.get("lead_pick_month").toString() + " " + d.get("lead_pick_year").toString() + " , " + d.get("lead_pick_time").toString());
                                        pickup_time_des.setText(d.get("lead_pick_date").toString() + " " + d.get("lead_pick_month").toString() + " " + d.get("lead_pick_year").toString() + " , " + d.get("lead_pick_time").toString());
                                        phone_price_des.setText("Rs. " + d.get("price").toString());
                                        tvrequotenewprice.setText("Rs. " + d.get("price").toString());
                                        comiision_price_des.setText("");
                                        location_des_summary.setText(d.get("city").toString());
                                        screentouchcalibration_des.setText(d.get("touch_screen").toString());
                                        screendisplayspot_des.setText(d.get("display_spot").toString());
                                        screendisplayline_des.setText(d.get("physical_tablet_conditions").toString());
                                        screenphysical_des.setText(d.get("physical_issue").toString());
                                        frontcamera_des.setText(d.get("front_camera").toString());//start
                                        backcamera_des.setText(d.get("back_camera").toString());
                                        wifi_des.setText(d.get("wifi").toString());
                                        volume_des.setText(d.get("volume_button").toString());
                                        battery_des.setText(d.get("battery").toString());
                                        speaker_des.setText(d.get("speaker").toString());
                                        fingertouchsenser_des.setText(d.get("finger_touch").toString());
                                        bluetooth_desip.setText(d.get("bluetooth").toString());
                                        chargingport_des.setText(d.get("charging_port").toString());//end
                                        powerbutton_des.setText(d.get("power_btn").toString());
                                        facesenser_des.setText(d.get("face_sensor").toString());
                                        audioic_des.setText(d.get("audio_receiver").toString());
                                        cameraglass_desip.setText(d.get("camera_glass").toString());
                                        silentbutton_desip.setText(d.get("silent_btn").toString());
                                        mircophone_desip.setText(d.get("microphone").toString());
                                        vibrator_desip.setText(d.get("vibrator").toString());
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
                                        // earphone_des.setText(d.get("earphone").toString());
                                        //validbill_des.setText(d.get("bill").toString());
                                        //charger_des.setText(d.get("charger").toString());
                                        //box_des.setText(d.get("box").toString());
                                        mobileage_des.setText(d.get("mobile_age").toString());
                                        //simnetwork_des.setText("");
                                        physicalconditionscratch_des.setText(d.get("body_scratches").toString());
                                        physicalconditiondent_des.setText(d.get("body_dents").toString());
                                        physicalconditionpanel_des.setText(d.get("side_back_panel").toString());
                                        physicalconditionbent_des.setText(d.get("body_bents").toString());
                                        customername_desip.setText(d.get("name").toString());
                                        mobileno = d.get("mobile").toString();
                                        String lastFourDigits = mobileno.substring(mobileno.length() - 4);
                                        customermobile_desip.setText("******" + lastFourDigits);
                                        address_desip.setText(d.get("city").toString());
                                        mobilewarranty_des.setText(d.get("warrenty").toString());
                                        pickup_locationip.setText(d.get("location").toString());
                                        pickup_timedateyearip.setText(d.get("lead_pick_date").toString() + " " + d.get("lead_pick_month").toString() + " " + d.get("lead_pick_year").toString() + " , " + d.get("lead_pick_time").toString());
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
                params.put("flag", "Inprogress");
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


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.CALL_PHONE",
                "android.permission.CAMERA",
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    @Override
    public void onClick(View v) {//btn_completedipdcr
        switch (v.getId()) {//btn_accept
            case R.id.back_desipneww:
                finish();
                break;
            case R.id.btn_completedipdcr:

                try {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.windpopupselfisubmit,
                            findViewById(R.id.rellayoutwindpopupmain));
                    pwindoselfie = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
                    pwindoselfie.showAtLocation(layout, Gravity.CENTER, 0, 0);
                    TextView btn_selfieupload = layout.findViewById(R.id.btn_selfieupload);
                    TextView btn_continue = layout.findViewById(R.id.btn_continue);

                    btn_continue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pwindoselfie.dismiss();
                            Intent page = new Intent(getApplicationContext(), CompleteleadItemdescriptionActivity.class);
                            page.putExtra("lead_id", lead_id);
                            page.putExtra("ajentassign", ajentid);
                            page.putExtra("model_name", model_name);
                            page.putExtra("imageurl", imageurl);
                            page.putExtra("price", price);
                            page.putExtra("location", location);
                            page.putExtra("lead_pick_time", lead_pick_time);
                            page.putExtra("bank_name", bank_name);
                            page.putExtra("accountnumber", accountnumber);
                            page.putExtra("ifsccode", ifsccode);
                            page.putExtra("beneficiary_name", beneficiary_name);
                            page.putExtra("upiid", upiid);
                            startActivity(page);
                            finish();
                        }
                    });

                    btn_selfieupload.setOnClickListener(new View.OnClickListener() {//1111111111111111111111111111111111111111111111
                        @Override
                        public void onClick(View v) {
                            pwindoselfie.dismiss();
                            Intent intent = new Intent(InprogressItemdescriptionActivity.this, AndroidCameraExample.class);
                            intent.putExtra("lead_id", lead_id);
                            intent.putExtra("ajentassign", ajentassign);
                            intent.putExtra("flag", flag);
                            startActivity(intent);
                            /*pwindoselfie.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE1);*/
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
               /* Intent page = new Intent(getApplicationContext(), CompleteleadItemdescriptionActivity.class);
                page.putExtra("lead_id", lead_id);
                page.putExtra("ajentassign", ajentid);
                page.putExtra("model_name", model_name);
                page.putExtra("imageurl", imageurl);
                page.putExtra("price", price);
                page.putExtra("location", location);
                page.putExtra("lead_pick_time", lead_pick_time);
                page.putExtra("bank_name", bank_name);
                page.putExtra("accountnumber", accountnumber);
                page.putExtra("ifsccode", ifsccode);
                page.putExtra("beneficiary_name", beneficiary_name);
                page.putExtra("upiid", upiid);
                startActivity(page);
                finish();*/
                break;
            case R.id.btn_acceptip:
                if (editText_captchacode.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please verify captcha code ", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog();
                    sendcaptchacodeserver();
                }
                break;
            case R.id.btn_reschedule:
                datePicker();
                break;
            case R.id.btn_faileddcr:
                leadfailed();
                break;
            case R.id.btncalltocustomer:
                try {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.windpopupcallshortly,
                            findViewById(R.id.rellayoutwindpopupmaincallshortly));
                    pwindoshortlycall = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
                    pwindoshortlycall.showAtLocation(layout, Gravity.CENTER, 0, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 5 seconds
                        pwindoshortlycall.dismiss();
                        calltocustomer();//dheeraj call
                    }
                }, 5000);
                break;
            case R.id.btncalltocustomeralternateno:
                //calltocustomeralternate();
                break;
            case R.id.btn_requoteprice:
                Intent intent = new Intent(getApplicationContext(), RequoteActivity.class);
                intent.putExtra("cat_id", cat_id);
                intent.putExtra("deviceid", deviceid);
                intent.putExtra("varientid", varientid);
                intent.putExtra("brandid", brandid);
                intent.putExtra("lead_id", lead_id);
                intent.putExtra("front_camera", front_camera);
                intent.putExtra("back_camera", back_camera);
                intent.putExtra("wifi", wifi);
                intent.putExtra("volume_button", volume_button);
                intent.putExtra("battery", battery);
                intent.putExtra("speaker", speaker);
                intent.putExtra("finger_touch", finger_touch);
                intent.putExtra("bluetooth", bluetooth);
                intent.putExtra("charging_port", charging_port);
                intent.putExtra("powerbutton", powerbutton);
                intent.putExtra("facesensor", facesensor);
                intent.putExtra("audioic", audioic);
                intent.putExtra("cameraglass", cameraglass);
                intent.putExtra("silentbutton", silentbutton);
                intent.putExtra("microphone", microphone);
                intent.putExtra("vibrator", vibrator);
                intent.putExtra("earphone", earphone);
                intent.putExtra("copy_display", copy_display);
                intent.putExtra("validbill", validbill);
                intent.putExtra("charger", charger);
                intent.putExtra("box", box);
                intent.putExtra("mobileage", mobileage);
                intent.putExtra("abletotakecall", abletotakecall);
                intent.putExtra("touchworking", touchworking);
                intent.putExtra("display_spot", display_spot);
                intent.putExtra("screenlines", screenlines);
                intent.putExtra("screen_Condition", screen_Condition);
                intent.putExtra("body_scratches", body_scratches);
                intent.putExtra("body_dents", body_dents);
                intent.putExtra("side_back_panel", side_back_panel);
                intent.putExtra("body_bents", body_bents);
                intent.putExtra("warrenty", warrenty);

                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void calltocustomer() {
        System.out.println("getting_mobileboth " + mobileboth);
        System.out.println("getting_mobilecustomer " + mobileno);
        progressDialog();
        //StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://postman-echo.com/post",
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://mcube.vmc.in/api/outboundcall",
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
                                String message = jsonObject.getString("msg");
                                String callid = jsonObject.getString("callid");
                                System.out.println("getting_data_message_callid  " + message + "      " + callid);
                               /* Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_DIAL); // Action for what intent called for
                                intent.setData(Uri.parse("tel: " + callid)); // Data with intent respective action on intent
                                startActivity(intent);*/
                                // Toast.makeText(getApplicationContext(), "Callid " + callid, Toast.LENGTH_LONG).show();
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
                params.put("apikey", "dadfe5ff337bacdc750883d9e9475bf5");
                // params.put("exenumber", "7982171362");//vishal sir // To call // agents
                params.put("exenumber", mobileboth);//vishal sir // To call // agents
                //params.put("exenumber", agentmobile);//vishal sir // To call // agents
                //params.put("exenumber", mobileboth);//vishal sir // To call // agents
                //params.put("exenumber", "9958977432");
                //params.put("custnumber", "9467537034");//vikram sir // From Call // normal customer
                //params.put("custnumber", "8595870905");// dheeraj jio
                params.put("custnumber", mobileno);// dheeraj jio
                params.put("mct", "");
                params.put("wct", "");
                params.put("refid", "");
                params.put("url", "");
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

