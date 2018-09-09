package com.yadong.huawei.module.widget.loadsir.callback;

import android.content.Context;
import android.view.View;

/**
 */

public class SuccessCallback extends Callback {
    public SuccessCallback(View view, Context context, OnReloadListener onReloadListener) {
        super(view, context, onReloadListener);
    }

    @Override
    protected int onCreateView() {
        return 0;
    }
}
