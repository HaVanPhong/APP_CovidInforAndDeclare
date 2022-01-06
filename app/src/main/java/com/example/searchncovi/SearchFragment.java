package com.example.searchncovi;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private static RecyclerView rcvSearchResult;
    private static ItemSearchNcoviAdapter adapter;
    private static List<InformationNcoviItem> listSearch;
    private static EditText edtSearch;
    private static Button btnUpdate;

    public static final String urlNcov = "https://static.pipezero.com/covid/data.json";
    Moshi moshi = new Moshi.Builder().build();
    Type countryType = Types.newParameterizedType(List.class, InformationNcoviItem.class);
    final JsonAdapter<List<InformationNcoviItem>> listJsonAdapter = moshi.adapter(countryType);

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        rcvSearchResult = view.findViewById(R.id.recycler_view_search_result);
        edtSearch = view.findViewById(R.id.edt_search);
        btnUpdate = view.findViewById(R.id.btn_update_data);
        btnUpdate.setVisibility(View.INVISIBLE);
        listSearch = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest getAllCountryResult = new StringRequest(Request.Method.GET, urlNcov, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj =  new JSONObject(response);
                    String arrayResult= jsonObj.getString("locations");
                    JSONArray jsonArray = new JSONArray(arrayResult);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String country = jsonObject.getString("name");
                        long confirmed = jsonObject.getLong("cases");
                        long deaths = jsonObject.getLong("death");
                        long recovered = jsonObject.getLong("recovered");
                        long treating= jsonObject.getLong("treating");
                        long casesToday= jsonObject.getLong("casesToday");

                        listSearch.add(new InformationNcoviItem(country, confirmed, deaths, recovered, treating, casesToday));

                        adapter = new ItemSearchNcoviAdapter(listSearch, getContext());
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                        rcvSearchResult.setLayoutManager(layoutManager);
                        rcvSearchResult.setAdapter(adapter);
                        layoutAnimation(rcvSearchResult);
                        adapter.notifyDataSetChanged();
                        btnUpdate.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                try {
//                    final List<InformationNcoviItem> listResult = listJsonAdapter.fromJson(response);
//                    listResult.size();
//                    adapter = new ItemSearchNcoviAdapter(listResult);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No Internet!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(getAllCountryResult);

//        adapter = new ItemSearchNcoviAdapter(listSearch);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//        rcvSearchResult.setLayoutManager(layoutManager);
//        rcvSearchResult.setAdapter(adapter);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutAnimation(rcvSearchResult);
            }
        });

        return view;
    }


    public void layoutAnimation(RecyclerView recyclerView){
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_silde_right);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void layoutAnimationDownToUp(RecyclerView recyclerView){
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_down_to_up);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void filter(String text) {
        ArrayList<InformationNcoviItem> filteredList = new ArrayList<>();
        for(InformationNcoviItem item : listSearch){
            if (item.getCountry_Region().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
            layoutAnimationDownToUp(rcvSearchResult);
        }
        adapter.filterList(filteredList);
//        layoutAnimation(rcvSearchResult);
    }
}