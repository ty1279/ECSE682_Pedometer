package com.example.pedometer.ui.setgoals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SetGoalsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SetGoalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Goal Setting fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}