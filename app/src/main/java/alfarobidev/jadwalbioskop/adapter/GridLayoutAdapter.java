package alfarobidev.jadwalbioskop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import alfarobidev.jadwalbioskop.CinemaListActivity;
import alfarobidev.jadwalbioskop.MovieGridActivity;
import alfarobidev.jadwalbioskop.R;
import alfarobidev.jadwalbioskop.model.City;
import alfarobidev.jadwalbioskop.model.Movie;
import alfarobidev.jadwalbioskop.util.Utils;

/**
 * Created by Alfarobi on 7/17/16.
 */
public class GridLayoutAdapter extends RecyclerView.Adapter<GridLayoutAdapter.ViewHolder> {
    Context context;
    List<Movie.DataMovie> dataMovieList;
    City.Data data;

    public GridLayoutAdapter(Context context, List<Movie.DataMovie> dataMovieList, City.Data data) {
        this.context=context;
        this.dataMovieList=dataMovieList;
        this.data=data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie.DataMovie dataMovie = dataMovieList.get(position);

        Picasso.with(context).load(dataMovie.getPoster()).error(R.mipmap.ic_launcher)
                .into(holder.imageView);
        holder.titleTV.setText(Html.fromHtml(dataMovie.getMovie()));
        holder.durationTV.setText(dataMovie.getGenre()+"\n"+dataMovie.getDuration());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CinemaListActivity.class)
                        .putExtra("data",dataMovie));
//                Utils.toastLong(context,dataMovie.getMovie());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView durationTV,titleTV;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageIV);
            this.titleTV = (TextView) itemView.findViewById(R.id.titleTV);
            this.durationTV = (TextView) itemView.findViewById(R.id.durationTV);
            this.cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
