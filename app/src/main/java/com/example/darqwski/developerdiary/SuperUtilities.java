package com.example.darqwski.developerdiary;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Darqwski on 2018-11-24.
 */

public class SuperUtilities {
    public static String serverAddres = "http://darqwski.cba.pl/DeveloperDiary/MainRequester.php";
    final public static int HAND_OK = 1;
    final public static int HAND_ANGER= 2;
    final public static int HAND_BRAVO= 3;
    final public static int HAND_GTFO= 4;
    final public static int HAND_NOTBAD=5;
    final public static int HAND_NOTOK= 6;
    final public static int HAND_PERFECT=7;
    final public static int HAND_VICTORY=8;
    public static int selectedHand=0;
    public static ArrayList<NoteEvent> addedNoteEvents = new ArrayList<>();

    public static GridView justifyListViewHeightBasedOnChildren (GridView gridView) {

        ListAdapter adapter = gridView.getAdapter();

        if (adapter == null) {
            return null;
        }
        ViewGroup vg = gridView;
        int totalHeight = 15;
        for (int i = 0; i < adapter.getCount(); i+=2) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = gridView.getLayoutParams();
        par.height = totalHeight ;//+ (gridView.getHeight() * (adapter.getCount() - 1));
        gridView.setLayoutParams(par);
        gridView.requestLayout();
        return gridView;
    }
    public static ListView justifyListViewHeightBasedOnChildren (ListView gridView) {

        ListAdapter adapter = gridView.getAdapter();

        if (adapter == null) {
            return null;
        }
        ViewGroup vg = gridView;
        int totalHeight = 15;
        for (int i = 0; i < adapter.getCount(); i+=2) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = gridView.getLayoutParams();
        par.height = totalHeight ;//+ (gridView.getHeight() * (adapter.getCount() - 1));
        gridView.setLayoutParams(par);
        gridView.requestLayout();
        return gridView;
    }
    public static GridView justifyListViewHeightBasedOnChildren (GridView gridView,int divider) {

        ListAdapter adapter = gridView.getAdapter();

        if (adapter == null) {
            return null;
        }
        ViewGroup vg = gridView;
        int totalHeight = 10;
        int iterations =adapter.getCount();
        if(iterations%divider!=0)iterations++;
        for (int i = 0; i < iterations; i+=divider) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = gridView.getLayoutParams();
        par.height = totalHeight ;//+ (gridView.getHeight() * (adapter.getCount() - 1));
        par.height += (iterations/divider)*6;
        gridView.setLayoutParams(par);
        gridView.requestLayout();
        return gridView;
    }
    public String buildData(ArrayList<String>dataNames,ArrayList<String>dataValues){
        String finalValue = "";

                for(int i=0;i<dataValues.size();i++){
                    finalValue += URLEncoder.encode(dataNames.get(i));
                    finalValue+="=";
                    finalValue += URLEncoder.encode(dataValues.get(i));
                    if(i+1<dataValues.size())finalValue+="&";
                }
                return finalValue;




    }
    public static String parseNoteToUrl(NoteCard noteCard)  {
        String finalValue = "";
        finalValue+="title=";
        finalValue+=URLEncoder.encode(noteCard.getTitle());
        if(noteCard.getID()!=""){
            finalValue+="&ID=";
            finalValue+=noteCard.getID();
        }
        finalValue+="&date=";
        finalValue+=URLEncoder.encode(noteCard.getDateString());
        finalValue+="&hand=";
        finalValue+=String.valueOf(noteCard.getHand());

        for(int i=0;i<noteCard.getEvents().size();i++){
            finalValue +="&events[]=";
            finalValue += URLEncoder.encode(noteCard.getEvents().get(i).getID().toString());
        }
        return finalValue;
    }
    public static Date getDateFromString(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }
    public static void snackbar(Context context,String message){
        Snackbar.make(((Activity)context).getWindow().getDecorView().getRootView(),message, Snackbar.LENGTH_SHORT).show();

    }
    public static String getDateString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(date);
        return dateTime;

    }
    public static int getMonthLength(int month){
        ArrayList<Integer> lengths= new ArrayList<>();
        lengths.add(31);
        lengths.add(28);
        lengths.add(31);
        lengths.add(30);
        lengths.add(31);
        lengths.add(30);
        lengths.add(31);
        lengths.add(31);
        lengths.add(30);
        lengths.add(31);
        lengths.add(30);
        lengths.add(31);
        return lengths.get(month);
    }
    public static boolean isSameDay(Date date1,Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        return sameDay;
    }
}
