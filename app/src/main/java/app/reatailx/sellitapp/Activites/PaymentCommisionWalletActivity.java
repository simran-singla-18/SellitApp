package app.reatailx.sellitapp.Activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import app.reatailx.sellitapp.R;
import app.reatailx.sellitapp.SharePrefrence.SessionManager;

public class PaymentCommisionWalletActivity extends AppCompatActivity implements View.OnClickListener {

    public SessionManager session;
    public RelativeLayout relativeLayout_back;
    public Button btn_commission, btn_wallet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commissionwallet_activity);
        session = new SessionManager(getApplicationContext());
        btn_commission = findViewById(R.id.btn_commission);
        btn_wallet = findViewById(R.id.btn_wallet);
        relativeLayout_back = findViewById(R.id.back_paymentcommwallet);
        relativeLayout_back.setOnClickListener(this);
        btn_wallet.setOnClickListener(this);
        btn_commission.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_paymentcommwallet:
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.btn_commission:
                Intent intent = new Intent(getApplicationContext(), PaymentVirtualBankActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_wallet:
                Intent intentw = new Intent(getApplicationContext(), WalletMainPaymentActivity.class);
                startActivity(intentw);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);
        finish();
    }

}

