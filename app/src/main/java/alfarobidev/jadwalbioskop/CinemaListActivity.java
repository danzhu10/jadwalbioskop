package alfarobidev.jadwalbioskop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import alfarobidev.jadwalbioskop.adapter.CinemaAdapter;
import alfarobidev.jadwalbioskop.model.Movie;
import alfarobidev.jadwalbioskop.util.Utils;

/**
 * Created by Alfarobi on 7/17/16.
 */
public class CinemaListActivity extends Activity {

    List<Movie.Schedule> dataMovieList = new ArrayList<>();
    Movie.DataMovie data;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        listView = (ListView)findViewById(R.id.listView);
        data = (Movie.DataMovie) getIntent().getSerializableExtra("data");
        getCinema();
    }

    private void getCinema() {
        for (int i=0;i<data.getScheduleList().size(); i++){
            Movie.Schedule schedule = data.getScheduleList().get(i);
            dataMovieList.add(schedule);
        }
        listView.setAdapter(new CinemaAdapter(getApplicationContext(), dataMovieList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie.Schedule schedule =(Movie.Schedule) listView.getAdapter().getItem(position);
                startActivity(new Intent(getApplicationContext(),MovieDetailActivity.class)
                .putExtra("schedule",schedule)
                .putExtra("data",data));
            }
        });
    }
}
