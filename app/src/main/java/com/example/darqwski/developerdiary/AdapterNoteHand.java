package com.example.darqwski.developerdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.darqwski.developerdiary.SuperUtilities.selectedHand;

/**
 * Created by Darqwski on 2018-11-24.
 */

public class AdapterNoteHand extends ArrayAdapter<HandIcon> {

    ArrayList<HandIcon> items;
    int resource;
    public AdapterNoteHand(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public AdapterNoteHand(Context context, int resource, ArrayList<HandIcon> items) {
        super(context, resource, items);
        this.items= items;
        this.resource=resource;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(resource, null);

        }
        final HandIcon value = items.get(position);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(200,250);
        v.setLayoutParams(layoutParams);
        ((TextView)v.findViewById(R.id.EventLayoutText)).setText(value.getTitle());
        ((ImageView)v.findViewById(R.id.EventLayoutImage)).setImageDrawable(value.getIcon());
        v.findViewById(R.id.EventLayoutImage).setBackground(value.getBackground());

        if(selectedHand!=value.getIconId())
                v.setBackground(getContext().getDrawable(R.drawable.border_radius_add_events));
            else
                v.setBackground(getContext().getDrawable(R.drawable.border_radius_minus_events));
            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(selectedHand!=0){
                        for(int i =0;i<((GridView)(view.getParent())).getChildCount();i++)
                            ((GridView)(view.getParent()))
                                    .getChildAt(i).setBackground(getContext().getDrawable(R.drawable.border_radius_add_events));
                        if(selectedHand!=value.getIconId()){
                            view.setBackground(getContext().getDrawable(R.drawable.border_radius_minus_events));
                            selectedHand=value.getIconId();
                        }
                        else{
                            selectedHand=0;
                        }

                    }
                    else{
                        selectedHand=value.getIconId();
                        view.setBackground(getContext().getDrawable(R.drawable.border_radius_minus_events));
                    }
                    return false;
                }
            });


      /*  imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(8, 8, 8, 8); // why not be explicit about the padding while we're at it*/
        //  String p = getItem(position);
        final int pos = position;

        return v;
    }




}