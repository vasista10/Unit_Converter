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

public class VolumeActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_volume);

        numInput = (EditText) findViewById(R.id.input);
        resOut = (TextView) findViewById(R.id.result);
        resTxt = (TextView) findViewById(R.id.textView2);

        //1st dropdown list Spinner for the input type
        Spinner ddInput = (Spinner) findViewById(R.id.droplist);
        ArrayAdapter<String> volumeInput = new ArrayAdapter<String>(VolumeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.volumedd));
        volumeInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddInput.setAdapter(volumeInput);
        ddInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if (position == 0){
                    type = "l";
                }else if (position == 1){
                    type = "Hectolitres";
                }
                else if (position == 2){
                    type = "Pints";
                }
                else if (position == 3){
                    type = "Gallon";
                }
                else if (position == 4){
                    type = "Ounces";
                }
                else if (position == 5){
                    type = "Quarts";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //There is nothing to write here because always there is something selected
            }
        });

        //2nd dropdown list that will set the units to the result selected
        Spinner ddResult = (Spinner) findViewById(R.id.droplist2);
        ArrayAdapter<String> volumeResult = new ArrayAdapter<String>(VolumeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.volumedd));
        volumeResult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddResult.setAdapter(volumeResult);
        ddResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if (position == 0){
                    type2 = "l";
                }else if (position == 1){
                    type2 = "Hectolitres";
                }
                else if (position == 2){
                    type2 = "Pints";
                }
                else if (position == 3){
                    type2 = "Gallon";
                }
                else if (position == 4){
                    type2 = "Ounces";
                }
                else if (position == 5){
                    type2 = "Quarts";
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
            number = Float.parseFloat(numInput.getText().toString()); //takes the value from the input and converts it into integer to store it on variable number
            if (type.equals("l") & type2.equals("Hectolitres")){
                //Convert from litres to hectolitres
                result = (number / 100);
                symbol = "Hl";
            }else if(type.equals("l") & type2.equals("Pints")){
                //Convert from litres to Pints
                result = (number * 1.7598);
                symbol = "pt";
            }else if(type.equals("l") & type2.equals("Gallon")){
                //Convert from litres to gallon (1 gallon = 4.54609)
                result = (number / 4.54609);
                symbol = "gal";
            }else if(type.equals("l") & type2.equals("Ounces")){
                //Convert from litres to fluid ounces (1 litre = 35.195 fl oz)
                result = (number * 35.195);
                symbol = "fl oz";
            }else if(type.equals("l") & type2.equals("Quarts")){
                //Convert from litres to quarts (1 litre = 1.0569)
                result = (number / 1.137);
                symbol = "qt";
            }else if(type.equals("Hectolitres") & type2.equals("l")){
                //Convert hectolitres to litres
                result = (number * 100);
                symbol = "l";
            }else if(type.equals("Hectolitres") & type2.equals("Pints")){
                //Convert hectolitres to Pints (1Hl = 175.975)
                result = (number * 175.975);
                symbol = "pt";
            }else if(type.equals("Hectolitres") & type2.equals("Gallon")){
                //Convert hectolitres to gallons (1Hl = 21.9969)
                result = (number * 21.9969);
                symbol = "g";
            }else if(type.equals("Hectolitres") & type2.equals("Ounces")){
                //Convert hectolitres to Ounces (1Hl = 3519.51)
                result = (number * 3519.51);
                symbol = "fl oz";
            }else if(type.equals("Hectolitres") & type2.equals("Quarts")){
                //Convert hectolitres to quarts (1 Hl = 87.988)
                result = (number * 87.988);
                symbol = "qt";
            }else if(type.equals("Pints") & type2.equals("l")){
                //Convert pints to litres
                result = (number / 1.7598);
                symbol = "l";
            }else if(type.equals("Pints") & type2.equals("Hectolitres")){
                //Convert pints to hectolitres
                result = (number / 175.98);
                symbol = "Hl";
            }else if(type.equals("Pints") & type2.equals("Gallon")){
                //Convert pints to gallon (1 gallon = 8 pints)
                result = (number / 8);
                symbol = "gal";
            }else if(type.equals("Pints") & type2.equals("Ounces")){
                //Convert pints to fluid ounces (1pt = 20 fl oz)
                result = (number * 20);
                symbol = "fl oz";
            }else if(type.equals("Pints") & type2.equals("Quarts")){
                //Convert pints to quarts (1 quart = 2pt)
                result = (number / 2);
                symbol = "qt";
            }else if(type.equals("Gallon") & type2.equals("l")){
                //Convert gallon to litres (1 gal = 4.54609 l)
                result = (number * 4.54609);
                symbol = "l";
            }else if(type.equals("Gallon") & type2.equals("Hectolitres")){
                //Convert gallon to hectolitres (get litres / 100)
                result = ((number * 4.54609) / 100);
                symbol = "Hl";
            }else if(type.equals("Gallon") & type2.equals("Pints")){
                //Convert gallon to Pints (1 gallon = 8 pints)
                result = (number * 8);
                symbol = "pt";
            }else if(type.equals("Gallon") & type2.equals("Ounces")){
                //Convert gallon to fluid ounces (1 gallon = 160 fl oz)
                result = (number * 160);
                symbol = "fl oz";
            }else if(type.equals("Gallon") & type2.equals("Quarts")){
                //Convert gallon to Quarts (1gal = 4qt)
                result = (number * 4);
                symbol = "qt";
            }else if(type.equals("Ounces") & type2.equals("l")){
                //Convert ounces to litres (l = fl oz * 35.195)
                result = (number / 35.195);
                symbol = "l";
            }else if(type.equals("Ounces") & type2.equals("Hectolitres")){
                //Convert ounces to hectolitres (1 fl oz = 0.0002841306 Hl)
                result = (number * 0.0002841306);
                symbol = "Hl";
            }else if(type.equals("Ounces") & type2.equals("Pints")){
                //Convert ounces to pints (1 fl oz = 0.05 pints)
                result = (number * 0.05);
                symbol = "pt";
            }else if(type.equals("Ounces") & type2.equals("Gallon")){
                //Convert ounces to gallon (1 fl oz = 1/160 (0.00625‬ gal))
                result = (number * 0.00625);
                symbol = "gal";
            }else if(type.equals("Ounces") & type2.equals("Quarts")){
                //Convert ounces to quarts (1 fl oz = 1/40 (0.025 qt))
                result = (number * 0.025);
                symbol = "qt";
            }else if(type.equals("Quarts") & type2.equals("l")){
                //Convert quarts to litres (1qt = 1.13652 l)
                result = (number * 1.13652);
                symbol = "l";
            }else if(type.equals("Quarts") & type2.equals("Hectolitres")){
                //Convert quarts to hectolitres (1qt = 0.011365225 Hl)
                result = (number * 0.011365225);
                symbol = "Hl";
            }else if(type.equals("Quarts") & type2.equals("Pints")){
                //Convert quarts to pints (1qt = 2 pt)
                result = (number * 2);
                symbol = "pt";
            }else if(type.equals("Quarts") & type2.equals("Gallon")){
                //Convert quarts to gallon (1qt = 0.25 gal)
                result = (number / 0.25);
                symbol = "gal";
            }else if(type.equals("Quarts") & type2.equals("Ounces")){
                //Convert quarts to ounces (1qt = 40 fl oz)
                result = (number * 40);
                symbol = "fl oz";
            }else if(type.equals(type2)){
                //checking that both types selected are the shame
                symbol = "Equal";
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
