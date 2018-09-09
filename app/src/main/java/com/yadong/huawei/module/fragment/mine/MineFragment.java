package com.yadong.huawei.module.fragment.mine;

import android.view.View;
import android.widget.GridView;

import com.yadong.huawei.R;
import com.yadong.huawei.model.remote.bean.MyGvBean;
import com.yadong.huawei.module.adapter.MySubAdapter;
import com.yadong.huawei.module.base.BaseFragment;
import com.yadong.huawei.module.widget.EnterLayout;
import com.yadong.huawei.module.widget.LoadingPager;
import com.yadong.huawei.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.gv_title_grid)
    GridView gv_title_grid;

    @BindView(R.id.book_game_layout)
    EnterLayout book_game_layout;

    @BindView(R.id.buy_layout)
    EnterLayout buy_layout;

    @BindView(R.id.huaban_layout)
    EnterLayout huaban_layout;

    @BindView(R.id.setting_computer)
    EnterLayout setting_computer;

    @BindView(R.id.faq_layout)
    EnterLayout faq_layout;

    @BindView(R.id.check_update_layout)
    EnterLayout check_update_layout;

    @BindView(R.id.about_layout)
    EnterLayout about_layout;

    private List<MyGvBean> gvBeanList = new ArrayList<>();
    private String[] titles = {"奖品", "礼包", ""};

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_prize), R.drawable.icon_market_lucky_draw));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_gift), R.drawable.ic_mine_package_normal));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.appzone_comments), R.drawable.icon_market_comment));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_mine_message), R.drawable.icon_market_message));

        MySubAdapter adapter = new MySubAdapter(getContext(), gvBeanList);
        gv_title_grid.setNumColumns(gvBeanList.size());
        gv_title_grid.setAdapter(adapter);

        book_game_layout.setTitle(UIUtils.getString(R.string.reserve_warpup_game_str));
        buy_layout.setTitle(UIUtils.getString(R.string.purchase_title));
        huaban_layout.setTitle(UIUtils.getString(R.string.mine_point_area));
        setting_computer.setTitle(UIUtils.getString(R.string.action_settings));
        faq_layout.setTitle(UIUtils.getString(R.string.menu_feedback));
        check_update_layout.setTitle(UIUtils.getString(R.string.settings_check_version_update));
        about_layout.setTitle(UIUtils.getString(R.string.about));
    }

    @Override
    protected void updateViews() {
        setCurrentState(LoadingPager.LoadResult.success);

    }


    @OnClick({R.id.book_game_layout, R.id.buy_layout,
            R.id.huaban_layout, R.id.setting_computer,
            R.id.faq_layout, R.id.check_update_layout,
            R.id.about_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.book_game_layout:
                break;
            case R.id.buy_layout:
                break;
            case R.id.huaban_layout:
                break;
            case R.id.setting_computer:
                break;
            case R.id.faq_layout:
                break;
            case R.id.check_update_layout:
                break;
            case R.id.about_layout:
                break;
        }
    }
}
