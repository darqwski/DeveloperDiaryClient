package com.example.darqwski.developerdiary;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.darqwski.developerdiary.SuperUtilities.getMonthLength;
import static com.example.darqwski.developerdiary.SuperUtilities.isSameDay;

/**
 * Created by Darqwski on 2018-11-30.
 */

public class MonthCalculations {
    private ArrayList<NoteCard> noteCards;
    private Context context;
    private ArrayList<NoteEvent> noteEvents;
    private String month, year;
    public MonthCalculations(ArrayList<NoteCard> noteCards, Context context,String month,String year) {
        this.noteCards = noteCards;
        this.context=context;
        this.month=String.valueOf(Integer.valueOf(month)-1);
        this.year=year;
    }

    public ArrayList<NoteCard> getNoteCards() {
        return noteCards;
    }

    public void setNoteCards(ArrayList<NoteCard> noteCards) {
        this.noteCards = noteCards;
    }
    public int[] getDayColors(){
        ArrayList<HandIcon> handIcons=HandIcon.getHandIcons(context);
        HashMap<Integer,Integer> colors=new HashMap<>();
        for(int i=0;i<handIcons.size();i++){
            Integer tempColor =  handIcons.get(i).getColor();
            if(!colors.containsKey(tempColor))
                colors.put(tempColor,0);
        }
        for(int i=0;i<noteCards.size();i++){
            Integer tempColor = HandIcon.getIcon(handIcons,noteCards.get(i).getHand()).getColor();
            colors.put(tempColor,colors.get(tempColor)+1);
        }

        int foundColors[] = new int[4];
        foundColors[0]=colors.get(((GradientDrawable)context.getDrawable(R.drawable.hand_background_red)).getColor().getDefaultColor());
        foundColors[1]=colors.get(((GradientDrawable)context.getDrawable(R.drawable.hand_background_yellow)).getColor().getDefaultColor());
        foundColors[2]=colors.get(((GradientDrawable)context.getDrawable(R.drawable.hand_background_blue_light)).getColor().getDefaultColor());
        foundColors[3]=colors.get(((GradientDrawable)context.getDrawable(R.drawable.hand_background_green)).getColor().getDefaultColor());
        return foundColors;
    }
    public  ArrayList<NoteCard> getFullArray(){
        boolean emptyMonth=false;
        if(noteCards.size()==0)
            emptyMonth=true;
        Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR,Integer.valueOf(year));
            c.set(Calendar.MONTH,Integer.valueOf(month));
            c.set(Calendar.DATE,1);

        int startWeekDay=(c.get(Calendar.DAY_OF_WEEK)+5)%7;
        ArrayList<NoteCard> fullList= new ArrayList<>();
        int listPointer=emptyMonth?0:noteCards.size()-1;
        int repeats= startWeekDay+getMonthLength(c.get(Calendar.MONTH));
        repeats+=(7-repeats%7);
        c.add(Calendar.DAY_OF_MONTH,-startWeekDay);
        Log.wtf("Day",String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        if(!emptyMonth)
        for(int i=0;i<repeats;i++){
            if(isSameDay(c.getTime(),noteCards.get(listPointer).getDate())){
                fullList.add(noteCards.get(listPointer));
                if(listPointer!=0)listPointer--;
            }
            else{
                fullList.add(NoteCard.creatingMissingDayInfo(c.getTime()));
            }
            c.add(Calendar.DAY_OF_MONTH,1);
        }
        else{
            for(int i=0;i<repeats;i++){
                fullList.add(NoteCard.creatingMissingDayInfo(c.getTime()));
                c.add(Calendar.DAY_OF_MONTH,1);

            }
        }
        return fullList;
    }
    public ArrayList<NoteEvent> getAllNoteEvents(){
        ArrayList<NoteEvent>noteEvents = new ArrayList<>();
        for(NoteCard i: noteCards)
            for(NoteEvent j:i.getEvents())
                noteEvents.add(j);
        return noteEvents;
    }
    public int getNoteEventFrequence(NoteEvent i ){
        int counter=0;
        for(NoteEvent j:noteEvents){
            if(NoteEvent.compareNoteEvents(j,i))
                counter++;
        }
        return counter;
    }
    public ArrayList<NoteEvent> getMostFrequentEvent(){
        int currMax=0;
        ArrayList<NoteEvent>noteEvents=new ArrayList<>();
       HashMap<NoteEvent,Integer> hashMap = new HashMap<>();
        for(NoteEvent i: this.noteEvents){
            if(hashMap.containsKey(i)){
                hashMap.put(i,hashMap.get(i)+1);
            }
            else{
                hashMap.put(i,0);

            }
        }
        for(int i=0;i<4;i++){
            Map.Entry<NoteEvent, Integer> maxEntry =null;
            for ( Map.Entry<NoteEvent, Integer> entry : hashMap.entrySet())
            {
                if (maxEntry == null)
                    maxEntry=entry;
                if ( entry.getValue() >= maxEntry.getValue()) {
                    maxEntry = entry;
                }
            }
            noteEvents.add(maxEntry.getKey());
            hashMap.remove(maxEntry.getKey());
        }

        return noteEvents;
    }
     public int getValueFromHand(int handIcon){

         switch (handIcon){
             case SuperUtilities.HAND_VICTORY:
                 return 2;
             case SuperUtilities.HAND_PERFECT:
                 return 4;
             case SuperUtilities.HAND_BRAVO:
                 return 3;
             case SuperUtilities.HAND_OK:
                 return 3;
             case SuperUtilities.HAND_NOTOK:
                 return 2;
             case SuperUtilities.HAND_ANGER:
                 return 1;
             case SuperUtilities.HAND_GTFO:
                 return 1;

             default:
                 return 0;
         }

    }
}
