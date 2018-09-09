package com.yadong.huawei.common.factory;


import com.yadong.huawei.module.base.BaseMvpFragment;
import com.yadong.huawei.module.fragment.appcomment.AppCommentFragment;
import com.yadong.huawei.module.fragment.appintroduction.AppIntroductionFragment;
import com.yadong.huawei.module.fragment.apprecommend.AppRecommendFragment;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class AppDetailFragmentFactory {

    /**
     * App详情_介绍
     */
    public static final int TAB_APP_INTRODUCTION = 0;

    /**
     * App详情_评论
     */
    public static final int TAB_APP_COMMENT = 1;

    /**
     * App详情_推荐
     */
    public static final int TAB_APP_RECOMMEND = 2;


    private static Map<Integer, BaseMvpFragment> mFragments = new HashMap<>();

    /**
     * 创建fragment
     *
     * @param index 索引
     */
    public static BaseMvpFragment createFragment(int index) {
        BaseMvpFragment fragment = mFragments.get(index);
        // 如果之前没有创建, 创建新的Fragment
        if (fragment == null) {
            switch (index) {
                case TAB_APP_INTRODUCTION:
                    fragment = new AppIntroductionFragment();
                    break;
                case TAB_APP_COMMENT:
                    fragment = new AppCommentFragment();
                    break;
                case TAB_APP_RECOMMEND:
                    fragment = new AppRecommendFragment();
                    break;

            }
            // 把创建的Fragment 存起来
            mFragments.put(index, fragment);
        }
        return fragment;
    }

    /**
     * 删除fragment
     *
     * @param index 索引
     */
    public static void removeFragment(int index) {
        if (mFragments.containsKey(index)) {
            mFragments.remove(index);
        }
    }

    /**
     * 删除所有的fragment
     */
    public static void removeAll() {
        if (mFragments != null) {
            mFragments.clear();
        }
    }

}
