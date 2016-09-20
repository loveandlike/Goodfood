package com.example.administrator.eatgood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.eatgood.R;
import com.example.administrator.eatgood.utils.NewsDBUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/9/7.
 */
public class FragmentOneAdapter_zuijinliulan extends RecyclerView.Adapter<FragmentOneAdapter_zuijinliulan.ItemView> {

    private Context context;
    NewsDBUtil dbUtil;

    public FragmentOneAdapter_zuijinliulan(Context context, NewsDBUtil dbUtil) {
        this.context = context;
        this.dbUtil = dbUtil;
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(ItemView holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setOnItemClick(position);
            }
        });
        holder.item_one_teview_one.setText(dbUtil.getZuiJin().get(position).getTitle());
        holder.item_one_teview_two.setText(dbUtil.getZuiJin().get(position).getTags());
        holder.item_one_teview_three.setText(dbUtil.getZuiJin().get(position).getImtro());
        holder.item_one_teview_four.setText(dbUtil.getZuiJin().get(position).getBurden());
        Picasso.with(context).load(dbUtil.getZuiJin().get(position).getAlbum()).into(holder.item_one_image);
    }

    @Override
    public int getItemCount() {
        return dbUtil.getZuiJin().size();
    }

    public class ItemView extends RecyclerView.ViewHolder {
        TextView item_one_teview_one, item_one_teview_two, item_one_teview_three, item_one_teview_four;
        ImageView item_one_image;

        public ItemView(View itemView) {
            super(itemView);
            item_one_teview_one = (TextView) itemView.findViewById(R.id.item_one_teview_one);
            item_one_teview_two = (TextView) itemView.findViewById(R.id.item_one_teview_two);
            item_one_image = (ImageView) itemView.findViewById(R.id.item_one_image);
            item_one_teview_three = (TextView) itemView.findViewById(R.id.item_one_teview_three);
            item_one_teview_four = (TextView) itemView.findViewById(R.id.item_one_teview_four);
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
