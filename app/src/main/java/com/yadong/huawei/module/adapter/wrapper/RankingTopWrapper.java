package com.yadong.huawei.module.adapter.wrapper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.yadong.huawei.R;
import com.yadong.huawei.module.adapter.RankingSubAdapter;
import com.yadong.huawei.utils.UIUtils;
import com.yadong.huawei.model.remote.bean.TopBean;
import com.yadong.huawei.module.widget.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;


/**
 *
 */

public class RankingTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext;
    private View headerView;
    private List<TopBean.TopTopBean> topBeanList;

    private GridView gv_title_grid;

    public RankingTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        this.mContext = context;
        headerView = UIUtils.inflate(R.layout.header_category);
        gv_title_grid = (GridView) headerView.findViewById(R.id.gv_title_grid);
        addHeaderView(headerView);
    }

    public void addTopView() {
        addHeaderView(headerView);
    }

    public void deleteTopView() {
        deleteHeaderView(headerView);
    }

    public void addDataAll(List<TopBean.TopTopBean> list) {
        this.topBeanList = list;

        RankingSubAdapter adapter = new RankingSubAdapter(mContext, topBeanList);
        gv_title_grid.setNumColumns(topBeanList.size());
        gv_title_grid.setAdapter(adapter);

    }

    public void clearData() {
        if (topBeanList != null) {
            topBeanList.clear();
        }
    }
}
