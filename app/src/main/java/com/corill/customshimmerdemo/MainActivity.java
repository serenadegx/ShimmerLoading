package com.corill.customshimmerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.corill.customshimmerrecyclerview.CustomShimmerRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CustomShimmerRecyclerView recyclerView;
    private ExampleRecycleViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = (CustomShimmerRecyclerView) findViewById(R.id.rv);
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        adapter = new ExampleRecycleViewAdapter(R.layout.item_example, list);


        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
        recyclerView.showShimmerAdapter();
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.hideShimmerAdapter();
            }
        }, 3000);
    }
}
