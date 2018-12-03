package com.example.darqwski.developerdiary;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Darqwski on 2018-11-28.
 */

public class RequestCaller  extends AsyncTask<String,String,String> {

        RequestProperties props;
        Context context;
        int responseCode;
    RequestCaller(Context newContext, RequestProperties requestProperties){
        props=requestProperties;
        context=newContext;
        }
protected void onPreExecute(){

        }

@Override
protected String doInBackground(String... strings) {
        String urlString = strings[0];

        OutputStream out = null;
        try {

        URL url = new URL(strings[0]);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod(props.method);
        for(int i = 0;i<props.headerNames.size();i++)
        httpCon.setRequestProperty(props.headerNames.get(i),props.headerValues.get(i));
        if(props.timeOut!=0){
        httpCon.setConnectTimeout(props.timeOut+10000);
        httpCon.setReadTimeout(props.timeOut+10000);

        }
        OutputStream os = httpCon.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(props.getData());
        osw.flush();
        osw.close();
        os.close();
        httpCon.connect();
        Log.wtf("RESPONSE CODE",String.valueOf(httpCon.getResponseCode()));
        responseCode=httpCon.getResponseCode();
        BufferedInputStream bis = null;
        if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK)bis = new BufferedInputStream(httpCon.getInputStream());
        else bis = new BufferedInputStream(httpCon.getErrorStream());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
        buf.write((byte) result);
        result = bis.read();
        }

        return  buf.toString();

        } catch (Exception e) {
        return e.getMessage();

        }
        }
protected void onPostExecute(String result) {
    super.onPostExecute(result);

        Log.wtf("Responsecode",String.valueOf(responseCode));

        /*
        Error Catcher
        **/
        if(responseCode!=200){

        return;
        }
        switch (props.getHeader("request-action")){
            case "get_notes_from_number":
                ViewController.getNotesFromNumber(context,result);
                break;
            case "get_all_icons":
                ViewController.getAllIconsView(context,result);
                break;
            case "get_all_events":
                ViewController.getAllEvents(context,result);
                break;
            case "get_notes_from_month":
                ViewController.getNotesFromMonth(context,result,props.getBody("month"),props.getBody("year"));
                break;

        case "":
        break;
        default:
        ViewController.getCommunicate(context,result);
        break;
        }

        }

        }