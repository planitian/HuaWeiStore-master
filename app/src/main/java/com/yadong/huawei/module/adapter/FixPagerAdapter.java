package com.yadong.huawei.module.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * FragmentPagerAdapter和FragmentStatePagerAdapter的区别:
 * <p>
 * 1.FragmentPagerAdapter 继承自 PagerAdapter。相比通用的 PagerAdapter，该类更专注于每一页均为 Fragment 的情况。
 * 如文档所述，该类内的每一个生成的 Fragment 都将保存在内存之中，因此适用于那些相对静态的页，数量也比较少的那种；
 * 如果需要处理有很多页，并且数据动态性较大、占用内存较多的情况，应该使用FragmentStatePagerAdapter。
 * FragmentPagerAdapter 重载实现了几个必须的函数，因此来自 PagerAdapter 的函数，我们只需要实现 getCount()，即可。
 * 且，由于 FragmentPagerAdapter.instantiateItem() 的实现中，调用了一个新增的虚函数 getItem()，因此，我们还至少需要实现一个 getItem()。
 * 因此，总体上来说，相对于继承自 PagerAdapter，更方便一些。
 * <p>
 * <p>
 * 2.FragmentStatePagerAdapter 和前面的 FragmentPagerAdapter 一样，是继承自 PagerAdapter。
 * 但是，和 FragmentPagerAdapter 不一样的是，正如其类名中的 'State' 所表明的含义一样，该 PagerAdapter
 * 的实现将只保留当前页面，当页面离开视线后，就会被消除，释放其资源；而在页面需要显示时，生成新的页面(就像 ListView 的实现一样)。
 * 这么实现的好处就是当拥有大量的页面时，不必在内存中占用大量的内存。
 * <p>
 * <p>
 * viewPager中填充fragment
 */
public class FixPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> titles;


    private List<Fragment> fragments = null;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public FixPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) super.instantiateItem(container, position);
        } catch (Exception e) {

        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }




    public void addData(String title, List<Fragment> fragments) {
        this.fragments = fragments;
        titles.add(title);
        notifyDataSetChanged();

    }



    public void removeData(String title, List<Fragment> fragments) {
        titles.remove(title);
        this.fragments = fragments;
        notifyDataSetChanged();

    }


    public void setItems(List<Fragment> fragments, List<String> mTitles) {
        this.fragments = fragments;
        this.titles = mTitles;
        notifyDataSetChanged();
    }

    public void setItems(List<Fragment> fragments, String[] mTitles) {
        this.fragments = fragments;
        this.titles = Arrays.asList(mTitles);
        notifyDataSetChanged();
    }

    public void addItem(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
        notifyDataSetChanged();
    }

    public void delItem(int position) {
        titles.remove(position);
        fragments.remove(position);
        notifyDataSetChanged();
    }

    public int delItem(String title) {
        int index = titles.indexOf(title);
        if (index != -1) {
            delItem(index);
        }
        return index;
    }
}
