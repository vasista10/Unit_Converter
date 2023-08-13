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
import android.widget.Toast;

public class WeightActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_weight);

        numInput = (EditText) findViewById(R.id.input);
        resOut = (TextView) findViewById(R.id.result);
        resTxt = (TextView) findViewById(R.id.textView2);

        //1st dropdown list Spinner for the input type
        Spinner ddInput = (Spinner) findViewById(R.id.droplist);
        ArrayAdapter<String> weightInput = new ArrayAdapter<String>(WeightActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.weightdd));
        weightInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddInput.setAdapter(weightInput);
        ddInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if (position == 0){
                    type = "Kg";
                }else if (position == 1){
                    type = "g";
                }
                else if (position == 2){
                    type = "Pound";
                }
                else if (position == 3){
                    type = "Ounces";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //There is nothing to write here because always there is something selected
            }
        });

        //2nd dropdown list that will set the units to the result selected
        Spinner ddResult = (Spinner) findViewById(R.id.droplist2);
        ArrayAdapter<String> weightResult = new ArrayAdapter<String>(WeightActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.weightdd));
        weightResult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddResult.setAdapter(weightResult);
        ddResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if (position == 0){
                    type2 = "Kg";
                }else if (position == 1){
                    type2 = "g";
                }
                else if (position == 2){
                    type2 = "Pound";
                }
                else if (position == 3){
                    type2 = "Ounces";
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
        }else {
            number = Float.parseFloat(numInput.getText().toString()); //takes the value from the input and converts it into integer to store it on variable number
            if (type.equals("Kg") & type2.equals("g")){
                //Convert from Kilograms to grams
                result = (number * 1000);
                symbol = "g";
            }else if(type.equals("Kg") & type2.equals("Pound")){
                //Convert from kilograms to Pounds
                result = (number * 2.2046226218);
                symbol = "lb";
            }else if(type.equals("Kg") & type2.equals("Ounces")){
                //Convert from kilograms to Ounces
                result = (number * 35.2739619496);
                symbol = "oz";
            }else if(type.equals("g") & type2.equals("Kg")){
                //Convert from grams to Kilograms
                result = (number / 1000);
                symbol = "Kg";
            }else if(type.equals("g") & type2.equals("Pound")){
                //Convert from grams to Pounds
                result = (number * 0.0022046226);
                symbol = "lb";
            }else if(type.equals("g") & type2.equals("Ounces")){
                //Convert grams to Ounces
                result = (number * 0.0352739619);
                symbol = "oz";
            }else if(type.equals("Pound") & type2.equals("Kg")){
                //Convert pounds to Kilograms
                result = (number * 0.45359237);
                symbol = "Kg";
            }else if(type.equals("Pound") & type2.equals("g")){
                //Convert pounds to grams
                result = (number * 453.59237);
                symbol = "g";
            }else if(type.equals("Pound") & type2.equals("Ounces")){
                //Convert pounds to Ounces
                result = (number * 16);
                symbol = "oz";
            }else if(type.equals("Ounces") & type2.equals("Kg")){
                //Convert ounces to Kilograms
                result = (number * 0.028349523125);
                symbol = "Kg";
            }else if(type.equals("Ounces") & type2.equals("g")){
                //Convert ounces to grams
                result = (number * 28.349523125);
                symbol = "g";
            }else if(type.equals("Ounces") & type2.equals("Pound")){
                //Convert ounces to pounds
                result = (number * 0.0625);
                symbol = "lb";
            }else if(type.equals(type2)){
                //checking that both types selected are the shame
                symbol = "Equal";
            }
            //check if the symbol is different by checking that it is not Equal
            if (symbol.equals("Equal")){
                resOut.setText("Select different Units");
            }
            else {
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
