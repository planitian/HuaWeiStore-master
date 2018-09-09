package com.yadong.huawei.module.adapter.section;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yadong.huawei.R;
import com.yadong.huawei.model.remote.bean.CategoryBean;
import com.yadong.huawei.module.widget.recyclerview.base.ViewHolder;
import com.yadong.huawei.module.widget.recyclerview.section.StatelessSection;

import java.util.List;

/**
 *
 */

public class CategorySection extends StatelessSection {


    private Context mContext;
    private String mTitle;
    private List<CategoryBean.CategoryDataBean> mCategoryDataBeanList;

    public CategorySection(Context context, String title, List<CategoryBean.CategoryDataBean> categoryDataBeanList) {
        super(R.layout.section_category_title, R.layout.section_category_content);
        this.mContext = context;
        this.mTitle = title;
        this.mCategoryDataBeanList = categoryDataBeanList;
    }

    /**
     * 看总共有多少个条目
     */
    @Override
    public int getContentItemsTotal() {
        return mCategoryDataBeanList == null ? 0 : mCategoryDataBeanList.size();
    }

    /**
     * 获取条目的 ViewHolder
     */
    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ItemViewHolder(view);
    }

    /**
     * 绑定数据到 条目上
     */
    @Override
    public void onBindItemViewHolder(ViewHolder holder, final int position) {
        CategoryBean.CategoryDataBean categoryDataBean = mCategoryDataBeanList.get(position);

        holder.setImageUrl(R.id.appicon, categoryDataBean.getIconUrl());
        holder.setText(R.id.item_title, categoryDataBean.getName());

        holder.setOnClickListener(R.id.rl_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }


    /**
     * 获取头的ViewHolder
     */
    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new HeaderViewHolder(view);
    }

    /**
     * 绑定数据到头上
     */
    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.item_title, mTitle);
    }

    /**
     * 头 ViewHolder
     */
    class HeaderViewHolder extends ViewHolder {

        TextView tvTitle;
        TextView tvMore;
        ImageView ivMore;

        public HeaderViewHolder(View view) {
            super(mContext, view);
            tvTitle = (TextView) view.findViewById(R.id.item_title);
            tvMore = (TextView) view.findViewById(R.id.down_btn);
            ivMore = (ImageView) view.findViewById(R.id.arrow_right);

            tvMore.setVisibility(View.GONE);
            ivMore.setVisibility(View.GONE);

        }
    }


    /**
     * 条目 ViewHolder
     */
    class ItemViewHolder extends ViewHolder {

        ImageView appicon;
        TextView ItemTitle;

        public ItemViewHolder(View view) {
            super(mContext, view);
            appicon = (ImageView) view.findViewById(R.id.appicon);
            ItemTitle = (TextView) view.findViewById(R.id.item_title);
        }
    }


    public OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
