package com.beacon.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;

import com.beacon.app.NotifyService;
import com.example.saravanan.beaconsample.R;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity{
    protected static final String TAG = "MonitoringActivity";
    private com.estimote.sdk.BeaconManager beaconManager;
    private com.estimote.sdk.Region region;
    private ProgressDialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*RadioButton customerRadio = (RadioButton)findViewById(R.id.radioButton);
        customerRadio.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(i);
            }
        });*/
      /* Dialog = new ProgressDialog(MainActivity.this);
       Dialog.setMessage("Please wait..");
       Dialog.show();

        beaconManager = new com.estimote.sdk.BeaconManager(this);

       *//*
        beaconManager.setRangingListener(new com.estimote.sdk.BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(com.estimote.sdk.Region region, List<com.estimote.sdk.Beacon> list) {
                if (!list.isEmpty()) {
                    com.estimote.sdk.Beacon nearestBeacon = list.get(0);
                    Log.d("Beacon", " Inside onBeaconsDiscovered.....  ");
                    Dialog.dismiss();
                    Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(i);
                }
            }
        });*//*

        beaconManager.setRangingListener(new com.estimote.sdk.BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(com.estimote.sdk.Region region, List<com.estimote.sdk.Beacon> list) {
                Intent intent = new Intent(MainActivity.this, NotifyService.class);
                MainActivity.this.startService(intent);
            }
        });

        region = new com.estimote.sdk.Region("ranged region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);*/
        Intent intent = new Intent(MainActivity.this, NotifyService.class);
        MainActivity.this.startService(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*beaconManager.connect(new com.estimote.sdk.BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });*/
    }

    @Override
    protected void onPause() {
       //beaconManager.stopRanging(region);
       super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
