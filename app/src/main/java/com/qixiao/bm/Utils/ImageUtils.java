package com.qixiao.bm.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public  class ImageUtils {
    /**
     * 圆形图片，圆形图都是头像，所以可以将默认头像作为失败图
     */
    public static void loadCircleImg(final Context context, String path, ImageView imageView, int error) {
        RequestOptions options = RequestOptions
                .circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    public static void loadIntCircleImg(final Context context, int path, ImageView imageView) {
        RequestOptions options = RequestOptions
                .circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(path).apply(options).into(imageView);
    }
}
