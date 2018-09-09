package com.yadong.huawei.common.factory;


import com.yadong.huawei.module.base.BaseFragment;
import com.yadong.huawei.module.fragment.category.CategoryFragment;
import com.yadong.huawei.module.fragment.manage.ManageFragment;
import com.yadong.huawei.module.fragment.mine.MineFragment;
import com.yadong.huawei.module.fragment.ranking.RankingFragment;
import com.yadong.huawei.module.fragment.recommend.RecommendFragment;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class FragmentFactory {

    /**
     * 推荐
     */
    public static final int TAB_RECOMMEND = 0;

    /**
     * 分类
     */
    public static final int TAB_CATEGORY = 1;

    /**
     * 排行
     */
    public static final int TAB_RANKING = 2;

    /**
     * 管理
     */
    public static final int TAB_MANAGE = 3;

    /**
     * 我的
     */
    public static final int TAB_MINE = 4;


    private static Map<Integer, BaseFragment> mFragments = new HashMap<>();

    /**
     * 创建fragment
     *
     * @param index 索引
     */
    public static BaseFragment createFragment(int index) {
        BaseFragment fragment = mFragments.get(index);
        // 如果之前没有创建, 创建新的Fragment
        if (fragment == null) {
            switch (index) {
                case TAB_RECOMMEND:
                    fragment = new RecommendFragment();
                    break;
                case TAB_CATEGORY:
                    fragment = new CategoryFragment();
                    break;
                case TAB_RANKING:
                    fragment = new RankingFragment();
                    break;
                case TAB_MANAGE:
                    fragment = new ManageFragment();
                    break;
                case TAB_MINE:
                    fragment = new MineFragment();
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
