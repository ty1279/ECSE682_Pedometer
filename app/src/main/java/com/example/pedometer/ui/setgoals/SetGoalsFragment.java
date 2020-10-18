package com.example.pedometer.ui.setgoals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pedometer.MainActivity;
import com.example.pedometer.R;
import com.example.pedometer.ui.dashboard.DashboardFragment;
import com.example.pedometer.ui.setgoals.SetGoalsViewModel;

public class SetGoalsFragment extends Fragment {
    Button btn;
    EditText et;
    String st;

    private SetGoalsViewModel setGoalsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setGoalsViewModel =
                ViewModelProviders.of(this).get(SetGoalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setgoals, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        setGoalsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btn = root.findViewById(R.id.button);
        et = root.findViewById(R.id.name_edit_text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {    // Send goal count to Dashboard when button is clicked
                Bundle bundle = new Bundle();
                st = et.getText().toString();
                bundle.putString("key", st);
                MainActivity activity = (MainActivity) getActivity();
                activity.saveData(1, bundle);
            }
        });

        return root;
    }
}