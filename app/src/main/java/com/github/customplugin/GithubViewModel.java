package com.github.customplugin;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GithubViewModel extends ViewModel {
    private MutableLiveData<User> mUserLiveData = new MutableLiveData<>();

    public void updateUser() {
        mUserLiveData.postValue(null);
    }
}
