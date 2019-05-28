package com.example.emtehanfromali;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.View;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String URL="http://triangle-sh.ir/getPsychos.php";
    private static final String TAG = MainActivity.class.getName();

    private List<Psychologists> psychologistsList;
    Psychologists psychologists=new Psychologists();
    RecyclerView recycler_view;
    PsychologistAdapter adapter;
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        psychologistsList=new ArrayList<>();
        recycler_view=findViewById(R.id.recycler_view);

        //setting up volley request queue
        mRequestQueue = Volley.newRequestQueue(MainActivity.this);
        getJsonData();

        //initialising recyclerview and setting adapter to the recyclerview
        recycler_view = findViewById(R.id.recycler_view);
        adapter = new PsychologistAdapter(psychologistsList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        adapter=new PsychologistAdapter(psychologistsList,MainActivity.this);

        recycler_view.setAdapter(adapter);


                new MyClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Psychologists psychologists=psychologistsList.get(position);
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);


                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                };

    }

    public void getJsonData() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i(TAG,"response: "+response);
                try {
                    JSONArray ja=new JSONArray(response);

                    for (int i = 0; i < ja.length(); i++) {

                        JSONObject jsonObject = ja.getJSONObject(i);

                        psychologists.setP_name(jsonObject.getString("p_name"));
                        psychologists.setP_prof(jsonObject.getString("p_prof"));
                        psychologists.setP_email(jsonObject.getString("p_email"));
                        psychologists.setP_province(jsonObject.getString("p_province"));
                        psychologists.setP_city(jsonObject.getString("p_city"));
                        psychologists.setP_address(jsonObject.getString("p_address"));
                        psychologists.setP_rooy(jsonObject.getString("p_rooy"));
                        psychologists.setP_telegram(jsonObject.getString("p_telegram"));
                        psychologists.setP_telephone1(jsonObject.getString("p_telephone1"));
                        psychologists.setP_telephone2(jsonObject.getString("p_telephone2"));
                        psychologists.setP_image(jsonObject.getString("p_image"));
                        psychologists.setP_point(jsonObject.getInt("p_point"));
                        psychologists.setP_details(jsonObject.getString("p_details"));
                        psychologists.setP_cert(jsonObject.getString("p_cert"));
                        psychologists.setP_ID(jsonObject.getInt("p_ID"));
                        psychologists.setNumber(jsonObject.getInt("number"));
                        psychologists.setP_instagram(jsonObject.getString("p_instagram"));
                        psychologists.setP_voters(jsonObject.getInt("p_voters"));
                        psychologistsList.add(psychologists);

                    }
                    adapter=new PsychologistAdapter(psychologistsList,MainActivity.this);
                    recycler_view.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"returned "+psychologistsList.size()+" items.");


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mRequestQueue.add(stringRequest);


    }
}
