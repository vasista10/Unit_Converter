package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SpeedActivity extends AppCompatActivity {
    float number;
    double result;
    EditText numInput;
    TextView resOut;
    TextView resTxt;
    String type;
    String type2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);

        numInput = (EditText) findViewById(R.id.input);
        resOut = (TextView) findViewById(R.id.result);
        resTxt = (TextView) findViewById(R.id.textView2);

        //1st dropdown list Spinner for the input type
        Spinner ddInput = (Spinner) findViewById(R.id.droplist);
        ArrayAdapter<String> speedInput = new ArrayAdapter<String>(SpeedActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.speeddd));
        speedInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddInput.setAdapter(speedInput);
        ddInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if(position == 0){
                    type = "Km";
                }else if (position == 1){
                    type = "Miles";
                }
                else if( position == 2){
                    type = "M";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //There is nothing to write here because always there is something selected
            }
        });

        //2nd dropdown list that will set the units to the result selected
        Spinner ddResult = (Spinner) findViewById(R.id.droplist2);
        ArrayAdapter<String> speedResult = new ArrayAdapter<String>(SpeedActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.speeddd));
        speedResult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddResult.setAdapter(speedResult);
        ddResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if(position == 0){
                    type2 = "Km";
                }else if (position == 1){
                    type2 = "Miles";
                }
                else if( position == 2){
                    type2 = "M";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //There is nothing to write here because always there is something selected
            }
        });

        //clear button
        ImageButton clear = (ImageButton) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear(v);
            }
        });
        //home button
        ImageButton home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home(v);
            }
        });
        //update button
        ImageButton update = (ImageButton) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });
    }
    //Method for the actions when onClick at the clear button
    public void clear(View view){
        //clean all the fields
        numInput.setText("");
        resOut.setText("");
        resTxt.setVisibility(View.INVISIBLE);
    }
    //Method to return to the main menu
    public void home(View view){
        Intent intent = new Intent(this, MainActivity.class);
        this.finish();
    }
    //Method to update the fields and make the calculations
    public void update(View view){
        //take values and do calculations. then update the result field
        String symbol = "";
        //Verify if the editText is empty. Displays a message when empty
        String checkInp = numInput.getText().toString();
        if (checkInp.matches("")){
            resTxt.setVisibility(View.INVISIBLE);
            resOut.setText("Insert a number");
        }else {
            resTxt.setVisibility(View.VISIBLE);
            //takes the value from the input and converts it into double to store it on variable number to make the conversions
            number = Float.parseFloat(numInput.getText().toString()); //takes the value from the input and converts it into integer to store it on variable number
            if (type.equals("Km") & type2.equals("M")){
                //Convert from Kilometers to Meters
                result = (number / 3.6);
                symbol = "mps";
            }else if(type.equals("Km") & type2.equals("Miles")){
                //Convert from Kilometers to Miles
                result = (number * 0.62137119223);
                symbol = "Mph";
            }else if(type.equals("Miles") & type2.equals("Km")){
                //Convert from Miles to Kilometers
                result = (number * 1.609344);
                symbol = "Kph";
            }else if(type.equals("Miles") & type2.equals("M")){
                //Convert from Miles to Meters
                result = ((number * 1.609344) /3.6);
                symbol = "mps";
            }else if(type.equals("M") & type2.equals("Km")){
                //Convert Meters to Kilometers
                result = (number * 3.6);
                symbol = "Kph";
            }else if(type.equals("M") & type2.equals("Miles")){
                result = ((number * 3.6) / 1.6093440);
                symbol = "Mph";
            }else if(type.equals(type2)){
                symbol = "Equal";
            }
            //check if the symbol is different by checking that it is not Equal
            if (symbol.equals("Equal")){
                resOut.setText("Select different Units");
            }else{
                resTxt.setVisibility(View.VISIBLE);
                if (result < 0.000001){
                    resOut.setText(String.format("%.10f " + symbol + "." ,result)); //result printed out with 10 decimal places for very small results
                }else if(result <0.01){
                    resOut.setText(String.format("%.5f " + symbol + "." ,result)); //result printed out with 5 decimal places for small results
                }else {
                    resOut.setText(String.format("%.2f " + symbol + "." ,result)); //result printed out with 2 decimal places
                }
            }
        }
    }
}
