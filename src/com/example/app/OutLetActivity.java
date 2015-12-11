package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;
import com.example.app.model.Outlet;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutLetActivity extends Activity implements ChildImplements {


    private ListView listView;
    List<Outlet> outletList;
    NearbyOutletAdapter nearbyOutletAdapter;
    SortCriteria sortCriteria;
    IOutLetActivity parentImplements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sortCriteria = (SortCriteria) getIntent().getExtras().get("SortPlaces");
        listView = (ListView) findViewById(R.id.outlet_listview);
        parentImplements = (IOutLetActivity) getParent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (parentImplements != null) {
            parentImplements.getOutLets(this);
        }
    }

    public void setList(List<Outlet> list) {
        outletList = list;
        List<Outlet> sortedList = getOutletList(list);
        nearbyOutletAdapter = new NearbyOutletAdapter(this, sortedList);
        listView.setAdapter(nearbyOutletAdapter);
    }

    private List<Outlet> getOutletList(List<Outlet> list) {
        switch (sortCriteria) {
            case NEAR_BY:
                list = getSearchList("and");
                break;
            case BET_OFFERS:
            case CITY:
                list = sort(sortCriteria);
                break;
        }
        return list;
    }

    private List<Outlet> sort(SortCriteria sortCriteria) {
        List<Outlet> result = new ArrayList<Outlet>(outletList);
        Collections.sort(result, new SortPlaces(sortCriteria));
        return result;
    }

    private List<Outlet> getSearchList(String key) {
        List<Outlet> result;
        if (TextUtils.isEmpty(key)) {
            result = new ArrayList<Outlet>(outletList);
            Collections.sort(result, new SortPlaces(SortCriteria.CITY));
        } else {
            result = new ArrayList<Outlet>();
            String key2 = key.toLowerCase();
            for (Outlet outlet : outletList) {
                String landmark = outlet.getNeighbourhoodName();
                if (!TextUtils.isEmpty(landmark) && landmark.toLowerCase().contains(key2)) {
                    result.add(outlet);
                }
            }
        }
        return result;
    }

    public void onOutLetList(List<Outlet> list) {
        setList(list);
    }

    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
