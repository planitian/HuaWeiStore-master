package com.yadong.huawei.module.activity.clean;

import android.annotation.SuppressLint;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.module.base.BaseActivity;
import com.yadong.huawei.utils.AppInfoUtils;

import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CleanCacheActivity extends BaseActivity {


    @BindView(R.id.title_text)
    TextView mTitle;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.tv_scan_status)
    TextView mTvScanStatus;

    @BindView(R.id.ll_container)
    LinearLayout mContainer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_cache);
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
                .statusBarColor(R.color.white)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.tab_background)
                .init();
    }


    public void initViews() {
        mTitle.setText("清除缓存");
    }

    public void updateViews() {
        scanCache();
    }

    private void scanCache() {
        new Thread(){
            public void run() {
                PackageManager pm = getPackageManager();
                List<PackageInfo> packInfos = pm.getInstalledPackages(0);
                mProgressBar.setMax(packInfos.size());
                int total = 0;
                for(PackageInfo packinfo:packInfos){
                    String packname = packinfo.packageName;
                    try {
                        Method method = PackageManager.class.getMethod("getPackageSizeInfo", String.class,IPackageStatsObserver.class);
                        method.invoke(pm, packname,new MyObserver());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    final String appname = packinfo.applicationInfo.loadLabel(pm).toString();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mTvScanStatus.setText("正在扫描："+appname);
                        }
                    });
                    total++;
                    mProgressBar.setProgress(total);
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvScanStatus.setText("扫描完毕！");
                    }
                });
            };
        }.start();
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setContainer(msg);
        }
    };

    private void setContainer(Message msg) {
        final CacheInfo info = (CacheInfo) msg.obj;
        View view = View.inflate(getApplicationContext(), R.layout.item_cache_info, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_icon);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_size = (TextView) view.findViewById(R.id.tv_size);
        iv.setImageDrawable(info.icon);
        tv_name.setText(info.appname);
        tv_size.setText(Formatter.formatFileSize(getApplicationContext(), info.cachesize));
        mContainer.addView(view);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //引导用户进入APPInfo界面
                AppInfoUtils.showInstalledAppDetails(CleanCacheActivity.this,info.packname);

                //deleteApplicationCacheFiles
                //因为删除缓存的方法只有系统级别的应用才可以调用，所以只能通过引导用户进入系统的AppInfo界面，让用户手动删除缓存
                    /*PackageManager pm = getPackageManager();
                    Method[] methods = PackageManager.class.getMethods();
                    for(Method method:methods){
                        if("deleteApplicationCacheFiles".equals(method.getName())){
                            try {
                                method.invoke(pm, info.packname, new IPackageDataObserver.Stub() {
                                    @Override
                                    public void onRemoveCompleted(String packageName, boolean succeeded) throws RemoteException {
                                        System.out.println(succeeded);
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }*/
            }
        });
    }

    private class MyObserver extends IPackageStatsObserver.Stub{

        @Override
        public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
                throws RemoteException {
            //获取缓存
            long cache = pStats.cacheSize;
            if(cache>0){
                try {
                    CacheInfo cacheInfo = new CacheInfo();
                    PackageManager pm = getPackageManager();
                    //添加缓存信息到ui界面
                    //获取包名
                    cacheInfo.packname = pStats.packageName;
                    PackageInfo packInfo = pm.getPackageInfo(cacheInfo.packname, 0);
                    cacheInfo.cachesize = cache;
                    cacheInfo.appname = packInfo.applicationInfo.loadLabel(pm).toString();
                    cacheInfo.icon = packInfo.applicationInfo.loadIcon(pm);
                    Message msg = Message.obtain();
                    msg.obj = cacheInfo;
                    handler.sendMessage(msg);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class CacheInfo{
        String packname;
        String appname;
        Drawable icon;
        long cachesize;
    }


    @Override
    public void finish() {
        super.finish();
        exitAnim();
    }

    public void showLoading() {
        GlobalDialogManager.getInstance().show(getFragmentManager());
    }

    public void hideLoading() {
        GlobalDialogManager.getInstance().dismiss();
    }


}
