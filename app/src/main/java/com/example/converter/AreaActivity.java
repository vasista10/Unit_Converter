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

public class AreaActivity extends AppCompatActivity {

    double number;
    double result;
    EditText numInput;
    TextView resOut;
    TextView resTxt;
    String type;
    String type2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        numInput = (EditText) findViewById(R.id.input);
        resOut = (TextView) findViewById(R.id.result);
        resTxt = (TextView) findViewById(R.id.textView2);

        //1st dropdown list Spinner for the input type
        Spinner ddInput = (Spinner) findViewById(R.id.droplist);
        ArrayAdapter<String> areaInput = new ArrayAdapter<String>(AreaActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.areadd));
        areaInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddInput.setAdapter(areaInput);
        ddInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if(position == 0){
                    type = "Square feet";
                }
                else if (position == 1){
                    type = "Square meter";
                }
                else if( position == 2){
                    type = "Square Kilometer";
                }
                else if( position == 3){
                    type = "Square Miles";
                }
                else if( position == 4){
                    type = "Hectares";
                }
                else if( position == 5){
                    type = "Acre";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //There is nothing to write here because always there is something selected
            }
        });

        //2nd dropdown list that will set the units to the result selected
        Spinner ddResult = (Spinner) findViewById(R.id.droplist2);
        ArrayAdapter<String> areaResult = new ArrayAdapter<String>(AreaActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.areadd));
        areaResult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddResult.setAdapter(areaResult);
        ddResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if(position == 0){
                    type2 = "Square feet";
                }
                else if (position == 1){
                    type2 = "Square meter";
                }
                else if( position == 2){
                    type2 = "Square Kilometer";
                }
                else if( position == 3){
                    type2 = "Square Miles";
                }
                else if( position == 4){
                    type2 = "Hectares";
                }
                else if( position == 5){
                    type2 = "Acre";
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
        //Verify if the editText is empty. Displays a message when empty or allow calculations
        String checkInp = numInput.getText().toString();
        if (checkInp.matches("")){
            resTxt.setVisibility(View.INVISIBLE);
            resOut.setText("Insert a number");
        }else{
            resTxt.setVisibility(View.VISIBLE);
            //takes the value from the input and converts it into double to store it on variable number to make the conversions
            number = Double.parseDouble(numInput.getText().toString());
            if (type.equals("Square feet")){
                switch (type2) {
                    case "Square feet":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Square meter":
                        //convert from Square feet to Square meter
                        result = (number / 10.764);
                        symbol = "m2";
                        break;
                    case "Square Kilometer":
                        //convert from Square feet to Square Kilometer ()
                        result = (number / 10764000);
                        symbol = "km2";
                        break;
                    case "Square Miles":
                        //convert from Square feet to Square miles ()
                        result = (number * 0.000000035870);
                        symbol = "mi2";
                        break;
                    case "Hectares":
                        //convert from Square feet to Hectares ()
                        result = (number / 107640);
                        symbol = "ha";
                        break;
                    case "Acre":
                        //convert from Square feet to Acre ()
                        result = (number * 0.000022957);
                        symbol = "ac";
                        break;
                }
            }else if (type.equals("Square meter")){
                switch (type2) {
                    case "Square meter":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Square feet":
                        //Convert from Square meter to Square feet ()
                        result = (number * 10.764);
                        symbol = "ft2";
                        break;
                    case "Square Kilometer":
                        //Convert Square meter to Square Kilometer
                        result = (number / 1000000);
                        symbol = "km2";
                        break;
                    case "Square Miles":
                        //Convert from Square meter to Square Miles ()
                        result = (number * 0.00000038610);
                        symbol = "mi2";
                        break;
                    case "Hectares":
                        //convert from Square meter to Hectares ()
                        result = (number / 10000);
                        symbol = "ha";
                        break;
                    case "Acre":
                        //Convert from Square meter to Acre ()
                        result = (number * 0.00024711);
                        symbol = "ac";
                        break;
                }
            }else if (type.equals("Square Kilometer")){
                switch (type2) {
                    case "Square Kilometer":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Square feet":
                        //Convert from Square Kilometer to Square feet ()
                        result = (number * 10764000);
                        symbol = "ft2";
                        break;
                    case "Square meter":
                        //Convert from Square Kilometer to Square meter ()
                        result = (number / 0.0000010000);
                        symbol = "m2";
                        break;
                    case "Square Miles":
                        //Convert from Square Kilometer to Square Miles ()
                        result = (number * 0.38610);
                        symbol = "mi2";
                        break;
                    case "Hectares":
                        //convert from Square Kilometer to Hectares ()
                        result = (number / 0.010000);
                        symbol = "ha";
                        break;
                    case "Acre":
                        //Convert from Square Kilometer to Acre (1Month = 730.0008h)
                        result = (number * 247.11);
                        symbol = "ac";
                        break;
                }
            }else if (type.equals("Square Miles")){
                switch (type2) {
                    case "Square Miles":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Square feet":
                        //Convert from Square Miles to Square feet ()
                        result = (number * 27878000);
                        symbol = "ft2";
                        break;
                    case "Square meter":
                        //Convert from Square Miles to Square meter ()
                        result = (number / 0.00000038610);
                        symbol = "m2";
                        break;
                    case "Square Kilometer":
                        //Convert from Square Miles to Square Kilometer ()
                        result = (number / 0.38610);
                        symbol = "km2";
                        break;
                    case "Hectares":
                        //convert from Square Miles to Hectares
                        result = (number / 0.0038610);
                        symbol = "ha";
                        break;
                    case "Acre":
                        //Convert from Square Miles to Acre ()
                        result = (number * 640);
                        symbol = "ac";
                        break;
                }
            }else if (type.equals("Hectares")){
                switch (type2) {
                    case "Hectares":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Square feet":
                        //Convert from Hectares to Square feet ()
                        result = (number * 107640);
                        symbol = "ft2";
                        break;
                    case "Square meter":
                        //Convert from Hectares to Square meter ()
                        result = (number / 0.00010000);
                        symbol = "m2";
                        break;
                    case "Square Kilometer":
                        //Convert from Hectares to Square Kilometer ()
                        result = (number * 100);
                        symbol = "km2";
                        break;
                    case "Square Miles":
                        //convert from Hectares to Square Miles ()
                        result = (number * 0.0038610);
                        symbol = "mi2";
                        break;
                    case "Acre":
                        //Convert from Hectares to Acre ()
                        result = (number * 2.4711);
                        symbol = "ac";
                        break;
                }
            }else if (type.equals("Acre")){
                switch (type2) {case "Acre":
                    //no conversion
                    symbol = "Equal";
                    break;
                    case "Square feet":
                        //Convert from Acre to Square feet ()
                        result = (number * 43560);
                        symbol = "ft2";
                        break;
                    case "Square meter":
                        //Convert from Acre to Square meter ()
                        result = (number / 0.00024711);
                        symbol = "m2";
                        break;
                    case "Square Kilometer":
                        //Convert from Acre to Square Kilometer ()
                        result = (number / 247.11);
                        symbol = "km2";
                        break;
                    case "Square Mile":
                        //convert from Acre to Square Mile
                        result = (number * 0.0015625);
                        symbol = "mi2";
                        break;
                    case "Hectares":
                        //Convert from Acre to Hectares ()
                        result = (number / 2.4711);
                        symbol = "ha";
                        break;
                }
            }
            //check if the symbol is different by checking that it is not Equal
            if (symbol.equals("Equal")){
                resOut.setText("Select different Units");
            }else {
                resTxt.setVisibility(View.VISIBLE);
                if (result < 0.000001) {
                    resOut.setText(String.format("%.10f " + symbol + ".", result)); //result printed out with 10 decimal places for very small results
                } else if (result < 0.01) {
                    resOut.setText(String.format("%.5f " + symbol + ".", result)); //result printed out with 5 decimal places for small results
                } else {
                    resOut.setText(String.format("%.2f " + symbol + ".", result)); //result printed out with 2 decimal places
                }
            }
        }
    }
}
