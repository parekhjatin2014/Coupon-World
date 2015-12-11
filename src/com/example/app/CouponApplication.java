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
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.model.OutletResponseModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class CouponApplication extends Application {

    private static CouponApplication mCouponApplication = null;
    public static final String JSON_URL = "http://staging.couponapitest.com/task.txt";
    OutletResponseModel outletResponseModel;
    IOutletResponseModel iOutletResponseModel;

    @Override
    public final void onCreate() {
        super.onCreate();
        mCouponApplication = this;

    }

    public static CouponApplication getCouponApplication() {
        return mCouponApplication;
    }

    private void sendRequest() {

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mCouponApplication, error.getMessage(), Toast.LENGTH_LONG).show();
                        iOutletResponseModel.onError(error.getMessage());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<OutletResponseModel>() {
        }.getType();
        try {
            outletResponseModel = gson.fromJson(json, listOfTestObject);
            iOutletResponseModel.onOutLetsDownload(outletResponseModel);
        } catch (Exception e) {
            iOutletResponseModel.onError(e.getMessage());
        }

    }


    public void downloadOutLets(IOutletResponseModel iOutletResponseModel) {
        this.iOutletResponseModel = iOutletResponseModel;
        if (outletResponseModel == null) {
            sendRequest();
        } else {
            iOutletResponseModel.onOutLetsDownload(outletResponseModel);

        }

    }

    public interface IOutletResponseModel {
        void onOutLetsDownload(OutletResponseModel outletResponseModel);

        void onError(String errorMessage);
    }


}
