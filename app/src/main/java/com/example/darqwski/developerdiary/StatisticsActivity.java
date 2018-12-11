package com.example.darqwski.developerdiary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class StatisticsActivity extends AppCompatActivity {
    Context context;
    int actMonth;
    int actYear;
    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        c = Calendar.getInstance();
        c.setTime(new Date());
        actMonth=c.get(Calendar.MONTH);
        actYear=c.get(Calendar.YEAR);
        setContentView(R.layout.activity_statistics);
        RequestProperties requestProperties = new RequestProperties().prepareGetConnection()
                .setRequestAction("get_notes_from_month");
        requestProperties.setViewControllerFunction("get_statistics");
        requestProperties
                .addBody("month",String.valueOf(actMonth+1)).addBody("year",String.valueOf(actYear));
            new RequestCaller(context,requestProperties).execute(SuperUtilities.serverAddres);

    }
}
