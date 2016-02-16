package com.usv.androidtestapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.usv.androidtestapp.R;

public class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected ListView drawerList;
    protected String[] drowerNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        drowerNames = getResources().getStringArray(R.array.drawer_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drowerNames));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, NetworkActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, ThemeActivity.class));
                break;
            default:
                break;
        }
        drawerList.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerList);
    }
}
