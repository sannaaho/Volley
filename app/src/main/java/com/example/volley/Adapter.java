package com.example.volley;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONObject;
import java.util.ArrayList;

class Adapter extends ArrayAdapter<JSONObject>{

    Context context;
    private ArrayList<JSONObject> data;
    private int resource;

    public Adapter(@NonNull Context context, int resource, @NonNull ArrayList<JSONObject> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(context);
        convertView = i.inflate(resource, parent, false);

        TextView nameText = (TextView) convertView.findViewById(R.id.NimiText);
        TextView dateText = (TextView) convertView.findViewById(R.id.PvmText);

        try {
            nameText.setText(data.get(position).getString("Nimi: "));
            dateText.setText(date(data.get(position).getString("Pvm: ")));
        } catch(Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private String date(String date) {
        String[] parts = date.split(" ");
        return parts[0];
    }
}