package com.example.darqwski.developerdiary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import static com.example.darqwski.developerdiary.SuperUtilities.serverAddres;

public class MonthCalendarActivity extends AppCompatActivity {
Context context;
    int actMonth;
    int actYear;
    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_calendar);
        context=this;
        c = Calendar.getInstance();
        c.setTime(new Date());
        actMonth=c.get(Calendar.MONTH);
        actYear=c.get(Calendar.YEAR);
        findViewById(R.id.MonthCalendarLeftArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.add(Calendar.MONTH,-1);
                actMonth=c.get(Calendar.MONTH);
                actYear=c.get(Calendar.YEAR);
                refreshMonthView();
            }
        });
        findViewById(R.id.MonthCalendarRightArrowArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.add(Calendar.MONTH,1);
                actMonth=c.get(Calendar.MONTH);
                actYear=c.get(Calendar.YEAR);
                refreshMonthView();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshMonthView();
    }
    private void refreshMonthView(){
        RequestProperties requestProperties=new RequestProperties();

        ((TextView)findViewById(R.id.MonthCalendarMonthName)).setText(getResources().getStringArray(R.array.month_names)[actMonth]+actYear);
        requestProperties.prepareGetConnection().setRequestAction("get_notes_from_month")
                .addBody("month",String.valueOf(actMonth+1)).addBody("year",String.valueOf(actYear));
        new RequestCaller(context,requestProperties).execute(serverAddres);
    }
}
