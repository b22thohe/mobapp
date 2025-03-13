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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set Load data button
        Button loadBtn = findViewById(R.id.getDataBtn);

        // Add listener to button loadBtn
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("==>", "Load data button was clicked!");

                ArrayList<RecyclerViewItem> items = new ArrayList<>();

                try {
                    // Load JSON data from the raw resource file
                    InputStream inputStream = getResources().openRawResource(R.raw.userdata);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder stringBuilder = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }

                    // Convert the JSON string into an ArrayList of RecyclerViewItem objects using Gson
                    String jsonString = stringBuilder.toString();
                    Gson gson = new Gson();
                    items = gson.fromJson(jsonString, new TypeToken<ArrayList<RecyclerViewItem>>() {}.getType());

                    // DEBUG: Check if JSON string is being correctly read
                    Log.d("JSON_STRING", jsonString);

                    // DEBUG: Check the contents of items after Gson parsing
                    if (items != null && !items.isEmpty()) {
                        Log.d("==>", "Parsed items: " + items.toString());
                    } else {
                        Log.e("==>", "Parsed items are empty or null.");
                    }
                } catch (IOException e) {
                    Log.e("==>", "IOException occurred while reading the JSON", e);
                } catch (Exception e) {
                    Log.e("==>", "Unexpected error", e);
                    // Log and handle any exceptions (e.g., JSON parsing errors)
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, items, new RecyclerViewAdapter.OnClickListener() {
                    @Override
                    public void onClick(RecyclerViewItem item) {
                        // Reserved for handling click on RecyclerView item
                    }
                });

                RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(adapter);

                // Add custom divider between items
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(MainActivity.this, R.drawable.divider);
                recyclerView.addItemDecoration(itemDecoration);
            }
        });

        // Button to go to AboutActivity
        Button aboutBtn = findViewById(R.id.aboutAppBtn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}