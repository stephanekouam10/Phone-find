package com.example.phonefind;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Device {

    private int id;
    private String Manufacturer;
    private String Model;
    private String DeviceName;
    private  String IMEI;
    private String Serie;

    private Context context;
    public Device(Context context) {
        this.context = context;
    }

    public Device() {
    }

    public Device(int id, String manufacturer, String model, String IMEI, String serie, String DeviceName) {
        this.id = id;
        Manufacturer = manufacturer;
        Model = model;
        this.DeviceName = DeviceName;
        this.IMEI = IMEI;
        Serie = serie;
    }

    public Device(String manufacturer, String model, String IMEI, String serie) {
        Manufacturer = manufacturer;
        Model = model;
        this.IMEI = IMEI;
        Serie = serie;
    }

    public String getDeviceName() {
        return Build.DEVICE;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModel() {
        return Build.MODEL;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getIMEI() {
//        TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
//        String IMEI = tManager.getDeviceId();
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getSerie() {
         String serial = Build.getSerial();
        return serial;
    }

    public void setSerie(String serie) {
        Serie = serie;
    }
}
