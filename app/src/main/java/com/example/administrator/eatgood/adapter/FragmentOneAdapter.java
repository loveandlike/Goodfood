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
import com.example.administrator.eatgood.utils.UrlToBitmap;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class FragmentOneAdapter extends RecyclerView.Adapter<FragmentOneAdapter.ItemView> {

    private Context context;
    List<JiaChangCai.ResultBean.DataBean> data_hjc;

    public FragmentOneAdapter(Context context, List<JiaChangCai.ResultBean.DataBean> data_hjc) {
        this.context = context;
        this.data_hjc = data_hjc;
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
        holder.item_one_teview_one.setText(data_hjc.get(position).getTitle());
        holder.item_one_teview_two.setText(data_hjc.get(position).getTags());
        holder.item_one_teview_three.setText(data_hjc.get(position).getImtro());
        holder.item_one_teview_four.setText(data_hjc.get(position).getBurden());
        Picasso.with(context).load(data_hjc.get(position).getAlbums().get(0)).into(holder.item_one_image);
        UrlToBitmap  utb=new UrlToBitmap();


    }

    @Override
    public int getItemCount() {
        return data_hjc.size();
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
