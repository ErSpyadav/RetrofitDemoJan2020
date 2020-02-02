package demo.pushnotification.retrofitapp2020.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import demo.pushnotification.retrofitapp2020.R;
import demo.pushnotification.retrofitapp2020.model.Movie;

public class MovieAdapterNew extends RecyclerView.Adapter<MovieAdapterNew.MovieHolder> {

    List<Movie> movieList;
    Context context;
    int rowLayout;

    public MovieAdapterNew(List<Movie> movieList, Context context, int rowLayout) {
        this.movieList = movieList;
        this.context = context;
        this.rowLayout = rowLayout;
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView subTitle;
        TextView rating;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            subTitle=itemView.findViewById(R.id.subtitle);
            description=itemView.findViewById(R.id.description);
            rating=itemView.findViewById(R.id.rating);
        }
    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.title.setText(movieList.get(position).getTitle());
        holder.description.setText(movieList.get(position).getOverview());
        holder.subTitle.setText(movieList.get(position).getReleaseDate());
        holder.rating.setText(movieList.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
