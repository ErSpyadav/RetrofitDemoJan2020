package demo.pushnotification.retrofitapp2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import demo.pushnotification.retrofitapp2020.adapter.MoviesAdapter;
import demo.pushnotification.retrofitapp2020.model.Movie;
import demo.pushnotification.retrofitapp2020.model.MovieResponse;
import demo.pushnotification.retrofitapp2020.network.ApiClient;
import demo.pushnotification.retrofitapp2020.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();

    public static final String API_KEY = "724d596c9edc043e74bb4d0193b50244";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       recyclerView=findViewById(R.id.recycleView);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void OnButtonClick(View view){
        Toast.makeText(this, "Button is Clicked", Toast.LENGTH_LONG).show();
        APiCall();
    }

    public void APiCall(){
        if (API_KEY.isEmpty()) {
            Toast.makeText(this, "Please get API_KEY first", Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterface apiInterface=ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
        Call<MovieResponse> call=apiInterface.getTopRatedMovie(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                List<Movie> list=response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(list,R.layout.list_item_movie,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });



//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<MovieResponse> call = apiInterface.getTopRatedMovie(API_KEY);
//        call.enqueue(new Callback<MovieResponse>() {
//            @Override
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//            try{
//              System.out.println("Response"+response.body());
//                List<Movie> movieList = response.body().getResults();
//                recyclerView.setAdapter(new MoviesAdapter(movieList,R.layout.list_item_movie,getApplicationContext()));
//                Log.d(TAG, "Downloaded Movie Size:" + movieList.size());}
//            catch (Exception e){
//                System.out.println("Exception"+e.getMessage());
//            }
//            }
//
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable t) {
//                Log.d(TAG, t.getMessage());
//            }
//        });
    }
}
