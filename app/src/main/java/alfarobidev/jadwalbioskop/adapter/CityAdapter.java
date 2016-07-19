package alfarobidev.jadwalbioskop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import alfarobidev.jadwalbioskop.MovieGridActivity;
import alfarobidev.jadwalbioskop.component.RecyclerViewFastScroller;
import alfarobidev.jadwalbioskop.model.City;

public final class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> implements RecyclerViewFastScroller.BubbleTextGetter {

    private List<City.Data> items = new ArrayList<>();
    Context context;

    public CityAdapter(Context context,List<City.Data> items) {
        this.context=context;
        this.items=items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final City.Data data = items.get(position);
        holder.setText(data.getKota());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MovieGridActivity.class)
                .putExtra("city",data));
            }
        });
    }

    @Override
    public String getTextToShowInBubble(final int pos) {
        return Character.toString(items.get(pos).getKota().charAt(0));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        private ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(android.R.id.text1);
        }

        public void setText(CharSequence text) {
            textView.setText(text);
        }
    }

}
