package com.ypz.bangscreentools;

import android.content.Context;
import android.graphics.Rect;
import android.view.Window;

import java.util.List;

/**
 * Created by 易庞宙 on 2018 2018/10/16 10:47
 * email: 1986545332@qq.com
 */
public interface BangScreenSupport {
    boolean hasNotBangScreen(Window window);

    List<Rect> getBangSize(Window window);


    void extendStatusCutout(Window window, Context context);

    void setWindowLayoutBlockNotch(Window window);

    void transparentStatusCutout(Window window, Context context);

    void fullscreen(Window window, Context context);
}
