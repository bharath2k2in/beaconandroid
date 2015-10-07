package com.beacon.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.beacon.util.RoundImage;
import com.example.saravanan.beaconsample.R;

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

public class CustomerServiceActivity extends AppCompatActivity {

    RoundImage roundedImage;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);

        imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.my_photo);
        imageView.setImageBitmap(icon);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView welcomeText = (TextView) findViewById(R.id.textView2);
            welcomeText.setText("Customer " + extras.getString("customerFirstName") + " " + extras.getString("customerLastName") + " is here....");
        }
    }
}
