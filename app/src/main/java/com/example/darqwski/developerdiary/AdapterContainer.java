package com.example.darqwski.developerdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Darqwski on 2018-11-27.
 */

public class AdapterContainer extends ArrayAdapter<IconContainer> {

    ArrayList<IconContainer> items;
    int resource;
    public AdapterContainer(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public AdapterContainer(Context context, int resource, ArrayList<IconContainer> items) {
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
        final IconContainer value = items.get(position);
        ((TextView)v.findViewById(R.id.noteEventAddContainerTitle)).setText(value.getContainerName());
        GridView gridView = v.findViewById(R.id.noteEventAddContainerGrid);
        AdapterNoteEventAdd noteEventAddAdapter = new AdapterNoteEventAdd(getContext(),R.layout.add_note_event_layout,value.getEvents());
        gridView.setAdapter(noteEventAddAdapter);
        SuperUtilities.justifyListViewHeightBasedOnChildren(gridView,5);


      /*  imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(8, 8, 8, 8); // why not be explicit about the padding while we're at it*/
        //  String p = getItem(position);
        final int pos = position;

        return v;
    }




}
