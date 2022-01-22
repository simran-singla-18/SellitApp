package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import app.reatailx.sellitapp.Adapters.AgentMain_Adapter;
import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class AgentAddActivity extends AppCompatActivity implements View.OnClickListener {
    public RelativeLayout back_des;
    public SessionManager session;
    public ProgressBar progressBar;
    public String vendorid, lead_id;
    private KProgressHUD kProgressHUD;
    public RecyclerView recyclerView;
    public AgentMain_Adapter listAdapter;
    public LinearLayoutManager linearLayoutManager;
    public AgentAddActivity activity;
    public Button button_browse, button_save, button_cancel;
    private static final int REQUEST_PICK_FILE = 1;
    private static final int REQUEST_PICK_FILE_2 = 2;
    private String path, pathnew;
    private EditText editText_agentname, editText_phone, editText_mail, editText_address;
    private TextView textView_imagepathadhar;
    public String name, mail, phone, address;
    public ImageView imageView_profilepic, imageView_plus;
    public File file1, file2;
    public Uri outPutfileUri1, outPutfileUri2;
    public String profilepicturePath, adharcardPath;
    public Bitmap bitmap1, bitmap2;
    private Bitmap bitmapprofilephoto, bitmapadharcard;
    public String jsonString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        activity = this;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (shouldAskPermissions()) {
            askPermissions();
        }
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
        //imageView_profilepic = findViewById(R.id.img_profile);
        imageView_plus = findViewById(R.id.img_plus);
        textView_imagepathadhar = findViewById(R.id.agentadharcardfile_path);
        editText_agentname = findViewById(R.id.agentname);
        editText_mail = findViewById(R.id.agentmailadd);
        editText_phone = findViewById(R.id.agentphoneadd);
        editText_address = findViewById(R.id.agentaddressadd);
        button_cancel = findViewById(R.id.cancelbtn);
        button_save = findViewById(R.id.savebtn);
        button_browse = findViewById(R.id.browse);
        recyclerView = findViewById(R.id.recycle_agentmain);
        back_des = findViewById(R.id.back_agentadddetail);
        back_des.setOnClickListener(this);
        button_save.setOnClickListener(this);

        button_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browse_image();
            }
        });
        imageView_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browse_imageprofile();
            }
        });


        if (isNetworkAvailable()) {
            return;
        } else {
            dismissDialog();
            Toast.makeText(AgentAddActivity.this, getResources().getString(R.string.connecttointenet), Toast.LENGTH_LONG).show();
        }
    }

    //Profile pic
    private void browse_imageprofile() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        file1 = new File(Environment.getExternalStorageDirectory(), "MyPhoto1.jpg");
        outPutfileUri1 = Uri.fromFile(file1);
        System.out.println("getting_captureprofle  " + outPutfileUri1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri1);
        startActivityForResult(intent, REQUEST_PICK_FILE);
    }

    //Adhar pic
    private void browse_image() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        file2 = new File(Environment.getExternalStorageDirectory(), "MyPhoto2.jpg");
        outPutfileUri2 = Uri.fromFile(file2);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri2);
        startActivityForResult(intent, REQUEST_PICK_FILE_2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//Profile Pic
            switch (requestCode) {
                case REQUEST_PICK_FILE:
                    System.out.println("requestCode " + requestCode);
                    if (requestCode == REQUEST_PICK_FILE) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        profilepicturePath = cursor.getString(columnIndex);
                        cursor.close();
                        try {
                            File imageFile = new File(profilepicturePath);
                            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                            bitmap1 = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), bmOptions);
                            bitmapprofilephoto = getResizedBitmap(bitmap1, 200);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (profilepicturePath != null) {
                            ImageView imageView = findViewById(R.id.img_profile);
                            imageView.setVisibility(View.VISIBLE);
                            //imageView.setImageBitmap(BitmapFactory.decodeFile(profilepicturePath));
                            imageView.setImageBitmap(bitmap1);
                        }
                    }
                    break;
                case REQUEST_PICK_FILE_2:
                    System.out.println("requestCode2 " + requestCode);
                    if (requestCode == REQUEST_PICK_FILE_2) {//Adharcard pic
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        adharcardPath = cursor.getString(columnIndex);
                        System.out.println("getting_adharcardPath " + adharcardPath);
                        cursor.close();
                        textView_imagepathadhar.setText(adharcardPath);
                        try {
                            File imageFile = new File(adharcardPath);
                            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                            bitmap2 = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), bmOptions);
                            bitmapadharcard = getResizedBitmap(bitmap2, 200);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private Bitmap getResizedBitmap(Bitmap bitmap1, int i) {
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
        return Bitmap.createScaledBitmap(bitmap1, width, height, true);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {//savebtn
            case R.id.back_agentadddetail:
                finish();
                break;
            case R.id.savebtn:
                if (profilepicturePath == null || profilepicturePath.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please browse profile image", Toast.LENGTH_LONG).show();
                } else if (editText_agentname.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_LONG).show();
                } else if (editText_mail.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter mail", Toast.LENGTH_LONG).show();
                } else if (editText_phone.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter phone", Toast.LENGTH_LONG).show();
                } else if (adharcardPath == null || adharcardPath.equalsIgnoreCase("null")) {
                    Toast.makeText(getApplicationContext(), "Please browse adhar image", Toast.LENGTH_LONG).show();
                } else if (editText_address.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter address", Toast.LENGTH_LONG).show();
                } else {
                    name = editText_agentname.getText().toString().trim().toLowerCase();
                    mail = editText_mail.getText().toString().trim().toLowerCase();
                    phone = editText_phone.getText().toString().trim().toLowerCase();
                    address = editText_address.getText().toString().trim().toLowerCase();
                    progressDialog();
                    senddatatoserveragent();
                }
                break;
            default:
                break;
        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void senddatatoserveragent() {
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, "https://sellit.co.in/logisticapi/v1/addajent.php",
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
                                    Intent intent = new Intent(AgentAddActivity.this, AgentMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(AgentAddActivity.this, message, Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(AgentAddActivity.this, message, Toast.LENGTH_LONG).show();
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
                params.put("email", mail);
                params.put("name", name);
                params.put("phone", phone);
                params.put("address", address);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                params.put("adhar", new DataPart(adharcardPath + ".png", getFileDataFromDrawable(bitmapadharcard)));
                params.put("image", new DataPart(profilepicturePath + ".png", getFileDataFromDrawable(bitmapprofilephoto)));
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

    private void progressDialog() {
        kProgressHUD = new KProgressHUD(AgentAddActivity.this);
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

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

}

