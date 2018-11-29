package com.example.darqwski.developerdiary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.darqwski.developerdiary.AddEventActivity.choosenIcon;
import static com.example.darqwski.developerdiary.AddEventActivity.gridViewPos;
import static com.example.darqwski.developerdiary.AddEventActivity.listViewPos;

/**
 * Created by Darqwski on 2018-11-24.
 */

public class AdapterNoteEventAdd extends ArrayAdapter<NoteEvent> {

    ArrayList<NoteEvent> items;
    int resource;
    public AdapterNoteEventAdd(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public AdapterNoteEventAdd(Context context, int resource, ArrayList<NoteEvent> items) {
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
        ((ImageView)v.findViewById(R.id.EventLayoutImage)).setImageBitmap(value.getIcon());

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(170,150);
            v.setLayoutParams(layoutParams);
            v.setBackground(getContext().getDrawable(R.drawable.border_radius_add_events));
            if(choosenIcon==value.getIconID()){
                v.setBackground(getContext().getDrawable(R.drawable.border_radius_minus_events));

            }
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ListView mainListView = ((ListView)(view.getParent().getParent().getParent()));
                    Log.wtf("Act Icon choosen",value.getTitle());

                    if(AddEventActivity.choosenIcon!="-1"){

                        Log.wtf(String.valueOf(listViewPos),String.valueOf(gridViewPos));

                        if(choosenIcon!=value.getIconID())
                            view.setBackground(getContext().getDrawable(R.drawable.border_radius_minus_events));
                    }
                    else{
                        view.setBackground(getContext().getDrawable(R.drawable.border_radius_minus_events));
                    }

                    if(choosenIcon!=value.getIconID()){
                        AddEventActivity.choosenIcon=value.getIconID();
                        ((ArrayAdapter) mainListView.getAdapter()).notifyDataSetChanged();
                    }
                    else {
                        AddEventActivity.choosenIcon="-1";
                        ((ArrayAdapter) mainListView.getAdapter()).notifyDataSetChanged();

                    }
                }
            });

        final int pos = position;

        return v;
    }





}