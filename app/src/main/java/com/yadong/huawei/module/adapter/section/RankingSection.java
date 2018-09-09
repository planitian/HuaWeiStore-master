package com.yadong.huawei.module.adapter.section;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yadong.huawei.R;
import com.yadong.huawei.model.remote.bean.AppBean;
import com.yadong.huawei.module.widget.DownloadProgressButton;
import com.yadong.huawei.module.widget.recyclerview.base.ViewHolder;
import com.yadong.huawei.module.widget.recyclerview.section.StatelessSection;

import java.util.List;

/**
 *
 */

public class RankingSection extends StatelessSection {


    private Context mContext;
    private String mTitle;
    private List<AppBean> mDataBeanList;

    public RankingSection(Context context, String title, List<AppBean> appBeanList) {
        super(R.layout.section_category_title, R.layout.section_ranking_content);
        this.mContext = context;
        this.mTitle = title;
        this.mDataBeanList = appBeanList;
    }

    /**
     * 看总共有多少个条目
     */
    @Override
    public int getContentItemsTotal() {
        return mDataBeanList == null ? 0 : mDataBeanList.size();
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
        final AppBean bean = mDataBeanList.get(position);

        holder.setText(R.id.appSerial, bean.getAliasName());
        holder.setImageUrl(R.id.appicon, bean.getIcon());
        holder.setText(R.id.ItemTitle, bean.getName());
        holder.setText(R.id.ItemText_star, bean.getSizeDesc());
        holder.setText(R.id.memo, bean.getMemo());

        holder.setOnClickListener(R.id.AppListItem, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position, bean.getPackageName());
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

        TextView appSerial;
        ImageView appicon;
        DownloadProgressButton downbtn;
        TextView ItemTitle;
        TextView ItemText_star;
        TextView memo;

        public ItemViewHolder(View view) {
            super(mContext, view);
            appSerial = (TextView) view.findViewById(R.id.appSerial);
            appicon = (ImageView) view.findViewById(R.id.appicon);
            downbtn = (DownloadProgressButton) view.findViewById(R.id.downbtn);
            ItemTitle = (TextView) view.findViewById(R.id.ItemTitle);
            ItemText_star = (TextView) view.findViewById(R.id.ItemText_star);
            memo = (TextView) view.findViewById(R.id.memo);
        }
    }


    public OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, String packageName);
    }

}
