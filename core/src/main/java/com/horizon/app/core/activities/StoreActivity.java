package com.horizon.app.core.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.horizon.app.core.R;
import com.horizon.app.core.function.adapter.ImageAdapter;
import com.horizon.app.core.function.pojo.Image;
import java.util.ArrayList;
import java.util.List;


public class StoreActivity extends AppCompatActivity {

    private List<Image> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

//        传入瀑布流的图片初始化
        initImage();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //瀑布效果(交错网格),参数：列数，方向
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ImageAdapter adapter = new ImageAdapter(imageList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.layout.bottom_silent,R.layout.bottom_out);
    }

    //要显示的图像初始化
    private void initImage(){
        String pic_name1 = "name1";
        String pic_name2 = "name2";
        String pic_name3 = "name3";


        for (int i = 0;i<2;i++){
            Image boy = new Image(pic_name1,R.drawable.boy_pic);
            imageList.add(boy);
            Image boy1 = new Image(pic_name2,R.drawable.boy_pic);
            imageList.add(boy1);
            Image boy2 = new Image(pic_name3,R.drawable.boy_pic);
            imageList.add(boy2);
        }
    }


}
