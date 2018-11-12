package cn.kevinwu.zhihudaily_android.daily;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author: KevinWu
 * date:   On 2018/10/02.
 */

public abstract class BaseRecyclerListAdapter<M>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "BaseRecyclerListAdapter";
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_FOOTER = 2;
    protected List<M> listData;
    protected Context context;
    private View headerView;
    private View footerView;


    public void updateData(List dataSet) {
        this.listData.clear();
        appendData(dataSet);
    }

    public void appendData(List dataSet) {
        if (dataSet != null && !dataSet.isEmpty()) {
            this.listData.addAll(dataSet);
        }
    }

    protected OnRecyclerViewItemClickListener onItemClickListener = null;


    public BaseRecyclerListAdapter(Context context, List<M> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }


    public View getFooterView() {
        return footerView;
    }

    public void setFooterView(View footerView) {
        this.footerView = footerView;
        notifyItemInserted(getItemCount()-1);
    }

    public View getHeaderView() {
        return headerView;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView == null && footerView == null) return TYPE_NORMAL;
        if (position == 0 && headerView != null) return TYPE_HEADER;
        if (position == getItemCount()-1 && footerView != null) return TYPE_FOOTER;
        return TYPE_NORMAL;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setListData(List<M> listData) {
        this.listData = listData;
    }

    public void newList(List<M> list) {
        if (list == null) {
            return;
        }
        if (listData == null) {
            listData = new ArrayList<>();
        } else {
            listData.clear();
        }

        listData.addAll(list);
    }


    public interface OnRecyclerViewItemClickListener<M> {
        void onItemClick(int pos, M data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if (headerView != null && viewType == TYPE_HEADER) return new Holder(headerView);
        if (footerView !=null && viewType == TYPE_FOOTER) return new Holder(footerView);
        return onCreate(parent, viewType);
    }

    @Override
    public int getItemCount() {
        int addNum = 0;
        if(headerView != null)addNum++;
        if(footerView != null)addNum ++;
        return listData.size() + addNum;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER) return;
        final int pos = getRealPosition(viewHolder);
        onBind(viewHolder, pos, listData.get(pos));
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(pos, listData.get(pos));
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (getItemViewType(position) == TYPE_HEADER
                            || getItemViewType(position) == TYPE_FOOTER)
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    public abstract RecyclerView.ViewHolder onCreate(ViewGroup parent, final int viewType);

    public abstract void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, M data);

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

}
