package com.example.darqwski.developerdiary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.darqwski.developerdiary.EditNoteActivity.receivedNoteCard;

/**
 * Created by Darqwski on 2018-11-24.
 */

public class AdapterNoteCard extends ArrayAdapter<NoteCard> {

    ArrayList<NoteCard> items;
    Context context;
    int resource;
    public AdapterNoteCard(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public AdapterNoteCard(Context context, int resource, ArrayList<NoteCard> items) {
        super(context, resource, items);
        this.context=context;
        this.items= items;
        this.resource=resource;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {


        if(items.get(position).getFlag()==NoteCard.EMPTY_CARD)
            return getViewForEmptyCard(position, convertView, parent);
        View v = convertView;
            if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(resource, null);
        }
            if(v.findViewById(R.id.EmptyCardDay)!=null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(resource, null);
            }

        final NoteCard itemView = items.get(position);
        Calendar c = Calendar.getInstance();
        c.setTime(itemView.getDate());
        String month = context.getResources().getStringArray(R.array.month_names)[c.get(Calendar.MONTH)];
        ((TextView) v.findViewById(R.id.NoteCardTitle)).setText(itemView.getTitle());
        ((ImageView) v.findViewById(R.id.NoteCardButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receivedNoteCard=itemView;
               Intent intent= new Intent(getContext(),EditNoteActivity.class);
                intent.putExtra("seconds",itemView.getDateString());
                getContext().startActivity(intent);
            }
        });
        ((TextView) v.findViewById(R.id.NoteCardDateDay)).setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        ((TextView) v.findViewById(R.id.NoteCardDateMonth)).setText(month);

        switch(itemView.getHand()){
            case SuperUtilities.HAND_OK:
                ((ImageView) v.findViewById(R.id.NoteCardHand)).setImageDrawable(context.getResources().getDrawable(R.drawable.ok));
                break;
            case SuperUtilities.HAND_ANGER:
                ((ImageView) v.findViewById(R.id.NoteCardHand)).setImageDrawable(context.getResources().getDrawable(R.drawable.anger));
                break;
            case  SuperUtilities.HAND_BRAVO:
                ((ImageView) v.findViewById(R.id.NoteCardHand)).setImageDrawable(context.getResources().getDrawable(R.drawable.bravo));
                break;
            case  SuperUtilities.HAND_GTFO:
                ((ImageView) v.findViewById(R.id.NoteCardHand)).setImageDrawable(context.getResources().getDrawable(R.drawable.gtfo));
                break;
            case  SuperUtilities.HAND_NOTBAD:
                ((ImageView) v.findViewById(R.id.NoteCardHand)).setImageDrawable(context.getResources().getDrawable(R.drawable.notbad));
                break;
            case  SuperUtilities.HAND_NOTOK:
                ((ImageView) v.findViewById(R.id.NoteCardHand)).setImageDrawable(context.getResources().getDrawable(R.drawable.notok));
                break;
            case  SuperUtilities.HAND_PERFECT:
                ((ImageView) v.findViewById(R.id.NoteCardHand)).setImageDrawable(context.getResources().getDrawable(R.drawable.perfect));
                break;
            case  SuperUtilities.HAND_VICTORY:
                ((ImageView) v.findViewById(R.id.NoteCardHand)).setImageDrawable(context.getResources().getDrawable(R.drawable.victory));
                break;
            default:
                ((ImageView) v.findViewById(R.id.NoteCardHand)).setImageDrawable(context.getResources().getDrawable(R.drawable.ok));
                break;
        }

        GridView gridIcons = (GridView) v.findViewById(R.id.NoteGridIcons);
        AdapterNoteEvent noteEventAdapter = new AdapterNoteEvent(getContext(),R.layout.note_catd_event_layout,itemView.getEvents());
        gridIcons.setAdapter(noteEventAdapter);
        SuperUtilities.justifyListViewHeightBasedOnChildren(gridIcons);
        return v;
    }
    public View getViewForEmptyCard(final int position, final View convertView, final ViewGroup parent){
        View v = convertView;

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.empty_card_view, null);


        final NoteCard itemView = items.get(position);
        final Calendar c = Calendar.getInstance();
        c.setTime(itemView.getDate());
        String month = context.getResources().getStringArray(R.array.month_names)[c.get(Calendar.MONTH)];
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),AddNoteActivity.class);
                intent.putExtra("seconds",itemView.getDateString());
                getContext().startActivity(intent);

            }
        });
      if(((TextView) v.findViewById(R.id.EmptyCardDay))!=null)
          ((TextView) v.findViewById(R.id.EmptyCardDay)).setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        ((TextView) v.findViewById(R.id.EmptyCardMonth)).setText(month);
        return  v;
    }




}