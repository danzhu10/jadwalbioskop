package alfarobidev.jadwalbioskop.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Alfarobi on 29/12/2015.
 */
public class Utils {

    public static Toast toastLong(Context context,String s){
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }

    public static Toast toastShort(Context context,String s){
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }


    public static boolean IsNetworkConnected(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        return i != null && i.isConnected() && i.isAvailable();
    }

    public static ProgressDialog getWaitDialog(Context context, String message) {
        ProgressDialog waitDialog = new ProgressDialog(context);
        if (!TextUtils.isEmpty(message)) {
            waitDialog.setMessage(message);
        }
        return waitDialog;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }

}
