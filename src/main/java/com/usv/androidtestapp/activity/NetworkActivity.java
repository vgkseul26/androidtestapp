package com.usv.androidtestapp.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.usv.androidtestapp.model.Utils;

public class NetworkActivity extends Activity {

    public TextView connectionOne;
    public TextView connectionTwo;
    public TextView connectionThree;
    public Button reloadButton;
    private NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_network);

        reloadButton = (Button) findViewById(R.id.reload_button);
        connectionOne = (TextView) findViewById(R.id.t1_1);
        connectionTwo = (TextView) findViewById(R.id.t2_2);
        connectionThree = (TextView) findViewById(R.id.t3_3);
        networkInfo = getNetworkInfo(getApplicationContext());
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkInfo = getNetworkInfo(getApplicationContext());
                loadNetworkData();
            }
        });
        loadNetworkData();
    }

    private  void loadNetworkData(){
        if (isConnected(networkInfo) == true){
            connectionOne.setText("Yes");

        } else {
            connectionOne.setText("No");
            connectionTwo.setText("No connection");
            return;
        }
        if (isConnectedMobile(networkInfo) == true){
            connectionTwo.setText("Mobile connection");
            connectionThree.setText(showTypeOfMobileConnection(networkInfo.getType(), networkInfo.getSubtype()));
            connectionThree.setVisibility(View.VISIBLE);

        } else if (isConnectedWifi(networkInfo) == true){
            connectionTwo.setText("Wifi connection");
            connectionThree.setVisibility(View.INVISIBLE);
        }
    }

    private static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    public static boolean isConnected(NetworkInfo info){
        return (info != null && info.isConnected());
    }

    public static boolean isConnectedWifi(NetworkInfo info){
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    public static boolean isConnectedMobile(NetworkInfo info){
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    public static String showTypeOfMobileConnection(int type, int subType){
        switch(subType) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "NETWORK_TYPE_1xRTT"; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "NETWORK_TYPE_CDMA"; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "NETWORK_TYPE_EDGE"; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "NETWORK_TYPE_EVDO_0"; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "NETWORK_TYPE_EVDO_A"; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "NETWORK_TYPE_GPRS"; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "NETWORK_TYPE_HSDPA"; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "NETWORK_TYPE_HSPA"; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "NETWORK_TYPE_HSUPA"; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "NETWORK_TYPE_UMTS"; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                return "NETWORK_TYPE_EHRPD"; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                return "NETWORK_TYPE_EVDO_B"; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                return "NETWORK_TYPE_HSPAP"; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                return "NETWORK_TYPE_IDEN"; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                return "NETWORK_TYPE_LTE"; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return "UNKNOWN CONNECTION TYPE";
            default:
                return "ANOTHER TYPE";
        }
    }
}
