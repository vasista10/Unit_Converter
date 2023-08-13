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

public class TimeActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_time);

        numInput = (EditText) findViewById(R.id.input);
        resOut = (TextView) findViewById(R.id.result);
        resTxt = (TextView) findViewById(R.id.textView2);

        //1st dropdown list Spinner for the input type
        Spinner ddInput = (Spinner) findViewById(R.id.droplist);
        ArrayAdapter<String> timeInput = new ArrayAdapter<String>(TimeActivity.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.timedd));
        timeInput.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddInput.setAdapter(timeInput);
        ddInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if(position == 0){
                    type = "Second";
                }else if (position == 1){
                    type = "Minute";
                }
                else if( position == 2){
                    type = "Hour";
                }
                else if( position == 3){
                    type = "Day";
                }
                else if( position == 4){
                    type = "Week";
                }
                else if( position == 5){
                    type = "Month";
                }
                else if( position == 6){
                    type = "Year";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //There is nothing to write here because always there is something selected
            }
        });

        //2nd dropdown list that will set the units to the result selected
        Spinner ddResult = (Spinner) findViewById(R.id.droplist2);
        ArrayAdapter<String> timeResult = new ArrayAdapter<String>(TimeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.timedd));
        timeResult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddResult.setAdapter(timeResult);
        ddResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When a Item is selected, that generates a type for the different units
                if(position == 0){
                    type2 = "Second";
                }
                else if (position == 1){
                    type2 = "Minute";
                }
                else if( position == 2){
                    type2 = "Hour";
                }
                else if( position == 3){
                    type2 = "Day";
                }
                else if( position == 4){
                    type2 = "Week";
                }
                else if( position == 5){
                    type2 = "Month";
                }
                else if( position == 6){
                    type2 = "Year";
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
            number = Double.parseDouble(numInput.getText().toString()); //takes the value from the input and converts it into double to store it on variable number
            if (type.equals("Second")){
                switch (type2) {
                    case "Second":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Minute":
                        //convert from seconds to min
                        result = (number / 60);
                        symbol = "\'";
                        break;
                    case "Hour":
                        //convert from seconds to Hour (60*60=3600)
                        result = (number / 3600);
                        symbol = "h";
                        break;
                    case "Day":
                        //convert from seconds to day (3600*24=86400)
                        result = (number / 86400);
                        symbol = "days";
                        break;
                    case "Week":
                        //convert from seconds to week (86400*7=604800)
                        result = (number / 604800);
                        symbol = "weeks";
                        break;
                    case "Month":
                        //convert from seconds to month (there is 2628000s in a month (s in a day * 30.416 average days per month))
                        result = (number / 2628000);
                        symbol = "Months";
                        break;
                    case "Year":
                        //convert from seconds to year (there is 31536000s in a year (s in a day * 365 days))
                        result = (number / 31536000);
                        symbol = "Year";
                        break;
                }
            }else if (type.equals("Minute")){
                switch (type2) {
                    case "Minute":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Second":
                        //Convert from min to seconds
                        result = (number * 60);
                        symbol = "\"";
                        break;
                    case "Hour":
                        //Convert from min to Hour
                        result = (number / 60);
                        symbol = "h";
                        break;
                    case "Day":
                        //Convert from min to Day (60min in an h *24h = 1440min per day)
                        result = (number / 1440);
                        symbol = "days";
                        break;
                    case "Week":
                        //convert from min to week (min per day * 7 days= 10080min in a week)
                        result = (number / 10080);
                        symbol = "weeks";
                        break;
                    case "Month":
                        //Convert from min to Month (min per day * 30.416= 43799min in a month)
                        result = (number / 43799);
                        symbol = "months";
                        break;
                    case "Year":
                        //convert from min to Year (min per day * 365 = 525600 min in a year)
                        result = (number / 525600);
                        symbol = "Years";
                        break;
                }
            }else if (type.equals("Hour")){
                switch (type2) {
                    case "Hour":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Second":
                        //Convert from Hour to seconds (1h = 3600s)
                        result = (number * 3600);
                        symbol = "\"";
                        break;
                    case "Minute":
                        //Convert from Hour to min (1h = 60min)
                        result = (number * 60);
                        symbol = "\'";
                        break;
                    case "Day":
                        //Convert from Hour to Day (1day = 24h)
                        result = (number / 24);
                        symbol = "days";
                        break;
                    case "Week":
                        //convert from Hour to week (1week = 168h)
                        result = (number / 168);
                        symbol = "weeks";
                        break;
                    case "Month":
                        //Convert from Hour to Month (1Month = 730.0008h)
                        result = (number / 730);
                        symbol = "months";
                        break;
                    case "Year":
                        //convert from Hour to Year (730h in a month * 12 month= 8760h in a year)
                        result = (number / 8760);
                        symbol = "Years";
                        break;
                }
            }else if (type.equals("Day")){
                switch (type2) {
                    case "Day":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Second":
                        //Convert from Day to seconds (1day = 86400s)
                        result = (number * 86400);
                        symbol = "\"";
                        break;
                    case "Minute":
                        //Convert from Day to min (1day = 1440min)
                        result = (number * 1440);
                        symbol = "\'";
                        break;
                    case "Hour":
                        //Convert from Day to Hour (1day = 24h)
                        result = (number * 24);
                        symbol = "h";
                        break;
                    case "Week":
                        //convert from Day to week
                        result = (number / 7);
                        symbol = "weeks";
                        break;
                    case "Month":
                        //Convert from Day to Month (1Month = 30.416days)
                        result = (number / 30.416);
                        symbol = "months";
                        break;
                    case "Year":
                        //convert from Day to Year (1year = 365days)
                        result = (number / 365);
                        symbol = "Years";
                        break;
                }
            }else if (type.equals("Week")){
                switch (type2) {
                    case "Week":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Second":
                        //Convert from Week to seconds (1week = 604800s)
                        result = (number * 604800);
                        symbol = "\"";
                        break;
                    case "Minute":
                        //Convert from Week to min (1week = 10080min)
                        result = (number * 10080);
                        symbol = "\'";
                        break;
                    case "Hour":
                        //Convert from Week to Hour (1week = 168h)
                        result = (number * 168);
                        symbol = "h";
                        break;
                    case "Day":
                        //convert from Week to day (1week = 7days)
                        result = (number * 7);
                        symbol = "weeks";
                        break;
                    case "Month":
                        //Convert from Week to Month (1week = 0.230137Months)
                        result = (number * 0.230137);
                        symbol = "months";
                        break;
                    case "Year":
                        //convert from Week to Year (1week = 0.0191781 years)
                        result = (number * 0.0191781);
                        symbol = "Years";
                        break;
                }
            }else if (type.equals("Month")){
                switch (type2) {
                    case "Month":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Second":
                        //Convert from Month to seconds (1Month = 2627942.4s)
                        result = (number * 2627942.4);
                        symbol = "\"";
                        break;
                    case "Minute":
                        //Convert from Month to min (1month = 43799.04min)
                        result = (number * 43799.04);
                        symbol = "\'";
                        break;
                    case "Hour":
                        //Convert from Month to Hour (1 month = 730h)
                        result = (number * 730);
                        symbol = "h";
                        break;
                    case "Day":
                        //convert from Month to day
                        result = (number * 30.416);
                        symbol = "days";
                        break;
                    case "Week":
                        //Convert from Month to Week (1month = 4.345weeks)
                        result = (number * 4.345);
                        symbol = "weeks";
                        break;
                    case "Year":
                        //convert from Month to Year (1year = 12month)
                        result = (number / 12);
                        symbol = "Years";
                        break;
                }
            }else if (type.equals("Year")){
                switch (type2) {
                    case "Year":
                        //no conversion
                        symbol = "Equal";
                        break;
                    case "Second":
                        //Convert from Year to seconds (1year = 31536034.56s)
                        result = (number * 31536034);
                        symbol = "\"";
                        break;
                    case "Minute":
                        //Convert from Year to min (1year = 525600min)
                        result = (number * 525600);
                        symbol = "\'";
                        break;
                    case "Hour":
                        //Convert from Year to Hour (1year = 8760h)
                        result = (number * 8760);
                        symbol = "h";
                        break;
                    case "Day":
                        //convert from Year to day (1year = 365days)
                        result = (number * 365);
                        symbol = "days";
                        break;
                    case "Week":
                        //Convert from Year to Week (1year = 52.1429weeks)
                        result = (number * 52.1429);
                        symbol = "weeks";
                        break;
                    case "Month":
                        //convert from Year to Month (1year = 12months)
                        result = (number * 12);
                        symbol = "months";
                        break;
                }
            }
            //check if the symbol is different by checking that it is not Equal
            if (symbol.equals("Equal")){
                resOut.setText("Select different Units");
            }else {
                resTxt.setVisibility(View.VISIBLE);
                if (result < 0.0000000001) {
                    resOut.setText(String.format("%.15f " + symbol + ".", result)); //result printed out with 15 decimal places for very small results
                } else if (result < 0.000001) {
                    resOut.setText(String.format("%.10f " + symbol + ".", result)); //result printed out with 10 decimal places for small results
                }else if (result < 0.01) {
                    resOut.setText(String.format("%.5f " + symbol + ".", result)); //result printed out with 5 decimal places for small results
                } else {
                    resOut.setText(String.format("%.2f " + symbol + ".", result)); //result printed out with 2 decimal places
                }
            }
        }
    }
}
