package com.qixiao.bm.base;

import android.Manifest;
import android.os.Environment;

/**
 * Description：常量类
 *
 * @author qingmanyu
 * @time 11/15/2017 2:29 PM
 * Comment：
 */
public class Const {

    public static final String APP_NAME = "SZ";
    /**
     * 本项目在SD卡的文件夹
     */
    public final static String PATH_ON_SD_CARD_OF_APP = Environment.getExternalStorageDirectory() + "/" + APP_NAME + "/";
    /**
     * 图片保存路径
     */
    public final static String PATH_ON_SD_CARD_OF_PIC = PATH_ON_SD_CARD_OF_APP + "pictures/";
    /**
     * 存放临时文件
     */
    public static final Integer DOWNLOAD_APK_FAILED = 0;
    public static final Integer PAGE_SIZE = 15;

    //分页
    public static final Integer DOWNLOAD_APK_SUC = 1;


    //拍照权限
    public static String[] CALL_PERMS = {Manifest.permission.CALL_PHONE};
    //上传图片权限
    public static String[] CALL_PICKER_PHOTO_PERMS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.READ_EXTERNAL_STORAGE};

    //定位权限
    public static String[] LOCATION_PERMS =
            {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};


    public static final Integer RC_LOCATION = 996;


    public static class PageStatus {
        public static final int NORMAL = 0;
        public static final int EMPTY = 1;
        public static final int ERROR = 2;
        public static final int NO_NETWORK = 3;
    }

    /**
     * 主菜单角标位置
     */
    public static final class MenuIndex {
        public static final int HOME = 1;
        public static final int TRAIN = 2;
        public static final int SIMTSTION = 3;
        public static final int ABOUT = 4;
    }


    /**
     * 支付状态
     */
    public static final class PAY_STATUE {

        public static final int PROCESS = 0;// 正在进行
        public static final int ACCOMPLISH = 1;// 已经完成
    }

    /**
     * 培训状态
     */
    public static final class TRAIN_STATUE {

        public static final int PROCESS = 1;// 报名中
        public static final int NOSTART = 0;// 未开始
        public static final int FILLED = 3;// 人数已满
        public static final int DEADLINE = 2;// 已结束
    }

    /**
     * 报名状态
     */
    public static final class SIGN_STATUE {

        public static final int NOSIGN = -2;// 未报名
        public static final int PROCESS = 0;// 未审核
        public static final int SUCCESS = 1;// 已审核
        public static final int FAILED = -1;// 审核不通过
    }


    public final static String INTENT_EXTRA_ID = "INTENT_EXTRA_ID";


    public static final Integer RC_PICKER_PHOTO = 998;
    public static final Integer RC_CHOOSE_PHOTO = 997;

    /**
     * app升级--下载ID存储
     */
    public static final String DOWNLOAD_APK_ID_PREFS = "prefs_consts.download_apk_id_prefs";

    public static class download {
        /**
         * 存放临时文件
         */
        public static final Integer DOWNLOAD_APK_FAILED = 0;
        public static final Integer DOWNLOAD_APK_SUC = 1;
    }
}
