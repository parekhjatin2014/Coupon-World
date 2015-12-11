package com.example.app;

import com.example.app.model.Outlet;

import java.util.List;

public interface IOutletResponseListener {
    void onOutLetList(List<Outlet> list);

    void onError(String errorMessage);
}
