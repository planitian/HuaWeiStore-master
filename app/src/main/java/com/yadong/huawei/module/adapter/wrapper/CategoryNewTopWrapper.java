package com.yadong.huawei.module.adapter.wrapper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yadong.huawei.R;
import com.yadong.huawei.utils.UIUtils;
import com.yadong.huawei.model.remote.bean.CategoryNewBean;
import com.yadong.huawei.module.widget.recyclerview.wrapper.HeaderAndFooterWrapper;


/**
 */

public class CategoryNewTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext;
    private View headerView;
    private CategoryNewBean.Head head;

    public CategoryNewTopWrapper(Context context, RecyclerView.Adapter adapter, CategoryNewBean.Head head) {
        super(adapter);
        this.mContext = context;
        this.head = head;
        headerView = UIUtils.inflate(R.layout.head_category_necessary);
        addHeaderView(headerView);

        TextView intro = (TextView) headerView.findViewById(R.id.intro);
        ImageView iv = (ImageView) headerView.findViewById(R.id.iv);

        intro.setText(head.getIntro());
        System.out.println("icon:" + head.getIcon());
        Glide.with(UIUtils.getContext()).load(head.getIcon()).into(iv);


    }


}
