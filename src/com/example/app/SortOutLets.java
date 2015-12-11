package com.example.app;

import com.example.app.model.Outlet;

import java.util.Comparator;

public class SortOutLets implements Comparator<Outlet> {

    ShowOutletsFor showOutletsFor;


    public SortOutLets(ShowOutletsFor showOutletsFor) {
        this.showOutletsFor = showOutletsFor;
    }


    public int compare(final Outlet place1, final Outlet place2) {
        int comp = 1;
        switch (showOutletsFor) {
            case BET_OFFERS:
                comp = place2.getNumCoupons() - place1.getNumCoupons();
                break;
            case CITY:
            default:
                comp = Double.compare(place1.getDistanceFromCurrent(), place2.getDistanceFromCurrent());
                break;
        }
        return comp;
    }


}