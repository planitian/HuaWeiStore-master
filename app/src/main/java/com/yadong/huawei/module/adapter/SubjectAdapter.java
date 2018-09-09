package com.yadong.huawei.module.adapter;

import android.content.Context;

import com.yadong.huawei.R;
import com.yadong.huawei.module.widget.recyclerview.adapter.CommonAdapter;
import com.yadong.huawei.module.widget.recyclerview.base.ViewHolder;

/**
 * Created by houyadong on 2017/12/8.
 */

public class SubjectAdapter extends CommonAdapter<String> {
    public SubjectAdapter(Context context) {
        super(context, R.layout.subject_item);
    }

    @Override
    protected void convert(ViewHolder holder, String url, int position) {
        holder.setImageUrl(R.id.item_icon, url);
    }
}
