package alfarobidev.jadwalbioskop;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import alfarobidev.jadwalbioskop.adapter.CityAdapter;
import alfarobidev.jadwalbioskop.component.RecyclerViewFastScroller;
import alfarobidev.jadwalbioskop.model.City;
import alfarobidev.jadwalbioskop.network.ApiService;
import alfarobidev.jadwalbioskop.network.RestApi;
import alfarobidev.jadwalbioskop.util.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.xip.errorview.ErrorView;
import tr.xip.errorview.RetryListener;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.recyclerView)RecyclerView recyclerView;
    @Bind(R.id.fastscroller)RecyclerViewFastScroller fastScroller;
    @Bind(R.id.errorView) ErrorView errorView;
    Context context;
    List<City.Data> dataList = new ArrayList<>();
    CityAdapter adapter; //tesssssss
    ProgressDialog dialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context=this;
        initDrawer();
        initRecycler();
        errorView.setVisibility(View.GONE);
        errorView.setOnRetryListener(new RetryListener() {
            @Override
            public void onRetry() {
                getCity();
            }
        });
        getCity();
    }

    private void initDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initRecycler() {

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
            @Override
            public void onLayoutChildren(final RecyclerView.Recycler recycler, final RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
                //TODO if the items are filtered, considered hiding the fast scroller here
                final int firstVisibleItemPosition = findFirstVisibleItemPosition();
                if (firstVisibleItemPosition != 0) {
                    // this avoids trying to handle un-needed calls
                    if (firstVisibleItemPosition == -1)
                        //not initialized, or no items shown, so hide fast-scroller
                        fastScroller.setVisibility(View.GONE);
                    return;
                }
                final int lastVisibleItemPosition = findLastVisibleItemPosition();
                int itemsShown = lastVisibleItemPosition - firstVisibleItemPosition + 1;
                //if all items are shown, hide the fast-scroller
                fastScroller.setVisibility(adapter.getItemCount() > itemsShown ? View.VISIBLE : View.GONE);
            }
        });
        fastScroller.setRecyclerView(recyclerView);
        fastScroller.setViewsToUse(R.layout.fastscroller_layout, R.id.fastscroller_bubble, R.id.fastscroller_handle);
    }

    private void getCity() {
        fastScroller.setVisibility(View.GONE);
        dialog = Utils.getWaitDialog(context,"Loading...");
        dialog.show();
        dialog.setCancelable(false);
        RestApi restApi = ApiService.createService(RestApi.class);
        restApi.getCity().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                fastScroller.setVisibility(View.VISIBLE);
                errorView.setVisibility(View.GONE);
                dialog.dismiss();
                dataList.clear();
                City city = response.body();
                if(city.getStatus().equals("success")) {
                    for (int i = 0; i < city.getDataList().size(); i++) {
                        City.Data data = city.getDataList().get(i);
                        dataList.add(data);
                    }
                    adapter = new CityAdapter(context,dataList);
                    recyclerView.setAdapter(adapter);
                }else {
                    Utils.toastLong(context,"Can't get data");
                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                errorView.setVisibility(View.VISIBLE);
                fastScroller.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.feedback) {
            goToPlaystore();
        } else if (id == R.id.about) {
            aboutUs();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
