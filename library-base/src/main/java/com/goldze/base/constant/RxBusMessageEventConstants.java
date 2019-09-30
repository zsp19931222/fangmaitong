package com.goldze.base.constant;

/**
 * description:
 * author:created by Andy on 2019/7/1 0001 10:43
 * email:zsp872126510@gmail.com
 */
public class RxBusMessageEventConstants {

    public final static String OPEN_GALLERY = "打开相册";
    public final static String OPEN_CAMERA = "打开相机";
    public final static String OPEN_ZXING = "打开二维码扫描";
    public final static String OPEN_ZXING_AGAIN = "重新打开二维码扫描";
    public final static String REFRESH_DATA = "刷新数据";
    public final static String REFRESH_HEAD = "刷新头像";
    public final static String RECEIVE_NEW_PUSH_MESSAGE = "接收到新推送消息";
    public final static String REFRESH_HOME_LIST = "刷新首页服务应用列表数据";
    public final static String MULTIPLE_CHOICE = "多选";
    public final static String CANCEL_MULTIPLE_CHOICE = "取消多选";
    public final static String DELETE_MESSAGE = "删除消息数据";
    public final static String DELETE = "删除";
    public final static String MESSAGE_NUM = "消息条数";


    public final static String XF = "XF";
    public final static String SYDC = "SYDC";
    public final static String ZXZX = "最新资讯";
    public final static String ZPXX = "招聘信息";
    public final static String ZZFY = "跳转房源";


    /**
     * description:资讯事件消息
     * author: Andy
     * date: 2019/9/16 0016 15:59
     */
    public static class InformationRxMessage {
        public int position;

        public InformationRxMessage(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

    }

}
