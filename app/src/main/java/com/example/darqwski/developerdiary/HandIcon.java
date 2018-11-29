package com.example.darqwski.developerdiary;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by Darqwski on 2018-11-25.
 */

public class HandIcon {
    private Drawable icon;
    private String title;
    private int iconId;

    public HandIcon(Drawable icon, String title, int iconId) {
        this.icon = icon;
        this.title = title;
        this.iconId = iconId;
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
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.perfect),"Perfekcyjnie",SuperUtilities.HAND_PERFECT));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.bravo),"Ponad przeciętnie",SuperUtilities.HAND_BRAVO));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.ok),"Nawet dobrze",SuperUtilities.HAND_OK));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.notbad),"Prawidłowo",SuperUtilities.HAND_NOTBAD));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.notok),"Nie dobrze",SuperUtilities.HAND_NOTOK));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.anger),"Arrr",SuperUtilities.HAND_ANGER));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.gtfo),"A idź",SuperUtilities.HAND_GTFO));
        handIcons.add(new HandIcon(context.getResources().getDrawable(R.drawable.victory),"Przetrwane",SuperUtilities.HAND_VICTORY));
        return handIcons;
    }
}

