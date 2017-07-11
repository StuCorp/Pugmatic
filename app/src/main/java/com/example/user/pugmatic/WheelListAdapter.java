package com.example.user.pugmatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by user on 11/07/2017.
 */

public class WheelListAdapter extends ArrayAdapter {

    public WheelListAdapter(Context context, ArrayList<Symbol> wheel){
        //0 because we don't want to use a default resource
        super(context, 0, wheel);
    }

    //responsible for creating views
    @Override
    public View getView(int position, View listItemView, ViewGroup parent){
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.wheel1_item, parent, false);
        }

        Symbol currentSymbol =  (Symbol) getItem(position);

        ImageView imageView = listItemView.findViewById(R.id.symbol_image);
        imageView.setImageResource(currentSymbol.getImage());

//        listItemView.setTag(currentSymbol);

        return listItemView;
    }



}
