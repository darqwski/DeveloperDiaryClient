package com.example.darqwski.developerdiary;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Darqwski on 2018-08-20.
 */

public class RequestProperties {

    ArrayList<String> headerNames = new ArrayList();
    ArrayList<String> headerValues = new ArrayList();
    public ArrayList<String> dataNames = new ArrayList();
    public ArrayList<String> dataValues = new ArrayList();
    String method;

    public boolean isDebug() {
        return isDebug;
    }

    boolean isDebug;
    String data;
    int timeOut;

    public String getViewControllerFunction() {
        return ViewControllerFunction;
    }

    public RequestProperties setViewControllerFunction(String viewControllerFunction) {
        ViewControllerFunction = viewControllerFunction;
        return null;
    }

    String ViewControllerFunction;
    String defaultAddress = "http://localhost:8080/requester.php";
    final static String METHOD_POST = "POST";
    final static String METHOD_GET = "GET";
    final static String EDIT = "EDIT";
    final static String GET = "GET";
    final static String DELETE = "DELETE";
    final static String POST = "POST";
    String defaultCharset = "utf-8";
    String contentType = "application/x-www-form-urlencoded";

    final static String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    final static String CONTENT_TYPE_JSON = "application/json";

    RequestProperties(){
        timeOut = 0;
        method = METHOD_GET;
        addHeader("content-type",contentType);
        addHeader("accept-charset",defaultCharset);
        data="";
        ViewControllerFunction="";
        isDebug=false;
    }
    public RequestProperties setValidation(){
        addHeader("Validation","16180339887");
        return this;
    }
    public RequestProperties setRequestType(String connectionType){
        addHeader("request-type",connectionType);
        return this;
    }
    public RequestProperties setRequestAction(String connectionType){
        addHeader("request-action",connectionType);
        return this;
    }

    public RequestProperties addHeader(String key,String value){
        headerNames.add(key);
        headerValues.add(value);
        return this;
    }
    public RequestProperties setDebug(){
        isDebug=true;
        return this;
    }
    public String getData(){
        String finalValue = "";

        switch (contentType){
            case "application/x-www-form-urlencoded":
                for(int i=0;i<dataValues.size();i++){
                    finalValue += URLEncoder.encode(dataNames.get(i));
                    finalValue+="=";
                    finalValue += URLEncoder.encode(dataValues.get(i));
                    if(i+1<dataValues.size())finalValue+="&";
                }
                return finalValue;
            case "application/json":
                finalValue = "{";
                for(int i=0;i<dataValues.size();i++){
                    finalValue += ("\""+dataNames.get(i)+"\"");
                    finalValue+=":";
                    finalValue += ("\""+dataValues.get(i)+"\"");
                    if(i+1<dataValues.size())finalValue+=",";
                }
                finalValue+="}";
                return finalValue;
            default:

                return  "";

        }

    }
    public RequestProperties addBody(String key,String value){
        dataNames.add(key);
        dataValues.add(value);
        return this;
    }
    public String getBody(String index){
        for(int i = 0;i<dataNames.size();i++)
            if(dataNames.get(i).equals(index))
                return dataValues.get(i);
        return "";
    }

    public String getHeader(String index){
        for(int i = 0;i<headerNames.size();i++)
            if(headerNames.get(i).equals(index))
                return headerValues.get(i);
        return "";
    }
    public RequestProperties setMethodPost(){
        method = METHOD_POST;
        return this;
    }
    public RequestProperties setMethodGet(){
        method = METHOD_GET;
        return this;
    }
    public RequestProperties prepareSendConnection(){
        setValidation();
        setRequestType(POST);
        setMethodPost();
        return this;
    }
    public RequestProperties prepareGetConnection(){
        setValidation();
        setRequestType(GET);
        return this;
    }
    public RequestProperties prepareChangeConnection(){
        setValidation();
        setRequestType(EDIT);
        setMethodPost();
        return this;
    }
    public RequestProperties prepareDeleteConnection(){
        setValidation();
        setRequestType(DELETE);
        setMethodPost();
        return this;
    }

}
