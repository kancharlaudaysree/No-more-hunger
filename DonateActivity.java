package com.example.greatework;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class DonateActivity extends AppCompatActivity {
    EditText name,desc,item,addr,phone;
    Button donate,back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);


        name=findViewById(R.id.e1);
        desc=findViewById(R.id.e3);
        item=findViewById(R.id.e2);
        phone=findViewById(R.id.e4);
        addr=findViewById(R.id.e5);
        donate=findViewById(R.id.b1);
        back=findViewById(R.id.b2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=name.getText().toString();
                String udesc=desc.getText().toString();
                String uitem=item.getText().toString();
                String uaddr=addr.getText().toString();
                String uphone=phone.getText().toString();

                if(TextUtils.isEmpty(uname))
                {
                    name.setError("Name is not Empty");
                    return;
                }
                if(TextUtils.isEmpty(udesc))
                {
                    desc.setError("Description is Required");
                    return;
                }
                if(TextUtils.isEmpty(uitem))
                {
                    item.setError("Item type is Required");
                    return;
                }
                if(TextUtils.isEmpty(uaddr))
                {
                    addr.setError("Address is Required");
                    return;
                }
                if(TextUtils.isEmpty(uphone) || phone.length()<10 || phone.length()>10)
                {
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
        String uname=name.getText().toString();
        String udesc=desc.getText().toString();
        String uitem=item.getText().toString();
        String uaddr=addr.getText().toString();
        String uphone=phone.getText().toString();
        String num="7702852625";
        String msg="\nName: "+uname+"\n Description: "+udesc+"\n Item: "+uitem+"\n Address "+uaddr+"\n Phone: "+uphone;
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