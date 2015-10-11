package com.beacon.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by saravanan on 11-Oct-15.
 */
public class CustomerServiceFragmentAdapter extends FragmentPagerAdapter {

    int PAGE_COUNT = 5;
    private JSONArray  jsonArray;

    /** Constructor of the class */
    public CustomerServiceFragmentAdapter(FragmentManager fm, JSONArray jsonArray) {
        super(fm);
        PAGE_COUNT = jsonArray.length();
        this.jsonArray = jsonArray;
    }

    /** This method will be invoked when a page is requested to create */
    @Override
    public Fragment getItem(int arg0) {

        CustomerServiceFragment myFragment = new CustomerServiceFragment();
        Bundle data = new Bundle();
        data.putInt("current_page", arg0);

        try {
            if(jsonArray != null) {
                JSONObject jsonObject = jsonArray.getJSONObject(arg0);
                byte[] imgBytes = Base64.decode(jsonObject.getString("imageBytes").getBytes(), Base64.DEFAULT);
                data.putByteArray("image", imgBytes);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        myFragment.setArguments(data);
        return myFragment;
    }

    /** Returns the number of pages */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
