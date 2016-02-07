package com.usv.androidtestapp.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import com.usv.androidtestapp.model.Utils;

public class ThemeActivity extends BaseActivity {

    public  Toolbar toolbar;
    private final Activity ACTIVITY = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_theme, null, false);
        drawerLayout.addView(contentView, 0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(drowerNames[2]);
        setSupportActionBar(toolbar);

        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radiogroup.clearCheck();
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.default1:
                        Utils.changeToTheme(ACTIVITY, Utils.THEME_DEFAULT);
                        break;
                    case R.id.red_theme:
                        Utils.changeToTheme(ACTIVITY, Utils.THEME_RED);
                        break;
                    case R.id.green_theme:
                        Utils.changeToTheme(ACTIVITY, Utils.THEME_GREEN);
                        break;
                    case R.id.blue_theme:
                        Utils.changeToTheme(ACTIVITY, Utils.THEME_BLUE);
                        break;
                }
            }
        });

    }
}
