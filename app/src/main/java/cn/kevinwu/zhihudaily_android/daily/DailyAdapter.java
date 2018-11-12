package cn.kevinwu.zhihudaily_android.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.kevinwu.zhihudaily_android.R;
import cn.kevinwu.zhihudaily_android.model.NewsModel;

/**
 * author: KevinWu
 * date:   On 2018/11/9.
 */

public class DailyAdapter extends BaseRecyclerListAdapter<NewsModel>{

    public DailyAdapter(Context context, List<NewsModel> listData) {
        super(context, listData);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily, parent, false);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, NewsModel data) {
        VH holder = null;
        if (viewHolder instanceof VH) {
            holder = (VH) viewHolder;
        } else {
            return;
        }
        holder.tvTitle.setText(data.getTitle());
        Glide.with(context).load(data.getImage()).into(holder.imgAvatar);
    }

    static class VH extends BaseRecyclerListAdapter.Holder {
        TextView tvTitle;
        ImageView imgAvatar;

        public VH(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
        }
    }

}
