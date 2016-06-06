package com.beacon.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.beacon.activity.WelcomeActivity;
import com.example.saravanan.beaconsample.R;

public class NotifyService extends Service {

    final static String ACTION = "NotifyServiceAction";

    private com.estimote.sdk.BeaconManager beaconManager;
    private com.estimote.sdk.Region region;

    @Override
    public void onCreate() {
        super.onCreate();

       /* beaconManager = new com.estimote.sdk.BeaconManager(this);
        beaconManager.setRangingListener(new com.estimote.sdk.BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(com.estimote.sdk.Region region, List<com.estimote.sdk.Beacon> list) {
                Intent intent = new Intent(NotifyService.this, NotifyService.class);
                NotifyService.this.startService(intent);
            }
        });

        region = new com.estimote.sdk.Region("ranged region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);*/
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sendNotification();
        return START_NOT_STICKY;
    }

    private void  sendNotification(){
        // notification is selected
        Intent intent = new Intent(this, WelcomeActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification noti = new Notification.Builder(this)
                .setContentTitle("RaboBank")
                .setContentText("").setSmallIcon(R.drawable.rabo_image1)
                .setContentIntent(pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(9999, noti);
    }

    @Override
    public void onDestroy() {
       // beaconManager.stopRanging(region);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}