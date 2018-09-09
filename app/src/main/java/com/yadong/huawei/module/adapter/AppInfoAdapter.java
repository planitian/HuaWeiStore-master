package com.yadong.huawei.module.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.EmptyUtils;
import com.yadong.huawei.R;
import com.yadong.huawei.utils.DateUtils;
import com.yadong.huawei.model.remote.bean.AppInfo;
import com.yadong.huawei.module.widget.recyclerview.adapter.CommonAdapter;
import com.yadong.huawei.module.widget.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by houyadong on 2017/12/9.
 */

public class AppInfoAdapter extends CommonAdapter<AppInfo> {

    public AppInfoAdapter(Context context) {
        super(context, R.layout.install_record_item);
    }

    @Override
    protected void convert(final ViewHolder holder, final AppInfo appInfo, final int position) {
        holder.setImageDrawable(R.id.localpackage_item_icon_view,appInfo.getIcon());
        holder.setText(R.id.localpackage_item_name_view,appInfo.getName());
        holder.setText(R.id.localpackage_item_version_view,appInfo.getVersionName());
        holder.setText(R.id.localpackage_item_date_view, DateUtils.getDate(appInfo.getFirstInstallTime()));

        holder.setOnClickListener(R.id.ll_info, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout ll = (LinearLayout) holder.itemView.findViewById(R.id.uninstall_list_actions_layout);
                ll.setVisibility(ll.isShown() ? View.GONE : View.VISIBLE);
            }
        });

        holder.setOnClickListener(R.id.localpackage_option_button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //卸载应用
                if(EmptyUtils.isNotEmpty(mItemClickListener)){
                    mItemClickListener.onUninstallApp(position,mDatas.get(position).getPackageName());
                }

            }
        });
        holder.setOnClickListener(R.id.app_management_button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //管理应用
                if(EmptyUtils.isNotEmpty(mItemClickListener)){
                    mItemClickListener.onShowInstalledAppDetail(position,mDatas.get(position).getPackageName());
                }

            }
        });
    }




    private OnItemDetailClickListener mItemClickListener;

    public void setItemClickListener(OnItemDetailClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setData(List<AppInfo> appInfoList) {
        mDatas.clear();
        mDatas.addAll(appInfoList);
        notifyDataSetChanged();
    }

    public interface OnItemDetailClickListener{
        void onUninstallApp(int position,String packageName);
        void onShowInstalledAppDetail(int position,String packageName);
    }
}
