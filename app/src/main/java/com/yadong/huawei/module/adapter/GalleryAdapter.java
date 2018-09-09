package com.yadong.huawei.module.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.yadong.huawei.utils.UIUtils;

import java.util.List;

/**
 *
 */

public class GalleryAdapter extends PagerAdapter {
    private List<String> mUrlList;
    private Context context = null;


    public GalleryAdapter(Context context, List<String> urlList) {
        this.context = context;
        this.mUrlList = urlList;
    }

    @Override
    public int getCount() {
        return mUrlList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup paramViewGroup, int position) {
        ImageView imageView = new PhotoView(this.context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        paramViewGroup.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Glide.with(UIUtils.getContext()).load(mUrlList.get(position)).into(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View paramView, Object paramObject) {
        return paramView == paramObject;
    }

    @Override
    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
        View localView = (View) paramObject;
        paramViewGroup.removeView(localView);
        recycleImage(localView);
    }

    private void recycleImage(View view) {
        if (!(view instanceof ImageView)) {
            ImageView imageView = (ImageView) view;
            Drawable drawable = imageView.getDrawable();
            if (drawable == null || !(drawable instanceof BitmapDrawable)) {
                return;
            } else {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap == null || bitmap.isRecycled()) {
                    return;
                }
                bitmap.recycle();
            }
        }
    }
}
