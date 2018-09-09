package com.yadong.huawei.module.adapter;

import android.content.Context;

import com.yadong.huawei.R;
import com.yadong.huawei.model.remote.bean.AppBean;
import com.yadong.huawei.module.widget.recyclerview.adapter.CommonAdapter;
import com.yadong.huawei.module.widget.recyclerview.base.ViewHolder;

/**
 * Created by houyadong on 2017/12/8.
 */

public class MoreRecommendAdapter extends CommonAdapter<AppBean> {

    public MoreRecommendAdapter(Context context) {
        super(context, R.layout.applistitem_recommend);
    }
    @Override
    protected void convert(ViewHolder holder, AppBean moreRecommendBean, int position) {
        holder.setText(R.id.appTitle,moreRecommendBean.getName());
        holder.setText(R.id.app_size,moreRecommendBean.getSizeDesc());
        holder.setText(R.id.app_des,moreRecommendBean.getMemo());
        holder.setImageUrl(R.id.appicon,moreRecommendBean.getIcon());
    }
}
