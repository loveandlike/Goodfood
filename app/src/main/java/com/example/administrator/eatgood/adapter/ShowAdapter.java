package com.example.administrator.eatgood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.eatgood.R;
import com.example.administrator.eatgood.entitys.JiaChangCai;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ItemView> {
    Context context;
    List<JiaChangCai.ResultBean.DataBean.StepsBean> steps;

    public ShowAdapter(List<JiaChangCai.ResultBean.DataBean.StepsBean> steps, Context context) {
        this.steps = steps;
        this.context = context;
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(ItemView holder, final int position) {
        holder.item_show_teview_one.setText(position + 1 + "");
        holder.item_show_teview_two.setText(steps.get(position).getStep());
        Picasso.with(context).load(steps.get(position).getImg()).into(holder.item_show_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setOnItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {
        TextView item_show_teview_one, item_show_teview_two;
        ImageView item_show_image;

        public ItemView(View itemView) {
            super(itemView);
            item_show_teview_one = (TextView) itemView.findViewById(R.id.number);
            item_show_image = (ImageView) itemView.findViewById(R.id.item_show_image);
            item_show_teview_two = (TextView) itemView.findViewById(R.id.item_show_teview_one);
        }
    }

    //    回调接口
    public interface ItemClick {
        void setOnItemClick(int position);
    }

    private ItemClick listener;


    public void setOnItemClickListener(ItemClick listener) {
        this.listener = listener;
    }
}
