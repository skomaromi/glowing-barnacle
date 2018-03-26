package com.github.skomaromi.inspiringpeople;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;


public class ListAdapter extends ArrayAdapter {
    private final Activity context;
    private final InspiringPerson[] ipArray;

    public ListAdapter(Activity _context, InspiringPerson[] _ip) {
        // gonna bite the bullet and try this without the third argument
        super(_context, R.layout.activity_main_lvmain_row, _ip);
        context = _context;
        ipArray = _ip;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(
                R.layout.activity_main_lvmain_row,
                null,
                true);

        ImageView ivRowImage = rowView.findViewById(R.id.ivRowImage);
        TextView tvRowName = rowView.findViewById(R.id.tvRowName),
                 tvRowLifespan = rowView.findViewById(R.id.tvRowLifespan),
                 tvRowBio = rowView.findViewById(R.id.tvRowBio);

        ivRowImage.setImageDrawable(ipArray[position].getmImage());
        tvRowName.setText(ipArray[position].getmName());
        tvRowLifespan.setText(ipArray[position].getmLifespan());
        tvRowBio.setText(ipArray[position].getmBio());

        return rowView;
    }

}
