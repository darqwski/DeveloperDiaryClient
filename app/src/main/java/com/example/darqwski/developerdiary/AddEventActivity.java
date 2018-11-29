package com.example.darqwski.developerdiary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static com.example.darqwski.developerdiary.SuperUtilities.serverAddres;

public class AddEventActivity extends AppCompatActivity {
    public static String choosenIcon="-1";
    public static int listViewPos=-1;
    public static int gridViewPos=-1;
    Context context ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        context=this;
        RequestProperties requestProperties = new RequestProperties().prepareGetConnection().setRequestAction("get_all_icons");
        new RequestCaller(context,requestProperties).execute(serverAddres);
        findViewById(R.id.addEventButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestProperties getLists = new RequestProperties()
                        .prepareSendConnection()
                        .setRequestAction("add_event")
                        .addBody("icon",choosenIcon)
                        .addBody("title",((EditText)findViewById(R.id.AddEventEditText)).getText().toString())
                        ;
                new RequestCaller(context,getLists).execute(serverAddres);

            }
        });
    }
}
