package alfarobidev.jadwalbioskop.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.CookieManager;

public class PreferencesUtil {

    //Attribute
    private static PreferencesUtil instance = null;
    private static SharedPreferences myPref;
    private final String USERNAME = "username";
    private final String LEVEL = "level";

    public String getLEVEL() {
        return myPref.getString(LEVEL,"");
    }

    public void setLEVEL(String level) {
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString(LEVEL, level);
        editor.apply();
    }

    //Counstructure
    public static PreferencesUtil getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesUtil();
            myPref = context.getSharedPreferences("order", 0);
        }
        return instance;
    }

    public void setUsername(String username) {
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString(USERNAME, username);
        editor.apply();
    }

    public String getUSERNAME() {
        return myPref.getString(USERNAME, "");
    }


    public void logout() {
        setUsername("");
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }
}
