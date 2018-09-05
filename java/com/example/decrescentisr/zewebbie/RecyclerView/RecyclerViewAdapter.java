package com.example.decrescentisr.zewebbie.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.decrescentisr.zewebbie.R;

/**
 * Created by decrescentisr on 8/7/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private String[] mDataset;

    //Provide a reference to the views for each data item
    //Complex data items may need more than one view per item, and
    //you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //inflating recycler item view

        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    //Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(String[] webDataset){
        mDataset = webDataset;
    }

    //Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_websites_recycler, parent, false);
        //set the view's size, margins, paddings and layout parameters

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
