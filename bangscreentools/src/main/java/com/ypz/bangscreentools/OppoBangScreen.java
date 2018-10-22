package com.ypz.bangscreentools;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import static com.ypz.bangscreentools.BangScreenTools.TAG;

/**
 * Created by 易庞宙 on 2018 2018/10/16 16:03
 * email: 1986545332@qq.com
 */
public class OppoBangScreen implements BangScreenSupport {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean hasNotBangScreen(Window window) {
        if (window == null) return false;
        return window.getContext().getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    //目前Oppo刘海屏机型尺寸规格都是统一的,显示屏宽度为1080px，高度为2280px,刘海区域宽度为324px, 高度为80px
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<Rect> getBangSize(Window window) {
        List<Rect> result = new ArrayList<>();
        if (window == null) return result;
        DisplayMetrics displayMetrics = window.getContext().getResources().getDisplayMetrics();
        Rect rect = new Rect();
        int width = 324;
        int height = 80;
        rect.left = (displayMetrics.widthPixels - width) / 2;
        rect.right = rect.left + width;
        rect.bottom = height;
        rect.top = 0;
        result.add(rect);
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void extendStatusCutout(Window window, Context context) {
        if (window==null) return;
        Log.i(TAG,"next");

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setWindowLayoutBlockNotch(Window window) {
        if (window == null) return;
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        systemUiVisibility &= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        systemUiVisibility &= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    @Override
    public void transparentStatusCutout(Window window, Context context) {

    }

    @Override
    public void fullscreen(Window window, Context context) {

    }
}
