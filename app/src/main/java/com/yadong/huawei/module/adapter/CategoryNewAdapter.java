package com.yadong.huawei.module.adapter;

import android.content.Context;

import com.yadong.huawei.R;
import com.yadong.huawei.model.remote.bean.AppBean;
import com.yadong.huawei.module.widget.recyclerview.adapter.CommonAdapter;
import com.yadong.huawei.module.widget.recyclerview.base.ViewHolder;

/**
 */

public class CategoryNewAdapter extends CommonAdapter<AppBean> {

    public CategoryNewAdapter(Context context) {
        super(context, R.layout.applistitem_recommend);
    }

    @Override
    protected void convert(ViewHolder holder, AppBean appBean, int position) {
        holder.setText(R.id.appTitle, appBean.getName());
        holder.setText(R.id.app_size, appBean.getSizeDesc());
        holder.setText(R.id.app_des, appBean.getMemo());
        holder.setImageUrl(R.id.appicon, appBean.getIcon());
    }
}
