package com.qixiao.bm.Utils;

/**
 * 处理过快重复点击屏幕的问题
 */
public class ClickOnUtil {

    private static long lastClickTime = 0;

    /**
     * 是否快速双击
     *
     * @return
     */
    public static boolean isDoubleClickQuickly() {
        long currentTime = System.currentTimeMillis();
        if (lastClickTime == 0) {
            lastClickTime = currentTime;
            return false;
        }
        if ((currentTime - lastClickTime) < 1000) {
            return true;
        }
        lastClickTime = currentTime;
        return false;
    }

}
