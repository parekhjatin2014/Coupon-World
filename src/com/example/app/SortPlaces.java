package com.example.app;

import com.example.app.model.Outlet;

import java.util.Comparator;

public class SortPlaces implements Comparator<Outlet> {

    SortCriteria sortCriteria;


    public SortPlaces(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
    }


    public int compare(final Outlet place1, final Outlet place2) {
        int comp = 1;
        switch (sortCriteria) {
            case BET_OFFERS:
                comp = place2.getNumCoupons() - place1.getNumCoupons();
                break;
            case CITY:
            default:
                comp = (int) (place2.getDistanceFromCurrent() - place1.getDistanceFromCurrent());
                break;
        }
        return comp;
    }


}