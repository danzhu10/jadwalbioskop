package alfarobidev.jadwalbioskop;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import alfarobidev.jadwalbioskop.model.Movie;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Alfarobi on 7/17/16.
 */
public class MovieDetailActivity extends BaseActivity {

    @Bind(R.id.cinemaTV)
    TextView cinemaTV;
    @Bind(R.id.titleTV)
    TextView titleTV;
    @Bind(R.id.actTV)
    TextView actTV;
    @Bind(R.id.timeTV)
    TextView timeTV;
    @Bind(R.id.priceTV)
    TextView priceTV;
    @Bind(R.id.coverIV)
    ImageView coverIV;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initToolbar();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;
//
        data = (Movie.DataMovie) getIntent().getSerializableExtra("data");
        schedule = (Movie.Schedule) getIntent().getSerializableExtra("schedule");
        cinemaTV.setText(schedule.getBioskop());
        titleTV.setText(data.getMovie());
        actTV.setText(data.getGenre() + " | " + data.getDuration());
        for (int i = 0; i < schedule.getTimeList().size(); i++) {
            String time=null;
            time = schedule.getTimeList().get(i);
            if(i<schedule.getTimeList().size()-1){
                 time+= " | ";
            }
            timeTV.append(time);
        }
        priceTV.setText(schedule.getHarga());
        coverIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    Movie.DataMovie data;
    Movie.Schedule schedule;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        width = coverIV.getWidth();
        height = coverIV.getHeight();
        Log.d("gambar2", width + " " + height);

        Picasso.with(this).load(data.getPoster()).error(R.mipmap.ic_launcher)
                .resize(width, height)
                .centerCrop()
                .into(coverIV);
    }
}
