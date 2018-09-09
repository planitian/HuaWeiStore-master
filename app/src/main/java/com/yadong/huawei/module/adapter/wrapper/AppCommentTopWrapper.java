package com.yadong.huawei.module.adapter.wrapper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.model.remote.bean.AppCommentBean;
import com.yadong.huawei.module.widget.recyclerview.wrapper.HeaderAndFooterWrapper;


/**
 * App详情_评论的头信息包装器
 */
public class AppCommentTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext;
    private AppCommentController appCommentController;

    public AppCommentTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        this.mContext = context;
        appCommentController = new AppCommentController(mContext);
        addHeaderView(appCommentController.getContentView());
    }

    public void addDataAll(AppCommentBean appCommentBean) {
        if (appCommentController != null) {
            appCommentController.setData(appCommentBean);
        }

    }
}
