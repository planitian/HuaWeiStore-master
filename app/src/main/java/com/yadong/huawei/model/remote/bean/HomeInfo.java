package com.yadong.huawei.model.remote.bean;

import java.util.List;

/**
 *
 *
 */
public class HomeInfo {

    /**
     * code : 0
     * data :
     */
    public String code;
    public DataBean data;

    public static class DataBean {

        /**
         * head
         * body
         */
        public HeadBean head;
        public List<BodyBean> body;

        public static class HeadBean {

            public List<CategorieListBean> categorieList;
            public List<PromotionListBean> promotionList;

            public static class CategorieListBean {
                /**
                 * id : 1
                 * name : 美食
                 * pic : http://172.16.0.116:8080/TakeoutService/imgs/category/1.png
                 */
                public int id;
                public String name;
                public String pic;
            }

            public static class PromotionListBean {
                /**
                 * id : 1
                 * info : promotion info...
                 * pic : http://172.16.0.116:8080/TakeoutService/imgs/promotion/1.jpg
                 */
                public int id;
                public String info;
                public String pic;
            }

        }

        public static class BodyBean {

            /**
             * recommendInfos : []
             * seller : {"activityList":[],"deliveryFee":"","distance":"","ensure":"","id":1,"invoice":"","name":"外卖项目","pic":"http://172.16.0.116:8080/TakeoutService/imgs/category/1.png","recentVisit":"","sale":"","score":"5","sendPrice":0,"time":""}
             * type : 0
             */
            public SellerBean seller;
            public int type;
            public List<String> recommendInfos;

            public static class SellerBean {

                /**
                 * activityList : []
                 * deliveryFee :
                 * distance :
                 * ensure :
                 * id : 1
                 * invoice :
                 * name : 外卖项目
                 * pic : http://172.16.0.116:8080/TakeoutService/imgs/category/1.png
                 * recentVisit :
                 * sale :
                 * score : 5
                 * sendPrice : 0
                 * time :
                 */
                public String deliveryFee;
                public String distance;
                public String ensure;
                public int id;
                public String invoice;
                public String name;
                public String pic;
                public String recentVisit;
                public String sale;
                public String score;
                public int sendPrice;
                public String time;
                public List<?> activityList;
            }
        }
    }


}
