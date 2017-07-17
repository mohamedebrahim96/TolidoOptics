package com.tolido.optics.tolidooptics;

import android.content.res.Resources;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;

import com.tolido.optics.tolidooptics.Adapter.ItemOffsetDecoration;
import com.tolido.optics.tolidooptics.Adapter.PostsAdapter;
import com.tolido.optics.tolidooptics.Model.Datum;
import com.tolido.optics.tolidooptics.Model.Post;
import com.tolido.optics.tolidooptics.Retofit.ApiClient;
import com.tolido.optics.tolidooptics.Retofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "493383167666376|-jRi4AWvxsDu6EHRUEY73Kg_0z8";

    RecyclerView recyclerView;

    List<Datum> postList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Post> call = apiService.getposts2(API_KEY,"full_picture,message");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post>call, Response<Post> response) {

                postList =  response.body().getData();

                /*recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
                ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(MainActivity.this, R.dimen.item_offset);
                recyclerView.addItemDecoration(itemDecoration);
                PostsAdapter mAdapter = new PostsAdapter(postList,MainActivity.this);
                recyclerView.setAdapter(mAdapter);*/


                PostsAdapter mAdapter = new PostsAdapter(postList,MainActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this,2);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.addItemDecoration(new ItemOffsetDecoration(2, dpToPx(10), true));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);




                Log.e("success", "Number of movies received: " + postList.get(2).getFullPicture());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                // Log error here since request failed
                Log.e("Error", t.toString());
            }
        });

    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
