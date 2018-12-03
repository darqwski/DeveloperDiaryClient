package com.example.darqwski.developerdiary;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.darqwski.developerdiary.SuperUtilities.snackbar;

/**
 * Created by Darqwski on 2018-11-28.
 */

public class ViewController{
    public static void getNotesFromNumber(Context context, String result){
        final ArrayList<NoteCard> list = new ArrayList<>();
        AdapterNoteCard listAdapter;
        ListView expListView;
        try {
            JSONObject jObject = new JSONObject(result);
            JSONArray jsonArray=jObject.getJSONArray("MainArray");
            for(int i = 0;i<jsonArray.length();i++) {
                JSONObject actObject = new JSONObject(jsonArray.getString(i));
                NoteCard noteCard = new NoteCard();
                noteCard.setTitle(actObject.getString("Title"));
                noteCard.setDate(actObject.getString("Date"));
                noteCard.setID(actObject.getString("ID"));
                noteCard.setHand(Integer.valueOf(actObject.getString("Hand")));
                JSONArray actEvents = actObject.getJSONArray("Events");
                ArrayList<NoteEvent> eventList = new ArrayList<>();
                for(int j = 0;j<actEvents.length();j++){
                    JSONObject act = new JSONObject(actEvents.getString(j));
                    eventList.add(new NoteEvent(act.getString("Title"),act.getString("Description"),act.getString("Image"),act.getString("ID")));
                }
                noteCard.setEvents(eventList);

                if(!list.isEmpty()){
                    int missingDays = (int)((list.get(list.size()-1).getDate().getTime()-(list.get(list.size()-1).getDate().getTime()%(1000*3600*24)))
                            -(noteCard.getDate().getTime()-(noteCard.getDate().getTime()%(1000*3600*24))))/(1000*3600*24);
                    for(int j=1;j<missingDays;j++){
                        Date newDate=new Date(list.get(list.size()-1).getDate().getTime()-(1000*3600*24));
                        list.add(NoteCard.creatingMissingDayInfo(newDate));
                    }
                }

                list.add( noteCard);
            }

            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {


                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            Log.wtf("RESULT",result);
        }

        expListView =(ListView) ((Activity)context).findViewById(R.id.mainListView);
        listAdapter = new AdapterNoteCard(context, R.layout.note_card_view,list);
        expListView.setAdapter(listAdapter);

    }

    public static void getNotesFromMonth(Context context, String result,String monthNumber,String yearNumber){
        final ArrayList<NoteCard> list = new ArrayList<>();
        int doneActions = 0;

        try {
            JSONObject jObject = new JSONObject(result);
            JSONArray jsonArray=jObject.getJSONArray("MainArray");
            for(int i = 0;i<jsonArray.length();i++) {
                JSONObject actObject = new JSONObject(jsonArray.getString(i));
                NoteCard noteCard = new NoteCard();
                noteCard.setID(actObject.getString("ID"));
                noteCard.setHand(Integer.valueOf(actObject.getString("Hand")));
                noteCard.setDate(actObject.getString("Date"));
                noteCard.setEventsCount(Integer.valueOf(actObject.getString("Events")));
                doneActions+=noteCard.getEventsCount();
                list.add( noteCard);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.wtf("RESULT",result);
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        Log.wtf("RESULT",result);

        MonthCalculations monthCalculations = new MonthCalculations(list,context,monthNumber,yearNumber);
        int percentage = (((list.size()*100)/(calendar.get(Calendar.DAY_OF_MONTH))));
        GridView gridView =(GridView) ((Activity)context).findViewById(R.id.MonthCalendarGrid);
        gridView.setAdapter(new AdapterCalendar(context,R.layout.single_date_view,monthCalculations.getFullArray()));
        SuperUtilities.justifyListViewHeightBasedOnChildren(gridView,7);

        ((TextView)((Activity)context).findViewById(R.id.MonthCalendarEmptyDaysNumber)).setText(String.valueOf(percentage)+"%");
        ((TextView)((Activity)context).findViewById(R.id.MonthCalendarDoneActionsNumber)).setText(String.valueOf(doneActions));
        int colors[]=monthCalculations.getDayColors();
        ((TextView)((Activity)context).findViewById(R.id.MonthCalendarDayRed)).setText(String.valueOf(colors[0]));
        ((TextView)((Activity)context).findViewById(R.id.MonthCalendarDayYellow)).setText(String.valueOf(colors[1]));
        ((TextView)((Activity)context).findViewById(R.id.MonthCalendarDayBlue)).setText(String.valueOf(colors[2]));
        ((TextView)((Activity)context).findViewById(R.id.MonthCalendarDayGreen)).setText(String.valueOf(colors[3]));


       // listAdapter = new AdapterNoteCard(context, R.layout.note_card_view,list);
       // expListView.setAdapter(listAdapter);

    }

    public static void getAllEvents(Context context,String result) {
        final ArrayList<NoteEvent> list = new ArrayList<>();
        ArrayList<String> textList = new ArrayList<>();
        AdapterNoteEvent listAdapter;
        GridView gridView;
        try {
            JSONObject jObject = new JSONObject(result);
            JSONArray jsonArray=jObject.getJSONArray("MainArray");
            for(int i = 0;i<jsonArray.length();i++) {
                JSONObject actObject = new JSONObject(jsonArray.getString(i));
                NoteEvent noteCard = new NoteEvent(actObject.getString("Title"),"",actObject.getString("Image"),actObject.getString("ID"));
                list.add( noteCard);
            }
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {


                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            Log.wtf("RESULT",result);
        }
        list.add(new NoteEvent(null,"Dodaj","-1"));
        gridView =(GridView) ((Activity)context).findViewById(R.id.editNoteEventsGrid);
        listAdapter = new AdapterNoteEvent(context, R.layout.add_note_event_layout,list);
        gridView.setAdapter(listAdapter);
        SuperUtilities.justifyListViewHeightBasedOnChildren(gridView,5);
    }
    public static void getAllIconsView(Context context,String result){
        ArrayList<NoteEvent> list = new ArrayList<>();
        try {
            JSONObject jObject = new JSONObject(result);
            JSONArray jsonArray=jObject.getJSONArray("MainArray");
            for(int i = 0;i<jsonArray.length();i++) {
                JSONObject actObject = new JSONObject(jsonArray.getString(i));
                NoteEvent noteCard = new NoteEvent("","",actObject.getString("Image"),"",actObject.getString("ID"),actObject.getString("Category"));
                list.add( noteCard);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.wtf("RESULT",result);
        }
        final ArrayList<IconContainer> separedIcons=separeteIconsByCategory(list);
        ListView listView =(ListView) ((Activity)context).findViewById(R.id.AddEventGrid);
        AdapterContainer listAdapter = new AdapterContainer(context, R.layout.note_event_add_container,separedIcons);
        listView.setAdapter(listAdapter);
        SuperUtilities.justifyListViewHeightBasedOnChildren(listView);
    }


    public static ArrayList<IconContainer>  separeteIconsByCategory( ArrayList<NoteEvent> list){

        String tempCategory="";
        IconContainer tempIconContainer=new IconContainer("");
        ArrayList<IconContainer> separedIcons=new ArrayList<>();

        for(int i=0;i<list.size();i++){
            if(list.get(i).getCategory().equals(tempCategory)){
                tempIconContainer.addEvent(list.get(i));
            }
            else{
                if(tempIconContainer.getEvents().size()!=0) separedIcons.add(tempIconContainer);
                tempIconContainer = new IconContainer(list.get(i).getCategory());
                tempIconContainer.addEvent(new NoteEvent(null,"Dodaj","-1"));

                tempIconContainer.addEvent(list.get(i));
                tempCategory=list.get(i).getCategory();
            }

        }
        separedIcons.add(tempIconContainer);
        return separedIcons;
    }
    public static void getCommunicate(Context context,String result){
        String message;
        try {
            JSONObject jsonObject = new JSONObject(result);
            jsonObject.getString("MainArray");
            switch (jsonObject.getString("MainArray")){
                default:
                    message=context.getString(R.string.server_ok);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            message=context.getString(R.string.server_error);

        }
        snackbar(context,message);
    }

}
