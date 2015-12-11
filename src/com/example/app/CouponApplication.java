/*
 * Copyright Medtronic, Inc. 2015
 *
 * MEDTRONIC CONFIDENTIAL: This document is the property of Medtronic,
 * Inc.,and must be accounted for. Information herein is confidential. Do
 * not reproduce it, reveal it to unauthorized persons, or send it outside
 * Medtronic without proper authorization.
 */

package com.example.app;

import android.app.Application;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.model.OutletResponseModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class CouponApplication extends Application implements IOutLetRequest{

    public static final String JSON_URL = "http://staging.couponapitest.com/task.txt";
    private OutletResponseModel outletResponseModel;
    private IOutletResponseListener responseListener;
    private static IOutLetRequest outLetRequest;

    @Override
    public final void onCreate() {
        super.onCreate();
        outLetRequest = this;

    }

    public static IOutLetRequest getIOutLetRequest() {
        return outLetRequest;
    }

    private void sendRequest() {

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        setOutLetList(response);
                    }
                },
                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {
                        responseListener.onError(error.getMessage());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setOutLetList(String json) {
        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<OutletResponseModel>() {
        }.getType();
        try {
            outletResponseModel = gson.fromJson(json, listOfTestObject);
            responseListener.onOutLetList(outletResponseModel.data);
        } catch (Exception e) {
            responseListener.onError(e.getMessage());
        }

    }


    public void getOutLets(IOutletResponseListener iOutletResponseListener) {
        this.responseListener = iOutletResponseListener;
        if (outletResponseModel == null) {
            sendRequest();
        } else {
            iOutletResponseListener.onOutLetList(outletResponseModel.data);

        }
    }



}
