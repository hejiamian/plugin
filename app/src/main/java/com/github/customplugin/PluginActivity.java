package com.github.customplugin;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.github.customplugin.databinding.ActivityPluginBinding;

public class PluginActivity extends BaseActivity {
    private static final String TAG = "PluginActivity";
    private ActivityPluginBinding mViewBinding;
    private GithubViewModel mGithubViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityPluginBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());
        initData();
    }

    private void initData() {
        Log.i(TAG, "initData");
        mGithubViewModel = new GithubViewModel();
    }

    public GithubViewModel getGithubViewModel() {
        return mGithubViewModel;
    }
}
