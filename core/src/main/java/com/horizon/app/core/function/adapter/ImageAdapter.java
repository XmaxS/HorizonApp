package com.horizon.app.core.function.adapter;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.horizon.app.core.R;
import com.horizon.app.core.app.Horizon;
import com.horizon.app.core.function.pojo.Image;
import com.horizon.app.core.ui.loader.HorizonLoader;
import com.horizon.app.core.util.web.HttpUtil;
import okhttp3.*;

import java.io.IOException;
import java.util.List;



//瀑布效果的适配器
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<Image> mImageList;

    private String responseData;


    //把要展示的数据传进来，并传给数据源mImageList
    public ImageAdapter(List<Image> imageList){
        mImageList = imageList;
    }

    //ViewHolder通常出现在适配器里，相当于一个临时存储器，为了滚动时快速设置值，提升性能
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageImage;
        TextView imageName;
        View imageView;

        //itemView参数就是RecyclerView子项的最外层布局
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView;
            imageImage = itemView.findViewById(R.id.image_image);
            imageName = itemView.findViewById(R.id.image_name);
        }
    }

    //创建ViewHolder实例,加载自定义的image_item布局文件
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //加载自定义的image_item布局文件
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_adapter,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        //设置瀑布中的图片点击效果
        holder.imageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                HorizonLoader.showLoading(v.getContext());
                int position = holder.getAdapterPosition();
                Image image = mImageList.get(position);

                getJsonWithOkHttp(image);

            }
        });
        //设置瀑布中的文字点击效果
        holder.imageName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Image image = mImageList.get(position);
                Toast.makeText(v.getContext(),"点击文字:"+image.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    //对RecyclerView子项数据进行赋值,在每个子项滚动到屏幕内执行
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        //得到当前显示的实例
        Image image = mImageList.get(i);
        //这一行是设置瀑布里面要显示的图片，可以直接获取图片
        viewHolder.imageImage.setImageResource(image.getImgId());
        //这一行显示的是瀑布里图片下面的文字
        viewHolder.imageName.setText(image.getName());

    }

    //告诉RecyclerView有多少个子项
    @Override
    public int getItemCount() {
        return mImageList.size();
    }


    /**
     * okHttp网络请求
     */
    private void getJsonWithOkHttp(final Image image) {

        HttpUtil httpUtil = new HttpUtil();
        httpUtil.sendRequsetWithOkHttp("http://10.129.18.153:10086/api/item/store/query/7", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                responseData = "失败！";
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String function = image.getName();

                switch (function){
                    case "Open Camera":{
                        responseData = response.body().string();
                        HorizonLoader.stopLoading();
                        Looper.prepare();
                        Toast.makeText(Horizon.getApplicationContext(),responseData,Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }
                    case "Photo Album":HorizonLoader.stopLoading();
                    case "Function2":HorizonLoader.stopLoading();
                }


            }
        });

    }


}
