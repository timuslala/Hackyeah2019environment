package com.example.hackyeah;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_trash, R.id.nav_energy, R.id.nav_water)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void onSubmitButtonClick(View view)
    {
        try {
            DecimalFormat df = new DecimalFormat("#.##");
            EditText waterFormS = (EditText)findViewById(R.id.inputWater);
            Double waterS = Double.valueOf(waterFormS.getText().toString());
            EditText litresForm = (EditText)findViewById(R.id.inputLitres);
            Double litres = Double.valueOf(litresForm.getText().toString());
            EditText secondsForm = (EditText)findViewById(R.id.inputSeconds);
            Integer seconds = Integer.valueOf(secondsForm.getText().toString());
            EditText minutesForm = (EditText)findViewById(R.id.inputMinutes);
            Integer minutes = Integer.valueOf(minutesForm.getText().toString());

            double litresPerSecond = litres/waterS;
            double howMuchWouldItUse = litresPerSecond*(minutes*60+seconds);

            TextView textResult = (TextView)findViewById(R.id.textResult);
            textResult.setText("Your water flowrate is: "+df.format(litresPerSecond)+"litres/s."+System.getProperty("line.separator")+"It would use around: "+df.format(howMuchWouldItUse)+"litres."+
                    System.getProperty("line.separator")+"Assuming you drunk 3litres per day it would last you for around: "+Math.round(howMuchWouldItUse/3)+"days.");
        } catch (Exception e)
        {
            TextView textResult = (TextView)findViewById(R.id.textResult);
            textResult.setText("Check if all spaces are filled. Are there any impossible numbers?");
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view != null && view instanceof EditText) {
                Rect r = new Rect();
                view.getGlobalVisibleRect(r);
                int rawX = (int)ev.getRawX();
                int rawY = (int)ev.getRawY();
                if (!r.contains(rawX, rawY)) {
                    view.clearFocus();
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    class DoneOnEditorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        }
    }
    public class SampleActivity extends Activity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main); // sample_activity_layout contains our target EditText, target_edittext

            EditText targetEditText = (EditText)findViewById(R.id.inputWater);
            targetEditText.setOnEditorActionListener(new MainActivity.DoneOnEditorActionListener());

            // The rest of the onCreate() code
        }
    }


    public void onSubmitButtonClickPower(View view) {
        try {
            DecimalFormat df = new DecimalFormat("#.##");
            EditText priceOfkWhForm = (EditText)findViewById(R.id.inputPriceOfkWh);
            Double priceOfkWh = Double.valueOf(priceOfkWhForm.getText().toString());
            EditText hoursOfUsePerDayForm = (EditText)findViewById(R.id.inputHoursOfUsePerDay);
            Double hoursOfUsePerDay = Double.valueOf(hoursOfUsePerDayForm.getText().toString());
            EditText powerDrawnForm = (EditText)findViewById(R.id.inputPowerDrawn);
            int powerDrawn = Integer.valueOf(powerDrawnForm.getText().toString());



            TextView textResultPower = (TextView)findViewById(R.id.resultPowerCalc);
            textResultPower.setText("Price of energy consumed per day: "+ df.format(priceOfkWh*hoursOfUsePerDay*powerDrawn*0.001)+"(your currency)."+System.getProperty("line.separator")+
                    "Price of energy consumed per week: "+ df.format(priceOfkWh*hoursOfUsePerDay*powerDrawn*0.001*7)+"(your currency)."+System.getProperty("line.separator")+
                    "Price of energy consumed per month: "+ df.format(priceOfkWh*hoursOfUsePerDay*powerDrawn*0.001*30)+"(your currency).");
        } catch (Exception e)
        {
            TextView textResult = (TextView)findViewById(R.id.resultPowerCalc);
            textResult.setText("Check if all spaces are filled. Are there any impossible numbers?");
        }

    }



}




