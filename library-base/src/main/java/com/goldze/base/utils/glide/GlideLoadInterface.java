package com.goldze.base.utils.glide;

import android.content.Context;
import android.widget.ImageView;

/**
 * description:
 * author:created by Andy on 2019/6/20 0020 15:33
 * email:zsp872126510@gmail.com
 */
public interface GlideLoadInterface {
    void glideLoad(Context context, Object url, ImageView view, int default_image, int radius);
}
