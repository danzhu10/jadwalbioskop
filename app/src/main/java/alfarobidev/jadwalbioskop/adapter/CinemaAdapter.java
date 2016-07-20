package alfarobidev.jadwalbioskop.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import alfarobidev.jadwalbioskop.R;
import alfarobidev.jadwalbioskop.model.Movie;

/**
 * Created by Alfarobi on 7/17/16.
 */
public class CinemaAdapter extends BaseAdapter {
    Context context;
    List<Movie.Schedule> dataMovieList;
    public CinemaAdapter(Context context, List<Movie.Schedule> dataMovieList) {
        this.context=context;
        this.dataMovieList=dataMovieList;
    }

    @Override
    public int getCount() {
        return dataMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        Movie.Schedule dataMovie = dataMovieList.get(position);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder.textView = (TextView)convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(Html.fromHtml(dataMovie.getBioskop()));
        return convertView;
    }
}
