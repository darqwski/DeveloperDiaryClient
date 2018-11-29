package com.example.darqwski.developerdiary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

/**
 * Created by Darqwski on 2018-11-24.
 */

public class NoteEvent {
    private Bitmap icon;
    private String title;
    private String description;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;
    private String ID;

    public String getIconID() {
        return iconID;
    }

    public void setIconID(String iconID) {
        this.iconID = iconID;
    }

    private String iconID;

    public NoteEvent(Bitmap icon, String title , String ID) {
        this.icon = icon;
        this.title = title;
        this.ID = ID;
    }
    public NoteEvent(String title, String description, String icon, String id) {
        this.icon = Base64ToBitmap(icon);
        this.title = title;
        this.description = description;
        this.ID = id;
    }
    public NoteEvent(String title, String description, String icon, String id,String iconID) {
        this.icon = Base64ToBitmap(icon);
        this.title = title;
        this.description = description;
        this.ID = id;
        this.iconID=iconID;
    }
    public NoteEvent(String title, String description, String icon, String id,String iconID,String category) {
        this.icon = Base64ToBitmap(icon);
        this.title = title;
        this.description = description;
        this.ID = id;
        this.iconID=iconID;
        this.category=category;
    }
    public NoteEvent( String title,String icon) {
        this.icon = Base64ToBitmap(icon);
        this.title = title;
    }


    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private static Bitmap Base64ToBitmap(String name)
    {
        try
        {
            String pureBase64Encoded = name.substring(name.indexOf(",")  + 1);
            byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedByte;

        }
        catch (Exception e)
        {
            Log.d("A",name);
            return null;
        }
    }
}
