package com.usv.androidtestapp.activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.usv.androidtestapp.fragments.EmptyFragment;
import com.usv.androidtestapp.fragments.PostsFragment;
import com.usv.androidtestapp.model.Utils;

public class MainActivity extends BaseActivity {
    private PostsFragment postsFragment;
    private EmptyFragment emptyFragment;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        drawerLayout.addView(contentView, 0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(drowerNames[0]);
        setSupportActionBar(toolbar);

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
}
