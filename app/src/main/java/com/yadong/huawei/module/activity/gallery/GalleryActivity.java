package com.yadong.huawei.module.activity.gallery;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.module.adapter.GalleryAdapter;
import com.yadong.huawei.module.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 画廊的页面
 */
public class GalleryActivity extends BaseActivity
        implements ViewPager.OnPageChangeListener {


    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.img_choose)
    LinearLayout mImgChoose;

    private ImageView[] choose;
    private int curOffset = -1;
    private List<String> urlList;
    private int tag;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        initViews();
        updateViews();
    }

    /**
     * 重写初始化沉浸式状态栏
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .statusBarDarkFont(true)
                .init();
    }


    public void initViews() {
        getIntentData();
    }

    private void getIntentData() {
        tag = getIntent().getIntExtra("tag", 0);
        urlList = getIntent().getStringArrayListExtra("urlList");
        curOffset = tag;
    }

    public void updateViews() {
        showPoint();
        initViewPager();
    }

    /**
     * 初始化点
     */
    private void showPoint() {
        mImgChoose.removeAllViews();

        choose = new ImageView[urlList.size()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        for (int i = 0; i < urlList.size(); i++) {
            choose[i] = new ImageView(this);
            choose[i].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_normal));
            if (i == curOffset) {
                choose[i].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_selected));
            }

            if (i < this.choose.length - 1) {
                layoutParams.leftMargin = (int) getResources().getDimension(R.dimen.detail_screen_point_margin);
            }
            choose[i].setLayoutParams(layoutParams);

            mImgChoose.addView(this.choose[i]);
        }
    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        GalleryAdapter adapter = new GalleryAdapter(this, urlList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(curOffset);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        choose[position].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_selected));
        choose[curOffset].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_normal));
        curOffset = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void finish() {
        super.finish();
        exitAnim();
    }
}
