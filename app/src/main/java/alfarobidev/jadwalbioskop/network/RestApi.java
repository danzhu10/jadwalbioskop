package alfarobidev.jadwalbioskop.network;

import alfarobidev.jadwalbioskop.model.City;
import alfarobidev.jadwalbioskop.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alfarobi on 5/31/16.
 */
public interface RestApi {

    @GET("jadwal-bioskop")
    Call<City> getCity();

    @GET("jadwal-bioskop")
    Call<Movie> getMovie(@Query("id") String id);

}