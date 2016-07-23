package alfarobidev.jadwalbioskop;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Alfarobi on 7/17/16.
 */
public class BaseActivity extends AppCompatActivity{

    public void initToolbar(){
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void goToPlaystore(){
        final String appPackageName = "alfarobidev.jadwalbioskop"; // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public void aboutUs(){
        new MaterialDialog.Builder(BaseActivity.this).titleColor(Color.BLACK)
                .itemColorRes(R.color.colorPrimary)
                .positiveText("Ok")
                .title("About Us")
                .content("API from ibacor.com/api\ngithub.com/danzhu10/jadwalbioskop")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);

                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }
                })
                .build()
                .show();
    }

    public void dialogNet() {
        new MaterialDialog.Builder(BaseActivity.this).titleColor(Color.BLACK)
                .itemColorRes(R.color.colorPrimary)
                .positiveText("Ok")
                .title("Upss")
                .content("Please turn on your internet connection")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        finish();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }
                })
                .build()
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
