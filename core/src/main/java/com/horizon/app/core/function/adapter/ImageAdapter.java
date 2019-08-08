package com.horizon.app.core.function.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.horizon.app.core.R;
import com.horizon.app.core.function.pojo.Image;

import java.util.List;


//瀑布效果的适配器
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<Image> mImageList;
    private Context mContext;

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
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Image image = mImageList.get(position);
                Toast.makeText(v.getContext(),"点击图像:"+image.getName(),
                        Toast.LENGTH_SHORT).show();
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

}
