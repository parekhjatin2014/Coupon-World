package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.app.model.Outlet;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutLetActivity extends Activity implements IOutletResponseListener {


    private ListView listView;
    List<Outlet> outletList;
    NearbyOutletAdapter nearbyOutletAdapter;
    ShowOutletsFor showOutletsFor;
    IOutLetRequest parentImplements;
    EditText searchEditText;
    String searchKey = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        showOutletsFor = (ShowOutletsFor) getIntent().getExtras().get("SortOutLets");
        listView = (ListView) findViewById(R.id.outlet_listview);
        parentImplements = (IOutLetRequest) getParent();
        searchEditText = (EditText) findViewById(R.id.outlet_search);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (showOutletsFor == ShowOutletsFor.NEAR_BY) {
            searchEditText.setVisibility(View.VISIBLE);
            searchEditText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String search = s.toString();
                    if (!search.equals(searchKey)) {
                        searchKey = search;
                        setList();
                    }
                }

                public void afterTextChanged(Editable s) {

                }
            });
        } else {
            searchEditText.setVisibility(View.GONE);
        }
        if (parentImplements != null) {
            parentImplements.getOutLets(this);
        }
    }

    public void setList() {
        List<Outlet> sortedList;
        if (showOutletsFor == ShowOutletsFor.NEAR_BY && !TextUtils.isEmpty(searchKey)) {
            sortedList = getSearchList(searchKey);
        } else {
            sortedList = sort(showOutletsFor);
        }
        if (nearbyOutletAdapter == null) {
            nearbyOutletAdapter = new NearbyOutletAdapter(this, sortedList);
            listView.setAdapter(nearbyOutletAdapter);
        } else {
            nearbyOutletAdapter.updateList(sortedList);
        }
    }


    private List<Outlet> sort(ShowOutletsFor showOutletsFor) {
        List<Outlet> result = new ArrayList<Outlet>(outletList);
        Collections.sort(result, new SortOutLets(showOutletsFor));
        return result;
    }

    private List<Outlet> getSearchList(String key) {
        List<Outlet> result;
        if (TextUtils.isEmpty(key)) {
            result = new ArrayList<Outlet>(outletList);
            Collections.sort(result, new SortOutLets(ShowOutletsFor.CITY));
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
        outletList = list;
        setList();
    }

    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
