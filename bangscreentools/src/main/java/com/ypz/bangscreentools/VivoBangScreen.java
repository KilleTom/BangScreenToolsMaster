package com.ypz.bangscreentools;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.ypz.bangscreentools.BangScreenTools.TAG;

/**
 * Created by 易庞宙 on 2018 2018/10/16 16:58
 * email: 1986545332@qq.com
 */
public class VivoBangScreen implements BangScreenSupport {
    private Class vivo;
    private Method vivoMethod;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean hasNotBangScreen(Window window) {
        if (window == null)
            return false;
        if (vivo == null) {
            ClassLoader vivoLoader = window.getContext().getClassLoader();
            try {
                vivo = vivoLoader.loadClass("android.util.FtFeature");
                vivoMethod = vivo.getMethod("isFeatureSupport", Integer.TYPE);
                return (boolean) vivoMethod.invoke(vivo, 0x00000020);
            } catch (ClassNotFoundException e) {
                logError(e);
                return false;
            } catch (NoSuchMethodException e) {
                logError(e);
                return false;
            } catch (IllegalAccessException e) {
                logError(e);
                return false;
            } catch (InvocationTargetException e) {
                logError(e);
                return false;
            }
        } else {
            if (vivoMethod == null) {
                try {
                    vivoMethod = vivo.getMethod("isFeatureSupport", Integer.TYPE);
                } catch (NoSuchMethodException e) {
                    logError(e);
                    return false;
                }
                try {
                    return (boolean) vivoMethod.invoke(vivo, 0x00000020);
                } catch (IllegalAccessException e) {
                    logError(e);
                    return false;
                } catch (InvocationTargetException e) {
                    logError(e);
                    return false;
                }
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<Rect> getBangSize(Window window) {
        List<Rect> result = new ArrayList<>();
        if (window == null) return result;
        Rect rect = new Rect();
        DisplayMetrics displayMetrics = window.getContext().getResources().getDisplayMetrics();
        int notchWidth = (int) TypedValue.applyDimension(1, 100.0F, displayMetrics);
        int notchHeight = (int) TypedValue.applyDimension(1, 27.0F, displayMetrics);
        rect.left = (displayMetrics.widthPixels - notchWidth) / 2;
        rect.right = rect.left + notchWidth;
        rect.top = 0;
        rect.bottom = notchHeight;
        result.add(rect);
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void extendStatusCutout(Window window, Context context) {
        if (window == null) return;
        Log.i(TAG, "next");
        /*window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );*/
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setWindowLayoutBlockNotch(Window window) {
        if (window == null) return;
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = window.getDecorView();
        int systemUiVisibility = view.getSystemUiVisibility();
        systemUiVisibility &= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        systemUiVisibility &= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        view.setSystemUiVisibility(systemUiVisibility);
    }

    @Override
    public void transparentStatusCutout(Window window, Context context) {

    }

    @Override
    public void fullscreen(Window window, Context context) {

    }

    private void logError(Exception e) {
        Log.e(TAG, e.getMessage());
    }
}
