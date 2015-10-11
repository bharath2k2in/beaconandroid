package com.beacon.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beacon.util.RoundImage;
import com.example.saravanan.beaconsample.R;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomerServiceFragment extends Fragment {

    int mCurrentPage;
    RoundImage roundedImage;
    ImageView imageView;
    byte [] imageByte;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle data = getArguments();
        mCurrentPage = data.getInt("current_page", 0);
        imageByte = data.getByteArray("image");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer, container,false);
       /* TextView tv = (TextView ) v.findViewById(R.id.tv);
        tv.setText("You are viewing the page #" + mCurrentPage + "\n\n" + "Swipe Horizontally left / right");*/

        imageView = (ImageView) v.findViewById(R.id.imageView);
        try {
            if(imageByte != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                roundedImage = new RoundImage(bitmap);
                imageView.setImageDrawable(roundedImage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return v;
    }

}

