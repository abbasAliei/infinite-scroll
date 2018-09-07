package com.abbas.ali.infinitescroll.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.abbas.ali.infinitescroll.adapter.Adapter;
import com.abbas.ali.infinitescroll.api.Api;
import com.abbas.ali.infinitescroll.R;
import com.abbas.ali.infinitescroll.model.Contact;
import com.abbas.ali.infinitescrollprovider.InfiniteScrollProvider;
import com.abbas.ali.infinitescrollprovider.OnLoadMoreListener;

import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ITEM_PER_PAGE = 18;
    int page = 1;

    private RelativeLayout rootLayout;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FloatingActionButton githubFab;

    private Api api;
    private Adapter adapter;
    private InfiniteScrollProvider infiniteScrollProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupViews();
        getProducts();
    }

    private void init() {
        api = new Api(200);
        adapter = new Adapter(this);
        infiniteScrollProvider = new InfiniteScrollProvider();
    }

    private void setupViews() {
        rootLayout = findViewById(R.id.rl_main_rootLayout);
        recyclerView = findViewById(R.id.recyclerView_main);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        progressBar = findViewById(R.id.pb_main);
        infiniteScrollProvider.setInfiniteScroll(recyclerView, new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<Contact> contacts = api.getContacts(page, ITEM_PER_PAGE);
                        if (contacts != null){
                            adapter.addContacts(contacts);
                            progressBar.setVisibility(View.GONE);
                            page += 1;
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, getString(R.string.main_endOfList), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 600);
            }
        });

        githubFab = findViewById(R.id.fab_main_github);
        githubFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/abbasAliei"));
                startActivity(intent);
            }
        });
    }

    private void getProducts() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Contact> contacts = api.getContacts(page, ITEM_PER_PAGE);
                if (contacts != null){
                    adapter.addContacts(contacts);
                    progressBar.setVisibility(View.GONE);
                    page += 1;
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, getString(R.string.main_endOfList), Toast.LENGTH_SHORT).show();
                }
            }
        }, 600);
    }
}
