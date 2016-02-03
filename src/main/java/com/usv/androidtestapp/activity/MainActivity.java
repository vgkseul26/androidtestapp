package com.usv.androidtestapp.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.usv.androidtestapp.fragments.EmptyFragment;
import com.usv.androidtestapp.fragments.PostsFragment;
import com.usv.androidtestapp.model.Utils;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ListView drawerList;
    public String[] drowerNames;
    private CharSequence drawerTitle;
    private CharSequence title;
    public PostsFragment postsFragment;
    public EmptyFragment emptyFragment;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        drowerNames = getResources().getStringArray(R.array.drawer_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drowerNames));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);
        }
        postsFragment = new PostsFragment();
        emptyFragment = new EmptyFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
        fragTrans.add(R.id.frame, postsFragment);
        fragTrans.add(R.id.frame, emptyFragment);
        switch(item.getItemId()) {
            case R.id.posts:
                fragTrans.hide(emptyFragment);
                fragTrans.show(postsFragment);
                break;
            case R.id.empty_folder:
                fragTrans.hide(postsFragment);
                fragTrans.show(emptyFragment);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        fragTrans.commit();
        return true;
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
        setTitle(drowerNames[position]);
        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        toolbar.setTitle(title);
    }
}
