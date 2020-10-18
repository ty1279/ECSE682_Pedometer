package com.example.pedometer.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            // Display Goal count
            Bundle savedData = activity.getSavedData();
            String data = savedData.getString("key");
            if (data == null)
                data = "";
            goalView.setText(data + " Steps");

            // Display current step count
            String steps = "";
            MainActivity application = (MainActivity) getActivity();
            steps = application.steps;
            if (steps == null)
                steps = "0";
            textView.setText(steps + " / ");
        }

        return root;
    }
}
