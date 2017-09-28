package com.github.xu.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.github.xu.gank.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xukankan on 2017/9/26.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> list_data;
    private View view;
    private static int TYPE_ITEM = 0;
    private static int TYPE_FOOTER = 1;
    public View footerView;
    private ProgressBar progressBar;

    public RecyclerAdapter(List<String> sourceData, View nView) {
        list_data = sourceData;
        view = nView;
    }

    /**
     * 得到View Type
     *
     * @param position
     * @return
     */

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    /**
     * 创建recyclerView的item视图Holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == TYPE_FOOTER) {
            footerView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_footer, parent, false);
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) footerView.getLayoutParams();
            p.setFullSpan(true);
            progressBar = (ProgressBar) footerView.findViewById(R.id.progress);
            return new ViewHolder(footerView);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_item, parent, false);
            return new ViewHolder(itemView);
        }

    }

    /**
     * 把数据绑定在View上
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerAdapter.ViewHolder holder, final int position) {
        if (position + 1 == getItemCount()) {

        } else {
            String url = list_data.get(position);
            Glide.with(view)
                    .load(url).into(holder.imageView);
        }
    }


    /**
     * 数据个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return list_data.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View v) {

        }
    }

    /**
     * 通过这个来进行数据的上滑加载
     *
     * @param list
     */

    public void addAll(List<String> list) {
        int size = list_data.size();
        list_data.addAll(list);
        list.addAll(list);
        notifyItemRangeChanged(size, list.size());
    }

    /**
     * 通过这个来进行数据的下拉刷新
     *
     * @param list
     */
    public void reFresh(List<String> list) {
        list_data = new ArrayList<String>();
        list_data = list;
        notifyDataSetChanged();
        setLoading(false);
    }

    /**
     * 设置上滑加载的加载loading
     * @param state
     */
    public void setLoading(boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }


    }

}
