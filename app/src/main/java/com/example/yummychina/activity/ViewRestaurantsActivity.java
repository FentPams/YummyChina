package com.example.yummychina.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummychina.R;
import com.example.yummychina.adapter.PopularFoodAdapter;
import com.example.yummychina.adapter.TimeHorneredFoodAdapter;
import com.example.yummychina.model.PopularFood;
import com.example.yummychina.model.TimeHorneredFood;

import java.util.ArrayList;
import java.util.List;

/**
 * This class displays the restaurants of the making the dish very good, enter from ViesPosts activity
 * Links to the single DetailActivity
 *
 * Features:
 * 1)Displays time-hornered restaurants and popular restaurants(hardcoded for now, needs improvement)
 *  Time-hornerd restaurants are the business have been running for more than 20 or 30 years
 *  Popular restaurants are maybe young but win good reputation.
 * 2)Each restaurant is an entry button, link to the Details interface(only one sample entry displayed)
 *
 * Expected feature:
 * 1)Bookmark and search functions
 * 2)Complete the entry for each restaurant
 * 3)Read and load infor from firebase
 *
 * The according layout:activity_view_restaurants.xml
 */
public class ViewRestaurantsActivity extends AppCompatActivity {
    // declare the view, adapters
    RecyclerView timeHorneredRecycler, popularRecycler;
    TimeHorneredFoodAdapter timeHorneredFoodAdapter;
    PopularFoodAdapter popularFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //connects with xml file
        setContentView(R.layout.activity_view_restaurants);

        //for now, I hardcode the lists of restaurants, but will make it dynamically loaded in the future
        List<TimeHorneredFood> timeHorneredFoodList = new ArrayList<>();

        timeHorneredFoodList.add(new TimeHorneredFood("Jiajia Tangbao", "$3", R.drawable.jjtb));
        timeHorneredFoodList.add(new TimeHorneredFood("Fuchun Xiaolong", "$5", R.drawable.fcxl));
        timeHorneredFoodList.add(new TimeHorneredFood("Wan Shou Zhai", "$4", R.drawable.jjtb3));
        timeHorneredFoodList.add(new TimeHorneredFood("LaiLai", "$6", R.drawable.llxl));
        timeHorneredFoodList.add(new TimeHorneredFood("Nan Xiang", "$8", R.drawable.nxmtd));
        timeHorneredFoodList.add(new TimeHorneredFood("Wu You Xian", "$10", R.drawable.wyx));

        // put list into recycler view
        setTimeHorneredRecycler(timeHorneredFoodList);


        List<PopularFood> popularFoodList = new ArrayList<>();
        popularFoodList.add(new PopularFood("Su Xiao Liu", "$$", R.drawable.sxl, "4.9", "SuXiaoLiu"));
        popularFoodList.add(new PopularFood("Jiajia Soup Dumpling", "$", R.drawable.jjtb, "4.5", "JiaJia"));
        popularFoodList.add(new PopularFood("Ding Tai Fung", "$$", R.drawable.dtf, "4.5", "DingTaiFeng"));
        popularFoodList.add(new PopularFood("Wan Shou Zhai", "$", R.drawable.jjtb3, "4.3", "WanShouZhai"));
        popularFoodList.add(new PopularFood("LaiLai", "$$", R.drawable.llxl, "4.2", "LaiLai"));
        popularFoodList.add(new PopularFood("Nan Xiang Soup Dumpling", "$$", R.drawable.nxmtd, "4.1", "NanXiang"));


        setPopularRecycler(popularFoodList);

    }

    // Adds time-hornered restaurants lists into recycler view
    // Apply TimeHorneredFoodAdapter
    private void setTimeHorneredRecycler(List<TimeHorneredFood> timeHorneredFoodList) {

        timeHorneredRecycler = findViewById(R.id.time_hornered_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        timeHorneredRecycler.setLayoutManager(layoutManager);
        timeHorneredFoodAdapter = new TimeHorneredFoodAdapter(this, timeHorneredFoodList);
        timeHorneredRecycler.setAdapter(timeHorneredFoodAdapter);

    }
    // Adds popular restaurants lists into recycler view
    // Apply PopularFoodAdapter
    private void setPopularRecycler(List<PopularFood> popularFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }
}
