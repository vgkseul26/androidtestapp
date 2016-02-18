package com.usv.androidtestapp.activity;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.usv.androidtestapp.model.Utils;

public class NetworkActivity extends BaseActivity {

    public Toolbar toolbar;
    public TextView connectionOne;
    public TextView connectionTwo;
    public TextView connectionThree;
    public Button reloadButton;
    private NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_network, null, false);
        drawerLayout.addView(contentView, 0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(drowerNames[1]);
        setSupportActionBar(toolbar);

        reloadButton = (Button) findViewById(R.id.reload_button);
        connectionOne = (TextView) findViewById(R.id.t1_1);
        connectionTwo = (TextView) findViewById(R.id.t2_2);
        connectionThree = (TextView) findViewById(R.id.t3_3);
        networkInfo = Utils.getNetworkInfo(getApplicationContext());
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkInfo = Utils.getNetworkInfo(getApplicationContext());
                loadNetworkData();
            }
        });
        loadNetworkData();
    }

    private  void loadNetworkData(){
        if (Utils.isConnected(networkInfo) == true){
            connectionOne.setText("Yes");

        } else {
            connectionOne.setText("No");
            connectionTwo.setText("No connection");
            return;
        }
        if (Utils.isConnectedMobile(networkInfo) == true){
            connectionTwo.setText("Mobile connection");
            connectionThree.setText(Utils.showTypeOfMobileConnection(networkInfo.getType(), networkInfo.getSubtype()));
            connectionThree.setVisibility(View.VISIBLE);

        } else if (Utils.isConnectedWifi(networkInfo) == true){
            connectionTwo.setText("Wifi connection");
            connectionThree.setVisibility(View.INVISIBLE);
        }
    }

}
