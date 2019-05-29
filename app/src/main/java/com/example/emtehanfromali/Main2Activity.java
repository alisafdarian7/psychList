package com.example.emtehanfromali;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private static final String URL = "http://triangle-sh.ir/getVol.php";
    TextView name2,prof,email,ostan,shahr,address,rooykard,telegram,tel1,tel2,point,details,madrak,id,insta;
    ImageView photo2;

    private static final String TAG = MainActivity.class.getName();

    RequestQueue mRequestQueue;
    public ArrayList<Psychologists> psychologistsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        psychologistsList=new ArrayList<>();

        name2=findViewById(R.id.name2);
        photo2=findViewById(R.id.photo2);
        prof=findViewById(R.id.prof);
        email=findViewById(R.id.email);
        ostan=findViewById(R.id.ostan);
        shahr=findViewById(R.id.shahr);
        address=findViewById(R.id.address);
        rooykard=findViewById(R.id.rooykard);
        telegram=findViewById(R.id.telegram);
        tel1=findViewById(R.id.tel1);
        tel2=findViewById(R.id.tel2);
        point=findViewById(R.id.point);
        details=findViewById(R.id.details);
        madrak=findViewById(R.id.madrak);
        id=findViewById(R.id.id);
        insta=findViewById(R.id.insta);

        getJsonData();

        String names=getIntent().getExtras().get("name2").toString();
        name2.setText(names);

        String takhasos=getIntent().getExtras().get("prof").toString();
        prof.setText(takhasos);

        String link=getIntent().getExtras().get("aks").toString();
        Glide.with(Main2Activity.this).load(link).into(photo2);

        String emaail=getIntent().getExtras().get("email").toString();
        email.setText(emaail);

        String prov=getIntent().getExtras().get("ostan").toString();
        ostan.setText(prov);

        String city=getIntent().getExtras().get("shahr").toString();
        shahr.setText(city);

        String adres=getIntent().getExtras().get("address").toString();
        address.setText(adres);

        String roy=getIntent().getExtras().get("rooykard").toString();
        rooykard.setText(roy);

        String teleg=getIntent().getExtras().get("telegram").toString();
        rooykard.setText(teleg);

        String telephone1=getIntent().getExtras().get("tel1").toString();
        tel1.setText(telephone1);

        String telephone2=getIntent().getExtras().get("tel2").toString();
        tel2.setText(telephone2);

        String emtiaz=Integer.toString(getIntent().getExtras().getInt("point"));
        point.setText(emtiaz);

        String det=getIntent().getExtras().get("details").toString();
        details.setText(det);

        String cert=getIntent().getExtras().get("madrak").toString();
        madrak.setText(cert);

        String iddd=Integer.toString(getIntent().getExtras().getInt("id"));
        id.setText(iddd);

        String instaa=getIntent().getExtras().get("insta").toString();
        insta.setText(instaa);





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
        mRequestQueue = Volley.newRequestQueue(Main2Activity.this);

        mRequestQueue.add(stringRequest);


    }




}
