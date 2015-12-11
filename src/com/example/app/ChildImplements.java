package com.example.app;

import com.example.app.model.Outlet;

import java.util.List;

/**
 * Created by jatin.parekh on 10-12-2015.
 */
public interface ChildImplements {
    void onOutLetList(List<Outlet> list);

    void onError(String message);
}
