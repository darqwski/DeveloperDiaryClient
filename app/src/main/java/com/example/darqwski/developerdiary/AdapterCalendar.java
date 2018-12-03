package com.example.darqwski.developerdiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Darqwski on 2018-11-29.
 */

public class AdapterCalendar extends ArrayAdapter<NoteCard> {
   public static int filledDaysCount;
   public static int doneActionsCount;
    ArrayList<NoteCard> items;
    int resource;
    public AdapterCalendar(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public AdapterCalendar(Context context, int resource, ArrayList<NoteCard> items) {
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
        final NoteCard itemView = items.get(position);
        final Calendar c = Calendar.getInstance();
        final Calendar middle = Calendar.getInstance();
        middle.setTime(items.get(items.size()/2).getDate());
        Date date = itemView.getDate();
        c.setTime(date);
        String day =String.valueOf( c.get(Calendar.DAY_OF_MONTH));
        ((TextView)v.findViewById(R.id.SingleDateText)).setText(day);
          HandIcon handIcon=(HandIcon.getIcon(HandIcon.getHandIcons(getContext()),itemView.getHand()));
           if(handIcon!=null){
               v.setBackground(handIcon.getBackground());
               String text="";
                    filledDaysCount++;
               for(int i=0;i<itemView.getEventsCount();i++){
                   text+=".";
                   doneActionsCount++;
               }
               ((TextView)v.findViewById(R.id.SingleDateEvents)).setText(text);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SuperUtilities.snackbar(getContext(),"Ten dzień został już uzupełniony");
                    }
                });
           }
           else if(c.get(Calendar.MONTH)==middle.get(Calendar.MONTH)){
               v.setBackground(getContext().getResources().getDrawable(R.drawable.hand_background_gray));
               ((TextView)v.findViewById(R.id.SingleDateEvents)).setText("");
               v.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent=new Intent(getContext(),AddNoteActivity.class);
                       intent.putExtra("seconds",itemView.getDateString());
                       getContext().startActivity(intent);
                   }
               });
           }
           else {
               v.setBackground(getContext().getResources().getDrawable(R.drawable.hand_background_white));
               ((TextView)v.findViewById(R.id.SingleDateText)).setTextColor(Color.argb(128,200,200,200));
               ((TextView)v.findViewById(R.id.SingleDateEvents)).setText("");


           }


        return v;
    }




}