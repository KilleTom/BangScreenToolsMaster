package com.ypz.bangscreentools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

/**
 * Created by 易庞宙 on 2018 2018/10/16 09:59
 * email: 1986545332@qq.com
 */
public class BangScreenTools {

    private static BangScreenTools bangScreenTools;
    /**
     * The constant TAG.
     */
    protected static final String TAG = "BangScreenTools";
    private final int systemCode = android.os.Build.VERSION.SDK_INT;
    private BangScreenSupport bangScreenSupport;
    private boolean isHaveResult;
    private boolean isBangScree;
    private int statusBarHeight = -1;

    /**
     * Gets bang screen tools.
     *
     * @return the bang screen tools
     */
    public static BangScreenTools getBangScreenTools() {
        if (bangScreenTools == null) {
            synchronized (BangScreenTools.class) {
                bangScreenTools = new BangScreenTools();
            }
        }
        return bangScreenTools;
    }

    private BangScreenTools() {
        bangScreenSupport = null;
    }

    /**
     * 判断是否是刘海屏
     *
     * @param Window the window
     * @return the boolean
     */
    public boolean hasBangScreen(Window Window) {
        if (!isHaveResult) {
            if (bangScreenSupport == null) checkScreenSupportInit();
            if (bangScreenSupport == null) {
                isHaveResult = true;
                return isBangScree = false;
            } else return isBangScree = bangScreenSupport.hasNotBangScreen(Window);
        } else return isBangScree;
    }

    /**
     * 获取刘海屏的大小
     */
    private List<Rect> getDisplayCutoutSize(Window window) {
        if (bangScreenSupport == null)
            checkScreenSupportInit();
        if (bangScreenSupport == null) return new ArrayList<Rect>();
        return bangScreenSupport.getBangSize(window);
    }


    /**
     * 设置始终使用凹口屏区域并将状态栏隐藏
     * always to use bangScreen and layout extend to status bar,
     * and status bar was hide
     *
     * @param window  the window
     * @param context the context
     */
    public void extendStatusCutout(Window window, Context context) {
        if (bangScreenSupport == null)
            checkScreenSupportInit();
        if (window == null) return;
        if (bangScreenSupport == null) return;

        if (context instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
            Log.i(TAG, "isAppCompatActivity");
            if (actionBar != null) actionBar.hide();

        } else if (context instanceof Activity) {
            android.app.ActionBar actionBar = ((Activity) context).getActionBar();
            if (actionBar != null) actionBar.hide();
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | SYSTEM_UI_FLAG_LAYOUT_STABLE);
        bangScreenSupport.extendStatusCutout(window, context);
    }

    /**
     * to be used Activity onWindowFocusChanged . Pledge extendStatusCutout is work's
     * 使用在Activity的onWindowFocusChanged保证extendStatusCutout能够起作用
     *
     * @param window the window
     */
    public void windowChangeExtendStatusCutout(Window window) {
        if (bangScreenSupport == null)
            checkScreenSupportInit();
        if (window == null) return;
        window.getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | SYSTEM_UI_FLAG_IMMERSIVE_STICKY | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    /**
     * 设置始终使用凹口屏区域并将状态栏透明
     * * always to use bangScreen and layout extend to status bar,
     * and status bar was transparent
     *
     * @param window  the window
     * @param context the context
     */
    public void transparentStatusCutout(Window window, Context context) {
        if (bangScreenSupport == null)
            checkScreenSupportInit();
        if (window == null) return;
        if (bangScreenSupport == null) return;

        if (context instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
            Log.i(TAG, "isAppCompatActivity");
            if (actionBar != null) actionBar.hide();

        } else if (context instanceof Activity) {
            android.app.ActionBar actionBar = ((Activity) context).getActionBar();
            if (actionBar != null) actionBar.hide();

        }
        /*if (!(bangScreenSupport instanceof HuaWeiBangScreen)) {
         *//*  *//*
            //透明状态栏/导航栏 ;
            window.getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);
        }*/
        /*window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        window.getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.setStatusBarColor(Color.TRANSPARENT);
        bangScreenSupport.transparentStatusCutout(window, context);
    }

    /**
     * to be used Activity onWindowFocusChanged . Pledge extendStatusCutout is work's
     * 使用在Activity的onWindowFocusChanged保证transparentStatusCutout能够起作用
     *
     * @param window the window
     */
    public void windowChangeTransparentStatusCutout(Window window) {
        if (bangScreenSupport == null)
            checkScreenSupportInit();
        if (window == null) return;
        window.getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    /**
     * Fullscreen.
     *
     * @param window  the window
     * @param context the context
     */
    public void fullscreen(Window window, Context context) {
        if (bangScreenSupport == null)
            checkScreenSupportInit();
        if (window == null) return;
        if (bangScreenSupport == null) return;
        if (context instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
            Log.i(TAG, "isAppCompatActivity");
            if (actionBar != null) actionBar.hide();

        } else if (context instanceof Activity) {
            android.app.ActionBar actionBar = ((Activity) context).getActionBar();
            if (actionBar != null) actionBar.hide();
        }
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
         bangScreenSupport.fullscreen(window, context);
    }

    /**
     * to be used Activity onWindowFocusChanged . Pledge extendStatusCutout is work's
     * 使用在Activity的onWindowFocusChanged保证fullscreen能够起作用
     *
     * @param window the window
     */
    public void windowChangeFullscreen(Window window) {
        if (window == null) return;
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    /**
     * 设置始终不使用凹口屏区域
     * don't use bangScreen
     *
     * @param window the window
     */
    public void blockDisplayCutout(Window window) {
        if (bangScreenSupport == null)
            checkScreenSupportInit();
        if (bangScreenSupport != null) bangScreenSupport.setWindowLayoutBlockNotch(window);
    }

    private void checkScreenSupportInit() {
        if (bangScreenSupport == null) {
            if (systemCode < 26) {
                bangScreenSupport = new BangScreenSupport() {
                    @Override
                    public boolean hasNotBangScreen(Window window) {
                        return false;
                    }

                    @Override
                    public List<Rect> getBangSize(Window window) {
                        return new ArrayList<Rect>();
                    }

                    @Override
                    public void extendStatusCutout(Window window, Context context) {

                    }

                    @Override
                    public void setWindowLayoutBlockNotch(Window window) {

                    }

                    @Override
                    public void transparentStatusCutout(Window window, Context context) {

                    }

                    @Override
                    public void fullscreen(Window window, Context context) {

                    }
                };
            } else if (systemCode < 28) {
                PhoneManufacturersJudgeTools PMJ = PhoneManufacturersJudgeTools.getPMJTools();
                if (PMJ.isHuaWei()) {
                    Log.i(TAG, "HuaWei");
                    bangScreenSupport = new HuaWeiBangScreen();
                } else if (PMJ.isMiui()) {
                    Log.i(TAG, "Miui");
                    bangScreenSupport = new MiuiBangScreen();
                } else if (PMJ.isVivo()) {
                    Log.i(TAG, "Vivo");
                    bangScreenSupport = new VivoBangScreen();
                } else if (PMJ.isOppo()) {
                    Log.i(TAG, "Oppo");
                    bangScreenSupport = new OppoBangScreen();
                }
            } else {
                Log.i(TAG, "PB");
                bangScreenSupport = new PBangScreen();
            }
        }
    }

    /**
     * h
     * Gets status bar height.
     *
     * @param context the context
     * @return the status bar height
     */
    protected final int getStatusBarHeight(Context context) {
        if (statusBarHeight != -1) return statusBarHeight;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resId);
        }
        return statusBarHeight;
    }
}
