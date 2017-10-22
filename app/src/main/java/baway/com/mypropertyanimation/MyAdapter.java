package baway.com.mypropertyanimation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import baway.com.mypropertyanimation.model.bean.MyBean;

/**
 * Created by 贾秀坤 on 2017/10/21.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public OnItemClickListener listener;
    private Context context;
    private List<MyBean.DataBean> list = new ArrayList<>();

    public MyAdapter(Context context, List<MyBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.item_title.setText(list.get(position).getTitle());
        holder.item_nr.setText(list.get(position).getSubhead());
//        String strs=list.get(position).getImages();
//        strs.split("//|");
//        Glide.with(context).load(list.get(position).getImages()).into(holder.item_image);
        holder.item_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.onItemClickListener(position,view);
            }
        });
        holder.item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClickListener(position, view);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView item_image;
        private TextView item_title;
        private TextView item_nr;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_image = (ImageView) itemView.findViewById(R.id.item_image);
            item_title = (TextView) itemView.findViewById(R.id.item_title);
            item_nr = (TextView) itemView.findViewById(R.id.item_nr);
        }
    }

    //自定义条目点击接口
    interface OnItemClickListener {

        void onItemClickListener(int position, View view);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
