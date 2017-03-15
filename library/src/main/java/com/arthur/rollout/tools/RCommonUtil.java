package com.arthur.rollout.tools;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by arthur on 2017/3/13.
 * arthurShen
 */

public class RCommonUtil {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕高度
     *
     * @param context 环境
     * @return 高度
     */
    public static float getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        if (dm.heightPixels > 0) {
            return dm.heightPixels;
        }else {
            return px2dip(context, 1920);
        }
    }

    public static float getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        if (dm.widthPixels > 0) {
            return dm.widthPixels;
        }else {
            return px2dip(context, 1080);
        }
    }
}
