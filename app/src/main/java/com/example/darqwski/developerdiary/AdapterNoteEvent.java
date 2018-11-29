package com.example.darqwski.developerdiary;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.darqwski.developerdiary.SuperUtilities.addedNoteEvents;

/**
 * Created by Darqwski on 2018-11-24.
 */

public class AdapterNoteEvent extends ArrayAdapter<NoteEvent> {

    ArrayList<NoteEvent> items;
    int resource;
    View.OnTouchListener onTouchListener = null;
    public AdapterNoteEvent(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public AdapterNoteEvent(Context context, int resource, ArrayList<NoteEvent> items) {
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
        final NoteEvent value = items.get(position);

        ((TextView)v.findViewById(R.id.EventLayoutText)).setText(value.getTitle());
        ((ImageView)v.findViewById(R.id.EventLayoutImage)).setImageBitmap(value.getIcon());
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        if(resource==R.layout.add_note_event_layout){

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(170,200);
            v.setLayoutParams(layoutParams);
            v.setBackground(getContext().getDrawable(R.drawable.border_radius_add_events));
            Log.wtf(getClass().getName(),value.getTitle());

            for(int i = 0; i< addedNoteEvents.size(); i++){
                if(addedNoteEvents.get(i).getTitle().equals(value.getTitle())){
                    addedNoteEvents.remove(i);
                    addedNoteEvents.add(value);
                    v.setBackground(getContext().getDrawable(R.drawable.border_radius_minus_events));

                }
            }

                v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(!addedNoteEvents.contains(value)){
                        view.setBackground(getContext().getDrawable(R.drawable.border_radius_minus_events));
                        addedNoteEvents.add(value);

                    }
                    else{
                        view.setBackground(getContext().getDrawable(R.drawable.border_radius_add_events));
                        addedNoteEvents.remove(value);

                    }
                    return false;
                }
            });
            if(value.getID().equals("-1")){
                ((ImageView)v.findViewById(R.id.EventLayoutImage)).setImageDrawable(getContext().getDrawable(R.drawable.baseline_add_black_48dp));
                v.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        Intent intent = new Intent(getContext(),AddEventActivity.class);
                        getContext().startActivity(intent);
                        return false;
                    }
                });
            }


        }
      /*  imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(8, 8, 8, 8); // why not be explicit about the padding while we're at it*/
        //  String p = getItem(position);
        final int pos = position;

        return v;
    }




}