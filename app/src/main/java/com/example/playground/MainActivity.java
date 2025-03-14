package com.example.playground;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private ArrayList<RecyclerViewItem> items;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add custom divider between items
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, R.drawable.divider);
        recyclerView.addItemDecoration(itemDecoration);

        // Initialize the RecyclerView adapter and RecyclerView item
        items = new ArrayList<>();
        adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                // TODO: Handle click on RecyclerView item
            }
        });
        recyclerView.setAdapter(adapter);

        // Set Load data button
        Button loadBtn = findViewById(R.id.getDataBtn);
        // Add a listener to button to handle click events
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("--> Inside MainActivity/onCreate/onClick", "Load data button was clicked!");

                // Use JsonTask with the URL for stored Json data online
                String jsonUrl = "https://api.npoint.io/ab3d1974355f704b9db6";
                Log.d("--> Inside MainActivity/onCreate/onClick", "Starting JsonTask with URL: " + jsonUrl);
                new JsonTask(MainActivity.this).execute(jsonUrl);
            }
        });

        // Button to go to AboutActivity
        Button aboutBtn = findViewById(R.id.aboutAppBtn);
        // Add listener to button to handle click events
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create new intent to start AboutActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("--> Inside MainActivity/onCreate/onPostExecute", "onPostExecute called with json: " + json);
        if (json != null) {
            try {
                // Convert the JSON string into an ArrayList of RecyclerViewItem objects using Gson
                Gson gson = new Gson();
                items = gson.fromJson(json, new TypeToken<ArrayList<RecyclerViewItem>>() {}.getType());

                // DEBUG: Check if JSON string is being correctly read
                Log.d("--> Inside MainActivity/onCreate/onPostExecute", "JSON_STRING: " + json);

                // DEBUG: Check the contents of items after Gson parsing
                if (items != null && !items.isEmpty()) {
                    Log.d("--> Inside MainActivity/onCreate/onPostExecute", "Parsed items: " + items.toString());
                    // Update the adapter with the new items
                    adapter.updateItems(items);
                } else {
                    Log.e("--> Inside MainActivity/onCreate/onPostExecute", "ERROR: Parsed items are empty or null.");
                }
            } catch (Exception e) {
                Log.e("--> Inside MainActivity/onCreate/onPostExecute", "Unexpected error", e);
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e("MainActivity", "Failed to fetch JSON data.");
            Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
        }
    }
}