package com.yadong.huawei.module.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.yadong.huawei.R;
import com.yadong.huawei.utils.UIUtils;

/**
 *
 */
public abstract class LoadingPager extends FrameLayout {

    /**
     * 默认状态
     */
    public static final int STATE_DEFAULT = 0;
    /**
     * 加载中
     */
    public static final int STATE_LOADING = 1;
    /**
     * 加载失败
     */
    public static final int STATE_ERROR = 2;
    /**
     * 空
     */
    public static final int STATE_EMPTY = 3;
    /**
     * 加载成功状态
     */
    public static final int STATE_SUCCESS = 4;

    /**
     * 当前状态
     */
    private int mCurrentState = STATE_DEFAULT;

    private View mLoadingView;//加载中界面
    private View mErrorView;//加载失败界面
    private View mEmptyView;//空界面
    private View mSuccessView;//加载成功界面

    public LoadingPager(@NonNull Context context) {
        this(context, null);
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 将所有的界面添加到帧布局中
     */
    private void init() {
        this.setBackgroundColor(UIUtils.getColor(R.color.bg_page));
        mLoadingView = createLoadingView();
        if (mLoadingView != null) {
            this.addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        mErrorView = createErrorView();
        if (mErrorView != null) {
            this.addView(mErrorView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        mEmptyView = createEmptyView();
        if (mEmptyView != null) {
            this.addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        mSuccessView = createSuccessView();
        if (mSuccessView != null) {
            this.addView(mSuccessView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        showPager();
    }

    /**
     * 根据状态显示界面
     */
    private void showPager() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(mCurrentState == STATE_LOADING || mCurrentState == STATE_DEFAULT ? View.VISIBLE : View.GONE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE : View.GONE);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }
        if (mSuccessView != null) {
            mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }


    /**
     * 创建加载中界面
     */
    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page);
    }

    /**
     * 创建加载错误界面
     */
    private View createErrorView() {
        View view = UIUtils.inflate(R.layout.loading_error_page);
        Button settingBtn = (Button) view.findViewById(R.id.setting);
        settingBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入手机中的设置界面
            }
        });
        //点击刷新界面
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    /**
     * 创建空界面
     */
    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_empty_page);
    }

    /**
     * 加载成功界面
     */
    public abstract View createSuccessView();


    /**
     * 服务器返回状态枚举
     */
    public enum LoadResult {
        loading(STATE_LOADING), error(STATE_ERROR), empty(STATE_EMPTY), success(STATE_SUCCESS);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    /**
     * 显示布局
     */
    public void show() {
        if (mCurrentState == STATE_LOADING) {
            showPager();
            return;
        }
        if (mCurrentState == STATE_EMPTY || mCurrentState == STATE_ERROR) {
            mCurrentState = STATE_DEFAULT;
        }
        if (mCurrentState == STATE_DEFAULT) {
            mCurrentState = STATE_LOADING;
            loadData();
        }
        showPager();
    }

    /**
     * 加载获取数据
     */
    public abstract void loadData();


    public void setCurrentState(LoadResult result) {
        mCurrentState = result.getValue();
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPager();
            }
        });
    }
}
