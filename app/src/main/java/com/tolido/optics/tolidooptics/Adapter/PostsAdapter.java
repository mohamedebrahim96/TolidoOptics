package com.tolido.optics.tolidooptics.Adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tolido.optics.tolidooptics.Model.Datum;
import com.tolido.optics.tolidooptics.Model.Post;
import com.tolido.optics.tolidooptics.R;

import java.util.List;

/**
 * Created by Home on 2017-07-16.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private List<Datum> movies;
    private Context context;




    public PostsAdapter(List<Datum> movies , Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public PostsAdapter.PostsViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new PostsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final PostsViewHolder holder, final int position) {

        //holder.movieTitle.setText(movies.get(position).getMessage());
        Glide.with(context)
                .load(movies.get(position).getFullPicture())
                .into(holder.thumbnail);


        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder {

        public TextView title, count;
        public ImageView thumbnail, overflow;


        public PostsViewHolder(View v) {
            super(v);

            title =  v.findViewById(R.id.title);
            count =  v.findViewById(R.id.count);
            thumbnail =  v.findViewById(R.id.thumbnail);
            overflow =  v.findViewById(R.id.overflow);
        }
    }


    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }


    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(context, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

}