package alfarobidev.jadwalbioskop;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Movie.DataMovie data = (Movie.DataMovie) getIntent().getSerializableExtra("data");
        Movie.Schedule schedule = (Movie.Schedule) getIntent().getSerializableExtra("schedule");
        cinemaTV.setText(schedule.getBioskop());
        titleTV.setText(data.getMovie());
        actTV.setText(data.getGenre()+" | "+data.getDuration());
        for (int i=0;i<schedule.getTimeList().size();i++){
            timeTV.append(schedule.getTimeList().get(i)+"\n");
        }
        priceTV.setText(schedule.getHarga());
        int width = coverIV.getWidth();
        int height = coverIV.getHeight();
        Log.d("gambar", width + " " + height);
        Picasso.with(this).load(data.getPoster()).error(R.mipmap.ic_launcher)
                .fit()
                .centerInside()
                .into(coverIV);
    }
}
