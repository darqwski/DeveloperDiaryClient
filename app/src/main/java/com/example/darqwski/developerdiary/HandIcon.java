package com.example.darqwski.developerdiary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import java.util.ArrayList;

/**
 * Created by Darqwski on 2018-11-25.
 */

public class HandIcon {
    private Drawable icon;
    private String title;
    private int iconId;
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    private Drawable background;

    public HandIcon(Drawable icon, String title, int iconId, Drawable background) {
        this.icon = icon;
        this.title = title;
        this.iconId = iconId;
        this.background=background;
        this.color=((GradientDrawable)background).getColor().getDefaultColor();
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }


    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ArrayList<HandIcon> getHandIcons(Context context){
        ArrayList<HandIcon> handIcons = new ArrayList<>();
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.perfect),"Perfekcyjnie",SuperUtilities.HAND_PERFECT,
                context.getResources().getDrawable(R.drawable.hand_background_green)));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.bravo),"Ponad przeciętnie",SuperUtilities.HAND_BRAVO,
                context.getResources().getDrawable(R.drawable.hand_background_green)));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.ok),"Nawet dobrze",SuperUtilities.HAND_OK,
                context.getResources().getDrawable(R.drawable.hand_background_blue_light)));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.notbad),"Prawidłowo",SuperUtilities.HAND_NOTBAD,
                context.getResources().getDrawable(R.drawable.hand_background_blue_light)));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.notok),"Nie dobrze",SuperUtilities.HAND_NOTOK,
                context.getResources().getDrawable(R.drawable.hand_background_yellow)));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.anger),"Arrr",SuperUtilities.HAND_ANGER,
                context.getResources().getDrawable(R.drawable.hand_background_red)));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.gtfo),"A idź",SuperUtilities.HAND_GTFO,
                context.getResources().getDrawable(R.drawable.hand_background_red)));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.victory),"Przetrwane",SuperUtilities.HAND_VICTORY,
                context.getResources().getDrawable(R.drawable.hand_background_yellow)));
        return handIcons;
    }
    public static HandIcon getIcon(ArrayList<HandIcon> arrayList,int iconId){
        for(int i=0;i<arrayList.size();i++)
            if(arrayList.get(i).getIconId()==iconId)
                return arrayList.get(i);
        return null;
    }

}

