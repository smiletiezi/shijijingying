package com.py.shijijingying.entity;

import java.util.List;

/**
 * Created by Harry on 2019/1/26.
 */
public class LogisticsBean {

    /**
     * message : ok
     * nu : 803655204357538227
     * ischeck : 1
     * com : yuantong
     * status : 200
     * data : [{"time":"2018-12-28 14:35:38","context":"快件已由洛阳鹏祥小区11栋1门101店菜鸟驿站代收，请及时取件，如有疑问请联系13014749551","ftime":"2018-12-28 14:35:38","areaCode":null,"areaName":null,"status":"在途"},{"time":"2018-12-28 13:37:55","context":"客户 签收人: 菜鸟驿站代签如未收到请打60229609 已签收 感谢使用圆通速递，期待再次为您服务","ftime":"2018-12-28 13:37:55","areaCode":null,"areaName":null,"status":"签收"},{"time":"2018-12-28 09:31:00","context":"【河南省洛阳市老城区中电阳光分部公司】 派件人: 黄振 派件中 派件员电话18211920541","ftime":"2018-12-28 09:31:00","areaCode":"CN410302000000","areaName":"河南,洛阳市,老城区","status":"派件"},{"time":"2018-12-27 22:17:07","context":"【河南省洛阳市公司】 已收入","ftime":"2018-12-27 22:17:07","areaCode":"CN410300000000","areaName":"河南,洛阳市","status":"在途"},{"time":"2018-12-27 16:30:32","context":"【郑州转运中心】 已发出 下一站 【河南省洛阳市公司】","ftime":"2018-12-27 16:30:32","areaCode":"CN410100000000","areaName":"河南,郑州市","status":"在途"},{"time":"2018-12-27 16:23:24","context":"【郑州转运中心】 已收入","ftime":"2018-12-27 16:23:24","areaCode":"CN410100000000","areaName":"河南,郑州市","status":"在途"},{"time":"2018-12-26 18:41:05","context":"【江苏省宿迁市公司】 已发出 下一站 【郑州转运中心】","ftime":"2018-12-26 18:41:05","areaCode":"CN321300000000","areaName":"江苏,宿迁市","status":"在途"},{"time":"2018-12-26 11:20:34","context":"【江苏省宿迁市公司】 已收件","ftime":"2018-12-26 11:20:34","areaCode":"CN321300000000","areaName":"江苏,宿迁市","status":"揽收"}]
     * state : 3
     * condition : D01
     */

    public String message;
    public String nu;
    public String ischeck;
    public String com;
    public String status;
    public String state;
    public String condition;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * time : 2018-12-28 14:35:38
         * context : 快件已由洛阳鹏祥小区11栋1门101店菜鸟驿站代收，请及时取件，如有疑问请联系13014749551
         * ftime : 2018-12-28 14:35:38
         * areaCode : null
         * areaName : null
         * status : 在途
         */

        public String time;
        public String context;
        public String ftime;
        public Object areaCode;
        public Object areaName;
        public String status;
    }
}
