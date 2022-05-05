package com.example.phonefind;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaveDeviceActivity extends AppCompatActivity {

    Button btn_saveMyDevice;

    private TextView mDevice;
    private EditText mNameDevice;
    private TextView mModel;
    private TextView mManufacturer;
    private TextView mIMEI;
    private TextView mSerie;
    private Button mButton;

    private TelephonyManager manager;
    String deviceid;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_device);

        mDevice = (TextView) findViewById(R.id.txt_device);
        mNameDevice = (EditText) findViewById(R.id.txt_name);
        mModel = (TextView) findViewById(R.id.txt_model);
        mManufacturer = (TextView) findViewById(R.id.txt_manufacturer);
        mIMEI = (TextView) findViewById(R.id.txt_imei);
        mSerie = (TextView) findViewById(R.id.txt_serie);
        //mButton = (Button) findViewById(R.id.btn_save);

        SaveDevice();

        btn_saveMyDevice = (Button)findViewById(R.id.btn_saveMydevice);

        btn_saveMyDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mNameDevice.getText().toString().equals("")){
                    Toast.makeText(SaveDeviceActivity.this, "Veuillez entrer le nom de l'appareil", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(SaveDeviceActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void SaveDevice (){
        int permisI = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permisI == PackageManager.PERMISSION_GRANTED){
            manager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            deviceid = manager.getDeviceId().toString();
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},123);
        }

        Device device = new Device();

        String deviceName = device.getDeviceName().toString();
        String manufacturer = device.getManufacturer().toString();
        String model = device.getModel().toString();

        //String imei = deviceid ;;
        String serie = device.getSerie().toString();

        mDevice.setText(deviceName);
        mModel.setText(model);
        mManufacturer.setText(manufacturer);
        mIMEI.setText(deviceid);
        mSerie.setText(serie);
    }
}