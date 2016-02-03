package com.usv.androidtestapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import com.usv.androidtestapp.model.Utils;

public class ThemeActivity extends MainActivity {

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_theme);
        activity = this;
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radiogroup.clearCheck();
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.default1:
                        Utils.changeToTheme(activity, Utils.THEME_DEFAULT);
                        break;
                    case R.id.red_theme:
                        Utils.changeToTheme(activity, Utils.THEME_RED);
                        break;
                    case R.id.green_theme:
                        Utils.changeToTheme(activity, Utils.THEME_GREEN);
                        break;
                    case R.id.blue_theme:
                        Utils.changeToTheme(activity, Utils.THEME_BLUE);
                        break;
                }
            }
        });

    }
}
