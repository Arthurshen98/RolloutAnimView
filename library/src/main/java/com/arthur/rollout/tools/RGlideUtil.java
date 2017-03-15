package com.arthur.rollout.tools;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by arthur on 2017/3/13.
 * glide 工具
 */

public class RGlideUtil {
    /**
     * 加载图片
     *
     * @param context   上下文
     * @param url       路径
     * @param imageView view
     */
    public static void setImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).centerCrop().into(imageView);
    }

    /**
     * 清楚内存缓存,必须在UI线程调用
     */
    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }
}
