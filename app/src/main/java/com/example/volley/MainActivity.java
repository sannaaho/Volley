package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView myList;
    RequestQueue requestQueue;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        results = (TextView) findViewById(R.id.textView);
        myList = (ListView) findViewById(R.id.test_list);

        final ArrayAdapter<String> myAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        ListView myList = (ListView) findViewById(R.id.test_list);
        myList.setAdapter(myAdapter);

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<String> myArrayList = new ArrayList<>();

                        try {
                            JSONArray shapes = response.getJSONObject(1).getJSONArray("shapeArray");
                            for(int i=0; i<shapes.length(); i++) {
                                myArrayList.add(shapes.getJSONObject(i).getString("shapeName"));
                            }
                        } catch (JSONException e){
                            Log.e("Json parsing error", response.toString());
                        }
                    }
                };new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.toString());

                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    Toast.makeText(getApplicationContext(),
                            "Connect to internet!",
                            Toast.LENGTH_LONG).show();
                    }
            }
        };
    }
}