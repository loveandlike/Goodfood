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
 * Created by Administrator on 2016/9/9.
 */
public class ShowAdapter2 extends RecyclerView.Adapter<ShowAdapter2.ItemView> {
    Context context;
    List<JiaChangCai.ResultBean.DataBean.StepsBean> steps;

    public ShowAdapter2(Context context, List<JiaChangCai.ResultBean.DataBean.StepsBean> steps) {
        this.context = context;
        this.steps = steps;
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show2, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(ItemView holder, int position) {
        holder.show_number.setText(position+1+""+"/"+steps.size()+"");
        holder.show_jieshao.setText(steps.get(position).getStep());
        Picasso.with(context).load(steps.get(position).getImg()).into(holder.show2_image);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {
        TextView show_number, show_jieshao;
        ImageView show2_image;

        public ItemView(View itemView) {
            super(itemView);
            show_number = (TextView) itemView.findViewById(R.id.show_number);
            show2_image = (ImageView) itemView.findViewById(R.id.show2_image);
            show_jieshao = (TextView) itemView.findViewById(R.id.show_jieshao);
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
