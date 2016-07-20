package alfarobidev.jadwalbioskop.network;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import alfarobidev.jadwalbioskop.model.City;
import alfarobidev.jadwalbioskop.model.Movie;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Alfarobi on 5/31/16.
 */
public interface RestApi {
    String BASE_URL = "http://ibacor.com/api/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .build();

    @GET("jadwal-bioskop")
    Call<City> getCity();

    @GET("jadwal-bioskop")
    Call<Movie> getMovie(@Query("id") String id);

}