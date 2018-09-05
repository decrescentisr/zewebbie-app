package com.example.decrescentisr.zewebbie;

/**
 * Created by decrescentisr on 7/25/2017.
 */

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;


import com.example.decrescentisr.zewebbie.IdlingResource.SimpleIdlingResource;
import com.example.decrescentisr.zewebbie.model.Web;


/**
 * Created by decrescentisr on 7/21/2017.
 */

public class ImageDownloader {

    private static final int DELAY_MILLIS = 3000;

    final static ArrayList<Web> mWeb = new ArrayList<>();

    interface DelayerCallback{
        void onDone(ArrayList<Web> webs);
    }

    static void downloadImage(Context context, final DelayerCallback callback,
                              @Nullable final SimpleIdlingResource idlingResource){

        if (idlingResource != null){
            idlingResource.setIdleState(false);
        }

        String text = context.getString(R.string.loading_msg);
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        mWeb.add(new Web(context.getString(R.string.website), R.drawable.advanced));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.onDone(mWeb);
                    if (idlingResource != null){
                        idlingResource.setIdleState(true);
                    }
                }
            }
        }, DELAY_MILLIS);

    }
}

