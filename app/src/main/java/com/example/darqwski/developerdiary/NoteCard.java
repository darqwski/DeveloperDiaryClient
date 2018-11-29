package com.example.darqwski.developerdiary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Darqwski on 2018-11-24.
 */

public class NoteCard {
    public static final int EMPTY_CARD=1;
    public static final int NORMAL_CARD=0;

    private String title;
    private Date date;
    private ArrayList <NoteEvent> events;
    private String ID="";
    private int hand;
    private int flag;
    public int getHand() {
        return hand;
    }
    public void setHand(int hand) {
        this.hand = hand;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getFlag() {return flag;}
    public void setFlag(int flag) {this.flag = flag;}
    public void setDate(Date date){
        this.date=date;
    }
    public void setDate(String date) {

        String dateString = date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.date = new Date();
        try {
            this.date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public Date getDate() {
        return date;
    }
    public String getDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(this.date);
        return dateTime;

    }
    public ArrayList<NoteEvent> getEvents() {
        return events;
    }
    public String getTitle() {
        return title;
    }
    public void setEvents(ArrayList<NoteEvent> events) {this.events = events;}

    public NoteCard(String title, String date, ArrayList<NoteEvent> events) {
        this.flag=NORMAL_CARD;
        this.title = title;
        String dateString = date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.date = new Date();
        try {
            this.date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.events = events;
    }
    public NoteCard() {
        this.flag=NORMAL_CARD;
    }
    public static NoteCard creatingMissingDayInfo(Date date){
        NoteCard noteCard = new NoteCard("EmptyDate","2018-11-28 19:15:25",null);
        noteCard.setDate(date);
        noteCard.setFlag(EMPTY_CARD);
        return noteCard;
}

}
