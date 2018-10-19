package com.ypz.bangscreentools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.ypz.bangscreentools.BangScreenTools.TAG;


/**
 * Created by 易庞宙 on 2018 2018/10/16 10:51
 * email: 1986545332@qq.com
 */
public class HuaWeiBangScreen implements BangScreenSupport {
    private Class hwBangSizeUtil;
    private Field hwLayoutParamsFlags;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean hasNotBangScreen(Window window) {
        boolean result = false;
        try {
            ClassLoader huaWeiClassLoader = window.getContext().getClassLoader();
            Class HwNotchSizeUtil = huaWeiClassLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method method = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            return result = (boolean) method.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
            Log.e(TAG, "hasNotchInScreen Exception");
        } finally {
            return result;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<Rect> getBangSize(Window window) {
        ArrayList result = new ArrayList();
        if (window != null) {
            Rect rect = new Rect();
            try {
                Context context = window.getContext();
                if (hwBangSizeUtil == null && context != null) {
                    ClassLoader cl = context.getClassLoader();
                    hwBangSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
                }
                if (hwBangSizeUtil == null) {
                    return result;
                }
                Method get = hwBangSizeUtil.getMethod("getNotchSize");
                int[] ret = (int[]) get.invoke(hwBangSizeUtil);
                if (ret == null) {
                    return result;
                } else {
                    Resources resources = context.getResources();
                    if (resources != null) {
                        rect.left = (resources.getDisplayMetrics().widthPixels - ret[0]) / 2;
                        rect.bottom = ret[1];
                        rect.right = rect.left + ret[0];
                        rect.top = 0;
                        result.add(rect);
                    }
                    return result;
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                return result;
            }
        } else return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setWindowLayoutAroundNotch(Window window) {
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            try {
                /*if (hwLayoutParamsFlags == null) {
                    hwLayoutParamsFlags = layoutParams.getClass().getDeclaredField("hwFlags");
                    if (hwLayoutParamsFlags == null) {
                        return;
                    }
                    hwLayoutParamsFlags.setAccessible(true);
                }
                int old = (int) hwLayoutParamsFlags.get(layoutParams);*/
                Class layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx");
                Constructor con=layoutParamsExCls.getConstructor(ViewGroup.LayoutParams.class);
                Object layoutParamsExObj=con.newInstance(layoutParams);
                Method method=layoutParamsExCls.getMethod("addHwFlags", int.class);
                method.invoke(layoutParamsExObj, 0x00010000);
/*
                hwLayoutParamsFlags.set(layoutParams,old | 0x00010000);*/
            } catch (Exception e) {
                Log.e(TAG,e.getMessage());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setWindowLayoutBlockNotch(Window window) {
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            try {
                /*if (hwLayoutParamsFlags == null) {
                    hwLayoutParamsFlags = layoutParams.getClass().getDeclaredField("hwFlags");
                    if (hwLayoutParamsFlags == null) {
                        return;
                    }
                    hwLayoutParamsFlags.setAccessible(true);
                }
                int old = (int) hwLayoutParamsFlags.get(layoutParams);
                hwLayoutParamsFlags.set(layoutParams,old | 0x00010000);*/
                Class layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx");
                Constructor con=layoutParamsExCls.getConstructor(ViewGroup.LayoutParams.class);
                Object layoutParamsExObj=con.newInstance(layoutParams);
                Method method=layoutParamsExCls.getMethod("clearHwFlags", int.class);
                method.invoke(layoutParamsExObj, 0x00010000);
            } catch (Exception e) {
                Log.e(TAG,e.getMessage());
            }
        }
    }
}
