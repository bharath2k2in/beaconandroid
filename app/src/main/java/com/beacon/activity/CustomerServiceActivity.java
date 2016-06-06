package com.beacon.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beacon.fragment.CustomerServiceFragmentAdapter;
import com.beacon.server.ServerHandler;
import com.example.saravanan.beaconsample.R;

import org.apache.http.NameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;

public class CustomerServiceActivity extends AppCompatActivity {

    String serverURL = "https://beaconservicep1941589823trial.hanatrial.ondemand.com/beaconservice/checkforcustomers";
    CustomerServiceFragmentAdapter pagerAdapter;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);

        Button refreshButton = (Button)findViewById(R.id.button3);
        refreshButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                new LongOperation().execute(serverURL);
            }
        });

        pager = (ViewPager) findViewById(R.id.pager);

        new LongOperation().execute(serverURL);
    }

    private class LongOperation  extends AsyncTask<String, Void, Void> {

        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(CustomerServiceActivity.this);
        String result = "";

        protected void onPreExecute() {
            Log.d("Content ", "Starting onPreExecute  ");
            Dialog.setMessage("Please wait..");
            Dialog.show();
        }

        protected Void doInBackground(String... urls) {
            Log.d("Content ", "Starting doInBackground  ");
            ServerHandler serverHandler = new ServerHandler();
            result = serverHandler.makeServiceCall(serverURL, 1, new ArrayList<NameValuePair>());
            return null;
        }

        protected void onPostExecute(Void unused) {
            Log.d("Content ", "Starting onPostExecute  ");
            Dialog.dismiss();
            if (Error != null) {
                Log.e("Error ", Error);
            } else {
                parseJSon();
            }
        }


        private void parseJSon(){
            try {
                TextView waitText = (TextView)findViewById(R.id.textView7);
                JSONArray array = new JSONArray(result);
                if(array != null && array.length() > 0) {
                    waitText.setVisibility(View.INVISIBLE);

                    FragmentManager fm = getSupportFragmentManager();
                    pagerAdapter = new CustomerServiceFragmentAdapter(fm, array);
                    pager.setAdapter(pagerAdapter);

                    pagerAdapter.notifyDataSetChanged();
                    pager.invalidate();
                }else{
                    waitText.setVisibility(View.VISIBLE);
                    FragmentManager fm = getSupportFragmentManager();
                    pagerAdapter = new CustomerServiceFragmentAdapter(fm, new JSONArray());
                    pager.setAdapter(pagerAdapter);

                    pagerAdapter.notifyDataSetChanged();
                    pager.invalidate();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
