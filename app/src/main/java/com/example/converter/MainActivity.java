package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    String[] values = {
            "Length", "Weight", "Temperature", "Speed", "Volume", "Currency", "Area", "Time"
    };
    int[] icons = {
            R.drawable.length,
            R.drawable.weigth,
            R.drawable.temperature,
            R.drawable.speed,
            R.drawable.volume,
            R.drawable.money,
            R.drawable.area,
            R.drawable.time
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);

        final GridAdapter gridAdapter = new GridAdapter(this, values, icons);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = null;
                if(position == 0){
                    myIntent = new Intent(view.getContext(), LengthActivity.class);
                }
                else if (position == 1){
                    myIntent = new Intent(view.getContext(), WeightActivity.class);
                }
                else if(position == 2){
                    myIntent = new Intent(view.getContext(), TemperatureActivity.class);
                }
                else if(position == 3){
                    myIntent = new Intent(view.getContext(), SpeedActivity.class);
                }
                else if(position == 4){
                    myIntent = new Intent(view.getContext(), VolumeActivity.class);
                }
                else if(position == 5){
                    myIntent = new Intent(view.getContext(), CurrencyActivity.class);
                }
                else if(position == 6){
                    myIntent = new Intent(view.getContext(), AreaActivity.class);
                }
                else if(position == 7){
                    myIntent = new Intent(view.getContext(), TimeActivity.class);
                }
                startActivity(myIntent);
            }
        });
    }
}
