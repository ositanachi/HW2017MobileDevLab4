package com.ositanachi.hw2017mobiledevlab4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static android.R.attr.tag;
import static java.security.AccessController.getContext;

/**
 * Created by ootugo on 7/25/17.
 */

public class ExchangeRateListFragment extends Fragment {
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancetate) {
        View v = inflater.inflate(R.layout.fragment_exchangeratelist, container, false);

        mListView = (ListView) v.findViewById(R.id.list_view);

        List<String> list = Arrays.asList("foo", "bar", "baz");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);

        mListView.setAdapter(adapter);

        getData();
        return v;
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://api.fixer.io/latest?base=USD";
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("ONO", "got response: " + response.toString());
                        handleResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("ONO", "got error: " + error.toString());
                    }
                });
        queue.add(jsonObjRequest);
    }

    private void handleResponse(JSONObject response) {
        List<String> stringsList = new ArrayList<>();
        try {
            JSONObject ratesObject = response.getJSONObject("rates");
            Iterator<String> keys = ratesObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Double val = (Double) ratesObject.get(key);
                stringsList.add(key + " : " + val.toString());
            }

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, stringsList);
            mListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
