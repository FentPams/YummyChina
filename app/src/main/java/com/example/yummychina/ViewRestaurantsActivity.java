package com.example.yummychina;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummychina.adapter.AsiaFoodAdapter;
import com.example.yummychina.adapter.PopularFoodAdapter;
import com.example.yummychina.model.AsiaFood;
import com.example.yummychina.model.PopularFood;


import java.util.ArrayList;
import java.util.List;

public class ViewRestaurantsActivity extends AppCompatActivity {

    RecyclerView popularRecycler, asiaRecycler;
    PopularFoodAdapter popularFoodAdapter;
    AsiaFoodAdapter asiaFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurants);

        // now here we will add some dummy data to out model class

        List<PopularFood> popularFoodList = new ArrayList<>();

        popularFoodList.add(new PopularFood("Jiajia Tangbao", "$3", R.drawable.jjtb));
        popularFoodList.add(new PopularFood("Fuchun Xiaolong", "$5", R.drawable.fcxl));
        popularFoodList.add(new PopularFood("Wan Shou Zhai", "$4", R.drawable.jjtb3));
        popularFoodList.add(new PopularFood("LaiLai", "$6", R.drawable.llxl));
        popularFoodList.add(new PopularFood("Nan Xiang", "$8", R.drawable.nxmtd));
        popularFoodList.add(new PopularFood("Wu You Xian", "$10", R.drawable.wyx));

        setPopularRecycler(popularFoodList);


        List<AsiaFood> asiaFoodList = new ArrayList<>();
        asiaFoodList.add(new AsiaFood("Jiajia Tangbao", "$20", R.drawable.jjtb, "4.5", "JiaJia"));
        asiaFoodList.add(new AsiaFood("Fuchun Xiaolong", "$25", R.drawable.fcxl, "4.2", "Fuchun"));
        asiaFoodList.add(new AsiaFood("Wan Shou Zhai", "$20", R.drawable.jjtb3, "4.5", "Wan Shou Zhai"));
        asiaFoodList.add(new AsiaFood("LaiLai", "$25", R.drawable.llxl, "4.2", "LaiLai"));
        asiaFoodList.add(new AsiaFood("Nan Xiang", "$20", R.drawable.nxmtd, "4.5", "Nan Xiang"));
        asiaFoodList.add(new AsiaFood("Wu You Xian", "$25", R.drawable.wyx, "4.2", "Wu You Xian"));

        setAsiaRecycler(asiaFoodList);

    }


    private void setPopularRecycler(List<PopularFood> popularFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setAsiaRecycler(List<AsiaFood> asiaFoodList) {

        asiaRecycler = findViewById(R.id.asia_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        asiaRecycler.setLayoutManager(layoutManager);
        asiaFoodAdapter = new AsiaFoodAdapter(this, asiaFoodList);
        asiaRecycler.setAdapter(asiaFoodAdapter);

    }
}
