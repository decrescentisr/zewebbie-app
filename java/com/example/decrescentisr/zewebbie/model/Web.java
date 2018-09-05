package com.example.decrescentisr.zewebbie.model;

/**
 * Created by decrescentisr on 7/25/2017.
 */

public class Web {

    private String mWebName;
    private int mImageResourceId;

    public Web(String webName, int imageResourceId){
        mWebName = webName;
        mImageResourceId = imageResourceId;
    }

    public String getWebName() {
        return mWebName;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
}
