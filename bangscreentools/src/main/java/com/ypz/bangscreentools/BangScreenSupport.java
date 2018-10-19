package com.ypz.bangscreentools;

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


    void setWindowLayoutAroundNotch(Window window);

    void setWindowLayoutBlockNotch(Window window);
}
