package com.example.greatework;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;

public class ShareActivity extends AppCompatActivity {
    EditText name, desc, quant, addr,phone;
    Button share, back;
    ImageButton button_location;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        name = findViewById(R.id.e1);
        phone=findViewById(R.id.e2);
        desc = findViewById(R.id.e3);
        quant = findViewById(R.id.e4);
        addr = findViewById(R.id.e5);
        share = findViewById(R.id.b1);
        back = findViewById(R.id.b2);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uname = name.getText().toString();
                String udesc = desc.getText().toString();
                String uquant = quant.getText().toString();
                String uaddr = addr.getText().toString();
                String uphone = phone.getText().toString();
                if (TextUtils.isEmpty(uname)) {
                    name.setError("Name is not empty");
                    return;
                }
                if (TextUtils.isEmpty(udesc)) {
                    desc.setError("Description is required");
                    return;
                }
                if (TextUtils.isEmpty(uquant)) {
                    quant.setError("Quantity is required");
                    return;
                }
                if (TextUtils.isEmpty(uaddr)) {
                    addr.setError("Address is Required");
                    return;
                }
                if (TextUtils.isEmpty(uphone) || phone.length()>10 || phone.length()<10) {
                    phone.setError("Phone Number should be correct");
                    return;
                }
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                    {
                        sendmsg();
                    }
                    else {
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS},1);
                    }
                }

            }
        });

    }
    public void sendmsg()
    {
        String uname = name.getText().toString();
        String udesc = desc.getText().toString();
        String uquant = quant.getText().toString();
        String uaddr = addr.getText().toString();
        String uphone = phone.getText().toString().trim();
        String msg= "\nName: "+uname+"\n Description: "+udesc +"\n Quantity: "+uquant+"\n Address: "+uaddr+"\n phone: "+uphone;
        String num="7702852625";
        try {
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage(num, null, msg, null, null);
            Toast.makeText(this, "sent", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show();
        }


    }
}
