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

public class CurrencyActivity extends AppCompatActivity {
    float number;
    double result;
    EditText numInput;
    TextView resOut;
    TextView resTxt;
    TextView date;
    String type;
    String type2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        numInput = (EditText) findViewById(R.id.input);
        resOut = (TextView) findViewById(R.id.result);
        resTxt = (TextView) findViewById(R.id.textView2);
        date = (TextView) findViewById(R.id.date);

        //1st dropdown list Spinner for the input type
        Spinner ddInput = (Spinner) findViewById(R.id.droplist);
        ArrayAdapter<String> currencyInput = new ArrayAdapter<String>(CurrencyActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currencydd));
        currencyInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddInput.setAdapter(currencyInput);
        ddInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if(position == 0){
                    type = "GBP";
                }else if (position == 1){
                    type = "Euro";
                }
                else if( position == 2){
                    type = "Dollar";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //There is nothing to write here because always there is something selected
            }
        });

        //2nd dropdown list that will set the units to the result selected
        Spinner ddResult = (Spinner) findViewById(R.id.droplist2);
        ArrayAdapter<String> currencyResult = new ArrayAdapter<String>(CurrencyActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currencydd));
        currencyResult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddResult.setAdapter(currencyResult);
        ddResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if(position == 0){
                    type2 = "GBP";
                }else if (position == 1){
                    type2 = "Euro";
                }
                else if( position == 2){
                    type2 = "Dollar";
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
        date.setVisibility(View.INVISIBLE);
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
        String update = "(rate updated on 25/04/2020)";

        //Verify if the editText is empty
        String checkInp = numInput.getText().toString();
        if (checkInp.matches("")){
            resTxt.setVisibility(View.INVISIBLE);
            resOut.setText("Insert a number");
        }else{
            resTxt.setVisibility(View.VISIBLE);
            date.setVisibility(View.VISIBLE);
            //takes the value from the input and converts it into integer to store it on variable number
            number = Float.parseFloat(numInput.getText().toString());
            if (type.equals("GBP") & type2.equals("Euro")){
                //Convert from GBP to Euro (1GBP = 1.14E)
                result = (number * 1.14);
                symbol = "€";
            }else if(type.equals("GBP") & type2.equals("Dollar")){
                //Convert from GBP to Dollar (1GBP = 1.23dollar)
                result = (number * 1.24);
                symbol = "$";
            }else if(type.equals("Euro") & type2.equals("GBP")){
                //Convert from Euro to GBP (1 euro = 0.88 GBP)
                result = (number * 0.88);
                symbol = "£";
            }else if(type.equals("Euro") & type2.equals("Dollar")){
                //Convert from Euro to Dollar (1eur = 1.08)
                result = (number * 1.08);
                symbol = "$";
            }else if(type.equals("Dollar") & type2.equals("GBP")){
                //Convert Dollar to GBP (1dollar = 0.81)
                result = (number * 0.81);
                symbol = "£";
            }else if(type.equals("Dollar") & type2.equals("Euro")){
                //Convert Dollar to Euro (1dollar = 0.92eur)
                result = (number * 0.92);
                symbol = "€";
            }else if(type.equals(type2)){
                symbol = "Equal";
            }
            //check if the symbol is different by checking that it is not Equal
            if (symbol.equals("Equal")){
                resOut.setText("Please, select different Units");
            }else{
                resTxt.setVisibility(View.VISIBLE);
                resOut.setText(String.format("%.2f " + symbol + ".",result)); //result printed out with 2 decimal places
                date.setText(update);
            }
        }
    }
}
