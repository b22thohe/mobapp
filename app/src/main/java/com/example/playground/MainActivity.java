package com.example.playground;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //    return insets;
        //});

        TextView textView1 = findViewById(R.id.tvLabelId);
        textView1.setText("This is TextView 1");
        TextView textView2 = findViewById(R.id.txtView2);
        textView2.setText("This is TextView 2");

        // Set Load data button
        Button loadBtn = findViewById(R.id.getDataBtn);
        // Add listener to button loadBtn
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("==>", "Load data button was clicked!");

                // Load JSON data from file in raw
                InputStream inputStream = getResources().openRawResource(R.raw.userdata);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                try {
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Convert the JSON string into an ArrayList using Gson
                String jsonString = stringBuilder.toString();

                // Assuming the JSON represents an array of objects
                Gson gson = new Gson();
                ArrayList<RecyclerViewItem> items = gson.fromJson(jsonString, new TypeToken<ArrayList<RecyclerViewItem>>(){}.getType());

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, items, new RecyclerViewAdapter.OnClickListener() {
                    @Override
                    public void onClick(RecyclerViewItem item) {
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });

                RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(adapter);
            }
        });
    }

    // Inner class User to handle the json data in userdata.json
    private int ID;
    private String Login;
    private String Name;
    private String Email;
    private String Role;

    // Getters & setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", Login='" + Login + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }
}