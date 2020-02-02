package demo.pushnotification.retrofitapp2020.network;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import demo.pushnotification.retrofitapp2020.R;
import demo.pushnotification.retrofitapp2020.adapter.MoviesAdapter;
import demo.pushnotification.retrofitapp2020.model.Movie;
import demo.pushnotification.retrofitapp2020.model.MovieResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    public static String TAG = SecondActivity.class.getSimpleName();

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

    private void APiCall() {

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


    }

}
