package com.yadong.huawei.module.activity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yadong.huawei.R;
import com.yadong.huawei.common.factory.FragmentFactory;
import com.yadong.huawei.module.adapter.FixPagerAdapter;
import com.yadong.huawei.module.base.BaseActivity;
import com.yadong.huawei.module.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private FixPagerAdapter mFixPagerAdapter;
    private List<Fragment> fragments;
    private List<String> mTitles;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    public void initViews() {
        initFragment();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        mTitles = new ArrayList<>();
        mTitles.clear();

        mTitles.add("推荐");
        mTitles.add("分类");
        mTitles.add("排行");
        mTitles.add("管理");
        mTitles.add("我的");


        fragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            fragments.add(FragmentFactory.createFragment(i));
        }

        mFixPagerAdapter = new FixPagerAdapter(getSupportFragmentManager());
        mFixPagerAdapter.setTitles(mTitles);
        mFixPagerAdapter.setFragments(fragments);

        mViewPager.setAdapter(mFixPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                System.out.println("页面切换" + position);
                BaseFragment fragment = FragmentFactory.createFragment(position);
                fragment.show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentFactory.removeAll();
    }

    /**
     * 动态添加
     *
     * @param view
     */
    public void add(View view) {
        //        BaseMvpFragment fragment = FragmentFactory.createFragment(5);
        //        fragments.add(fragment);
        //        mFixPagerAdapter.addData("大爷", fragments);
    }

    /**
     * 动态删除
     *
     * @param view
     */
    public void remove(View view) {
        //        BaseMvpFragment fragment = FragmentFactory.createFragment(5);
        //        fragments.remove(fragment);
        //        mFixPagerAdapter.removeData("大爷", fragments);
    }


}
