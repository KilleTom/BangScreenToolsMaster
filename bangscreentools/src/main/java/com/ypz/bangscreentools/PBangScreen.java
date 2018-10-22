package com.ypz.bangscreentools;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import static com.ypz.bangscreentools.BangScreenTools.TAG;


/**
 * Created by 易庞宙 on 2018 2018/10/16 11:46
 * email: 1986545332@qq.com
 */
public class PBangScreen implements BangScreenSupport {

    @RequiresApi(api = 28)
    @Override
    public boolean hasNotBangScreen(Window window) {
        if (window == null) return false;

        View decorView = window.getDecorView();
        if (decorView == null) return false;
        WindowInsets insets = decorView.getRootWindowInsets();
        if (insets == null) {
            Log.i(TAG, "insets == null");
            return false;
        } else {
            DisplayCutout dct = insets.getDisplayCutout();
            Log.i(TAG, String.valueOf(dct == null) + "dct");
            if (dct == null) return false;
            List<Rect> rects = dct.getBoundingRects();
            return rects != null && rects.size() != 0;
        }
    }

    @RequiresApi(api = 28)
    @Override
    public List<Rect> getBangSize(Window window) {
        List<Rect> result = new ArrayList<>();
        if (window == null) return result;
        View decorView = window.getDecorView();
        if (decorView == null) return result;
        WindowInsets insets = decorView.getRootWindowInsets();
        if (insets != null) {
            DisplayCutout dct = insets.getDisplayCutout();
            if (dct != null) {
                result.addAll(dct.getBoundingRects());
            }
            return result;
        } else {
            return result;
        }
    }

    @RequiresApi(api = 28)
    @Override
    public void extendStatusCutout(Window window, Context context) {
        if (window == null) return;
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        window.setAttributes(attributes);
    }

    @RequiresApi(api = 28)
    @Override
    public void setWindowLayoutBlockNotch(Window window) {
        if (window == null) return;
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
        window.setAttributes(attributes);
    }

    @RequiresApi(api = 28)
    @Override
    public void transparentStatusCutout(Window window, Context context) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        window.setAttributes(attributes);
    }

    @RequiresApi(api = 28)
    @Override
    public void fullscreen(Window window, Context context) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        window.setAttributes(attributes);
    }
}
