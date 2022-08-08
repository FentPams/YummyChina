package com.example.yummychina;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummychina.adapter.PopularFoodAdapter;
import com.example.yummychina.adapter.TimeHorneredFoodAdapter;
import com.example.yummychina.model.PopularFood;
import com.example.yummychina.model.TimeHorneredFood;

import java.util.ArrayList;
import java.util.List;

public class ViewRestaurantsActivity extends AppCompatActivity {

    RecyclerView popularRecycler, asiaRecycler;
    TimeHorneredFoodAdapter timeHorneredFoodAdapter;
    PopularFoodAdapter popularFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurants);

        // now here we will add some dummy data to out model class

        List<TimeHorneredFood> timeHorneredFoodList = new ArrayList<>();

        timeHorneredFoodList.add(new TimeHorneredFood("Jiajia Tangbao", "$3", R.drawable.jjtb));
        timeHorneredFoodList.add(new TimeHorneredFood("Fuchun Xiaolong", "$5", R.drawable.fcxl));
        timeHorneredFoodList.add(new TimeHorneredFood("Wan Shou Zhai", "$4", R.drawable.jjtb3));
        timeHorneredFoodList.add(new TimeHorneredFood("LaiLai", "$6", R.drawable.llxl));
        timeHorneredFoodList.add(new TimeHorneredFood("Nan Xiang", "$8", R.drawable.nxmtd));
        timeHorneredFoodList.add(new TimeHorneredFood("Wu You Xian", "$10", R.drawable.wyx));

        setPopularRecycler(timeHorneredFoodList);


        List<PopularFood> popularFoodList = new ArrayList<>();
        popularFoodList.add(new PopularFood("Su Xiao Liu", "$$", R.drawable.sxl, "4.9", "SuXiaoLiu"));
        popularFoodList.add(new PopularFood("Jiajia Soup Dumpling", "$", R.drawable.jjtb, "4.5", "JiaJia"));
        popularFoodList.add(new PopularFood("Ding Tai Fung", "$$", R.drawable.dtf, "4.5", "DingTaiFeng"));
        popularFoodList.add(new PopularFood("Wan Shou Zhai", "$", R.drawable.jjtb3, "4.3", "WanShouZhai"));
        popularFoodList.add(new PopularFood("LaiLai", "$$", R.drawable.llxl, "4.2", "LaiLai"));
        popularFoodList.add(new PopularFood("Nan Xiang Soup Dumpling", "$$", R.drawable.nxmtd, "4.1", "NanXiang"));


        setAsiaRecycler(popularFoodList);

    }


    private void setPopularRecycler(List<TimeHorneredFood> timeHorneredFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        timeHorneredFoodAdapter = new TimeHorneredFoodAdapter(this, timeHorneredFoodList);
        popularRecycler.setAdapter(timeHorneredFoodAdapter);

    }

    private void setAsiaRecycler(List<PopularFood> popularFoodList) {

        asiaRecycler = findViewById(R.id.asia_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        asiaRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        asiaRecycler.setAdapter(popularFoodAdapter);

    }
}
