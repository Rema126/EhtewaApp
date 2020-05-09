package com.example.ehtewa.ui.Consultants;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultantsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConsultantsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(" ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}