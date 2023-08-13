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

import org.w3c.dom.Text;

public class TemperatureActivity extends AppCompatActivity {

    //creating the variable where we will store the information from the user
    float number;
    double result;
    //variable from where we will get the input value
    EditText numInput;
    //variable to display the result obtained
    TextView resOut;
    TextView resTxt;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        numInput = (EditText) findViewById(R.id.input);
        resOut = (TextView) findViewById(R.id.result);
        resTxt = (TextView) findViewById(R.id.textView2);

        //dropdown list Spinner
        Spinner ddInput = (Spinner) findViewById(R.id.droplist);
        ArrayAdapter<String> tempInput = new ArrayAdapter<String>(TemperatureActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tempdd));
        tempInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddInput.setAdapter(tempInput);
        ddInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){
                    //The user input is Fahrenheit, so we need the celsius
                    type = "Fahrenheit";
                }else{
                    //In this case, the other option is the user inputs celsius
                    type = "Celsius";
                }
            }
            //Nothing selected is never in use
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        resTxt.setVisibility(View.VISIBLE);
        //take values and do calculations. then update the result field
        String symbol = " ";
        //Verify if the editText is empty. Displays a message when empty or allow calculations
        String checkInp = numInput.getText().toString();
        if (checkInp.matches("")){
            resTxt.setVisibility(View.INVISIBLE);
            resOut.setText("Insert a number");
        }else {
            resTxt.setVisibility(View.VISIBLE);
            //takes the value from the input and converts it into double to store it on variable number to make the conversions
            number = Float.parseFloat(numInput.getText().toString()); //to obtain a integer from a string value
            if (type.equals("Fahrenheit")){
                result = (((number-32)*5) /9);
                symbol = (char) 0x00B0 + "C"; // Variable to generate the symbol to display in case of degrees
            }else if (type.equals("Celsius")){
                result = (float) ((1.8*number) + 32);
                symbol = (char) 0x00B0 + "F"; //Variable to generate the symbol to display in case of Farenheit
            }
            resOut.setText(String.format("%.1f " + symbol + "." ,result)); // The string to display the result with 1 decimal
        }
    }
}
