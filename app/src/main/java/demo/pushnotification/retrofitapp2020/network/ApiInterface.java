package demo.pushnotification.retrofitapp2020.network;

import demo.pushnotification.retrofitapp2020.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //move/top_rated api_key string
    //movie/{id} id int ,api_key string
   @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovie(@Query("api_key") String apikey);

   @GET("movie/{id}")
    Call<MovieResponse> getMovieDetail(@Path("id") int id,@Path("api_key") String apikey);

}
