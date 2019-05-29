package com.example.emtehanfromali;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String URL="http://triangle-sh.ir/getVol.php";
    private static final String TAG = MainActivity.class.getName();

    RecyclerView recycler_view;
    PsychologistAdapter adapter;
    RequestQueue mRequestQueue;
    public ArrayList<Psychologists> psychologistsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        psychologistsList=new ArrayList<>();


        getJsonData();


        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));

        recycler_view.addOnItemTouchListener(new RVItemTouchListener(this, recycler_view,
                new MyClickListener() {
                    @Override
                    public void onClick(View view, int pos) {
                        Toast.makeText(MainActivity.this,
                                "<Click>\nposition =  " + pos + "\nPsychologists : " + psychologistsList.get(pos),
                                Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);

                        intent.putExtra("name2",(psychologistsList.get(pos).getP_name()));
                        intent.putExtra("prof",(psychologistsList.get(pos).getP_prof()));
                        intent.putExtra("aks",psychologistsList.get(pos).getP_image());
                        intent.putExtra("email",(psychologistsList.get(pos).getP_email()));
                        intent.putExtra("ostan",(psychologistsList.get(pos).getP_province()));
                        intent.putExtra("shahr",(psychologistsList.get(pos).getP_city()));
                        intent.putExtra("address",(psychologistsList.get(pos).getP_address()));
                        intent.putExtra("rooykard",(psychologistsList.get(pos).getP_rooy()));
                        intent.putExtra("telegram",(psychologistsList.get(pos).getP_telegram()));
                        intent.putExtra("tel1",(psychologistsList.get(pos).getP_telephone1()));
                        intent.putExtra("tel2",(psychologistsList.get(pos).getP_telephone2()));
                        intent.putExtra("point",(psychologistsList.get(pos).getP_point()));
                        intent.putExtra("details",(psychologistsList.get(pos).getP_details()));
                        intent.putExtra("madrak",(psychologistsList.get(pos).getP_cert()));
                        intent.putExtra("id",(psychologistsList.get(pos).getP_ID()));
                        intent.putExtra("insta",(psychologistsList.get(pos).getP_instagram()));

                        startActivity(intent);



                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }
        ));




    }

    private void getJsonData() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET,URL,
                new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                Log.i(TAG,"response: "+response);

                try {
                    JSONArray ja=new JSONArray(response);



                    for (int i = 0; i < ja.length(); i++) {
                        Psychologists psychologists=new Psychologists();

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
                    adapter=new PsychologistAdapter(MainActivity.this,psychologistsList);
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
        mRequestQueue = Volley.newRequestQueue(MainActivity.this);

        mRequestQueue.add(stringRequest);


    }

}
