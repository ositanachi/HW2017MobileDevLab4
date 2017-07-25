package com.ositanachi.hw2017mobiledevlab4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by ootugo on 7/25/17.
 */

public class ExchangeRateListFragment extends Fragment{
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancetate){
        View v = inflater.inflate(R.layout.fragment_exchangeratelist, container, false);

        mListView = (ListView)v.findViewById(R.id.list_view);

        List<String> list = Arrays.asList("foo", "bar", "baz");
        ArrayAdapter<String>adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);
        return v;
    }
}
