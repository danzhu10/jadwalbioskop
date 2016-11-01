package alfarobidev.jadwalbioskop;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import alfarobidev.jadwalbioskop.adapter.GridLayoutAdapter;
import alfarobidev.jadwalbioskop.model.City;
import alfarobidev.jadwalbioskop.model.Movie;
import alfarobidev.jadwalbioskop.network.ApiService;
import alfarobidev.jadwalbioskop.network.RestApi;
import alfarobidev.jadwalbioskop.util.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.xip.errorview.ErrorView;
import tr.xip.errorview.RetryListener;

/**
 * Created by Alfarobi on 7/17/16.
 */
public class MovieGridActivity extends BaseActivity {
    @Bind(R.id.recyclerView)RecyclerView recyclerView;
    List<Movie.DataMovie> dataMovieList = new ArrayList<>();
    City.Data data;
    @Bind(R.id.errorView)
    ErrorView errorView;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.grid_recycler);
        initToolbar();
        ButterKnife.bind(this);
        data = (City.Data) getIntent().getSerializableExtra("city");
        //noinspection ConstantConditions
        Log.d("yoi", data.getId());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        getMovieGrid();
        errorView.setVisibility(View.GONE);
        errorView.setOnRetryListener(new RetryListener() {
            @Override
            public void onRetry() {
                getMovieGrid();
            }
        });
    }

    private void getMovieGrid() {
        final ProgressDialog dialog = Utils.getWaitDialog(MovieGridActivity.this,"Loading...");
        dialog.show();
        dialog.setCancelable(false);
        RestApi restApi = ApiService.createService(RestApi.class);
        restApi.getMovie(data.getId()).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                dialog.dismiss();
                errorView.setVisibility(View.GONE);
                Movie movie = response.body();
                Log.d("yoi",movie.getKota());
                getSupportActionBar().setSubtitle(data.getKota()+" "+movie.getDate());
                if(movie.getStatus().equals("success")){
                    for(int i=0; i<movie.getDataMovies().size(); i++){
                        Movie.DataMovie dataMovie = movie.getDataMovies().get(i);
                        Log.d("yoi",dataMovie.getDuration());
                        dataMovieList.add(dataMovie);
                    }
                    recyclerView.setAdapter(new GridLayoutAdapter(MovieGridActivity.this, dataMovieList, data));
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                errorView.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
    }
}
