package com.ypz.bangscreentools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Window;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.ypz.bangscreentools.BangScreenTools.TAG;


/**
 * Created by 易庞宙 on 2018 2018/10/16 15:10
 * email: 1986545332@qq.com
 */
public class MiuiBangScreen implements BangScreenSupport {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean hasNotBangScreen(Window window) {
        return "1".equals(SystemProperties.getSingle().get("ro.miui.notch"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<Rect> getBangSize(Window window) {
        List<Rect> result = new ArrayList<>();
        if (window == null) return result;
        Context context = window.getContext();
        Resources resources = context.getResources();
        Rect rect = new Rect();
        if (resources != null) {
            rect.left = 0;
            rect.bottom = BangScreenTools.getBangScreenTools().getStatusBarHeight(context);
            rect.right = resources.getDisplayMetrics().widthPixels;
            rect.top = 0;
            result.add(rect);
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void extendStatusCutout(Window window, Context context) {
        if (window != null) {
            short flag = 1792;
            try {
                Method method = Window.class.getMethod("addExtraFlags", Integer.TYPE);
                method.invoke(window, (int) flag);
                window.setStatusBarColor(Color.parseColor("#D9D9D9"));
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setWindowLayoutBlockNotch(Window window) {
        if (window == null) return;
        short flag = 1792;
        try {
            Method method = Window.class.getMethod("clearExtraFlags", Integer.TYPE);
            method.invoke(window, (int) flag);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void transparentStatusCutout(Window window, Context context) {
        if (window == null) return;
        short flag = 1792;
        try {
            Method method = Window.class.getMethod("clearExtraFlags", Integer.TYPE);
            method.invoke(window, (int) flag);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void fullscreen(Window window, Context context) {
        if (window == null) return;
        short flag = 1792;
        try {
            Method method = Window.class.getMethod("clearExtraFlags", Integer.TYPE);
            method.invoke(window, (int) flag);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
