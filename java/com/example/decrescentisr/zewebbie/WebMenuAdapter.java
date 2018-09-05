package com.example.decrescentisr.zewebbie;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.decrescentisr.zewebbie.model.Web;

import java.util.ArrayList;

/**
 * Created by decrescentisr on 7/25/2017.
 */

public class WebMenuAdapter extends ArrayAdapter<Web>{

    private Context mContext;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public WebMenuAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = context;
        this.data = data;

    }

    static class ViewHolder{
        TextView imageTitle;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){


        ViewHolder holder = null;
        Web currentWeb = getItem(position);


        if (convertView == null) {

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = convertView.findViewById(R.id.web_grid_name);
            holder.image = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageTitle.setText(currentWeb.getWebName());
        holder.image.setImageResource(currentWeb.getImageResourceId());
        return convertView;
    }

}
