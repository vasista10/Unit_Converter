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

public class LengthActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_lenght);

        numInput = (EditText) findViewById(R.id.input);
        resOut = (TextView) findViewById(R.id.result);
        resTxt = (TextView) findViewById(R.id.textView2);

        //1st dropdown list Spinner for the input type
        Spinner ddInput = (Spinner) findViewById(R.id.droplist);
        ArrayAdapter<String> lengthInput = new ArrayAdapter<String>(LengthActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lenghtdd));
        lengthInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddInput.setAdapter(lengthInput);
        ddInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if(position == 0){
                    type = "Km";
                }else if (position == 1){
                    type = "Meters";
                }
                else if( position == 2){
                    type = "Miles";
                }
                else if (position == 3){
                    type = "Yards";
                }
                else if (position == 4){
                    type = "Feet";
                }
                else if (position == 5){
                    type = "Inches";
                }
                else if (position == 6){
                    type = "Centimeters";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //There is nothing to write here because always there is something selected
            }
        });

        //2nd dropdown list that will set the units to the result selected
        Spinner ddResult = (Spinner) findViewById(R.id.droplist2);
        ArrayAdapter<String> lengthResult = new ArrayAdapter<String>(LengthActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lenghtdd));
        lengthResult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddResult.setAdapter(lengthResult);
        ddResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if(position == 0){
                    type2 = "Km";
                }else if (position == 1){
                    type2 = "Meters";
                }
                else if( position == 2){
                    type2 = "Miles";
                }
                else if (position == 3){
                    type2 = "Yards";
                }
                else if (position == 4){
                    type2 = "Feet";
                }
                else if (position == 5){
                    type2 = "Inches";
                }
                else if (position == 6){
                    type2 = "Centimeters";
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
        //take values and do calculations based on the selections from the drop down lists, then update the result field
        String symbol = "";
        //Verify if the editText is empty. Displays a message when empty
        String checkInp = numInput.getText().toString();
        if (checkInp.matches("")){
            resTxt.setVisibility(View.INVISIBLE);
            resOut.setText("Insert a number");
        }else {
            resTxt.setVisibility(View.VISIBLE);
            number = Float.parseFloat(numInput.getText().toString()); //takes the value from the input and converts it into integer to store it on variable number
            if (type.equals("Km") & type2.equals("Meters")){
                //Convert from Kilometers to Meters
                result = (number * 1000);
                symbol = "m";
            }else if(type.equals("Km") & type2.equals("Miles")){
                //Convert from Kilometers to Miles
                result = (number * 0.62137119223);
                symbol = "mi";
            }else if(type.equals("Km") & type2.equals("Yards")){
                //Convert from Kilometers to Yards (1km = 1093.61 yd)
                result = (number * 1093.61);
                symbol = "yd";
            }else if(type.equals("Km") & type2.equals("Feet")){
                //Convert from Kilometers to Feet (1km = 3280.84ft)
                result = (number * 3280.84);
                symbol = "ft";
            }else if(type.equals("Km") & type2.equals("Inches")){
                //Convert from Kilometers to Inches (1km = 39370.1")
                result = (number * 39370.1);
                symbol = "\"";
            }else if(type.equals("Km") & type2.equals("Centimeters")){
                //Convert from Kilometers to Centimeters
                result = (number * 100000);
                symbol = "cm";
            }else if(type.equals("Meters") & type2.equals("Km")){
                //Convert from Meters to Kilometers
                result = (number /1000);
                symbol = "Km";
            }else if(type.equals("Meters") & type2.equals("Miles")){
                //Convert from meters to Miles (1m = 0.00062137mi)
                result = (number * 0.00062137);
                symbol = "mi";
            }else if(type.equals("Meters") & type2.equals("Yards")){
                //Convert from meters to Yards (1m = 1.0936yd)
                result = (number * 1.0936);
                symbol = "yd";
            }else if(type.equals("Meters") & type2.equals("Feet")){
                //Convert from meters to Feet (1m = 3.28084ft)
                result = (number * 3.28084);
                symbol = "ft";
            }else if(type.equals("Meters") & type2.equals("Inches")){
                //Convert from meters to Inches (1m = 39.37")
                result = (number * 39.37);
                symbol = "\"";
            }else if(type.equals("Meters") & type2.equals("Centimeters")){
                //Convert from meters to Centimeters
                result = (number * 100);
                symbol = "cm";
            }else if(type.equals("Miles") & type2.equals("Km")){
                //Convert from Miles to Kilometers (1mile = 1.61km)
                result = (number / 0.62137119223);
                symbol = "km";
            }else if(type.equals("Miles") & type2.equals("Meters")){
                //Convert from Miles to meters
                result = (number * 0.00062137119223);
                symbol = "m";
            }else if(type.equals("Miles") & type2.equals("Yards")){
                //Convert from Miles to Yards (1mile = 1760yd)
                result = (number * 1760);
                symbol = "yd";
            }else if(type.equals("Miles") & type2.equals("Feet")){
                //Convert from Miles to Feet (1mile = 5280)
                result = (number * 5280);
                symbol = "ft";
            }else if(type.equals("Miles") & type2.equals("Inches")){
                //Convert from Miles to inches (1mi = 63360")
                result = (number * 63360);
                symbol = "\"";
            }else if(type.equals("Miles") & type2.equals("Centimeters")){
                //Convert from Miles to Centimeters (1mi = 160934.40cm)
                result = (number / 0.0000062137);
                symbol = "cm";
            }else if(type.equals("Yards") & type2.equals("Km")){
                //Convert from Yards to Kilometers
                result = (number / 1093.6);
                symbol = "km";
            }else if(type.equals("Yards") & type2.equals("Meters")){
                //Convert from Yards to meters
                result = (number / 1.0936);
                symbol = "m";
            }else if(type.equals("Yards") & type2.equals("Miles")){
                //Convert from Yards to miles
                result = (number * 0.00056818);
                symbol = "mi";
            }else if(type.equals("Yards") & type2.equals("Feet")){
                //Convert from Yards to Feet (1yd = 3ft)
                result = (number * 3);
                symbol = "ft";
            }else if(type.equals("Yards") & type2.equals("Inches")){
                //Convert from Yards to Inches (1yd = 36")
                result = (number * 36);
                symbol = "\"";
            }else if(type.equals("Yards") & type2.equals("Centimeters")){
                //Convert from Yards to Centimeters (1yd = 91.44cm)
                result = (number * 91.44);
                symbol = "cm";
            }else if(type.equals("Feet") & type2.equals("Km")){
                //Convert from Feet to Kilometers
                result = (number /3280.8);
                symbol = "km";
            }else if(type.equals("Feet") & type2.equals("Meters")){
                //Convert from Feet to meters
                result = (number / 3.2808);
                symbol = "m";
            }else if(type.equals("Feet") & type2.equals("Miles")){
                //Convert from Feet to Miles
                result = (number * 0.00018939);
                symbol = "mi";
            }else if(type.equals("Feet") & type2.equals("Yards")){
                //Convert from Feet to Yards
                result = (number * 0.33333);
                symbol = "yd";
            }else if(type.equals("Feet") & type2.equals("Inches")){
                //Convert from Feet to Inches (1ft = 12")
                result = (number * 12);
                symbol = "\"";
            }else if(type.equals("Feet") & type2.equals("Centimeters")){
                //Convert from Feet to centimeters
                result = (number / 0.032808);
                symbol = "cm";
            }else if(type.equals("Inches") & type2.equals("Km")){
                //Convert from Inches to Kilometers
                result = (number / 39370);
                symbol = "km";
            }else if(type.equals("Inches") & type2.equals("Meters")){
                //Convert from Inches to meters
                result = (number / 39.370);
                symbol = "m";
            }else if(type.equals("Inches") & type2.equals("Miles")){
                //Convert from Inches to Miles
                result = (number * 0.000015783);
                symbol = "mi";
            }else if(type.equals("Inches") & type2.equals("Yards")){
                //Convert from Inches to Yards
                result = (number * 0.027778);
                symbol = "yd";
            }else if(type.equals("Inches") & type2.equals("Feet")){
                //Convert from Inches to Feet
                result = (number * 0.083333);
                symbol = "ft";
            }else if(type.equals("Inches") & type2.equals("Centimeters")){
                //Convert from Inches to centimeters (1" = 2.54)
                result = (number / 0.39370);
                symbol = "cm";
            }else if(type.equals("Centimeters") & type2.equals("Km")){
                //Convert from Centimeters to Kilometers
                result = (number / 100000);
                symbol = "km";
            }else if(type.equals("Centimeters") & type2.equals("Meters")){
                //Convert from Centimeters to meters
                result = (number / 100);
                symbol = "m";
            }else if(type.equals("Centimeters") & type2.equals("Miles")){
                //Convert from Centimeters to Miles
                result = (number * 0.0000062137);
                symbol = "mi";
            }else if(type.equals("Centimeters") & type2.equals("Yards")){
                //Convert from Centimeters to Yards
                result = (number * 0.010936);
                symbol = "yd";
            }else if(type.equals("Centimeters") & type2.equals("Feet")){
                //Convert from Centimeters to Feet
                result = (number * 0.032808);
                symbol = "ft";
            }else if(type.equals("Centimeters") & type2.equals("Inches")){
                //Convert from Centimeters to Inches
                result = (number * 0.39370);
                symbol = "\"";
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
