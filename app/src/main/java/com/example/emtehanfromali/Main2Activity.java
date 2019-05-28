package com.example.emtehanfromali;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    List<Psychologists> psychologistsList;
    List<Psychologists> psychologistsList2;
    Psychologists psychologists=new Psychologists();
    Psychologists psychologists2=new Psychologists();
    RecyclerView recyclerView;
    PsychologistAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        psychologistsList=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_view2);
        adapter=new PsychologistAdapter2(psychologistsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }
}
