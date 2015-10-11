package com.beacon.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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
import com.beacon.scheduler.BeaconJobScheduler;
import com.estimote.sdk.Region;
import com.example.saravanan.beaconsample.R;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity  {
    protected static final String TAG = "MonitoringActivity";
    private com.estimote.sdk.BeaconManager beaconManager;
    private com.estimote.sdk.Region region;
    private ProgressDialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beaconManager = new com.estimote.sdk.BeaconManager(this);

        RadioButton customerRadio = (RadioButton)findViewById(R.id.radioButton);
        customerRadio.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                beaconManager.setMonitoringListener(new com.estimote.sdk.BeaconManager.MonitoringListener() {
                    @Override
                    public void onEnteredRegion(com.estimote.sdk.Region region, List<com.estimote.sdk.Beacon> list) {
                        Intent intent = new Intent(MainActivity.this, NotifyService.class);
                        MainActivity.this.startService(intent);
                    }

                    @Override
                    public void onExitedRegion(com.estimote.sdk.Region region) {

                    }
                });

                beaconManager.connect(new com.estimote.sdk.BeaconManager.ServiceReadyCallback() {
                    @Override
                    public void onServiceReady() {
               /* beaconManager.startMonitoring(new com.estimote.sdk.Region("monitored region",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 22504, 48827));*/
                        beaconManager.startMonitoring(new com.estimote.sdk.Region("regionId",
                                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                                null, null));
                    }
                });
            }
        });


        RadioButton employerRadio = (RadioButton)findViewById(R.id.radioButton2);
        employerRadio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, CustomerServiceActivity.class);
                startActivity(intent);
            }
        });

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

    private void backgroundJob(){

        /*Intent intent = new Intent(MainActivity.this, NotifyService.class);
        MainActivity.this.startService(intent);*/

        ComponentName serviceName = new ComponentName(getApplicationContext(), BeaconJobScheduler.class);
        JobInfo jobInfo = new JobInfo.Builder(1, serviceName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .setPeriodic(6000)
                .build();

        JobScheduler scheduler = (JobScheduler) getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int result = scheduler.schedule(jobInfo);
        if (result == JobScheduler.RESULT_SUCCESS) Log.d(TAG, "Job scheduled successfully!");
    }

}
