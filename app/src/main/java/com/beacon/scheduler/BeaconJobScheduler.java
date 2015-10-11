package com.beacon.scheduler;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.beacon.activity.MainActivity;
import com.beacon.activity.WelcomeActivity;
import com.beacon.server.ServerHandler;
import com.beacon.util.RoundImage;
import com.estimote.sdk.Beacon;
import com.example.saravanan.beaconsample.R;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by saravanan on 09-Oct-15.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class BeaconJobScheduler extends JobService {
    String serverURL = "http://beaconservice.elasticbeanstalk.com/checkforcustomers";

    public BeaconJobScheduler(){
    }


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("BeaconJobScheduler", "Inside onStartJob ");
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("BeaconJobScheduler", "Inside onStopJob ");
        return true;
    }


    private class LongOperation  extends AsyncTask<String, Void, Void> {
        private String Error = null;
        String result = "";

        protected void onPreExecute() {
            Log.d("Content ", "Starting onPreExecute  ");
        }

        protected Void doInBackground(String... urls) {
            Log.d("Content ", "Starting doInBackground  ");
            ServerHandler serverHandler = new ServerHandler();
            result = serverHandler.makeServiceCall(serverURL, 1, new ArrayList<NameValuePair>());
            return null;
        }

        protected void onPostExecute(Void unused) {
            Log.d("Content ", "Starting onPostExecute  ");
            if (Error != null) {
                Log.e("Error ", Error);
            } else {
                parseJSon();
            }
        }


        private void parseJSon(){
            try {

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void  sendNotification(){
        // notification is selected
        Intent intent = new Intent(this, WelcomeActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification noti = new Notification.Builder(this)
                .setContentTitle("Welcome to RaboBank ")
                .setContentText("Tap to explore more... ").setSmallIcon(R.drawable.icon)
                .setContentIntent(pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
    }
}
