package com.example.darqwski.developerdiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Date;

import static com.example.darqwski.developerdiary.HandIcon.getHandIcons;
import static com.example.darqwski.developerdiary.SuperUtilities.addedNoteEvents;
import static com.example.darqwski.developerdiary.SuperUtilities.getDateFromString;
import static com.example.darqwski.developerdiary.SuperUtilities.getDateString;
import static com.example.darqwski.developerdiary.SuperUtilities.selectedHand;
import static com.example.darqwski.developerdiary.SuperUtilities.serverAddres;

public class AddNoteActivity extends AppCompatActivity {
    Context context;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        addedNoteEvents=new ArrayList<>();
        context =this;
        selectedHand=0;
        Intent intent=getIntent();
        if(intent.getStringExtra("seconds")==null)
            date=new Date();
        else
            date = getDateFromString(intent.getStringExtra("seconds"));

        RequestProperties requestProperties = new RequestProperties().prepareGetConnection().setRequestAction("get_all_events");
        new RequestCaller(context,requestProperties).execute(serverAddres);
        GridView gridView = (GridView) findViewById(R.id.editNoteHandGrid);
        ArrayList<HandIcon> handIcons = getHandIcons(context);
        gridView.setAdapter(new AdapterNoteHand(context,R.layout.add_note_event_layout,handIcons));
        SuperUtilities.justifyListViewHeightBasedOnChildren(gridView, 4);
        findViewById(R.id.addNoteSendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedHand==0){
                    SuperUtilities.snackbar(context,"Dzień nie został oceniony");
                    return;
                }
                RequestProperties getLists = new RequestProperties()
                        .prepareSendConnection()
                        .setRequestAction("add_note")
                        ;
                getLists.addBody("title",((EditText)findViewById(R.id.editNoteTextInput)).getText().toString());
                getLists.addBody("date",getDateString(date));
                getLists.addBody("hand",String.valueOf(selectedHand));
                for(int i=0;i< addedNoteEvents.size();i++)
                    getLists.addBody("events[]",addedNoteEvents.get(i).getID().toString());

                new RequestCaller(context,getLists).execute(serverAddres);

            }
        });
    }

}