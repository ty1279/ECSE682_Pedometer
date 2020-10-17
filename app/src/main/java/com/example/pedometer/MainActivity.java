package com.example.pedometer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedometer.ui.dashboard.DashboardViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView textView5;
    TextView stepCountView;
    TextView goalView;
    SensorManager sensorManager;
    boolean running = false;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setText("0 Steps");

//        TextView stepCountView = (TextView) findViewById(R.id.textView); // Text View from Dashboard menu
//        stepCountView.setText("0 Steps");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, sensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            textView5.setText(String.valueOf(event.values[0]) + " Steps");     // Text View from Home menu
            String steps = String.valueOf(event.values[0]);   //Get step count from MainActivity.java

//            String stepText = "";
//            stepText = steps + (" steps /");

//            ConstraintLayout dash = (ConstraintLayout) findViewById(R.id.dashboardLayout);
//            TextView stepCountView = (TextView) dash.findViewById(R.id.textView); // Text View from Dashboard menu
//            stepCountView.setText(stepText);

            TextView goalView = (TextView) dash.findViewById(R.id.goalView); // Text View from Dashboard menu
            // Get "goal steps" from goal setting page
            goalView.setText(" steps");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}