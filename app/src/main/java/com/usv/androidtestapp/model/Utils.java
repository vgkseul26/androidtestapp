package com.usv.androidtestapp.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.usv.androidtestapp.activity.R;

public class Utils
{
    private static int sTheme;

    public final static int THEME_DEFAULT = 0;
    public final static int THEME_RED = 1;
    public final static int THEME_GREEN = 2;
    public final static int THEME_BLUE = 3;

    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();

        activity.startActivity(new Intent(activity, activity.getClass()));

    }

    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.AppTheme);
                break;
            case THEME_RED:
                activity.setTheme(R.style.RedTheme);
                break;
            case THEME_GREEN:
                activity.setTheme(R.style.GreenTheme);
                break;
            case THEME_BLUE:
                activity.setTheme(R.style.BlueTheme);
                break;
        }
    }

    public static NetworkInfo getNetworkInfo(Context context){
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

