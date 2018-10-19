package com.ypz.bangscreentools;

import android.os.Build;
import android.text.TextUtils;

/**
 * Created by 易庞宙 on 2018 2018/10/16 11:10
 * email: 1986545332@qq.com
 */
public class PhoneManufacturersJudgeTools {

    private static PhoneManufacturersJudgeTools PMJTools;

    public static PhoneManufacturersJudgeTools getPMJTools() {
        if (PMJTools==null){
            synchronized (PhoneManufacturersJudgeTools.class){
                PMJTools = new PhoneManufacturersJudgeTools();
            }
        }
        return PMJTools;
    }

    public final boolean isHuaWei() {
        String manufacturer = Build.MANUFACTURER;
        if (!TextUtils.isEmpty(manufacturer)){
            if (manufacturer.contains("HUAWEI")) return true;
        }
        return false;
    }

    public final boolean isMiui() {
        String manufacturer = getSystemProperty("ro.miui.ui.version.name");
        if (!TextUtils.isEmpty(manufacturer)){
            return true;
        }
        return false;
    }

    public final boolean isOppo() {
        String manufacturer = getSystemProperty("ro.product.brand");
        if (!TextUtils.isEmpty(manufacturer)){
            return true;
        }
        return false;
    }

    public final boolean isVivo() {
        String manufacturer = this.getSystemProperty("ro.vivo.os.name");
        if (!TextUtils.isEmpty(manufacturer)){
            return true;
        }
        return false;
    }

    private  String getSystemProperty(String propName) {
        return SystemProperties.getSingle().get(propName);
    }
}
