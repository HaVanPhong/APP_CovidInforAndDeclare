package com.example.searchncovi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.searchncovi.SearchFragment.urlNcov;


public class RulesAndPolicyFragment extends Fragment {
    TextView tvApi;
    RecyclerView revVungDo, revVungCam, revVungXanh;
    RequestQueue requestQueue1;

    VungAdapter adapterDo;
    VungAdapter adapterCam;
    VungAdapter adapterXanh;
    public RulesAndPolicyFragment() {
        // Required empty public constructor
    }


    public static RulesAndPolicyFragment newInstance(String param1, String param2) {
        RulesAndPolicyFragment fragment = new RulesAndPolicyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rules_and_policy, container, false);
        tvApi = view.findViewById(R.id.tv_api);
        revVungDo = view.findViewById(R.id.revVungDo);
        revVungCam= view.findViewById(R.id.revVungCam);
        revVungXanh= view.findViewById(R.id.revVungXanh);
        requestQueue1= Volley.newRequestQueue(getContext());

        tvApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLinkFacebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://static.pipezero.com/covid/data.json"));
                startActivity(intentLinkFacebook);
            }
        });

        List<InformationNcoviItem> listVungDo= new ArrayList<>();
        List<InformationNcoviItem> listVungCam= new ArrayList<>();
        List<InformationNcoviItem> listVungXanh= new ArrayList<>();



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

                        InformationNcoviItem ncoviItem= new InformationNcoviItem(country, confirmed, deaths, recovered, treating, casesToday);
                        if (casesToday>=200){
                            listVungDo.add(ncoviItem);
                        }else if (casesToday>=50){
                            listVungCam.add(ncoviItem);
                        }else {
                            listVungXanh.add(ncoviItem);
                        }
                        //đỏ
                        adapterDo = new VungAdapter(listVungDo, getContext());
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                        revVungDo.setLayoutManager(layoutManager);
                        revVungDo.setAdapter(adapterDo);
                        adapterDo.notifyDataSetChanged();

                        //cam
                        adapterCam = new VungAdapter(listVungCam, getContext());
                        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                        revVungCam.setLayoutManager(layoutManager2);
                        revVungCam.setAdapter(adapterCam);
                        adapterCam.notifyDataSetChanged();

                        //xanh
                        adapterXanh = new VungAdapter(listVungXanh, getContext());
                        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                        revVungXanh.setLayoutManager(layoutManager3);
                        revVungXanh.setAdapter(adapterXanh);
                        adapterXanh.notifyDataSetChanged();
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
        requestQueue1.add(getAllCountryResult);


        return view;
    }
}