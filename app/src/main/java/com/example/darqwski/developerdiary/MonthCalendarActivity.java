package com.example.darqwski.developerdiary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static com.example.darqwski.developerdiary.SuperUtilities.serverAddres;

public class MonthCalendarActivity extends AppCompatActivity {
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_calendar);
        context=this;
        RequestProperties requestProperties=new RequestProperties();
        requestProperties.prepareGetConnection().setRequestAction("get_notes_from_month").addBody("month","11");
        new RequestCaller(context,requestProperties).execute(serverAddres);
    }
}
