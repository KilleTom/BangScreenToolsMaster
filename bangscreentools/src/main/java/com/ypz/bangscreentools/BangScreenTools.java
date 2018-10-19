package com.ypz.bangscreentools;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 易庞宙 on 2018 2018/10/16 09:59
 * email: 1986545332@qq.com
 */
public class BangScreenTools {

    private static BangScreenTools bangScreenTools;
    protected static final String TAG = "BangScreenTools";
    private final int systemCode = android.os.Build.VERSION.SDK_INT;
    private BangScreenSupport bangScreenSupport;
    private int statusBarHeight = -1;

    public static BangScreenTools getBangScreenTools() {
        if (bangScreenTools == null) {
            synchronized (BangScreenTools.class) {
                bangScreenTools = new BangScreenTools();
            }
        }
        return bangScreenTools;
    }

    private BangScreenTools(){
        bangScreenSupport = null;
    }

    /**
     * 判断是否是刘海屏
     */
    public boolean hasBangScreen(Window Window) {
        checkScreenSupportInit();
        if (bangScreenSupport==null) return false;
        return bangScreenSupport.hasNotBangScreen(Window);
    }

    /**
     * 获取刘海屏的大小
     */
    private List<Rect> getDisplayCutoutSize(Window window) {
        checkScreenSupportInit();
        return bangScreenSupport.getBangSize(window);
    }


    /**
     * 设置始终使用凹口屏区域
     */
    public void immersiveDisplayCutout(Window window) {
        checkScreenSupportInit();
        if (bangScreenSupport != null) bangScreenSupport.setWindowLayoutAroundNotch(window);

    }

    /**
     * 设置始终不使用凹口屏区域
     */
    public void blockDisplayCutout(Window window) {
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
                    public void setWindowLayoutAroundNotch(Window window) {

                    }

                    @Override
                    public void setWindowLayoutBlockNotch(Window window) {

                    }
                };
            } else if (systemCode < 28) {
                PhoneManufacturersJudgeTools PMJ = PhoneManufacturersJudgeTools.getPMJTools();
                if (PMJ.isHuaWei()) {
                    Log.i(TAG,"HuaWei");
                    bangScreenSupport = new HuaWeiBangScreen();
                } else if (PMJ.isMiui()) {
                    Log.i(TAG,"Miui");
                    bangScreenSupport = new MiuiBangScreen();
                }else if (PMJ.isVivo()) {
                    Log.i(TAG,"Vivo");
                    bangScreenSupport = new VivoBangScreen();
                } else if (PMJ.isOppo()) {
                    Log.i(TAG,"Oppo");
                    bangScreenSupport = new OppoBangScreen();
                }
            } else {
                bangScreenSupport = new PBangScreen();
            }
        }
    }

    protected final int getStatusBarHeight(Context context) {
        if (statusBarHeight != -1) return statusBarHeight;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resId);
        }
        return statusBarHeight;
    }
}
