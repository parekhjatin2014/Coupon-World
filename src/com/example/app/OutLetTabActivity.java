package com.example.app;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;
import com.example.app.model.Outlet;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class OutLetTabActivity extends TabActivity implements IOutLetRequest {
    double myLat = -1, myLon = -1;
    List<Outlet> outletList;
    String tabCity = "In Your City";
    String tabNearBy = "Near By";
    String tabBestOffers = "Best Offers";
    private IOutletResponseListener responseListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);


        CouponApplication.getIOutLetRequest().getOutLets(new IOutletResponseListener() {
            public void onOutLetList(List<Outlet> list) {
                outletList = new ArrayList<Outlet>(list);
                if (myLat != -1 && myLon != -1) {
                    setDistance();
                    if (responseListener != null) {
                        responseListener.onOutLetList(outletList);
                    }
                }
            }

            public void onError(String errorMessage) {
                Toast.makeText(OutLetTabActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        GPSTracker gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            myLat = gps.getLatitude();
            myLon = gps.getLongitude();
            if (outletList != null) {
                setDistance();
                if (responseListener != null) {
                    responseListener.onOutLetList(outletList);
                }
            }
        } else {
            gps.showSettingsAlert();
        }

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        TabHost.TabSpec tab1 = tabHost.newTabSpec(tabCity);
        TabHost.TabSpec tab2 = tabHost.newTabSpec(tabNearBy);
        TabHost.TabSpec tab3 = tabHost.newTabSpec(tabBestOffers);

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected

        Intent intent;
        intent = new Intent(this, OutLetActivity.class);
        intent.putExtra("SortOutLets", ShowOutletsFor.CITY);
        tab1.setIndicator(tabCity);
        tab1.setContent(intent);
        intent = new Intent(this, OutLetActivity.class);
        intent.putExtra("SortOutLets", ShowOutletsFor.NEAR_BY);
        tab2.setIndicator(tabNearBy);
        tab2.setContent(intent);
        intent = new Intent(this, OutLetActivity.class);
        intent.putExtra("SortOutLets", ShowOutletsFor.BET_OFFERS);
        tab3.setIndicator(tabBestOffers);
        tab3.setContent(intent);
        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

    }

    private void setDistance() {
        for (Outlet outlet : outletList) {
            double distanceToPlace = distance(myLat, myLon, outlet.getLatitude(), outlet.getLongitude());
            outlet.setDistanceFromCurrent(distanceToPlace);
        }
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


    public static Double distance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double distance = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +
                Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                        Math.cos(deg2rad(theta));

        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515;
        distance = distance * 1609.344;
        return distance;
    }

    public void getOutLets(IOutletResponseListener responseListener) {
        this.responseListener = responseListener;
        if (outletList != null && responseListener != null) {
            responseListener.onOutLetList(outletList);
        }
    }
}