package com.beacon.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.beacon.fragment.CustomerServiceFragmentAdapter;
import com.beacon.server.ServerHandler;
import com.beacon.util.RoundImage;
import com.example.saravanan.beaconsample.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class CustomerServiceActivity extends AppCompatActivity {

    String serverURL = "http://beaconservice.elasticbeanstalk.com/checkforcustomers";
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
