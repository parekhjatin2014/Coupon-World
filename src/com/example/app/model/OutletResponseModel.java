package com.example.app.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OutletResponseModel {


    @SerializedName("status")
    public Status status;

    @SerializedName("data")
    public List<Outlet> data;


    public class Status {

       /* "rcode": 200,
                "message": "OK"*/

        @SerializedName("status")
        public int rcode;

        @SerializedName("message")
        public String message;
    }
}
