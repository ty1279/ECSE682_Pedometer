package com.example.pedometer.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pedometer.MainActivity;
import com.example.pedometer.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    TextView goalView;
    TextView textView;

    ProgressBar determinateBar;
    TextView maxValue;

    TextView distance;
    TextView calories;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        goalView = root.findViewById(R.id.goalView);
        textView = root.findViewById(R.id.textView);

        MainActivity activity = (MainActivity) getActivity();
        if (activity.getSavedData() == null){
            ;
        }

        else{
            // Display current step count
            String steps = "";
            MainActivity application = (MainActivity) getActivity();
            steps = application.steps;
            if (steps == null)
                steps = "0";
            textView.setText(steps + " / ");

            // Display Goal count
            Bundle savedData = activity.getSavedData();
            String data = savedData.getString("key");
            if (data == null)
                data = "";

            goalView.setText(data + " Steps");

            // Progress bar
            int goal_data = Integer.parseInt(data);
            int steps_in_integer = Integer.parseInt(steps);
            System.out.println("##### test " + goal_data);
            System.out.println("##### steps " + steps_in_integer);
            maxValue  = root.findViewById(R.id.MaxValue);
            determinateBar= root.findViewById(R.id.determinateBar);
            determinateBar.setMax(goal_data);
            maxValue.setText(String.valueOf(goal_data));

            determinateBar.setProgress(steps_in_integer);

            // Distance text
            distance =  root.findViewById(R.id.distance_text);
            // Calories text
            calories =  root.findViewById(R.id.calories_text);
            // 1 step = 0.0008 km walking = 0.061 cal
            distance.setText("Distance: " + (steps_in_integer * 0.0008) + " km");
            calories.setText("Calories: " + (steps_in_integer * 0.061) + " cal");

        }
        return root;
    }
}
