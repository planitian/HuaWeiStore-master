package com.yadong.huawei.model.remote.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 店铺点菜 数据
 * <p>
 * <p>
 * 头
 * 条目
 * 条目
 * 条目
 * 条目
 */
public class StoreMealInfo {

    public String code;
    public List<GoodsTypeInfo> data;//头数据(其内部包含 普通条目数据)

    public static class GoodsTypeInfo {

        public int groupFirstIndex; // 点击某个头时，需要知道其分组容器中对应组元素中第一条的下标
        public int id;
        public String info;
        public String name;
        public List<GoodsInfo> list;   //普通条目数据

        public static class GoodsInfo {

            public int headId;// 进行分组操作,同组数据该字段 相同
            public int headIndex;  // 当前条目对应头数据所在集合的下标
            public int count;// 用于购物数量统计

            /**
             * bargainPrice : true
             * form : 肉末烧汁茄子+千叶豆腐+小食+时蔬+含粗粮米饭)
             * icon : http://172.16.0.116:8080/TakeoutService/imgs/goods/caiping_taocan.webp
             * id : 1001
             * monthSaleNum : 53
             * name : 肉末烧汁茄子+千叶豆腐套餐(含粗粮米饭)
             * new : false
             * newPrice : 13.899999618530273
             * oldPrice : 30
             */
            public boolean bargainPrice;
            public String form;
            public String icon;
            public int id;
            public int monthSaleNum;
            public String name;
            @SerializedName("new")
            public boolean newX;
            public double newPrice;
            public int oldPrice;
        }
    }
}
